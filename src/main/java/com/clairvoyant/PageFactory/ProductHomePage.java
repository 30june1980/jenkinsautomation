package com.clairvoyant.PageFactory;

import com.clairvoyant.GenericUtils.WebElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.clairvoyant.GenericUtils.WebElementUtils.*;

public class ProductHomePage {
    private WebDriver driver;
    WebDriverWait wait;


    public ProductHomePage(){

    }
    public ProductHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, 60);

    }

    //@FindBy(how = How.XPATH,using = "//img[@alt='ToolsQA Demo Site']")
    @FindBy(how = How.CLASS_NAME, using = "noo-search")
    private WebElement searchButton;
    By homePageLabel = By.xpath("//img[@alt='ToolsQA Demo Site']");

    @FindBy(how =How.CLASS_NAME,using="form-control")
    private WebElement weTxtSearch;

    public void isLabelPresent() {
        System.out.println(WebElementUtils.isElementDisplayed(driver.findElement(homePageLabel)));
        driver.findElement(homePageLabel).click();
        searchButton.click();
    }


    public void searchOnpage(String value){
        weTxtSearch.sendKeys(value);
        pressEnter();

    }





}
