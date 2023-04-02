package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.opencart.factory.DriverFactory;

public class ExcelUtil {

	private static String TEST_DATA_SHEET;
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {
		
		String testDataPath = System.getProperty("excelPath"); 
		
		if (testDataPath==null) {
			System.out.println("Running with prod env excel data: ");
			TEST_DATA_SHEET = "./src/test/resources/testdata/OpenCartTestData.xlsx";
		} else {
			System.out.println("Running with excel data of env: " + testDataPath);
			switch (testDataPath) {
			case "dev":
				TEST_DATA_SHEET = "./src/test/resources/testdata/dev_OpenCartTestData.xlsx";
				break;
			case "qa":
				TEST_DATA_SHEET = "./src/test/resources/testdata/qa_OpenCartTestData.xlsx";
				break;
			case "stage":
				TEST_DATA_SHEET = "./src/test/resources/testdata/stage_OpenCartTestData.xlsx";
				break;
			case "uat":
				TEST_DATA_SHEET = "./src/test/resources/testdata/uat_OpenCartTestData.xlsx";
				break;
			case "prod":
				TEST_DATA_SHEET = "./src/test/resources/testdata/OpenCartTestData.xlsx";
				break;

			default:
				TEST_DATA_SHEET = "./src/test/resources/testdata/OpenCartTestData.xlsx";
				break;
			}
			
		}
		Object data[][] = null;

		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

}
