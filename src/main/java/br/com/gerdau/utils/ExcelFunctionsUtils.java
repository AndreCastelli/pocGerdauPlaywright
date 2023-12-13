package br.com.gerdau.utils;

import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.atomic.framework.utils.DateUtils;
import br.com.gerdau.validations.ValidationsHelper;

import java.io.File;
import java.time.LocalDateTime;

import static br.com.atomic.framework.helpers.ExcelHelper.startExcel;
import static br.com.atomic.framework.utils.FileUtilsAtomic.deleteFile;

public class ExcelFunctionsUtils
{
    static LoggerHelper logger = new LoggerHelper(ExcelFunctionsUtils.class);

    public static void validarDownload() throws Exception {

        boolean encontraArquivo = false;

        LocalDateTime dataAtual = DateUtils.getCurrentTime("");
        String dataCompleta = dataAtual.toString().split("T")[0];

        dataCompleta = dataCompleta.replaceAll("-", "");

        String nomeArquivo = "Cargas_" + dataCompleta;

        File[] listFiles = new File("C:/Proton/ProtonClient/Attachments").listFiles();

        for (int i = 0; i < listFiles.length; i++) {

            if (listFiles[i].isFile()) {
                String fileName = listFiles[i].getName();
                if (fileName.startsWith(nomeArquivo)
                        && fileName.endsWith(".xlsx")) {
                    logger.info("Download realizado com sucesso");

                    deleteFile("C:/Proton/ProtonClient/Attachments" + "/" + fileName);

                    encontraArquivo = true;
                    break;
                }
            }
        }
        if (encontraArquivo == false) {
            logger.error("Download não realizado");
            throw new Exception("Download não realizado");
        }
    }

    //Carga e Entrega
    public static void inicializaArquivoExcel() throws Exception {

        File[] listFiles = new File("C:/Proton/ProtonClient/Attachments").listFiles();
        String nomeDoArquivo = ValidationsHelper.getCv_().getDownloadFilePath();

        for (int i = 0; i < listFiles.length; i++) {

            if (listFiles[i].isFile()) {
                String fileName = listFiles[i].getName();
                if (fileName.equals(nomeDoArquivo)) {
                    logger.info("Arquivo encontrado!");

                    startExcel("C:/Proton/ProtonClient/Attachments" + "/" + nomeDoArquivo);
                    logger.info("inicializo o excel");
                }
            }
        }

    }
}
