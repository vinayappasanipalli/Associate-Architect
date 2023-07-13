package com.associateArchitect.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.associateArchitect.Utilities.CommonFunctions;

public class w3Profilepage {
	
	WebDriver driver;
	
	By profileLink = By.linkText("Profile");
	//By profileButton = By.name("Profile");
	By profileFName = By.xpath("//input[@id='profile-firstname-input']");
	By profileLName = By.xpath("//input[@id='profile-lastname-input']");
	//By profileUrl = By.xpath("//input[@placeholder='@\"nickname\"']");
	By profileSavebtn = By.xpath("//button[@class='Button_button__F-5UA Button_primary__nC-4W PublicProfile_btn_width__O1pyE']//span[contains(text(),'Save')]");

	public w3Profilepage(WebDriver d)
	{
		driver = d;
	}
	
	public void clickProfilelink() {
		driver.findElement(profileLink).click();
	}

	public void setProfilefname(String fName) {
		driver.findElement(profileFName).sendKeys(fName);

	}
	public void setProfilelname(String lName) {
		driver.findElement(profileLName).sendKeys(lName);

	}
	public void clickProfileSavebtn() {
		driver.findElement(profileSavebtn).click();

	}
	public void waitforElementPresent() {
		CommonFunctions.cf_explicitwaitForElementPresent(driver, profileFName,120); 
	}
	public void waitforprofilesaveLoad() {
		CommonFunctions.cf_explicitwaitForElementPresent(driver, profileSavebtn,120); 
	}

}
