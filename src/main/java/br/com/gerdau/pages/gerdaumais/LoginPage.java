package br.com.gerdau.pages.gerdaumais;

import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.gerdau.utils.EvidenceManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static br.com.atomic.framework.helpers.PropertyHelper.getProperty;
import static br.com.atomic.framework.playwright.PlaywrightElementHelper.waitUntilNotExist;


public class LoginPage
{
    private Page page;
    private Locator txtEmail;
    private  Locator txtSenha;
    private Locator btnFazerLogin;

    LoggerHelper logger = new LoggerHelper(LoginPage.class);
    public LoginPage(Page page)
    {
        this.page = page;
        this.txtEmail = page.getByTestId("email");
        this.txtSenha = page.getByTestId("password");
        this.btnFazerLogin = page.getByTestId("login-button");
    }

    public void acessarSiteGerdauMais(){
        page.navigate(getProperty("url.gerdaumais.qa"));
//        page.navigate(getProperty("url.gerdaumais.prd"));
    }

    public void realizarLoginGerdauMais(String usuario, String senha) throws Exception {
        txtEmail.fill(usuario);
        txtSenha.fill(senha);

        logger.info("Usuário "+usuario+" e senha preenchidos");
        //logger.takeScreenShot("UsuarioSenha", page);
        EvidenceManager.addStep(page, "Usuario e Senha");

        btnFazerLogin.click();

        waitUntilNotExist(page, txtEmail, 60);
    }
}
