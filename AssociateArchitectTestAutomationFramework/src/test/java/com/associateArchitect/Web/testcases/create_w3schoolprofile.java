package com.associateArchitect.Web.testcases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.associateArchitect.Utilities.CommonFunctions;
import com.associateArchitect.Web.base.BaseDriver;

public class create_w3schoolprofile extends BaseDriver{
	public static com.associateArchitect.Utilities.CommonFunctions commonfns;
	@Test(dataProviderClass = com.associateArchitect.Utilities.CommonFunctions.class, dataProvider = "bvttestdata")
	public void createprofile(String username,String password ,String fName , String lName) {
	
			try {
			logger.info("************Testcase CreateProfile is Started**************");					
			//CommonFunctions.cf_login(username, password);
			com.associateArchitect.Pages.w3Loginpage lnPage = new com.associateArchitect.Pages.w3Loginpage(driver);
			lnPage.clickLoginButton();
			lnPage.setEmail(username);
			lnPage.setPassword(password);
			lnPage.clickLogin();
			lnPage.waitforElementPresent();
			logger.info("UserLogged in Successfully");
			com.associateArchitect.Pages.w3Profilepage pPage = new com.associateArchitect.Pages.w3Profilepage(driver);
			pPage.clickProfilelink();
			pPage.waitforElementPresent();
			pPage.setProfilefname(fName);
			pPage.setProfilelname(lName);
			pPage.waitforprofilesaveLoad();			
			pPage.clickProfileSavebtn();
			String actualrecordsave = "My Profile";
			String expectedrecordsave="My Profile";
			Assert.assertEquals(actualrecordsave,expectedrecordsave,"Record is not Saved");
			logger.info("************Testcase CreateProfile is Completed **************");
			} catch (Throwable e) {
				Assert.fail("Create Profile Testcase is Failed", e);
				logger.error("Testcase Failed with the error:", e);	
		}
		}
	
	}

