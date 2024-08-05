package com.dlad.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.BaseClassPage;
import com.dlad.qa.pages.LeadListPage;
import com.dlad.qa.pages.SidebarCRM;
import com.dlad.qa.utils.Utils;

public class LeadListTest extends BaseClass {
	
	public WebDriver driver;
	SidebarCRM sidebarCRM;
	LeadListPage leadListPage;
	
    @SuppressWarnings("deprecation")
	@BeforeMethod
    @Parameters("browser")
    public void beforeMethod(@Optional("chrome") String browserName) throws InterruptedException {
    	
        // Load Property
        loadPropertiesFile();
        
        // Parallel Browser Testing
        if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
			System.out.println("\n" + cap.getBrowserName() + " Browser Version " + cap.getBrowserVersion() + " is Launched!");

		}if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
			System.out.println("\n" + cap.getBrowserName() + " Browser Version " + cap.getBrowserVersion() + " is Launched!");

		}if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
			System.out.println("\n" + cap.getBrowserName() + " Browser Version " + cap.getBrowserVersion() + " is Launched!");

		}if(browserName.equalsIgnoreCase("safari")){
			driver = new SafariDriver();
			Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
			System.out.println("\n" + cap.getBrowserName() + " Browser Version " + cap.getBrowserVersion() + " is Launched!");

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
		Thread.sleep(3000);
	}

	@Test(priority = 5)
	public void verifyTheCreateNewLeadWithoutProvidingAnyFields() {
		
		leadListPage.clickNewLeadFormBtn();
		leadListPage.clickSaveChangesBtn();
		
		String actualLeadNameErroMsg = leadListPage.leadNameErrorMessage();
		Assert.assertEquals(actualLeadNameErroMsg, leadListProp.getProperty("leadNameErrorMessage"));
		
		String actualLeadGroupErroMsg = leadListPage.leadGroupErrorMessage();
		Assert.assertEquals(actualLeadGroupErroMsg, leadListProp.getProperty("leadGroupErrorMessage"));
		
		String actualLeadRatingErroMsg = leadListPage.ratingErrorMessage();
		Assert.assertEquals(actualLeadRatingErroMsg, leadListProp.getProperty("ratingErrorMessage"));
	}
	
	@Test(priority = 6)
	
	public void verifyTheFillAllRequiredLeadMemberDetailsFields() {
		
		leadListPage.clickNewLeadFormBtn();	    
	    String createdLeadName = Utils.nameGenerate();
	    leadListPage.leadNameInputTextField(createdLeadName);
   
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
	
	@Test(priority = 7)
	public void verifyTheCreatedLeadMemberDisplayOnLeadsTable() {
		
		leadListPage.clickNewLeadFormBtn();	    
	    String createdLeadName = Utils.nameGenerate();
	    String createdCompanyName = Utils.companyNameGenerate();
	    leadListPage.leadNameInputTextField(createdLeadName);
		leadListPage.companyNameInputTextField(createdCompanyName);

		leadListPage.clickOnLeadGropDropdownField();
		leadListPage.clickSearchField(leadListProp.getProperty("selectLeadGroupOption"));
		leadListPage.selectDropdownOption();
		leadListPage.clickOnRatingsDropdownField();
		leadListPage.clickSearchField(leadListProp.getProperty("selectRatingsOption"));
		leadListPage.selectDropdownOption();
		leadListPage.clickSaveChangesBtn();

	    String pageHeader = leadListPage.leadPageViewHeaderTitle();
	    Assert.assertEquals(pageHeader, leadListProp.getProperty("verifyLeadViewPageHeader"));
	    
	    leadListPage.clickOnPageBackBtn();
	    
	    String verifyCreatedName = leadListPage.verifyCreatedLeadNameInTableView();
	    Assert.assertEquals(createdLeadName, verifyCreatedName);

	}
	
	@Test(priority = 8)
	
	public void verifyTheNewlyCreatedLeadStatusAndRatingDisplayedOnTheLeadTableView() throws InterruptedException {
		
		leadListPage.clickNewLeadFormBtn();	    
	    String createdLeadName = Utils.nameGenerate();
	    String createdCompanyName = Utils.companyNameGenerate();
	    leadListPage.leadNameInputTextField(createdLeadName);
		leadListPage.companyNameInputTextField(createdCompanyName);

		leadListPage.clickOnLeadGropDropdownField();
		leadListPage.clickSearchField(leadListProp.getProperty("selectLeadGroupOption"));
		leadListPage.selectDropdownOption();
		leadListPage.clickOnRatingsDropdownField();
		leadListPage.clickSearchField(leadListProp.getProperty("selectRatingsOption"));
		leadListPage.selectDropdownOption();
		leadListPage.clickSaveChangesBtn();

	    String pageHeader = leadListPage.leadPageViewHeaderTitle();
	    Assert.assertEquals(pageHeader, leadListProp.getProperty("verifyLeadViewPageHeader"));
	    
	    leadListPage.clickOnPageBackBtn();
	    
	    String actualStatus = leadListProp.getProperty("leadStatus");	    		
	    String verifyNewlyCreatedLeadStatus = leadListPage.verifyLeadStatusInTableView();
	    Assert.assertEquals(verifyNewlyCreatedLeadStatus, actualStatus);
	    
	    String actualRating = leadListProp.getProperty("leadRating");	    		
	    String verifyNewlyCreatedLeadRating = leadListPage.verifyLeadRatingInTableView();
	    Assert.assertEquals(verifyNewlyCreatedLeadRating, actualRating);
	    
	    Thread.sleep(3000);	
	}

	
//	@Test(priority = 9)
//	public void verifyTheFillAllLeadMemberDetailsInLeadViewFields() {
//		
//		
//	}
//	
//	@Test(priority = 10)
//	public void verifyAddedEmailDisplayedOnThedTableView() {
//		
//		
//	}
	
	@Test(priority =11)
	public void cancellButtonNewLeadForm() {
		
		leadListPage.clickNewLeadFormBtn();
		leadListPage.clickOnCancelBtn();
	}
//	
//	@Test(priority = 12)
//	public void verifyAllStatusMethodsDisplayOnLeadsViewTable() {
//		
//		
//	}
//	
//	@Test(priority = 13)
//	public void verifyAllRatingsMethodsDisplayOnLeadsViewTable() {
//		
//		
//	}
	

}
