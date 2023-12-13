package br.com.gerdau.engine;

import java.io.IOException;

public class CucumberHelper
{
    protected boolean isCucumberControllerInitialized = false;
    protected CucumberController cucumberController;

    protected static CucumberHelper instance;

    public static CucumberHelper getInstance() {
        if(instance == null) {
            instance = new CucumberHelper();
        }

        return instance;
    }

    public static void initializeCucumberController_() throws InterruptedException, IOException {
        getInstance().initializeCucumberController();
    }

    public void initializeCucumberController() throws InterruptedException{

        Thread.sleep(1000);

        cucumberController = new CucumberController();

        isCucumberControllerInitialized = true;
    }

    public static boolean isCucumberControllerInitialized() {
        return getInstance().isCucumberControllerInitialized;
    }

    public static void clearCucumberHelper() {
        instance = null;
    }

    //Cucumber Controller --------------------------------------------------------
    public static CucumberController getCucumberController_() {
        return getInstance().getCucumberController();
    }

    public CucumberController getCucumberController() {
        return cucumberController;
    }
    //Cucumber Controller --------------------------------------------------------
}
