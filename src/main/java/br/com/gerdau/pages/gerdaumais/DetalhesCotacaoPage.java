package br.com.gerdau.pages.gerdaumais;

import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.atomic.framework.proton.ProtonHelper;
import br.com.atomic.framework.utils.Utils;
import br.com.gerdau.models.ResumoDaAnaliseCotacao;
import br.com.gerdau.utils.EvidenceManager;
import br.com.gerdau.utils.ValueUtils;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static br.com.atomic.framework.playwright.PlaywrightElementHelper.elementExist;
import static br.com.atomic.framework.playwright.PlaywrightElementHelper.waitUntilVisible;
import static br.com.gerdau.validations.ValidationsHelper.getCv_;

public class DetalhesCotacaoPage
{
    private Page page;

    //PDF -----------------------------
    private Locator btnPdf;
    private Locator chkListPlantas;
    private Locator btnGerarPdf;
    //PDF -----------------------------

    //INFOS DETALHES ------------------
    private Locator boxListaInfosTecnicasCotacao;
    private Locator lblTituloCotacao;
    private Locator tabListaAbasDisponiveis;
    private Locator btnMenuNormaEspecifica;
    private Locator btnMenuComposicaoQuimica;
    private Locator btnMenuTipoDeProduto;
    private Locator btnMenuProcessoDeFabricacao;
    private Locator btnMenuForma;
    private Locator btnMenuTratamentoTermico;
    private Locator btnMenuAcabamento;
    private Locator btnMenuBitola;
    private Locator btnMenuComprimento;
    private Locator btnMenuProcessoDeCliente;
    private Locator btnMenuAplicacaoFinal;
    private Locator btnMenuCliente;
    private Locator btnMenuMercado;
    private Locator btnMenuConsumo;
    private Locator btnMenuDataInicioProjeto;
    private Locator lblConteudoMenuTexto;
    private Locator lblConteudoMenuTabela;
    //INFOS DETALHES ------------------

    private Locator usinaExibidaCliente;
    private Locator requisitarPlanta;
    private Locator statusCancelada;
    private Locator statusEdicaoCliente;
    private Locator statusFinalizada;
    private Locator statusValidacaoRTC;
    private Locator btnModalEnviar;
    private Locator enviarParaClient;
    private Locator enviarParaRtc;
    private Locator enviarParaSeller;
    private Locator modalCotacaoConsultaPreco;
    private Locator modalCotacaoAnaliseTecnica;
    private Locator btnmodalCotacaoProsseguir;


    //Valores padrões para abas de usinas
    private final String ABA_DESCRICAO_CHARQUEADAS = "Charqueadas";
    private final String ABA_DESCRICAO_MOGI = "Mogi das Cruzes";
    private final String ABA_DESCRICAO_PINDAMONHANGABA = "Pindamonhangaba";
    private final String ABA_DESCRICAO_GERDAU = "Gerdau";

    LoggerHelper logger = new LoggerHelper(DetalhesCotacaoPage.class);


