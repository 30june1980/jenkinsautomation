package com.clairvoyant.GenericUtils;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class Utilities {

    /**
     * @param strText
     * @return
     */
    public static BigDecimal getDecimalValue(String strText) {

        BigDecimal b = new BigDecimal(strText);
        return b;
    }

    public static String readAnyFile(String filePath) {
        AtomicReference<String> newLine = new AtomicReference<>("");
        try (Stream<String> inpstream = Files.lines(Paths.get(filePath))) {
            inpstream.forEach(line -> {
                newLine.set(line);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newLine.get();
    }


    public static String readJsonFromTxtFile(String inpString, String key,String type) {

        Object value = "";

        String jsonString = inpString;
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(jsonString);
            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject) object;
            JsonPath jsonpath=JsonPath.from(String.valueOf(jsonObject));
            //Reading the String
            //value =  jsonObject.get(key);
            if (type.equals("singleNode")) {
                value =  jsonObject.get(key);
            }else if (type.equals("ListNode")){
                List<Object> node = jsonpath.get(key);

                value = node.get(node.size()-1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return value.toString();
    }

    /*
     public static String getValue(Response resp, String jsonValue, String type) {
       String respJson = resp.asString();
        JsonPath jsonPath = JsonPath.from(respJson);
        String nodeValue = null;
        if (type.equals(SINGLE_NODE)) {
            nodeValue = jsonPath.get(jsonValue);
        }
        else if (type.equals(LIST_NODE)){
            List<String> node = jsonPath.get(jsonValue);
            nodeValue = node.toString();
        }
        Reporter.log("Output for "+jsonValue+" is "+nodeValue,true);
        return nodeValue;
    }
     */

    public static String readJsonUsingResponse(Response response, String key, String type) {

        Object value = "";
        JSONParser jsonparser = new JSONParser();

        String strResponseJson = response.getBody().prettyPrint();

        try {
            Object object = jsonparser.parse(strResponseJson);
            JSONObject jsonObject = (JSONObject) object;
            String nodeValue = null;
            JsonPath jsonpath=JsonPath.from(String.valueOf(jsonObject));
            if (type.equals("singleNode")) {
                value =  jsonObject.get(key);
            }else if (type.equals("ListNode")){
                List<Object> node = jsonpath.get(key);

                value = node.get(node.size()-1);
            }
            System.out.println("value " + value);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        return value.toString();
    }


    /**
     * @param jsonFilePath
     * @return
     */
    public static String readJson(String jsonFilePath,String key, String type) {
        Object value = "";
        JSONParser jsonparser = new JSONParser();


        try {
            Object object = jsonparser.parse(new FileReader(jsonFilePath));

            JSONObject jsonObject = (JSONObject) object;
            JsonPath jsonpath=JsonPath.from(String.valueOf(jsonObject));
            if (type.equals("singleNode")) {
                value =  jsonObject.get(key);
            }else if (type.equals("ListNode")){
                List<Object> node = jsonpath.get(key);
                //value = node.get(0);
                value = node.get(node.size()-1);
                //System.out.println("node " + node);
            }

           //System.out.println("value " + value.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value.toString();
    }
}
