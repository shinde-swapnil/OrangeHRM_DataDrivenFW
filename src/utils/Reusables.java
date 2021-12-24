package utils;

import static org.testng.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.logging.FileHandler;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reusables {
	
	

	// custom date creator
	public static String getCustomDate() 
	{		
		DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyy_HHmmss");		
		Date date = new Date();		
		String date1 = dateFormat.format(date);	
		return date1;
	}

	// Finding elements
	public static boolean isElementPresent(WebDriver driver, By locator) {
		

		if (driver.findElements(locator).size() != 0) {
			// Element present
			return true;

		} else {
			// Element not present
			return false;
		}
	}

	// Inserting string in Text Field in Selenium-WebDriver

	public static void insertText(WebElement field, String value) {
		field.clear();
		field.sendKeys(value);
	}

	// public void enterText(String elementLocator, String text) {
	// System.out.println(text);
	// System.out.println(elementLocator);
	// WebElement webElement = waitForElement(elementLocator);
	// webElement.clear();
	// webElement.sendKeys(text);
	// }

	// Method for screenshot
	public static void getScreenShot(WebDriver driver) {
		try {

			String formattedDate = getCustomDate();
			String ext = ".png";
			String currentDir = System.getProperty("user.dir");
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// File destFile = new File(currentDir + "//Screenshots//" + "snap_" +
			// System.currentTimeMillis() + ext);
			File destFile = new File(currentDir + "//Screenshots//" + "snap_" + formattedDate + ext);
			FileHandler.copy(scrFile, destFile);
			System.out.println("done");
		} catch (IOException ioe) {
			System.out.println("Exception while taking ScreenShot " + ioe.getMessage());
		}
	}

	public static void wait(WebDriver driver, int w) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(w, TimeUnit.SECONDS);
	}

	public static void waitForPageLoad() {
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static WebElement waitForElement(WebDriver driver, String elementLocator) {
		WebElement webElement = null;
		int timeout = 20; // in seconds
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			// System.out.println(elementLocator);
			webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementLocator)));
		} catch (WebDriverException e) {
			// do nothing, don't want to log this
		}

		if (webElement == null) {
			assertFalse(true, "WebElement not found within " + timeout + " seconds. Failing test!" + " of element: "
					+ elementLocator + "\nCurrent page: " + getCurrentURL(driver));
		}
		return webElement;
	}

	public static WebElement waitForElementPresence(WebDriver driver, String elementLocator) {
		WebElement webElement = null;
		int timeout = 20; // in seconds
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			// System.out.println(elementLocator);
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementLocator)));

		} catch (WebDriverException e) {
			// do nothing, don't want to log this
		}

		// if element wasn't found -> fail test
		if (webElement == null) {
			assertFalse(true, "WebElement not found within " + timeout + " seconds. Failing test!" + " of element: "
					+ elementLocator + "\nCurrent page: " + getCurrentURL(driver));
		}
		return webElement;
	}

	public static void postUrl(WebDriver driver, String baseUrl) {
		try {
			driver.get(baseUrl);
			driver.manage().window().maximize();
		} catch (WebDriverException e) {
			System.out.println("Cannot get the requested url" + baseUrl);
		}
	}

	public static void click(WebDriver driver, String elementLocator) {

		WebElement webElement = waitForElementPresence(driver, elementLocator);
		try {
			webElement.click();
		} catch (Exception ex) {

		}
	}

	public static void refreshPage(WebDriver driver) {

		String currentURL = getCurrentURL(driver);
		driver.navigate().to(currentURL);
	}

	public static String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public static String getElementText(WebDriver driver, String elementLocator) {
		WebElement webElement = driver.findElement(By.xpath(elementLocator));
		return webElement.getText();
	}

}
