package br.com.gerdau.pages.gerdaumais;

import br.com.gerdau.utils.EvidenceManager;
import br.com.gerdau.utils.ValueUtils;
import com.google.api.client.util.Strings;
import com.microsoft.playwright.*;
import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.gerdau.utils.ValueUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static br.com.atomic.framework.playwright.PlaywrightElementHelper.waitUntilVisible;
import static br.com.gerdau.validations.ValidationsHelper.getCv_;



public class CarteiraPage
{
    private Page page;

    //Pesquisa BP
    private Locator btnBarraPesquisa;
    private Locator btnFiltro;
    private Locator btnBPMI;
    private Locator lblPesquisaBP;
    private Locator chkPrimeiroCliente;
    private Locator btnVerFiliaisSelecionadas;

    //Para validação BP
    private Locator txtCliente;
    public String NovoCodCliente;
    private Locator btnCarteira;
    private Locator txtEsperaCodCliente;


    //Modal de Agendamento
    private Locator btnAgendarEnvio;
    private Locator btnCriarNovaProgramacao;
    private Locator txtNomeAgendamento;
    private Locator txtClienteAgendamento;
    private Locator txtEmailAgendamento;
    private Locator btnSelecionarColunas;
    private Locator txtColunas;
    private Locator btnFinalizarNovaProgramacao;
    private Locator txtMensagemSucessoCriacaoNovoAgendamento;
    private Locator btnDeletarAgendamento;
    private Locator txtMensagemSucessoDeletarAgendamento;

    //Permissionamento Modal de Agendamento
    private Locator btnModalCliente;
    private Locator chkSelecionarTodos;

    //Modal Faturado
    private Locator btnFaturado;
    private Locator btnConsultarFaturado;

    LoggerHelper logger = new LoggerHelper(CarteiraPage.class);
    public CarteiraPage(Page page)
    {
        this.page = page;
        this.btnBarraPesquisa = page.locator("//*[contains(@data-testid, 'customer-select')]");
        this.btnFiltro = page.locator("//*[contains(@data-testid, 'customer-search-modal-filter-type')]");
        this.btnBPMI = page.locator("//*[contains(@data-testid, 'filter-type-option-MI-0')]");
        this.lblPesquisaBP = page.locator("//*[contains(@data-testid, 'bp-search') and (@class = 'text-fieldcomponentstyle__TextFieldStyled-m1gg0l-0 bxuFQM')]");
        this.chkPrimeiroCliente = page.locator("(//*[contains(@data-icon, 'check-square')])[2]");
        this.btnVerFiliaisSelecionadas = page.locator("//*[contains(@class, 'buttoncomponentstyle__ButtonContentStyled-dt09ca-2 jRwuzA')and text()='Ver filiais selecionadas']");
        this.txtCliente = page.locator("(//*[contains(@data-testid, 'customer-id-name')])[1]");
        this.btnCarteira = page.locator("(//*[contains(@class, 'menucomponentstyle__MenuItemNavLinkStyled-sc-1s8eb1g-4 jjoTHI')])");
        this.txtEsperaCodCliente = page.locator("(//*[contains(@class, 'ag-cell ag-cell-not-inline-editing ag-cell-normal-height ag-cell-value')])[3]");
        this.btnAgendarEnvio = page.locator("(//div[text() = 'Agendar envio'])[1]");
        this.btnCriarNovaProgramacao = page.locator("//*[contains(text(), 'Criar novo agendamento')]");
        this.txtNomeAgendamento = page.locator("//input[@class = 'text-fieldcomponentstyle__TextFieldStyled-m1gg0l-0 fHkdtN']");
        this.txtClienteAgendamento = page.locator("//input[@placeholder = 'Selecionar filiais']");
        this.txtEmailAgendamento = page.locator("//input[@type = 'email']");
        this.btnSelecionarColunas = page.locator("//*[contains(@class, 'boxcomponentstyle__HboxStyled-sc-1b29j6y-0 hlXSfG')and text()='Selecionar Colunas']");
        this.txtColunas = page.locator("(//*[@class = 'schedule-modal-columnsstyle__CheckboxFieldWrapper-sc-14j50rb-0 JziYC']//label)");
        this.btnFinalizarNovaProgramacao = page.locator("//div[text() = 'Salvar agendamento']");
        this.txtMensagemSucessoCriacaoNovoAgendamento = page.locator("//*[text() = 'Agendamento programado com sucesso!']");
        this.btnDeletarAgendamento = page.locator("//*[text() = 'Deletar']");
        this.txtMensagemSucessoDeletarAgendamento = page.locator("//*[text() = 'Seu agendamento foi deletado']");
        this.btnModalCliente = page.locator("//div[@class = 'boxcomponentstyle__HboxStyled-sc-1b29j6y-0 dRhawo']");
        this.chkSelecionarTodos = page.locator("(//*[@class='svg-inline--fa fa-check-square fa-w-14 checkbox-fieldcomponentstyle__CheckboxCheckedStyledPrimary-sc-19x3vql-3 brLtFj'])[1]");
        this.btnFaturado = page.locator("(//*[contains(@class, 'tabscomponentstyle__TabItemTextStyled-sc-1qllehw-1 kcGmFx')and text()='Faturado'])[1]");
        this.btnConsultarFaturado = page.locator("//*[contains(@class, 'buttoncomponentstyle__ButtonContentStyled-dt09ca-2 jRwuzA')and text()='Consultar']");

    }


