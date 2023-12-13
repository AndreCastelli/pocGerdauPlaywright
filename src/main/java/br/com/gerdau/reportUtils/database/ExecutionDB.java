package br.com.gerdau.reportUtils.database;

import br.com.atomic.framework.helpers.PropertyHelper;
import br.com.gerdau.reportUtils.database.enumValues.ExecutionType;
import com.google.common.base.Strings;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ExecutionDB {


    public static JSONObject startExecution(ExecutionType executionType) throws JSONException {
        JSONObject executionJSON = new JSONObject();
        executionJSON.put("Execution_Type", executionType.get());
        executionJSON.put("Device_Runner", !Strings.isNullOrEmpty(System.getProperty("CI")) ? "ci" : System.getProperty("user.name"));

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(executionJSON.toString())
                .when()
                .post(PropertyHelper.getProperty("hostname") + "/execution");

        response.then()
                .statusCode(200);

        return new JSONObject(response.getBody().asString());
    }

    public static JSONObject endExecution(int idExecution) throws JSONException {
        JSONObject executionJSON = new JSONObject();
        executionJSON.put("Finish_At", true);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(executionJSON.toString())
                .when()
                .put(PropertyHelper.getProperty("hostname") + "/execution/"+idExecution);

        response.then()
                .statusCode(200);

        return new JSONObject(response.getBody().asString());
    }

    public static JSONObject getExecution(int idExecution) throws JSONException {

        Response response = RestAssured.given()
                .when()
                .get(PropertyHelper.getProperty("hostname") + "/execution/"+idExecution);

        response.then()
                .statusCode(200);

        return new JSONObject(response.getBody().asString());
    }

}
