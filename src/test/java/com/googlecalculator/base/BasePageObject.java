package com.googlecalculator.base;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePageObject
{
	public static final int Implicit_Wait = 10;
	protected final AppiumDriver<MobileElement> driver;
	
	/**
	  * A base page object class that is used to initialize the page object elements
     *
     * The page object model is used where we separate out the classes as per the screens
     * present in the application. The specific page objects and functions are kept in 
     * screen with intention to manage the maintainability
     *
     * The AppiumFieldDecorator class used within the page factory allows us to conveniently
     * create an Android Element object using the @AndroidFindBy annotation within the pages extending this class.
     *
     * @param 	driver	The instance of appium driver that we are passing to this class
     *  from the page class extending it. This driver is initialized in the Base class before
     *  a test represented by the testNG annotation
     */
	public BasePageObject(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		initElements();
	}
	
	private void initElements() {
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(Implicit_Wait)), this);
	}
}
