package com.clairvoyant.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.clairvoyant.GenericUtils.WebElementUtils.*;

public class Searchpage {
    private WebDriver driver;
    WebDriverWait wait;

    public Searchpage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(this.driver,60);

    }

    @FindBy(how=How.ID,using = "pa_color")
    private WebElement colorDropDown;

    @FindBy(how=How.ID,using = "pa_size")
    private WebElement sizeDropDown;

    @FindBy(how=How.XPATH,using = "//button[@class='single_add_to_cart_button button alt']")
    private  WebElement addToCartButton;

    @FindBy(how=How.XPATH,using="//div[@class='woocommerce-message']/a")
    private WebElement labelCartDetails;

    public void selectColorValue(String colorText){
        selectByText(colorDropDown,colorText);

    }
    public void selectSizeValue(String sizeText){
        selectByText(sizeDropDown,sizeText);

    }

    public void clickAddToCart(){
        addToCartButton.submit();
    }
    public String getTextCart(){
        return getText(labelCartDetails);

    }


}
