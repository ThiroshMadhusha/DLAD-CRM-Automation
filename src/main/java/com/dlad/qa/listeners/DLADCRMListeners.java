package com.dlad.qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class DLADCRMListeners implements ITestListener{

	// Execute Test Method in High Level
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of Project Test Started...!");
	}
	
	// Start Test Cases Execution
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		System.out.println("The " + testName + " testCase Started Executing...!");
	}
	
	// Test Pass without any Failure
	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		System.out.println("The " + testName + " testCase got Successfully Executed...!");
	}

	// Test Execution Faild
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		System.out.println("The " + testName + " testCase Execution Failed...!");
		System.out.println(result.getThrowable());
	}

	// Test Cases Skipped
	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		System.out.println("The " + testName + " testCase Execution Skipped...!");
		System.out.println(result.getThrowable());
	}


	// Test Case Successfully Passed
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Finished Executing Project Test...!");
	}

	
}
