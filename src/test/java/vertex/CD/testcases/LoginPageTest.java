package vertex.CD.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vertex.CD.base.TestBase;
import vertex.CD.pages.HomePage;
import vertex.CD.pages.LoginPage;
import vertex.CD.util.CustomListener;
import vertex.CD.util.TestUtil;
import vertex.CD.util.XLUtility;


@Listeners(CustomListener.class)
public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest()
	{
		super();
	}
	
	@DataProvider(name="LoginData")
	String [][] getData( ) throws IOException {
		
		String path="C:\\java\\ClaimDriver\\src\\main\\java\\vertex\\CD\\testdata\\LoginData.xlsx";
		
		int rownum = XLUtility.getRowCount("Sheet1");
		int colcount = XLUtility.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String [rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for (int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtility.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return logindata;
		
	}
	
	
@BeforeMethod
public void setup() 
{
	initialization();
	loginPage = new LoginPage();
}
	
@Test(priority=1, enabled=false)
public void loginPageTitleTest()
{
	String title = loginPage.validateLoginPageTitle();
	Assert.assertEquals(title, "Log in | ClaimDriver");
}

@Test(priority=2, enabled=false)
public void loginTest() throws IOException
{
	homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	boolean result = loginPage.LoginTest();
	
	Assert.assertFalse(result);
	
//	if(result == true) 
//	{
//		Assert.assertTrue(true);
//		//logger.info("Login test passed");
//	}
//	
//	else 
//	{
//		TestUtil.captureScreen(driver,"loginTest");
//		Assert.assertTrue(false);
//		//logger.info("Login test failed");	
//	}
   //Assert.assertEquals(result, true);
}

@Test(priority=1, dataProvider="LoginData")
public void loginTestDDT(String user, String pwd) throws IOException
{
	homePage = loginPage.login(user,pwd);
	boolean result = loginPage.LoginTest();
	
	Assert.assertFalse(result);
}


@AfterMethod
public void tearDown()
{
	driver.quit();
}
}
