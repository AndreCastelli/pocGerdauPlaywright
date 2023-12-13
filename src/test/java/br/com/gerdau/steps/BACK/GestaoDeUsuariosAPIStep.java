package br.com.gerdau.steps.back;

import br.com.gerdau.api.GestaoDeUsuariosAPI;
import br.com.gerdau.api.ValidacoesAPI;
import io.cucumber.java.pt.*;
import org.codehaus.jettison.json.JSONException;

import java.util.List;
import java.util.Map;

import static br.com.gerdau.validations.ValidationsHelper.getCv_;

public class GestaoDeUsuariosAPIStep {
    GestaoDeUsuariosAPI userControl = new GestaoDeUsuariosAPI();
    ValidacoesAPI apiValidator = new ValidacoesAPI();

    @E("envio a requisicao de listagem dos usuarios")
    public void envioARequisicaoDeListagemDosUsuarios() throws JSONException {
        userControl.listagemTodosUsuarios(getCv_().getLoginResponse());
    }

    @Entao("o retorno da quantidadade dos usuarios deve ser apresentado")
    public void oRetornoDaQuantidadadeDosUsuariosDeveSerApresentado() throws JSONException {
        apiValidator.quantidadeDeUsuariosListados(getCv_().getListAllResponse());
    }
    @Entao("a listagem de nome dos usuarios deve ser apresentada")
    public void aListagemDeNomeDosUsuariosDeveSerApresentada() throws JSONException {
        apiValidator.nomeDosUsuariosListados(getCv_().getListAllResponse());
    }
    
    @Entao("a listagem de BPs dos usuarios do excel deve ser igual a do retorno da API")
    public void aListagemDeBpsPorUsuariosDeveSerApresentada() throws Exception {
        Map<String, List<String>>  UsuariosBPs = apiValidator.bpsDosUsuarios(getCv_().getListAllResponse());
        apiValidator.validacaoDeBpsExcel(UsuariosBPs);

    }

    @Entao("a listagem de permissoes dos usuarios do excel deve ser igual a do retorno da API")
    public void aListagemDePermissoesPorUsuariosDeveSerApresentada() throws Exception {
        Map<String, List<String>> UsuariosTabs = apiValidator.permissoesDosUsuarios(getCv_().getListAllResponse());
        apiValidator.validacaoDePermissonamentoExcel(UsuariosTabs);
    }


}
