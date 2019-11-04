package com.clairvoyant.GenericUtils;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.clairvoyant.base.BaseClass.getDriver;

public class WebElementUtils {


    public static boolean isElementDisplayed(WebElement webElement){
        return webElement.isDisplayed();
    }

    public static boolean isElementEnabled( WebElement webElement){
        return webElement.isEnabled();
    }

    public static  void loadPage(String url){
        Reporter.log("To load fresh page, deleting cookies and refreshing page...", true);
        getDriver().manage().deleteAllCookies();
        getDriver().get(url);
    }

    public static void selectByText(WebElement element, String text) {
        try {
            Select selectElement = new Select(element);
            //scrollToView(getDriver(),element);
            selectElement.selectByVisibleText(text);
            Reporter.log("# Value: " + text + " was selected successfully for element", true);
        } catch(StaleElementReferenceException e){
            Select selectElement = new Select(element);
            selectElement.selectByVisibleText(text);

        }

        catch (Exception e1) {
            e1.printStackTrace();
            //Reporter.log("# Value:" + text + " was NOT selected for element" + element, true);
        }
    }

    public static void pressEnter(){
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static String getText(WebElement element){
        return element.getText();
    }

    public static JavascriptExecutor getJavaExecutor(WebDriver driver){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return executor;
    }

    public static void highlighterMethod(WebDriver driver,WebElement element){
        getJavaExecutor(driver).executeScript("arguments[0].style.border='2px solid red'", element);
    }


    public static void scrollToView(WebDriver driver,WebElement element){
        getJavaExecutor(driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }




}
