package br.com.gerdau.steps;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.atomic.framework.helpers.PropertyHelper;
import br.com.atomic.framework.proton.ProtonHelper;
import br.com.atomic.framework.utils.FileUtilsAtomic;
import br.com.atomicsolutions.proton.RunStatus;
import br.com.gerdau.engine.CucumberHelper;
import br.com.gerdau.engine.CucumberScenario;
import br.com.gerdau.engine.EnvHelper;
import br.com.gerdau.reportUtils.database.ExecutionDB;
import br.com.gerdau.reportUtils.database.ExecutionItemDB;
import br.com.gerdau.reportUtils.database.enumValues.ExecutionType;
import br.com.gerdau.reportUtils.notification.TeamsNotification;
import br.com.gerdau.utils.EvidenceManager;
import br.com.gerdau.utils.FileUtils;
import br.com.gerdau.utils.ValueUtils;
import br.com.gerdau.utils.ZipUtils;
import br.com.gerdau.validations.ValidationsHelper;
import com.google.common.base.Strings;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Result;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static br.com.atomic.framework.helpers.PropertyHelper.getProperty;
import static br.com.atomic.framework.helpers.PropertyHelper.setProperty;
import static br.com.atomic.framework.proton.ProtonHelper.*;
import static br.com.gerdau.engine.CucumberHelper.getCucumberController_;
import static br.com.gerdau.reportUtils.database.enumValues.ExecutionStatus.PASSED;
import static br.com.gerdau.reportUtils.database.enumValues.ExecutionStatus.FAILED;
import static br.com.gerdau.utils.GeneralUtils.isCIExecution;

public class Hooks {

    static LoggerHelper logger = new LoggerHelper(Hooks.class);
    Boolean backendScenario;

    @Before
    public void setupWeb(Scenario scenario) throws Exception {

        backendScenario = scenario.getSourceTagNames().contains("@module_backend");

        int numeroUltimoCenarioExecutado = 0;

        if (CucumberHelper.isCucumberControllerInitialized()) {
            numeroUltimoCenarioExecutado = getCucumberController_().getNumeroCenarioCorrente() + 1;
        } else {
            CucumberHelper.initializeCucumberController_();
            getCucumberController_().setCurrentExecutionDate();
            getCucumberController_().setExecutionID(ExecutionDB.startExecution(ExecutionType.DEV).getInt("Id_Execution"));
            PropertyHelper.loadProperties();

            if (isProtonExecution()) {
                updateProtonRunStatus(RunStatus.RUNNING);
                startProtonScript();

                //          if(ProtonHelper.getDatasetCategory().toLowerCase().equals("prd"))
                //             EnvHelper.setPRD();
                //          else
                EnvHelper.setQA();

                logger.info("Execução via Proton. Dataset Run ID: " + ProtonHelper.getDatasetRunID());
            } else
                logger.info("Execução Local");



        }
        if (isCIExecution() && !backendScenario) {
            setProperty("playwright.headless", "true");
        }
        logger.info("\n");
        logger.info("Cenário " + (numeroUltimoCenarioExecutado + 1) + " ----------------------------------------------------------------");
        logger.info("[INÍCIO] Feature: " + getFeatureFileNameFromScenarioId(scenario));
        logger.info("[INÍCIO] Execução do cenário: " + scenario.getName());
        logger.info("[INÍCIO] ID: " + scenario.getId());
        logger.info("[INÍCIO] Tags: " + scenario.getSourceTagNames().toString());

        CucumberScenario cucumberScenario = new CucumberScenario(scenario.getId(), scenario.getName(), numeroUltimoCenarioExecutado, scenario.getSourceTagNames().toString(), getFeatureFileNameFromScenarioId(scenario));
        getCucumberController_().setCenario(cucumberScenario);
        getCucumberController_().setNumeroCenarioCorrente(numeroUltimoCenarioExecutado);

        JSONObject executionScenarioJSON = ExecutionItemDB.startCucumberScenario(getCucumberController_());
        getCucumberController_().getCenario().setExecutionItemID(executionScenarioJSON.getInt("Id_Execution_Item"));

        if (!backendScenario) {
            FileUtils.checkAndCreateFolder(PropertyHelper.getProperty("playwright.download.path"));
            EvidenceManager.newInstance();
            EvidenceManager.getReport().setCover("Feature: " + getFeatureFileNameFromScenarioId(scenario) + "\n" + "Cenário: " + scenario.getName(), "");
            PlaywrightController.enableRecordTrace();
            PlaywrightController.startRecordTrace();
        }

        //PropertyHelper.loadProperties();
        ValidationsHelper.initializeCv_();

    }


