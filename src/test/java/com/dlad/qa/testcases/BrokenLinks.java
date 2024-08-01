package com.dlad.qa.testcases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.BaseClassPage;
import com.dlad.qa.pages.HomePage;
import com.dlad.qa.pages.SidebarCRM;
import com.dlad.qa.utils.Utils;

public class BrokenLinks extends BaseClass {

    public WebDriver driver;
    SidebarCRM sidebarCRM;
    HomePage homePage;

    @SuppressWarnings("deprecation")
	@BeforeMethod
    @Parameters("browser")
    public void beforeMethod(@Optional("chrome") String browserName) throws InterruptedException {
    	
        // Load Property
        loadPropertiesFile();
        
        // Parallel Browser Testing
        if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			System.out.println(" ");
			System.out.println("Chrome Browser is Launched");
			
		}if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			System.out.println(" ");
			System.out.println("Edge Browser is Launched");
			
		}if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			System.out.println(" ");
			System.out.println("Firefox Browser is Launched");
			
		}if(browserName.equalsIgnoreCase("safari")){
			driver = new SafariDriver();
			System.out.println("Safari Browser is Launched");
			System.out.println(" ");
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
