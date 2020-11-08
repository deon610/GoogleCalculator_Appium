package com.googlecalculator.test;

import org.testng.annotations.Test;

import com.googlecalculator.base.BaseClass;
import com.googlecalculator.pageobject.BasicCalculator;

public class SimpleAddition extends BaseClass
{
	@Test
	public void Add7to5() {
		BasicCalculator basCal = new BasicCalculator(driver);
		basCal.verifyTextInButton();
		basCal.verifyAddFor12();
	}
	
}
