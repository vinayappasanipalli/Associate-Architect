package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseDriver;

public class CommonFunctions extends BaseDriver {

	public static String capturescreenshot() {
		Date currentdate = new Date();
		String screenshotfilename = currentdate.toString().replace(" ", "-").replace(":", "-");
		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshots/" + screenshotfilename + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();
	}
	
	
	
	public static void cf_login(String username, String password) throws Throwable {

		cf_webelementclick((objrepo.getProperty("Login_Button")));
		cf_webelementfoundwait(objrepo.getProperty("Password"),20);		
		cf_webelementsendkeys((objrepo.getProperty("Email")), username);
		cf_webelementsendkeys((objrepo.getProperty("Password")), password);
		cf_webelementclick((objrepo.getProperty("Login")));

		Thread.sleep(4000);

	}

	public static void cf_webelementclick(String locator) {
		try {
			
			driver.findElement(By.xpath(locator)).click();
			logger.info("Webelement is clicked" );
		}
		catch (Exception e) {
			logger.info("Webelement is not clicked");
		}
			
				
	}

	public static void cf_webelementsendkeys(String locator, String text) {
		driver.findElement(By.xpath(locator)).sendKeys(text);

	}
	
	public static WebElement cf_webelementfoundwait(String locator,long waitTimeSeconds) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(waitTimeSeconds));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		return element;
	}
	
	
	public static void cf_weblinktextclick(String locator) {
		try {
	driver.findElement(By.linkText(locator)).click();
	logger.info("Weblinktext is clicked");
		}
		catch(Exception e) {
			logger.info("Weblinktext is not clicked");
			
			
		}

	}

}
