package com.rpachallenge;

import org.openqa.selenium.By;

public final class Locators {
    public static final By 
        start = By.xpath("/html/body/app-root/div[2]/app-rpa1/div/div[1]/div[6]/button"),
        submit = By.xpath("/html/body/app-root/div[2]/app-rpa1/div/div[2]/form/input"),
        inputs = By.xpath("//*[@class=\"row\"]//input"),
        status = By.xpath("/html/body/app-root/div[2]/app-rpa1/div/div[2]/div[1]"),
        mensagem = By.xpath("/html/body/app-root/div[2]/app-rpa1/div/div[2]/div[2]");
}