    //Pesquisa cliente BP/Permissionamento
    public void acessarPesquisa() {
        //Apenas para chegar na barra de pesquisa principal
        waitUntilVisible(page, btnBarraPesquisa);
        btnBarraPesquisa.click();
    }

    public void pesquisarBPNomeCliente(String usuario) throws Exception {
        waitUntilVisible(page, btnFiltro);
        btnFiltro.click();
        Thread.sleep(3000);
        btnBPMI.click();
        Thread.sleep(12000);

        lblPesquisaBP.fill(usuario);
        lblPesquisaBP.press("Enter");
        Thread.sleep(5000);
        chkPrimeiroCliente.click();

        //Para selecionar o cliente
        Thread.sleep(5000);
        String textoTotal = txtCliente.innerText();
        String[] splitCliente = textoTotal.split(" ");

        String CodCliente = splitCliente[0];
        NovoCodCliente = CodCliente.substring(1);

        logger.info("Codigo Cliente pesquisado: " + NovoCodCliente);
        //Para avançar
//        logger.takeScreenShot("Cliente selecionado", page);
        EvidenceManager.addStep(page, "Cliente selecionado");
        btnVerFiliaisSelecionadas.click();
        Thread.sleep(10000);
    }

    public void validarCodigoCliente () throws Exception {
        waitUntilVisible(page, txtEsperaCodCliente);
        Thread.sleep(5000);

//        logger.takeScreenShot("Valores de clientes apresentados", page);
        EvidenceManager.addStep(page, "Valores de clientes apresentados");

        for (int i = 2; i <= 10; i += 1) {
            String txtCodClienteApresentado = page.locator("(//*[contains(@col-id, 'clientNumber')])["+i+"]").textContent();
            logger.info("Valor de comparação apresentado:" + txtCodClienteApresentado);

            AssertHelper.assertTrue("O código do cliente ("+NovoCodCliente+") deve ser igual ao resultado" +
                    " apresentado ("+txtCodClienteApresentado+")", Objects.equals(NovoCodCliente, txtCodClienteApresentado));
        }
    }

    public void acessarCarteira() {
        //Apenas para chegar na barra de pesquisa principal
        waitUntilVisible(page, btnCarteira);
        btnCarteira.click();
    }

    public boolean validarCodigoBPsModal(List<String> listaBPs) throws Exception {
        Thread.sleep(10000);
        waitUntilVisible(page, txtCliente);

        ArrayList<String> listaBPsNecessarios = new ArrayList<String>();

        List<String> todosBP = page.locator("(//*[contains(@data-testid, 'customer-id-name')])").allInnerTexts();

        for (String elemento : todosBP) {
            String BP = elemento.substring(0, 10);
            listaBPsNecessarios.add(BP);
        }

        ArrayList<String> BPsValidos = new ArrayList<String>();
        ArrayList<String> BpsInvalidos = new ArrayList<String>();

        logger.info("Lista de BPs apresentados:");
        ValueUtils.printListString(listaBPsNecessarios);

        logger.info("Lista de BPs requeridos:");
        ValueUtils.printListString(listaBPs);

//        logger.takeScreenShot("Campos de BPs apresentados", page, true);
        EvidenceManager.addStep(page, "Campos de BPs apresentados", true);

        if (listaBPsNecessarios.size() == listaBPs.size()) {

            for(String tab : listaBPsNecessarios)
            {
                if (listaBPs.contains(tab))
                    BPsValidos.add(tab);
                else
                    BpsInvalidos.add(tab);
            }

            if(BpsInvalidos.size() > 0)
            {
                logger.info("BPs inválidos:");
                for(String invalida : BpsInvalidos)
                    logger.info(invalida);

                return false;
            }
            else
            {
                logger.info("BPs válidos:");
                for(String valida : BPsValidos)
                    logger.info(valida);

                return true;
            }
        }
        else{return false;}
    }

