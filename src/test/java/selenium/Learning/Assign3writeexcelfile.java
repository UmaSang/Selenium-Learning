package selenium.Learning;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterClass;

	public class Assign3writeexcelfile {
		
		 public static FileInputStream fi;
		 public static FileOutputStream fo;
		 public static XSSFWorkbook wb;
         public static XSSFSheet ws;
         public static XSSFRow row;
         public static XSSFCell cell;
         
         public static int getRowCount(String xlfile, String xlsheet) throws IOException
         {
        	String excelFilePath = "C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\MaturityValCalc.xlsx";
        	fi=new FileInputStream(excelFilePath);
        	ws=wb.getSheet(xlsheet);
        	int rowcount=ws.getLastRowNum();
        	wb.close();
        	fi.close();
        	return rowcount;       	
        	
         }

         public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException
         {
        	String excelFilePath = "C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\MaturityValCalc.xlsx";
        	fi=new FileInputStream(excelFilePath);
        	ws=wb.getSheet(xlsheet);
        	int cellcount=row.getLastCellNum();
        	wb.close();
        	fi.close();
        	return cellcount;       	
        	
         }
         public static String getCellData(String xlfile, String xlsheet, int rownum, int column) throws IOException
         {
        	String excelFilePath = "C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\MaturityValCalc.xlsx";
        	fi=new FileInputStream(excelFilePath);
        	ws=wb.getSheet(xlsheet);
        	row=ws.getRow(rownum);
        	cell=row.getCell(column);
        	String data;
        	try
        	{
        		DataFormatter formatter=new DataFormatter();
        		data=formatter.formatCellValue(cell);
        	}
        	catch(Exception e){
        		{
        			data=" ";
        		}
        	wb.close();
        	fi.close();
        	      	
         }
			return data;
         }
         public static void setCellData(String xlfile, String xlsheet, int rownum, int column, String data) throws IOException
         {
        	String excelFilePath = "C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\MaturityValCalc.xlsx";
        	fi=new FileInputStream(excelFilePath);
        	ws=wb.getSheet(xlsheet);
        	row=ws.getRow(rownum);
        	cell=row.getCell(column);
        	cell.setCellValue(data);
        	fo=new FileOutputStream(excelFilePath);
        	wb.write(fo);
        	wb.close();
        	fi.close();
        	fo.close();
    
        // Read from Excel file and //deal with Workbook
//        FileOutputStream file= new FileOutputStream(excelFilePath);
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFRow sheet = workbook.getSheetAt(0);
//        XSSFSheet sheet = workbook.getSheet("maturityValCalculator");
//       
        //Go to specific sheet to read from.
       // XSSFSheet sheet = workbook.getSheet("maturityValCalculator");
	}
        



}
