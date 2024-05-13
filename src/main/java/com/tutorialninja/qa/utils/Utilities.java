package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 5;

	public static Duration getImplicitWaitDuration() {
		return Duration.ofSeconds(IMPLICIT_WAIT_TIME);
	}

	public static Duration getPageLoadTimeoutDuration() {
		return Duration.ofSeconds(PAGE_LOAD_TIME);
	}

	public static String generateEmailWithTimestamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "shubham" + timestamp + "@gmail.com";

	}

	public static Object[][] getTestDataFromExcel(String Login) {
	    File excelFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\tutorialsninjatestdata.xlsx");
	    XSSFWorkbook workbook = null;
	    try {
	        FileInputStream fisExcel = new FileInputStream(excelFile);
	        workbook = new XSSFWorkbook(fisExcel);
	    } catch (IOException e) {
	        // Handle error gracefully
	        System.err.println("Error reading Excel file: " + e.getMessage());
	        return null;
	    }

	    if (workbook != null) {
	        XSSFSheet sheet = workbook.getSheet(Login);
	        if (sheet != null) {
	            int rows = sheet.getLastRowNum() + 1; // Add 1 to include the header row
	            int cols = sheet.getRow(0).getLastCellNum();

	            Object[][] data = new Object[rows][cols];
	            for (int i = 0; i < rows; i++) {
	                XSSFRow row = sheet.getRow(i);
	                if (row != null) {
	                    for (int j = 0; j < cols; j++) {
	                        XSSFCell cell = row.getCell(j);
	                        if (cell != null) {
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
	                                default:
	                                    data[i][j] = null; // Handle other cell types gracefully
	                                    break;
	                            }
	                        } else {
	                            data[i][j] = null; // Handle null cells gracefully
	                        }
	                    }
	                } else {
	                    // Handle null row gracefully
	                }
	            }

	            return data;
	        } else {
	            System.err.println("Sheet '" + Login + "' not found in Excel file.");
	            return null;
	        }
	    }
		return null;
		 
	}
	public static String captureScreenshot(WebDriver driver,String testName) {
		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}
}             
	                   
