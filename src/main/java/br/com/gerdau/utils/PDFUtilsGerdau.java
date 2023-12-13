package br.com.gerdau.utils;

import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.atomic.framework.helpers.PDFReaderHelper;
import com.google.common.base.Strings;
import org.apache.pdfbox.pdfparser.PDFParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class PDFUtilsGerdau
{

    static LoggerHelper logger = new LoggerHelper(PDFUtilsGerdau.class);
    public static boolean validarArquivoPDF(String path) throws IOException {

        try
        {
            File pdfFile = new File(path);
            logger.info("Tamanho do arquivo em kilobytes: "+(pdfFile.length()/1024));
            HashMap<String, String> pdfInfos = PDFReaderHelper.getPDFInfos(path);

            logger.info("Conteúdo: "+pdfInfos.get("content"));
            logger.info("Quantidade de páginas: "+pdfInfos.get("pagesQty"));

            if(Strings.isNullOrEmpty(pdfInfos.get("content")))
                return false;
            else
                return true;

        }
        catch (Exception e){
            logger.info("Arquivo corrompido");
            return false;
        }


//        return false;
    }
}
