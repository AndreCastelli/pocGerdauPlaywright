package br.com.gerdau.pages.gerdaumais;

import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.gerdau.utils.EvidenceManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static br.com.atomic.framework.playwright.PlaywrightElementHelper.elementExist;
import static br.com.atomic.framework.playwright.PlaywrightElementHelper.waitUntilVisible;


public class TelaInicioPage
{
    private Page page;
    //Tabs
    private Locator txtPesquisa;
    private Locator btnFiltro;
    private Locator btnBP;
    private Locator btnSelecionarTodos;
    private Locator btnVerFiliais;
    private Locator txtMensagemErro;
    private Locator btnCancelar;
    private Locator btnToolTip;

    LoggerHelper logger = new LoggerHelper(TelaInicioPage.class);
    public TelaInicioPage(Page page)
    {
        this.page = page;
        this.txtPesquisa = page.locator("//div[contains(@data-testid,'customer-select')]");
        this.btnFiltro = page.locator("//div[contains(@class, 'filter-type-drop-downstyle__SelectedText-rudc14-1 gCVGth')]");
        this.btnBP = page.locator("(//*[contains(@class, 'typographycomponentstyle__Label-sc-1pm6mqw-27 fFMIwI') and text() = 'BP'])[1]");
        this.btnSelecionarTodos = page.locator("(//*[contains(@class, 'svg-inline--fa fa-check-square fa-w-14 checkbox-fieldcomponentstyle__CheckboxCheckedStyledPrimary-sc-19x3vql-3 brLtFj')])[1]");
        this.btnVerFiliais = page.locator("//div[contains(@class, 'customer-search-modal-footer')]//button[@kind='primary']/div");
        this.txtMensagemErro = page.locator("//*[contains(@class, 'customer-search-modal-clientstyle__SelectSomeClientMessage-fmegcp-1 ixzSig')]");
        this.btnCancelar = page.locator("//div[contains(@class, 'customer-search-modal-footer')]//button[@kind='secondary']//div");
        this.btnToolTip = page.locator("(//div[contains(@class, 'boxcomponentstyle__Item-sc-1b29j6y-1 ibFXJV')])[2]");
    }

    public void acessarEscritasBP() throws Exception {
        try {
            waitUntilVisible(page, txtPesquisa);
            Thread.sleep(2000);
            txtPesquisa.click();
            logger.info("Pesquisa acessada com sucesso");

            waitUntilVisible(page, btnSelecionarTodos);
            Thread.sleep(5000);

            if (btnFiltro.isVisible()) {

                waitUntilVisible(page, btnFiltro);
                btnFiltro.click();
                logger.info("Filtro acessado.");

                waitUntilVisible(page, btnBP);
                btnBP.click();
                logger.info("Mercado interno - BP selecionado com sucesso.");

                Thread.sleep(5000);

                if (txtMensagemErro.isVisible()) {
                    btnSelecionarTodos.click();
                    logger.info("Selecionou todos os clientes.");

                    btnVerFiliais.dispatchEvent("click");
                    logger.info("Ver filiais selecionadas acessado corretamente.");
                } else {
                    Thread.sleep(3000);
                    btnVerFiliais.click();
                    logger.info("Ver filiais selecionadas acessado corretamente.");
                }
            } else {
                if (txtMensagemErro.isVisible()) {
                    btnSelecionarTodos.click();
                    logger.info("Selecionou todos os clientes.");

                    btnVerFiliais.dispatchEvent("click");
                    logger.info("Ver filiais selecionadas acessado corretamente.");

                } else {
                    Thread.sleep(3000);
                    btnVerFiliais.dispatchEvent("click");
                    logger.info("Ver filiais selecionadas acessado corretamente.");
                }
            }
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            logger.info("[FALHA] Erro ao verificar as abas da header");
            EvidenceManager.addStep(page, "Erro ao verificar as abas da header");
            throw new Exception("Erro inesperado ao tentar verificar as abas da header");
        }
    }

    public void selecionarTodosClientesModalAberto() throws Exception {
        waitUntilVisible(page, btnSelecionarTodos);
        Thread.sleep(5000);

        if (btnFiltro.isVisible()) {

            waitUntilVisible(page, btnFiltro);
            btnFiltro.click();
            logger.info("Filtro acessado.");

            waitUntilVisible(page, btnBP);
            btnBP.click();
            logger.info("Mercado interno - BP selecionado com sucesso.");

            Thread.sleep(5000);

            if (txtMensagemErro.isVisible()) {
                btnSelecionarTodos.click();
                logger.info("Selecionou todos os clientes.");

                btnVerFiliais.dispatchEvent("click");
                logger.info("Ver filiais selecionadas acessado corretamente.");
            } else {
                Thread.sleep(3000);
                btnVerFiliais.click();
                logger.info("Ver filiais selecionadas acessado corretamente.");
            }
        } else {
            if (txtMensagemErro.isVisible()) {
                btnSelecionarTodos.click();
                logger.info("Selecionou todos os clientes.");

                btnVerFiliais.dispatchEvent("click");
                logger.info("Ver filiais selecionadas acessado corretamente.");

            } else {
                Thread.sleep(3000);
                btnVerFiliais.dispatchEvent("click");
                logger.info("Ver filiais selecionadas acessado corretamente.");
            }
        }
        Thread.sleep(5000);

    }

    public void fecharModalInicial() throws Exception {
            if(elementExist(page,btnCancelar))
            {
                Thread.sleep(2000);
                btnCancelar.dispatchEvent("click");
            }


            if (btnToolTip.isVisible())
                btnToolTip.click();
    }
}
