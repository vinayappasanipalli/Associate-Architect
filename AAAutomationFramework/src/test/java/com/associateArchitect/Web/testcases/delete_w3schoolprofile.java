package com.associateArchitect.Web.testcases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.associateArchitect.Utilities.CommonFunctions;
import com.associateArchitect.Web.base.BaseDriver;

public class delete_w3schoolprofile extends BaseDriver {
	public static com.associateArchitect.Utilities.CommonFunctions commonfns;
	@Test(dataProviderClass = com.associateArchitect.Utilities.CommonFunctions.class, dataProvider = "bvttestdata")
	public void deleteprofile(String username,String password,String url) {
		
		try {
			logger.info("************Testcase DeleteProfile is Started**************");
			//CommonFunctions.cf_login(username, password);
			String expectedtitle="My learning | W3Schools";
			String actualtitle=driver.getTitle();
			Assert.assertEquals(actualtitle,expectedtitle,"User Logged in Failed.");			 
			CommonFunctions.cf_webelementfoundwait(objrepo.getProperty("upgrade"),50);
			logger.info("UserLogged in Successfully");
			Thread.sleep(6000);
			CommonFunctions.cf_weblinktextclick(objrepo.getProperty("profile"));
			Thread.sleep(6000);
			CommonFunctions.cf_webelementsendkeys((objrepo.getProperty("profile_url")), url);
			Thread.sleep(6000);
			CommonFunctions.cf_webelementclick((objrepo.getProperty("profile_save_button")));
			String actualrecordsave = "Successfully saved public profile";
			String expectedrecordsave="Successfully saved public profile";
			Assert.assertEquals(actualrecordsave,expectedrecordsave,"Profile Verification Failed.");
			Thread.sleep(6000);
			logger.info("************Testcase DeleteProfile is Completed **************");
			} catch (Throwable e) {
			Assert.fail("Delete Profile Testcase is Failed", e);
			logger.error("Testcase Failed with the error:", e);
			//e.printStackTrace();
		}
	
		
		
	}
	
	}

