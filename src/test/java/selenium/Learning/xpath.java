package selenium.Learning;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class xpath {
    private ExtentReports extentReports;
    private ExtentTest extentTest;

	private WebDriver driver;
	private String baseUrl = "https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT:::";
	private String excelFilePath = "C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\Quebec.xlsx";
	String newSheetName = "x";

	@BeforeClass
	public void setUp() {

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		driver = new ChromeDriver(ops);
		extentReports = new ExtentReports();
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
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}	

		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[195]")).click();  

		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);
		WebElement tableElement = driver.findElement(By.xpath("(//table)[2]"));
		List<WebElement> rows = tableElement.findElements(By.xpath("(//tbody)[2]//tr"));
		Workbook workbook = new XSSFWorkbook(); 
		XSSFSheet x = (XSSFSheet) workbook.createSheet(newSheetName);
		
		// Iterate over rows and columns and write to Excel sheet
		int rowNum = 0;
		for (WebElement row : rows) {
			Row excelRow =  x .createRow(rowNum++);
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

	
      @BeforeMethod
	  public String captureScreenshot() throws Exception {
	        String screenshotPath = " ";
	        try {
	            // Capture screenshot as a file
	            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            
	            // Define screenshot directory and filename
	            LocalDateTime now = LocalDateTime.now();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
	            String timestamp = now.format(formatter);
	         //   String screenshotDirectory = System.getProperty("user.dir") + "/screenshots/";
	            String screenshotName = "screenshot_" + timestamp + ".png";
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        return screenshotPath;
	    }
		    

  

	@AfterClass
	public void tearDown() {
		// Close the WebDriver
		if (driver != null) {
			driver.quit();
				}
		 extentReports.flush();
	}
}
