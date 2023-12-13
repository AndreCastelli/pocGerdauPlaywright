package br.com.gerdau.steps.back;

import br.com.gerdau.api.CotacaoAPI;
import br.com.gerdau.api.GestaoDeUsuariosAPI;
import br.com.gerdau.api.ValidacoesAPI;
import br.com.gerdau.enumValues.UsuariosPadroes;
import br.com.gerdau.utils.UsuarioPadroesUtils;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static br.com.gerdau.validations.ValidationsHelper.getCv_;

public class CotacaoAPIStep {
    CotacaoAPI cotacao = new CotacaoAPI();
    ValidacoesAPI apiValidator = new ValidacoesAPI();

    @E("envio a requisicao de busca de consultas de um usuario")
    public void envioARequisicaoDeBuscaDeConsultasDeUmUsuario() throws JSONException, ParseException {
        cotacao.listagemConsultasPorClientes(getCv_().getLoginResponse().jsonPath().getString("token"));

    }

    @Entao("verifico que o {string} das consultas esta correto para o usuario")
    public void verificoQueODasConsultasEstaCorretoParaOUsuario(String cliente) throws Exception {
        apiValidator.validacaoClienteCorretoEmConsulta(getCv_().getConsultasPorClienteResponse(),cliente);
    }
}
