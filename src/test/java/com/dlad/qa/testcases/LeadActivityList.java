package com.dlad.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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


public class LeadActivityList extends BaseClass {
	
	
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
		sidebarCRM.LeadActivityList();
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/*
	 * Test Cases
	 */
	
	@Test(priority = 1)
	public void verifyTheUserRedirectsToTheLeadActivityListPage() {
		
		String actualeadActivityListPage = driver.findElement(By.xpath("//div[@class='flex gap-3 items-center']/h1[contains(text(),'Lead Activities')]")).getText();
		Assert.assertEquals(actualeadActivityListPage, "Lead Activities");
	}
	
	@Test(priority = 2)
	public void verifyTheCreatenewLeadActivityListFormOpen() {

		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		String actualLeadActivityForm = driver.findElement(By.xpath("//div[@class='flex flex-col space-y-1.5 text-center sm:text-left']/h2")).getText();
		Assert.assertEquals(actualLeadActivityForm, "Create New Lead Activity", "Error Activity Form Opening...!");
	}
	
	@Test(priority = 3)
	public void verifyCreateNewActivityLeadsCloseButton() {
		
		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		boolean leadActivityCloseIcon = driver.findElement(By.xpath("//button[@type='button']/*[@class='h-4 w-4']")).isDisplayed();
		Assert.assertTrue(leadActivityCloseIcon);
		driver.findElement(By.xpath("//button[@type='button']/*[@class='h-4 w-4']")).click();
	}
	
	
}
