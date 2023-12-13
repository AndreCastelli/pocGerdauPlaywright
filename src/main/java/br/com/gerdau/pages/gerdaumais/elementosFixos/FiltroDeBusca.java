package br.com.gerdau.pages.gerdaumais.elementosFixos;

import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.helpers.LoggerHelper;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static br.com.atomic.framework.playwright.PlaywrightElementHelper.elementExist;

public class FiltroDeBusca {

    private static Page page;
    private static Locator tabFiltroDeBuscas;
    private Locator clientes;
    private Locator numeroIsa;
    private Locator aco;
    private Locator norma;
    private Locator emailCriador;
    private static Locator status;
    private static Locator statusTexto;
    private static String statusTextoTemplate;
    private Locator plantaPinda;
    private Locator pindaPlantaAbertura;
    private Locator pindaRespPorEPPPED;
    private Locator pindaEnviadaAoCliente;
    private Locator plantaCharqueadas;
    private Locator charqueadasPlantaAbertura;
    private Locator charqueadasPlantaAberturaRespPorEPPPED;
    private Locator charqueadasPlantaAberturaEnviadaAoCliente;
    private Locator plantaMogi;
    private Locator mogiPlantaAbertura;
    private Locator mogiRespPorEPPPED;
    private Locator mogiEnviadaAoCliente;
    private Locator forma;
    private Locator bitolaMinima;
    private Locator bitolaMaxima;
    private Locator ladoMinimo;
    private Locator ladoMaximo;
    private Locator larguraMinima;
    private Locator larguraMaxima;
    private Locator espessuraMinima;
    private Locator espessuraMaxima;
    private Locator raioDeCanto;

    private Locator prioridade;
    private Locator tipoConsulta;
    private Locator precoDisponivel;
    private static Locator carregarFiltros;
    private Locator ocultarFiltros;
    private Locator limparFiltros;
    private static Locator pesquisar;


    LoggerHelper logger = new LoggerHelper(FiltroDeBusca.class);

    public FiltroDeBusca(Page page) {

        this.page = page;
        this.tabFiltroDeBuscas = page.locator(" //div[@id='searchBar']");
        this.clientes = page.locator("//input[@data-testid='client']");
        this.numeroIsa = page.locator("//input[@data-testid='id']");
        this.aco = page.locator("//input[@data-testid='steel']");
        this.norma = page.locator("//input[@data-testid='specification']");
        this.emailCriador = page.locator("//input[@data-testid='email']");
        this.status = page.locator("//input[@data-testid='status']");
        this.statusTextoTemplate = "//input[@class='text-fieldcomponentstyle__TextFieldStyled-m1gg0l-0 bxuFQM' and text()='{var}']";
        this.plantaPinda = page.locator("//div[@data-testid='pindamonhangaba']");
        this.pindaPlantaAbertura = page.locator("//label[@for='pindamonhangaba_required']");
        this.pindaRespPorEPPPED = page.locator("//label[@for='pindamonhangaba_reviewed']");
        this.pindaEnviadaAoCliente = page.locator("//label[@for='pindamonhangaba_selected']");
        this.plantaCharqueadas = page.locator("//div[@data-testid='charqueadas']");
        this.charqueadasPlantaAbertura = page.locator("//label[@for='charqueadas_required']");
        this.charqueadasPlantaAberturaRespPorEPPPED = page.locator("//label[@for='charqueadas_reviewed']");
        this.charqueadasPlantaAberturaEnviadaAoCliente = page.locator("//label[@for='charqueadas_selected']");
        this.plantaMogi = page.locator("//div[@data-testid='mogiDasCruzes']");
        this.mogiPlantaAbertura = page.locator("//label[@for='mogiDasCruzes_required']");
        this.mogiRespPorEPPPED = page.locator("//label[@for='mogiDasCruzes_reviewed']");
        this.mogiEnviadaAoCliente = page.locator("//label[@for='mogiDasCruzes_selected']");
        this.forma = page.locator("//select[@data-testid='form']");
        this.bitolaMinima = page.locator("//input[@data-testid='gaugeMin']");
        this.bitolaMaxima = page.locator("//input[@data-testid='gaugeMax']");
        this.ladoMinimo = page.locator("//input[@data-testid='sideMin']");
        this.ladoMaximo = page.locator("//input[@data-testid='sideMax']");
        this.larguraMinima = page.locator("//input[@data-testid='widthMin']");
        this.larguraMaxima = page.locator("//input[@data-testid='widthMax']");
        this.espessuraMinima = page.locator("//input[@data-testid='thicknessMin']");
        this.espessuraMaxima = page.locator("//input[@data-testid='thicknessMax']");
        this.raioDeCanto = page.locator("//select[@data-testid='cornerRadius']");
        this.prioridade = page.locator("//input[@data-testid='priority']");
        this.tipoConsulta = page.locator("//input[@data-testid='flow']");
        this.precoDisponivel = page.locator("//select[@data-testid='hasPrice']");
        this.carregarFiltros = page.locator("//div[@class='buttoncomponentstyle__ButtonContentStyled-dt09ca-2 jRwuzA' and text()='Carregar mais filtros']");
        this.ocultarFiltros = page.locator("//div[text()='Ocultar filtros']");
        this.limparFiltros = page.locator("//div[text()='Limpar filtros']");
        this.pesquisar = page.locator("//div[text()='Pesquisar']");

    }

    public static void filtrarPorStatus(String filtroStatus) throws Exception {
        status.click();
        statusTexto = page.locator(statusTextoTemplate.replace("{var}", filtroStatus));
        statusTexto.click();
    }

    public static void carregarMaisFiltros() {carregarFiltros.click();}

    public static void clicarPesquisar() {pesquisar.click();}

}
