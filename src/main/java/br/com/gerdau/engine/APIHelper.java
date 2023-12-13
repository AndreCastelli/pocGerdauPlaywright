package br.com.gerdau.engine;

import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.gerdau.enumValues.EnumApi;
import br.com.gerdau.pages.gerdaumais.TelaInicioPage;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static br.com.gerdau.validations.ValidationsHelper.getCv_;
import static io.restassured.RestAssured.given;

public class APIHelper {
    LoggerHelper logger = new LoggerHelper(APIHelper.class);


    public Response sendHttpRequest(String endpoint, String requestBody, EnumApi method, Headers headers) {
        RequestSpecification request = given()
                .baseUri(endpoint)
                .headers(headers)
                .contentType(ContentType.JSON);
                //.log().all()
        logger.info(endpoint);

        if (method == EnumApi.GET) {
            return request.get();
        } else if (method == EnumApi.POST) {
            return request.body(requestBody)
                    .post();
        } else if (method == EnumApi.PUT) {
            return request.body(requestBody)
                    .put();
        } else if (method == EnumApi.DELETE) {
            return request.delete();
        } else if (method == EnumApi.PATCH) {
            return request.body(requestBody)
                    .patch();
        } else {
            throw new IllegalArgumentException("Método HTTP não suportado: " + method);
        }
    }

    public Response sendHttpRequest(String endpoint,Map<String, String> parameters, String requestBody, EnumApi method, Headers headers) {
        RequestSpecification request = given()
                .params(parameters)
                .baseUri(endpoint)
                .headers(headers)
                .contentType(ContentType.JSON);
                //.log().all();
        logger.info(endpoint);

        if (method == EnumApi.GET) {
            return request.get();
        } else if (method == EnumApi.POST) {
            return request.body(requestBody)
                    .post();
        } else if (method == EnumApi.PUT) {
            return request.body(requestBody)
                    .put();
        } else if (method == EnumApi.DELETE) {
            return request.delete();
        } else if (method == EnumApi.PATCH) {
            return request.body(requestBody)
                    .patch();
        } else {
            throw new IllegalArgumentException("Método HTTP não suportado: " + method);
        }
    }

    public Headers getDefaultHeaders() {
        Header tokenHeader = new Header("token", getCv_().getLoginResponse().jsonPath().getString("token"));
        Header clientSecretHeader = new Header("client_secret", "71D2386a8CA1460689a09d8dB75E4c40");
        Header clientIdHeader = new Header("client_id", "bcff0592bc66471e9f2e64df895230a1");

        return new Headers(tokenHeader, clientSecretHeader, clientIdHeader);
    }
}
