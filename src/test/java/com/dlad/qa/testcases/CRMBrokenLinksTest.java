package com.dlad.qa.testcases;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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
        System.out.println("Identified Total URL Links : " + links.size());

        for (WebElement link : links) {
            String linkURL = link.getAttribute("href");
            if (linkURL != null && !linkURL.isEmpty()) { 
                try {
                    URL url = new URL(linkURL);
                    URLConnection urlConnection = url.openConnection();
                    
                    if (urlConnection instanceof HttpURLConnection) {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                        httpURLConnection.setConnectTimeout(5000); 
                        httpURLConnection.connect();
                        int responseCode = httpURLConnection.getResponseCode();
                        if (responseCode == 200) {
                            System.out.println(linkURL + " - " + httpURLConnection.getResponseMessage());
                        } else {
                            System.err.println(linkURL + " - " + responseCode + " - " + httpURLConnection.getResponseMessage());
                        }
                        httpURLConnection.disconnect();
                    } else {
                        System.out.println(linkURL + " - Skipping non-http link");
                    }
                } catch (java.net.SocketTimeoutException e) {
                    System.err.println(linkURL + " - Connection timed out");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
