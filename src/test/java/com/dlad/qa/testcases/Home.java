package com.dlad.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.HomePage;
import com.dlad.qa.pages.SidebarCRM;


public class Home extends BaseClass {

	
	WebDriver driver;
	SidebarCRM sidebarCRM;
	HomePage homePage;
	
	@BeforeMethod
	public void beforeMethod() {

		//	load Property
		loadPropertiesFile();
		driver = initializeBrowser(prop.getProperty("browserName"));
		// Validate Login
		driver = validLogin(loginProp.getProperty("validCRMEmailAddress", "validCRMPassword"));
		
		// Sidebar
		sidebarCRM = new SidebarCRM(driver);
		sidebarCRM.HomeTab();
		
		homePage = new HomePage(driver);

	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/*
	 * Test Cases
	 */
	
	
	@Test(priority = 1)
	public void verifyUserRedirectsToTheHomePage() {
		String verifyHomePage = homePage.homePageHeaderTitle();
		Assert.assertEquals(verifyHomePage, "Leads");
	}


	@Test(priority = 2)
	public void verifyDisplayTheHomePageCardNames() {
		
		String ownLead = homePage.leadLabel();
		Assert.assertEquals(ownLead, "Leads");
		System.out.println(ownLead);
		
		String ownBps = homePage.ownBpsLabel();
		Assert.assertEquals(ownBps, "Own BPs");
		System.out.println(ownBps);

		String todayActivities = homePage.leadActivitiesLabel();
		Assert.assertEquals(todayActivities, "Lead Activities");
		System.out.println(todayActivities);

		String dueActivities = homePage.bpActivitiesLabel();
		Assert.assertEquals(dueActivities, "BP Activities");
		System.out.println(dueActivities);

	}
	
	@Test(priority = 3)
	public void verifyDisplayTheHomePageCardCounts() {
		
		// Verify "Leads" label and count
        String ownLeadsText = homePage.leadLabel();
        Assert.assertEquals(ownLeadsText, "Leads");
        String ownLeadsCount = homePage.getOwnLeadsCount();
        System.out.println(ownLeadsText + ": " + ownLeadsCount);

        // Verify "Own BPs" label and count
        String ownBpsText = homePage.ownBpsLabel();
        Assert.assertEquals(ownBpsText, "Own BPs");
        String ownBpsCount = homePage.getOwnBpsCount();
        System.out.println(ownBpsText + ": " + ownBpsCount);

        // Verify "Lead Activities" label and count
        String leadActivitiesText = homePage.leadActivitiesLabel();
        Assert.assertEquals(leadActivitiesText, "Lead Activities");
        String leadActivitiesCount = homePage.getLeadActivitiesCount();
        System.out.println(leadActivitiesText + ": " + leadActivitiesCount);

        // Verify "BP Activities" label and count
        String bpActivitiesText = homePage.bpActivitiesLabel();
        Assert.assertEquals(bpActivitiesText, "BP Activities");
        String bpActivitiesCount = homePage.getBpActivitiesCount();
        System.out.println(bpActivitiesText + ": " + bpActivitiesCount);

	}

}
