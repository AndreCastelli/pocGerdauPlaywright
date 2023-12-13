package br.com.gerdau.api;

import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.gerdau.pages.gerdaumais.TelaInicioPage;
import br.com.gerdau.utils.ValueUtils;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import static br.com.atomic.framework.helpers.ExcelHelper.*;
import static br.com.gerdau.validations.ValidationsHelper.getCv_;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ValidacoesAPI {

    LoggerHelper logger = new LoggerHelper(ValidacoesAPI.class);
    public void validarTokenCondizComUsario(Response validateResponse, String usuario) throws Exception {
        String usuarioTokenValidate = validateResponse.jsonPath().getString("name");

        logger.info("----------------------------------------------------------------");
        logger.info("Token gerado: "+getCv_().getLoginResponse().jsonPath().getString("token"));
        logger.info("----------------------------------------------------------------");

        AssertHelper.assertTrue("O usuário de login '"+usuario+"' deve ser igual ao usuário do token '"+usuarioTokenValidate+"'", usuario.toLowerCase().trim().equals(usuarioTokenValidate.toLowerCase().trim()));
    }
    public int quantidadeDeUsuariosListados(Response listagemUsuariosResponse) throws JSONException {
        JSONObject jsonObject = new JSONObject(listagemUsuariosResponse.getBody().asString());
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("items");

        int quantidadeUsuarios = jsonArray.length();

        logger.info("Número de usuários apresentados: " + quantidadeUsuarios);

        return quantidadeUsuarios;
    }

    public List <String> nomeDosUsuariosListados(Response listagemUsuariosResponse) throws JSONException {
        List <String> listaNomesUsuarios = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(listagemUsuariosResponse.getBody().asString());
        JSONArray jsonArrayItems = jsonObject.getJSONObject("data").getJSONArray("items");

        logger.info("Lista de usuários encontrados no sistema: ");
        for (int i = 0; i < jsonArrayItems.length(); i++) {
            String nomeUsuario = jsonArrayItems.getJSONObject(i).getString("name");
            listaNomesUsuarios.add(nomeUsuario);
            logger.info("Nome do usuário: " + nomeUsuario);
        }

        return listaNomesUsuarios;
    }
    public Map<String, List<String>> bpsDosUsuarios(Response listagemUsuariosResponse) throws JSONException {
        Map<String, List<String>> UsuariosBPs = new HashMap<>();

        JSONObject jsonObject = new JSONObject(listagemUsuariosResponse.getBody().asString());
        JSONArray jsonArrayItems = jsonObject.getJSONObject("data").getJSONArray("items");

        logger.info("Lista de BPs por usuários: ");
        for (int i = 0; i < jsonArrayItems.length(); i++) {
            String nomeUsuario = jsonArrayItems.getJSONObject(i).getString("name");
            logger.info("\n");
            logger.info("Nome do usuário: " + nomeUsuario);

            JSONArray jsonArrayClients = jsonArrayItems.getJSONObject(i).getJSONArray("clients");

            List<String> bpsUsuario = new ArrayList<>();
            if (jsonArrayClients.length() > 0) {
                logger.info("LISTA DE BPs DO USUÁRIO:");
                for (int j = 0; j < jsonArrayClients.length(); j++) {
                    //String nomeBp = jsonArrayClients.getJSONObject(j).getString("name");
                    String numeroBp = jsonArrayClients.getJSONObject(j).getString("bp");
                    //bpsUsuario.add(nomeBp + " - " + numeroBp);
                    bpsUsuario.add(numeroBp);
                    logger.info(j+1 +"º BP do usuário: " + numeroBp);
                }
            } else {
                logger.info("USUÁRIO SEM BPs");
            }

            UsuariosBPs.put(nomeUsuario, bpsUsuario);
        }

        return UsuariosBPs;
    }

    public Map<String, List<String>> permissoesDosUsuarios(Response listagemUsuariosResponse) throws JSONException {
        Map<String, List<String>> UsuariosPermissoes = new HashMap<>();

        JSONObject jsonObject = new JSONObject(listagemUsuariosResponse.getBody().asString());
        JSONArray jsonArrayItems = jsonObject.getJSONObject("data").getJSONArray("items");

        logger.info("Lista de bps por usuários: ");
        for (int i = 0; i < jsonArrayItems.length(); i++) {
            String nomeUsuario = jsonArrayItems.getJSONObject(i).getString("name");
            logger.info("\n");
            logger.info("Nome do usuário: " + nomeUsuario);

            JSONArray jsonArrayTabs = jsonArrayItems.getJSONObject(i).getJSONArray("tabs");

            List<String> permissoesUsuario = new ArrayList<>();
            logger.info("LISTA DE TABS DO USUÁRIO:");
            for (int j = 0; j < jsonArrayTabs.length(); j++) {
                String tabsUsuario = jsonArrayTabs.getString(j);
                permissoesUsuario.add(tabsUsuario);
                logger.info(tabsUsuario);
            }

            UsuariosPermissoes.put(nomeUsuario, permissoesUsuario);
        }

        return UsuariosPermissoes;
    }
    public Boolean validarStatusResponse(Response response, int statusEsperado) throws Exception {
        int statusCodeObtido = response.getStatusCode();
        if( !(statusCodeObtido==statusEsperado))
        {
            AssertHelper.assertTrue("O status obtido '"+statusCodeObtido+"' deve ser igual ao esperado: '"+statusEsperado+"'",  false);
        }
        logger.info("Status esperado: " +statusEsperado + " é igual ao status obtido: "+statusCodeObtido);
        return true;

    }

    public boolean abasListadasDoUsuario(Response response, List<String> listaAbasNecessarias) throws JSONException {
        List <String> listaAbasApresentadas = new ArrayList<>();
        ArrayList<String> abasValidas = new ArrayList<String>();
        ArrayList<String> abasInvalidas = new ArrayList<String>();

        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONObject("profile").getJSONArray("tabs");

        for (int i = 0; i < jsonArray.length(); i++) {
            String abasUsuario = jsonArray.getJSONObject(i).getString("tag");
            listaAbasApresentadas.add(abasUsuario);
        }

        logger.info("Lista de abas apresentadas:");
        ValueUtils.printListString(listaAbasApresentadas);

        logger.info("Lista de abas requeridas:");
        ValueUtils.printListString(listaAbasNecessarias);

        if(listaAbasNecessarias.size() > listaAbasApresentadas.size())
        {
            logger.info("Quantidade de abas necessárias para o usuário ("+listaAbasNecessarias.size()+") é maior que apresentado ("+listaAbasApresentadas.size()+")");
            return false;
        }
        else if(listaAbasApresentadas.size() > listaAbasNecessarias.size())
        {
            logger.info("Quantidade de abas necessárias para o usuário ("+listaAbasNecessarias.size()+") é menor que apresentado ("+listaAbasApresentadas.size()+")");
            return false;
        }
        else
        {
            for(String aba : listaAbasApresentadas)
            {
                if (listaAbasNecessarias.contains(aba))
                    abasValidas.add(aba);
                else
                    abasInvalidas.add(aba);
            }

            if(!abasInvalidas.isEmpty())
            {
                logger.info("Abas inválidas:");
                for(String invalida : abasInvalidas)
                    logger.info(invalida);

                return false;
            }
            else
            {
                logger.info("Abas válidas:");
                for(String valida : abasValidas)
                    logger.info(valida);

                return true;
            }

        }
    }

    public boolean bpsListadosDoUsuario(Response response, List<String> listaBPsNecessarios) throws JSONException {
        List <String> listaBPsApresentados = new ArrayList<>();
        ArrayList<String> bpsValidos = new ArrayList<String>();
        ArrayList<String> bpsInvalidos = new ArrayList<String>();

        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("bps");

        for (int i = 0; i < jsonArray.length(); i++) {
            String bpsUsuario = jsonArray.getJSONObject(i).getString("customerId");
            listaBPsApresentados.add(bpsUsuario);
        }

        logger.info("Lista de BPs apresentados:");
        ValueUtils.printListString(listaBPsApresentados);

        logger.info("Lista de BPs requeridos:");
        ValueUtils.printListString(listaBPsNecessarios);

        if(listaBPsNecessarios.size() > listaBPsApresentados.size())
        {
            logger.info("Quantidade de BPs necessários para o usuário ("+listaBPsNecessarios.size()+") é maior que apresentado ("+listaBPsApresentados.size()+")");
            return false;
        }
        else if(listaBPsApresentados.size() > listaBPsNecessarios.size())
        {
            logger.info("Quantidade de BPs necessários para o usuário ("+listaBPsNecessarios.size()+") é menor que apresentado ("+listaBPsApresentados.size()+")");
            return false;
        }
        else
        {
            for(String aba : listaBPsApresentados)
            {
                if (listaBPsNecessarios.contains(aba))
                    bpsValidos.add(aba);
                else
                    bpsInvalidos.add(aba);
            }

            if(!bpsInvalidos.isEmpty())
            {
                logger.info("BPs inválidos:");
                for(String invalida : bpsInvalidos)
                    logger.info(invalida);

                return false;
            }
            else
            {
                logger.info("BPs válidos:");
                for(String valida : bpsValidos)
                    logger.info(valida);

                return true;
            }

        }

    }

    public void perfilDoUsuarioListado(Response response, String perfilNecessario) throws Exception {
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        String perfilApresentado = jsonObject.getJSONObject("data").getJSONObject("profile").getString("profile");

        logger.info("Perfil necessário: " + perfilNecessario.toUpperCase());
        logger.info("Perfil apresentado: " + perfilApresentado.toUpperCase());

        AssertHelper.assertTrue("O perfil apresentado '"+perfilApresentado.toUpperCase()+"' deve ser igual ao perfil necessário '"+perfilNecessario+"'", perfilNecessario.toLowerCase().trim().equals(perfilApresentado.toLowerCase().trim()));
    }

    public void permissaoIsAdminDoUsuario(Response response, String isAdminNecessario) throws Exception {
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        String isAdminApresentado = jsonObject.getJSONObject("data").getString("isAdmin");

        logger.info("Permissão (IsAdmin) necessária: " + isAdminNecessario.toUpperCase());
        logger.info("Permissão (IsAdmin) apresentada: " + isAdminApresentado.toUpperCase());

        AssertHelper.assertTrue("A permissão apresentada (isAdmin = '"+isAdminApresentado.toUpperCase()+"') deve ser igual à permissão necessária (isAdmin = '"+isAdminNecessario.toUpperCase()+"')", isAdminNecessario.toLowerCase().trim().equals(isAdminApresentado.toLowerCase().trim()));
    }
    public void validacaoDePermissonamentoExcel(Map<String, List<String>>  UsuariosTabs) throws Exception {
        String caminhoExcel = getCv_().getExcelPathGerenciadorUsarios();
        startExcel(caminhoExcel);
        int quantidadeLinhas = getRowCount();

        String nomeUsuarioExcel;
        String tabsDoUsuarioExcel;

        for (int i = 1; i <= quantidadeLinhas; i++)
        {

            setRow(i);
            nomeUsuarioExcel= getCell("Nome");
            List<String> tabsDoUsuario = UsuariosTabs.get(nomeUsuarioExcel);
            tabsDoUsuarioExcel = getCell("Permissionamentos por aba");
            logger.info("\n");
            logger.info("Nome do usuário obtido na linha "+i+" do excel: " + nomeUsuarioExcel);
            logger.info("Tabs do usuário "+nomeUsuarioExcel+ " obtidas no excel: "+tabsDoUsuarioExcel);
            for(String tab: tabsDoUsuario)
            {
                AssertHelper.assertTrue("O usuário "+nomeUsuarioExcel+" deve conter a tab "+tab,tabsDoUsuarioExcel.toLowerCase().contains(tab.toLowerCase()));
            }


        }
    }
    public void validacaoDeBpsExcel(Map<String, List<String>>  UsuariosBPs) throws Exception {
        String caminhoExcel = getCv_().getExcelPathGerenciadorUsarios();
        startExcel(caminhoExcel);
        int quantidadeLinhas = getRowCount();

        String nomeUsuarioExcel;
        String bpsDoUsuarioExcel;

        for (int i = 1; i <= quantidadeLinhas; i++)
        {

            setRow(i);
            nomeUsuarioExcel= getCell("Nome");
            List<String> bpsDoUsuario = UsuariosBPs.get(nomeUsuarioExcel);
            bpsDoUsuarioExcel = getCell("BPs");
            logger.info("\n");
            logger.info("Nome do usuário obtido na linha "+i+" do excel: " + nomeUsuarioExcel);
            logger.info("BPs do usuário "+nomeUsuarioExcel+ " obtidos no excel: "+bpsDoUsuarioExcel);
            if(bpsDoUsuario.size()>0)
            {
                for(String bp: bpsDoUsuario)
                {
                    AssertHelper.assertTrue("O usuário "+nomeUsuarioExcel+" deve conter o BP "+bp+" ",bpsDoUsuarioExcel.toLowerCase().contains(bp.toLowerCase()));
                }
            }
            else
            {
                logger.info("O usuário "+nomeUsuarioExcel+" não possui BPs");
            }



        }
    }

    public void validacaoBpsCarteiraMIEmAberto(Response response, String BPNecessario) throws Exception {
        List <String> listaBPsApresentados = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        JSONArray jsonArray = jsonObject.getJSONArray("salesOrdersOnlineSumary");

        for (int i = 0; i < jsonArray.length(); i++) {
            String bpsUsuario = jsonArray.getJSONObject(i).getString("clientNumber");
            listaBPsApresentados.add(bpsUsuario);
        }

        logger.info("BP necessário: " + BPNecessario);

        for (String item : listaBPsApresentados) {
            AssertHelper.assertTrue("O BP necessário '"+BPNecessario+"' deve ser igual ao BP apresentado '" + item + "'", BPNecessario.contains(item));
        }
    }
    public void validacaoClienteCorretoEmConsulta(Response response, String cliente) throws Exception {

        logger.info("\n");
        logger.info("PERIODO CONSIDERADO NA REQUISIÇÃO: ÚLTIMOS 30 DIAS");

        logger.info("\n");
        JSONArray jsonArray = new JSONArray(response.getBody().asString());
        int qtdConsultas = jsonArray.length();
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONObject clientObject = jsonObject.getJSONObject("client");
            String clientName = clientObject.getString("name");
            if(!clientName.trim().toLowerCase().equals(cliente.trim().toLowerCase()))
            {
                AssertHelper.assertTrue("O cliente da consulta("+clientName+") deve ser igual ao cliente matriz do usuario("+cliente+")", clientName.trim().toLowerCase().equals(cliente.trim().toLowerCase()));
            }
        }
        logger.info("PARA TODAS AS CONSULTAS ("+ Integer.toString(qtdConsultas)+" Casos) O CLIENTE("+cliente+") ESTÁ CORRETO");

    }
    public void validacaoBpsCarteiraMIEmEstoque(Response response, String BPNecessario) throws Exception {
        List <String> listaBPsApresentados = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        JSONArray jsonArray = jsonObject.getJSONArray("stocks");

        for (int i = 0; i < jsonArray.length(); i++) {
            String bpsUsuario = jsonArray.getJSONObject(i).getString("soldToParty");
            listaBPsApresentados.add(bpsUsuario);
        }

        logger.info("BP necessário: " + BPNecessario);

        for (String item : listaBPsApresentados) {
            AssertHelper.assertTrue("O BP necessário '"+BPNecessario+"' deve ser igual ao BP apresentado '" + item + "'", BPNecessario.contains(item));
        }
    }

    public void validacaoBpsCarteiraMIFaturado(Response response, String BPNecessario) throws Exception {
        List <String> listaBPsApresentados = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        JSONArray jsonArray = jsonObject.getJSONArray("onlineBillings");

        for (int i = 0; i < jsonArray.length(); i++) {
            String bpsUsuario = jsonArray.getJSONObject(i).getString("clientNumber");
            listaBPsApresentados.add(bpsUsuario);
        }

        logger.info("BP necessário: " + BPNecessario);

        for (String item : listaBPsApresentados) {
            AssertHelper.assertTrue("O BP necessário '"+BPNecessario+"' deve ser igual ao BP apresentado '" + item + "'", BPNecessario.contains(item));
        }
    }

    public void validacaoBpsFinancasMIVerLimites(Response response, String bpNecessario) throws Exception {
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        String bpApresentado = jsonObject.getJSONArray("credit").getJSONObject(0).getString("clientNumber");

        logger.info("BP necessário: " + bpNecessario);
        logger.info("BP apresentado: " + bpApresentado);

        AssertHelper.assertTrue("O BP apresentado '"+bpApresentado+"' deve ser igual ao BP necessário '"+bpNecessario+"'", bpNecessario.toLowerCase().trim().equals(bpApresentado.toLowerCase().trim()));
    }

}

