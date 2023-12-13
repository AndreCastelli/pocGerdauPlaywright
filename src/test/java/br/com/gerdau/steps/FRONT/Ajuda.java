package br.com.gerdau.steps.front;

import br.com.atomic.framework.base.PageBase;
import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.AssertHelper;
import br.com.gerdau.pages.gerdaumais.CentralDeAjudaPage;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.Arrays;
import java.util.List;

public class Ajuda extends PageBase {
    CentralDeAjudaPage centralDeAjudaPage = new CentralDeAjudaPage(PlaywrightController.getPage());

    @Quando("a central de ajuda é apresentada")
    public void aCentralDeAjudaÉApresentada() throws Exception {
        centralDeAjudaPage.acessarCentralDeAjuda();
    }

    @Então("as {string} são listadas conforme atualização para o {string}")
    public void asSãoListadasConformeAtualizaçãoParaO(String menusPermitidos, String usuario) throws Exception {
        List<String> menusPermitidosList = Arrays.asList(menusPermitidos.trim().split(","));

        for (int i = 0; i < menusPermitidosList.size(); i++)
            menusPermitidosList.set(i, menusPermitidosList.get(i).trim());

       AssertHelper.assertTrue("As abas necessárias ("+menusPermitidos+") do usuário "+usuario+" devem estar visíveis", centralDeAjudaPage.validarCentralDeMenu(menusPermitidosList));
    }
}
