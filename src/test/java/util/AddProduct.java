package util;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class AddProduct {

    public static XSSFWorkbook workbook;
    public static XSSFSheet worksheet;
    public static DataFormatter formatter= new DataFormatter();
    public static String file_location = System.getProperty("user.dir")+"/src/test/java/resources/data-provider.xlsx";
    static String SheetName= "Sheet1";
    public int DataSet=-1;

    @DataProvider
    public static Object[][] ReadVariant() throws IOException
    {
        FileInputStream fileInputStream= new FileInputStream(file_location); //Excel sheet file location get mentioned here
        workbook = new XSSFWorkbook (fileInputStream); //get my workbook
        worksheet=workbook.getSheet(SheetName);// get my sheet from workbook
        XSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0

        int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
        int ColNum= Row.getLastCellNum(); // get last ColNum

        Object[][] Data = new Object[RowNum - 1][ColNum]; // pass my  count data in array

        for(int i=0; i<RowNum-1; i++) //Loop work for Rows
        {
            XSSFRow row= worksheet.getRow(i+1);

            for (int j=0; j<ColNum; j++) //Loop work for colNum
            {
                if(row==null)
                    Data[i][j]= "";
                else
                {
                    XSSFCell cell= row.getCell(j);
                    if(cell==null)
                        Data[i][j]= ""; //if it get Null value it pass no data
                    else
                    {
                        String value=formatter.formatCellValue(cell);
                        Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
                    }
                }
            }
        }

        return Data;
    }

    @Test(dataProvider="ReadVariant") //It get values from ReadVariant function method

//Here my all parameters from excel sheet are mentioned.
    public void AddVariants(String NAME, String DESCRIPTION, String WEIGHT, String PRICE, String MODEL, String RS) throws Exception
    {
        //Data will set in Excel sheet once one parameter will get from excel sheet to Respective locator position.
        DataSet++;
        System.out.println("NAme of product available are:" +NAME);
        System.out.println("Weight for products are:" +DESCRIPTION);
        System.out.println("Volume of product are:" +WEIGHT);
        System.out.println("Description quotation are:" +PRICE);
        System.out.println("Description Model are:" +MODEL);
    }
}