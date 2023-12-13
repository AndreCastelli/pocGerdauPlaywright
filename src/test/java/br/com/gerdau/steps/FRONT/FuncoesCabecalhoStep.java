package br.com.gerdau.steps.front;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.AssertHelper;
import br.com.gerdau.enumValues.UsuariosPadroes;
import br.com.gerdau.pages.gerdaumais.ConfiguracoesDoAdminPage;
import br.com.gerdau.pages.gerdaumais.GerenciadorUsuariosPage;
import br.com.gerdau.pages.gerdaumais.LoginPage;
import br.com.gerdau.pages.gerdaumais.elementosFixos.CabecalhoFixo;
import br.com.gerdau.utils.UsuarioPadroesUtils;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.Arrays;
import java.util.List;

import static br.com.gerdau.validations.ValidationsHelper.getCv_;

public class FuncoesCabecalhoStep {

    CabecalhoFixo cabecalhoFixo = new CabecalhoFixo(PlaywrightController.getPage());
    LoginPage loginPage = new LoginPage(PlaywrightController.getPage());
    ConfiguracoesDoAdminPage configuracoesAdmin = new ConfiguracoesDoAdminPage(PlaywrightController.getPage());


    @E("acesso a aba de cotação")
    public void acessoAAbaDeCotação() throws Exception {
        cabecalhoFixo.acessarTabCotacao();
    }

    @Quando("o cabeçalho é apresentado")
    public void oCabeçalhoÉApresentado() throws Exception {
        cabecalhoFixo.validarExistenciaCabecalho();
    }


    @Dado("acesso a aba de {string}")
    public void acessoAAbaDe(String tipoAba) throws Exception {
        switch (tipoAba.toLowerCase()){
            case "cotação":
                cabecalhoFixo.acessarTabCotacao();
                break;
        }
    }

    @Então("as {string} são listadas conforme permissão do {string}")
    public void asSãoListadasConformePermissãoDo(String abasPermitidas, String usuario) throws Exception {
        List<String> abasPermitidasList = Arrays.asList(abasPermitidas.trim().split(","));

        for(int i = 0; i < abasPermitidasList.size(); i++)
            abasPermitidasList.set(i, abasPermitidasList.get(i).trim());

//        AssertHelper.assertTrue("As abas necessárias ("+abasPermitidas+") do usuário "+usuario+" devem estar visíveis", cabecalhoFixo.validarExistenciaTab(abasPermitidasList));



    }

    @Quando("o modal do usuário é apresentado")
    public void oModalDoUsuárioÉApresentado() throws Exception {
        cabecalhoFixo.validarExistenciaModal();
    }

    @Então("os {string} do modal são listados conforme permissão do {string}")
    public void oDoModalSãoListadosConformePermissãoDo(String campos, String usuario) throws Exception {
        List<String> camposPermitidosList = Arrays.asList(campos.trim().split(","));

        for(int i = 0; i < camposPermitidosList.size(); i++)
            camposPermitidosList.set(i, camposPermitidosList.get(i).trim());

        AssertHelper.assertTrue("Os campos do modal ("+campos+") do usuário "+usuario+" devem estar visíveis", cabecalhoFixo.validarExistenciaCampos(camposPermitidosList));

    }

    @Dado("que realizo login com perfil {string} na plataforma GerdauMais")
    public void queRealizoLoginComPerfilNaPlataformaGerdauMais(String usuario) throws Exception {
        UsuariosPadroes usuarioEscolhido = UsuarioPadroesUtils.getUsuario(usuario);

        loginPage.acessarSiteGerdauMais();
        loginPage.realizarLoginGerdauMais(usuarioEscolhido.getEmail(), usuarioEscolhido.getSenha());

        getCv_().setUsuarioEscolhido(usuarioEscolhido);
    }
    @E("acesso o campo de Gerenciador de usuários")
    public void acessoOCampoDeGerenciadorDeUsuários() {
        GerenciadorUsuariosPage gerenciadorUsuariosPage = new GerenciadorUsuariosPage(PlaywrightController.getPage());
        gerenciadorUsuariosPage.acessarGerenciadorUsuarios();
    }

    @Então("os {string} devem ser apresentados corretamente")
    public void osDevemSerApresentadosCorretamente(String usuarios) throws Exception {
        GerenciadorUsuariosPage gerenciadorUsuariosPage = new GerenciadorUsuariosPage(PlaywrightController.getPage());
        List<String> usuariosPermitidosList = Arrays.asList(usuarios.trim().split(","));

        for(int i = 0; i < usuariosPermitidosList.size(); i++)
            usuariosPermitidosList.set(i, usuariosPermitidosList.get(i).trim());

        AssertHelper.assertTrue("Os ("+usuarios+") no gerenciador devem estar visíveis", gerenciadorUsuariosPage.validarGerenciadorUsuario(usuariosPermitidosList));
    }

    @E("acesso o campo de Congigurações de administrador")
    public void acessoOCampoDeCongiguraçõesDeAdministrador() throws Exception {
        configuracoesAdmin.acessarConfiguracoesDoAdministrador();
    }

    @Então("as funcionalidades {string} devem ser apresentados corretamente")
    public void asFuncionalidadesDevemSerApresentadosCorretamente(String funcionalidades) throws Exception {
        List<String> funcionalidadesPermitidasList = Arrays.asList(funcionalidades.trim().split(","));

        for(int i = 0; i < funcionalidadesPermitidasList.size(); i++)
            funcionalidadesPermitidasList.set(i, funcionalidadesPermitidasList.get(i).trim());

        AssertHelper.assertTrue("Os ("+funcionalidades+") no gerenciador devem estar visíveis", configuracoesAdmin.validarConfiguracoesDoAdministrador(funcionalidadesPermitidasList));
    }

    @Então("os {string} do supply devem ser apresentados corretamente")
    public void osDoSupplyDevemSerApresentadosCorretamente(String usuarios) throws Exception {
        GerenciadorUsuariosPage gerenciadorUsuariosPage = new GerenciadorUsuariosPage(PlaywrightController.getPage());
        List<String> usuariosPermitidosList = Arrays.asList(usuarios.trim().split(","));

        for(int i = 0; i < usuariosPermitidosList.size(); i++)
            usuariosPermitidosList.set(i, usuariosPermitidosList.get(i).trim());

        AssertHelper.assertTrue("Os ("+usuarios+") no gerenciador devem estar visíveis", gerenciadorUsuariosPage.validarSupplyGerenciadorUsuario(usuariosPermitidosList));
    }

    @E("realizo o download do excel")
    public void realizoODownloadDoExcel() {
        GerenciadorUsuariosPage gerenciadorUsuariosPage = new GerenciadorUsuariosPage(PlaywrightController.getPage());
        gerenciadorUsuariosPage.downloadExcelGerenciadorDeUsuarios();
    }
}
