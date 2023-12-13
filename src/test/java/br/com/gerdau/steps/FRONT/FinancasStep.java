package br.com.gerdau.steps.front;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.AssertHelper;
import br.com.gerdau.pages.gerdaumais.FinancasPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.Arrays;
import java.util.List;

public class FinancasStep {
    FinancasPage financasPage = new FinancasPage(PlaywrightController.getPage());

    //Permissionamento Finanças
    @Quando("acesso a aba de Financas")
    public void acessoAAbaDeFinancas() throws Exception {
        financasPage.acessarFinancas();
    }

    @E("acesso o modal de Ver limites")
    public void acessoOModalDeVerLimites() throws Exception {
        financasPage.acessarModalVerLimites();
    }

    @Então("os {string} devem ser apresentados na aba de finanças de acordo com as permissões corretas")
    public void osDevemSerApresentadosNaAbaDeFinançasDeAcordoComAsPermissõesCorretas(String BP) throws Exception {
        List<String> BPsPermitidosList = Arrays.asList(BP.trim().split(","));

        for(int i = 0; i < BPsPermitidosList.size(); i++)
            BPsPermitidosList.set(i, BPsPermitidosList.get(i).trim());

        AssertHelper.assertTrue("Os ("+BP+") na finanças devem ser correspondidos", financasPage.validarBpsFinancas(BPsPermitidosList));
    }

    @E("o nome do cliente {string} deve ser visualizado corretamente")
    public void oNomeDoClienteDeveSerVisualizadoCorretamente(String nomeCliente) throws Exception {
        financasPage.validarNomeFinancas(nomeCliente);
    }

}




