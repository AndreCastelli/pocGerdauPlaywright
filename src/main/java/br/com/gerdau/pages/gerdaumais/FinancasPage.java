package br.com.gerdau.pages.gerdaumais;

import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.gerdau.utils.EvidenceManager;
import br.com.gerdau.utils.ValueUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

import static br.com.atomic.framework.playwright.PlaywrightElementHelper.waitUntilVisible;

public class FinancasPage
{
    private Page page;
    private Locator btnFinancas;
    private Locator btnVerLimites;

    LoggerHelper logger = new LoggerHelper(FinancasPage.class);
    public FinancasPage(Page page)
    {
        this.page = page;
        this.btnFinancas = page.locator("(//*[text() = 'Finanças'])");
        this.btnVerLimites = page.locator("//*[contains(@class, 'buttoncomponentstyle__ButtonContentStyled-dt09ca-2 jRwuzA')and text()='Ver limites']");

    }

    //Permissionamento Finanças
    public void acessarFinancas() throws Exception {
        //Apenas para chegar na tela de finanças
        waitUntilVisible(page, btnFinancas);
        btnFinancas.click();
        Thread.sleep(2000);
    }

    public void acessarModalVerLimites() throws Exception {
        //Acessar a aba de Ver limites
        waitUntilVisible(page, btnVerLimites);
        Thread.sleep(2000);
        btnVerLimites.click();
    }

    public void validarNomeFinancas(String nomeCliente) throws Exception {
        String nomeApresentado = page.locator("//*[contains(@class, 'boxcomponentstyle__Item-sc-1b29j6y-1 jDmVSE')]//h3").innerText();

        AssertHelper.assertTrue("O nome do cliente ("+nomeCliente+") deve conter no resultado" +
                " apresentado ("+nomeApresentado+")", nomeApresentado.contains(nomeCliente));
    }

    public boolean validarBpsFinancas(List<String> listaBPs) throws Exception {

        String todosBP = page.locator("//*[contains(@class, 'boxcomponentstyle__Item-sc-1b29j6y-1 jDmVSE')]//p").innerText();

        List<String> listaBPsApresentados = new ArrayList<>();

        if (todosBP.contains(",")) {

            String[] splitBPs = todosBP.split(",");
            for (String BP : splitBPs) {
                listaBPsApresentados.add(BP.substring(0, 10));
            }
        } else {
            listaBPsApresentados.add(todosBP.substring(0, 10));
        }

        ArrayList<String> BPsValidos = new ArrayList<String>();
        ArrayList<String> BpsInvalidos = new ArrayList<String>();

        logger.info("Lista de BPs apresentados:");
        ValueUtils.printListString(listaBPsApresentados);

        logger.info("Lista de BPs requeridos:");
        ValueUtils.printListString(listaBPs);

        EvidenceManager.addStep(page, "Campos de BPs apresentados");

        if (listaBPsApresentados.size() == listaBPs.size()) {

            for(String tab : listaBPsApresentados)
            {
                if (listaBPs.contains(tab))
                    BPsValidos.add(tab);
                else
                    BpsInvalidos.add(tab);
            }

            if(BpsInvalidos.size() > 0)
            {
                logger.info("BPs inválidos:");
                for(String invalida : BpsInvalidos)
                    logger.info(invalida);

                return false;
            }
            else
            {
                logger.info("BPs válidos:");
                for(String valida : BPsValidos)
                    logger.info(valida);

                return true;
            }
        }
        else{return false;}
    }

}


