package com.teknosa.login;

import com.teknosa.driver.DriverManager;
import com.teknosa.elements.Elements;
import com.teknosa.methods.Methods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login extends DriverManager {
    Methods methods = new Methods();

    @Test
    public void Test1(){
        Assertions.assertEquals("https://www.teknosa.com/", webDriver.getCurrentUrl());
        methods.clickWithFluentWait(Elements.teknosa);

        SearchContext searchContext = webDriver.findElement(By.xpath("//efilli-layout-dynamic")).getShadowRoot();
        WebElement shadowElement = searchContext.findElement(By.cssSelector("div[data-name='Accept Button']"));
        shadowElement.click();

        methods.waitSeconds(5);
    }
}
