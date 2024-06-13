package com.dlad.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.SidebarCRM;

public class Calander extends BaseClass {

	
	WebDriver driver;
	SidebarCRM sidebarCRM;
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException{

		//	load Property
		loadPropertiesFile();
		driver = initializeBrowser(prop.getProperty("browserName"));
		// Validate Login
		driver = validLogin(loginProp.getProperty("validCRMEmailAddress", "validCRMPassword"));
		
		// Sidebar
		
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/*
	 * Test Cases
	 */
	
}
