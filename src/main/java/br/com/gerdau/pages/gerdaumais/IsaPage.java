package br.com.gerdau.pages.gerdaumais;

import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.atomic.framework.utils.Utils;
import br.com.gerdau.models.ElementosQuimicosCotacao;
import br.com.gerdau.models.ResumoDaAnaliseCotacao;
import br.com.gerdau.utils.EvidenceManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static br.com.atomic.framework.playwright.PlaywrightElementHelper.*;
import static br.com.gerdau.utils.PlaywrightUtils_Gerdau.pressionarEnter;
import static br.com.gerdau.validations.ValidationsHelper.getCv_;

public class IsaPage {
    private Page page;
    private Locator txtEnviarMensagemParaChatbot;
    private Locator lblTodasMensagens;
    private Locator btnOpcoesSugeridasPelaIsa;
    private Locator btnOpcoesSugeridasPelaIsa_FinalizarConsulta;
    private Locator boxListaInformacoesTecnicasAnalise;
    private Locator lblAguardeEnquantoAnalisamosCotacao;

    LoggerHelper logger = new LoggerHelper(IsaPage.class);
    private final int DEFAULT_TIMEOUT = 30;
    private final String DEFAULT_MENSAGEM_INICIO_ISA_PERFIL_VENDEDOR_CLIENTE = "Você deseja cotar um produto, submeter uma norma para revisão ou inserir um manual qualidade?";
    private final String DEFAULT_MENSAGEM_INICIO_ISA_PERFIL_GERDAU = "Por gentileza, me informe o cliente da cotação.";

    public IsaPage(Page page) {
        this.page = page;

        this.txtEnviarMensagemParaChatbot = page.locator("//input[contains(@class, 'ChatField')]");
        this.lblTodasMensagens = page.locator("//div[contains(@class, 'ChatMessageStyled')]");
        this.btnOpcoesSugeridasPelaIsa = page.locator("//div[@type='success']");
        this.btnOpcoesSugeridasPelaIsa_FinalizarConsulta = page.locator("//div[@type='analysis']");
        this.boxListaInformacoesTecnicasAnalise = page.locator("[id='result-analysis']");
        this.lblAguardeEnquantoAnalisamosCotacao = page.locator("//h2[contains(text(), 'Aguarde enquanto analisamos')]");
    }

    private void aguardarRespostaIsa(List<Locator> listaMensagensAntesEnvio) throws InterruptedException {
        int tries = 0;
        List<Locator> listaMensagensPosEnvio = lblTodasMensagens.all();

        while (listaMensagensAntesEnvio.size()+2 != listaMensagensPosEnvio.size() && tries < 30) {
            Thread.sleep(1000);
            tries++;
            listaMensagensPosEnvio = lblTodasMensagens.all();
        }
    }

    public void enviarMensagemChat(String mensagem) throws InterruptedException {
        List<Locator> listaMensagensAntesEnvio = lblTodasMensagens.all();
        txtEnviarMensagemParaChatbot.fill(mensagem);
        pressionarEnter(page);

        logger.info("Mensagem enviada: "+mensagem);
        //Aguarda resposta da Isa
        aguardarRespostaIsa(listaMensagensAntesEnvio);
    }

    private boolean isMensagemDaIsa(Locator lblMensagem){
        if(lblMensagem.locator("//span").getAttribute("direction").equals("left"))
            return true;
        else
            return false;
    }

    private List<String> getTextoUltimaMensagem(){
        Locator ultimaMensagem = lblTodasMensagens.last();
        List<Locator> todosElementosTextosUltimaMensagem = ultimaMensagem.locator("//p").all();
        List<String> textosUltimaMensagem = new ArrayList<String>();

        for(Locator elementoTexto : todosElementosTextosUltimaMensagem){
            textosUltimaMensagem.add(elementoTexto.innerText());
        }
        return textosUltimaMensagem;
    }

    private List<String> getTodasMensagensChat(){
        List<Locator> listaTodasMensagens = lblTodasMensagens.all();
        List<String> conteudoTodasMensagens = new ArrayList<String>();

        for(Locator elementoMensagem : listaTodasMensagens){
            List<Locator> listaTextoMensagem = elementoMensagem.locator("//p").all();

            for(Locator textoMensagem : listaTextoMensagem){
                conteudoTodasMensagens.add(textoMensagem.innerText());
            }
        }

        return conteudoTodasMensagens;
    }

