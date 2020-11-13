package com.googlecalculator.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.googlecalculator.utils.PropertyUtility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass
{
	// Instance of driver that drive the test cases running
	public static AppiumDriver<MobileElement> driver;

	@BeforeTest
	public void initialize() throws MalformedURLException
	{
		DesiredCapabilities desCap = new DesiredCapabilities();
		initializeDesiredCapabilities(desCap);
		String server = PropertyUtility.getProperty("url");
		URL url = new URL(server);
		driver = new AppiumDriver<MobileElement>(url, desCap);
	}

	/**
	 * Method to initialize the required Desired Capabilities
	 *
	 * @param desiredCapabilities Instance of desired Capabilities passed
	 */
	private void initializeDesiredCapabilities(DesiredCapabilities desCap)
	{
		String deviceName = PropertyUtility.getProperty("deviceName");
		String udid = PropertyUtility.getProperty("udid");
		String platformName = PropertyUtility.getProperty("platformName");
		String platformVersion = PropertyUtility.getProperty("platformVersion");
		String appPath = getPath(PropertyUtility.getProperty("app"));
		String appPackage = PropertyUtility.getProperty("appPackage");
		String appActivity = PropertyUtility.getProperty("appActivity");
		String timeout = PropertyUtility.getProperty("implicitWait", "30");

		desCap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		desCap.setCapability(MobileCapabilityType.UDID, udid);
		desCap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		desCap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		desCap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, timeout);
		desCap.setCapability("appPackage", appPackage);
		desCap.setCapability("appActivity", appActivity);
		desCap.setCapability(MobileCapabilityType.APP, appPath);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(final ITestResult result) throws IOException
	{
		String fileName = result.getTestClass().getName() + "_" + result.getName();
		System.out.println("Test Case:" + fileName + " has executed");
	}

	/**
	 * Returning a driver instance in order to take screenshots
	 * @return
	 */
	public static WebDriver getDriverForScreenShot()
	{
		final WebDriver augmentedDriver = new Augmenter().augment(driver);
		return augmentedDriver;
	}
	
	/**
	 * The method is used to get the absolute path of the application
	 * @param appRelativePath
	 * @return
	 */
    private static String getPath(String appRelativePath) {
        File file = new File(appRelativePath);
        return file.getAbsolutePath();
    }

	@AfterTest
	private void tearDown()
	{
		driver.quit();
	}
}
