package com.dlad.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.HomePage;
import com.dlad.qa.pages.SidebarCRM;


public class HomeTest extends BaseClass {

	
	public WebDriver driver;
	SidebarCRM sidebarCRM;
	HomePage homePage;
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

		//	load Property
		loadPropertiesFile();
		driver = initializeBrowser(configProp.getProperty("browserName"));
		// Validate Login
		driver = validLogin(loginProp.getProperty("validCRMEmailAddress", "validCRMPassword"));
		
		// Sidebar
		sidebarCRM = new SidebarCRM(driver);
		sidebarCRM.HomeTab();
		
		homePage = new HomePage(driver);

		Thread.sleep(2000);
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
		Assert.assertEquals(verifyHomePage, homeProp.getProperty("leadLabel"));
	}


	@Test(priority = 2)
	public void verifyDisplayTheHomePageCardNames() {
		
		String ownLead = homePage.leadLabel();
		Assert.assertEquals(ownLead, homeProp.getProperty("leadLabel"));
		System.out.println(ownLead);
		
		String ownBps = homePage.ownBpsLabel();
		Assert.assertEquals(ownBps, homeProp.getProperty("ownBpsLabel"));
		System.out.println(ownBps);

		String todayActivities = homePage.leadActivitiesLabel();
		Assert.assertEquals(todayActivities, homeProp.getProperty("leadActivitiesLabel"));
		System.out.println(todayActivities);

		String dueActivities = homePage.bpActivitiesLabel();
		Assert.assertEquals(dueActivities, homeProp.getProperty("bpActivitiesLabel"));
		System.out.println(dueActivities);

	}
	
	@Test(priority = 3)
	public void verifyDisplayTheHomePageCardCounts() {
		
		// Verify "Leads" label and count
        String ownLeadsText = homePage.leadLabel();
        Assert.assertEquals(ownLeadsText, homeProp.getProperty("leadLabel"));
        String ownLeadsCount = homePage.getOwnLeadsCount();
        System.out.println(ownLeadsText + ": " + ownLeadsCount);

        // Verify "Own BPs" label and count
        String ownBpsText = homePage.ownBpsLabel();
        Assert.assertEquals(ownBpsText, homeProp.getProperty("ownBpsLabel"));
        String ownBpsCount = homePage.getOwnBpsCount();
        System.out.println(ownBpsText + ": " + ownBpsCount);

        // Verify "Lead Activities" label and count
        String leadActivitiesText = homePage.leadActivitiesLabel();
        Assert.assertEquals(leadActivitiesText, homeProp.getProperty("leadActivitiesLabel"));
        String leadActivitiesCount = homePage.getLeadActivitiesCount();
        System.out.println(leadActivitiesText + ": " + leadActivitiesCount);

        // Verify "BP Activities" label and count
        String bpActivitiesText = homePage.bpActivitiesLabel();
        Assert.assertEquals(bpActivitiesText, homeProp.getProperty("bpActivitiesLabel"));
        String bpActivitiesCount = homePage.getBpActivitiesCount();
        System.out.println(bpActivitiesText + ": " + bpActivitiesCount);

	}

	@Test(priority = 4)
	public void verifyHomePageNavigationLinks() {
		
		homePage.navigateToLeadActivity();
		String actualLeadActivityURl = homeProp.getProperty("LeadActivityURl");
		String expectedLeadActivityURL = driver.getCurrentUrl();
		Assert.assertEquals(actualLeadActivityURl, expectedLeadActivityURL);
		homePage.clickOnHomeBtn();
		
		homePage.navigateToBpActivity();
		String actualBpActivityURl = homeProp.getProperty("BPActivityURl");
		String expectedBpURL = driver.getCurrentUrl();
		Assert.assertEquals(actualBpActivityURl, expectedBpURL);
		homePage.clickOnHomeBtn();
		
		homePage.navigateToBusinessPartner();
		String actualBusinessPartnerURl = homeProp.getProperty("BusinessPartnerURl");
		String expectedBusinessPartnerURL = driver.getCurrentUrl();
		Assert.assertEquals(actualBusinessPartnerURl, expectedBusinessPartnerURL);
		homePage.clickOnHomeBtn();
		
		homePage.navigateToNewOrder();
		String actualSalesNewOrderURl = homeProp.getProperty("SalesNewOrderURl");
		String expectedSalesNewOrderURL = driver.getCurrentUrl();
		Assert.assertEquals(actualSalesNewOrderURl, expectedSalesNewOrderURL);
		homePage.clickOnHomeBtn();
	}

	
	@Test(priority = 5)
	public void verifyHomePageNavigationToupleCardIconsAndLabelDisplayed() {
		
		boolean leadActivityIcon = homePage.leadActivityIconSVG();
		Assert.assertTrue(leadActivityIcon);
		String expectedleadActivityLabel = homeProp.getProperty("navigatioNToupleCardLeadActivityLabelName");
		String actualLeadActivityLabel = homePage.navigatioNToupleCardLeadActivityLabel();
		Assert.assertEquals(expectedleadActivityLabel, actualLeadActivityLabel);
		
		boolean bpActivityIcon = homePage.bpActivityIconSVG();
		Assert.assertTrue(bpActivityIcon);
		String expectedBpActivityLabel = homeProp.getProperty("navigatioNToupleCardBpActivityLabelName");
		String actualBpActivityLabel = homePage.navigatioNToupleCardBpActivityLabel();
		Assert.assertEquals(expectedBpActivityLabel, actualBpActivityLabel);
		
		boolean businessPartnerIcon = homePage.businessPartnerIconSVG();
		Assert.assertTrue(businessPartnerIcon);
		String expectedBusinessPartnerLabel = homeProp.getProperty("navigatioNToupleCardBusinessPartnerLabelName");
		String actualBusinessPartnerLabel = homePage.navigatioNToupleCardBusinessPartnerLabel();
		Assert.assertEquals(expectedBusinessPartnerLabel, actualBusinessPartnerLabel);
		
		boolean salesNewOrderIcon = homePage.salesNewOrderIconSVG();
		Assert.assertTrue(salesNewOrderIcon);
		String expectedSalesNewOrderLabel = homeProp.getProperty("navigatioNToupleCardNewOrderLabelName");
		String actualSalesNewOrderLabel = homePage.navigatioNToupleCardNewOrderLabel();
		Assert.assertEquals(expectedSalesNewOrderLabel, actualSalesNewOrderLabel);
		
	}
}
