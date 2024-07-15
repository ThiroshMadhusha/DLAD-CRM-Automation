package com.dlad.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utils {

	public static String emailGenerate() {

		Date date = new Date();
		String emailGenerate = date.toString().replace(":", "").replace(" ", "");
		return emailGenerate + "@gmail.com";
	}

	public static String passwordGenerate() {

		Date date = new Date();
		String passwordGenerate = date.toString().replace(":", "").replace(" ", "");
		return passwordGenerate;
	}

	public static String nameGenerate() {

		Date date = new Date();
		String nameGenerate = date.toString().replace(":", "").replace(" ", "");
//		return nameGenerate + "Name";
		return nameGenerate.substring(10, nameGenerate.length() - 4) + " Name";
	}
	
	public static String companyNameGenerate() {

		Date date = new Date();
		String nameGenerate = date.toString().replace(":", "").replace(" ", "");
//		return nameGenerate.substring(11) + " CompanyName";
		return nameGenerate.substring(10, nameGenerate.length() - 4) + " CompanyName";

	}

	public static final int IMPLICIT_WAIT_TIME = 30;
	public static final int PAGE_LOAD_TIME = 60;
	public static final int SCRIPT_TIME_OUT = 30;

	// Data Driven Testing for Login Page by Using Excell Sheet
	@SuppressWarnings("incomplete-switch")
	public static Object[][] getTestDataFromExcel(String sheetName) {
		File excellFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\dlad\\qa\\testdataconfig\\TestDataPropExcell.xlsx");
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(excellFile);
			workbook = new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {

				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
	}
	
	// Take Screenhot When the Test Execution Failure
	public static String captureScreenShots(WebDriver driver, String testName) {
		
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\" + testName +".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return destinationScreenshotPath;
	}
}
