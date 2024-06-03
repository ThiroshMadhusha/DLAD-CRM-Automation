package com.dlad.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.dlad.qa.utils.Utils;

public class BaseClass {
	
//	Create Reusable Method
	
	WebDriver driver;
	public Properties prop;
	public Properties loginProp;
	
//	Create Load Properties File for Config Main before Browser Initialize
	public void loadPropertiesFile(){
		
		// Main Properties
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dlad\\qa\\config\\config.properties");
		
		FileInputStream fils;
		try {
			fils = new FileInputStream(propFile);
			prop.load(fils);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Login Properties
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
		
	}
	
//	Initializing the Browser
	
	public WebDriver initializeBrowser(String browserName) throws InterruptedException {			
		
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
		
		driver.get(prop.getProperty("url"));
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(loginProp.getProperty("validCRMEmailAddress"));
		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(loginProp.getProperty("validCRMPassword"));
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		return driver;
	}

}
