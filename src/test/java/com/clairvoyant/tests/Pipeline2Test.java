package com.clairvoyant.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Pipeline2Test {

    @Test
    public void test1(){
        Assert.assertTrue(true);
        Reporter.log("# I am in Pipeline2 task.",true);
    }
}
