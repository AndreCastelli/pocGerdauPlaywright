package br.com.gerdau.reportUtils;

import br.com.atomic.framework.helpers.PropertyHelper;
import br.com.gerdau.reportUtils.exception.ReportException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static br.com.gerdau.engine.CucumberHelper.getCucumberController_;

public class ReportManager {

	private static HashMap<String, List<Path>> pathMap;
	private static HashMap<String, List<String>> reportMap;
	private static Path pathToSave;

	static {
		pathMap = new HashMap<String, List<Path>>();
		reportMap = new HashMap<String, List<String>>();
		pathToSave = Paths.get(PropertyHelper.getProperty("report.evidences") + File.separator +"Execucao_" + getCucumberController_().getCurrentExecutionDate() + File.separator);
		try {
			Files.createDirectories(pathToSave);
		} catch (IOException e) {
			throw new ReportException(e);
		}
	}

	private ReportManager() {
	}

	public static void addEvidence(Path path) throws IOException {
		addEvidence(path, false);
	}

	public static void addEvidence(Path path, boolean copyFile) throws IOException {
		String testName = getTestName();
		Path fullPath = Paths.get(pathToSave + "/" + path.getFileName());
		if (!pathMap.containsKey(testName)) {
			pathMap.put(testName, new ArrayList<Path>());
		}
		try {
			if (!Files.exists(fullPath)) {
				Files.createDirectories(fullPath.getParent());
			}
			if (copyFile) {
				Files.copy(path, fullPath, StandardCopyOption.REPLACE_EXISTING);
			} else {
				Files.move(path, fullPath, StandardCopyOption.REPLACE_EXISTING);
			}
			pathMap.get(testName).add(fullPath);
		} catch (IOException e) {
			throw new ReportException("Falha ao mover o arquivo de " + path.toString() + " para " + fullPath
					+ " -----\n" + e.getMessage());
		}
	}

	public static Path getFullPath() {
		return pathToSave;
	}

	public static List<Path> getEvidences() {
		String testName = getTestName();
		return pathMap.get(testName);
	}

	public static List<Path> getEvidencesZip() {
		String testName = getTestName();
		return pathMap.get(testName + ".zip");
	}

	public static List<Path> getEvidences(String testName) {
		return pathMap.get(testName);
	}

	public static String getFolder() {
		return pathToSave.getFileSystem().toString();
	}

	private static String getTestName() {
		return Thread.currentThread().getName();
	}

	public static void addTextReport(String text) {
		String testName = getTestName();
		if (!reportMap.containsKey(testName)) {
			reportMap.put(testName, new ArrayList<String>());
		}
		reportMap.get(testName).add(text);
	}

	public static List<String> getTextReport(String testName) {
		List<String> listTextReport = new ArrayList<String>();
		try {
			if (reportMap.containsKey(testName)) {
				int i = 0;
				for (String text : reportMap.get(testName)) {
					listTextReport.add((i == 0) ? text : "\n" + text);
					i++;
				}
			}
			reportMap.get(testName).clear();
			return listTextReport;
		} catch (Exception e) {
			return listTextReport;
		}
	}

	public static void clearTextReport(String testName) {
		if (reportMap.containsKey(testName)) {
			reportMap.get(testName).clear();
		}
	}

	public static String formatDate(String format, Date date) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

}
