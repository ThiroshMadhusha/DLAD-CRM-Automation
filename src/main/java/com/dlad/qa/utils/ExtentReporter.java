package com.dlad.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

    public static ExtentReports generateExtendReport() {
        
        ExtentReports extentReport = new ExtentReports();
        
        File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

        
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("DLAD CRM Automation Testing Report");
        sparkReporter.config().setDocumentTitle("DLAD CRM Test Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
        
        extentReport.attachReporter(sparkReporter);
        
        // Initialize WebDriver and Capabilities
//        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
//        extentReport.setSystemInfo("Browser Name", cap.getBrowserName());
//        extentReport.setSystemInfo("Browser Version", cap.getBrowserVersion());
//        extentReport.setSystemInfo("Platform", cap.getPlatformName().toString());
        
        Properties configProp = new Properties();
        File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dlad\\qa\\config\\config.properties");
        
        try {
            FileInputStream filsConfigPro = new FileInputStream(configPropFile);
            configProp.load(filsConfigPro);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
        extentReport.setSystemInfo("Email", configProp.getProperty("validCRMEmailAddress"));
        extentReport.setSystemInfo("Password", configProp.getProperty("validCRMPassword"));
        
        extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReport.setSystemInfo("Test User Name", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java JDK Version", System.getProperty("java.version"));

        return extentReport;
    }
}
