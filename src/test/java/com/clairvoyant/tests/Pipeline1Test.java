package com.clairvoyant.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Pipeline1Test {

    @Test
    public void test2(){
        Assert.assertTrue(true);
        Reporter.log("# I am in Pipeline1 task.",true);
    }
}
