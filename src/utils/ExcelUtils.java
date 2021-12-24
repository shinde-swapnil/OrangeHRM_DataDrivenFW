package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static final String testDataExcelFileName = "hrmtestdata.xlsx"; // Global test data excel file
	public static final String currentDir = System.getProperty("user.dir"); // Main Directory of the project
	public static String testDataExcelPath = null; // Location of Test data excel file
	private static XSSFWorkbook excelWBook; // Excel WorkBook
	private static XSSFSheet excelWSheet; // Excel Sheet
	private static XSSFCell cell; // Excel cell
	private static XSSFRow row; // Excel row
	public static int rowNumber; // Row Number
	public static int columnNumber; // Column Number

	// This method has parameter: "Excel sheet name"
	// It creates FileInputStream and set excel file and excel sheet to excelWBook
	// and excelWSheet variables.
	public static void setExcel(String sheetName) throws IOException {
		testDataExcelPath = currentDir + "\\TestData\\";

		// step : get file data in bytes
		FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);

		// refer excel file
		excelWBook = new XSSFWorkbook(ExcelFile);

		// refer sheet into excel file
		excelWSheet = excelWBook.getSheet(sheetName);
	}

	// This method reads the test data from the Excel cell.
	// We are passing row number and column number as parameters.
	public static String getCellData(int RowNum, int ColNum) {
		cell = excelWSheet.getRow(RowNum).getCell(ColNum);
		DataFormatter formatter = new DataFormatter();
		return formatter.formatCellValue(cell);
	}

	// This method takes row number as a parameter and returns the data of given row
	// number.
	public static XSSFRow getRowData(int RowNum) {
		row = excelWSheet.getRow(RowNum);
		return row;
	}

	public static int getRowCount(String Sheet) {
		try {
			return excelWBook.getSheet(Sheet).getLastRowNum();
		} catch (Exception e) {
			return 0;
		}
	}

	public static void getColumnData(String sheet, String column) throws IOException 
	{		
		testDataExcelPath = currentDir + "\\TestData\\";		
		FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
		// refer excel file
		excelWBook = new XSSFWorkbook(ExcelFile);
		// refer sheet into excel file
		excelWSheet = excelWBook.getSheet(sheet);

		XSSFRow header = excelWSheet.getRow(0);
		int n = header.getLastCellNum();
		int noOfColumns = excelWSheet.getRow(0).getLastCellNum();
		int rowCount = excelWBook.getSheet(sheet).getLastRowNum();
		String header1 = header.getCell(0).getStringCellValue();
		String header2 = header.getCell(1).getStringCellValue();

		List<String> columnList = new ArrayList<>();
		
		if (header1.equals(column)) 
		{
			System.out.println("Column selected by user is : " + header1);
			int j = 0;
			for (int i = 1; i <= rowCount; i++) 
			{
				columnList.add(getCellData(i, j));
			}
		}
		else 
		{
			if (header2.equals(column)) 
			{
				System.out.println("Column selected by user is : " + header2);
				int j = 1;
				for (int i = 1; i <= rowCount; i++) 
				{
					columnList.add(getCellData(i, j));
				}
			} 
			else 
			{
				System.out.println("Column selected by user is not present in Excel sheet");
			}
		}		
		for(int i=0;i<columnList.size();i++)
		{
		    System.out.println(columnList.get(i));
		}
	}
}
