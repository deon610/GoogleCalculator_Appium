package com.googlecalculator.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass
{
	public static AppiumDriver<MobileElement> driver;
	public static Properties prop;
	
	public BaseClass() {
		prop = new Properties();
		String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
		String propertiesPath = currentPath + "\\src\\test\\resources\\config.properties";
		FileInputStream fis=null;;
		try
		{
			fis = new FileInputStream(propertiesPath);
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			prop.load(fis);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@BeforeTest
	public void initialize() throws MalformedURLException {
		String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
		String deviceName = prop.getProperty("deviceName");
		String udid = prop.getProperty("udid");
		String platformName = prop.getProperty("platformName");
		String platformVersion = prop.getProperty("platformVersion");
		String timeout = prop.getProperty("implicitwait");
		String appPath = currentPath+ "\\src\\test\\resources\\"+ prop.getProperty("app");
		String appPackage = prop.getProperty("appPackage");
		String appActivity = prop.getProperty("appActivity");
		String server = prop.getProperty("url");
		
		DesiredCapabilities desCap = new DesiredCapabilities();
		desCap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		desCap.setCapability(MobileCapabilityType.UDID, udid);
		desCap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		desCap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		desCap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, timeout);
		desCap.setCapability("appPackage", appPackage);
		desCap.setCapability("appActivity", appActivity);
		desCap.setCapability(MobileCapabilityType.APP, appPath);
		
		URL url = new URL(server);
		driver = new AppiumDriver<MobileElement>(url,desCap);
	}
	
	  @AfterMethod(alwaysRun = true)
	    public void afterMethod(final ITestResult result) throws IOException {
	        String fileName = result.getTestClass().getName() + "_" + result.getName();
	        System.out.println("Test Case:" + fileName + " has executed");
	    }
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
