package com.dlad.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.LeadListPage;
import com.dlad.qa.pages.SidebarCRM;
import com.dlad.qa.utils.Utils;

public class LeadList extends BaseClass {
	
	WebDriver driver;
	SidebarCRM sidebarCRM;
	LeadListPage leadListPage;
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

		//	load Property
		loadPropertiesFile();
		driver = initializeBrowser(prop.getProperty("browserName"));
		// Validate Login
		driver = validLogin(loginProp.getProperty("validCRMEmailAddress", "validCRMPassword"));
		
		// Sidebar
		sidebarCRM = new SidebarCRM(driver);
		sidebarCRM.LeadListGroup();
		sidebarCRM.LeadList();
		
		leadListPage = new LeadListPage(driver);
		Thread.sleep(1000);
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/*
	 * Test Cases
	 */

	@Test(priority = 1)
	public void verifyTheUserRedirectsToTheLeadListPage() {
		
		String actualeadListPage = leadListPage.leadPageHeaderTitle();
		Assert.assertEquals(actualeadListPage, leadListProp.getProperty("verifyLeadPageHeader"));
	}
	
	@Test(priority = 2)
	public void verifyTheCreateNewLeadFormOpen() {
		
		leadListPage.clickNewLeadFormBtn();
		String actualForm = leadListPage.verifyOpenNewLeadForm();
		Assert.assertEquals(actualForm, leadListProp.getProperty("verifyNewLeadCreateForm"));
	}
	
	@Test(priority = 3)
	public void verifyTheCreateNewLeadFormCloseIcon() {
		
		leadListPage.clickNewLeadFormBtn();
		boolean leadCloseIcon = leadListPage.closeNewLeadFormIcon();
		Assert.assertTrue(leadCloseIcon);
		leadListPage.closeNewLeadForm();
	}
		
	@Test(priority = 4)
	public void verifyTheCreateNewLeadWithValidData() throws InterruptedException {
		
		leadListPage.clickNewLeadFormBtn();
		leadListPage.leadNameInputTextField(Utils.nameGenerate());
		leadListPage.companyNameInputTextField(Utils.companyNameGenerate());
		leadListPage.clickOnLeadGropDropdownField();
		leadListPage.clickSearchField(leadListProp.getProperty("selectLeadGroupOption"));
		leadListPage.selectDropdownOption();
		leadListPage.clickOnRatingsDropdownField();
		leadListPage.clickSearchField(leadListProp.getProperty("selectRatingsOption"));
		leadListPage.selectDropdownOption();
		leadListPage.clickSaveChangesBtn();
		String pageHeader = leadListPage.leadPageViewHeaderTitle();
		Assert.assertEquals(pageHeader, leadListProp.getProperty("verifyLeadViewPageHeader"));
		
	}
	
	@Test(priority = 5)
	public void verifyTheClickOnCreateButtonWithWithoutFillingData() {
		
		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Save changes')]")).click();
	}
	
	
	@Test(priority = 6)
	public void verifyTheCreateNewLeadWithoutProvidingAnyFields() {
		
		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Save changes')]")).click();
		
		String actualLeadNameErroMsg = driver.findElement(By.xpath("//p[contains(text(),'Lead name is required')]")).getText();
		Assert.assertEquals(actualLeadNameErroMsg, "Lead name is required");
		
		String actualLeadGroupErroMsg = driver.findElement(By.xpath("//p[contains(text(),'Lead group is required')]")).getText();
		Assert.assertEquals(actualLeadGroupErroMsg, "Lead group is required");
		
		String actualLeadRatingErroMsg = driver.findElement(By.xpath("//p[contains(text(),'Rating is required')]")).getText();
		Assert.assertEquals(actualLeadRatingErroMsg, "Rating is required");
	}
	
	@Test(priority = 7)
	public void cancellButtonNewLeadForm() {
		
		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Cancel')]")).click();
	}
	
	@Test(priority = 8)
	public void verifyTheCreatedLeadMemberDisplayOnLeadsTable() throws InterruptedException {
		
	    driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
	    
	    String generatedLeadName = Utils.nameGenerate();
	    String generatedCompanyName = Utils.companyNameGenerate();
	    
	    driver.findElement(By.xpath("//input[@id='lead_name']")).sendKeys(generatedLeadName);
	    driver.findElement(By.xpath("//input[@id='about_company']")).sendKeys(generatedCompanyName);
	    
	    System.out.println("Generated Lead Name: " + generatedLeadName);
	    System.out.println("Generated Company Name: " + generatedCompanyName);
	    
	    driver.findElement(By.xpath("//button[@id='lead_group']")).click();
	    driver.findElement(By.xpath("//div[@class='flex items-center border-b px-3']/input[@placeholder='Search']")).sendKeys("Cafe");
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//div[@class='relative overflow-hidden']/div/div/div/div/div[1]")).click();
	    driver.findElement(By.xpath("//button[@id='rating']")).click();
	    driver.findElement(By.xpath("//div[@class='flex items-center border-b px-3']/input[@placeholder='Search']")).sendKeys("Warm");
	    driver.findElement(By.xpath("//div[@class='relative overflow-hidden']/div/div/div/div/div[1]")).click();
	    driver.findElement(By.xpath("//button[contains(text(),'Save changes')]")).click();
	   
	    String pageHeader = driver.findElement(By.xpath("//div[@class='flex items-center gap-2']/h2[contains(text(),'Lead')]")).getText();
	    Assert.assertEquals(pageHeader, "Lead");
		
	}
	
	@Test(priority = 9)
	public void verifyTheLeadMemberetailsView() {
		
		
	}
	
	@Test(priority = 10)
	public void verifyTheAddingEmailAddressForLeadsTable() {
		
		
	}
}
