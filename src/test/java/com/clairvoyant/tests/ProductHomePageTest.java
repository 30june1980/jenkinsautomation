package com.clairvoyant.tests;

import com.clairvoyant.GenericUtils.WebElementUtils;
import com.clairvoyant.base.BaseClass;
import com.clairvoyant.helper.ProductHomePageHelper;

import org.testng.annotations.Test;


public class ProductHomePageTest extends BaseClass {
    ProductHomePageHelper objProductHomePageHelper = new ProductHomePageHelper();
    @Test(description = "Validate home page")
    public void validateHomePageLoad() {
        WebElementUtils.loadPage(envProp.getProperty("weburl"));
        objProductHomePageHelper.validateHomePageLoad();
        objProductHomePageHelper.addToCart();
    }


}
