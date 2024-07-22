package selenium.Learning;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList; // Import ArrayList class
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Assignment2 {
	String url = "https://www.hope.com/";
	WebDriver driver;

	@Test(description = "Finding number of rows and columns")
	public void Test1() {

		// identify the table:
		WebElement tableEle = driver.findElement(By.id("table2"));

		// Find all rows in the table// Print the number of rows in the table
		List<WebElement> rows = tableEle.findElements(By.tagName("tr"));
		System.out.println("Number of rows in the table: " + rows.size());

		// After finding rows in the table
		for (int i = 1; i < rows.size(); i++) {
			WebElement row = rows.get(i);
			// Find all columns in the row
			List<WebElement> columns = row.findElements(By.tagName("td"));
			System.out.print("No. of columns are: " + columns.size() + "\t");
			// Iterate through columns and print data
			for (WebElement column : columns) {
				System.out.print(column.getText() + "\t");
			}
			System.out.println(); // Move to the next row
		}
	}

	@Test(description = "validating firstname,lastname and due")
	public void Test2() {

		// identify the table:
		WebElement tableElement = driver.findElement(By.id("table2"));

		// Find all rows in the table// Print the number of rows in the table
		List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
		System.out.println("Number of rows in the table: " + rows.size());

		// After finding rows in the table
		for (int i = 1; i < rows.size(); i++) {
			WebElement row = rows.get(i);
			// Find all columns in the row
			List<WebElement> columns = row.findElements(By.tagName("td"));
			// System.out.print("No. of columns are: "+columns.size() + "\t");
			// Iterate through columns and print data
			System.out.println();
			WebElement column = columns.get(0);
			String firstcol = column.getText();

			WebElement column1 = columns.get(1);
			String secondcol = column1.getText();

			WebElement column2 = columns.get(2);
			String thirdcol = column2.getText();

			WebElement column3 = columns.get(3);
			String fourthcol = column3.getText();

			System.out.println(" Lastname is:" + firstcol + " " + " Firstname is: " + secondcol + " " + "Email is:"
					+ thirdcol + "\t" + "Due is:" + fourthcol);
			System.out.println(); // Move to the next row

		}
		System.out.println(); // Move to the next row
	}

	@Test(description = "open that link and get title of the webpage and display")
	public void test3() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		String expectedTile = "Bitcoin is Hope";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTile);
		System.out.println(); // Move to the next row
		System.out.println("Page title is: " + actualTitle);
		System.out.println(); // Move to the next row
	}
//	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//	  driver.get("https://www.hope.com/");
//	  driver.manage().window().maximize();
//	  WebElement title=driver.findElement(By.className("MuiStack-root mui-style-1y3ojfh\""));
//       System.out.println("get title of the webpage and display:"+title);
//  }

	@BeforeTest
	public void beforeTest() throws InterruptedException {

		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/tables");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
}
