package com.associateArchitect.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.associateArchitect.Utilities.CommonFunctions;

public class w3Loginpage {
	
	WebDriver driver;
	
	By loginButton = By.xpath("//a[@id='w3loginbtn']");
	By email = By.xpath("//input[@id='modalusername']");
	By loginPassword = By.xpath("//input[@id='current-password']");
	By login = By.xpath("//span[normalize-space()='Log in']");
	By myLearning = By.xpath("//a[@class='_a7DAA _aKDOu']");
	
	public w3Loginpage(WebDriver d)
	{
		driver = d;
	}
	
	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}

	public void setEmail(String username) {
		driver.findElement(email).sendKeys(username);

	}
	public void setPassword(String password) {
		driver.findElement(loginPassword).sendKeys(password);

	}
	public void clickLogin() {
		driver.findElement(login).click();
	}
public void waitforElementPresent() {
	CommonFunctions.cf_explicitwaitForElementPresent(driver, myLearning,120); 
}
}
