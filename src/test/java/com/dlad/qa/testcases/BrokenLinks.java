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

public class BrokenLinks extends BaseClass {

    public WebDriver driver;
    SidebarCRM sidebarCRM;
    HomePage homePage;

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {

        // Load Property
        loadPropertiesFile();
        driver = initializeBrowser(configProp.getProperty("browserName"));
        // Validate Login
        sidebarCRM = new SidebarCRM(driver);
        driver = validLogin(loginProp.getProperty("validCRMEmailAddress", "validCRMPassword"));

        Thread.sleep(2000);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void verifyHomePageBrokenLinks() throws InterruptedException {
        sidebarCRM.BusinessPartnersGroup();
        sidebarCRM.HomeTab();
        checkLinksOnCurrentPage(driver);
    }

    @Test
    public void verifyBPActivityOverviewPageBrokenLinks() throws InterruptedException {
    	sidebarCRM.BusinessPartnersGroup();
    	sidebarCRM.BusinessPartnersOverview();
        checkLinksOnCurrentPage(driver);
    }

    @Test
    public void verifyBPActivitiesPageBrokenLinks() throws InterruptedException {
    	sidebarCRM.BusinessPartnersGroup();
    	sidebarCRM.BPsActivityList();
        checkLinksOnCurrentPage(driver);
    }

    @Test
    public void verifyBusinessPartnersFullListPageBrokenLinks() throws InterruptedException {
    	sidebarCRM.BusinessPartnersGroup();
    	sidebarCRM.BusinessPartnerFullList();
        checkLinksOnCurrentPage(driver);
    }

//    @Test
//    public void checkActivitiesLinks() throws InterruptedException {
//    	sidebarCRM.MyOwnLeadList();
//        checkLinksOnCurrentPage(driver);
//    }
//    
//    @Test
//    public void checkActivitiesLinks() throws InterruptedException {
//    	sidebarCRM.MyOwnLeadList();
//        checkLinksOnCurrentPage(driver);
//    }
//    
//    @Test
//    public void checkActivitiesLinks() throws InterruptedException {
//    	sidebarCRM.MyOwnLeadList();
//        checkLinksOnCurrentPage(driver);
//    }
//    
//    @Test
//    public void checkActivitiesLinks() throws InterruptedException {
//    	sidebarCRM.MyOwnLeadList();
//        checkLinksOnCurrentPage(driver);
//    }
//    
//    @Test
//    public void checkActivitiesLinks() throws InterruptedException {
//    	sidebarCRM.MyOwnLeadList();
//        checkLinksOnCurrentPage(driver);
//    }

    private void checkLinksOnCurrentPage(WebDriver driver) {
        List<WebElement> links = driver.findElements(By.tagName("a"));

        for (WebElement link : links) {
            String url = link.getAttribute("href");

            System.out.println("----------------------------------------");

            if (url == null || url.isEmpty()) {
                System.out.println("URL is Empty.");
                continue;
            }

            try {
                HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());

                huc.connect();
                if (huc.getResponseCode() >= 400) {
                    System.out.println(url + " is broken!");
                } else {
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
