package br.com.gerdau.engine;

import br.com.atomic.framework.helpers.LoggerHelper;
import br.com.atomic.framework.helpers.PropertyHelper;

public class EnvHelper {

    static LoggerHelper logger = new LoggerHelper(EnvHelper.class);
    public static void setPRD(){
        PropertyHelper.setProperty("env.target", "prd");
    }

    public static boolean isPRD(){
        if(PropertyHelper.getProperty("env.target").toLowerCase().equals("prd"))
            return true;
        else
            return false;
    }


    public static void setQA(){
        PropertyHelper.setProperty("env.target", "qa");
    }

    public static boolean isQA(){
        if(PropertyHelper.getProperty("env.target").toLowerCase().equals("qa"))
            return true;
        else
            return false;
    }

    public static String getGerdauMaisURL(){
        if(isPRD()){
            logger.info("[AMBIENTE - PRD] URL: "+PropertyHelper.getProperty("url.gerdaumais.prd"));
            return PropertyHelper.getProperty("url.gerdaumais.prd");
        }
        else{
            logger.info("[AMBIENTE - QA] URL: "+PropertyHelper.getProperty("url.gerdaumais.qa"));
            return PropertyHelper.getProperty("url.gerdaumais.qa");
        }
    }

}
