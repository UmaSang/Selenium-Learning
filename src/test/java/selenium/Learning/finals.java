package selenium.Learning;

import java.io.FileNotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@Test
public class finals {

	WebDriver driver;


	@BeforeMethod
	public void beforeMethod() {

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");

		driver = new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

	}	
	@Test
	public void readfromapp() throws IOException{

		driver.get("https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().window().maximize();
		String excelFilePath = System.getProperty("C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\Quebec.xlsx");



		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[185]")).click();   
		WebElement  table=driver.findElement(By.tagName("tbody"));	
		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);		

		List<WebElement> rows= driver.findElements(By.xpath("(//tbody)[2]//tr"));
		System.out.println("Number of rows in the table:"+rows.size());
		System.out.println("\t");
		List<WebElement> columns = driver.findElements(By.xpath("(//tr)[2]//..//td"));
		System.out.println("No. of columns are: "+columns.size() + "\t");
		List<String> tableData = new ArrayList<>();


		for(WebElement row:rows) {
			List<String> rowData = new ArrayList<>();
			String value=row.getText();
			System.out.println("\t");

			System.out.println("\t"+ " "+value);	

			for(WebElement column:columns) {
				rowData.add(column.getText());
			}

			tableData.addAll(rowData);

		}	
		driver.switchTo().defaultContent();

		// Create a workbook and a sheet        	
		Workbook workbook = new XSSFWorkbook();
		XSSFSheet  sheet = (XSSFSheet) workbook.createSheet("link1");
		int rowIndex = 0;

		for (String rowData : tableData) {
			XSSFRow row = sheet.createRow(rowIndex++);
			
//			int cellIndex = 0;
//			for (String cellData : rowData) {
//				XSSFCell cell = row.createCell(cellIndex++);
//				cell.setCellValue(cellData);
//			}
		}

		// Write the workbook content to a file
		try {


			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
			System.out.println("\t");
			System.out.println("Excel file has been generated successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}




	}

}



//		FileOutputStream outputStream = new FileOutputStream(excelFilePath);
//		workbook.write(outputStream);
//		String ma_val=driver.findElement(By.xpath("//span[contains(@id,'matval')]")).getText();
//		double matur_value = Double.parseDouble(ma_val);
//		Cell r1=currentrow.createCell(5);
//		mv.setCellValue(matur_value);  						
//
//		String int_val=driver.findElement(By.cssSelector("span[id='resp_intval'] em")).getText();
//		//double interest_value = Double.parseDouble(int_val);
//		Cell ie=currentrow.createCell(6);
//		ie.setCellValue(int_val);  
//
//		
//
//	}




