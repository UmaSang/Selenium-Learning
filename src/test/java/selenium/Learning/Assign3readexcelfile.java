package selenium.Learning;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Assign3readexcelfile {

	@Test
	public void readFromExcelFile() throws Exception {
		String excelFilePath = "C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\MaturityValCalc.xlsx";

		try {
			// Read from Excel file and //deal with Workbook
			FileInputStream file= new FileInputStream(excelFilePath);
			XSSFWorkbook workbook = new XSSFWorkbook(file);		            
			XSSFSheet sheet = workbook.getSheet("maturityValCalculator"); //Go to specific sheet to read from.

			//Find Number of Rows and Column
			int totalrows = sheet.getLastRowNum();
			int totalcells = sheet.getRow(totalrows).getLastCellNum();
			System.out.println("Number of rows:"+totalrows);
			System.out.println("Number of Cells:"+totalcells);
			System.out.println("\t");

			// Iterate over rows and columns using for..loop //2D array
			for(int i = 0; i<= totalrows ; i++) {
				XSSFRow currentrow = sheet.getRow(i);
				System.out.println("\t ");
				for(int j = 0; j< totalcells ; j++) {
					XSSFCell cell = currentrow.getCell(j);
					System.out.print(cell.toString()+"\t" );

				}
				System.out.println(" ");
			}

			// Close streams
			file.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void readexcel() throws Exception {



		try {
			// Assuming you have a workbook and sheet already initialized
			FileInputStream file= new FileInputStream("C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\MaturityValCalc.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0); // Get the first sheet

			// Iterate through each row to find the maximum number of cells used
			int maxCells = 0;
			for (Row row : sheet) {
				int lastCellNum = row.getLastCellNum();
				if (lastCellNum > maxCells) {
					maxCells = lastCellNum;
				}
			}
			System.out.println("\t ");
			System.out.println("Total number of cells used: " + maxCells);
			System.out.println("\t ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}