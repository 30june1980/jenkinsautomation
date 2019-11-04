package com.clairvoyant.Listeners;

import com.clairvoyant.GenericUtils.DateUtil;
import com.clairvoyant.base.BaseClass;
import com.clairvoyant.base.GetScreenshot;
import com.vimalselvam.testng.listener.ExtentTestNgFormatter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

public class ListenerTest extends BaseClass implements ITestListener {

    String caseId;
    String runId;
    String className;
    String simpleClassName;
    String dest;
    String replace;
    String datefolder;

    @Override
    public void onTestStart(ITestResult iTestResult) {

        className = iTestResult.getMethod().getTestClass().getName();
        simpleClassName = className.substring(className.lastIndexOf(".") +1);
        datefolder = DateUtil.currentDate("yyyyMMddhhmmss");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {


        Reporter.log("# class name :- " + simpleClassName + " and method name - " + iTestResult.getName() + " has been executed and passed.",true);
        String tc = datefolder + "_" + simpleClassName + "_" + iTestResult.getName();
        if(iTestResult.getTestContext().getCurrentXmlTest().getParameter("Screenshot").equalsIgnoreCase("Yes")){
            try {
                dest = GetScreenshot.capturePassed(simpleClassName,tc,getDriver());
                ExtentTestNgFormatter.getInstance().addScreenCaptureFromPath("file:///" + dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Reporter.log("<a href=\"file://" + dest + "\"><img src=\"file:///"  + dest + "\" style=width:100px;height:100px;/>" + " Passed Screenshotlink" + "</a><br/>");

        }

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Reporter.log("# class name :- " + simpleClassName + " and method name - " + iTestResult.getName() + " has been executed and Failed.",true);
        String tc = datefolder + "_" + simpleClassName + "_" + iTestResult.getName();
        if(iTestResult.getTestContext().getCurrentXmlTest().getParameter("Screenshot").equalsIgnoreCase("Yes")){
            try {
                dest = GetScreenshot.captureFailed(simpleClassName,tc,getDriver());
                ExtentTestNgFormatter.getInstance().addScreenCaptureFromPath("file:///" + dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Reporter.log("<a href=\"file://" + dest + "\"><img src=\"file:///"  + dest + "\" style=width:100px;height:100px;/>" + " Failed Screenshotlink" + "</a><br/>");

        }


    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
