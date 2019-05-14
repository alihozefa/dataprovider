package util;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {

    private static XSSFSheet excelWSheet;

    private static XSSFWorkbook excelWBook;

    public static Object[][] getTableArray(String filePath, String sheetName) throws Exception {

        String[][] tabArray = null;

        try {

            FileInputStream excelFile = new FileInputStream(filePath);

            // Access the required test data sheet

            excelWBook = new XSSFWorkbook(excelFile);

            excelWSheet = excelWBook.getSheet(sheetName);

            int startRow = 1;

            int startCol = 1;

            int ci, cj;

            int totalRows = excelWSheet.getLastRowNum();

            // you can write a function as well to get Column count

            int totalCols = excelWSheet.getRow(0).getLastCellNum()-1;

            tabArray = new String[totalRows][totalCols];

            ci = 0;

            for (int i = startRow; i <= totalRows; i++, ci++) {

                cj = 0;

                for (int j = startCol; j <= totalCols; j++, cj++) {

                    tabArray[ci][cj] = getCellData(i, j);

                }

            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return (tabArray);

    }

    private static String getCellData(int rowNum, int colNum) throws Exception {

        DataFormatter formatter = new DataFormatter();
        try {

            //get the value from cell using row and col number and then  format it into String irrespective of original format
            String cellData = formatter.formatCellValue(excelWSheet.getRow(rowNum).getCell(colNum));

           return cellData;

        }catch(Exception e){

                e.getMessage();

                throw (e);

        }

    }

}