    public void pesquisarClienteQualquer(String Cliente) throws Exception {
        page.locator("//*[contains(@class,'text-fieldcomponentstyle__TextFieldStyled-m1gg0l-0 bxuFQM')]").fill(Cliente);
    }
    public void ValidarClienteInexistente() throws Exception {
        Thread.sleep(10000);

        List<String> todosBP = page.locator("(//*[contains(@data-testid, 'customer-id-name')])").allInnerTexts();

//        logger.takeScreenShot("Clientes apresentados", page);
        EvidenceManager.addStep(page, "Clientes apresentados");

        logger.info("Lista de Clientes apresentados:");
        ValueUtils.printListString(todosBP);

            AssertHelper.assertTrue("A pesquisa realizada não deve retornar resultado" +
                    "", todosBP == null || todosBP.isEmpty());

    }


    //Modal de agendamento
    public void acessarModalAgendamento() throws Exception {
        //Apenas para acessar o modal
        waitUntilVisible(page, btnAgendarEnvio);
        btnAgendarEnvio.click();
        Thread.sleep(3000);
    }

    public void numeroAgendamentosRealizadosParaCriacao() throws Exception {

        List<String> listaAgendamentos = page.locator("//*[contains(@class, 'schedule-modal-liststyle__ScheduleItem-sc-195osiv-2 lnMdOv')]").allInnerTexts();

//        logger.takeScreenShot("Agendamentos apresentados", page);
        EvidenceManager.addStep(page, "Agendamentos apresentados");

        AssertHelper.assertTrue("A lista deve retornar até 4 resultados para realizar criação de um novo agendamento" +
                "", listaAgendamentos.size() < 5);

        btnCriarNovaProgramacao.click();
        Thread.sleep(3000);
    }

    public void preencherDadosAgendamento(String nomeAgendamento, String BPCliente) throws Exception {
        //Método preenche nome do agendamento, seleciona um cliente e preenche o email para envio

        //Preencher nome agendamento
        waitUntilVisible(page, txtNomeAgendamento);
        txtNomeAgendamento.fill(nomeAgendamento);

        Locator mensagemErroNome = page.locator("//*[contains(@class, 'schedule-namestyke__ValidateDuplicateMessage-m1agcn-0 eIjhUr')]");
        int i = 1;

        if (mensagemErroNome.isVisible()) {
            while (mensagemErroNome.isVisible()) {
                txtNomeAgendamento.fill(nomeAgendamento + " " + i);
                i++;
            }
        }
        logger.info("Nome agendamento preenchido.");


        //Preencher nome Cliente
        txtClienteAgendamento.fill(BPCliente);
        Locator txtSelecaoAgendamento = page.locator("//label[contains(text(), '"+BPCliente+"')]");

        AssertHelper.assertTrue("Deve possuir um cliente com o Nome/BP inserido: " +
                "" + BPCliente, txtSelecaoAgendamento != null);

        txtSelecaoAgendamento.click();
        logger.info("Cliente selecionado: " + BPCliente);


        /*//Preencher o email
        txtEmailAgendamento.fill(email);
        txtEmailAgendamento.press("Enter");

        Locator mensagemErroEmail = page.locator("//*[contains(@class, 'typographycomponentstyle__Body-sc-1pm6mqw-6 oermN')]");

        AssertHelper.assertTrue("Email preenchido deve estar correto: " +
                "", mensagemErroEmail.isHidden());*/

    }

