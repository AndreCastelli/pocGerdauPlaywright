package br.com.gerdau.pages.gerdaumais;

import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.gerdau.utils.EvidenceManager;
import br.com.gerdau.utils.ValueUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

import static br.com.atomic.framework.playwright.PlaywrightElementHelper.waitUntilVisible;

public class PainelDeGestaoPage {
    private Page page;

    private Locator btnPainelDeGestao;
    private Locator txtNomeBlocos;

    LoggerHelper logger = new LoggerHelper(PainelDeGestaoPage.class);

    public PainelDeGestaoPage(Page page) {
        this.page = page;
        this.btnPainelDeGestao = page.locator("//*[contains(text(), 'Painel de gestão')]");
        this.txtNomeBlocos = page.locator("//*[contains(@class, 'typographycomponentstyle__H2-sc-1pm6mqw-3 eMnaoM')]");
    }


    //Permissionamento Painel de gestão
    public void acessarPainelDeGestao() throws Exception {
        //Apenas para chegar na tela de implantação
        waitUntilVisible(page, btnPainelDeGestao);
        btnPainelDeGestao.click();
        Thread.sleep(2000);
    }

    public boolean validarTextosPainelDeGestao(List<String> listaBlocosNecessarios) throws Exception {

        ArrayList<String> blocosValidos = new ArrayList<String>();
        ArrayList<String> blocosInvalidos = new ArrayList<String>();

        List<String> todosBlocos = txtNomeBlocos.allInnerTexts();

        logger.info("Lista de blocos apresentados:");
        ValueUtils.printListString(todosBlocos);

        logger.info("Lista de blocos requeridos:");
        ValueUtils.printListString(listaBlocosNecessarios);

        EvidenceManager.addStep(page, "Campos de blocos apresentados");

        if (todosBlocos.size() == listaBlocosNecessarios.size()) {

            for (String tab : todosBlocos) {
                if (listaBlocosNecessarios.contains(tab))
                    blocosValidos.add(tab);
                else
                    blocosInvalidos.add(tab);
            }

            if (blocosInvalidos.size() > 0) {
                logger.info("Campos de blocos inválidos:");
                for (String invalida : blocosInvalidos)
                    logger.info(invalida);

                return false;
            } else {
                logger.info("Campos de blocos válidos:");
                for (String valida : blocosValidos)
                    logger.info(valida);

                return true;
            }
        } else {
            return false;
        }
    }

    public void validarGraficosPainelDeGestao(int qtdGraficosNecessarios) throws Exception {
        int qtdGraficosApresentados = page.locator("//*[contains(@class, 'recharts-surface')]").count();

        logger.info("Quantidade de gráficos apresentados: " + qtdGraficosApresentados);
        logger.info("Lista de blocos requeridos: " + qtdGraficosNecessarios);

        EvidenceManager.addStep(page, "Gráficos apresentados");

        AssertHelper.assertTrue("A quantidade de gráficos exibidos deve ser igual à quantidade desejada." +
                " Valores apresentados: ("+qtdGraficosApresentados+"). Valores desejados: ("+qtdGraficosNecessarios+")", qtdGraficosNecessarios == qtdGraficosApresentados);

    }
}