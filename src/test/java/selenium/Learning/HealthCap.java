package selenium.Learning;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class HealthCap {

	private WebDriver driver;
	private String baseUrl = "https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT:::";
	private String excelFilePath = "C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\Quebec.xlsx";
	String newSheetName = "HealthCap";
	
	

	@BeforeClass
	public void setUp() {

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		driver = new ChromeDriver(ops);

		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

	}

	
	
	
	@Test
	public void testReadTableAndWriteToExcel() throws IOException {
		

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().window().maximize();
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//
//			e.printStackTrace();
//		}	
//		
		

		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[191]")).click();  

		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);
		WebElement tableElement = driver.findElement(By.xpath("(//table)[2]"));
		List<WebElement> rows = tableElement.findElements(By.xpath("(//tbody)[2]//tr"));
		Workbook workbook = new XSSFWorkbook(); 			  
		XSSFSheet HealthCap = (XSSFSheet) workbook.createSheet(newSheetName);
		// Iterate over rows and columns and write to Excel sheet
		int rowNum = 0;
		for (WebElement row : rows) {
			Row excelRow = HealthCap.createRow(rowNum++);
			List<WebElement> columns = row.findElements(By.tagName("td"));
			int colNum = 0;
			for (WebElement column : columns) {
				excelRow.createCell(colNum++).setCellValue(column.getText());
			}
					}

		// Write the workbook to an Excel file
		try
		{
			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	} 
	
	
	@AfterClass
	public void tearDown() {
		// Close the WebDriver
		if (driver != null) {
			driver.quit();
		}
	}
}
