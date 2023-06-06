package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import base.BaseDriver;
import utilities.CommonFunctions;
import utilities.ReadTestDataExcel;

public class LoginToW3School extends BaseDriver { 
	
	//public static CommonFunctions commonfns;
	
	@Test(dataProviderClass=ReadTestDataExcel.class,dataProvider="bvttestdata")
	public static void W3schoollogin(String username,String password) throws Throwable {
		logger.info("Test Case Started");
        driver.findElement(By.xpath(objrepo.getProperty("Login_Button"))).click();     
		driver.findElement(By.xpath(objrepo.getProperty("Email"))).sendKeys(username);
		driver.findElement(By.xpath(objrepo.getProperty("Password"))).sendKeys(password);
		driver.findElement(By.xpath(objrepo.getProperty("Login"))).click();
		boolean loginbtnclicked = driver.findElement(By.xpath(objrepo.getProperty("Login"))).isSelected();
		logger.info("Login Button Clicked Successfully" + loginbtnclicked);
		Thread.sleep(4000);
					
	}



@AfterClass
public void tearDown() {
	logger.info("*******TC#4 is Finished******");
}
}

