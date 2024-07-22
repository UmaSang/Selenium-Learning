package selenium.Learning;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Assignment1 {
	WebDriver driver;
	 String Url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    
	//@BeforeClass
	public void setUp() throws InterruptedException {
       // // Initialize WebDriver (ChromeDriver in this case)
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(3000);
	}

	@Test(description = "Test Case 1: Wrong Username and wrong password")
	public void testWrongUsernameAndPassword() {
		
		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.name("username")).sendKeys("user");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.tagName("button")).click();

		// Verify error message
		String errorMessage = driver.findElement(By.id("spanMessage")).getText();
		Assert.assertTrue(errorMessage.contains("Invalid credentials"));
	}

	@Test(description = "Test Case 2: Correct Username and wrong password")
	public void testCorrectUsernameAndWrongPassword() {
		
		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("user123");
		driver.findElement(By.tagName("button")).click();

		String errorMessage = driver.findElement(By.tagName("p")).getText();
		Assert.assertTrue(errorMessage.contains("Invalid credentials"));
	}

	@Test(description = "Test Case 3: Wrong Username and correct password")
	public void testWrongUsernameAndCorrectPassword() {
		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.name("username")).sendKeys("Admi");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.tagName("button")).click();
		// Verify error message
		String errorMessage = driver.findElement(By.tagName("p")).getText();
		Assert.assertTrue(errorMessage.contains("Invalid credentials"));
	}

	@Test(description = "Test Case 4: Correct Username and correct password")
	public void testCorrectUsernameAndCorrectPassword() {
		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.tagName("button")).click();

		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, "orangehrm-login-branding", "Dashboard title is matching");

		// Logout
		driver.findElement(By.className("oxd-userdropdown-link")).click();
		driver.findElement(By.className("oxd-userdropdown-link")).click();
	}
//
    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
}
       }

	// public static void main(String[] args) {
	@Test
	public void test3() {
		Assignment1 test = new Assignment1();

//		// Execute each test case one by one
		try {
			test.setUp();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		test.testWrongUsernameAndPassword();
		test.testCorrectUsernameAndWrongPassword();
		test.testWrongUsernameAndCorrectPassword();
		test.testCorrectUsernameAndCorrectPassword();

	}
}