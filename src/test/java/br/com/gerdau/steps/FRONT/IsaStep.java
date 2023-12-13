package br.com.gerdau.steps.front;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.utils.DateUtils;
import br.com.gerdau.pages.gerdaumais.DetalhesCotacaoPage;
import br.com.gerdau.pages.gerdaumais.IsaPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static br.com.gerdau.validations.ValidationsHelper.getCv_;

public class IsaStep {

    IsaPage isaPage = new IsaPage(PlaywrightController.getPage());
    DetalhesCotacaoPage detalhesCotacaoPage = new DetalhesCotacaoPage(PlaywrightController.getPage());

    @Quando("informo o cliente {string}")
    public void informoOCliente(String cliente) throws InterruptedException {
        isaPage.informarCliente(cliente);
        getCv_().addInfosEscolhidasParaNovaCotacao("cliente", cliente);
    }


    @E("escolho cotar o produto")
    public void escolhoCotarOProduto() throws InterruptedException {
        isaPage.escolherOpcaoSugeridaPelaIsa("Cotar produto");
    }

    @E("determino algum aço sugerido no pelo histórico")
    public void determinoAlgumAçoSugeridoNoPeloHistórico() throws InterruptedException {
        isaPage.escolherAleatoriamenteOpcaoSugeridaIsa();
    }

    @E("escolho uma norma sugerida")
    public void escolhoUmaNormaSugerida() throws InterruptedException {
        isaPage.escolherAleatoriamenteOpcaoSugeridaIsa();
    }

    @E("confirmo as características da ultima compra")
    public void confirmoAsCaracterísticasDaUltimaCompra() throws InterruptedException {
        isaPage.informarCaracteristica();
//        isaPage.escolherPrimeiraOpcaoSugeridaIsa();
//        isaPage.escolherOpcaoSugeridaPelaIsa("Sim");
    }

    @E("escolho um processo de fabricação")
    public void escolhoUmProcessoDeFabricação() throws InterruptedException {
        isaPage.escolherAleatoriamenteOpcaoSugeridaIsa();
    }

    @E("informo a dimensão da bitola principal")
    public void informoADimensãoDaBitolaPrincipal() throws InterruptedException {
        isaPage.enviarMensagemChat("1000");
    }

    @E("recuso a inclusão de mais bitolas")
    public void recusoAInclusãoDeMaisBitolas() throws InterruptedException {
        isaPage.escolherOpcaoSugeridaPelaIsa("Não");
    }

    @E("adiciono o comprimeiro em mm do aço")
    public void adicionoOComprimeiroEmMmDoAço() throws InterruptedException {
        isaPage.enviarMensagemChat("100 mm");
    }

    @E("escolho qualquer processo de fabricação da lista")
    public void escolhoQualquerProcessoDeFabricaçãoDaLista() throws InterruptedException {
        isaPage.escolherAleatoriamenteOpcaoSugeridaIsa();
    }

    @E("informo qual peça será produzida com a barra")
    public void informoQualPeçaSeráProduzidaComABarra() throws InterruptedException {
        isaPage.enviarMensagemChat("Peça para automação de testes");
    }

    @E("informo quem é o cliente final")
    public void informoQuemÉOClienteFinal() throws InterruptedException {
        isaPage.enviarMensagemChat("Atomic Solutions");
    }

    @E("informo que a {string} é o cliente final")
    public void informoQueAÉOClienteFinal(String clienteFinal) throws InterruptedException {
        isaPage.enviarMensagemChat(clienteFinal);
    }

    @E("informo que a cotacão será para o mercado {string}")
    public void informoQueACotacãoSeráParaOMercado(String tipoMercado) throws InterruptedException {
        isaPage.enviarMensagemChat("Mercado "+tipoMercado);
    }

    @E("insiro o consumo potencial do produto em toneladas-ano")
    public void insiroOConsumoPotencialDoProdutoEmToneladasAno() throws InterruptedException {
        isaPage.enviarMensagemChat("10");
    }
    @E("adiciono a data inicial do projeto")
    public void adicionoADataInicialDoProjeto() throws InterruptedException {
        isaPage.enviarMensagemChat(DateUtils.increaseOrDecreaseDate(0));
    }

    @E("finalizo a consulta")
    public void finalizoAConsulta() throws Exception {
        isaPage.finalizarConsultaCotacao();
    }

    @Então("aguardo a análise ser concluída")
    public void aguardoAAnáliseSerConcluída() throws Exception {
        isaPage.aguardarCriacaoCotacao();
    }

    @E("a cotação é criada")
    public void aCotaçãoÉCriada() throws Exception {
        detalhesCotacaoPage.validarCriacaoCotacao();
    }


    @Quando("o chat inicia sem solicitar a inclusão de um cliente devido ao perfil de Vendedor")
    public void oChatIniciaSemSolicitarAInclusãoDeUmClienteDevidoAoPerfilDeVendedor() throws Exception {
        isaPage.validarInicioCotacaoPerfilVendedorCliente();
    }


    @E("todas as abas são validadas com as informações fornecidas na ISA")
    public void todasAsAbasSãoValidadasComAsInformaçõesFornecidasNaISA() throws Exception {
        detalhesCotacaoPage.fecharModalToolTip();
        detalhesCotacaoPage.validarInfosCotacao_TodasUsinas(getCv_().getResumoDaAnaliseCotacao());
    }

    @E("apenas a primeira aba é validada as informações fornecidas na ISA")
    public void apenasAPrimeiraAbaÉValidadaAsInformaçõesFornecidasNaISA() throws Exception {
        detalhesCotacaoPage.validarInfosCotacao_PrimeiraUsina(getCv_().getResumoDaAnaliseCotacao());
    }

    @E("determino o aço {string}")
    public void determinoOAço(String aco) throws InterruptedException {
        isaPage.enviarMensagemChat(aco);
    }
    @E("escolho o processo de fabricação {string}")
    public void escolhoOProcessoDeFabricação(String processoFabricacao) throws InterruptedException {
        isaPage.escolherOpcaoSugeridaPelaIsa(processoFabricacao);
    }
    @E("informo a dimensão da bitola principal {string}")
    public void informoADimensãoDaBitolaPrincipal(String bitola) throws InterruptedException {
        isaPage.enviarMensagemChat(bitola);
    }
    @E("adiciono o comprimeiro em mm do aço {string}")
    public void adicionoOComprimeiroEmMmDoAço(String comprimento) throws InterruptedException {
        isaPage.enviarMensagemChat(comprimento);
    }
    @E("escolho o processo de fabricação da lista {string}")
    public void escolhoOProcessoDeFabricaçãoDaLista(String processoFabricacao) throws InterruptedException {
        isaPage.escolherOpcaoSugeridaPelaIsa(processoFabricacao);
    }
    @E("informo a peça será produzida com a barra {string}")
    public void informoAPeçaSeráProduzidaComABarra(String peca) throws InterruptedException {
        isaPage.escolherOpcaoSugeridaPelaIsa(peca);
    }
    @E("escolho o sistema aleatorio")
    public void escolhoOSistemaAleatorio() throws InterruptedException {
        isaPage.escolherAleatoriamenteOpcaoSugeridaIsa();
    }

}
