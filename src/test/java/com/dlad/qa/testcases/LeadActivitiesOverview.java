package com.dlad.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.SidebarCRM;


public class LeadActivitiesOverview extends BaseClass {

	
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
		sidebarCRM = new SidebarCRM(driver);
		sidebarCRM.ActivityGroup();
		sidebarCRM.ActivityOverview();
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/*
	 * Test Cases
	 */
	
	@Test(priority = 1)
	public void verifyTheUserRedirectsToTheActivityOverviewPage() {
		
		String activityOverview = driver.findElement(By.xpath("//div[@class='flex gap-3 items-center']/h1")).getText();
		Assert.assertEquals(activityOverview, "Leads");
	}
	
	
}
