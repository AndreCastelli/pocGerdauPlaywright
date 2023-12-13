package br.com.gerdau.utils;

import com.microsoft.playwright.Page;

public class PlaywrightUtils_Gerdau
{
    public static void pressionarEnter(Page page){
        page.keyboard().press("Enter");
    }
}
