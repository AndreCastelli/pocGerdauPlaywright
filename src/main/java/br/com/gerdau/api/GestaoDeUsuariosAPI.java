package br.com.gerdau.api;


import br.com.gerdau.engine.APIHelper;
import br.com.gerdau.enumValues.EnumApi;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;

import static br.com.gerdau.validations.ValidationsHelper.getCv_;

public class GestaoDeUsuariosAPI {
    private  Headers defaultHeader;
    APIHelper apiHelper = new APIHelper();
    public final String endpointListAll = "cxp.us-e1.cloudhub.io/api/v1/admin/users";


    public String getCompleteEndpoint(String endpoint)
    {
        String ambiente = getCv_().getAmbiente();

        if (ambiente.equals("qa")|| ambiente.equals("dev")){
            return "https://"+ambiente+"-experience-gerdau-" + endpoint;
        }
        else{
            return "https://experience-gerdau-" + endpoint;
        }
    }

    public Response listagemTodosUsuarios(Response loginResponse) throws JSONException {
        String accessTokenAdmin = loginResponse.jsonPath().getString("token");
        Header accessTokenHeader = new Header("access_token", accessTokenAdmin);
        Header clientSecretHeader = new Header("client_secret", "44064989A8774DCaA6939C2015E1Ec03");
        Header clientIdHeader = new Header("client_id", "2a1565142b974b0bbc8681cad74ef38b");
        Header skip = new Header("skip", "0");
        Header limit = new Header("limit", "500");
        Headers listAllHeader = new Headers(clientIdHeader, clientSecretHeader, accessTokenHeader, skip, limit);

        Response response = apiHelper.sendHttpRequest(getCompleteEndpoint(endpointListAll), "", EnumApi.GET, listAllHeader);
        getCv_().setListAllResponse(response);
        getCv_().setLastResponse(response);
        return response;
    }


}

