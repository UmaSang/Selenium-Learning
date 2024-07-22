package selenium.Learning;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assign3 {
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");

		driver = new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

	}

	@Test
	public void readFromExcelFile() throws Exception {

		String excelFilePath = "C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\MaturityValCalc.xlsx";		
		driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
		driver.manage().window().maximize();

		// Read from Excel file and //deal with Workbook
		FileInputStream file= new FileInputStream(excelFilePath);
		try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
			XSSFSheet sheet = workbook.getSheet("maturityValCalculator");//Go to specific sheet to read from.
			int totalrows=sheet.getLastRowNum();
			
			//Iterate over rows and columns using for..loop //2D array
			for(int i = 1; i<= totalrows ; i++) 
			{      

			XSSFRow currentrow = sheet.getRow(i);        	               

				// Now convert this decimal into integer and then String

				int pri_int =(int)currentrow.getCell(0).getNumericCellValue();
				String principal = String.valueOf(pri_int);	   			    
				driver.findElement(By.xpath("//input[@id='principal']")).sendKeys(principal);

				String roi=currentrow.getCell(1).toString();
				driver.findElement(By.xpath("//input[@id='interest']")).sendKeys(roi);

				int periodval =(int)currentrow.getCell(2).getNumericCellValue();
				String per_val=String.valueOf(periodval);	
				driver.findElement(By.xpath("//input[@id='tenure']")).sendKeys(per_val);
				//	 	     
				String periodtype=currentrow.getCell(3).toString();
				Select per_type=new Select(driver.findElement(By.xpath("//select[@id='tenurePeriod']")));
				per_type.selectByVisibleText(periodtype);

				String frequency=currentrow.getCell(4).toString();
				Select fredrp=new Select(driver.findElement(By.xpath("//select[@id='frequency']")));
				fredrp.selectByVisibleText(frequency);
				driver.findElement(By.xpath("//div[@class='cal_div']//a[1]")).click();// clicked on the calculate button


				//validation	    

				FileOutputStream outputStream = new FileOutputStream(excelFilePath);
				workbook.write(outputStream);
				String ma_val=driver.findElement(By.xpath("//span[contains(@id,'matval')]")).getText();
				double matur_value = Double.parseDouble(ma_val);
				XSSFCell mv=currentrow.createCell(5);
				mv.setCellValue(matur_value);  						

				String int_val=driver.findElement(By.cssSelector("span[id='resp_intval'] em")).getText();
				//double interest_value = Double.parseDouble(int_val);
				XSSFCell ie=currentrow.createCell(6);
				ie.setCellValue(int_val);  


				Thread.sleep(3000);
				driver.findElement(By.xpath("//img[@class='PL5']")).click();  //clicked on clear button

			}
		}
	}



@AfterMethod
public void afterMethod() {
	driver.quit();
}

}











// Write to Excel file

//			public void writetoExcelFile() throws Exception {
//				String excelFilePath = "C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\MaturityValCalc.xlsx";
//				WebDriver driver = null;
//
//			try {
//				FileOutputStream outputStream = new FileOutputStream(excelFilePath);
//				XSSFWorkbook workbook = new XSSFWorkbook();
//				XSSFSheet sheet = workbook.getSheet("maturityValCalculator");
//				XSSFRow currentrow = sheet.getRow(1);
//
//				WebElement act_mv= driver.findElement(By.cssSelector("span[id='resp_matval'] strong"));
//				String value=act_mv.getText();
//				Cell mv=currentrow.getCell(5);
//				mv.setCellValue(value);  						
//
//
//				WebElement act_IE=driver.findElement(By.cssSelector("span[id='resp_intval'] em"));
//				String int_value=act_IE.getText();
//				Cell ie=currentrow.getCell(6);
//				ie.setCellValue(int_value);  						



// Close streams
//				outputStream.close();
//				workbook.clo
////
//			catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
//		}
