package com.dlad.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.LeadActivityOverviewPage;
import com.dlad.qa.pages.SidebarCRM;


public class LeadActivitiesOverview extends BaseClass {

	
	public WebDriver driver;
	SidebarCRM sidebarCRM;
	LeadActivityOverviewPage leadActivityOverviewPage;
	 
	@BeforeMethod
	public void beforeMethod() throws InterruptedException{

		//	load Property
		loadPropertiesFile();
		driver = initializeBrowser(configProp.getProperty("browserName"));
		// Validate Login
		driver = validLogin(loginProp.getProperty("validCRMEmailAddress", "validCRMPassword"));
		
		// Sidebar
		sidebarCRM = new SidebarCRM(driver);
		sidebarCRM.LeadListGroup();
		sidebarCRM.LeadActivityOverview();
		
		// Assign Lead Overview Page
		leadActivityOverviewPage = new LeadActivityOverviewPage(driver);
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/*
	 * Test Cases
	 */
	
	@Test(priority = 1)
	public void verifTheUserRedirectsToTheLeadsActivityOverviewPage() {
		
		String activityOverview = leadActivityOverviewPage.LeadActivityPageTitle();
		Assert.assertEquals(activityOverview, leadActivityOverviewProp.getProperty("leadActivityHeaderTitle"));
	}
	
	@Test(priority = 2)
	public void verifyDisplayTheLeadAcivityOverviewPageCardNames() {
		
		String DeadLeads = leadActivityOverviewPage.DeadLabel();
		Assert.assertEquals(DeadLeads, leadActivityOverviewProp.getProperty("deadStatusLabel"));
		
		String ConvertedLeads = leadActivityOverviewPage.ConvertedLabel();
		Assert.assertEquals(ConvertedLeads, leadActivityOverviewProp.getProperty("convertedStatusLabel"));

		String AccountApplicationReceiveLeads = leadActivityOverviewPage.AccountApplicationReceiveLabel();
		Assert.assertEquals(AccountApplicationReceiveLeads, leadActivityOverviewProp.getProperty("accountApplicationReceiveStatusLabel"));

		String PricingLeads = leadActivityOverviewPage.PricingLabel();
		Assert.assertEquals(PricingLeads, leadActivityOverviewProp.getProperty("pricingStatusLabel"));

		String AccountApplicationSentLabelLeads = leadActivityOverviewPage.AccountApplicationSentLabel();
		Assert.assertEquals(AccountApplicationSentLabelLeads, leadActivityOverviewProp.getProperty("acountApplicationSentStatusLabel"));

		String SamplingLabelLeads = leadActivityOverviewPage.SamplingLabel();
		Assert.assertEquals(SamplingLabelLeads, leadActivityOverviewProp.getProperty("samplingStatusLabel"));
		
		String ContactMadeLabelLeads = leadActivityOverviewPage.ContactMadeLabel();
		Assert.assertEquals(ContactMadeLabelLeads, leadActivityOverviewProp.getProperty("contactMadeStatusLabel"));

		String NewLabelLeads = leadActivityOverviewPage.NewLabel();
		Assert.assertEquals(NewLabelLeads, leadActivityOverviewProp.getProperty("newStatusLabel"));
	}
	
	
	@Test(priority = 3)
	public void verifyDisplayTheLeadActivityOverviewPageCardCount() {

		// Verify "Leads" label and count
//        String ownLeadsText = homePage.leadLabel();
//        Assert.assertEquals(ownLeadsText, homeProp.getProperty("leadLabel"));
//        String ownLeadsCount = homePage.getOwnLeadsCount();
//        System.out.println(ownLeadsText + ": " + ownLeadsCount);
        
		String totalOwnLeads = driver.findElement(By.xpath("//h3[contains(text(), 'Total Own Leads')]")).getText();
		Assert.assertEquals(totalOwnLeads, "Total Own Leads");
        WebElement totalOwnLeadsLabel = driver.findElement(By.xpath("//h3[contains(text(), 'Total Own Leads')]"));
        WebElement totalOwnLeadsParentDiv = totalOwnLeadsLabel.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement totalOwnLeadsCounts = totalOwnLeadsParentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        String totalOwnLeadsValue = totalOwnLeadsCounts.getText();
        System.out.println(totalOwnLeads + ":" + totalOwnLeadsValue);
        
		String totalActiveLeads = driver.findElement(By.xpath("//h3[contains(text(), 'Total Active Leads')]")).getText();
		Assert.assertEquals(totalActiveLeads, "Total Active Leads");
        WebElement totalActiveLeadsLabel = driver.findElement(By.xpath("//h3[contains(text(), 'Total Active Leads')]"));
        WebElement totalActiveLeadsParentDiv = totalActiveLeadsLabel.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement totalActiveLeadsCounts = totalActiveLeadsParentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        String totalActiveLeadsValue = totalActiveLeadsCounts.getText();
        System.out.println(totalActiveLeads + ":" + totalActiveLeadsValue);
        
		String totalInactiveLeads = driver.findElement(By.xpath("//h3[contains(text(), 'Total Inactive Leads')]")).getText();
		Assert.assertEquals(totalInactiveLeads, "Total Inactive Leads");
        WebElement totalInactiveLeadsLabel = driver.findElement(By.xpath("//h3[contains(text(), 'Total Inactive Leads')]"));
        WebElement totalInactiveLeadsParentDiv = totalInactiveLeadsLabel.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement totalInactiveLeadsCounts = totalInactiveLeadsParentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        String totalInactiveLeadsValue = totalInactiveLeadsCounts.getText();
        System.out.println(totalInactiveLeads + ":" + totalInactiveLeadsValue);
        
		String totalDeadLeads = driver.findElement(By.xpath("//h3[contains(text(), 'Total Dead Leads')]")).getText();
		Assert.assertEquals(totalDeadLeads, "Total Dead Leads");
        WebElement totalDeadLeadsLabel = driver.findElement(By.xpath("//h3[contains(text(), 'Total Dead Leads')]"));
        WebElement totalDeadLeadsParentDiv = totalDeadLeadsLabel.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement totalDeadLeadsCounts = totalDeadLeadsParentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        String totalDeadLeadsValue = totalDeadLeadsCounts.getText();
        System.out.println(totalDeadLeads + ":" + totalDeadLeadsValue);
        
		String forCurrentQuarter = driver.findElement(By.xpath("//h3[contains(text(), 'For Current Quarter')]")).getText();
		Assert.assertEquals(forCurrentQuarter, "For Current Quarter");
        WebElement forCurrentQuarterLabel = driver.findElement(By.xpath("//h3[contains(text(), 'For Current Quarter')]"));
        WebElement forCurrentQuarterParentDiv = forCurrentQuarterLabel.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement forCurrentQuarterCounts = forCurrentQuarterParentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        String forCurrentQuarterValue = forCurrentQuarterCounts.getText();
        System.out.println(forCurrentQuarter + ":" + forCurrentQuarterValue);
        
		String forCurrentMonth = driver.findElement(By.xpath("//h3[contains(text(), 'For Current Month')]")).getText();
		Assert.assertEquals(forCurrentMonth, "For Current Month");
        WebElement forCurrentMonthLabel = driver.findElement(By.xpath("//h3[contains(text(), 'For Current Month')]"));
        WebElement forCurrentMonthParentDiv = forCurrentMonthLabel.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement forCurrentMonthCounts = forCurrentMonthParentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        String forCurrentMonthValue = forCurrentMonthCounts.getText();
        System.out.println(forCurrentMonth + ":" + forCurrentMonthValue);
        
		String forCurrentWeek = driver.findElement(By.xpath("//h3[contains(text(), 'For Current Week')]")).getText();
		Assert.assertEquals(forCurrentWeek, "For Current Week");
        WebElement forCurrentWeekLabel = driver.findElement(By.xpath("//h3[contains(text(), 'For Current Week')]"));
        WebElement forCurrentWeekParentDiv = forCurrentWeekLabel.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement forCurrentWeekCounts = forCurrentWeekParentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        String forCurrentWeekValue = forCurrentWeekCounts.getText();
        System.out.println(forCurrentWeek + ":" + forCurrentWeekValue);
        
		String forToday = driver.findElement(By.xpath("//h3[contains(text(), 'For Today')]")).getText();
		Assert.assertEquals(forToday, "For Today");
        WebElement forTodayLabel = driver.findElement(By.xpath("//h3[contains(text(), 'For Today')]"));
        WebElement forTodayParentDiv = forTodayLabel.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement forTodayCounts = forTodayParentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        String forTodayValue = forTodayCounts.getText();
        System.out.println(forToday + ":" + forTodayValue);
        
        
	}
}