    public DetalhesCotacaoPage(Page page){
        this.page = page;
        this.boxListaInfosTecnicasCotacao = page.locator("//div[contains(@class, 'TechnicalAnalysisGeneralInfo')]//div[contains(@class, 'gridcomponent')]");
        this.lblTituloCotacao = page.locator("//h1[text()='Consulta Preço']/following-sibling::p");
        this.btnMenuNormaEspecifica = page.locator("[data-rbd-draggable-id='SPECIFIC_SPECIFICATION']");
        this.btnMenuComposicaoQuimica = page.locator("[data-rbd-draggable-id='CHEMICAL_COMPOSITION']");
        this.btnMenuTipoDeProduto = page.locator("[data-rbd-draggable-id='PRODUCT']");
        this.btnMenuProcessoDeFabricacao = page.locator("[data-rbd-draggable-id='GERDAU_PROCESS']");
        this.btnMenuForma = page.locator("[data-rbd-draggable-id='SHAPE']");
        this.btnMenuTratamentoTermico = page.locator("[data-rbd-draggable-id='HEAT_TREATMENT']");
        this.btnMenuAcabamento = page.locator("[data-rbd-draggable-id='SURFACE_FINISH']");
        this.btnMenuBitola = page.locator("[data-rbd-draggable-id='DIAMETER']");
        this.btnMenuComprimento = page.locator("[data-rbd-draggable-id='LENGTH']");
        this.btnMenuProcessoDeCliente = page.locator("[data-rbd-draggable-id='CLIENT_PROCESS']");
        this.btnMenuAplicacaoFinal = page.locator("[data-rbd-draggable-id='FINAL_APPLICATION']");
        this.btnMenuCliente = page.locator("[data-rbd-draggable-id='CUSTOMER']");
        this.btnMenuMercado = page.locator("[data-rbd-draggable-id='MARKET']");
        this.btnMenuConsumo = page.locator("[data-rbd-draggable-id='AMOUNT']");
        this.btnMenuDataInicioProjeto = page.locator("[data-rbd-draggable-id='SOP']");
        this.lblConteudoMenuTexto = page.locator("//div[contains(@class, 'framecomponent')]/div[contains(@class, 'cellcomponent')]/p");
        this.lblConteudoMenuTabela = page.locator("//div[contains(@class, 'framecomponent')]/div[contains(@class, 'cellcomponent')]/table");
        this.tabListaAbasDisponiveis = page.locator("//li[contains(@class, 'tab')]"); //Elemento retorna TODAS abas presentes na página
        this.usinaExibidaCliente = page.locator("//p[text()='* A Usina Charqueadas foi selecionada para exibição ao cliente']");
        this.requisitarPlanta = page.locator("//div[@class='switchcomponentstyle__SwitchBackgroundStyled-sc-1qgqlp8-2 iCbKuI']");
        this.statusCancelada = page.locator("//span[@data-testid='CANCELED']");
        this.statusEdicaoCliente = page.locator("//span[@data-testid='CLIENT_EDITION']");
        this.statusFinalizada = page.locator("//span[@data-testid='FINALIZED']");
        this.statusValidacaoRTC = page.locator("//span[@data-testid='VALIDATION_RTC']");
        this.btnModalEnviar = page.locator("//div[text()='Enviar']");
        this.enviarParaClient = page.locator("(//div[@class='text-fieldcomponentstyle__TextFieldWrapperStyled-m1gg0l-2 cYGZgq']//input[@placeholder='Separe por \",\" para enviar à mais de um e-mail'])[1]");
        this.enviarParaRtc = page.locator("(//div[@class='text-fieldcomponentstyle__TextFieldWrapperStyled-m1gg0l-2 cYGZgq']//input[@placeholder='Separe por \",\" para enviar à mais de um e-mail'])[2]");
        this.enviarParaSeller = page.locator("(//div[@class='text-fieldcomponentstyle__TextFieldWrapperStyled-m1gg0l-2 cYGZgq']//input[@placeholder='Separe por \",\" para enviar à mais de um e-mail'])[3]");
        this.modalCotacaoAnaliseTecnica = page.locator("//label[@for='analysisType_CP']");
        this.modalCotacaoConsultaPreco = page.locator("//label[@for='analysisType_AT']");
        this.btnmodalCotacaoProsseguir = page.locator("//div[text()='Prosseguir']");

        //PDF
        this.btnPdf = page.locator("//button[contains(@title, 'Gerar PDF')]");
        this.chkListPlantas = page.locator("//form//div[contains(@class, 'CheckboxField')]");
        this.btnGerarPdf = page.locator("//div[contains(@class, 'button') and text()='Gerar PDF']");
    }


    public void validarCriacaoCotacao() throws Exception {
        waitUntilVisible(page, boxListaInfosTecnicasCotacao);
        getNumeroISA();
//        logger.takeScreenShot("CotacaoCriada", page, true);
        EvidenceManager.addStep(page, "CotacaoCriada", true);
    }
    public void validarInfosCotacao_TodasUsinas(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao) throws Exception {
        waitUntilVisible(page, boxListaInfosTecnicasCotacao);
        validarTodosMenusCotacao_TodasUsinas(resumoDaAnaliseCotacao);
    }

