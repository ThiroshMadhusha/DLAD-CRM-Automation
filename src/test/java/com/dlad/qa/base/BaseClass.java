package com.dlad.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.dlad.qa.pages.BaseClassPage;
import com.dlad.qa.utils.Utils;

public class BaseClass {
	
//	Create Reusable Method
	
	WebDriver driver;
	public Properties configProp;
	public Properties loginProp;
	public Properties homeProp;
	public Properties leadListProp;
	public Properties leadActivityProp;
	public Properties leadActivityOverviewProp;
	public Properties bpActivityProp;
	public Properties bpProp;
	public Properties salesOrderProp;
	
	
//	Create Load Properties File for Config Main before Browser Initialize
	public void loadPropertiesFile(){
		
		// Main Properties
		configProp = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dlad\\qa\\config\\config.properties");
		
		FileInputStream fils;
		try {
			fils = new FileInputStream(propFile);
			configProp.load(fils);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Login Page Properties
		loginProp = new Properties();
		File loginPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\dlad\\qa\\testdataconfig\\login_config.properties");
		FileInputStream loginFils;
		try {
			loginFils = new FileInputStream(loginPropFile);
			loginProp.load(loginFils);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Home Page Properties
		homeProp = new Properties();
		File homePropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\dlad\\qa\\testdataconfig\\home_config.properties");
		FileInputStream homeFils;
		try {
			homeFils = new FileInputStream(homePropFile);
			homeProp.load(homeFils);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Lead List Page Properties
		leadListProp = new Properties();
		File leadListPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\dlad\\qa\\testdataconfig\\lead_list_config.properties");
		FileInputStream leadListFils;
		try {
			leadListFils = new FileInputStream(leadListPropFile);
			leadListProp.load(leadListFils);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Lead Activity Overview Page Properties
		leadActivityOverviewProp = new Properties();
		File leadActivityOverviewPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\dlad\\qa\\testdataconfig\\lead_activity_overview_config.properties");
		FileInputStream leadActivityOverviewFils;
		try {
			leadActivityOverviewFils = new FileInputStream(leadActivityOverviewPropFile);
			leadActivityOverviewProp.load(leadActivityOverviewFils);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Lead Activity Page Properties
		leadActivityProp = new Properties();
		File leadActivityPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\dlad\\qa\\testdataconfig\\lead_activity_config.properties");
		FileInputStream leadActivityFils;
		try {
			leadActivityFils = new FileInputStream(leadActivityPropFile);
			leadActivityProp.load(leadActivityFils);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// BP Activity Page Properties
		bpActivityProp = new Properties();
		File bpActivityPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\dlad\\qa\\testdataconfig\\bp_activity_config.properties");
		FileInputStream bpActivityFils;
		try {
			bpActivityFils = new FileInputStream(bpActivityPropFile);
			bpActivityProp.load(bpActivityFils);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Business Partner Page Properties
		bpProp = new Properties();
		File bpPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\dlad\\qa\\testdataconfig\\business_partners_config.properties");
		FileInputStream bpFils;
		try {
			bpFils = new FileInputStream(bpPropFile);
			bpProp.load(bpFils);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Sales Order Page Properties
		salesOrderProp = new Properties();
		File salesOrderPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\dlad\\qa\\testdataconfig\\sales_order_config.properties");
		FileInputStream salesOrderFils;
		try {
			salesOrderFils = new FileInputStream(salesOrderPropFile);
			salesOrderProp.load(salesOrderFils);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	Initializing the Browser
	
	@SuppressWarnings("deprecation")
	public WebDriver initializeBrowser(String browserName) {			
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("safari")){
			driver = new SafariDriver();
			
		}else {
			System.out.println("Browser Not Found... Try Again!");

		}
				
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGE_LOAD_TIME));
		driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(Utils.SCRIPT_TIME_OUT));
		
		driver.navigate().to(configProp.getProperty("url"));
		
		return driver;
	}
	
	// Verify Login
	
	public WebDriver validLogin(String string) {
		
		BaseClassPage baseClassPage = new BaseClassPage(driver);
		baseClassPage.EnterTheUserName(configProp.getProperty("validCRMEmailAddress"));
		baseClassPage.EnterThePassword(configProp.getProperty("validCRMPassword"));
		baseClassPage.ClickOnSubmit();
		
		return driver;
	
	}

}
