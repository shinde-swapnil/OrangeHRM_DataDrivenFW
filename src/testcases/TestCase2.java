package testcases;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.GenericUtility;
import utils.Reusables;



public class TestCase2 {
	private static WebDriver driver;	
	private Properties p;
	
	@BeforeTest
	public void start() throws IOException, InterruptedException
	{
		p = GenericUtility.loadProperties();
		
		//Setting the webdriver.chrome.driver property to its executable's location
        System.setProperty(p.getProperty("ChromeDriverKey"), p.getProperty("ChromeDriverPath"));
        
      //Instantiating driver object
        driver = new ChromeDriver(); 
        
        
      //Using get() method to open a webpage    
        
        Reusables.postUrl(driver, p.getProperty("loginPageURL"));
        
        Reusables.wait(driver, 30);
		
		
	}
	
	@Test()
	public void landingPageTest() throws IOException, InterruptedException
	{		
		String actualURL = Reusables.getCurrentURL(driver);
		Assert.assertEquals(actualURL, p.getProperty("loginPageURL"), "Wrong URL");
	}
	
	
	@AfterTest
	public void tearDown() throws Exception {
		Reusables.waitForPageLoad();
		driver.close();
	}

}
