package com.clairvoyant.DataProvider;

import com.clairvoyant.ExcelUtils.ExcelRead;
import com.clairvoyant.base.BaseClass;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.clairvoyant.base.Constants.DATA_SHEET_PATH;

public class DataProviderClass extends BaseClass {
    Object[][] testObjArray=null;
    @DataProvider(name = "testdataProvider")
    public Object[][] dellTcData(Method method) {
        try {
            Test test = method.getAnnotation(Test.class);
            testObjArray = ExcelRead.getTCData(System.getProperty("user.dir") + File.separator+ "src"+File.separator+"main"+File.separator+"resources"+File.separator+"TestDataFiles"+File.separator+"qaReport.xlsx", "Sheet1", "123.0");
        } catch (Exception e) {
            Reporter.log(e.getMessage(), true);
        }
        return testObjArray;
    }


    //here we need to ensure that testcase method name should be same as that of ExcelSheetname
    @DataProvider(name = "testDataProvider")
    public static Object returnDataSet(Method testName, ITestContext context) throws IOException {
        ExcelRead objExcelRead = new ExcelRead();
        String dataFileName = context.getCurrentXmlTest().getParameter("datafilename");
        Object exceldata = objExcelRead.readExcel(DATA_SHEET_PATH, dataFileName, testName.getName());
        return exceldata;
    }
}
