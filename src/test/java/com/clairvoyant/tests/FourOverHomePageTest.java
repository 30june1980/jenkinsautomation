package com.clairvoyant.tests;

import com.clairvoyant.DataProvider.DataProviderClass;
import com.clairvoyant.GenericUtils.WebElementUtils;
import com.clairvoyant.PageFactory.FourOverHomePage;
import com.clairvoyant.base.BaseClass;
import com.clairvoyant.helper.FourOverHomePageHelper;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FourOverHomePageTest extends BaseClass {

    FourOverHomePageHelper objFourOverHomePageHelper = new FourOverHomePageHelper();

    @Test
    public void validateLogin() {
        Reporter.log("# Login to the application ", true);
        boolean isNameDisplayed = objFourOverHomePageHelper.validate4OverLoginPage();
        Assert.assertTrue(isNameDisplayed, "Name do not match");
        Assert.assertTrue(objFourOverHomePageHelper.isLogOutButtonDisplayed(), "LogOut button not displayed");
        Reporter.log("# LogOut button displayed", true);
    }


    @Test(dataProvider = "testDataProvider", dataProviderClass = DataProviderClass.class)
    public void validateMajesticProd(Object tcname, Object exec, Object majesticproduct) {
        executionCheck(exec.toString());
        objFourOverHomePageHelper.validate4OverLoginPage();
        Assert.assertTrue(objFourOverHomePageHelper.validateMajesticProdText(majesticproduct.toString()), "Menu not present");
    }

    @Test(dataProvider = "testDataProvider", dataProviderClass = DataProviderClass.class)
    public void validatePrintSpec(Object tcname, Object exec, Object majesticproduct, Object producttype, Object majestictype, Object productcategory,
                                  Object size, Object stock, Object coating,Object Colorspec, Object Runsize,Object	BlankEnvelopes,
                                  Object TurnaroundTime    ) {
        executionCheck(exec.toString());
        objFourOverHomePageHelper.validate4OverLoginPage();
        //int occurance = (int) Double.parseDouble(Occurance.toString());
        objFourOverHomePageHelper.navigateToProduct(majesticproduct.toString(), producttype.toString(), majestictype.toString(), productcategory.toString(),
                size.toString(),stock.toString(),coating.toString(),Colorspec.toString(), String.valueOf((int) Double.parseDouble(Runsize.toString())),
                        BlankEnvelopes.toString(),TurnaroundTime.toString());
        Assert.assertTrue(objFourOverHomePageHelper.validatePrintSpecPriceDisplayed());
        //Assert.assertTrue(objFourOverHomePageHelper.validateShippingDetailsPriceDisplayed());
    }


}
