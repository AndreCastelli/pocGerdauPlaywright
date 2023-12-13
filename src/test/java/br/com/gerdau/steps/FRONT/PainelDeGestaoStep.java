package br.com.gerdau.steps.front;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.AssertHelper;
import br.com.gerdau.pages.gerdaumais.PainelDeGestaoPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;

import java.util.Arrays;
import java.util.List;

public class PainelDeGestaoStep {
    PainelDeGestaoPage painelDeGestaoPage = new PainelDeGestaoPage(PlaywrightController.getPage());

    //Permissionamento Painel de gestão

    @E("acessar a aba de Painel de Gestão")
    public void acessarAAbaDe() throws Exception {
        painelDeGestaoPage.acessarPainelDeGestao();
    }

    @Então("os blocos principais {string} devem ser apresentados corretamente")
    public void osBlocosPrincipaisDevemSerApresentadosCorretamente(String blocos) throws Exception {
        List<String> blocosPermitidosList = Arrays.asList(blocos.trim().split(","));

        for(int i = 0; i < blocosPermitidosList.size(); i++)
            blocosPermitidosList.set(i, blocosPermitidosList.get(i).trim());

        AssertHelper.assertTrue("Os ("+blocos+") no painel de gestão devem ser correspondidos", painelDeGestaoPage.validarTextosPainelDeGestao(blocosPermitidosList));
    }

    @Então("a quantidade dos {string} gráficos devem ser apresentados corretamente")
    public void aQuantidadeDosGráficosDevemSerApresentadosCorretamente(String txtQuantidade) throws Exception {
        int qtdGraficosNecessarios = Integer. parseInt(txtQuantidade);
        painelDeGestaoPage.validarGraficosPainelDeGestao(qtdGraficosNecessarios);
    }
}