    public void escolherFrequenciaEnvio(String frequencia) throws Exception {

        switch (frequencia.toLowerCase()) {
            case "mensal":
                //Selecionar a opção de mensal
                page.locator("//*[contains(text(), 'Mensal')]").click();
                logger.info("Frequência mensal escolhida");

                //Calendário
                page.locator("//*[@class = 'day-selectstyle__DaySelectWrapper-t24xz7-0 gdWjPv']").click();
                Random random = new Random();
                int contador = 0;

                while (contador < 3) {
                    int NumeroRandomico = random.nextInt(31) + 1;
                    logger.info("Dia escolhido: " + NumeroRandomico);
                    page.locator("(//*[@class = 'fixed-calendarstyle__DayWrapper-sc-1cqvgyt-1 jIYbGV'])["+NumeroRandomico+"]").click();
                    contador++;
                    Thread.sleep(500);
                }
                page.locator("//*[text() = 'Selecionar']").click();
                break;

            case "semanal":
                //Selecionar a opção de semanal
                page.locator("//*[contains(text(), 'Semanal')]").click();
                logger.info("Frequência semanal escolhida");

                //Randomico para selecionar o dia da semana
                Random randomSemanal = new Random();
                int contadorSemanal = 0;

                while (contadorSemanal < 3) {
                    int NumeroRandomico = randomSemanal.nextInt(7) + 1;
                    Locator semanal = page.locator("(//*[@class = 'boxcomponentstyle__HboxStyled-sc-1b29j6y-0 cnTJGp']//label)["+NumeroRandomico+"]");
                    semanal.click();
                    logger.info("Dia escolhido: " + semanal.innerText());
                    contadorSemanal++;
                }
                break;

            case "diário":
                page.locator("//*[contains(text(), 'Diário')]").click();
                logger.info("Frequência diária escolhida");
                break;

            default:
                logger.error("Opção inválida");
//                logger.takeScreenShot("Frequencia de envio invalida", page);
                EvidenceManager.addStep(page, "Frequencia de envio invalida");
        }
        //Para avançar
        Thread.sleep(2000);
//        logger.takeScreenShot("Dados preenchidos", page);
        EvidenceManager.addStep(page, "Dados preenchidos");
        btnSelecionarColunas.click();
        Thread.sleep(4000);
    }

    public void selecionarColunasAleatoriamente(String numero) throws Exception {
        waitUntilVisible(page, txtColunas);

        //Randomico para selecionar o numero de colunas
        Random randomColunas = new Random();
        int contadorColunas = 0;

        while (contadorColunas < Integer.parseInt(numero)) {
            int NumeroRandomico = randomColunas.nextInt(txtColunas.count()) + 1;
            Locator colunaEscolhida = page.locator("(//*[@class = 'schedule-modal-columnsstyle__CheckboxFieldWrapper-sc-14j50rb-0 JziYC']//label)["+NumeroRandomico+"]");
            colunaEscolhida.click();
            logger.info("Coluna escolhida: " + colunaEscolhida.innerText());
            contadorColunas++;
        }

        Thread.sleep(2000);
//        logger.takeScreenShot("Colunas selecionadas", page);
        EvidenceManager.addStep(page, "Colunas selecionadas");
        logger.info("Colunas selecionadas com sucesso.");
    }

    public void finalizarAgendamento()  {
        waitUntilVisible(page, btnFinalizarNovaProgramacao);
        btnFinalizarNovaProgramacao.click();

        logger.info("Agendamento finalizado com sucesso.");
    }

    public void mensagemSucessoCriacaoNovoAgendamento() throws Exception {
        waitUntilVisible(page, txtMensagemSucessoCriacaoNovoAgendamento);

        AssertHelper.assertTrue("A mensagem de sucesso deve aparecer" +
                "", txtMensagemSucessoCriacaoNovoAgendamento.isVisible());

//        logger.takeScreenShot("Agendamento criado com sucesso.", page);
        EvidenceManager.addStep(page, "Agendamento criado com sucesso");
    }


    //Modal de agendamento: Deletar
    public void numeroAgendamentosRealizados() throws Exception {

        List<String> listaAgendamentos = page.locator("//*[@class = 'schedule-modal-liststyle__ScheduleItem-sc-195osiv-2 lnMdOv']").allInnerTexts();

//        logger.takeScreenShot("Agendamentos apresentados", page);
        EvidenceManager.addStep(page, "Agendamentos apresentados");

        AssertHelper.assertTrue("A lista deve retornar pelo menos 1 resultado para realizar a exclusão de um agendamento" +
                "", listaAgendamentos.size() > 0);

        Random randomColunas = new Random();

            int NumeroRandomico = randomColunas.nextInt(listaAgendamentos.size()) + 1;
            Locator agendamentoEscolhido = page.locator("(//*[@class = 'schedule-modal-liststyle__ScheduleItem-sc-195osiv-2 lnMdOv'])["+NumeroRandomico+"]");
            logger.info("Agendamento escolhido: " + agendamentoEscolhido.innerText());
            agendamentoEscolhido.click();

        Thread.sleep(3000);
//        logger.takeScreenShot("Agendamento escolhido", page);
        EvidenceManager.addStep(page, "Agendamento escolhido");
    }

