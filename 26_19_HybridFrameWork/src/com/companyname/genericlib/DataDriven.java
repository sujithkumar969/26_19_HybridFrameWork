package com.companyname.genericlib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataDriven extends BaseTest implements AutoConstants {
	
//--------------------------------------------to get row count from xls---------------------------------------------------//
	public static int getRowCountPlus1(String excelFilePath, String sheetName) {
		int rowCount = 0;
		try {
			FileInputStream fis = new FileInputStream(excelFilePath);
			Workbook wb = WorkbookFactory.create(fis);
			int num = wb.getSheet(sheetName).getLastRowNum()+1;
			rowCount = num;
		}
        catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public static int getRowCount(String excelFilePath, String sheetName) {
		int rowCount = 0;
		try {
			FileInputStream fis = new FileInputStream(excelFilePath);
			Workbook wb = WorkbookFactory.create(fis);
			int num = wb.getSheet(sheetName).getLastRowNum();
			rowCount = num;
		}
        catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
//--------------------------------------------to get column count from xls---------------------------------------------------//	
	public static int getColumnCount(String excelFilePath, String sheetName) {   
		int columnCount = 0;
		try {
			FileInputStream fis = new FileInputStream(excelFilePath);
			Workbook wb = WorkbookFactory.create(fis);
			int num = wb.getSheet(sheetName).getRow(0).getLastCellNum();
			columnCount = num;
		}
        catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}	
		return columnCount;
	}
	
//-------------------------------function to fetch data from particular cell---------------------------------//
	public static String getData(String excelFilePath, String sheetName, int rowIndex, int cellIndex) {
			String data="";	
			try {
				FileInputStream fis = new FileInputStream(excelFilePath);
				Workbook wb = WorkbookFactory.create(fis);
			//	data = wb.getSheet(sheet).getRow(rowIndex).getCell(cellIndex).getStringCellValue();
				data = wb.getSheet(sheetName).getRow(rowIndex).getCell(cellIndex).toString().trim();
			}
			catch (IOException | InvalidFormatException e) {
				e.printStackTrace();				
			}
			catch (NullPointerException e) {
				data = "";
			}
			return data;
		}
		
//--------------------It performs decimal/exponential > long > string. If numbers are more than 10 digits in xls-----------------//
	public static String toIntegerFormat(String data) {	
			BigDecimal bigDecimalValue = new BigDecimal(data);
			long longValue = bigDecimalValue.longValue();
			String stringValue = String.valueOf(longValue);
			return stringValue;
		}

//---------------functions to fetch multiple data from excel using 2 dimensional array Object and data provider-------------------------//
	 public static Object[][] testData(String excelFilePath, String sheetName){
		    int rows = getRowCountPlus1(excelFilePath, sheetName);
		    int columns = getColumnCount(excelFilePath, sheetName);
		    Object[][] excelData = new Object[rows-1][columns];
		
		for(int ix=1; ix<rows; ix++) {           // starts from 1 (to exclude column header row) //
			for(int jx=0; jx<columns; jx++) {
				excelData[ix-1][jx] = DataDriven.getData(excelFilePath, sheetName, ix, jx);
			}
		}
		return excelData;
	}
	

}
