package selenium.Learning;


import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Rawdon {

	private WebDriver driver;
	private String excelFileName = "C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\Quebec.xlsx";
	Workbook workbook = new XSSFWorkbook(); 
	Sheet sheet;
	String newSheetName = "Rawdon";
	//    		
	@BeforeMethod
	public void beforeMethod() {

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");

		driver = new ChromeDriver(ops);
		driver.get("https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT::::");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

	}

	@Test
	public void readfromapp() throws IOException, InterruptedException{

		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().window().maximize();
		Thread.sleep(2000);	
	
		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[192]")).click();  
		
		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);
		WebElement table = driver.findElement(By.xpath("(//table)[2]"));
		List<WebElement> rows = table.findElements(By.xpath("(//tbody)[2]//tr"));	
		XSSFSheet Rawdon = (XSSFSheet) workbook.createSheet(newSheetName);
		
		try {			
		
			for (int i = 0; i < rows.size(); i++) {
				WebElement row = rows.get(i);
				XSSFRow excelRow = Rawdon.createRow(1);
				List<WebElement> cells = row.findElements(By.tagName("td"));

				for (int c = 0; c< cells.size(); c++) {
				// Extract data from each cell
				String stringData = cells.get(c).getText();
				excelRow.createCell(c).setCellValue(stringData);
				System.out.print(stringData + "\t");
				}
				
				System.out.println();
		
			}
			
		}		
				catch (NullPointerException e) {
				System.out.println("Null pointer exception: " + e.getMessage());
			} 
				catch (Exception e) {
					e.printStackTrace();
				}													
		
	}
@AfterClass
public void tearDown() {
	// Write Excel file and close WebDriver
	try {
		FileOutputStream fos = new FileOutputStream(excelFileName);
		workbook.write(fos);
		workbook.close();
		System.out.println("Excel file has been written successfully.");
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}

}


	
//

