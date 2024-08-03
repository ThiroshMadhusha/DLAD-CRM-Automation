package com.dlad.qa.testcases;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.BaseClassPage;
import com.dlad.qa.utils.Utils;

public class CRMBrokenLinksTest extends BaseClass {
	
    public WebDriver driver;
    
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
			Thread.sleep(2000);
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
		
		Thread.sleep(4000);
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
            if (linkURL != null && !linkURL.isEmpty()) { // Check if href is not null or empty
                try {
                    URL url = new URL(linkURL);
                    URLConnection urlConnection = url.openConnection();
                    
                    if (urlConnection instanceof HttpURLConnection) {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                        httpURLConnection.setConnectTimeout(5000); // Increase timeout to 5 seconds
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