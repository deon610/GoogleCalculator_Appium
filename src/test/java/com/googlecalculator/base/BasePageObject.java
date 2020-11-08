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
	
	public BasePageObject(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		initElements();
	}
	
	private void initElements() {
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(Implicit_Wait)), this);
	}
}
