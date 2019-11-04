package com.clairvoyant.base;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import static com.clairvoyant.base.Constants.*;


public class BaseClass {
    public static  WebDriver driver;
    public static Properties envProp;
    private static final Logger log = LoggerFactory.getLogger(BaseClass.class);

    public static Properties fileRead(String path) {
        File file = new File(path);
        Properties properties = null;
        try (FileInputStream fis = new FileInputStream(file)) {
            properties = new Properties();
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
    public void setWebDriver(WebDriver driver){
        this.driver = driver;
    }

    public static WebDriver getDriver(){
        return driver;
    }


    @BeforeSuite
    public void createOutPutFolder(){
        File file = new File(OUTPUT_DIR);
        boolean mkDir = file.mkdirs();

    }

    @BeforeTest
    @Parameters("browser")
    public void browserSetup(String browser) {
        envProp = fileRead(PROPERTY_FILES);
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty(WEBDRIVER_CHROME_DRIVER, DRIVER_PATH + "chromedriver_new.exe");
            driver = new ChromeDriver();

        }

        setWebDriver(driver);
        getDriver().manage().window().maximize();
        //getDriver().manage().timeouts().pageLoadTimeout(360, TimeUnit.SECONDS);

        getDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT_SECS, TimeUnit.SECONDS);
    }

   @AfterTest
    public void closeBrowser() {
        getDriver().close();
        getDriver().quit();
    }

    /*
    skipping the test
     */
    public static void executionCheck(String execution){
        if (execution.equalsIgnoreCase("N")){
            Reporter.log("# Skipping the test",true);
            throw new SkipException("Skipping the test");
        }
    }






}
