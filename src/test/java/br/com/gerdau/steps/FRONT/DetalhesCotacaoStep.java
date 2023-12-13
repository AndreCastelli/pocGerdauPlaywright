package br.com.gerdau.steps.front;

import br.com.atomic.framework.controllers.PlaywrightController;
import br.com.atomic.framework.helpers.AssertHelper;
import br.com.atomic.framework.utils.FileUtilsAtomic;
import br.com.gerdau.pages.gerdaumais.DetalhesCotacaoPage;
import br.com.gerdau.utils.PDFUtilsGerdau;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static br.com.gerdau.validations.ValidationsHelper.getCv_;

public class DetalhesCotacaoStep {

    @Quando("extraio o PDF com todas as plantas apresentadas na cotação")
    public void extraioOPDFComTodasAsPlantasApresentadasNaCotação() throws Exception {
        DetalhesCotacaoPage detalhesCotacaoPage = new DetalhesCotacaoPage(PlaywrightController.getPage());
        detalhesCotacaoPage.gerarPdfCotacao();
    }

    @Então("o download do PDF da cotação é realizado")
    public void oDownloadDoPDFDaCotaçãoÉRealizado() throws Exception {
        AssertHelper.assertTrue("O arquivo no caminho "+getCv_().getDownloadFilePath()+" deve existir", FileUtilsAtomic.isFileExist(getCv_().getDownloadFilePath()));
    }

    @E("o arquivo PDF possui conteúdo válido")
    public void oArquivoPDFPossuiConteúdoVálido() throws Exception {
        AssertHelper.assertTrue("O conteúdo do arquivo "+getCv_().getDownloadFilePath()+" deve ser válido", PDFUtilsGerdau.validarArquivoPDF(getCv_().getDownloadFilePath()));
    }
}
