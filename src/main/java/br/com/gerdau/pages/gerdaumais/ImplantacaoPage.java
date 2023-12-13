package br.com.gerdau.pages.gerdaumais;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.gerdau.utils.EvidenceManager;
import br.com.gerdau.utils.ValueUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import java.util.ArrayList;
import java.util.List;

import static br.com.atomic.framework.playwright.PlaywrightElementHelper.waitUntilVisible;
import static br.com.gerdau.validations.ValidationsHelper.getCv_;
public class ImplantacaoPage
{
    private Page page;
    private Locator btnImplantação;
    private Locator btnImplantacaoViaPlanilha;
    private Locator btnImplantaçãoViaSelecaoItens;
    private Locator btnModalEmissor;
    private Locator btnModalRecebedor;
    private Locator btnPrimeiroEmissor;
    private Locator btnPrimeiroRecebedor;
    private Locator btnDescricaoMaterial;
    private Locator txtBuscaImplantacao;

    LoggerHelper logger = new LoggerHelper(ImplantacaoPage.class);
    CarteiraPage carteiraPage = new CarteiraPage(PlaywrightController.getPage());

    public ImplantacaoPage(Page page)
    {
        this.page = page;
        this.btnImplantação = page.locator("//*[contains(text(), 'Implantação')]");
        this.btnImplantacaoViaPlanilha = page.locator("//*[contains(text(), 'pedido via planilha')]");
        this.btnModalEmissor = page.locator("(//*[contains(@class, 'selectcomponentstyle__SelectStyled-sc-1b21i86-1 hSEeVZ')])[1]");
        this.btnModalRecebedor = page.locator("(//*[contains(@class, 'selectcomponentstyle__SelectStyled-sc-1b21i86-1 hSEeVZ')])[2]");
        this.btnImplantaçãoViaSelecaoItens = page.locator("//*[contains(text(), 'seleção de itens')]");
        this.btnPrimeiroEmissor = page.locator("(//option[@class='option_issuer'])[1]");
        this.btnPrimeiroRecebedor = page.locator("(//*[contains(@class, 'option_receiver')])[1]");
        this.btnDescricaoMaterial = page.locator("//*[@data-icon= 'circle'][2]");
        this.txtBuscaImplantacao = page.locator("//*[contains(@class, 'text-fieldcomponentstyle__TextFieldStyled-m1gg0l-0 bxuFQM')]");
    }


    //Permissionamento Implantação
    public void acessarImplantacao() throws Exception {
        //Apenas para chegar na tela de implantação
        waitUntilVisible(page, btnImplantação);
        btnImplantação.click();
        Thread.sleep(2000);
    }

    public void acessarPedidoViaPlanilha() throws Exception {
        waitUntilVisible(page, btnImplantacaoViaPlanilha);
        btnImplantacaoViaPlanilha.click();
        Thread.sleep(2000);
    }

    public void acessarPedidoViaSelecaoDeItens () throws InterruptedException {
        waitUntilVisible(page, btnImplantaçãoViaSelecaoItens);
        btnImplantaçãoViaSelecaoItens.click();
        Thread.sleep(2000);
    }
    public void selecionarModalEmissor() throws Exception {
        waitUntilVisible(page, btnModalEmissor);
        btnModalEmissor.click();
        Thread.sleep(2000);
    }

