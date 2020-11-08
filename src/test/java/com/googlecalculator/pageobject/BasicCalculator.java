package com.googlecalculator.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.googlecalculator.base.BasePageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class BasicCalculator extends BasePageObject
{
	public BasicCalculator(AppiumDriver<MobileElement> driver)
	{
		super(driver);
	}
	
	@AndroidFindBy(id="com.google.android.calculator:id/digit_5")
	AndroidElement digit5;
	
	@AndroidFindBy(id="com.google.android.calculator:id/digit_7")
	AndroidElement digit7;
	
	@AndroidFindBy(id="com.google.android.calculator:id/op_add")
	AndroidElement opp_add;
	
	@AndroidFindBy(id="com.google.android.calculator:id/eq")
	AndroidElement equals;
	
	//Generic Function to verify the text in the given button
	private void verifyTextInButton(AndroidElement ele, String expecButtonText) {
		Assert.assertEquals(ele.getText(), expecButtonText);
	}
	
	//Verify texts within the boxes of the numbers and operations
	public void verifyTextInButton() {
		verifyTextInButton(digit5,"5");
		verifyTextInButton(digit7,"7");
		verifyTextInButton(opp_add,"+");
		verifyTextInButton(equals,"=");
	}
	
	//verify addition operations for the number 7 and 12
	public void verifyAddFor12() {
		digit5.click();
		opp_add.click();
		digit7.click();
		equals.click();
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.numberOfElementsToBe(By.id("com.google.android.calculator:id/result_final"), 1));
		MobileElement answer = (MobileElement) driver.findElement(By.id("com.google.android.calculator:id/result_final"));
		Assert.assertEquals(answer.getText(), "12");
	}
	
}
