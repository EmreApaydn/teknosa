package com.teknosa.driver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverManager {
    public static WebDriver webDriver;
    protected static WebDriverWait webDriverWait;

    @BeforeAll
    public static void start(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(2));

        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        webDriverWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        //  webDriver.manage().window().maximize();

        webDriver.navigate().to("https://www.teknosa.com");

    }
    @AfterAll
    public static void end(){
        if(webDriver!= null){
            webDriver.close();
        }
    }

    public JavascriptExecutor getJSExecutor() {     //javascript kodlarını çalıştırmak için kullanılır.
        return (JavascriptExecutor) webDriver;
    }


}
