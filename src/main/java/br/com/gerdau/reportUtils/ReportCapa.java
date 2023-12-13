package br.com.gerdau.reportUtils;

import br.com.gerdau.reportUtils.exception.ReportException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportCapa {

    private Document document;
    private ColumnText descText;
    private Font tituloFormatacao = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, BaseColor.BLACK);
    private Font subtituloFormatacao = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.GRAY);
    private PdfWriter writer;

    public ColumnText getDescText() {
        return descText;
    }

    public ReportCapa(Document document, float margin, String path) {
        this.document = document;
        document.open();
        document.setMargins(margin, margin, margin, margin);
        try {
            if (path.length() >= 255) {
                path = path.substring(0, 254);
            }
            this.writer = PdfWriter.getInstance(document, new FileOutputStream(path));
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    public void setCapa(String testName, String objective) {
        Paragraph titulo = new Paragraph(testName, tituloFormatacao);
        document.open();
        if (!document.isOpen() == true) {
            Document document = new Document();
            this.document = document;
            document.open();
        }
        ReportLogo imgLogo = new ReportLogo("src/test/resources/imagensreport/logo.png", 455f, 780f, 140f, 50f);
        try {
            document.add(imgLogo.getImage());
        } catch (DocumentException e) {
            throw new ReportException(e);
        }
        ColumnText title = addTextArea(30, 400, 560, 720);
        title.addText(titulo);
        title.setLeading(23);
        try {
            title.go();
        } catch (DocumentException e) {
            throw new ReportException(e);
        }
        ColumnText obj = addTextArea(30, 360, 560, 600);
        titulo = new Paragraph(70, objective, subtituloFormatacao);
        obj.addText(new Paragraph(titulo));
        try {
            obj.go();
        } catch (DocumentException e) {
            throw new ReportException(e);
        }
        // Set status
        String info = "Data: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + "\nHora da execução: "
                + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "\r\nUsuário logado: "
                + System.getProperty("user.name");
        Paragraph p = new Paragraph(70, info);
        PdfTemplate status = writer.getDirectContent().createTemplate(800, 800);
        writer.getDirectContent().addTemplate(status, 0, 30);
        descText = addTextArea(35, 360, 560, 600, status);
        descText.addText(p);
        try {
            descText.go();
        } catch (DocumentException e) {
            throw new ReportException(e);
        }
    }

    private ColumnText addTextArea(int startX, int endX, int startY, int endY, PdfContentByte contentByte) {
        ColumnText ct = new ColumnText(contentByte);
        ct.setSimpleColumn(startX, endX, startY, endY);
        return ct;
    }

    private ColumnText addTextArea(int startX, int endX, int startY, int endY) {
        return addTextArea(startX, endX, startY, endY, writer.getDirectContent());
    }

    public void setStatus(Boolean isPassed) {
        if (isPassed) {
            Font f = new Font();
            f.setColor(BaseColor.GREEN);
            descText.addText(new Phrase("Status: Passed \n", f));
        } else {
            Font f = new Font();
            f.setColor(BaseColor.RED);
            descText.addText(new Phrase("Status: Failed \n", f));
        }
        try {
            descText.go();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
