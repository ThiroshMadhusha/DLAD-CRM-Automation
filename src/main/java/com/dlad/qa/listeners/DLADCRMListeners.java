package com.dlad.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.dlad.qa.utils.ExtentReporter;
import com.dlad.qa.utils.Utils;

public class DLADCRMListeners implements ITestListener{

	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	// Execute Test Method in High Level
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of Project Test Started in Parallel Cross Browser Testing...!");
		
        // Generate Extent Report
        extentReport = ExtentReporter.generateExtendReport();
	}
	
	// Start Test Cases Execution
	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		System.out.println("The " + testName + " Test Case Started Executing...!");
		
		extentTest.log(Status.INFO, "The " + testName + " Test Case Started Executing...!");
	}
	
	// Test Pass without any Failure
	@Override
	public void onTestSuccess(ITestResult result) {
		testName = result.getName();
		System.out.println("The " + testName + " Test Case got Successfully Executed...!");
		
		extentTest.log(Status.PASS, "The " + testName + " Test Case got Successfully Executed...!");
	}

	// Test Execution Faild
	@Override
	public void onTestFailure(ITestResult result) {
		testName = result.getName();
		System.out.println("The " + testName + " Test Case Execution Failed...!");
		System.out.println(result.getThrowable());
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		// Screenshot when the Test Case Failure
		String destinationScreenshotPath = Utils.captureScreenShots(driver, testName);
		
		// Attached Screenshot into ExtentReports
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, "The " + testName + " Test Case Execution Failed...!");
	}

	// Test Cases Skipped
	@Override
	public void onTestSkipped(ITestResult result) {
		testName = result.getName();
		System.out.println("The " + testName + " Test Case Execution Skipped...!");
		System.out.println(result.getThrowable());
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, "The " + testName + " Test Case Execution Skipped...!");
	}


	// Test Case Successfully Passed
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("\n" + "Finished Executing Project Test...!");
		extentReport.flush();
		
		// Open Extent Report Automatically
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
