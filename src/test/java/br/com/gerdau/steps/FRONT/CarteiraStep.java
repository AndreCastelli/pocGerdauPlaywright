package br.com.gerdau.steps.front;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.AssertHelper;
import br.com.gerdau.pages.gerdaumais.CarteiraPage;
import br.com.gerdau.pages.gerdaumais.ImplantacaoPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.Arrays;
import java.util.List;

import static br.com.gerdau.utils.ValueUtils.getValue;

public class CarteiraStep {
    CarteiraPage carteiraPage = new CarteiraPage(PlaywrightController.getPage());
    ImplantacaoPage implantacaoPage = new ImplantacaoPage(PlaywrightController.getPage());


    //Escritas de BPs
    @Quando("o modal de pesquisa for apresentado")
    public void oModalDePesquisaForApresentado() {
        carteiraPage.acessarPesquisa();
    }

    @E("pesquisar pelo nome de cliente")
    public void pesquisarPeloNomeDeCliente() throws Exception {
        carteiraPage.pesquisarBPNomeCliente(getValue("in_pesquisa", "maxiforja"));
    }

    @Quando("acesso a aba de carteira")
    public void acessoAAbaDeCarteira() {
        carteiraPage.acessarCarteira();
    }

    @Então("as escritas de BP devem ser apresentadas corretamente de acordo com o código do cliente")
    public void asEscritasDeBPDevemSerApresentadasCorretamenteDeAcordoComOCódigoDoCliente() throws Exception {
        carteiraPage.validarCodigoCliente();
    }



    //ValidarBP'sCorretos

    @Então("os {string} devem ser apresentados de acordo com o cliente correto")
    public void osDevemSerApresentadosDeAcordoComOClienteCorreto(String BP) throws Exception {
        List<String> BPsPermitidosList = Arrays.asList(BP.trim().split(","));

        for(int i = 0; i < BPsPermitidosList.size(); i++)
            BPsPermitidosList.set(i, BPsPermitidosList.get(i).trim());

        AssertHelper.assertTrue("Os ("+BP+") no modal devem ser correspondidos", carteiraPage.validarCodigoBPsModal(BPsPermitidosList));
    }



    //ValidarNomeInvalidoBP
    @E("pesquisar pelo nome de cliente {string}")
    public void pesquisarPeloNomeDeCliente(String Cliente) throws Exception {
        carteiraPage.pesquisarClienteQualquer(getValue("in_pesquisa", Cliente));
    }

    @Então("não devem ser apresentados clientes não relacionados ao BP")
    public void nãoDevemSerApresentadosClientesNãoRelacionadosAoBP() throws Exception {
        carteiraPage.ValidarClienteInexistente();
    }




    //Modal de agendamento
    @Quando("acessar o modal de agendar envio")
    public void acessarOModalDeAgendarEnvio() throws Exception {
        carteiraPage.acessarModalAgendamento();
    }

    @E("validar a quantidade de agendamentos para criar uma nova programação")
    public void validarAQuantidadeDeAgendamentosParaCriarUmaNovaProgramação() throws Exception {
        carteiraPage.numeroAgendamentosRealizadosParaCriacao();
    }

    @E("preencher as informações necessárias do agendamento")
    public void preencherAsInformaçõesNecessáriasDoAgendamento() throws Exception {
        carteiraPage.preencherDadosAgendamento(getValue("in_nome", "Teste QA"),
                getValue("in_bpCliente", "0100242721"));
    }

    @E("seleciono a frequência de envio")
    public void selecionoAFrequênciaDeEnvio() throws Exception {
        carteiraPage.escolherFrequenciaEnvio(getValue("in_envio", "semanal"));
    }

    @Quando("selecionar as colunas aleatoriamente")
    public void selecionarAsColunasAleatoriamente() throws Exception {
        carteiraPage.selecionarColunasAleatoriamente(getValue("in_colunas", "5"));
    }

    @E("criar o novo agendamento")
    public void criarONovoAgendamento() {
        carteiraPage.finalizarAgendamento();
    }

    @Então("deve ser exibida a mensagem de sucesso")
    public void deveSerExibidaAMensagemDeSucesso() throws Exception {
        carteiraPage.mensagemSucessoCriacaoNovoAgendamento();
    }

    //Modal de agendamento: Deletar
    @E("selecionar um agendamento criado")
    public void selecionarUmAgendamentoCriado() throws Exception {
        carteiraPage.numeroAgendamentosRealizados();
    }

    @Quando("deletar esse agendamento")
    public void deletarEsseAgendamento() throws Exception {
        carteiraPage.excluirAgendamento();
    }

    @Então("a mensagem de exclusão deve ser exibida")
    public void aMensagemDeExclusãoDeveSerExibida() throws Exception {
        carteiraPage.mensagemSucessoDeletarAgendamento();
    }

    @E("pesquisar pelo nome determinado de {string}")
    public void pesquisarPeloNomeDeterminadoDe(String usuario) throws Exception {
        carteiraPage.pesquisarTodosNomeCliente(usuario);
    }

    //Permissionamento Modal de agendamento
    @E("pesquisar pelo nome de {string}")
    public void pesquisarPeloNomeDe(String usuário) throws Exception {
        carteiraPage.pesquisarNomeClientePermissionamento(usuário);
    }



    @E("verificar os BPs disponíveis para selecionar agendamento")
    public void verificarOsBPsDisponíveisParaSelecionarAgendamento() throws Exception {
        carteiraPage.acessarModalClienteAgendamento();
    }

    @Então("os {string} devem ser apresentados no modal de agendamento de acordo com o cliente correto")
    public void osDevemSerApresentadosNoModalDeAgendamentoDeAcordoComOClienteCorreto(String BP) throws Exception {
        List<String> BPsPermitidosList = Arrays.asList(BP.trim().split(","));

        for(int i = 0; i < BPsPermitidosList.size(); i++)
            BPsPermitidosList.set(i, BPsPermitidosList.get(i).trim());

        AssertHelper.assertTrue("Os ("+BP+") no modal devem ser correspondidos", carteiraPage.validarBpsModalDeAgendamento(BPsPermitidosList));
    }


    //Modal de Faturado

    @E("acesso o modal de faturado")
    public void acessoOModalDeFaturado() throws Exception {
        carteiraPage.acessarModalFaturado();
    }

    @E("consulto uma data específica")
    public void consultoUmaDataEspecífica() throws Exception {
        carteiraPage.consultarUmaDataEspecificaPreenchendoOInicio(getValue("in_data", "11/05/2021"));
    }

    @E("guardo as informações de materiais relacionados ao cliente")
    public void guardoAsInformaçõesDeMateriaisRelacionadosAoCliente() throws Exception {
        carteiraPage.guardarDescricaoMaterial();
    }

    @E("pesquisar pelo nome de {string} específico")
    public void pesquisarPeloNomeDeEspecífico(String usuario) throws Exception {
        carteiraPage.pesquisarNomeCliente(usuario);
    }

    @E("guardo as informações de Part Number relacionadas ao cliente")
    public void guardoAsInformaçõesDePartNumberRelacionadasAoCliente() throws Exception {
        carteiraPage.guardarPartNumber();
    }

}