    public void validarInfosCotacao_PrimeiraUsina(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao) throws Exception {
        waitUntilVisible(page, boxListaInfosTecnicasCotacao);
        validarTodosMenusCotacao_PrimeiraUsina(resumoDaAnaliseCotacao);
    }

    private String getNumeroISA(){
        //Extrair número ISA da URL
        String url = page.url();
        String numeroIsa = ValueUtils.getSomenteNumeros(url);

        logger.info("Número ISA gerado: "+numeroIsa);
        ProtonHelper.setProtonOutputValue("out_numeroIsa", numeroIsa);

        return numeroIsa;
    }

    private void validarTodosMenusCotacao_TodasUsinas(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao) throws Exception {
        List<Locator> abasUsinas = getAbasUsinasApresentadas();

        AssertHelper.assertTrue("Deve ser apresentado ao menos uma aba de usinas", abasUsinas.size() > 0);

        for(Locator usina : abasUsinas){
            usina.click();
            String usinaDescricao = usina.innerText();
            logger.info("-------------- Usina: "+usina.innerText()+" --------------");
            validarMenuNormaEspecifica(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuComposicaoQuimica(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuTipoProduto(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuProcessoFabricacao(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuForma(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuTratamentoTermico(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuAcabamento(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuBitola(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuComprimento(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuProcessoDeCliente(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuAplicacaoFinal(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuCliente(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuMercado(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuConsumo(resumoDaAnaliseCotacao, usinaDescricao);
            validarMenuDataInicioProjeto(resumoDaAnaliseCotacao, usinaDescricao);
            logger.info("-------------- Usina: "+usina.innerText()+" --------------\n");
        }
    }

    private void validarTodosMenusCotacao_PrimeiraUsina(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao) throws Exception {
        Locator abaPrimeiraUsina = getAbasUsinasApresentadas().get(0);

        abaPrimeiraUsina.click();
        String usinaDescricao = abaPrimeiraUsina.innerText();
        logger.info("-------------- Usina: "+usinaDescricao+" --------------");
        validarMenuNormaEspecifica(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuComposicaoQuimica(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuTipoProduto(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuProcessoFabricacao(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuForma(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuTratamentoTermico(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuAcabamento(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuBitola(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuComprimento(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuProcessoDeCliente(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuAplicacaoFinal(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuCliente(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuMercado(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuConsumo(resumoDaAnaliseCotacao, usinaDescricao);
        validarMenuDataInicioProjeto(resumoDaAnaliseCotacao, usinaDescricao);
        logger.info("-------------- Usina: "+usinaDescricao+" --------------\n");
    }


    private List<Locator> getAbasUsinasApresentadas(){
        List<Locator> todasAbasDaPagina = tabListaAbasDisponiveis.all();
        List<Locator> todasAbasDeUsinas = new ArrayList<Locator>();

        for(Locator aba : todasAbasDaPagina){
            if(aba.innerText().equals(ABA_DESCRICAO_CHARQUEADAS) ||
                            aba.innerText().equals(ABA_DESCRICAO_MOGI) ||
                                aba.innerText().equals(ABA_DESCRICAO_PINDAMONHANGABA) ||
                                    (aba.innerText().equals(ABA_DESCRICAO_GERDAU) || aba.innerText().contains(ABA_DESCRICAO_GERDAU+" - Resumo"))){
                todasAbasDeUsinas.add(aba);
            }

        }

        return todasAbasDeUsinas;
    }

    private void validarMenuNormaEspecifica(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Norma Específica -----");
        btnMenuNormaEspecifica.click();
        String normaApresentada = lblConteudoMenuTexto.innerText();
        AssertHelper.assertTrue("Norma específica apresentada ("+normaApresentada+") deve ser equivalente a informada na ISA ("+resumoDaAnaliseCotacao.getNormaEspecifica()+")",
                                        normaApresentada.contains(resumoDaAnaliseCotacao.getNormaEspecifica()));

//        logger.takeScreenShot("NormaEspecifica_"+usina, page);
        EvidenceManager.addStep(page, "NormaEspecifica_"+usina);
        logger.info("----- Norma Específica -----\n");
    }

    private void validarMenuComposicaoQuimica(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Composição Química -----");
        btnMenuComposicaoQuimica.click();

        List<Locator> composicaoQuimicaLinhas = lblConteudoMenuTabela.locator("//tr").all();

        int qtdElementos = 0; //Elemento 0 -> Título | Do 1 a adiante representa os elementos
        for(Locator linhaDoElemento : composicaoQuimicaLinhas){
            if(qtdElementos > 0){
                List<Locator> composicaoDetalhadaElementos = linhaDoElemento.locator("//td").all();

                logger.info("[Elemento Químico "+qtdElementos+"] ---------------");
                //Elemento químico
                AssertHelper.assertTrue("Elemento químico apresentado ("+composicaoDetalhadaElementos.get(0).innerText()+") deve ser igual ao informado na ISA ("+resumoDaAnaliseCotacao.getComposicaoQuimica().get(qtdElementos-1).getSiglaElemento()+")",
                                                composicaoDetalhadaElementos.get(0).innerText().equals(resumoDaAnaliseCotacao.getComposicaoQuimica().get(qtdElementos-1).getSiglaElemento()));

                //Mínimo
                String valorMinimoISA = resumoDaAnaliseCotacao.getComposicaoQuimica().get(qtdElementos-1).getMinComposicao();
                if(valorMinimoISA.equals("-"))
                    valorMinimoISA += "-";
                AssertHelper.assertTrue("Valor mínimo do elemento químico apresentado ("+composicaoDetalhadaElementos.get(1).innerText()+") deve ser igual ao informado na ISA ("+valorMinimoISA+")",
                                                composicaoDetalhadaElementos.get(1).innerText().equals(valorMinimoISA));

                //Máximo
                String valorMaximoISA = resumoDaAnaliseCotacao.getComposicaoQuimica().get(qtdElementos-1).getMaxComposicao();
                if(valorMaximoISA.equals("-"))
                    valorMaximoISA += "-";

                AssertHelper.assertTrue("Valor máximo do elemento químico apresentado ("+composicaoDetalhadaElementos.get(2).innerText()+") deve ser igual ao informado na ISA ("+valorMaximoISA+")",
                                                composicaoDetalhadaElementos.get(2).innerText().equals(valorMaximoISA));

                //Unidade de medida
                AssertHelper.assertTrue("Unidade de medida do elemento químico apresentado ("+composicaoDetalhadaElementos.get(3).innerText()+") deve ser igual ao informado na ISA ("+resumoDaAnaliseCotacao.getComposicaoQuimica().get(qtdElementos-1).getUnidadeComposicao()+")",
                                                composicaoDetalhadaElementos.get(3).innerText().contains(resumoDaAnaliseCotacao.getComposicaoQuimica().get(qtdElementos-1).getUnidadeComposicao()));

                logger.info("[Elemento Químico "+qtdElementos+"] ---------------");
            }
            qtdElementos++;
        }

//        logger.takeScreenShot("ComposicaoQuimica_"+usina, page, true);
        EvidenceManager.addStep(page, "ComposicaoQuimica_"+usina, true);
        logger.info("----- Composição Química -----\n");
    }

    private void validarMenuTipoProduto(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Tipo Produto -----");
        btnMenuTipoDeProduto.click();
        String tipoProdutoApresentada = lblConteudoMenuTexto.innerText();
        AssertHelper.assertTrue("Tipo do Produto apresentada ("+tipoProdutoApresentada+") deve ser igual a informada na ISA ("+resumoDaAnaliseCotacao.getTipoProduto()+")",
                tipoProdutoApresentada.equals(resumoDaAnaliseCotacao.getTipoProduto()));

//        logger.takeScreenShot("TipoProduto_"+usina, page);
        EvidenceManager.addStep(page, "TipoProduto_"+usina);
        logger.info("----- Tipo Produto -----\n");
    }

    private void validarMenuProcessoFabricacao(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Processo de Fabricação -----");
        btnMenuProcessoDeFabricacao.click();
        String processoFabricacaoApresentada = lblConteudoMenuTexto.innerText();
        AssertHelper.assertTrue("Processo de Fabricação apresentada ("+processoFabricacaoApresentada+") deve ser igual a informada na ISA ("+resumoDaAnaliseCotacao.getProcessoFabricacao()+")",
                processoFabricacaoApresentada.equals(resumoDaAnaliseCotacao.getProcessoFabricacao()));

//        logger.takeScreenShot("ProcessoFabricacao_"+usina, page);
        EvidenceManager.addStep(page, "ProcessoFabricacao_"+usina);

        logger.info("----- Processo de Fabricação -----\n");
    }

    private void validarMenuForma(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Forma -----");
        btnMenuForma.click();
        String formaApresentada = lblConteudoMenuTexto.innerText();
        AssertHelper.assertTrue("Forma apresentada ("+formaApresentada+") deve ser igual a informada na ISA ("+resumoDaAnaliseCotacao.getForma()+")",
                formaApresentada.equals(resumoDaAnaliseCotacao.getForma()));

//        logger.takeScreenShot("Forma_"+usina, page);
        EvidenceManager.addStep(page, "Forma_"+usina);
        logger.info("----- Forma -----\n");
    }

    private void validarMenuTratamentoTermico(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Tratamento Térmico -----");
        btnMenuTratamentoTermico.click();
        String tratamentoTermicoApresentada = lblConteudoMenuTexto.innerText();
        AssertHelper.assertTrue("Tratamento térmico apresentado ("+tratamentoTermicoApresentada+") deve ser igual a informada na ISA ("+resumoDaAnaliseCotacao.getTratamentoTermico()+")",
                tratamentoTermicoApresentada.equals(resumoDaAnaliseCotacao.getTratamentoTermico()));

//        logger.takeScreenShot("TratamentoTermico_"+usina, page);
        EvidenceManager.addStep(page, "TratamentoTermico_"+usina);
        logger.info("----- Tratamento Térmico -----\n");
    }

    private void validarMenuAcabamento(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Acabamento -----");
        btnMenuAcabamento.click();
        String acabamentoApresentada = lblConteudoMenuTexto.innerText();
        AssertHelper.assertTrue("Acabamento apresentado ("+acabamentoApresentada+") deve ser igual a informada na ISA ("+resumoDaAnaliseCotacao.getAcabamento()+")",
                acabamentoApresentada.equals(resumoDaAnaliseCotacao.getAcabamento()));

//        logger.takeScreenShot("Acabamento_"+usina, page);
        EvidenceManager.addStep(page, "Acabamento_"+usina);
        logger.info("----- Acabamento -----\n");
    }

    private void validarMenuBitola(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Bitola -----");
        btnMenuBitola.click();
        String bitolaApresentada = lblConteudoMenuTexto.innerText();
        AssertHelper.assertTrue("Bitola apresentada ("+bitolaApresentada+") deve ser igual a informada na ISA ("+resumoDaAnaliseCotacao.getBitola()+")",
                bitolaApresentada.equals(resumoDaAnaliseCotacao.getBitola()));

//        logger.takeScreenShot("Bitola_"+usina, page);
        EvidenceManager.addStep(page, "Bitola_"+usina);
        logger.info("----- Bitola -----\n");
    }

    private void validarMenuComprimento(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Comprimento -----");
        btnMenuComprimento.click();
        List<String> comprimentoApresentada = lblConteudoMenuTexto.allInnerTexts();
        List<String> comprimentoISA = Arrays.asList(resumoDaAnaliseCotacao.getComprimento().replace("mm", "").replaceAll("\\s+", "").split("a"));

        AssertHelper.assertTrue("Comprimento Mínimo apresentado ("+comprimentoApresentada.get(0)+") deve ser igual a informada na ISA ("+comprimentoISA.get(0)+")",
                ValueUtils.getSomenteNumeros(comprimentoApresentada.get(0)).equals(ValueUtils.getSomenteNumeros(comprimentoISA.get(0))));

        AssertHelper.assertTrue("Comprimento Máximo apresentado ("+comprimentoApresentada.get(1)+") deve ser igual a informada na ISA ("+comprimentoISA.get(1)+")",
                ValueUtils.getSomenteNumeros(comprimentoApresentada.get(1)).equals(ValueUtils.getSomenteNumeros(comprimentoISA.get(1))));

//        logger.takeScreenShot("Comprimento_"+usina, page);
        EvidenceManager.addStep(page, "Comprimento_"+usina);
        logger.info("----- Comprimento -----\n");
    }

    private void validarMenuProcessoDeCliente(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Processo de Cliente -----");
        btnMenuProcessoDeCliente.click();
        String processoDeClienteApresentada = lblConteudoMenuTexto.innerText();

        AssertHelper.assertTrue("Processo de Cliente apresentado ("+processoDeClienteApresentada+") deve ser igual a informada na ISA ("+resumoDaAnaliseCotacao.getProcessoDeCliente()+")",
                processoDeClienteApresentada.equals(resumoDaAnaliseCotacao.getProcessoDeCliente()));

//        logger.takeScreenShot("ProcessoDeCliente_"+usina, page);
        EvidenceManager.addStep(page, "ProcessoDeCliente_"+usina );
        logger.info("----- Processo de Cliente -----\n");
    }

    private void validarMenuAplicacaoFinal(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Aplicação final -----");
        btnMenuAplicacaoFinal.click();
        String aplicacaoFinalApresentada = lblConteudoMenuTexto.innerText();
        AssertHelper.assertTrue("Aplicação final apresentada ("+aplicacaoFinalApresentada+") deve ser igual a informada na ISA ("+resumoDaAnaliseCotacao.getAplicacaoFinal()+")",
                aplicacaoFinalApresentada.equals(resumoDaAnaliseCotacao.getAplicacaoFinal()));

//        logger.takeScreenShot("AplicacaoFinal_"+usina, page);
        EvidenceManager.addStep(page, "AplicacaoFinal_"+usina);
        logger.info("----- Aplicação final -----\n");
    }

    private void validarMenuCliente(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Cliente -----");
        btnMenuCliente.click();
        String clienteApresentada = lblConteudoMenuTexto.innerText();
        AssertHelper.assertTrue("Cliente apresentado ("+clienteApresentada+") deve ser igual a informada na ISA ("+resumoDaAnaliseCotacao.getCliente()+")",
                clienteApresentada.equals(resumoDaAnaliseCotacao.getCliente()));

//        logger.takeScreenShot("AplicacaoFinal_"+usina, page);
        EvidenceManager.addStep(page, "AplicacaoFinal_"+usina);
        logger.info("----- Cliente -----\n");
    }

    private void validarMenuMercado(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Mercado -----");
        btnMenuMercado.click();
        String mercadoApresentada = lblConteudoMenuTexto.innerText();
        AssertHelper.assertTrue("Mercado apresentado ("+mercadoApresentada+") deve ser igual a informada na ISA ("+resumoDaAnaliseCotacao.getMercado()+")",
                mercadoApresentada.equals(resumoDaAnaliseCotacao.getMercado()));

//        logger.takeScreenShot("Mercado_"+usina, page);
        EvidenceManager.addStep(page, "Mercado_"+usina);
        logger.info("----- Mercado -----\n");
    }

    private void validarMenuConsumo(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Consumo -----");
        btnMenuConsumo.click();
        String consumoApresentada = lblConteudoMenuTexto.innerText();
        AssertHelper.assertTrue("Consumo apresentado ("+consumoApresentada+") deve ser igual a informada na ISA ("+resumoDaAnaliseCotacao.getConsumo()+")",
                consumoApresentada.equals(resumoDaAnaliseCotacao.getConsumo()));

//        logger.takeScreenShot("Consumo_"+usina, page);
        EvidenceManager.addStep(page, "Consumo_"+usina);
        logger.info("----- Consumo -----\n");
    }

    private void validarMenuDataInicioProjeto(ResumoDaAnaliseCotacao resumoDaAnaliseCotacao, String usina) throws Exception {
        logger.info("----- Data Inicio do Projeto -----");
        btnMenuDataInicioProjeto.click();
        String dataInicioProjetoApresentada = lblConteudoMenuTexto.innerText();
        AssertHelper.assertTrue("Data de inicio do projeto apresentada ("+dataInicioProjetoApresentada+") deve ser igual a informada na ISA ("+resumoDaAnaliseCotacao.getDataInicioDoProjeto()+")",
                dataInicioProjetoApresentada.equals(resumoDaAnaliseCotacao.getDataInicioDoProjeto()));

//        logger.takeScreenShot("DataInicioProjeto_"+usina, page);
        EvidenceManager.addStep(page, "DataInicioProjeto_"+usina);
        logger.info("----- Data Inicio do Projeto -----\n");
    }
    //-------------------------------------------------------------------------------------------------
    public void gerarPdfCotacao() throws Exception {
        //Clicar no logo de pdf para abrir o menu de opções
        elementExist(page, btnPdf);
//        logger.takeScreenShot("Cotacao escolhida", page);
        EvidenceManager.addStep(page, "Cotacao escolhida");
        btnPdf.dispatchEvent("click");

        List<Locator> listaPlantasApresentadas = chkListPlantas.all();


        for(Locator planta : listaPlantasApresentadas) {
            planta.dispatchEvent("click");
        }

//        logger.takeScreenShot("Plantas selecionadas", page);
        EvidenceManager.addStep(page, "Plantas selecionadas");
        //Gerando pdf
        Download download = page.waitForDownload(() -> {
            btnGerarPdf.dispatchEvent("click");
        });

        String pathDonwloadPDF = "C:/Proton/ProtonClient/Attachments/"+download.suggestedFilename();
        download.saveAs(Paths.get(pathDonwloadPDF));
        getCv_().setDownloadFilePath(pathDonwloadPDF);

        logger.info("Download realizado com sucesso.");
    }

    public void fecharModalToolTip() throws Exception {
        //Fechar o ToolTip de materiais similares
        if (page.locator("(//div[contains(@class, 'boxcomponentstyle__Item-sc-1b29j6y-1 ibFXJV')])[2]").isVisible())
            page.locator("(//div[contains(@class, 'boxcomponentstyle__Item-sc-1b29j6y-1 ibFXJV')])[2]").click();
    }

    public void verificarUsinaSelecionada() {
        String usinaSelecionada = usinaExibidaCliente.textContent();

        if (usinaSelecionada.contains("Charqueadas")) {
            Locator abaPrimeiraUsina = getAbasUsinasApresentadas().get(0);

            abaPrimeiraUsina.click();
        } else if (usinaSelecionada.contains("Mogi das Cruzes")) {
            Locator abaSegundaUsina = getAbasUsinasApresentadas().get(1);

            abaSegundaUsina.click();
        } else if (usinaSelecionada.contains("Pindamonhangaba")) {
            Locator abaTerceiraUsina = getAbasUsinasApresentadas().get(2);

            abaTerceiraUsina.click();
        }
    }

    public void requisitarPlantaParaAnalise() {
        verificarUsinaSelecionada();
        requisitarPlanta.click();
    }

    public void selecionarStatusDeEnvio(String novoStatus){
        if (novoStatus.equals("Cancelada")) {
            statusCancelada.click();
            enviarParaRtc.fill("andre.castelli@ex.gerdau.com");
            enviarParaSeller.fill("andre.castelli@ex.gerdau.com");
            btnModalEnviar.click();

        } else if (novoStatus.equals("Edição cliente")) {
            statusEdicaoCliente.click();

        } else if (novoStatus.equals("Finalizada")) {
            statusFinalizada.click();
            btnModalEnviar.click();

        } else if (novoStatus.equals("Validação RTC")) {
            statusValidacaoRTC.click();
            Random random = new Random();
            int aleatorio = random.nextInt(2) + 1;

            if (aleatorio == 1) {
                modalCotacaoAnaliseTecnica.click();
            } else {
                modalCotacaoConsultaPreco.click();
            }
            btnmodalCotacaoProsseguir.click();
            enviarParaRtc.fill("andre.castelli@ex.gerdau.com");

        }

    }

}