    public void aguardaInicioIsa() throws InterruptedException {
        int tries = 0;
        boolean isaAtiva = false;
        List<String> mensagensAtuais = getTodasMensagensChat();

        while (!isaAtiva && tries < 30){
            Thread.sleep(1000);

            for (String mensagem : mensagensAtuais){
                if(mensagem.contains(DEFAULT_MENSAGEM_INICIO_ISA_PERFIL_GERDAU) || mensagem.contains(DEFAULT_MENSAGEM_INICIO_ISA_PERFIL_VENDEDOR_CLIENTE)){
                    isaAtiva = true;
                    break;
                }
            }

            tries++;
            mensagensAtuais = getTodasMensagensChat();
        }

    }

    public List<Locator> aguardarListaElementosTerValor(Locator locator) throws InterruptedException {
        int tries = 0;
        List<Locator> listLocator = locator.all();

        while (listLocator.size() == 0 && tries < DEFAULT_TIMEOUT){
            Thread.sleep(1000);
            listLocator = locator.all();
            tries++;
        }

        return  listLocator;

    }

    public String escolherOpcaoSugeridaPelaIsa(String opcao) throws InterruptedException {
        List<Locator> listaOpcoesIsa = aguardarListaElementosTerValor(btnOpcoesSugeridasPelaIsa);
        List<Locator> listaMensagensAntesEnvio = lblTodasMensagens.all();

        for(Locator opcaoSugerida : listaOpcoesIsa){
            String opcaoSugeridaTexto = opcaoSugerida.innerText();
            if (opcaoSugeridaTexto.toLowerCase().equals(opcao.toString().toLowerCase())){
                opcaoSugerida.click();
                logger.info("Opção escolhida pela descrição: "+opcao);
                aguardarRespostaIsa(listaMensagensAntesEnvio);
                return opcao;
            }
        }


        return null;
    }

    public String escolherPrimeiraOpcaoSugeridaIsa() throws InterruptedException {
        List<Locator> listaMensagensAntesEnvio = lblTodasMensagens.all();
        Locator primeiraOpcaoSugerida = btnOpcoesSugeridasPelaIsa.first();
        String descricaoOpcao = primeiraOpcaoSugerida.innerText();

        primeiraOpcaoSugerida.click();
        logger.info("Primeira opção: "+descricaoOpcao);
        aguardarRespostaIsa(listaMensagensAntesEnvio);
        return descricaoOpcao;
    }

    public String escolherAleatoriamenteOpcaoSugeridaIsa() throws InterruptedException {
        List<Locator> listaOpcoesIsa = aguardarListaElementosTerValor(btnOpcoesSugeridasPelaIsa);
        List<Locator> listaMensagensAntesEnvio = lblTodasMensagens.all();


        int indexAleatorio = Utils.randomNumberInRange(0, listaOpcoesIsa.size()-1);
        String descricaoOpcaoEscolhida = listaOpcoesIsa.get(indexAleatorio).innerText();
        listaOpcoesIsa.get(indexAleatorio).click();
        logger.info("Opção aleatória escolhida: "+descricaoOpcaoEscolhida);
        aguardarRespostaIsa(listaMensagensAntesEnvio);

        return descricaoOpcaoEscolhida;

    }

    //---------------------------------------------------------------------------------------------------------
    public void informarCliente(String cliente) throws InterruptedException {
        aguardaInicioIsa();
        enviarMensagemChat(cliente);
    }

    public void informarCaracteristica() throws InterruptedException {
        List<Locator> listaMensagensAntesEnvio = lblTodasMensagens.all();
        aguardarRespostaIsa(listaMensagensAntesEnvio);

        boolean encontrouMensagemFormaEscolhida = false;
        List<String> listaMensagens = getTextoUltimaMensagem();

        for(String mensagem : listaMensagens){
            if(mensagem.contains("Na sua última compra do aço") && mensagem.contains("as características foram")){
                encontrouMensagemFormaEscolhida = true;
                break;
            }
        }

        if(!encontrouMensagemFormaEscolhida) {
            escolherAleatoriamenteOpcaoSugeridaIsa();

            listaMensagensAntesEnvio = lblTodasMensagens.all();
            aguardarRespostaIsa(listaMensagensAntesEnvio);
        }


        escolherOpcaoSugeridaPelaIsa("Sim");


    }

