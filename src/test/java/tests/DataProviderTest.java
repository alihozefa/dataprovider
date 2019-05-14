package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.ExcelUtils;


public class DataProviderTest {

    private String fileLocation= System.getProperty("user.dir")+"/src/test/java/resources/";

    @Test(dataProvider="Authentication")
    public void loginData(String sUserName,String sPassword){

        System.out.println("Username for the user is:" + sUserName);

        System.out.println("Password for the user is:" + sPassword);

    }

    @Test(dataProvider="ReadVariant") //It get values from ReadVariant function method
    public void AddVariants(String NAME, String DESCRIPTION, String WEIGHT, String PRICE, String MODEL) throws Exception
    {
        System.out.println("NAme of product available are:" +NAME);
        System.out.println("Weight for products are:" +DESCRIPTION);
        System.out.println("Volume of product are:" +WEIGHT);
        System.out.println("Description quotation are:" +PRICE);
        System.out.println("Description Model are:" +MODEL);
    }

    @DataProvider
    public Object[][] Authentication() throws Exception{

        Object[][] testObjArray = ExcelUtils.getTableArray(fileLocation+"data-provider.xlsx","Sheet2");

        return (testObjArray);

    }

    @DataProvider
    public Object[][] ReadVariant() throws Exception{

        Object[][] testObjArray = ExcelUtils.getTableArray(fileLocation+"data-provider.xlsx","Sheet1");

        return (testObjArray);

    }

}