    public void excluirAgendamento() throws Exception {
        //Apenas para excluir o modal
        waitUntilVisible(page, btnDeletarAgendamento);
        btnDeletarAgendamento.click();
        Thread.sleep(3000);
    }
    public void mensagemSucessoDeletarAgendamento() throws Exception {
        waitUntilVisible(page, txtMensagemSucessoDeletarAgendamento);

        AssertHelper.assertTrue("A mensagem de sucesso deve aparecer" +
                "", txtMensagemSucessoDeletarAgendamento.isVisible());

//        logger.takeScreenShot("Agendamento deletado com sucesso.", page);
        EvidenceManager.addStep(page, "Agendamento deletado com sucesso");
    }

    public void pesquisarTodosNomeCliente(String usuario) throws Exception {
        page.locator("//*[@class = 'text-fieldcomponentstyle__TextFieldStyled-m1gg0l-0 bxuFQM']").fill(usuario);
        page.locator("//*[@class = 'text-fieldcomponentstyle__TextFieldStyled-m1gg0l-0 bxuFQM']").press("Enter");
        Thread.sleep(7000);

        //Para selecionar o cliente

        if (!page.locator("(//*[@class='customer-search-modal-adminstyle__SelectSomeClientMessage-m6b5gt-1 ebowsB'])[1]").isVisible())
            chkSelecionarTodos.click();

        chkSelecionarTodos.click();

        logger.takeScreenShot("Cliente pesquisado.", page);
        logger.info("Cliente pesquisado.");
        btnVerFiliaisSelecionadas.click();
        Thread.sleep(5000);
    }

    //Permissionamento Modal de agendamento
    public void pesquisarNomeClientePermissionamento(String usuario) throws Exception {
        page.locator("//*[@class = 'text-fieldcomponentstyle__TextFieldStyled-m1gg0l-0 bxuFQM']").fill(usuario);
        page.locator("//*[@class = 'text-fieldcomponentstyle__TextFieldStyled-m1gg0l-0 bxuFQM']").press("Enter");
        Thread.sleep(7000);

        //Para selecionar o cliente

        if (!page.locator("(//*[@class='customer-search-modal-adminstyle__SelectSomeClientMessage-m6b5gt-1 ebowsB'])[1]").isVisible())
            chkSelecionarTodos.click();

        page.locator("(//*[@class = 'svg-inline--fa fa-check-square fa-w-14 checkbox-fieldcomponentstyle__CheckboxCheckedStyledPrimary-sc-19x3vql-3 brLtFj'])[2]").click();
        logger.takeScreenShot("Cliente pesquisado.", page);
        logger.info("Cliente pesquisado.");
        btnVerFiliaisSelecionadas.click();
        Thread.sleep(5000);
    }

    public void pesquisarNomeCliente(String usuario) throws Exception {
        page.locator("//*[@class = 'text-fieldcomponentstyle__TextFieldStyled-m1gg0l-0 bxuFQM']").fill(usuario);
        page.locator("//*[@class = 'text-fieldcomponentstyle__TextFieldStyled-m1gg0l-0 bxuFQM']").press("Enter");
        Thread.sleep(7000);

        //Para selecionar o cliente

        if (!page.locator("(//*[@class='customer-search-modal-adminstyle__SelectSomeClientMessage-m6b5gt-1 ebowsB'])[1]").isVisible())
            chkSelecionarTodos.click();

        page.locator("(//*[@class = 'svg-inline--fa fa-check-square fa-w-14 checkbox-fieldcomponentstyle__CheckboxCheckedStyledPrimary-sc-19x3vql-3 brLtFj'])[2]").click();
        logger.takeScreenShot("Cliente pesquisado.", page);
        logger.info("Cliente pesquisado.");
        btnVerFiliaisSelecionadas.click();
        Thread.sleep(5000);
    }

    public void acessarModalClienteAgendamento() throws Exception {
        //Apenas para abrir a opção do modal no cliente
        waitUntilVisible(page, btnModalCliente);
        btnModalCliente.click();
        Thread.sleep(1000);
    }

