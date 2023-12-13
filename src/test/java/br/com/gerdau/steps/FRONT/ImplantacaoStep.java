package br.com.gerdau.steps.front;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.AssertHelper;
import br.com.gerdau.pages.gerdaumais.ImplantacaoPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.Arrays;
import java.util.List;

public class ImplantacaoStep {
    ImplantacaoPage implantacaoPage = new ImplantacaoPage(PlaywrightController.getPage());

    //Permissionamento Implantação
    @Quando("acesso a aba de implantação")
    public void acessoAAbaDeImplantação() throws Exception {
        implantacaoPage.acessarImplantacao();
    }

    @E("acessar para solicitar produto via planilha")
    public void acessarParaSolicitarProdutoViaPlanilha() throws Exception {
        implantacaoPage.acessarPedidoViaPlanilha();
    }

    @E("acessar para solicitar produto via seleção de itens")
    public void acessarParaSolicitarProdutoViaSeleçãoDeItens() throws InterruptedException {
        implantacaoPage.acessarPedidoViaSelecaoDeItens();
    }

    @E("entrar no modal de emissor")
    public void entrarNoModalDeEmissor() throws Exception {
        implantacaoPage.selecionarModalEmissor();
    }

    @Então("os {string} devem ser apresentados na implantação de acordo com o cliente correto")
    public void osDevemSerApresentadosNaImplantaçãoDeAcordoComOClienteCorreto(String BP) throws Exception {
        List<String> BPsPermitidosList = Arrays.asList(BP.trim().split(","));

        for(int i = 0; i < BPsPermitidosList.size(); i++)
            BPsPermitidosList.set(i, BPsPermitidosList.get(i).trim());

        AssertHelper.assertTrue("Os ("+BP+") na implantação devem ser correspondidos", implantacaoPage.validarBpsImplantaçãoEmissor(BPsPermitidosList));
    }

    @E("selecionar um emissor")
    public void selecionarUmEmissor() throws Exception {
        implantacaoPage.selecionarEmissor();
    }

    @E("entrar no modal de recebedor")
    public void entrarNoModalDeRecebedor() throws Exception {
        implantacaoPage.selecionarModalRecebedor();
    }

    @Então("os {string} devem ser apresentados pelo recebedor de acordo com o cliente correto")
    public void osDevemSerApresentadosPeloRecebedorDeAcordoComOClienteCorreto(String BP) throws Exception {
        List<String> BPsPermitidosList = Arrays.asList(BP.trim().split(","));

        for(int i = 0; i < BPsPermitidosList.size(); i++)
            BPsPermitidosList.set(i, BPsPermitidosList.get(i).trim());

        AssertHelper.assertTrue("Os ("+BP+") na implantação devem ser correspondidos", implantacaoPage.validarBpsImplantaçãoRecebedor(BPsPermitidosList));
    }


    //Permissionamento de materiais
    @E("selecionar um recebedor")
    public void selecionarUmRecebedor() throws Exception {
        implantacaoPage.selecionarRecebedor();
    }

    @E("selecionar a nomenclatura de descrição do material")
    public void selecionarANomenclaturaDeDescriçãoDoMaterial() throws Exception {
        implantacaoPage.selecionarDescricaoMaterial();
    }

    @Então("os materias devem ser apresentados corretamente")
    public void osMateriasDevemSerApresentadosCorretamente() throws Exception {
        implantacaoPage.validarPermissionamentoDeMateriais();
    }

    @E("selecionar a opção Part Number")
    public void selecionarAOpçãoPartNumber() throws Exception {
        implantacaoPage.selecionarPartNumber();
    }

    @Então("os materias de acordo com o Part Number devem ser apresentados corretamente")
    public void osMateriasDeAcordoComOPartNumberDevemSerApresentadosCorretamente() throws Exception {
        implantacaoPage.validarPermissionamentoDePartNumberVerificandoOsMateriais();
    }

    @Então("não deve aparecer material {string} correspondente a outro cliente")
    public void nãoDeveAparecerMaterialCorrespondenteAOutroCliente(String materiais) throws Exception {
        List<String> materiaisParaInserção = Arrays.asList(materiais.trim().split(";"));

        for(int i = 0; i < materiaisParaInserção.size(); i++)
            materiaisParaInserção.set(i, materiaisParaInserção.get(i).trim());

        implantacaoPage.validarMateriaisDeOutroCliente(materiaisParaInserção);
    }
}