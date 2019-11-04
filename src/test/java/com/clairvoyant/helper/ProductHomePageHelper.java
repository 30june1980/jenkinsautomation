package com.clairvoyant.helper;

import com.clairvoyant.GenericUtils.WebElementUtils;
import com.clairvoyant.PageFactory.ProductHomePage;
import com.clairvoyant.PageFactory.Searchpage;
import com.clairvoyant.base.BaseClass;
import org.testng.Reporter;

public class ProductHomePageHelper extends BaseClass {
    ProductHomePage objProductHomePage ;
    Searchpage objSearchpage;


    public boolean validateHomePageLoad(){
        boolean blnIsPresent= false;
        objProductHomePage = new ProductHomePage(getDriver());
        //ProductHomePage objProductHomePage = new ProductHomePage(getDriver());
        objProductHomePage.isLabelPresent();
           return blnIsPresent;

    }

    public void addToCart(){
        String textItem = "PINK DROP SHOULDER OVERSIZED T SHIRT";
        performSearch(textItem);
    }

    public void performSearch(String text){

        objProductHomePage = new ProductHomePage(getDriver());
        objSearchpage=new Searchpage(getDriver());
        objProductHomePage.searchOnpage(text);
        objSearchpage.selectColorValue("Pink");
        objSearchpage.selectSizeValue("36");
        objSearchpage.clickAddToCart();
        System.out.println(objSearchpage.getTextCart());

    }


}
