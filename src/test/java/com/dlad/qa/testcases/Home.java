package com.dlad.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		homePage = new HomePage(driver);
		String verifyHomePage = homePage.homePageHeaderTitle();
		Assert.assertEquals(verifyHomePage, "Leads");
	}
	
	
	@Test(priority = 2)
	public void verifyDisplayTheHomePageCardNames() {
		
		String ownLead = driver.findElement(By.xpath("//h3[contains(text(), 'Own Leads')]")).getText();
		Assert.assertEquals(ownLead, "Own Leads");
		System.out.println(ownLead);
		
		String ownBps = driver.findElement(By.xpath("//h3[contains(text(), 'Own BPs')]")).getText();
		Assert.assertEquals(ownBps, "Own BPs");
		System.out.println(ownBps);

		String todayActivities = driver.findElement(By.xpath("//h3[contains(text(), 'Today Activities')]")).getText();
		Assert.assertEquals(todayActivities, "Today Activities");
		System.out.println(todayActivities);

		String dueActivities = driver.findElement(By.xpath("//h3[contains(text(), 'Due Activities')]")).getText();
		Assert.assertEquals(dueActivities, "Due Activities");
		System.out.println(dueActivities);

	}
	
	@Test(priority = 3)
	public void verifyDisplayTheHomePageCardCounts() {
		
		String ownLead = driver.findElement(By.xpath("//h3[contains(text(), 'Own Leads')]")).getText();
		Assert.assertEquals(ownLead, "Own Leads");
		// Find the element with the label "Own Leads"
        WebElement ownLeadsLabel = driver.findElement(By.xpath("//h3[contains(text(), 'Own Leads')]"));
        // Find the parent div of the label
        WebElement ownLeadParentDiv = ownLeadsLabel.findElement(By.xpath("./ancestor::div[@class='rounded-xl border text-card-foreground shadow bg-cyan-900']"));
        // Find the element containing the count
        WebElement ownLeadCounts = ownLeadParentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold text-white']"));
        // Get the count text
        String ownLeadValue = ownLeadCounts.getText();
        System.out.println(ownLead + ":" + ownLeadValue);
        	
		String ownBps = driver.findElement(By.xpath("//h3[contains(text(), 'Own BPs')]")).getText();
		Assert.assertEquals(ownBps, "Own BPs");
        WebElement ownBpsLabel = driver.findElement(By.xpath("//h3[contains(text(), 'Own BPs')]"));
        WebElement ownBpsParentDiv = ownBpsLabel.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement ownBpsCounts = ownBpsParentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        String ownBpsValue = ownBpsCounts.getText();
        System.out.println(ownBps + ":" + ownBpsValue);
        
		String todayActivities = driver.findElement(By.xpath("//h3[contains(text(), 'Today Activities')]")).getText();
		Assert.assertEquals(todayActivities, "Today Activities");
        WebElement todayActivitiesLabel = driver.findElement(By.xpath("//h3[contains(text(), 'Today Activities')]"));
        WebElement todayActivitiesParentDiv = todayActivitiesLabel.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement todayActivitiesCounts = todayActivitiesParentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        String todayActivitiesValue = todayActivitiesCounts.getText();
        System.out.println(todayActivities + ":" + todayActivitiesValue);
        

		String dueActivities = driver.findElement(By.xpath("//h3[contains(text(), 'Due Activities')]")).getText();
		Assert.assertEquals(dueActivities, "Due Activities");
        WebElement dueActivitiesLabel = driver.findElement(By.xpath("//h3[contains(text(), 'Due Activities')]"));
        WebElement dueActivitiesParentDiv = dueActivitiesLabel.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement dueActivitiesCounts = dueActivitiesParentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        String dueActivitiesValue = dueActivitiesCounts.getText();
        System.out.println(dueActivities + ":" + dueActivitiesValue);

	}

}
