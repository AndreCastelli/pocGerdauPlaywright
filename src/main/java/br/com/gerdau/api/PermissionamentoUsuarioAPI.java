package br.com.gerdau.api;


import br.com.atomic.framework.utils.DateUtils;
import br.com.gerdau.engine.APIHelper;
import br.com.gerdau.enumValues.EnumApi;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import static br.com.gerdau.validations.ValidationsHelper.getCv_;

public class PermissionamentoUsuarioAPI {
    APIHelper apiHelper = new APIHelper();

    public final String endpointProfile = "/api/v1/profile";
    public final String endpointCarteiraEmAberto = "mi/sales-orders-online-sumary";
    public final String endpointCarteiraEmEstoque = "stock/advanced";
    public final String endpointCarteiraFaturado = "sales-orders/mi/billed";

    public final String endpointFinancasVerLimites = "mi/financials/credit";


    public String getCompleteEndpoint(String endpoint)
    {
        String ambiente = getCv_().getAmbiente();

        if (ambiente.equals("qa")|| ambiente.equals("dev")){
            return "https://cxp-user-management-api-cxp-"+ambiente+".apps2.rosa-gerdau.bxnu.p1.openshiftapps.com" + endpoint;
        }
        else{
            return "https://cxp-user-management-api-cxp.apps2.rosa-gerdau.bxnu.p1.openshiftapps.com" + endpoint;
        }
    }

    public String getCompleteEndpointCarteiraMI(String endpoint)
    {
        String ambiente = getCv_().getAmbiente();

        if (ambiente.equals("qa")|| ambiente.equals("dev")){
            return "https://"+ambiente+"-experience-gerdau-cxp.us-e1.cloudhub.io/api/v1/" + endpoint;
        }
        else{
            return "https://experience-gerdau-cxp.us-e1.cloudhub.io/api/v1/" + endpoint;
        }
    }


    public Response listagemProfileDoUsuario(String accessTokenUsuario) throws JSONException {
        Header accessTokenHeader = new Header("Authorization", accessTokenUsuario);
        Headers listAllHeader = new Headers(accessTokenHeader);

        Response response = apiHelper.sendHttpRequest(getCompleteEndpoint(endpointProfile), "", EnumApi.POST, listAllHeader);
        getCv_().setListagemProfileResponse(response);
        getCv_().setLastResponse(response);
        return response;
    }

    public Response listagemCarteiraMIEmAberto(String accessTokenUsuario, String customerIds, String language) throws JSONException {
        Map<String, String> parameters = new HashMap<>();

        Header accessTokenHeader = new Header("access_token", accessTokenUsuario);
        Header clientSecretHeader = new Header("client_secret", "44064989A8774DCaA6939C2015E1Ec03");
        Header clientIdHeader = new Header("client_id", "2a1565142b974b0bbc8681cad74ef38b");
        Headers listAllHeader = new Headers(clientIdHeader, clientSecretHeader, accessTokenHeader);

        parameters.put("customerIds", customerIds);
        parameters.put("language", language);

        Response response = apiHelper.sendHttpRequest(getCompleteEndpointCarteiraMI(endpointCarteiraEmAberto), parameters, "", EnumApi.GET, listAllHeader);

        getCv_().setListagemCarteiraMIEmAbertoResponse(response);
        getCv_().setLastResponse(response);
        return response;
    }

    public Response listagemCarteiraMIEmEstoque(String accessTokenUsuario, String customerIds, String language) throws JSONException {
        Map<String, String> parameters = new HashMap<>();

        Header accessTokenHeader = new Header("access_token", accessTokenUsuario);
        Header clientSecretHeader = new Header("client_secret", "44064989A8774DCaA6939C2015E1Ec03");
        Header clientIdHeader = new Header("client_id", "2a1565142b974b0bbc8681cad74ef38b");
        Headers listAllHeader = new Headers(clientIdHeader, clientSecretHeader, accessTokenHeader);

        parameters.put("customerIds", customerIds);
        parameters.put("language", language);

        Response response = apiHelper.sendHttpRequest(getCompleteEndpointCarteiraMI(endpointCarteiraEmEstoque), parameters, "", EnumApi.GET, listAllHeader);

        getCv_().setListagemCarteiraMIEmEstoqueResponse(response);
        getCv_().setLastResponse(response);
        return response;
    }

    public Response listagemCarteiraMIFarturado(String accessTokenUsuario, String customerIds, String language, String dataInicial) throws JSONException {
        Map<String, String> parameters = new HashMap<>();
        String dataFinal = DateUtils.getCurrentYear() + DateUtils.getMonthNumber(DateUtils.getCurrentMonth()) + "27";

        Header accessTokenHeader = new Header("access_token", accessTokenUsuario);
        Header clientSecretHeader = new Header("client_secret", "44064989A8774DCaA6939C2015E1Ec03");
        Header clientIdHeader = new Header("client_id", "2a1565142b974b0bbc8681cad74ef38b");
        Headers listAllHeader = new Headers(clientIdHeader, clientSecretHeader, accessTokenHeader);

        parameters.put("customerIds", customerIds);
        parameters.put("language", language);
        parameters.put("startInvoiceDate", dataInicial);
        parameters.put("endInvoiceDate", dataFinal);

        Response response = apiHelper.sendHttpRequest(getCompleteEndpointCarteiraMI(endpointCarteiraFaturado), parameters, "", EnumApi.GET, listAllHeader);

        getCv_().setListagemCarteiraFaturadoResponse(response);
        getCv_().setLastResponse(response);
        return response;
    }

    public Response listagemFinancasVerLimites(String accessTokenUsuario, String customerIds) throws JSONException {
        Map<String, String> parameters = new HashMap<>();

        Header accessTokenHeader = new Header("access_token", accessTokenUsuario);
        Header clientSecretHeader = new Header("client_secret", "44064989A8774DCaA6939C2015E1Ec03");
        Header clientIdHeader = new Header("client_id", "2a1565142b974b0bbc8681cad74ef38b");
        Headers listAllHeader = new Headers(clientIdHeader, clientSecretHeader, accessTokenHeader);

        parameters.put("customerIds", customerIds);

        Response response = apiHelper.sendHttpRequest(getCompleteEndpointCarteiraMI(endpointFinancasVerLimites), parameters, "", EnumApi.GET, listAllHeader);

        getCv_().setListagemFinancasVerLimitesResponse(response);
        getCv_().setLastResponse(response);
        return response;
    }


}

