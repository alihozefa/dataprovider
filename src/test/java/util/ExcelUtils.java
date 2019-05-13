package util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {

    private static XSSFSheet excelWSheet;

    private static XSSFWorkbook excelWBook;

    private static XSSFCell xssfCell;

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

                    System.out.println(tabArray[ci][cj]);

                }

            }

        } catch (FileNotFoundException e) {

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        } catch (IOException e) {

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        }

        return (tabArray);

    }

    public static String getCellData(int rowNum, int colNum) throws Exception {

        try {

            xssfCell = excelWSheet.getRow(rowNum).getCell(colNum);

            int dataType = xssfCell.getColumnIndex();

            if (dataType == 3) {

                return "";

            } else {

                String CellData = xssfCell.getStringCellValue();

                return CellData;

            }
        }catch(Exception e){

                System.out.println(e.getMessage());

                throw (e);

        }

    }

}