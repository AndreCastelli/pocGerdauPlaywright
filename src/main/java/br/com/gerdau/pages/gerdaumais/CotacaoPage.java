package br.com.gerdau.pages.gerdaumais;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.atomic.framework.utils.Utils;
import br.com.gerdau.bean.CamposValidacao;
import br.com.gerdau.utils.EvidenceManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.util.Arrays;
import java.util.List;

import static br.com.atomic.framework.playwright.PlaywrightElementHelper.waitUntilNotVisible;
import static br.com.atomic.framework.playwright.PlaywrightElementHelper.waitUntilVisible;

public class CotacaoPage
{
    private Page page;
    private Locator btnEnviarCF;
    private Locator btnModalEnviarParaGerdau;
    private Locator statusInterno;
    private Locator statusExterno;
    private Locator mensagemDeProcessamento;

    //Tabs
    private Locator tabTodas;

    //Filtros Pesquisa
    private Locator txtNumeroIsa;

    private  Locator btnPesquisar;
    private  Locator btnNovaConsulta;

    //Resultado da Pesquisa
    private Locator itemListPesquisaCotacao;
    private Locator lblErroInesperado;

    //Pesquisa por cliente
    private Locator txtNomeCliente;

    LoggerHelper logger = new LoggerHelper(CotacaoPage.class);
    CamposValidacao camposValidacao = new CamposValidacao();

    public CotacaoPage(Page page)
    {
        this.page = page;
        this.tabTodas = page.locator("//a[contains(@class, 'tabcomponent') and text()='Todas']");
        this.txtNumeroIsa = page.locator("//div[@data-rbd-draggable-id='id']//input");
        this.btnNovaConsulta = page.locator("//a[contains(@class, 'buttoncomponentstyle__LinkButtonStyled-dt09ca-1 eULroR')]");
        this.btnPesquisar = page.locator("//div[contains(@class, 'buttoncomponent') and text()='Pesquisar']");
        this.itemListPesquisaCotacao = page.locator("//div[contains(@class, 'QuoteItemW')]");
        this.lblErroInesperado = page.locator("//div[contains(@class, 'AppRoutesStyled')]//*[text()='Um erro inesperado aconteceu']");
        this.btnNovaConsulta = page.locator("//a[contains(@href, 'chat/new')]");
        this.txtNomeCliente = page.locator("(//*[contains(@class, 'text-fieldcomponentstyle__TextFieldStyled-m1gg0l-0 fHkdtN')])[1]");
        this.btnEnviarCF = page.locator("//div[text()='Enviar para Gerdau']");
        this.btnModalEnviarParaGerdau = page.locator("//button[@type='submit']//div[ text()='Enviar para Gerdau']");
        this.statusInterno = page.locator("//span[text()='{var}']");
        this.statusExterno = page.locator("//span[text()='{var}']");
        this.mensagemDeProcessamento = page.locator("//*[text()='Consulta em processamento - Quando o processo for concluído, as ações estarão liberadas para interação.]");
    }

    public void acessarTabTodas()
    {
        tabTodas.click();
    }
    public void realizarPesquisaIsa(String numeroIsa) throws Exception {
        txtNumeroIsa.fill(numeroIsa);
        logger.info("Número Isa "+numeroIsa+" preenchido");

//        logger.takeScreenShot("PesquisaNumeroIsa", page);
        EvidenceManager.addStep(page, "PesquisaNumeroIsa");
        btnPesquisar.click();
    }

    public void validarPesquisaCotacaoIsa(String numeroIsa) throws Exception {
        boolean numeroIsaEncontrado = false;

        List<Locator> resultadosPesquisaList = esperaCarregamentoPesquisa();

        AssertHelper.assertTrue("Lista de resultado para pesquisa via Isa deve ser 1. Valores encontrados: "+resultadosPesquisaList.size(), resultadosPesquisaList.size() == 1);

        List<Locator> resultadosPesquisaNumeroIsaList = resultadosPesquisaList.get(0).locator("//div[contains(@class, 'QuoteItemIsa')]/p").all();
        logger.info("Infos da cotação encontrada na pesquisa:");

        for(Locator elementoNumeroIsa : resultadosPesquisaNumeroIsaList)
        {
            logger.info(elementoNumeroIsa.innerText());
            if(elementoNumeroIsa.innerText().contains(numeroIsa))
                numeroIsaEncontrado = true;
        }

        AssertHelper.assertTrue("Número Isa "+numeroIsa+" deve ser listado no primeiro elemento encontrado na pesquisa", numeroIsaEncontrado);
//        logger.takeScreenShot("NumeroIsaEncontrado", page);
        EvidenceManager.addStep(page, "NumeroIsaEncontrado");
    }

