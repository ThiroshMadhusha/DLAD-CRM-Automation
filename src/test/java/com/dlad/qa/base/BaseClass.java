package com.dlad.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

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
	public Properties forgotPasswordProp;
	
	
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
			e.printStackTrace();
		}
		
		// Forgot Password Page Properties
		forgotPasswordProp = new Properties();
		File forgotPasswordPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\dlad\\qa\\testdataconfig\\forgotpassword_config.properties");
		FileInputStream forgotPasswordPropFils;
		try {
			forgotPasswordPropFils = new FileInputStream(forgotPasswordPropFile);
			forgotPasswordProp.load(forgotPasswordPropFils);

		} catch (IOException e) {
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
			e.printStackTrace();
		}

	}
}
