package selenium.Learning;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager1 {
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	WebDriver driver;
	String baseUrl = "https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT::";
	public void initializer() {
		sparkReporter =  new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/extentSparkReport.html");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Test Execution Report");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);		
	}

	public static String captureScreenshot(WebDriver driver) throws IOException {
		String FileSeparator = System.getProperty("file.separator"); // "/" or "\"
		String Extent_report_path = "."+FileSeparator+"Reports"; // . means parent directory
		File Src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String Screenshotname = "screenshot"+Math.random()+".png";
		File Dst = new File(Extent_report_path+FileSeparator+"Screenshots"+FileSeparator+Screenshotname);
		FileUtils.copyFile(Src, Dst);
		String absPath = Dst.getAbsolutePath();
		//System.out.println("Absolute path is:"+absPath);
		return absPath;
	}

	@Test (priority = 1)
	public void redemption() throws IOException {
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		String className = new Exception().getStackTrace()[0].getClassName();
		test = extent.createTest(methodName,"Validate front webpage");
		test.log(Status.INFO, "Starting and Verify title");
		test.assignCategory("Regression Testing");

		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[188]")).click(); 
		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);

		driver.findElement(By.xpath("(//table)[2]"));
		scrollIntoView(driver, driver.findElement(By.xpath("(//tbody)[2]//tr")));
		test.log(Status.INFO, "Entered the Quebec webpage successfully");

		test.log(Status.PASS, "Entered the webtable Successfully");
		//test.log(Status.WARNING, "Entered password Successfully");
		test.addScreenCaptureFromPath(captureScreenshot(driver));

		String actualTitle = driver.getTitle();
		test.log(Status.PASS, "Navigating to webpage is successfull");
		test.addScreenCaptureFromPath(captureScreenshot(driver));
		System.out.println("\t");
		System.out.println("Actual Title is :"+actualTitle);
		String expectedTitle = "Résultats publics VF Anglaise";

		AssertJUnit.assertEquals(expectedTitle,actualTitle);
	}


	public static void scrollIntoView(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	@Test (priority = 2)
	public void saintbasil() throws IOException {
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		String className = new Exception().getStackTrace()[0].getClassName();
		test = extent.createTest(methodName,"Validate login to the application");
		test.log(Status.INFO, "Starting to Open Quebec Webpage and Verifing title");
		test.assignCategory("Regression Testing");

		driver.get(baseUrl);	
		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[189]")).click(); 
		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);

		driver.findElement(By.xpath("(//table)[2]"));
		scrollIntoView(driver, driver.findElement(By.xpath("(//tbody)[2]//tr")));
		test.addScreenCaptureFromPath(captureScreenshot(driver));


		String actualTitle = driver.getTitle();
		test.log(Status.PASS, "Navigating to webpage is successfull");
		test.addScreenCaptureFromPath(captureScreenshot(driver));
		System.out.println("\t");
		System.out.println("Actual Title is :"+actualTitle);
		String expectedTitle = "Résultats publics VF Anglaise";

		AssertJUnit.assertEquals(expectedTitle,actualTitle);
	}
	
	

	@Test (priority = 3)
	public void healthcap() throws IOException {
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		String className = new Exception().getStackTrace()[0].getClassName();
		test = extent.createTest(methodName,"Validate login to the application");
		test.log(Status.INFO, "Starting Quebec Webpage and Verifing title");
		test.assignCategory("Regression Testing");

		driver.get(baseUrl);	
		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[190]")).click(); 
		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);

		driver.findElement(By.xpath("(//table)[2]"));
		scrollIntoView(driver, driver.findElement(By.xpath("(//tbody)[2]//tr")));
		test.addScreenCaptureFromPath(captureScreenshot(driver));


		String actualTitle = driver.getTitle();
		test.log(Status.PASS, "Navigating to webpage is successfull");
		test.addScreenCaptureFromPath(captureScreenshot(driver));
		System.out.println("\t");
		System.out.println("Actual Title is :"+actualTitle);
		String expectedTitle = "Résultats publics VF Anglaise";

		AssertJUnit.assertEquals(expectedTitle,actualTitle);
	}
	

	@Test (priority = 4)
	public void nicolet() throws IOException {
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		String className = new Exception().getStackTrace()[0].getClassName();
		test = extent.createTest(methodName,"Validate login to the application");
		test.log(Status.INFO, "Starting Quebec Webpage and Verifing title");
		test.assignCategory("Regression Testing");

		driver.get(baseUrl);	
		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[191]")).click(); 
		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);

		driver.findElement(By.xpath("(//table)[2]"));
		scrollIntoView(driver, driver.findElement(By.xpath("(//tbody)[2]//tr")));
		test.addScreenCaptureFromPath(captureScreenshot(driver));


		String actualTitle = driver.getTitle();
		test.log(Status.PASS, "Navigating to webpage is successfull");
		test.addScreenCaptureFromPath(captureScreenshot(driver));
		System.out.println("\t");
		System.out.println("Actual Title is :"+actualTitle);
		String expectedTitle = "Résultats publics VF Anglaise";

		AssertJUnit.assertEquals(expectedTitle,actualTitle);
	}
	

	@Test (priority = 5)
	public void rawdon() throws IOException {
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		String className = new Exception().getStackTrace()[0].getClassName();
		test = extent.createTest(methodName,"Validate login to the application");
		test.log(Status.INFO, "Starting to Open Quebec Webpage and Verifing title");
		test.assignCategory("Regression Testing");

		driver.get(baseUrl);	
		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[192]")).click(); 
		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);

		driver.findElement(By.xpath("(//table)[2]"));
		scrollIntoView(driver, driver.findElement(By.xpath("(//tbody)[2]//tr")));
		test.addScreenCaptureFromPath(captureScreenshot(driver));


		String actualTitle = driver.getTitle();
		test.log(Status.PASS, "Navigating to webpage is successfull");
		test.addScreenCaptureFromPath(captureScreenshot(driver));
		System.out.println("\t");
		System.out.println("Actual Title is :"+actualTitle);
		String expectedTitle = "Résultats publics VF Anglaise";

		AssertJUnit.assertEquals(expectedTitle,actualTitle);
	}
	@AfterTest
	public void closeMethod() {
		extent.flush();
		driver.close();
	}

	@BeforeTest
	public void driverSetup() {
		initializer();

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		driver = new ChromeDriver(ops);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}