    public boolean validarBpsImplantaçãoEmissor(List<String> listaBPs) throws Exception {

        ArrayList<String> listaBPsNecessarios = new ArrayList<String>();

        List<String> todosBP = page.locator("(//*[contains(@class, 'selectcomponentstyle__SelectStyled-sc-1b21i86-1 hSEeVZ')])//option").allInnerTexts();

        for (String elemento : todosBP) {
            if (!elemento.contains("Selecione ")) {
                String BP = elemento.substring(0, 10);
                listaBPsNecessarios.add(BP);
            }
        }

        ArrayList<String> BPsValidos = new ArrayList<String>();
        ArrayList<String> BpsInvalidos = new ArrayList<String>();

        logger.info("Lista de BPs apresentados:");
        ValueUtils.printListString(listaBPsNecessarios);

        logger.info("Lista de BPs requeridos:");
        ValueUtils.printListString(listaBPs);

        EvidenceManager.addStep(page, "Campos de BPs apresentados");

        if (listaBPsNecessarios.size() == listaBPs.size()) {

            for(String tab : listaBPsNecessarios)
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

    public void selecionarEmissor() throws Exception {
        page.getByPlaceholder("Selecione o emissor do pedido").selectOption(new SelectOption().setIndex(1));
    }


    //Permissionamento materiais

    public void selecionarModalRecebedor() throws Exception {
        waitUntilVisible(page, btnModalRecebedor);
        btnModalRecebedor.click();
        Thread.sleep(2000);
    }

    public boolean validarBpsImplantaçãoRecebedor(List<String> listaBPs) throws Exception {
        ArrayList<String> BPsValidos = new ArrayList<String>();
        ArrayList<String> BpsInvalidos = new ArrayList<String>();

        List<String> todosBP = page.locator("(//*[contains(@class, 'option_receiver')])").allInnerTexts();
        ArrayList<String> listaBPsNecessarios = new ArrayList<String>();

        for (String elemento : todosBP) {
            String BP = elemento.substring(0, 10);
            listaBPsNecessarios.add(BP);
        }

        logger.info("Lista de BPs apresentados:");
        ValueUtils.printListString(listaBPsNecessarios);

        logger.info("Lista de BPs requeridos:");
        ValueUtils.printListString(listaBPs);

        EvidenceManager.addStep(page, "Campos de BPs apresentados");

        if (listaBPsNecessarios.size() == listaBPs.size()) {

            for(String tab : listaBPsNecessarios)
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


    public void selecionarRecebedor() throws Exception {
        page.getByPlaceholder("Selecione o recebedor do pedido").selectOption(new SelectOption().setIndex(1));
    }

    public void selecionarDescricaoMaterial() throws Exception {
        page.locator("//label[@for='searchOptionsForm_salesDocumentItemText']").click();
    }

    public void validarPermissionamentoDeMateriais() throws Exception {
        List<String> listaMateriaisCarteira = new ArrayList<>(getCv_().getListaMateriaisCarteira());

        for (int i = 0; i < listaMateriaisCarteira.size(); i++){
            txtBuscaImplantacao.fill(listaMateriaisCarteira.get(i));

            Thread.sleep(8000);
            List<String> todosMateriais = page.locator("//div[@class='material-optionsstyle__MaterialsWrapper-sc-5881i1-0 bXKwmb']//label").allInnerTexts();
            AssertHelper.assertTrue("A pesquisa deve retornar resultado. Valor Desejado: " + listaMateriaisCarteira.get(i) + ". " +
                    "Valores Encontrados: " + todosMateriais, !todosMateriais.isEmpty());

        }

        EvidenceManager.addStep(page, "Materiais apresentados");
    }


    //Permissionamento de acordo com o Part Number
    public void selecionarPartNumber() throws Exception {
        page.locator("//*[@for='searchOptionsForm_customerMaterialCode']").click();
    }

    public void validarPermissionamentoDePartNumberVerificandoOsMateriais() throws Exception {
        List<String> listaPartNumberCarteira = new ArrayList<>(getCv_().getListaPartNumberFiltrada());

        for (int i = 0; i < listaPartNumberCarteira.size(); i++){
            txtBuscaImplantacao.fill(listaPartNumberCarteira.get(i));

            Thread.sleep(8000);
            String todosPartNumber = page.locator("//div[@class='material-optionsstyle__MaterialsWrapper-sc-5881i1-0 bXKwmb']//label").innerText();

            int posicaoEspaco = todosPartNumber.indexOf(' ');
            String PartNumberApresentado = todosPartNumber.substring(0, posicaoEspaco);

            AssertHelper.assertTrue("A pesquisa deve retornar resultado. Valor Desejado: [" + listaPartNumberCarteira.get(i) + "]. " +
                    "Valores Encontrados: [" + PartNumberApresentado + "]", PartNumberApresentado.contains(listaPartNumberCarteira.get(i)));

        }
        EvidenceManager.addStep(page, "Part Number apresentado");
    }


    //Permissionamento de materiais
    public void validarMateriaisDeOutroCliente(List<String> listaMateriais) throws Exception {

        for (int i = 0; i < listaMateriais.size(); i++){

            txtBuscaImplantacao.fill(listaMateriais.get(i));
            List<String> todosMateriais = page.locator("//div[@class='material-optionsstyle__MaterialsWrapper-sc-5881i1-0 bXKwmb']//label").allInnerTexts();

            Thread.sleep(8000);

            AssertHelper.assertTrue("A pesquisa não deve retornar resultado. Valor inserido: " + listaMateriais.get(i) + ". " +
                    "Valores Encontrados: " + todosMateriais, todosMateriais.isEmpty());

        }

        EvidenceManager.addStep(page, "Lista de materiais apresentados");
    }

}