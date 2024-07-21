package com.dlad.qa.testcases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.HomePage;
import com.dlad.qa.pages.SidebarCRM;

public class CRMBrokenLinksTest extends BaseClass {

    public WebDriver driver;
    SidebarCRM sidebarCRM;
    HomePage homePage;

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {

        // Load Property
        loadPropertiesFile();
        driver = initializeBrowser(configProp.getProperty("browserName"));
        // Validate Login
        driver = validLogin(loginProp.getProperty("validCRMEmailAddress", "validCRMPassword"));

        Thread.sleep(2000);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
    
    @Test(priority = 1)
	public void verifyTheBrokenLinks() {
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		for(WebElement link : links) {
			//get the url of the link using "href"
			String url = link.getAttribute("href");
			
			System.out.println("----------------------------------------");
			System.out.println(" ");
			
			//check this url is blank or not
			if(url == null || url.isEmpty()) {
				System.out.println("URL is Empty.");
				continue;
				//continue mean stop and go to next url. because empty urls cannot get responce
			}
			//retrive the response code
			try {
				HttpURLConnection huc = (HttpURLConnection)(new URL(url).openConnection());
				
				huc.connect();
				if(huc.getResponseCode() >= 400) {
					System.out.println(url + " is broken!");
				}else {
					System.out.println(url + " is valid!");
				}
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

}
