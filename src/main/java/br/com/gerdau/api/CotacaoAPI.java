package br.com.gerdau.api;


import br.com.atomic.framework.utils.DateUtils;
import br.com.gerdau.engine.APIHelper;
import br.com.gerdau.enumValues.EnumApi;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static br.com.gerdau.validations.ValidationsHelper.getCv_;

public class CotacaoAPI {


    private  Headers defaultHeader;

    APIHelper apiHelper = new APIHelper();
    public final String endpointAnalyses = "/v1/analyses";

    public String getCompleteEndpoint(String endpoint) {
        String ambiente = getCv_().getAmbiente();
        if (ambiente.equals("qa")|| ambiente.equals("dev")){
            return "https://"+ambiente+"-experience-gerdau-cxp.us-e1.cloudhub.io/api" + endpoint;
        }
        else{
            return "https://experience-gerdau-cxp.us-e1.cloudhub.io/api" + endpoint;
        }
    }

    public Response listagemConsultasPorClientes(String accessTokenUsuario) throws JSONException, ParseException {


        Header accessTokenHeader = new Header("access_token", accessTokenUsuario);
        Header clientSecretHeader = new Header("client_secret", "44064989A8774DCaA6939C2015E1Ec03");
        Header clientIdHeader = new Header("client_id", "2a1565142b974b0bbc8681cad74ef38b");

        Headers listAllHeader = new Headers(accessTokenHeader,clientSecretHeader,clientIdHeader);


        SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");

        Date startDateUnformatted = sdfInput.parse(DateUtils.increaseOrDecreaseDate(-30));
        String startDate = sdfOutput.format(startDateUnformatted);
        Date endDateUnformatted = sdfInput.parse(DateUtils.increaseOrDecreaseDate(0));
        String endDate = sdfOutput.format(endDateUnformatted);



        Map<String, String> parameters = new HashMap<>();
        parameters.put("hasPrice", "false");
        parameters.put("sort", "desc");
        parameters.put("start_date", startDate);
        parameters.put("end_date", endDate);
        parameters.put("my_step", "true");
        parameters.put("skip", "0");
        //parameters.put("limit", "500");
        parameters.put("count", "true");





        Response response = apiHelper.sendHttpRequest(getCompleteEndpoint(endpointAnalyses), parameters, "", EnumApi.GET, listAllHeader);
        getCv_().setConsultasPorClienteResponse(response);
        getCv_().setLastResponse(response);
        return response;
    }


}

