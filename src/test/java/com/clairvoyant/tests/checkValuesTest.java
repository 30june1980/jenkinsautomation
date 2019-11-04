package com.clairvoyant.tests;

import com.clairvoyant.DataProvider.DataProviderClass;
import com.clairvoyant.ExcelUtils.ExcelRead;
import com.clairvoyant.GenericUtils.Utilities;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class checkValuesTest {

    String strJsonText = "{\"lineNum\":1,\"itemNum\":\"6d6f6c3b-8713-4f68-9e4a-93ba64f86f18\",\"sku\":\"14PT-BCAIPMATT-2X3.5\",\"itemConfig\":{\"orderPayload\":{\"order_id\":\"Business Cards\",\"skip_conformation\":false,\"jobs\":[{\"dropship\":\"true\",\"job_name\":\"Business Cards\",\"product_uuid\":\"6d6f6c3b-8713-4f68-9e4a-93ba64f86f18\",\"runsize_uuid\":\"8a3a0fd1-38ae-49a0-8736-3fedadc3dc93\",\"turnaroundtime_uuid\":\"ad290b77-c9d5-476c-b847-9ad4d25ae14e\",\"colorspec_uuid\":\"32d3c223-f82c-492b-b915-ba065a00862f\",\"option_uuids\":[\"b33687ae-ed50-4903-b009-014531f4e1bb\",\"cb23ad7e-5e87-41f9-9956-4fe91dc8820b\",\"f169e8b1-5d3f-408a-81fd-c857f7b9c37b\",\"ee567ae7-d0e9-47ca-830a-b172c097b44f\",\"6f4fd47a-f70f-4f3d-84f8-a78ef9a62a5a\",\"121bb7b5-3b4d-429f-bd8d-bbf80e953313\",\"32d3c223-f82c-492b-b915-ba065a00862f\",\"8a3a0fd1-38ae-49a0-8736-3fedadc3dc93\",\"ad290b77-c9d5-476c-b847-9ad4d25ae14e\",\"04797b1f-f27b-492f-b19f-fcf88962dee3\"],\"files\":{\"fr\":\"https://proforma-sandbox-api.4over.com/files/f112be83-8537-422e-9483-3c1494d252c2\"},\"ship_to\":{\"address\":\"5900 SAN FERNANDO RD\",\"address2\":\"\",\"city\":\"GLENDALE\",\"zipcode\":\"91202\",\"state\":\"CA\",\"country\":\"US\",\"phone\":\"818 554 2323\",\"firstname\":\"John\",\"lastname\":\"Smith\"},\"shipper\":{\"shipping_method\":\"Free Ground Shipping\",\"shipping_code\":\"03f\"},\"ship_from_facility\":\"GLN\",\"custom_data\":{\"expiration_data\":{\"shipping_amount\":\"0.00\",\"base_price\":16.76,\"options_price\":0,\"turnaroundtime_price\":0,\"total_price\":\"16.76\",\"shipping_tax_amount\":\"0.00\",\"tax_amount\":1.72,\"date_expiration\":\"2019-10-15 11:05:40\",\"check_sum\":\"f2492ce7fd59a5f79776846e4c5c61578ab4878bdb2bc9edbb63158bcb97169f\"}}}]},\"editData\":{\"current_tag_uuid\":\"04797b1f-f27b-492f-b19f-fcf88962dee3\",\"options_uuid\":\"&option_uuids[]=b33687ae-ed50-4903-b009-014531f4e1bb&option_uuids[]=cb23ad7e-5e87-41f9-9956-4fe91dc8820b&option_uuids[]=f169e8b1-5d3f-408a-81fd-c857f7b9c37b&option_uuids[]=ee567ae7-d0e9-47ca-830a-b172c097b44f&option_uuids[]=6f4fd47a-f70f-4f3d-84f8-a78ef9a62a5a&option_uuids[]=121bb7b5-3b4d-429f-bd8d-bbf80e953313&option_uuids[]=32d3c223-f82c-492b-b915-ba065a00862f&option_uuids[]=8a3a0fd1-38ae-49a0-8736-3fedadc3dc93&option_uuids[]=9c83da8f-f410-4977-8839-2a991928c39a\",\"selected_uuids\":[\"b33687ae-ed50-4903-b009-014531f4e1bb\",\"cb23ad7e-5e87-41f9-9956-4fe91dc8820b\",\"f169e8b1-5d3f-408a-81fd-c857f7b9c37b\",\"ee567ae7-d0e9-47ca-830a-b172c097b44f\",\"6f4fd47a-f70f-4f3d-84f8-a78ef9a62a5a\",\"121bb7b5-3b4d-429f-bd8d-bbf80e953313\",\"32d3c223-f82c-492b-b915-ba065a00862f\",\"8a3a0fd1-38ae-49a0-8736-3fedadc3dc93\"],\"product_uuid\":\"04797b1f-f27b-492f-b19f-fcf88962dee3\",\"category_slug\":\"marketing\"},\"uploaderData\":[{\"name\":\"Front\",\"url\":\"https://proforma-sandbox-api.4over.com/files/f112be83-8537-422e-9483-3c1494d252c2\",\"method\":\"print\",\"color\":\"\",\"raw\":{\"status\":\"warning\",\"pdf_uuid\":\"f112be83-8537-422e-9483-3c1494d252c2\",\"pdf_uri\":\"https://proforma-sandbox-api.4over.com/files/f112be83-8537-422e-9483-3c1494d252c2\",\"customer_filename\":\"5da40923362f0.jpg\",\"customer_file\":\"https://proforma-sandbox-api.4over.com/files/80228370-453d-49b0-ac62-6caa23dd8628\",\"preview_uuid\":\"3c30c914-30cc-425b-b304-b30297954051\",\"preview_uri\":\"https://proforma-sandbox-api.4over.com/files/3c30c914-30cc-425b-b304-b30297954051\",\"message\":{\"102\":\"Images below 250 dpi\",\"103\":\"Object uses RGB\"},\"original_file_name\":\"PreflightSuccess.jpg\",\"name\":\"Front\"}}]},\"description\":\"ALL INCLUSIVE PRICING 2\\\" X 3.5\\\" 14PT Matte/Dull Finish Business Cards\",\"quantity\":\"100\",\"quantityType\":\"L\",\"unitCost\":\"5.97\",\"currencyCode\":\"USD\",\"validUntil\":\"2019-10-15 11:05:40\",\"imprintLocations\":[{\"name\":\"Front\",\"url\":\"https://proforma-sandbox-api.4over.com/files/f112be83-8537-422e-9483-3c1494d252c2\",\"method\":\"print\",\"color\":\"\",\"raw\":{\"status\":\"warning\",\"pdf_uuid\":\"f112be83-8537-422e-9483-3c1494d252c2\",\"pdf_uri\":\"https://proforma-sandbox-api.4over.com/files/f112be83-8537-422e-9483-3c1494d252c2\",\"customer_filename\":\"5da40923362f0.jpg\",\"customer_file\":\"https://proforma-sandbox-api.4over.com/files/80228370-453d-49b0-ac62-6caa23dd8628\",\"preview_uuid\":\"3c30c914-30cc-425b-b304-b30297954051\",\"preview_uri\":\"https://proforma-sandbox-api.4over.com/files/3c30c914-30cc-425b-b304-b30297954051\",\"message\":{\"102\":\"Images below 250 dpi\",\"103\":\"Object uses RGB\"},\"original_file_name\":\"PreflightSuccess.jpg\",\"name\":\"Front\"}}],\"sizes\":\"\",\"extraCharges\":[{\"code\":\"tax\",\"description\":\"total tax\",\"unitCost\":\"1.72\",\"quantity\":1}],\"freight\":[{\"itemNum\":\"03f\",\"quantity\":0,\"quantityType\":\"EA\",\"description\":\"Free Ground Shipping\",\"unitCost\":\"0.00\",\"currencyCode\":\"USD\"}]}";

    @Test
    public void testJsonText(){
       String retValue1 = Utilities.readJsonFromTxtFile(strJsonText,"itemNum","singleNode");
        String retValue = Utilities.readJsonFromTxtFile(strJsonText,"itemConfig.orderPayload.jobs.custom_data.expiration_data.tax_amount","ListNode");
        System.out.println("retValue " + retValue);
        System.out.println("retValue1 " + retValue1);

    }


    public static String getMapData(String key, String filePath) throws IOException {

        Map<Object, Object> mapdata = ExcelRead.setMapData(filePath).get("DataSheet");
        Object value = mapdata.get(key);
        return value.toString();
    }

    @Test(dataProvider = "testdataProvider", dataProviderClass = DataProviderClass.class)
    public void getDataExcel(HashMap tcdata) {
        System.out.println("value " + tcdata.get("similarityscore").toString());

    }

    public static List<HashMap<String, String>> jsondataSet = null;


    public void loadExcel(String filename, ArrayList<ArrayList<String>> listOfLists) {
        ExcelRead.writeInExcel(filename, listOfLists);
    }

    public ArrayList<HashMap<String, Object>> loadDataMap(String filename) {
        ArrayList<HashMap<String, Object>> listOfmaps = ExcelRead.readFromExcelMap(filename);
       /* for (HashMap<String, Object> map : listOfmaps) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("Key :" + entry.getKey());
                System.out.println("Value : " + entry.getValue());
            }
        }
*/
        return listOfmaps;
    }

    @Test
    public void getAverage() {
        String fileName = "D:\\RnD\\JenkinsProj\\src\\main\\resources\\TestDataFiles\\diffReport.xlsx";
        String columnHeaderName = "externalSimilarityScore";
        calculateAverage(fileName, columnHeaderName);

    }

    public void calculateAverage(String excelFilePath, String columnName) {
        ArrayList<HashMap<String, Object>> sourceMapList = loadDataMap(excelFilePath);
        HashMap<String, Object> dataMap;
        double sum = 0;
        double avg = 0;
        NumberFormat format = new DecimalFormat("#.##");
        for (int i = 0; i < sourceMapList.size(); i++) {
            dataMap = sourceMapList.get(i);

            sum = sum + Math.abs(Double.parseDouble(dataMap.get(columnName).toString()));

        }
        System.out.println("sum :" + sum);
        avg = sum / sourceMapList.size();
        System.out.println("Avg data for column : " + columnName + " is : ->  " + format.format(avg));
    }


    @Test
    public void getReport() {
        String reoprtExcelFilepath = "D:\\RnD\\JenkinsProj\\src\\main\\resources\\TestDataFiles\\qaReport.xlsx";
        String devExcel = "D:\\RnD\\JenkinsProj\\src\\main\\resources\\TestDataFiles\\devReport.xlsx";
        String jSonFilePath = "D:\\RnD\\JenkinsProj\\src\\main\\resources\\PropertiesFile\\reportJson.json";
        //HashMap<String, String> mapJsondata = new HashMap<>();
        try {


            ArrayList<String> rowList = new ArrayList<>();
            rowList.add(Utilities.readJson(jSonFilePath, "comments.submissionId", "ListNode"));
            rowList.add("abc.txt");
            rowList.add(Utilities.readJson(jSonFilePath, "similarityScore", "singleNode"));
            rowList.add(Utilities.readJson(jSonFilePath, "externalSimilarityScore", "singleNode"));
            rowList.add(Utilities.readJson(jSonFilePath, "internalSimilarityScore", "singleNode"));
            rowList.add(Utilities.readJson(jSonFilePath, "grammar.grammarIssueId", "ListNode"));

            ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
            listOfLists.add(rowList);
            //loadExcel( reoprtExcelFilepath, listOfLists);
            getDifference(reoprtExcelFilepath, devExcel);

            //loadDataMap("D:\\RnD\\JenkinsProj\\src\\main\\resources\\TestDataFiles\\devReport.xlsx");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public int getColDiff(int firstNo, int secondNo) {
        return firstNo - secondNo;
    }

    public void getDifference(String sourceFilePath, String targetFilePath) {
        String diffExcelSheet = "D:\\RnD\\JenkinsProj\\src\\main\\resources\\TestDataFiles\\diffReport.xlsx";
        ExcelRead.clearExceldata(diffExcelSheet, "Sheet1");

        ArrayList<HashMap<String, Object>> targetMapList;
        ArrayList<HashMap<String, Object>> sourceMapList;
        sourceMapList = loadDataMap(sourceFilePath);
        targetMapList = loadDataMap(targetFilePath);

        ArrayList<ArrayList<String>> listOfdiffernce = new ArrayList<>();
        //for(Map.Entry<String,Object> entry: map.entrySet()){
        //targetMap.forEach((k)->System.out.println("Key " + k + "value " + v);

        HashMap<String, Object> testmapTarget;
        HashMap<String, Object> testmapSource;
        for (int i = 0; i < sourceMapList.size(); i++) {
            ArrayList<String> rowList = new ArrayList<>();
            testmapTarget = targetMapList.get(i);
            testmapSource = sourceMapList.get(i);
            String originalFilename = (String) testmapSource.get("originalFilename");
            rowList.add(originalFilename);
            rowList.add(String.valueOf(getColDiff((int) Double.parseDouble(testmapSource.get("similarityscore").toString()), (int) Double.parseDouble(testmapTarget.get("similarityscore").toString()))));
            rowList.add(String.valueOf(getColDiff((int) Double.parseDouble(testmapSource.get("externalSimilarityScore").toString()), (int) Double.parseDouble(testmapTarget.get("externalSimilarityScore").toString()))));

            rowList.add(String.valueOf(getColDiff((int) Double.parseDouble(testmapSource.get("internalSimilarityScore").toString()), (int) Double.parseDouble(testmapTarget.get("internalSimilarityScore").toString()))));

            listOfdiffernce.add(rowList);

        }
        loadExcel(diffExcelSheet, listOfdiffernce);

        for (HashMap<String, Object> map : sourceMapList) {

            for (Map.Entry<String, Object> entry : map.entrySet()) {

            }
        }


    }

    @Test
    public void checkProntDecimal() {
        String s = "$150.20";
        // convert string to BigDecimal
        BigDecimal b = new BigDecimal(s);
        System.out.println(b);

        System.out.println("test" + Utilities.getDecimalValue(s).compareTo(BigDecimal.valueOf(0)));
    }


    @Test
    public void printJsonValue() {
        String filename = "D:\\RnD\\JenkinsProj\\src\\main\\resources\\PropertiesFile\\report1.txt";
        String jSonFilePath = "D:\\RnD\\JenkinsProj\\src\\main\\resources\\PropertiesFile\\reportJson.json";
       /* String content =Utilities.readAnyFile(filename);
        System.out.println("value " + Utilities.readJsonFromTxtFile(content,"lineNum"));*/

        //working code
        //String value = Utilities.readJson(jSonFilePath,"internalSimilarityScore","singleNode");
        //String value = Utilities.readJson(jSonFilePath, "comments.submissionId", "ListNode");
        String value = Utilities.readJson(jSonFilePath, "grammar.grammarIssueId", "ListNode");

        System.out.println("value 2 : " + value);

    }


    @Test
    public void getColumnValueExcel() {


    }
}