    public void validarInicioCotacaoPerfilVendedorCliente() throws Exception {
        boolean encontrouMensagemInicioVendedorCliente = false;
        aguardaInicioIsa();
        List<String> listaMensagens = getTextoUltimaMensagem();

        for(String mensagem : listaMensagens){
            if(mensagem.equals(DEFAULT_MENSAGEM_INICIO_ISA_PERFIL_VENDEDOR_CLIENTE)){
                encontrouMensagemInicioVendedorCliente = true;
                break;
            }
        }

        AssertHelper.assertTrue("Mensagem "+ DEFAULT_MENSAGEM_INICIO_ISA_PERFIL_VENDEDOR_CLIENTE +" deve ser apresentada no início do chat", encontrouMensagemInicioVendedorCliente);
    }

    public void finalizarConsultaCotacao() throws Exception {
        AssertHelper.assertTrue("Botão 'Finalizar consulta' deve ser apresentado", waitUntilVisible(page, btnOpcoesSugeridasPelaIsa_FinalizarConsulta));

        List<String> listaTodasEspecificacoesTecnicasCotacao = boxListaInformacoesTecnicasAnalise.allInnerTexts();
        List<Locator> listaTodosElementosResumoAnalise = page.locator("//div[contains(@class, 'ChatResultAttribute')]").all();
        ArrayList<ElementosQuimicosCotacao> listaComposicaoQuimica = new ArrayList<ElementosQuimicosCotacao>();
        HashMap<String, String> resumoAnaliseCotacao = new HashMap<String, String>();

        for(Locator elementoAnalise : listaTodosElementosResumoAnalise){
            String titulo = elementoAnalise.locator("//label").innerText();

            //Análise da composição quimica
            if(titulo.toLowerCase().equals("composição química")){
                //Busca todas as linhas da composição quimica
                List<Locator> composicaoQuimicaLinhas = elementoAnalise.locator("//table[contains(@class, 'Table')]/tbody/tr").all();

                int qtdElementos = 0; //Elemento 0 -> Título | Do 1 a adiante representa os elementos
                for(Locator linhaDoElemento : composicaoQuimicaLinhas){
                    if(qtdElementos > 0){
                        List<Locator> composicaoDetalhadaElementos = linhaDoElemento.locator("//td").all();
                        ElementosQuimicosCotacao elementoQuimico = new ElementosQuimicosCotacao(composicaoDetalhadaElementos.get(0).innerText(), //Sigla elemento
                                composicaoDetalhadaElementos.get(1).innerText(), //Min
                                composicaoDetalhadaElementos.get(2).innerText(), //Max
                                composicaoDetalhadaElementos.get(3).innerText()); //Unidade de medida

                        listaComposicaoQuimica.add(elementoQuimico);
                    }
                    qtdElementos++;
                }
            }
            else {
                String valor = elementoAnalise.locator("//span[contains(@class, 'inline')]").innerText();
                resumoAnaliseCotacao.put(titulo, valor);

            }
        }

        logger.info("Informações técnicas:\n");
        logger.info("\n"+listaTodasEspecificacoesTecnicasCotacao.toString()+"\n");

        ResumoDaAnaliseCotacao resumoDaAnaliseCotacao = new ResumoDaAnaliseCotacao(resumoAnaliseCotacao, listaComposicaoQuimica);
        getCv_().setResumoDaAnaliseCotacao(resumoDaAnaliseCotacao);

//        logger.takeScreenShot("ConversaISA", page, true);
        EvidenceManager.addStep(page, "ConversaISA");
        btnOpcoesSugeridasPelaIsa_FinalizarConsulta.click();
    }

    public void aguardarCriacaoCotacao() throws Exception {
        AssertHelper.assertTrue("Modal de aguarde análise deve existir", elementExist(page, lblAguardeEnquantoAnalisamosCotacao, 10));
//        logger.takeScreenShot("AguardeAnalise", page);
        EvidenceManager.addStep(page, "AguardeAnalise");
        waitUntilNotExist(page, lblAguardeEnquantoAnalisamosCotacao, 60);
    }






}
