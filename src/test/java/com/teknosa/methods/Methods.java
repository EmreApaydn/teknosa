package com.teknosa.methods;

import com.teknosa.driver.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Methods extends DriverManager {
    public final static Logger logger = Logger.getLogger(Methods.class);
    private static final WebDriver driver = DriverManager.webDriver;
    private static final WebDriverWait webDriverWait = DriverManager.webDriverWait;
    public static JavascriptExecutor jsExecutor;
    public Methods(){
        jsExecutor = getJSExecutor();
    }

    public WebElement findElement(By by) {
        logger.info(by + " Element founded.");
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> findElements(By by) {
        return webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void click(By by) {
        findElement(by).click();
        logger.info("Clicked on this element " + by + " .");
    }

    public void sendKeys(By by,String text) {
        findElement(by).sendKeys(text);
        logger.info(text + " has been written to this element " + by + " .");
    }

    public void waitSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void urlGit(String url){
        webDriver.navigate().to(url);
    }

    public boolean isElementVisible(By by)
    {
        try {
            webDriver.findElement(by);
            return true;
        }catch(Exception e ){
            return false;
        }
    }

    public void clickWithJS(WebElement element){
        jsExecutor.executeScript("arguments[0].click();",element);
    }
    public void moveToElementWithJS(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        System.out.println("aşağıya kaydırıldı");
    }
    public void clickWithFluentWait(By locator) {

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        jsExecutor.executeScript("arguments[0].style.border='3px solid yellow'", element);
        element.click();

        logger.info("Clicked on the element " + locator + ".");
    }



}
