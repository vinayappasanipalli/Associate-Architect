package com.associateArchitect.Cucumber.base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.associateArchitect.Utilities.CommonFunctions;
import com.associateArchitect.Utilities.CommonFunctions.FrameworkConfig;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseDriver_Cucumber {
	public static WebDriver driver;
	public static Properties appconfig = new Properties();
	public static Properties objrepo = new Properties();
	public static Properties keywordlibrary = new Properties();
	public static FileReader configfile;
	public static FileReader objrepofile;
	public static Logger logger;	
	public static com.associateArchitect.Utilities.CommonFunctions.FrameworkConfig config;  

	
}
	