package com.clairvoyant.ExcelUtils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelRead {

    private static final String NEW_EXCEL_XTNSION = ".xlsx";
    private static final String OLD_EXCEL_XTNSION = ".xls";
    private static HashMap<String, List<HashMap<String, String>>> testDataRepo = null;
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell1;
    private static XSSFRow Row1;

    /**
     * clears excel data ecluding column headers
     *
     * @param excelFileName
     * @param sheetName
     */
    public static void clearExceldata(String excelFileName, String sheetName) {

        try (FileInputStream fileInputStream = new FileInputStream(excelFileName)) {
            try (Workbook workbook = WorkbookFactory.create(fileInputStream)) {
                XSSFSheet sheet = (XSSFSheet) workbook.getSheet(sheetName);
                for (int i = sheet.getLastRowNum(); i > 0; i--) {
                    sheet.removeRow(sheet.getRow(i));
                }
                fileInputStream.close();
                FileOutputStream outputStream = new FileOutputStream(excelFileName);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }


    }

    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
        }

        return null;
    }

    public static ArrayList<HashMap<String, Object>> readFromExcelMap(String filename) {
        ArrayList<HashMap<String, Object>> listOfmaps = new ArrayList<>();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(filename));
            try (Workbook workbook = WorkbookFactory.create(inputStream)) {
                XSSFSheet firstSheet = (XSSFSheet) workbook.getSheetAt(0);
                ExcelWSheet = firstSheet;
                Iterator<Row> iterator = firstSheet.iterator();

                int totalCols = firstSheet.getRow(0).getLastCellNum();
                int totalRows = firstSheet.getLastRowNum();
                //while (iterator.hasNext()) {
                for (int iRow = 1; iRow <= totalRows; iRow++) {
                    //Row nextRow = iterator.next();
                    HashMap<String, Object> mapLocal = new HashMap<>();
                    // Iterator<Cell> cellIterator = nextRow.cellIterator();
                    //while (cellIterator.hasNext()) {
                    // Cell nextCell = cellIterator.next();
                    //int columnIndex = nextCell.getColumnIndex();
                    for (int iCol = 0; iCol < totalCols; iCol++) {
                        //Cell nextCell = nextRow.getCell(iCol);

                        String headercellName = (String) getCellData(0, iCol, firstSheet);
                        //mapLocal.put((String) getCellData(0, iCol,firstSheet),  getCellData(iRow, iCol,firstSheet));
                        switch (headercellName) {

                              /*  case "submission_id":
                                    //mapLocal.put("submission_id",  getCellValue(nextCell));
                                    mapLocal.put("submission_id",  getCellData(iRow, iCol,firstSheet));
                                    break;*/
                            case "originalFilename":
                                mapLocal.put("originalFilename", getCellData(iRow, iCol, firstSheet));
                                break;
                            case "similarityscore":
                                mapLocal.put("similarityscore", getCellData(iRow, iCol, firstSheet));
                                break;
                            case "externalSimilarityScore":
                                mapLocal.put("externalSimilarityScore", getCellData(iRow, iCol, firstSheet));
                                break;
                            case "internalSimilarityScore":
                                mapLocal.put("internalSimilarityScore", getCellData(iRow, iCol, firstSheet));
                                break;

                        }
                    }
                    listOfmaps.add(mapLocal);
                    // }


                }

                workbook.close();
                inputStream.close();


            } catch (InvalidFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return listOfmaps;

    }

    public static void writeInExcel(String filePath, ArrayList<ArrayList<String>> listOfLists) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(filePath));
            try (Workbook workbook = WorkbookFactory.create(inputStream)) {
                Sheet sheet = workbook.getSheetAt(0);
                int rowCount = sheet.getLastRowNum();
                for (ArrayList<String> list : listOfLists) {
                    Row row = sheet.createRow(++rowCount);
                    int columnCount = -1;
                    Cell cell;

                    for (String str : list) {
                        cell = row.createCell(++columnCount);
                        if (str instanceof String) {
                            cell.setCellValue((String) str);
                        } else if (Integer.valueOf(str) instanceof Integer) {
                            cell.setCellValue((Integer) Integer.valueOf(str));
                        }
                    }

                }
                inputStream.close();
                FileOutputStream outputStream = new FileOutputStream(filePath);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Object[][] getTCData(String filePath, String sheetName, String tcName) {
        Object[][] obj = null;
        try {
            HashMap<String, List<HashMap<String, String>>> testDataRepo = getTableArray(filePath, sheetName);
            System.out.println("testDataRepo " + testDataRepo);
            List<HashMap<String, String>> testDataSet = testDataRepo.get(tcName);
            obj = new Object[testDataSet.size()][1];
            for (int i = 0; i < testDataSet.size(); i++)
                obj[i][0] = testDataSet.get(i);
            return obj;
        } catch (IOException e) {
            System.out.println("Could not locate the test data file - " + filePath);
        } catch (NullPointerException e) {
            System.out.println("Could not find test data for test case '" + tcName + "' from sheet '" + sheetName + "'. Please Check!!");
        } catch (Exception e) {
            System.out.println("Something went wrong!!Please check exception: " + e);
        }
        return getEmpty();
    }

    private static Object[][] getEmpty() {
        Object[][] obj = new Object[][]{
                {new HashMap<String, String>()}
        };
        return obj;
    }

    public static HashMap<String, List<HashMap<String, String>>> getTableArray(String FilePath, String SheetName) throws Exception {
        if (testDataRepo != null)
            return testDataRepo;
        try {
            FileInputStream ExcelFile = new FileInputStream(FilePath);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int startRow = 1;
            int startCol = 1;
            int totalRows = ExcelWSheet.getLastRowNum();
            int totalCols = ExcelWSheet.getRow(0).getLastCellNum();
            testDataRepo = new HashMap<String, List<HashMap<String, String>>>();

            for (int iRow = startRow; iRow <= totalRows; iRow++) {
                String tcName = getCellData(iRow, 0, ExcelWSheet);
                if (tcName == null || tcName.trim().isEmpty())
                    continue; //if TC name is blank, then continue to next row

                HashMap<String, String> mapcols = new HashMap<String, String>();
                for (int iCol = startCol; iCol < totalCols; iCol++) {
                    String data = getCellData(iRow, iCol, ExcelWSheet);
                    if (data != null && !data.trim().isEmpty())
                        mapcols.put(getCellData(0, iCol, ExcelWSheet), getCellData(iRow, iCol, ExcelWSheet));
                }


                List<HashMap<String, String>> dataSet = testDataRepo.get(tcName);
                if (dataSet == null) {
                    dataSet = new ArrayList<>();

                    dataSet.add(mapcols);
                    testDataRepo.put(tcName, dataSet);
                } else {
                    testDataRepo.get(tcName).add(mapcols);
                }

            }
        } catch (NullPointerException n) {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ExcelWBook != null)
                ExcelWBook.close();
        }
        return testDataRepo;
    }

    /**
     * @param RowNum
     * @param ColNum
     * @return
     * @throws Exception
     */
    public static String getCellData(int RowNum, int ColNum, Sheet sheet) throws Exception {

        try {
//            if(ExcelWSheet.getRow(RowNum).getCell(ColNum)!=null)
            Cell1 = (XSSFCell) sheet.getRow(RowNum).getCell(ColNum);

            int dataType = Cell1.getCellType();

            if (dataType == 3) {

                return "";

            } else {

                Object CellData = Cell1;

                return CellData.toString();

            }
        } catch (Exception e) {

            System.out.println(e.getMessage());

            throw (e);

        }

    }


    public static Map<Object, Map<Object, Object>> setMapData(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        int colSize;
        int lastRow = sheet.getLastRowNum();
        Map<Object, Map<Object, Object>> excelFileMap = new HashMap<Object, Map<Object, Object>>();
        //Row row = sheet.getRow(0);
        //colSize = row.getLastCellNum();
        Map<Object, Object> dataMap = new HashMap<Object, Object>();
        //Looping over entire row
        for (int i = 0; i <= lastRow; i++) {
            Cell keyCell = null;
            Cell valueCell = null;

            Row row = sheet.getRow(i);
            colSize = row.getLastCellNum();
            for (int c = 1; c < colSize; c++) {
                valueCell = row.getCell(c);

                //1st Cell as Value
                //valueCell = row.getCell(1);

                //0th Cell as Key
                keyCell = row.getCell(0);

                Object value = valueCell;
                Object key = keyCell;

                //Putting key & value in dataMap
                dataMap.put(key, value);
            }
            //Putting dataMap to excelFileMap
            excelFileMap.put("DataSheet", dataMap);
        }
        //Returning excelFileMap
        return excelFileMap;

    }


    public Object[][] readExcel(String path, String fileName, String sheetName) throws IOException {
        Object[][] exceldata = null;
        int rowSize;
        int colSize;
        Workbook myworkbook = null;
        File file = new File(path + fileName);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            String fileXtesnion = fileName.substring(fileName.indexOf('.'));
            if (fileXtesnion.equals(NEW_EXCEL_XTNSION)) {
                myworkbook = new XSSFWorkbook(fileInputStream);
            } else if (fileXtesnion.equals(OLD_EXCEL_XTNSION)) {
                myworkbook = new HSSFWorkbook(fileInputStream);
            }
            Sheet sheet = myworkbook.getSheet(sheetName);
            rowSize = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            colSize = row.getLastCellNum();
            exceldata = new Object[rowSize][colSize];
            for (int i = 1; i <= rowSize; i++) {
                row = sheet.getRow(i);
                for (int j = 0; j < colSize; j++) {
                    Cell cellData = row.getCell(j);
                    if (cellData != null) {
                        exceldata[i - 1][j] = cellData;
                    } else {
                        exceldata[i - 1][j] = "";
                    }
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            myworkbook.close();
            ;
        }

        return exceldata;
    }

}
