package selenium.Learning;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class XpathLocators {
	 WebDriver driver;
  @Test
	  public void f() throws InterruptedException {
		  
		   
			driver.findElement(By.xpath("//input[@id='my-text-id']")).sendKeys("Smith");
			driver.findElement(By.xpath("//input[contains(@name,'my-pass')]")).sendKeys("John");
			driver.findElement(By.xpath("//label[contains(text(),'Texta')]/textarea")).sendKeys("Successful x-path");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='my-text-id']")).clear();
			driver.findElement(By.xpath("//input[contains(@name,'my-pass')]")).clear();
			driver.findElement(By.xpath("//label[contains(text(),'Texta')]/textarea")).clear();
	  
			driver.findElement(By.xpath("//input[@type='text' and @name='my-text']")).sendKeys("Charles");
			driver.findElement(By.xpath("//input[@name='my-password' or @type='password']")).sendKeys("Catherine");
			
	  
	  }
  
  @BeforeMethod
  public void beforeMethod() {
          driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
	  }
  
  @AfterMethod
  public void afterMethod() {
  }

}