    private List<Locator> esperaCarregamentoPesquisa() throws Exception {
        int tries = 0;
        boolean finishLoading = false;
        List<Locator> resultadosPesquisaList = itemListPesquisaCotacao.all();

        while(tries < 30 && !finishLoading)
        {
            resultadosPesquisaList = itemListPesquisaCotacao.all();

            if(resultadosPesquisaList.size() == 0)
            {
                if(waitUntilVisible(page, lblErroInesperado, 2))
                {
                    Thread.sleep(10000);
                    logger.info("[FALHA] Erro inesperado apresentado na pesquisa");
//                    logger.takeScreenShot("Erro inesperado", page);
                    EvidenceManager.addStep(page, "Erro inesperado");
                    throw new Exception("Erro inesperado não deve aparecer após carregamento");
                }
                tries++;
                Thread.sleep(1000);
            }
            else
                finishLoading = true;

        }

        return resultadosPesquisaList;

    }

    public void acessarNovaConsulta() { btnNovaConsulta.click(); }

    public void consultaPesquisaRandomica() throws Exception{
        //Gerando número randomico
        double random = Math.floor(Math.random()*10) + 1;
        logger.info(String.valueOf(random));

        //Clicar em uma cotação aleatória do site na aba "Cotação"
        itemListPesquisaCotacao = page.locator("//div[contains(@class, 'QuoteItemW')]["+random+"]");
        itemListPesquisaCotacao.click();
    }

    public void acessarCotacaoAleatoria() throws Exception {
        List<Locator> listaCotacao = esperaCarregamentoPesquisa();
        AssertHelper.assertTrue("Usuário deve conter ao menos uma cotação disponível. Valores encontrados: "+listaCotacao.size(), listaCotacao.size() > 0);

        Page newPage = page.context().waitForPage(() -> {
            listaCotacao.get(Utils.randomNumberInRange(0, listaCotacao.size()-1)).click();
        });
        PlaywrightController.switchToLastTab(newPage);
        logger.info("Cotação escolhida.");
    }

    public void realizarPesquisaCliente(String cliente) throws Exception {
        txtNomeCliente.fill(cliente);
        page.locator(" //*[contains(@class, 'typographycomponentstyle__H3-sc-1pm6mqw-4 enzAkF')][text()='"+cliente+"']").click();
        logger.info("Cliente "+cliente+" preenchido");

//        logger.takeScreenShot("Cliente "+cliente+" preenchido", page);
        EvidenceManager.addStep(page, "Cliente "+cliente+" preenchido");
        btnPesquisar.click();
    }

    public void validarPesquisaCliente(String cliente) throws Exception {
        List<Locator> resultadosPesquisaList = esperaCarregamentoPesquisa();

        AssertHelper.assertTrue("Lista de CFs deve ser maior ou igual a 1. Valores encontrados: "+resultadosPesquisaList.size(), resultadosPesquisaList.size() >= 1);

        for (int i = 1; i <= resultadosPesquisaList.size(); i++) {
            String txtClienteApresentado = page.locator("(//*[contains(@id, 'quote_client')])["+i+"]").textContent();

            AssertHelper.assertTrue("O nome do cliente ("+cliente+") deve conter no resultado" +
                    " apresentado ("+txtClienteApresentado+")", txtClienteApresentado.contains(cliente));
        }

//        logger.takeScreenShot("CFs encontradas", page);
        EvidenceManager.addStep(page, "CFs encontradas");
    }

    public void validarPermissionamentoCotação(String cliente) throws Exception {
        List<Locator> resultadosPesquisaList = esperaCarregamentoPesquisa();

        AssertHelper.assertTrue("Lista de CFs deve ser maior ou igual a 1. Valores encontrados: "+resultadosPesquisaList.size(), resultadosPesquisaList.size() >= 1);

        for (int i = 1; i <= resultadosPesquisaList.size(); i++) {
            String txtClienteApresentado = page.locator("(//*[contains(@id, 'quote_client')])["+i+"]").textContent();

            AssertHelper.assertTrue("O nome do cliente ("+cliente+") deve conter no resultado" +
                    " apresentado ("+txtClienteApresentado+")", txtClienteApresentado.contains(cliente));
        }

//        logger.takeScreenShot("Resultados encontrados sobre o cliente ("+cliente+") ", page);
        EvidenceManager.addStep(page, "Resultados encontrados sobre o cliente ("+cliente+")");
    }