    public boolean validarBpsModalDeAgendamento(List<String> listaBPs) throws Exception {

        ArrayList<String> listaBPsNecessarios = new ArrayList<String>();

        List<String> todosBP = page.locator("//label[@class = 'typographycomponentstyle__Label-sc-1pm6mqw-27 fBQgwl']").allInnerTexts();

        for (String elemento : todosBP) {
            String BP = elemento.substring(0, 10);
            listaBPsNecessarios.add(BP);
        }

        ArrayList<String> BPsValidos = new ArrayList<String>();
        ArrayList<String> BpsInvalidos = new ArrayList<String>();

        logger.info("Lista de BPs apresentados:");
        ValueUtils.printListString(listaBPsNecessarios);

        logger.info("Lista de BPs requeridos:");
        ValueUtils.printListString(listaBPs);

//        logger.takeScreenShot("Campos de BPs apresentados", page, true);
        EvidenceManager.addStep(page, "Campos de BPs apresentados", true);

        if (listaBPsNecessarios.size() == listaBPs.size()) {

            for(String tab : listaBPsNecessarios)
            {
                if (listaBPs.contains(tab))
                    BPsValidos.add(tab);
                else
                    BpsInvalidos.add(tab);
            }

            if(BpsInvalidos.size() > 0)
            {
                logger.info("BPs inválidos:");
                for(String invalida : BpsInvalidos)
                    logger.info(invalida);

                return false;
            }
            else
            {
                logger.info("BPs válidos:");
                for(String valida : BPsValidos)
                    logger.info(valida);

                return true;
            }
        }
        else{return false;}
    }



    //Modal de Faturado
    public List<String> guardarDescricaoMaterial() throws Exception {
        Thread.sleep(5000);
        List<String> listaDescricaoMaterial = new ArrayList<>();
        waitUntilVisible(page, page.locator("(//*[@col-id= 'descMaterial'])[1]"));

        listaDescricaoMaterial = page.locator("//div[@id='BilledMI-grid']//div[@class='ag-root ag-unselectable ag-layout-normal']//div[@col-id='descMaterial']").allInnerTexts();
        listaDescricaoMaterial.remove(0); //Remover o texto do título da tabela

        logger.info("Valores de materiais encontrados na aba de Carteira:");
        ValueUtils.printListString(listaDescricaoMaterial);
        getCv_().setListaMateriaisCarteira(listaDescricaoMaterial);
        return listaDescricaoMaterial;
    }

    public void acessarModalFaturado() throws Exception {
        //Apenas para acessar o modal de faturado dentro da carteira
        waitUntilVisible(page, btnFaturado);
        btnFaturado.click();
        Thread.sleep(2000);
    }

    public void consultarUmaDataEspecificaPreenchendoOInicio(String data) throws Exception {
        page.locator("(//*[@class = 'date-pickercomponentstyle__DatePickerStyled-x8l3we-0 jRBAyO'])[1]").fill(data);
        page.locator("(//*[@class = 'date-pickercomponentstyle__DatePickerStyled-x8l3we-0 jRBAyO'])[1]").press("Enter");
        Thread.sleep(1000);

        btnConsultarFaturado.click();
    }


    public List<String> guardarPartNumber() throws Exception {
        Thread.sleep(5000);
        List<String> listaPartNumberSemFiltro = new ArrayList<>();
        waitUntilVisible(page, page.locator("(//*[@col-id= 'descMaterial'])[1]"));

        listaPartNumberSemFiltro = page.locator("//div[@id='BilledMI-grid']//div[@class='ag-root ag-unselectable ag-layout-normal']//div[@col-id='codMaterialClient']").allInnerTexts();
        listaPartNumberSemFiltro.remove(0); //Remover o texto do título da tabela

        List<String> listaPartNumberFiltrada = new ArrayList<>();

        //Filtrar os espaços em branco da lista
        for (String PartNumber : listaPartNumberSemFiltro) {
            if (!PartNumber.trim().isEmpty()) {
                listaPartNumberFiltrada.add(PartNumber);
            }
        }

        logger.info("Valores de materiais encontrados na aba de Carteira:");
        ValueUtils.printListString(listaPartNumberFiltrada);
        getCv_().setListaPartNumberFiltrada(listaPartNumberFiltrada);
        return listaPartNumberFiltrada;
    }


}


