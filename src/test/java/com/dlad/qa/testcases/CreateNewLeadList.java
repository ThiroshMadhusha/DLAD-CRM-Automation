package com.dlad.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;

public class CreateNewLeadList extends BaseClass {
	
	WebDriver driver;
	
	@BeforeMethod
	public void beforeMethod(){

//		load Property
		loadPropertiesFile();
		try {
			driver = initializeBrowser(prop.getProperty("browserName"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/**
	 * From Excell Sheet Data
	 * 
	 * @param Email
	 * @param Password
	 * @throws InterruptedException
	 */
	
	@Test
	public void createNewLeads() throws InterruptedException {
		
		String title = driver.getTitle();
		System.out.println(title);
		
		Thread.sleep(2000);
//		driver.findElement(By.linkText("Quotations")).click();
//		Thread.sleep(3000);
		driver.findElement(By.xpath("//nav[@class='grid gap-1 px-2']/div[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@href='/app/lead/list']")).click();
		Thread.sleep(3000);
				
		
	}
}
