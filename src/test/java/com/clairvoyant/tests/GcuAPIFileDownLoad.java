package com.clairvoyant.tests;
import static io.restassured.RestAssured.given;

import com.clairvoyant.GenericUtils.Utilities;
import com.mongodb.DB;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.internal.util.IOUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static io.restassured.path.xml.XmlPath.given;

public class GcuAPIFileDownLoad {
    String DBName = "mysql";


@Test
    public void Get_Submissions_From_prod() throws SQLException {
    DataBaseConnection DB = new DataBaseConnection(DBName);
        System.out.println("Done connecting db");
        ArrayList<String> userdetailsfromdb = DB
                .Student_login("select submission_id,original_file from submission limit 1");
        String submissionids;
        String originalFileName;
        submissionids = userdetailsfromdb.get(0);
        originalFileName = userdetailsfromdb.get(1);
        System.out.println(submissionids);
    }
//prod
    @Test
    public void getreport_prod() throws SQLException {

        RestAssured.baseURI = "https://lopescgc-prod.us-west-2.elasticbeanstalk.com";
        RestAssured.config = RestAssured.config()
                .sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation());
        Response response;
        /*response = given().contentType(ContentType.URLENC).header("api-key", "demo-key")
                .pathParam("submission_id", "8a6732c6-4458-426c-8a8e-5434785152fd").when().get("/webapi/v1.0/submissions/{submission_id}/report")
                .getBody().asString();*/
        String submission_id = "8a6732c6-4458-426c-8a8e-5434785152fd";
        String resultAstring;
        response = given().contentType(ContentType.URLENC).header("api-key", "demo-key")
                .pathParam("submission_id", submission_id).when().get("/webapi/v1.0/submissions/{submission_id}/report");

      /*  response = given().contentType(ContentType.JSON).header("api-key", "demo-key")
                .pathParam("submission_id", submission_id).when().get("/webapi/v1.0/submissions/{submission_id}/report");*/

        resultAstring = response.getBody().toString();



        //System.out.println(Utilities.readJsonUsingResponse(response,"internalSimilarityScore","singleNode"));
        System.out.println(Utilities.readJsonUsingResponse(response,"grammar.grammarIssueId", "ListNode"));
        getReportProd(response,submission_id,"test123.docx");
    }





    @Test
    public void downloadsubmissions_originalReport() throws SQLException {
        //getreport_prod();
        RestAssured.baseURI = "https://lopescgc-prod.us-west-2.elasticbeanstalk.com";
        RestAssured.config = RestAssured.config()
                .sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation());
        String submissionids = "8a6732c6-4458-426c-8a8e-5434785152fd";
        String originalFileName = "Essay+03.docx";
        Response response;
        response = given().contentType("application/json").header("api-key", "demo-key")
                .pathParam("submissionId", submissionids).queryParam("fileName", originalFileName).when()
                .get("/webapi/v1.0/admin/download/{submissionId}");
       // String filePath = response.getBody().asString();

        InputStream inputStream=response.asInputStream();
        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            downloadLocally(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }







    }

  /*
    Download file locally and store.
    */

    private static void downloadLocally(byte[] pdfFile) {
        FileOutputStream fos;

        try {
            fos = new FileOutputStream("D:\\RnD\\JenkinsProj\\src\\main\\resources\\Data\\test1.pdf");
            fos.write(pdfFile);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void getReportProd(Response response, String submissionid,String filename) {
        String qaExcelPath = "D:\\RnD\\JenkinsProj\\src\\main\\resources\\TestDataFiles\\qaReport.xlsx";
        String devExcel = "D:\\RnD\\JenkinsProj\\src\\main\\resources\\TestDataFiles\\devReport.xlsx";

        try {

            ArrayList<String> rowList = new ArrayList<>();
            //rowList.add(Utilities.readJsonUsingResponse(response, "comments.submissionId", "ListNode"));
            rowList.add(submissionid);
            rowList.add(filename);
            rowList.add(Utilities.readJsonUsingResponse(response, "similarityScore", "singleNode"));
            rowList.add(Utilities.readJsonUsingResponse(response, "externalSimilarityScore", "singleNode"));
            rowList.add(Utilities.readJsonUsingResponse(response, "internalSimilarityScore", "singleNode"));
            rowList.add(Utilities.readJsonUsingResponse(response, "grammar.grammarIssueId", "ListNode"));

            ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
            listOfLists.add(rowList);
            checkValuesTest objchkValueTest = new checkValuesTest();
            objchkValueTest.loadExcel( qaExcelPath, listOfLists);
            //getDifference(reoprtExcelFilepath, devExcel);

            //loadDataMap("D:\\RnD\\JenkinsProj\\src\\main\\resources\\TestDataFiles\\devReport.xlsx");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
