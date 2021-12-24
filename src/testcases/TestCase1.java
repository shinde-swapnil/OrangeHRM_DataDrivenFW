package testcases;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.ExcelUtils;
import utils.GenericUtility;
import utils.Reusables;

public class TestCase1 {
	private static WebDriver driver;
	private Properties p;

	@BeforeTest
	public void start() throws IOException, InterruptedException {
		p = GenericUtility.loadProperties();

		// Setting the webdriver.chrome.driver property to its executable's location
		System.setProperty(p.getProperty("ChromeDriverKey"), p.getProperty("ChromeDriverPath"));

		// Instantiating driver object
		driver = new ChromeDriver();

		// Using get() method to open a webpage
		// driver.get(p.getProperty("loginPageURL"));

		Reusables.postUrl(driver, p.getProperty("loginPageURL"));

		Reusables.wait(driver, 30);

	}

	@Test()
	public void loginHrmTest() throws IOException, InterruptedException {
		ExcelUtils.setExcel("sample");

		// Get locators
		WebElement userNameTxtBox;
		WebElement passwordTxtBox;

		int maxRowCount = ExcelUtils.getRowCount("sample");
		System.out.println(maxRowCount);

		// action
		for (int i = 1; i <= maxRowCount; i++) {
			String userNamedata =  ExcelUtils.getCellData(i, 0);
			String pwddata = ExcelUtils.getCellData(i, 1);
			System.out.println(userNamedata);
			System.out.println(pwddata);

			Reusables.waitForPageLoad();
			// input username
			driver.findElement(By.xpath(p.getProperty("inputUserName"))).clear();
			driver.findElement(By.xpath(p.getProperty("inputUserName"))).sendKeys(userNamedata);

			Reusables.waitForPageLoad();
			// input password
			driver.findElement(By.xpath(p.getProperty("inputPassword"))).clear();
			driver.findElement(By.xpath(p.getProperty("inputPassword"))).sendKeys(pwddata);

			// login button click
			Reusables.click(driver, p.getProperty("buttonLogin"));

			Reusables.waitForPageLoad();
			Reusables.getScreenShot(driver);
			
		}
	}

	@Test
	public void columnTest() throws IOException {
		ExcelUtils.setExcel("sample");
		ExcelUtils.getColumnData("sample", "username");
	}

	@AfterTest
	public void tearDown() throws Exception {
		Reusables.waitForPageLoad();
		driver.close();
	}

}
