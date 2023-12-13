package br.com.gerdau.validations;

import java.io.IOException;

import br.com.gerdau.bean.CamposValidacao;

public class ValidationsHelper 
{
	protected boolean isCvInitialized = false;
	protected CamposValidacao cv;

	protected static ValidationsHelper instance;

	public static ValidationsHelper getInstance() {
		if(instance == null) {
			instance = new ValidationsHelper();
		}

		return instance;
	}

	public static void initializeCv_() throws InterruptedException, IOException{
		getInstance().initializeCv();
	}

	public void initializeCv() throws InterruptedException{

		Thread.sleep(1000);

		cv = new CamposValidacao();

		isCvInitialized = true;
	}

	//Campos Validação --------------------------------------------------------
	public static CamposValidacao getCv_() {
		return getInstance().getCv();
	}

	public CamposValidacao getCv() {
		return cv;
	}
	//Campos Validação --------------------------------------------------------

	public static boolean isCvInitialized() {
		return getInstance().isCvInitialized;
	}

	public static void clearValidationsHelper() {
		instance = null;
	}

}