    @After
    public void tearDownScenarios(Scenario scenario) throws Exception {


        if (scenario.isFailed()) {
            getCucumberController_().getCenario().setPassed(false);

            logger.error("[" + scenario.getId() + "] Falha na execução do cenário '" + scenario.getName() + "'");

            if (PlaywrightController.getPage() != null && !backendScenario)
                //logger.takeScreenShot("["+scenario.getId()+"] FALHA - Tela de Erro", PlaywrightController.getPage());
                EvidenceManager.addStep(PlaywrightController.getPage(), "[" + scenario.getId() + "] FALHA - Tela de Erro");

            logger.error(getFailureMessage(scenario));
        } else {
            logger.info("[" + scenario.getId() + "] Cenário '" + scenario.getName() + "' executado com sucesso.");
            getCucumberController_().getCenario().setPassed(true);
        }

        getCucumberController_().addCenarioExecutado(getCucumberController_().getCenario());

        logger.info("[FIM] Execução do cenário: [" + scenario.getId() + "] " + scenario.getName());

        ExecutionItemDB.endCucumberScenario(getCucumberController_(), scenario.isFailed() ? FAILED : PASSED);


        if (!backendScenario) {
            logger.info("----------------------------------------------------------------");
            FileUtils.deleteDirectory(PropertyHelper.getProperty("report.images"));
            EvidenceManager.getReport().save(getCucumberController_().getCenario().isPassed());

            String defaultExecutionPath = PropertyHelper.getProperty("report.evidences") + File.separator + "Execucao_" + getCucumberController_().getCurrentExecutionDate();
            String defaultPassedPath = defaultExecutionPath + File.separator + "Passeds";
            String defaultFailedPath = defaultExecutionPath + File.separator + "Faileds";

            FileUtils.checkAndCreateFolder(defaultPassedPath);
            FileUtils.checkAndCreateFolder(defaultFailedPath);

            String traceviewFilePath = PlaywrightController.stopRecordTrace(scenario.isFailed() ? defaultFailedPath : defaultPassedPath, "Traceview_" + (getCucumberController_().getCenario().getNomeCenario().replaceAll("[\\\\/:*?\"<>|]", "") + ValueUtils.obterDataFormatada()));

            if (isCIExecution()) {
                if (!scenario.isFailed())
                    FileUtilsAtomic.deleteFile(traceviewFilePath);
            }

            List<Path> pathVideos = PlaywrightController.closePage();

            if (pathVideos.size() > 0) {
                for (Path videoPath : pathVideos) {
                    Path videoRename = Paths.get((scenario.isFailed() ? defaultFailedPath : defaultPassedPath) + File.separator + (getCucumberController_().getCenario().getNomeCenario().replaceAll("[\\\\/:*?\"<>|]", "") + ValueUtils.obterDataFormatada()) + ".webm");
                    Files.move(videoPath, videoRename, StandardCopyOption.REPLACE_EXISTING);
                }
            }

            //Move report PDF
            Path pdfStatusPath = Paths.get((scenario.isFailed() ? defaultFailedPath : defaultPassedPath) + File.separator + EvidenceManager.getReport().getReportPath().getFileName());
            Files.move(Paths.get(defaultExecutionPath + File.separator + EvidenceManager.getReport().getReportPath().getFileName()), pdfStatusPath, StandardCopyOption.REPLACE_EXISTING);

        }

    }

    @AfterAll
    public static void afterAll() throws JSONException {

        if (getCucumberController_() != null) {
            ArrayList<CucumberScenario> listaCenariosExecutados = getCucumberController_().getListaCenariosExecutados();
            int passeds = 0;
            int failed = 0;
            int totalCenarios = getCucumberController_().getNumeroCenarioCorrente() + 1;

            logger.info("\n\n============== Relatório Final ==============");
            logger.info("Fim da execução de " + totalCenarios + " cenário" + (totalCenarios > 1 ? "s" : ""));
            for (CucumberScenario cenario : listaCenariosExecutados) {
                if (cenario.isPassed()) {
                    logger.info("Sucesso na execução do cenário: " + cenario.getNomeCenario());
                    passeds++;
                } else {
                    logger.error("Falha na execução do cenário: " + cenario.getNomeCenario());
                    failed++;
                }
            }

            logger.info("Quantidade de cenários passed: " + passeds);
            logger.info("Quantidade de cenários failed: " + failed);
            if (failed == 0) {
                updateProtonRunStatus(RunStatus.PASSED);
                endProtonScript();
            } else {
                updateProtonRunStatus(RunStatus.FAILED);
            }
            ExecutionDB.endExecution(getCucumberController_().getExecutionID());
            if (isCIExecution()) {
                TeamsNotification.sendTeamsMessage(TeamsNotification.createResultsReportJSON(getCucumberController_()));
            }

//        File evidencesZip = new File(PropertyHelper.getProperty("report") + File.separator + "Evidences.zip");
//        if(evidencesZip.exists())
//           evidencesZip.delete();

//        ZipUtils.main();
        } else {
            logger.info("Nenhum cenário executado.");
        }
    }

    public String getFailureMessage(Scenario scenario) {
        Result failResult = null;

        try {
            // Get the delegate from the scenario
            Field delegate = scenario.getClass().getDeclaredField("delegate");
            delegate.setAccessible(true);
            TestCaseState testCaseState = (TestCaseState) delegate.get(scenario);

            // Get the test case results from the delegate
            Field stepResults = testCaseState.getClass().getDeclaredField("stepResults");
            stepResults.setAccessible(true);
            List<Result> results = (List<Result>) stepResults.get(testCaseState);

            for (Result result : results) {
                if (result.getStatus().name().equalsIgnoreCase("FAILED")) {
                    failResult = result;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return (failResult != null) ? failResult.getError().getMessage() : "";
    }

    private String getFeatureFileNameFromScenarioId(Scenario scenario) {
        String[] test = scenario.getUri().toString().split("/");
        String[] longFeatureName = test[test.length - 1].split("/.");
        return longFeatureName[0].replace(".feature", "");
    }
}