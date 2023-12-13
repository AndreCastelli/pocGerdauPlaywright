package br.com.gerdau.utils;

import br.com.atomic.framework.helpers.LoggerHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.atomic.framework.proton.ProtonHelper;
import com.microsoft.playwright.Locator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueUtils 
{
	static LoggerHelper logger = new LoggerHelper(ValueUtils.class);
	public static String getValue(String protonKey, String value)
	{
		if(ProtonHelper.isProtonExecution())
			return ProtonHelper.getProtonParameter(protonKey);
		else
			return value;
	}
	
	  public static String formatJSON(String jsonString) 
	  {
	      JsonParser parser = new JsonParser();
	      JsonObject json = parser.parse(jsonString).getAsJsonObject();

	      Gson gson = new GsonBuilder().setPrettyPrinting().create();
	      String prettyJson = gson.toJson(json);

	      return prettyJson;
	  }

	  public static void printListString(List<String> list){
			for (String value : list)
				logger.info(value);
	  }
	  public static void printListLocator(List<Locator> list){
			for (Locator value : list)
				logger.info(value.innerText());
	  }

	  public static String getSomenteNumeros(String valor){
		  String regex = "/(\\d+)$";
		  String valorSomenteNumero = "";

		  Pattern pattern = Pattern.compile(regex);
		  Matcher matcher = pattern.matcher(valor);

		  if (matcher.find()) {
			  valorSomenteNumero = matcher.group(1);
		  }
		  return valorSomenteNumero;
	  }

	public static String obterDataFormatada() {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss-SSSS");
		Date data = new Date();
		return formato.format(data);
	}

	public static String formatDate(String format, Date date) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
}