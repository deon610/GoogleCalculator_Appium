package com.googlecalculator.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.googlecalculator.base.BaseClass;

public class ScreenshotUtility implements ITestListener
{

	public void onTestStart(ITestResult result)
	{
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result)
	{
		try
		{
			getScreenshot(result);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result)
	{
		try
		{
			getScreenshot(result);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result)
	{
		// TODO Auto-generated method stub
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result)
	{
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context)
	{
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context)
	{
		// TODO Auto-generated method stub
	}

	//Method to capture the screenshot
	public void getScreenshot(ITestResult result) throws IOException
	{

		final WebDriver driver = BaseClass.getDriverForScreenShot();
		DateFormat date = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		String testName =  result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
		String destination = "";
		
		//Setting up destination folder to store the screenshots depending upon 
		//the success or failure of the test case
		if (result.getStatus() == ITestResult.SUCCESS)
		{
			destination = "screenshots/Success";
		}
		else if (result.getStatus() == ITestResult.FAILURE)
		{
			destination = "screenshots/Failures";
		}
		
		//Checking if the directory exists; Creates directory if it doesn't exist
		File dir = new File(destination);
		if (!dir.exists())
		{
			dir.mkdirs();
		}
		
		//Setting up the name of screenshot image using the test name and date of execution
		String fileName = testName + " - " + date.format(new Date()) + ".png";
		
		// Capturing the screenshot
		File screenshot = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(dir + "/" + fileName));
	}

}
