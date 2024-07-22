package selenium.Learning;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
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

public class Finalassignone {

	WebDriver driver;
	private Workbook workbook;
	Sheet sheet;
	private String excelFileName = "C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\Quebec.xlsx";
	@BeforeMethod
	public void beforeMethod() {

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		driver = new ChromeDriver(ops);
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Redemption");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

	}

	@Test
	public void readfromapp() throws IOException, InterruptedException{

		driver.get("https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().window().maximize();
		Thread.sleep(2000);	
		String excelFilePath = System.getProperty("C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\Quebec.xlsx");



		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[197]")).click();   
		WebElement  table=driver.findElement(By.xpath("(//table)[2]"));	
		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);

		List<WebElement> rows= driver.findElements(By.xpath("(//tbody)[2]//tr"));
		System.out.println("Total no. of Rows: "+rows.size());
		int rowcount=rows.size();


		
		

		try {
			for (int i = 2; i <=rowcount; i++) {

				WebElement row = rows.get(i);
				List<WebElement> columns = row.findElements(By.tagName("td"));
				System.out.println("Total no. of Elements in the Table: "+columns.size() + "\t");

				for (WebElement column : columns) {

					if (column != null) {
						System.out.print(column.getText().toString() + "\t");
					} else {
						System.out.println("Cell element not found.");
					}
			

					// Extract data from each cell
					String stringData =  columns.get(0).getText();
				    String integerData = columns.get(1).getText();
					double doubleData = Double.parseDouble(columns.get(2).getText());

					// Write data to Excel
					Row excelRow = sheet.createRow(i);
					Cell cell1 = excelRow.createCell(0);
					cell1.setCellValue(stringData);
					Cell cell2 = excelRow.createCell(1);
					cell2.setCellValue(integerData);
					Cell cell3 = excelRow.createCell(2);
					cell3.setCellValue(doubleData);
				}				
		
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
	try (FileOutputStream fos = new FileOutputStream(excelFileName)) {
		workbook.write(fos);
		workbook.close();
		System.out.println("Excel file has been written successfully.");
	} catch (IOException e) {
		e.printStackTrace();
	}
	//driver.quit();
}
}

	

	





