package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.ExcelUtils;


public class DataProviderTest {

    @Test(dataProvider="Authentication")
    public void loginData(String sUserName,String sPassword){

        System.out.println("Username for the user is:" + sUserName);

        System.out.println("Password for the user is:" + sPassword);

    }

    @DataProvider

    public Object[][] Authentication() throws Exception{

        Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir")+"/src/test/java/resources/data-provider.xlsx","Sheet2");

        return (testObjArray);

    }

}