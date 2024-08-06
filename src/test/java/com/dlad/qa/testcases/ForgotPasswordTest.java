package com.dlad.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.ForgotPasswordPage;
import com.dlad.qa.pages.HomePage;
import com.dlad.qa.pages.SidebarCRM;
import com.dlad.qa.utils.Utils;

public class ForgotPasswordTest extends BaseClass {

	public WebDriver driver;
	SidebarCRM sidebarCRM;
	HomePage homePage;
	ForgotPasswordPage forgotPasswordPage;
    
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
		
		forgotPasswordPage = new ForgotPasswordPage(driver);
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/*
	 * Test Cases
	 */
	
	@Test
	public void verifyForgotPasswordLinkIsDisplayed() throws InterruptedException {
		
		forgotPasswordPage.forgotPasswordLink();
		String forgotPasswordLinkName = forgotPasswordPage.forgotPasswordLinkText();
		Assert.assertEquals(forgotPasswordLinkName, forgotPasswordProp.getProperty("forgotPasswordLinkText"));
	}
	
	@Test
	public void verifyForgotPasswordLinkIsClickable() {
		
		forgotPasswordPage.forgotPasswordLinkClick();
		String forgotPasswordPageName = forgotPasswordPage.forgotPasswordPageHeader();
		Assert.assertEquals(forgotPasswordPageName, forgotPasswordProp.getProperty("forgotPasswordPageHeader"));
	}
	
	@Test
	public void verifyForgotPasswordEmailSendSuccessfuly() {
		
		forgotPasswordPage.forgotPasswordLinkClick();
		forgotPasswordPage.forgotPasswordEmailTextField(forgotPasswordProp.getProperty("forgotPasswordEmail"));
		forgotPasswordPage.clickOnSubmitButton();
		String actualSuccessfulMessage = forgotPasswordPage.passwordResetSuccessfulMessage();
		Assert.assertEquals(actualSuccessfulMessage, forgotPasswordProp.getProperty("resetPasswordSuccessMessage"));
	}
	
	@Test(dataProvider = "invalidUsernameDataSet")
	public void verifyForgotPasswordInvalidEmailSendErrorMessage(String invalidEmailAddress) {
		
		forgotPasswordPage.forgotPasswordLinkClick();
		forgotPasswordPage.forgotPasswordEmailTextField(invalidEmailAddress);
		forgotPasswordPage.clickOnSubmitButton();
		String actualSuccessfulMessage = forgotPasswordPage.passwordResetErrorMessage();
		Assert.assertEquals(actualSuccessfulMessage, forgotPasswordProp.getProperty("resetPasswordErrorMessage"));
	}
	

	// Create Dataset for Parallel Testing	
		
	@DataProvider(name = "invalidUsernameDataSet")
	public Object[][] invalidUsernameDataSet(){
		return new Object[][] {
			{"malaka123@dlad.io"},
		    {"dinesh123@dlad.io"}
		};
	}
	
}
