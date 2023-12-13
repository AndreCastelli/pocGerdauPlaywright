package br.com.gerdau.steps.front;
import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.gerdau.pages.gerdaumais.TelaInicioPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;

public class TelaInicioStep {

    TelaInicioPage telaInicioPage = new TelaInicioPage(PlaywrightController.getPage());

    @Quando("acesso as escritas de BP")
    public void acessoAsEscritasDeBP() throws Exception {
        telaInicioPage.acessarEscritasBP();
    }

    @Quando("seleciono as escritas de BP automaticamente")
    public void selecionoAsEscritasDeBPAutomaticamente() throws Exception {
        telaInicioPage.selecionarTodosClientesModalAberto();
    }


    @E("fecho o modal inicial de seleção de BPs")
    public void fechoOModalInicialDeSeleçãoDeBPs() throws Exception {
        telaInicioPage.fecharModalInicial();
    }
}
