package com.dlad.qa.testcases;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.LoginPage;
import com.dlad.qa.utils.Utils;

public class LoginTest extends BaseClass {

	public WebDriver driver;
	LoginPage loginPage;
	
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
				
		loginPage = new LoginPage(driver);
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}


	/*
	 * Test Cases
	 */
	
	@Test(priority = 1)
	public void verifyTheUserCanRedirectToTheLoginPage() {

		String actualLoginPageTitle = loginPage.successfullyRedirectsToLoginPage();
		Assert.assertEquals(actualLoginPageTitle, loginProp.getProperty("successfullyRedirectsToLoginPage"));
	}

	@Test(priority = 2)
	public void verifyTheLoginWithValidCredentials() {

		loginPage.loginUserName(loginProp.getProperty("validCRMEmailAddress"));
		loginPage.loginPassword(loginProp.getProperty("validCRMPassword"));
		loginPage.clickOnLoginButton();
		String actualLoginSuccessfulMessage = loginPage.loginSuccessfulMessage();
		Assert.assertEquals(actualLoginSuccessfulMessage, loginProp.getProperty("successfulLoginMessage"));
	}

	@Test(priority = 3, dataProvider = "invalidUsernameAndPasswordDataSet")
	public void verifyloginWithInvalidCredentials(String invalidUserNames, String invalidPasswords) {

		loginPage.loginUserName(invalidUserNames);
		loginPage.loginPassword(invalidUserNames);
		loginPage.clickOnLoginButton();
		String actualLoginFailMessage = loginPage.loginInvalidErrorMessage();
		Assert.assertEquals(actualLoginFailMessage, loginProp.getProperty("invalidLoginMessage"));
	}
	
	@Test(priority = 4, dataProvider = "invalidUsernameDataSet")
	public void verifyloginWithInvalidEmailAndValidPassword(String invalidUserNames) {

		loginPage.loginUserName(invalidUserNames);
		loginPage.loginPassword(loginProp.getProperty("validCRMPassword"));
		loginPage.clickOnLoginButton();
		String actualLoginFailMessage = loginPage.loginInvalidErrorMessage();
		Assert.assertEquals(actualLoginFailMessage, loginProp.getProperty("invalidLoginMessage"));
	}

	
	@Test(priority = 5, dataProvider = "invalidPasswordsDataSet")
	public void verifyloginWithValidEmailAddressAndInValidPassword(String invalidPasswords) {

		loginPage.loginUserName(loginProp.getProperty("validCRMEmailAddress"));
		loginPage.loginPassword(invalidPasswords);
		loginPage.clickOnLoginButton();
		String actualLoginFailMessage = loginPage.loginInvalidErrorMessage();
		Assert.assertEquals(actualLoginFailMessage, loginProp.getProperty("invalidLoginMessage"));
	}

	@Test(priority = 6)
	public void verifyloginWithoutProvidingCredencials() {

		loginPage.loginUserName(" ");
		loginPage.loginPassword(" ");
		loginPage.clickOnLoginButton();
		String actualLoginFailMessage = loginPage.loginInvalidErrorMessage();
		Assert.assertEquals(actualLoginFailMessage, loginProp.getProperty("invalidLoginMessage"));
	}


	@Test(priority = 7)
	public void verifyTheLoginPageForgotPasswordLinkIsWorking() {

		loginPage.clickOnForgotPasswordLink();
		String actualForgotPasswordPage = loginPage.forgotPasswordPageTitle();
		Assert.assertEquals(actualForgotPasswordPage, loginProp.getProperty("forgotPasswordTitle"));
	}

	@Test(priority = 8)
	public void verifyRequiredFieldRedAsteriskMark() {

		boolean usernameFieldRedAsteriskMark = loginPage.usernameFieldRedAsteriskMark();
		Assert.assertTrue(usernameFieldRedAsteriskMark);

		boolean passwordFieldRedAsteriskMark = loginPage.passwordFieldRedAsteriskMark();
		Assert.assertTrue(passwordFieldRedAsteriskMark);
	}


	@Test(priority = 6)
	public void verifyLoginPagePasswordFieldHideAndViewIcon() {
		
		loginPage.clickHideAndViewIcon();
		boolean hideAndViewIcon = loginPage.hideAndViewIconIsDisplayed();
		Assert.assertTrue(hideAndViewIcon);
		
	}
	
	
//	passord view --> Text should be shown 
//	password hide --> Password text should be dot
	
	
//	@Test(priority = 4)
//	public void verifyTheUserCanViewFieldsRequiredMessages() {
//		
//		loginPage.loginUserName(loginProp.getProperty("invalidUsername"));
//		loginPage.loginPassword(loginProp.getProperty("invalidPassword"));
//		loginPage.clickOnLoginButton();
//		String actualLoginFailMessage = loginPage.loginInvalidErrorMessage();
//		Assert.assertEquals(actualLoginFailMessage, loginProp.getProperty("invalidLoginMessage"));
//		
//		loginPage.clearLoginUserName();
//		String actualUsernameRequiredMsg = loginPage.loginUsernameRequiredMessage();
//		Assert.assertEquals(actualUsernameRequiredMsg, loginProp.getProperty("usernameRequiredMessage"));
//		
//		loginPage.loginUserName(loginProp.getProperty("invalidUsername"));
//		loginPage.clearLoginPassword();
//		String actualPasswordRequiredMsg = loginPage.loginPasswordRequiredMessage();
//		Assert.assertEquals(actualPasswordRequiredMsg, loginProp.getProperty("passwordRequiredMessage"));
//		
//	}


	// Create Dataset for Parallel Testing
		@DataProvider(name = "invalidUsernameAndPasswordDataSet")
		public Object[][] invalidUsernameAndPasswordDataSet(){
			
			String[] invalidUserNames = {"malaka123@dlad.io", "dinesh123@dlad.io"};
			String[] invalidPasswords = {"NX@&Qwq23e2", "32eWQQWUDUq&E"};
			
			Object[][] data = new Object[invalidUserNames.length][2];
			
			for(int i = 0; i < invalidUserNames.length; i++) {
				data[i][0] = invalidUserNames[i];
				data[i][1] = invalidPasswords[i];

			}
			return data;
		}
		
		
		@DataProvider(name = "invalidUsernameDataSet")
		public Object[][] invalidUsernameDataSet(){
		    return new Object[][] {
		        {"malaka123@dlad.io"},
		        {"dinesh123@dlad.io"}
		    };
		}
		
		@DataProvider(name = "invalidPasswordsDataSet")
		public Object[][] invalidPasswordsDataSet(){
		    return new Object[][] {
		        {"NX@&Qwq23e2"},
		        {"32eWQQWUDUq&E"}
		    };
		}
	

	}
