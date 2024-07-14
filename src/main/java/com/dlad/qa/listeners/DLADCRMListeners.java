package com.dlad.qa.listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
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
		System.out.println("Execution of Project Test Started...!");
		
		extentReport = ExtentReporter.generateExtendReport();
	}
	
	// Start Test Cases Execution
	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, "The " + testName + " Test Case Started Executing...!");
	}
	
	// Test Pass without any Failure
	@Override
	public void onTestSuccess(ITestResult result) {
		testName = result.getName();
		extentTest.log(Status.PASS, "The " + testName + " Test Case got Successfully Executed...!");
	}

	// Test Execution Faild
	@Override
	public void onTestFailure(ITestResult result) {
		testName = result.getName();
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
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
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, "The " + testName + " Test Case Execution Skipped...!");
	}


	// Test Case Successfully Passed
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Finished Executing Project Test...!");
		extentReport.flush();
	}

	
}
