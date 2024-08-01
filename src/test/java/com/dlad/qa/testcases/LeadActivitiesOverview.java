package com.dlad.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.BaseClassPage;
import com.dlad.qa.pages.SidebarCRM;
import com.dlad.qa.utils.Utils;


public class LeadActivitiesOverview extends BaseClass {

	
	public WebDriver driver;
	SidebarCRM sidebarCRM;
	
    @SuppressWarnings("deprecation")
	@BeforeMethod
    @Parameters("browser")
    public void beforeMethod(@Optional("chrome") String browserName) throws InterruptedException {
    	
        // Load Property
        loadPropertiesFile();
        
        // Parallel Browser Testing
        if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			System.out.println(" ");
			System.out.println("Chrome Browser is Launched");
			
		}if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			System.out.println(" ");
			System.out.println("Edge Browser is Launched");
			
		}if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			System.out.println(" ");
			System.out.println("Firefox Browser is Launched");
			
		}if(browserName.equalsIgnoreCase("safari")){
			driver = new SafariDriver();
			System.out.println("Safari Browser is Launched");
			System.out.println(" ");
		}
				
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGE_LOAD_TIME));
		driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(Utils.SCRIPT_TIME_OUT));
		
		driver.navigate().to(configProp.getProperty("url"));
		
		// Login Properly
		BaseClassPage baseClassPage = new BaseClassPage(driver);
		baseClassPage.EnterTheUserName(configProp.getProperty("validCRMEmailAddress"));
		baseClassPage.EnterThePassword(configProp.getProperty("validCRMPassword"));
		baseClassPage.ClickOnSubmit();
		
		Thread.sleep(3000);
		
		// Sidebar
		sidebarCRM = new SidebarCRM(driver);
		sidebarCRM.LeadListGroup();
		sidebarCRM.LeadActivityOverview();
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
	
	@Test(priority = 2)
	public void verifyDisplayTheLeadAcivityOverviewPageCardNames() {
		
		String totalOwnLeads = driver.findElement(By.xpath("//h3[contains(text(), 'Total Own Leads')]")).getText();
		Assert.assertEquals(totalOwnLeads, "Total Own Leads");
		System.out.println(totalOwnLeads);
		
		String totalActiveLeads = driver.findElement(By.xpath("//h3[contains(text(), 'Total Active Leads')]")).getText();
		Assert.assertEquals(totalActiveLeads, "Total Active Leads");
		System.out.println(totalActiveLeads);

		String totalInactiveLeads = driver.findElement(By.xpath("//h3[contains(text(), 'Total Inactive Leads')]")).getText();
		Assert.assertEquals(totalInactiveLeads, "Total Inactive Leads");
		System.out.println(totalInactiveLeads);

		String totalDeadLeads = driver.findElement(By.xpath("//h3[contains(text(), 'Total Dead Leads')]")).getText();
		Assert.assertEquals(totalDeadLeads, "Total Dead Leads");
		System.out.println(totalDeadLeads);

		String forCurrentQuarter = driver.findElement(By.xpath("//h3[contains(text(), 'For Current Quarter')]")).getText();
		Assert.assertEquals(forCurrentQuarter, "For Current Quarter");
		System.out.println(forCurrentQuarter);

		String forCurrentMonth = driver.findElement(By.xpath("//h3[contains(text(), 'For Current Month')]")).getText();
		Assert.assertEquals(forCurrentMonth, "For Current Month");
		System.out.println(forCurrentMonth);
		
		String forCurrentWeek = driver.findElement(By.xpath("//h3[contains(text(), 'For Current Week')]")).getText();
		Assert.assertEquals(forCurrentWeek, "For Current Week");
		System.out.println(forCurrentWeek);

		String forToday = driver.findElement(By.xpath("//h3[contains(text(), 'For Today')]")).getText();
		Assert.assertEquals(forToday, "For Today");
		System.out.println(forToday);
	}
	
	
	@Test(priority = 3)
	public void verifyDisplayTheLeadActivityOverviewPageCardCount() {

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
