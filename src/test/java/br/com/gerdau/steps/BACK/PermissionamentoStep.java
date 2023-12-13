package br.com.gerdau.steps.back;

import br.com.atomic.framework.helpers.AssertHelper;
import br.com.gerdau.api.PermissionamentoUsuarioAPI;
import br.com.gerdau.api.ValidacoesAPI;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;

import java.util.Arrays;
import java.util.List;

import static br.com.gerdau.validations.ValidationsHelper.getCv_;

public class PermissionamentoStep {
    ValidacoesAPI apiValidator = new ValidacoesAPI();
    PermissionamentoUsuarioAPI permissionamentoUsuarioAPI = new PermissionamentoUsuarioAPI();
    private Response profileResponse;
    private Response carteiraMIEmAbertoResponse;
    private Response carteiraMIEmEstoqueResponse;
    private Response carteiraMIFaturadoResponse;
    private Response financasMIVerLimitesResponse;


    @E("envio a requisicao do profile de um usuário")
    public void envioARequisicaoDoProfileDeUmUsuário() throws JSONException {
        profileResponse = permissionamentoUsuarioAPI.listagemProfileDoUsuario(getCv_().getLoginResponse().jsonPath().getString("token"));
    }
    @Entao("verifico que o usuario possui acesso as {string} corretas")
    public void verificoQueOUsuarioPossuiAcessoAsCorretas(String abasNecessarias) throws Exception {
        List<String> listAbasNecessarias = Arrays.asList(abasNecessarias.trim().split(","));

        for(int i = 0; i < listAbasNecessarias.size(); i++)
            listAbasNecessarias.set(i, listAbasNecessarias.get(i).trim());

        AssertHelper.assertTrue("As abas do usuário devem ser apresentadas corretamente", apiValidator.abasListadasDoUsuario(profileResponse, listAbasNecessarias));
    }

    @Entao("verifico que o usuario possui acesso nos {string} corretos")
    public void verificoQueOUsuarioPossuiAcessoNosCorretos(String bpsNecessarios) throws Exception {
        List<String> listBPsNecessarios = Arrays.asList(bpsNecessarios.trim().split(","));

        for(int i = 0; i < listBPsNecessarios.size(); i++)
            listBPsNecessarios.set(i, listBPsNecessarios.get(i).trim());

        AssertHelper.assertTrue("Os BPs do usuário devem ser apresentados corretamente", apiValidator.bpsListadosDoUsuario(profileResponse, listBPsNecessarios));
    }

    @Entao("verifico que o perfil do usuario {string} esta correto")
    public void verificoQueOPerfilDoUsuarioEstaCorreto(String perfil) throws Exception {
        apiValidator.perfilDoUsuarioListado(profileResponse, perfil);
    }

    @Entao("verifico que a permissao \\(isAdmin) do usuario {string} esta correta")
    public void verificoQueAPermissaoIsAdminDoUsuarioEstaCorreta(String isAdminNecessario) throws Exception {
        apiValidator.permissaoIsAdminDoUsuario(profileResponse, isAdminNecessario);
    }

    @E("envio a requisicao de sales orders com o BP {string} e o idioma {string}")
    public void envioARequisicaoDeSalesOrdersComOBPEOIdioma(String bp, String idioma) throws JSONException {
        carteiraMIEmAbertoResponse = permissionamentoUsuarioAPI.listagemCarteiraMIEmAberto(getCv_().getLoginResponse().jsonPath().getString("token"), bp, idioma);
    }

    @Entao("verifico que o usuario possui acesso em Carteira \\(Em aberto) com o {string} correto")
    public void verificoQueOUsuarioPossuiAcessoEmCarteiraEmAbertoComOCorreto(String bp) throws Exception {
        apiValidator.validacaoBpsCarteiraMIEmAberto(carteiraMIEmAbertoResponse, bp);
    }

    @E("envio a requisicao de stock com o BP {string} e o idioma {string}")
    public void envioARequisicaoDeStockComOBPEOIdioma(String bp, String idioma) throws JSONException {
        carteiraMIEmEstoqueResponse = permissionamentoUsuarioAPI.listagemCarteiraMIEmEstoque(getCv_().getLoginResponse().jsonPath().getString("token"), bp, idioma);
    }

    @Entao("verifico que o usuario possui acesso em Carteira \\(Em estoque) com o {string} correto")
    public void verificoQueOUsuarioPossuiAcessoEmCarteiraEmEstoqueComOCorreto(String bp) throws Exception {
        apiValidator.validacaoBpsCarteiraMIEmEstoque(carteiraMIEmEstoqueResponse, bp);
    }


    @Entao("verifico que o usuario possui acesso em Carteira \\(Faturado) com o {string} correto")
    public void verificoQueOUsuarioPossuiAcessoEmCarteiraFaturadoComOCorreto(String bp) throws Exception {
        apiValidator.validacaoBpsCarteiraMIFaturado(carteiraMIFaturadoResponse, bp);
    }

    @E("envio a requisicao de limites de credito com o BP {string}")
    public void envioARequisicaoDeLimitesDeCreditoComOBP(String bp) throws JSONException {
        financasMIVerLimitesResponse = permissionamentoUsuarioAPI.listagemFinancasVerLimites(getCv_().getLoginResponse().jsonPath().getString("token"), bp);
    }

    @Entao("verifico que o usuario possui permissao ao consultar os limites de credito em Finanças com o {string} correto")
    public void verificoQueOUsuarioPossuiPermissaoAoConsultarOsLimitesDeCreditoEmFinançasComOCorreto(String bp) throws Exception {
        apiValidator.validacaoBpsFinancasMIVerLimites(financasMIVerLimitesResponse, bp);
    }

    @E("envio a requisicao de billed com o BP {string}, idioma {string}, data inicial {string} e data final")
    public void envioARequisicaoDeBilledComOBPIdiomaDataInicialEDataFinal(String bp, String idioma, String dataInicial) throws JSONException {
        carteiraMIFaturadoResponse = permissionamentoUsuarioAPI.listagemCarteiraMIFarturado(getCv_().getLoginResponse().jsonPath().getString("token"), bp, idioma, dataInicial);
    }
}
