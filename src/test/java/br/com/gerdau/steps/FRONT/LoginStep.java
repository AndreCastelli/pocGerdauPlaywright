package br.com.gerdau.steps.front;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.gerdau.enumValues.UsuariosPadroes;
import br.com.gerdau.pages.gerdaumais.LoginPage;
import br.com.gerdau.utils.UsuarioPadroesUtils;
import io.cucumber.java.pt.Dado;

import static br.com.gerdau.utils.ValueUtils.getValue;
import static br.com.gerdau.validations.ValidationsHelper.getCv_;

public class LoginStep {

    LoginPage loginPage = new LoginPage(PlaywrightController.getPage());

    @Dado("que realizo login na plataforma GerdauMais")
    public void queRealizoLoginNaPlataformaGerdauMais() throws Exception {
        loginPage.acessarSiteGerdauMais();
//        loginPage.realizarLoginGerdauMais(getValue("in_usuario", "rbtcxpgat@gerdau.com.br"), getValue("in_senha", "Isa@2022!1"));
        loginPage.realizarLoginGerdauMais(getValue("in_usuario", "rbtcxprtc@gerdau.com.br"), getValue("in_senha", "Isa@2022"));
    }

    @Dado("que realizo login com {string} na plataforma GerdauMais")
    public void queRealizoLoginComNaPlataformaGerdauMais(String usuario) throws Exception {
        UsuariosPadroes usuarioEscolhido = UsuarioPadroesUtils.getUsuario(usuario);

        loginPage.acessarSiteGerdauMais();
        loginPage.realizarLoginGerdauMais(usuarioEscolhido.getEmail(), usuarioEscolhido.getSenha());

        getCv_().setUsuarioEscolhido(usuarioEscolhido);
    }

    @Dado("que realizo login na plataforma GerdauMais com perfil {string} aleatório para cotação")
    public void queRealizoLoginNaPlataformaGerdauMaisComPerfilAleatórioParaCotação(String perfil) throws Exception {
        UsuariosPadroes usuarioEscolhido;

        if(perfil.toLowerCase().equals("vendedor"))
            usuarioEscolhido = UsuarioPadroesUtils.getUsuarioVendedorAleatorio_ParaCotacao();
        else
            usuarioEscolhido = UsuarioPadroesUtils.getUsuarioGerdauAleatorio_ParaCotacao();

        loginPage.acessarSiteGerdauMais();
        loginPage.realizarLoginGerdauMais(usuarioEscolhido.getEmail(), usuarioEscolhido.getSenha());

        getCv_().setUsuarioEscolhido(usuarioEscolhido);
        UsuarioPadroesUtils.setPerfil(perfil);

    }

    @Dado("que realizo login na plataforma GerdauMais com perfil {string} para cotação")
    public void queRealizoLoginNaPlataformaGerdauMaisComPerfilParaCotação(String perfil) throws Exception {
        loginPage.acessarSiteGerdauMais();
        loginPage.realizarLoginGerdauMais(getValue("in_usuario", "rbtcxpclient@gerdau.com.br"), getValue("in_senha", "Isa@2022"));
//        loginPage.realizarLoginGerdauMais(perfil,"Isa@2022");

    }
}
