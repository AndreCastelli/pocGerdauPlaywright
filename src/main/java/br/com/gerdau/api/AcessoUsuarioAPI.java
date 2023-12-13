package br.com.gerdau.api;


import br.com.gerdau.engine.APIHelper;
import br.com.gerdau.enumValues.EnumApi;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import static br.com.gerdau.validations.ValidationsHelper.getCv_;
public class AcessoUsuarioAPI {


    private  Headers defaultHeader;

    APIHelper apiHelper = new APIHelper();
    public final String endpointLogin = "gmais-login.us-e1.cloudhub.io/api/v1/login";
    public final String endpointValidate = "gmais-login.us-e1.cloudhub.io/api/v1/validate";

    public String getCompleteEndpoint(String endpoint) {
        String ambiente = getCv_().getAmbiente();
        if (ambiente.equals("qa")|| ambiente.equals("dev")){
            return "https://"+ambiente+"-experience-gerdau-" + endpoint;
        }
        else{
            return "https://experience-gerdau-" + endpoint;
        }
    }

    public Response realizarLogin(String usuario, String senha)throws JSONException {
        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("username", usuario);
        bodyRequest.put("password", senha);

        Header clientSecretHeader = new Header("client_secret", "71D2386a8CA1460689a09d8dB75E4c40");
        Header clientIdHeader = new Header("client_id", "bcff0592bc66471e9f2e64df895230a1");

        Headers loginHeader = new Headers(clientSecretHeader, clientIdHeader);
        Response response = apiHelper.sendHttpRequest(getCompleteEndpoint(endpointLogin), bodyRequest.toString(), EnumApi.POST, loginHeader);

        getCv_().setLoginResponse(response);
        getCv_().setLastResponse(response);
        return response;
    }

    public Response validarToken(Response loginResponse) throws Exception {

        String token = loginResponse.jsonPath().getString("token");

        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("token", token);

        defaultHeader = apiHelper.getDefaultHeaders();
        Response validateResponse = apiHelper.sendHttpRequest(getCompleteEndpoint(endpointValidate), bodyRequest.toString(), EnumApi.POST, defaultHeader);
        getCv_().setValidateResponse(validateResponse);
        getCv_().setLastResponse(validateResponse);
        return validateResponse;
    }


}

