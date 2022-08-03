package vertex.CD.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import vertex.CD.util.TestUtil;

public class TestBase {
	
public static WebDriver driver;

public static Properties prop;


public TestBase()
{
	try {
		
		prop = new Properties();
		FileInputStream ip = new FileInputStream ("C:\\java\\ClaimDriver\\src\\main\\java\\vertex\\CD\\config\\config.properties");
		prop.load(ip);
	    }
	catch (FileNotFoundException e) 
	      {
	    	e.printStackTrace();
	      }
	
	catch (IOException e)
	{
	e.printStackTrace();	
	}
}

public static void initialization()
{
	String browserName = prop.getProperty("browser");
	String chromepath = prop.getProperty("chromepath");
	String ffpath = prop.getProperty("firefoxpath");
	
	if (browserName.equals("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\102\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", chromepath);
		//System.setProperty("webdriver.chrome.driver", "./Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}
	
	else if (browserName.equals("firefox"))
	{
		//System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\99\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", ffpath);
		driver = new ChromeDriver();
	}
	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_load_timeout, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_wait, TimeUnit.SECONDS);
	
	driver.get(prop.getProperty("baseURL"));
	
	
}


}
