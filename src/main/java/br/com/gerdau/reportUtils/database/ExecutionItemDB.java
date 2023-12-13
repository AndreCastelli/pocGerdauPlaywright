package br.com.gerdau.reportUtils.database;

import br.com.atomic.framework.helpers.PropertyHelper;
import br.com.gerdau.engine.CucumberController;
import br.com.gerdau.reportUtils.database.enumValues.ExecutionStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import static br.com.gerdau.reportUtils.database.enumValues.ExecutionStatus.IN_PROGRESS;

public class ExecutionItemDB {


    public static JSONObject startCucumberScenario(CucumberController cucumberExecution) throws JSONException {
        JSONObject executionJSON = new JSONObject();
        executionJSON.put("Id_Execution", cucumberExecution.getExecutionID());
        executionJSON.put("Feature_Name", cucumberExecution.getCenario().getFeatureName());
        executionJSON.put("Scenario_Name", cucumberExecution.getCenario().getNomeCenario());
        executionJSON.put("Tags", cucumberExecution.getCenario().getTags());
        executionJSON.put("Module", "");
        executionJSON.put("Status", IN_PROGRESS.get());
        executionJSON.put("Environment", "qa");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(executionJSON.toString())
                .when()
                .post(PropertyHelper.getProperty("hostname") + "/execution-item");

        response.then()
                .statusCode(200);

        return new JSONObject(response.getBody().asString());
    }

    public static JSONObject endCucumberScenario(CucumberController cucumberExecution, ExecutionStatus status) throws JSONException {
        JSONObject executionJSON = new JSONObject();
        executionJSON.put("Status", status.get());
        executionJSON.put("Finish_At", true);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(executionJSON.toString())
                .when()
                .put(PropertyHelper.getProperty("hostname") + "/execution-item/"+cucumberExecution.getCenario().getExecutionItemID());

        response.then()
                .statusCode(200);

        return new JSONObject(response.getBody().asString());
    }

    public static JSONObject getCucumberScenarioExecution(CucumberController cucumberExecution) throws JSONException {

        Response response = RestAssured.given()
                .when()
                .get(PropertyHelper.getProperty("hostname") + "/execution/"+cucumberExecution.getCenario().getExecutionItemID());

        response.then()
                .statusCode(200);

        return new JSONObject(response.getBody().asString());
    }

}
