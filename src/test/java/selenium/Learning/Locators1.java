package selenium.Learning;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Locators1 {
	
	WebDriver driver;
  @Test
  public void byID() {
	  WebElement usernameElement=driver.findElement(By.id("email"));
      usernameElement.sendKeys("Smith");
  }
  @Test
  public void byName() {
	  WebElement usernameElement=driver.findElement(By.name("password"));
      usernameElement.sendKeys("2024");
  }
  @Test
  public void byName2() {
	  WebElement usernameElement=driver.findElement(By.name("confirmPassword"));
      usernameElement.sendKeys("2024");
  }
  @Test
  public void byName3() {
	  WebElement usernameElement=driver.findElement(By.name("submit"));
      usernameElement.click();
  }
  @BeforeMethod
  public void beforeMethod() throws InterruptedException{
	Thread.sleep(2000); 
  }
 

  @AfterMethod
  public void afterMethod() {
  }
  @BeforeClass
  public void beforeClass1() {
	  driver=new ChromeDriver();
	  driver.get("https://demo.guru99.com/test/newtours/support.php");
  }
	  
  @Test 
  public void byLinkText() throws InterruptedException {

	  driver.findElement(By.linkText("REGISTER")).click();
	  Thread.sleep(4000);
  }
  
  @Test 
  public void byPartialLinkText() throws InterruptedException {

	  driver.findElement(By.partialLinkText("PORT")).click();
	  Thread.sleep(4000);
  }

  @BeforeClass
  public void beforeClass() {
	  driver=new ChromeDriver();
	  driver.get("https://demo.guru99.com/test/newtours/register.php");
	  
  }

  @AfterClass
  public void afterClass() {
  }

}
