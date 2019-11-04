package com.clairvoyant.helper;

import com.clairvoyant.GenericUtils.WebElementUtils;
import com.clairvoyant.PageFactory.FourOverHomePage;
import com.clairvoyant.base.BaseClass;
import org.openqa.selenium.WebDriver;

import static com.clairvoyant.GenericUtils.WebElementUtils.highlighterMethod;
import static com.clairvoyant.GenericUtils.WebElementUtils.scrollToView;

public class FourOverHomePageHelper extends BaseClass {
    FourOverHomePage objFourOverHomePage;


    public void navigateToProduct(String majesticproduct,String producttype,String majestictype,String productcategory, String size,
                                  String stock, String coating,String colorspec, String runsize, String blankenvelopes, String turnaroundtime){

        objFourOverHomePage = new FourOverHomePage(getDriver());
        objFourOverHomePage.clickOnMajestProdLink(majesticproduct);
        objFourOverHomePage.selectProductDropDown(envProp.getProperty("ProductType"),producttype,driver);
        objFourOverHomePage.selectProductDropDown( envProp.getProperty("MajesticType"),majestictype,driver);
        objFourOverHomePage.selectProductDropDown( envProp.getProperty("ProductCategory"),productcategory,driver);
        objFourOverHomePage.selectValueInDropDown(objFourOverHomePage.dropdownsize,size.trim());
        objFourOverHomePage.selectProductDropDown( envProp.getProperty("Stock"),stock.trim(),driver);
        objFourOverHomePage.selectValueInDropDown(objFourOverHomePage.dropdownCoating,coating.trim());
        objFourOverHomePage.selectValueInDropDown(objFourOverHomePage.dropdownColorSpec,colorspec.trim());

        //scrollToView(driver,objFourOverHomePage.dropdownRunSize);
        objFourOverHomePage.selectValueInDropDown(objFourOverHomePage.dropdownRunSize,runsize.trim());
        objFourOverHomePage.selectValueInDropDown(objFourOverHomePage.dropdownBlankEnv,blankenvelopes.trim());
        objFourOverHomePage.selectValueInDropDown(objFourOverHomePage.dropdownTurnArndTime,turnaroundtime.trim());


    }

    public boolean validatePrintSpecPriceDisplayed(){
        boolean blnIsValueGreatThnZero=false;
        objFourOverHomePage = new FourOverHomePage(getDriver());
        if (objFourOverHomePage.isPriceDispalyed(objFourOverHomePage.printspecificationpPrice)){
            blnIsValueGreatThnZero=  objFourOverHomePage.checkValueGreaterThanZero(objFourOverHomePage.printspecificationpPrice);
        }
        return blnIsValueGreatThnZero;

    }

    public boolean validateShippingDetailsPriceDisplayed(){
        boolean blnIsValueGreatThnZero=false;
        objFourOverHomePage = new FourOverHomePage(getDriver());
        if (objFourOverHomePage.isPriceDispalyed(objFourOverHomePage.shippingDetailsPrice)){
            blnIsValueGreatThnZero=  objFourOverHomePage.checkValueGreaterThanZero(objFourOverHomePage.shippingDetailsPrice);
        }
        return blnIsValueGreatThnZero;

    }

    public boolean validate4OverLoginPage() {
        objFourOverHomePage = new FourOverHomePage(getDriver());
        WebElementUtils.loadPage(envProp.getProperty("4Overurl"));
        objFourOverHomePage.enterUsername(envProp.getProperty("username"));
        objFourOverHomePage.enterPassword(envProp.getProperty("password"));
        objFourOverHomePage.clickLogin();
        return objFourOverHomePage.getLoginName();

    }

    public boolean isLogOutButtonDisplayed() {
        objFourOverHomePage = new FourOverHomePage(getDriver());
        return objFourOverHomePage.isLogOutButtonPresent();
    }

    public boolean validateMajesticProdText(String prodName) {
        objFourOverHomePage = new FourOverHomePage(getDriver());
        objFourOverHomePage.clickOnMajestProdLink(prodName);
        objFourOverHomePage.isMenuHeadingDisplayed(prodName);
        return objFourOverHomePage.isMenuPresent(prodName);

    }




}
