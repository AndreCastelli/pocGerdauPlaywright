package br.com.gerdau.steps.back;
import static br.com.gerdau.validations.ValidationsHelper.getCv_;

import br.com.gerdau.api.AcessoUsuarioAPI;
import br.com.gerdau.api.ValidacoesAPI;
import br.com.gerdau.enumValues.UsuariosPadroes;
import br.com.gerdau.utils.UsuarioPadroesUtils;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;

public class AcessoAPIStep {

    AcessoUsuarioAPI userAccess = new AcessoUsuarioAPI();
    ValidacoesAPI apiValidator = new ValidacoesAPI();


    @Dado("que realizo o login no ambiente {string} com usuario {string} por API")
    @E("realizo o login no ambiente {string} com usuario {string} por API")
    public void queRealizoOLoginNoAmbienteComUsuarioPorAPI(String ambiente, String usuario) throws JSONException {
        getCv_().setAmbiente(ambiente.toLowerCase());
        //Insere as informações do usuário para a request
        UsuariosPadroes usuarioEscolhido = UsuarioPadroesUtils.getUsuario(usuario);
        userAccess.realizarLogin(usuarioEscolhido.getEmail(), usuarioEscolhido.getSenha());
    }
    @Quando("valido que a requisição retornou status de sucesso")
    public void validoQueARequisiçãoRetornouStatusDeSucesso() throws Exception {
        int statusCodeEsperado = 200;
        Response lastResponse = getCv_().getLastResponse();
        apiValidator.validarStatusResponse(lastResponse,statusCodeEsperado);
    }
    @E("busco o usuario atrelado ao token gerado")
    public void buscoOUsuarioAtreladoAoTokenGerado() throws Exception {
        userAccess.validarToken(getCv_().getLoginResponse());
    }
    @Então("verifico que o token gerado pertence ao {string}")
    public void verificarQueOTokenGeradoPertenceAo(String usuario) throws Exception {
        //Verificação do token
        apiValidator.validarTokenCondizComUsario(getCv_().getValidateResponse(), usuario);
    }


}
