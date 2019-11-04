package com.clairvoyant.PageFactory;

import com.clairvoyant.GenericUtils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.math.BigDecimal;

import static com.clairvoyant.GenericUtils.WebElementUtils.*;
import static com.clairvoyant.base.BaseClass.envProp;
import static com.clairvoyant.base.BaseClass.getDriver;

public class FourOverHomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public FourOverHomePage(WebDriver drvier) {
        this.driver = drvier;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, 60);
    }


    @FindBy(how = How.XPATH, using = "//dd[@class='pricing-available']")
    public WebElement totalValue;

    @FindBy(how = How.ID, using = "logout")
    private WebElement btnlogout;

    @FindBy(how = How.ID, using = "txtEmail")
    private WebElement username;

    @FindBy(how = How.ID, using = "txtPassword")
    private WebElement password;

    @FindBy(how = How.ID, using = "btnLogin")
    private WebElement btnLogin;

    String nameText = envProp.getProperty("nameValidate");
    By loginName = By.xpath("//*[text()[contains(.,'" + nameText + "')]]");
    By prodNamexPath;


    @FindBy(how = How.XPATH, using = "//div[@class='builder-pricing-status']//div[@class = 'item-price']")
    public WebElement printspecificationpPrice;

    @FindBy(how = How.XPATH, using = "//label[@class='shipment-panel-price-region']//div[@class = 'item-price']")
    public WebElement shippingDetailsPrice;

    public boolean isPriceDispalyed(WebElement element) {
        return isElementDisplayed(element);
    }

    public boolean checkValueGreaterThanZero(WebElement element) {
        boolean blnValueGrtThnZero = false;
        String getPriceText = getText(element);
        Reporter.log("# Price displayed :- " + getPriceText, true);
        String strPrice = getPriceText.substring(getPriceText.lastIndexOf("$") + 1);
        highlighterMethod(driver, element);
        if (Utilities.getDecimalValue(strPrice).compareTo(BigDecimal.valueOf(0))==1) { //first value is greater
            blnValueGrtThnZero = true;
        }

        System.out.println("totalValue " + totalValue.getText());
        return blnValueGrtThnZero;

    }



    @FindBy (how = How.XPATH, using = "//select[@name='Size']")
    public WebElement dropdownsize;

    @FindBy (how = How.XPATH, using = "//select[@name='Coating']")
    public WebElement dropdownCoating;

    @FindBy (how = How.XPATH, using = "//select[@name='Colorspec']")
    public WebElement dropdownColorSpec;

    @FindBy (how = How.XPATH, using = "//select[@name='Runsize']")
    public WebElement dropdownRunSize;
    @FindBy (how = How.XPATH, using = "//select[@name='Blank Envelopes']")
    public WebElement dropdownBlankEnv;
    @FindBy (how = How.XPATH, using = "//select[@name='Turnaround Time']")
    public WebElement dropdownTurnArndTime;


  public void selectValueInDropDown(WebElement element, String value){
      try {
          Thread.sleep(3000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      wait.until(ExpectedConditions.visibilityOf(element));
      selectByText(element, value);
  }

    private By getDynXpathHeaderLink(String value) {
        return By.xpath("//h1[@class='page-title'][text()='" + value + "']");
    }

    public By getDynXPathmenuNamevar(String value) {
        prodNamexPath = By.xpath("//a[@class='product active' and text()='" + value + "']");
        return prodNamexPath;
    }


    //By selectPrintSpecDropDown;
    public String getDynxPathSelect(String value) {
        /*selectPrintSpecDropDown = By.xpath("//select[@name='"+value+"']");
        return selectPrintSpecDropDown;*/
        return "//select[@name='" + value + "']";
    }

    public void selectProductDropDown(String dropdownName, String value, WebDriver driver) {
        WebElement element = driver.findElement(By.xpath(getDynxPathSelect(dropdownName)));
        wait.until(ExpectedConditions.visibilityOf(element));
        selectByText(element, value);
    }



    public boolean isMenuPresent(String menuName) {
        prodNamexPath = getDynXPathmenuNamevar(menuName);
        if (driver.findElement(prodNamexPath).isDisplayed())
            Reporter.log("# found menu :- " + menuName, true);
        return driver.findElement(prodNamexPath).isDisplayed();
    }

    public boolean isMenuHeadingDisplayed(String menuName) {
        By menuHeading;
        menuHeading = getDynXpathHeaderLink(menuName);
        if (driver.findElement(menuHeading).isDisplayed())
            Reporter.log("# found menu heading :- " + menuName, true);
        highlighterMethod(driver, driver.findElement(menuHeading));
        return driver.findElement(menuHeading).isDisplayed();
    }

    public void enterUsername(String uname) {
        wait.until(ExpectedConditions.visibilityOf(username));
        username.sendKeys(uname);
    }

    public boolean getLoginName() {
        return driver.findElement(loginName).isDisplayed();
    }

    public void enterPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public void clickLogin() {
        btnLogin.click();
    }

    public boolean isLogOutButtonPresent() {
        return btnlogout.isDisplayed();
    }

    public void clickOnMajestProdLink(String value) {
        prodNamexPath = getDynXPathmenuNamevar(value);
        driver.findElement(prodNamexPath).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(prodNamexPath).click();

    }


}
