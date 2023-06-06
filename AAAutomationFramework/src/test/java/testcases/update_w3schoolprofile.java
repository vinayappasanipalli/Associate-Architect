package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseDriver;
import utilities.CommonFunctions;
import utilities.ReadTestDataExcel;

public class update_w3schoolprofile extends BaseDriver {
	public static CommonFunctions commonfns;
	@Test(dataProviderClass = ReadTestDataExcel.class, dataProvider = "bvttestdata")
	public void updateprofile(String username,String password,String url) {
		
		try {
			CommonFunctions.cf_login(username, password);
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
			Assert.assertEquals(actualrecordsave,expectedrecordsave);
			Thread.sleep(6000);
			} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
	}

