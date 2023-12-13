package br.com.gerdau.utils;

import br.com.atomic.framework.helpers.PropertyHelper;
import br.com.gerdau.reportUtils.ReportPDF;
import br.com.gerdau.reportUtils.interfaces.Report;
import com.google.common.io.Files;
import com.microsoft.playwright.Page;

import java.io.File;
import java.io.IOException;

public class EvidenceManager {

    private static Report report;

    private static String caminho = PropertyHelper.getProperty("report.images");

    public static void newInstance() {
        createFolderEvidence();
        createFolderImages();
        report = new ReportPDF();
    }

    public static Report getReport() {
        return report;
    }

    private static byte[] takeStep(Page page, String string) throws IOException {
        byte[] src = page.screenshot();
        Files.write(src, new File(caminho + "/" + string + ".png"));
        return src;
    }

    public static void addStep(Page page, String descricao) throws IOException {
        report.addStep("\n" + descricao, takeStep(page, descricao));
    }

    public static void addStep(Page page, String descricao, boolean isFullScreen) throws IOException {
        report.addStep("\n" + descricao, takeStep(page, descricao));
    }

    public static void addText(String descricao) {
        report.addText(descricao);
    }

    public static void addTextStep(String step, String descricao) {
        report.addText("\n" + step);
        report.addText(descricao);
    }

    private static void createFolderEvidence() {
        File folderEvidence = new File(PropertyHelper.getProperty("report.evidences"));
        if (!folderEvidence.exists())
            folderEvidence.mkdirs();

    }

    private static void createFolderImages() {
        File folderImages = new File(PropertyHelper.getProperty("report.images"));
        if (!folderImages.exists())
            folderImages.mkdirs();
    }

}
