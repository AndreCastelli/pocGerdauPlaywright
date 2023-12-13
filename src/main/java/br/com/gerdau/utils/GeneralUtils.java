package br.com.gerdau.utils;

import com.google.common.base.Strings;

public class GeneralUtils {

    public static boolean isCIExecution(){
        if (!Strings.isNullOrEmpty(System.getProperty("CI")))
            return true;
        else
            return false;
    }
}
