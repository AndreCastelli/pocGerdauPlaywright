package br.com.gerdau.reportUtils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import br.com.gerdau.reportUtils.exception.ReportException;

import java.io.IOException;

public class ContentPDF {
	private Document document;
	private Font dadosFormatacao = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
	private float margin;

	public ContentPDF(Document document, float margin) {
		this.document = document;
		this.margin = margin;
	}

	public void addStep(String description, byte[] imagem) {
		try {
			if (this.document.isOpen() == true) {
				ReportManager.clearTextReport(Thread.currentThread().getName());
				this.document.add(new Paragraph(description, dadosFormatacao));
			} else {
				StringBuilder report = new StringBuilder();
				for (String t : ReportManager.getTextReport(Thread.currentThread().getName())) {
					report.append(t);
				}
				report.append("\n" + description);
				document.add(new Paragraph(report.toString(), dadosFormatacao));
			}
			setImage(imagem);
		} catch (DocumentException e) {
			throw new ReportException(e);
		}
	}

	public void addStep(String description, String fontName, float size, int style, BaseColor color, byte[] imagem) {
		Font format;
		try {
			if (this.document.isOpen() == true) {
				ReportManager.clearTextReport(Thread.currentThread().getName());
				this.document.newPage();
				format = FontFactory.getFont(fontName, size, style, color);
				this.document.add(new Paragraph(description, format));
			} else {
				StringBuilder report = new StringBuilder();
				for (String t : ReportManager.getTextReport(Thread.currentThread().getName())) {
					report.append(t);
				}
				report.append("\n" + description);
				document.add(new Paragraph(report.toString(), dadosFormatacao));
			}
			setImage(imagem);
		} catch (DocumentException e) {
			throw new ReportException(e);
		}
	}

	public void addStep(byte[] imagem) {
		try {
			if (document.isOpen()) {
				ReportManager.clearTextReport(Thread.currentThread().getName());
				document.newPage();
			} else {
				StringBuilder report = new StringBuilder();
				for (String t : ReportManager.getTextReport(Thread.currentThread().getName())) {
					report.append(t);
				}
				document.add(new Paragraph(report.toString(), dadosFormatacao));
			}
			setImage(imagem);
		} catch (DocumentException e) {
			throw new ReportException(e);
		}
	}

	public void addText(String description, String fontName, float size, int style, BaseColor color) {
		Font format;
		try {
			ReportManager.clearTextReport(Thread.currentThread().getName());
			format = FontFactory.getFont(fontName, size, style, color);
			document.add(new Paragraph(description, format));
		} catch (DocumentException e) {
			throw new ReportException(e);
		}
	}

	private void setImage(byte[] pictureData) {
		try {
			Image image = Image.getInstance(pictureData);
			image.scaleToFit(document.getPageSize().getWidth() - (margin * 2), document.getPageSize().getHeight());
			document.add(image);
			document.newPage();
		} catch (BadElementException | IOException e) {
			throw new ReportException(e);
		} catch (DocumentException e) {
			throw new ReportException(e);
		}
	}

}
