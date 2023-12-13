package br.com.gerdau.reportUtils.interfaces;

import com.itextpdf.text.BaseColor;

import java.nio.file.Path;

public interface Report {

	public void setCover(String titulo, String objetivo);

	public void addStep(String description, byte[] imagem);

	public void addStep(byte[] imagem);

	public void addStep(String description, String fontName, float size, int style, BaseColor color, byte[] imagem);

	public void addText(String description, String fontName, float size, int style, BaseColor color);

	public void addText(String description);

	public void save(boolean isPassed, String[] versions);

	public void save(boolean isPassed);

	public void automationType(String[] version);

	public Path getReportPath();

}
