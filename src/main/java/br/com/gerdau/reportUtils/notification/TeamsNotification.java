package br.com.gerdau.reportUtils.notification;

import br.com.atomic.framework.helpers.PropertyHelper;
import br.com.gerdau.engine.CucumberController;
import br.com.gerdau.engine.CucumberScenario;
import br.com.gerdau.reportUtils.database.enumValues.ExecutionType;
import com.google.common.base.Strings;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;

import static br.com.gerdau.engine.CucumberHelper.getCucumberController_;

public class TeamsNotification {

    public static void sendTeamsMessage(JSONObject messageJSON) throws JSONException {

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(messageJSON.toString())
                    .when()
                    .post(PropertyHelper.getProperty("report.notification.channel"));

            response.then()
                    .statusCode(200);
    }

    public static JSONObject createResultsReportJSON(CucumberController listCucumberScenarios) throws JSONException {

        ArrayList<CucumberScenario> listaCenariosExecutados = listCucumberScenarios.getListaCenariosExecutados();
        ArrayList<CucumberScenario> listaCenariosFailed = new ArrayList<>();
        int passeds = 0;
        int failed = 0;
        int totalCenarios = getCucumberController_().getNumeroCenarioCorrente()+1;

        for(CucumberScenario cenario : listaCenariosExecutados){
            if(cenario.isPassed())
                passeds++;
            else{
                failed++;
                listaCenariosFailed.add(cenario);
            }

        }

        JSONObject messageJSON = new JSONObject();
        messageJSON.put("@type", "MessageCard");
        messageJSON.put("@content", "http://schema.org/extensions");
        messageJSON.put("themeColor", "0076D7");
        messageJSON.put("summary", "Execuções Regressivo CXP");

        JSONArray sectionsJSONArray = new JSONArray();

        JSONObject sectionHeader = new JSONObject();
        sectionHeader.put("activityTitle", "Execuções Regressivo CXP");
        sectionHeader.put("activitySubtitle", "[ID:"+listCucumberScenarios.getExecutionID()+" | "+listCucumberScenarios.getCurrentExecutionDate()+ "]");

        JSONArray factsJSONArray = new JSONArray();

        JSONObject sectionTotalResults = new JSONObject();
        sectionTotalResults.put("name", "&#x1F3C1; Total de Execuções:");
        sectionTotalResults.put("value", totalCenarios);
        sectionTotalResults.put("markdown", true);
        factsJSONArray.put(sectionTotalResults);

        JSONObject sectionPassedResults = new JSONObject();
        sectionPassedResults.put("name", "&#x2705; Passeds:");
        sectionPassedResults.put("value", passeds);
        sectionPassedResults.put("markdown", true);
        factsJSONArray.put(sectionPassedResults);

        JSONObject sectionFailedResults = new JSONObject();
        sectionFailedResults.put("name","&#x274C; Faileds:");
        sectionFailedResults.put("value",failed);
        sectionFailedResults.put("markdown", true);
        factsJSONArray.put(sectionFailedResults);

        if(failed > 0) {
            String listaCenariosFailedString = "";
             for(CucumberScenario scenario : listaCenariosFailed){
               listaCenariosFailedString += scenario.getNomeCenario() + " | ";
            }

            JSONObject sectionFailed = new JSONObject();
            sectionFailed.put("name", "Cenários failed:");
            sectionFailed.put("value", listaCenariosFailed.toString());
            sectionFailed.put("markdown", true);
            factsJSONArray.put(sectionFailed);
        }

        sectionHeader.put("facts", factsJSONArray);
        sectionsJSONArray.put(sectionHeader);

        messageJSON.put("sections", sectionsJSONArray);
        messageJSON.put("markdown", true);

//        JSONArray sectionsJSONArray = new JSONArray();
//
//        JSONObject sectionSummary = new JSONObject();
//        sectionSummary.put("text", "# Resultado Execução Regressivo [ID:"+listCucumberScenarios.getExecutionID()+" | "+listCucumberScenarios.getCurrentExecutionDate()+ "]");
//        sectionSummary.put("markdown", true);
//        sectionsJSONArray.put(sectionSummary);
//
//        JSONObject sectionTotalResults = new JSONObject();
//        sectionTotalResults.put("text", "&#x1F3C1; **Total de Execuções**: "+totalCenarios+"\n"+
//                                    "&#x2705; **Passeds**: "+passeds+"\n"+
//                                    "&#x274C; **Faileds**: "+failed);
//        sectionTotalResults.put("markdown", true);
//        sectionsJSONArray.put(sectionTotalResults);
//
//        if(failed > 0) {
//            JSONObject sectionFailed = new JSONObject();
//            sectionFailed.put("text", "## Cenários failed:");
//            sectionFailed.put("text", listaCenariosFailed.toString());
//            sectionFailed.put("markdown", true);
//            sectionsJSONArray.put(sectionFailed);
//        }
//
//        messageJSON.put("sections", sectionsJSONArray);

        System.out.println(messageJSON.toString());
        return messageJSON;
    }
}
