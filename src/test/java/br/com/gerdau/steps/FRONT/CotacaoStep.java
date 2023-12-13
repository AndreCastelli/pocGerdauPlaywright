package br.com.gerdau.steps.front;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.AssertHelper;
import br.com.gerdau.pages.gerdaumais.CotacaoPage;
import br.com.gerdau.pages.gerdaumais.DetalhesCotacaoPage;
import br.com.gerdau.pages.gerdaumais.IsaPage;
import br.com.gerdau.pages.gerdaumais.elementosFixos.FiltroDeBusca;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static br.com.gerdau.utils.ValueUtils.getValue;

public class CotacaoStep {

    CotacaoPage cotacaoPage = new CotacaoPage(PlaywrightController.getPage());
    DetalhesCotacaoPage detalhesCotacaoPage = new DetalhesCotacaoPage(PlaywrightController.getPage());
    //IsaPage cotacaoClienteExistente = new IsaPage(PlaywrightController.getPage());

    @Quando("busco uma cotação a partir do número ISA válido {string}")
    public void buscoUmaCotaçãoAPartirDoNúmeroISAVálido(String cotacao) throws Exception {
        cotacaoPage.realizarPesquisaIsa(getValue("in_numeroIsa", cotacao));
    }

    @Então("a cotação deve ser apresentada")
    public void aCotaçãoDeveSerApresentada() throws Exception {
        cotacaoPage.validarPesquisaCotacaoIsa(getValue("in_numeroIsa", "0004506/23"));
    }


    @E("acesso a Isa para criar uma nova cotação")
    public void acessoAIsaParaCriarUmaNovaCotação() {
        cotacaoPage.acessarNovaConsulta();
    }
    @E("acesso uma cotação disponível")
    public void acessoUmaCotaçãoDisponível() throws Exception {
        cotacaoPage.acessarCotacaoAleatoria();
    }

    @Quando("filtro pelo status {string}")
    public void filtro_pelo_status(String status) throws Exception {
        FiltroDeBusca.carregarMaisFiltros();
        FiltroDeBusca.filtrarPorStatus(status);
        FiltroDeBusca.clicarPesquisar();
    }

    @Quando("seleciono a planta")
    public void selecionoAPlanta() {
        detalhesCotacaoPage.requisitarPlantaParaAnalise();

    }
    @Quando("altero o status da CF para {string}")
    public void alteroOStatusDaCFPara(String novoStatus) {
        detalhesCotacaoPage.selecionarStatusDeEnvio(novoStatus);

    }


    @Quando("envio a CF para Gerdau")
    public void envioACFParaGerdau() throws Exception {
        cotacaoPage.aguardarProcessamentoDaCF();
        cotacaoPage.enviarCF();
        cotacaoPage.enviarCFModal();
    }

    @Então("valido status {string} da CF")
    public void validoStatusDaCF(String status) throws Exception {
        cotacaoPage.validarEnvioCF(status);
    }

    //Busca cotação com cliente
    @Quando("busco uma cotação a partir do nome de cliente válido")
    public void buscoUmaCotaçãoAPartirDoNomeDeClienteVálido() throws Exception {
        cotacaoPage.realizarPesquisaCliente(getValue("in_cliente", "TOYOTA DO BRASIL"));
    }

    @Então("a cotação deve ser apresentada corretamente")
    public void aCotaçãoDeveSerApresentadaCorretamente() throws Exception {
        cotacaoPage.validarPesquisaCliente(getValue("in_cliente", "TOYOTA DO BRASIL"));
    }


    //Permissionamento cotação
    @Então("a lista de cotação para o cliente deve ser apresentada corretamente de acordo com o nome do {string}")
    public void aListaDeCotaçãoParaOClienteDeveSerApresentadaCorretamenteDeAcordoComONomeDo(String clientes) throws Exception {
        cotacaoPage.validarPermissionamentoCotação(clientes);
    }


    //Validação Status Cotação Cliente
    @Então("a lista de status para o cliente deve ser apresentada corretamente de acordo com a permissão do {string}")
    public void aListaDeStatusParaOClienteDeveSerApresentadaCorretamenteDeAcordoComAPermissãoDo(String usuario) throws Exception {
        cotacaoPage.ValidarPermissionamentoStatus(usuario);
    }

    @Quando("filtro o historico {string}")
    public void filtroOHistorico(String historico) {
        cotacaoPage.fecharModal();
        cotacaoPage.filtrarPorData(historico);
    }

    @Então("valido mensagem de consulta indisponivel")
    public void validoMensagemDeConsultaIndisponivel() throws Exception {
        cotacaoPage.validarMensagemConsultaIndisponivel();
    }

    @Quando("acesso a cotacao buscada")
    public void acessoACotacaoBuscada() {
        cotacaoPage.clicarCotacaoFiltrada();
    }

    @Quando("altero numero da cotacao na url")
    public void alteroNumeroDaCotacaoNaUrl() {
        cotacaoPage.alterarCotacaoNaURL();
    }

    @Então("valido mensagem de erro inesperado")
    public void validoMensagemDeErroInesperado() throws Exception {
        cotacaoPage.validarMensagemDeErroInesperado();
    }
}
