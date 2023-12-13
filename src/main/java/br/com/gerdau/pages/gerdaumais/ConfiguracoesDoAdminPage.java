package br.com.gerdau.pages.gerdaumais;

import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.gerdau.utils.EvidenceManager;
import br.com.gerdau.utils.ValueUtils;
import com.google.api.client.util.Strings;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

import static br.com.atomic.framework.playwright.PlaywrightElementHelper.elementExist;

public class ConfiguracoesDoAdminPage
{
    private Page page;
    private Locator btnEngrenagem;

    private Locator listTabCampos;
    private Locator btnConfigAdmin;
    private Locator listTabFuncionalidades;
    private Locator btnFuga;


    LoggerHelper logger = new LoggerHelper(ConfiguracoesDoAdminPage.class);
    public ConfiguracoesDoAdminPage(Page page)
    {
        this.page = page;
        this.btnEngrenagem = page.locator("//*[contains(@class, 'svg-inline--fa fa-cog fa-w-16 fa-fw fa-lg fa-iconcomponentstyle__FontAwesomeIconStyled-sc-1ht276p-0 jsNAXZ')]");
        this.listTabCampos = page.locator("//*[contains(@class, 'typographycomponentstyle__Body-sc-1pm6mqw-6 bAidNF')]");
        this.btnConfigAdmin = page.locator("//*[contains(@class, 'typographycomponentstyle__Body-sc-1pm6mqw-6 bAidNF') and text() = 'Configurações do administrador']");
        this.listTabFuncionalidades = page.locator("//*[contains(@class, 'typographycomponentstyle__Body-sc-1pm6mqw-6 dNLvhY')]");
        this.btnFuga = page.locator("//*[contains(@class, 'typographycomponentstyle__H1-sc-1pm6mqw-1 gUVgPD')]");
    }

    public void acessarConfiguracoesDoAdministrador() throws Exception {
        AssertHelper.assertTrue("O botão de configurações de admin deve existir", elementExist(page, btnConfigAdmin, 10));
        btnConfigAdmin.click();
    }

    public boolean validarConfiguracoesDoAdministrador(List<String> listaFuncionalidadesNecessarias) throws Exception {

        ArrayList<String> funcionalidadesValidas = new ArrayList<String>();
        ArrayList<String> funcionalidadesInvalidas = new ArrayList<String>();

        Thread.sleep(5000);
        btnEngrenagem.dispatchEvent("click");

        List<String> todasFuncionalidades = listTabFuncionalidades.allInnerTexts();

        int index = 0;
        List<Integer> listaIndexSemDescricao = new ArrayList<Integer>();

        for(String resLocator : todasFuncionalidades){
            if(Strings.isNullOrEmpty(resLocator))
                listaIndexSemDescricao.add(index);
            index++;
        }

        for(int indexSemDescricao : listaIndexSemDescricao){
            todasFuncionalidades.remove(indexSemDescricao);
        }

        logger.info("Lista de funcionalidades apresentadas:");
        ValueUtils.printListString(todasFuncionalidades);

        logger.info("Lista de funcionalidades requeridas:");
        ValueUtils.printListString(listaFuncionalidadesNecessarias);

//        logger.takeScreenShot("Campos de funcionalidades apresentadas", page, true);
        EvidenceManager.addStep(page, "Campos de funcionalidades apresentadas", true);

        if (todasFuncionalidades.size() == listaFuncionalidadesNecessarias.size()) {

            for(String tab : todasFuncionalidades)
            {
                if (listaFuncionalidadesNecessarias.contains(tab))
                    funcionalidadesValidas.add(tab);
                else
                    funcionalidadesInvalidas.add(tab);
            }

            if(funcionalidadesInvalidas.size() > 0)
            {
                logger.info("Campos de funcionalidades inválidos:");
                for(String invalida : funcionalidadesInvalidas)
                    logger.info(invalida);

                return false;
            }
            else
            {
                logger.info("Campos de funcionalidades válidos:");
                for(String valida : funcionalidadesValidas)
                    logger.info(valida);

                return true;
            }
        }
        else{return false;}
        }
    }
