package br.com.gerdau.pages.gerdaumais;

import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.gerdau.utils.EvidenceManager;
import br.com.gerdau.utils.ValueUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

import static br.com.atomic.framework.playwright.PlaywrightElementHelper.elementExist;

public class CentralDeAjudaPage
{
    private Page page;
    private Locator listMenu;
    private Locator btnCentralAjuda;


    LoggerHelper logger = new LoggerHelper(CentralDeAjudaPage.class);
    public CentralDeAjudaPage(Page page)
    {
        this.page = page;
        this.listMenu = page.locator("//*[contains(@class, 'section-cellstyle__SectionCellStyled-sc-16varfn-0 jlhTeR')]//h3");
        this.btnCentralAjuda = page.locator("//*[contains(@class, 'boxcomponentstyle__Item-sc-1b29j6y-1 cViMoK') and text()='Ajuda']");
    }

    public void acessarCentralDeAjuda() throws Exception {
        AssertHelper.assertTrue("O botão de configurações de admin deve existir", elementExist(page, btnCentralAjuda, 10));
        btnCentralAjuda.click();
        Thread.sleep(5000);
    }

    public boolean validarCentralDeMenu(List<String> listMenuNecessarios) throws Exception {

        ArrayList<String> MenusValidos = new ArrayList<String>();
        ArrayList<String> MenusInvalidos = new ArrayList<String>();

        List<String> todosMenu = listMenu.allInnerTexts();

        int index = 0;
        List<Integer> listaIndexSemDescricao = new ArrayList<Integer>();

        for(String resLocator : todosMenu){
            if(resLocator.equals("Configurar notificações"))
                listaIndexSemDescricao.add(index);
            index++;
        }

        for(int indexSemDescricao : listaIndexSemDescricao){
            todosMenu.remove(indexSemDescricao);
        }

        logger.info("Lista de menus apresentados:");
        ValueUtils.printListString(todosMenu);

        logger.info("Lista de menus requeridos:");
        ValueUtils.printListString(listMenuNecessarios);

        EvidenceManager.addStep(page, "Campos de menu apresentados");

        if (todosMenu.size() == listMenuNecessarios.size()) {

            for(String tab : todosMenu)
            {
                if (listMenuNecessarios.contains(tab))
                    MenusValidos.add(tab);
                else
                    MenusInvalidos.add(tab);
            }

            if(MenusInvalidos.size() > 0)
            {
                logger.info("Campos de menu inválidos:");
                for(String invalida : MenusInvalidos)
                    logger.info(invalida);

                return false;
            }
            else
            {
                logger.info("Campos de menu válidos:");
                for(String valida : MenusValidos)
                    logger.info(valida);

                return true;
            }
        }
        else{return false;}
        }
    }
