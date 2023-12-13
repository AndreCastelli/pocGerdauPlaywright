package br.com.gerdau.pages.gerdaumais.elementosFixos;

import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.gerdau.utils.EvidenceManager;
import br.com.gerdau.utils.ValueUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

import static br.com.atomic.framework.playwright.PlaywrightElementHelper.elementExist;
public class CabecalhoFixo
{
    private Page page;
    private Locator tabPainelGestao;
    private Locator tabCotacao;
    private Locator tabImplantacao;
    private Locator tabCarteira;
    private Locator tabFinancas;
    private Locator tabManifestacao;
    private Locator listTabCabecalho;
    private Locator btnAjuda;
    private Locator btnNotificacoes;
    private Locator btnEngrenagem;

    private Locator listTabCampos;

    private Locator btnFecharModalAjuda;

    LoggerHelper logger = new LoggerHelper(CabecalhoFixo.class);
    public CabecalhoFixo(Page page)
    {
        this.page = page;
        this.tabPainelGestao = page.locator("//a[contains(@class, 'menucomponent') and text()='Painel de gestão']");
        this.tabCotacao = page.locator("//a[contains(@class, 'menucomponent') and text()='Cotação']");
        this.tabImplantacao = page.locator("//a[contains(@class, 'menucomponent') and text()='Implantação']");
        this.tabCarteira = page.locator("//a[contains(@class, 'menucomponent') and text()='Carteira']");
        this.tabFinancas = page.locator("//a[contains(@class, 'menucomponent') and text()='Financas']");
        this.tabManifestacao = page.locator("//a[contains(@class, 'menucomponent') and text()='Manifestação']");
        this.listTabCabecalho = page.locator("//div[contains(@class, 'MenuList')]//a[contains(@class, 'menucomponent')]");
        this.btnEngrenagem = page.locator("//*[contains(@class, 'svg-inline--fa fa-cog fa-w-16 fa-fw fa-lg fa-iconcomponentstyle__FontAwesomeIconStyled-sc-1ht276p-0 jsNAXZ')]");
        this.listTabCampos = page.locator("//*[contains(@class, 'boxcomponentstyle__HboxStyled-sc-1b29j6y-0 hlXSfG')]/div/p");
        this.btnFecharModalAjuda = page.locator("//button[@kind='secondary']");
    }

    public void validarExistenciaCabecalho() throws Exception {
        AssertHelper.assertTrue("Cabeçalho deve existir", elementExist(page, listTabCabecalho.first(), 30));
    }

    public void acessarTabCotacao() throws Exception {
        AssertHelper.assertTrue("Aba cotação deve existir", elementExist(page, tabCotacao, 60));

        if(tabCotacao.getAttribute("active").equals("false")) {
            tabCotacao.click();
        }

    }

    private boolean validarExistenciaTab(Locator tab, int timeoutSeconds) throws Exception {
        return elementExist(page, tab, timeoutSeconds);
    }

    public boolean validarExistenciaTab(List<String> listaAbasNecessarias) throws Exception {
        ArrayList<String> tabsValidas = new ArrayList<String>();
        ArrayList<String> tabsInvalidas = new ArrayList<String>();

        List<Locator> todasTabsListadas = listTabCabecalho.all();

        logger.info("Lista de abas apresentadas:");
        ValueUtils.printListLocator(todasTabsListadas);

        logger.info("Lista de abas requeridas:");
        ValueUtils.printListString(listaAbasNecessarias);

//        logger.takeScreenShot("Abas apresentadas", page);
        EvidenceManager.addStep(page, "Abas apresentedas");

        if(listaAbasNecessarias.size() > todasTabsListadas.size())
        {
            logger.info("Quantidade de abas necessárias para o usuário ("+listaAbasNecessarias.size()+") é maior que apresentado ("+todasTabsListadas.size()+")");
            return false;
        }
        else if(todasTabsListadas.size() > listaAbasNecessarias.size())
        {
            logger.info("Quantidade de abas necessárias para o usuário ("+listaAbasNecessarias.size()+") é menor que apresentado ("+todasTabsListadas.size()+")");
            return false;
        }
        else
        {
            for(Locator tab : todasTabsListadas)
            {
                if (listaAbasNecessarias.contains(tab.innerText()))
                    tabsValidas.add(tab.innerText());
                else
                    tabsInvalidas.add(tab.innerText());
            }

            if(tabsInvalidas.size() > 0)
            {
                logger.info("Abas inválidas:");
                for(String invalida : tabsInvalidas)
                    logger.info(invalida);

                return false;
            }
            else
            {
                logger.info("Abas válidas:");
                for(String valida : tabsValidas)
                    logger.info(valida);

                return true;
            }
        }
    }

    public void validarExistenciaModal() throws Exception {
        if(elementExist(page, btnFecharModalAjuda, 2)){
            btnFecharModalAjuda.first().click();
            Thread.sleep(1000);
        }

        Thread.sleep(3000);
        btnEngrenagem.click();
        AssertHelper.assertTrue("Modal deve existir", elementExist(page, listTabCampos.first(), 30));
    }

    public boolean validarExistenciaCampos(List<String> listaCamposNecessarios) throws Exception {
        ArrayList<String> camposValidos = new ArrayList<String>();
        ArrayList<String> camposInvalidos = new ArrayList<String>();

        List<Locator> todosCamposListados = listTabCampos.all();

        for (int i = 0; i < todosCamposListados.size(); i++) {
            String elementoAtual = todosCamposListados.get(i).innerText();

            if (elementoAtual.contains("Alguma dúvida sobre os campos?")
                    || elementoAtual.contains("Você conhece nossas marcas? Clique nos ícones e saiba mais.")) {
                todosCamposListados.remove(i);
                i--;
            }
        }

        logger.info("Lista de campos apresentados:");
        ValueUtils.printListLocator(todosCamposListados);

        logger.info("Lista de campos requeridos:");
        ValueUtils.printListString(listaCamposNecessarios);

//        logger.takeScreenShot("Campos apresentados", page);
        EvidenceManager.addStep(page, "Campos apresentados");

        if(listaCamposNecessarios.size() > todosCamposListados.size())
        {
            logger.info("Quantidade de campos no modal necessários para o usuário ("+listaCamposNecessarios.size()+") é maior que apresentado ("+todosCamposListados.size()+")");
            return false;
        }
        else if(todosCamposListados.size() > listaCamposNecessarios.size())
        {
            logger.info("Quantidade de campos no modal necessários para o usuário ("+listaCamposNecessarios.size()+") é menor que apresentado ("+todosCamposListados.size()+")");
            return false;
        }
        else
        {
            for(Locator tab : todosCamposListados)
            {
                if (listaCamposNecessarios.contains(tab.innerText()))
                    camposValidos.add(tab.innerText());
                else
                    camposInvalidos.add(tab.innerText());
            }

            if(camposInvalidos.size() > 0)
            {
                logger.info("Campos inválidos:");
                for(String invalida : camposInvalidos)
                    logger.info(invalida);

                return false;
            }
            else
            {
                logger.info("Campos válidos:");
                for(String valida : camposValidos)
                    logger.info(valida);

                return true;
            }
        }
    }

}