    public void ValidarPermissionamentoStatus(String usuario) throws Exception{
        List<Locator> resultadosPesquisaList = esperaCarregamentoPesquisa();

        AssertHelper.assertTrue("Lista de CFs deve ser maior ou igual a 1. Valores encontrados: "+resultadosPesquisaList.size(), resultadosPesquisaList.size() >= 1);

        String[] valoresStatus = {"Pendente de envio", "Em análise", "Em validação", "Aguardando parecer cliente", "Cancelada", "Rejeitada", "Finalizada", "Finalizado com derroga"};

        Thread.sleep(3000);

        for (int i = 1; i <= esperaCarregamentoPesquisa().size() * 2; i += 2) {
            String txtClienteApresentado = page.locator("(//*[contains(@id, 'quote_status')])["+i+"]").textContent();

            AssertHelper.assertTrue("O status deve estar apresentado corretamente. Valor apresentado: "+txtClienteApresentado, Arrays.asList(valoresStatus).contains(txtClienteApresentado));
        }

//        logger.takeScreenShot("Status encontrados sobre o cliente ("+usuario+")", page);
        EvidenceManager.addStep(page, "Status encontrados sobre o cliente ("+usuario+")");
    }

    public void filtrarPorData(String historico) {
        page.locator("//button[@type='button']//*[text()='Últimos 30 dias']").dispatchEvent("click");

        String buscaPorData = "//p[text()='{var}']";
        String buscaPorDataVar = buscaPorData.replace("{var}", historico);
        page.locator(buscaPorDataVar).dispatchEvent("click");
    }

    public void fecharModal() {
            while (page.locator("(//div[@class='boxcomponentstyle__Item-sc-1b29j6y-1 fvCwQR']//div[text()='Fechar'])[1]").isVisible()){
            page.locator("(//div[@class='boxcomponentstyle__Item-sc-1b29j6y-1 fvCwQR']//div[text()='Fechar'])[1]").click();
        }
    }

    public void validarMensagemConsultaIndisponivel() throws Exception {
        String mensagemIndisponivel = page.locator("//h2[text()='Não há nenhuma consulta disponível']").textContent();
        AssertHelper.assertEquals("Não há nenhuma consulta disponível", mensagemIndisponivel);
    }

    public void clicarCotacaoFiltrada() {
        String numeroIsa = txtNumeroIsa.getAttribute("value");
        String cotacaoFiltrada = "//a[@href='/quote/analysis/{var}']";
        String cotacaoFiltradaVar = cotacaoFiltrada.replace("{var}",numeroIsa);
        Page page1 = page.waitForPopup(() -> {
            page.locator(cotacaoFiltradaVar).click();
        });
        page1.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void alterarCotacaoNaURL() {
        String url = page.url();
        String novaUrl = url + "/2684";
//        String novaUrl = url.substring(0, url.length() - 4) + "/9999";
        page.navigate(novaUrl);

    }

    public void validarMensagemDeErroInesperado() throws Exception {
        String mensagemIndisponivel = page.locator("//h2[text()='Um erro inesperado aconteceu']").textContent();
        AssertHelper.assertEquals("Um erro inesperado aconteceu", mensagemIndisponivel);
    }

    public void aguardarProcessamentoDaCF() {waitUntilNotVisible(page, mensagemDeProcessamento);}

    public void enviarCF() {btnEnviarCF.click();}

    public void enviarCFModal() {btnModalEnviarParaGerdau.click();}

    public void validarEnvioCF(String statusDaCF) throws Exception {
        String status;
        if(camposValidacao.getUsuarioEscolhido().equals("RBTCXPCLIENT")){
            status = String.valueOf(statusExterno);
        } else {
            status = String.valueOf(statusInterno);
        }

        String statusDoEnvio = status.replace("{var}", statusDaCF);
        statusDoEnvio = statusDoEnvio.substring(statusDoEnvio.indexOf("/"));
        String statusTexto = page.locator(statusDoEnvio).textContent();

        waitUntilNotVisible(page,btnEnviarCF);
        AssertHelper.assertEquals(statusDaCF,statusTexto);
    }
}
