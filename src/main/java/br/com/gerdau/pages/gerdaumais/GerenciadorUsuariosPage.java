package br.com.gerdau.pages.gerdaumais;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.atomic.framework.helpers.PropertyHelper;
import br.com.atomic.framework.playwright.PlaywrightElementHelper;
import br.com.gerdau.utils.EvidenceManager;
import br.com.gerdau.utils.ValueUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Download;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;
import static br.com.atomic.framework.playwright.PlaywrightElementHelper.elementExist;
import static br.com.gerdau.validations.ValidationsHelper.getCv_;


public class GerenciadorUsuariosPage
{
    private Page page;
    private Locator btnEngrenagem;

    private Locator listTabCampos;
    private Locator btnGerenciadorUsuarios;
    private Locator listTabUsuarios;
    private Locator listTabUsuariosEscolhido;
    private Locator listTabCliente;
    private Locator btnDownloadExcel;

    LoggerHelper logger = new LoggerHelper(GerenciadorUsuariosPage.class);
    public GerenciadorUsuariosPage(Page page)
    {
        this.page = page;
        this.btnEngrenagem = page.locator("//*[contains(@class, 'svg-inline--fa fa-cog fa-w-16 fa-fw fa-lg fa-iconcomponentstyle__FontAwesomeIconStyled-sc-1ht276p-0 jsNAXZ')]");
        this.listTabCampos = page.locator("//*[contains(@class, 'typographycomponentstyle__Body-sc-1pm6mqw-6 bAidNF')]");
        this.btnGerenciadorUsuarios = page.locator("//*[contains(@class, 'typographycomponentstyle__Body-sc-1pm6mqw-6 bAidNF') and text() = 'Gerenciador de usuários']");
        this.listTabUsuarios = page.locator("//*[contains(@role, 'tablist')]//span[contains(@class, 'name')]");
        this.listTabCliente = page.locator("//*[contains(@role, 'tablist')]//span[contains(@class, 'name')]");
        this.btnDownloadExcel = page.locator("//button[@name='userExcellExportBtn']");
    }

    public void acessarGerenciadorUsuarios(){
        Page newPage = page.context().waitForPage(() -> {
            btnGerenciadorUsuarios.click();
        });
        PlaywrightController.switchToLastTab(newPage);
    }

    public boolean validarGerenciadorUsuario(List<String> listaUsuariosNecessarios) throws Exception {

        ArrayList<String> camposValidos = new ArrayList<String>();
        ArrayList<String> camposInvalidos = new ArrayList<String>();

        Thread.sleep(5000);
        List<String> todosUsuariosListados = listTabUsuarios.allInnerTexts();

        logger.info("Lista de usuários apresentados:");
        ValueUtils.printListString(todosUsuariosListados);

        logger.info("Lista de usuários requeridos:");
        ValueUtils.printListString(listaUsuariosNecessarios);

//        logger.takeScreenShot("Campos de usuarios apresentados", page);
        EvidenceManager.addStep(page, "Campos de usuarios apresentados");

        if (todosUsuariosListados.size() == listaUsuariosNecessarios.size()) {

            for(String tab : todosUsuariosListados)
            {
                if (listaUsuariosNecessarios.contains(tab))
                    camposValidos.add(tab);
                else
                    camposInvalidos.add(tab);
            }

            if(camposInvalidos.size() > 0)
            {
                logger.info("Campos de usuários inválidos:");
                for(String invalida : camposInvalidos)
                    logger.info(invalida);

                return false;
            }
            else
            {
                logger.info("Campos de usuários válidos:");
                for(String valida : camposValidos)
                    logger.info(valida);

                return true;
            }
        }
        else{return false;}
        }

    public boolean validarSupplyGerenciadorUsuario(List<String> listaUsuariosNecessarios) throws Exception {

        ArrayList<String> camposValidos = new ArrayList<String>();
        ArrayList<String> camposInvalidos = new ArrayList<String>();

        Thread.sleep(5000);
        List<String> todosUsuariosListados = listTabCliente.allInnerTexts();

        logger.info("Lista de usuários apresentados:");
        ValueUtils.printListString(todosUsuariosListados);

        logger.info("Lista de usuários requeridos:");
        ValueUtils.printListString(listaUsuariosNecessarios);

//        logger.takeScreenShot("Campos de usuarios apresentados", page);
        EvidenceManager.addStep(page, "Campos de usuarios apresentados");

        if (todosUsuariosListados.size() == listaUsuariosNecessarios.size()) {

            for(String tab : todosUsuariosListados)
            {
                if (listaUsuariosNecessarios.contains(tab))
                    camposValidos.add(tab);
                else
                    camposInvalidos.add(tab);
            }

            if(camposInvalidos.size() > 0)
            {
                logger.info("Campos de usuários inválidos:");
                for(String invalida : camposInvalidos)
                    logger.info(invalida);

                return false;
            }
            else
            {
                logger.info("Campos de usuários válidos:");
                for(String valida : camposValidos)
                    logger.info(valida);

                return true;
            }
        }
        else{return false;}
    }

    public void downloadExcelGerenciadorDeUsuarios()
    {

        elementExist(page, btnDownloadExcel);
        Download download = page.waitForDownload(() -> {
            // Perform the action that initiates download
            btnDownloadExcel.dispatchEvent("click");
        });

        String excelPathGerenciadorUsarios = PropertyHelper.getProperty("playwright.download.path")+download.suggestedFilename();
        getCv_().setExcelPathGerenciadorUsarios(excelPathGerenciadorUsarios);
        download.saveAs(Paths.get(excelPathGerenciadorUsarios));
        logger.info("Download do excel realizado com sucesso");


    }
}
