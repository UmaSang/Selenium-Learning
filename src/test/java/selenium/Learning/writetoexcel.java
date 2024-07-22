package selenium.Learning;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class writetoexcel {

	public static void main(String[] args) throws IOException, InterruptedException {
		//WebDriver driver=new ChromeDriver();
		ChromeOptions ops = new ChromeOptions();
	    ops.addArguments("--disable-notifications");
	      
	    WebDriver driver = new ChromeDriver(ops);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
	    driver.manage().window().maximize();
	    String excelFilePath = System.getProperty("user.dir")+"\\src\\test\\MaturityValCalc.xlsx";
	    
	    int rows=Assign3writeexcelfile.getRowCount(excelFilePath, "maturityValCalculator");
	    
	    for(int i=1;i<=rows;i++)
	    {
	     //read data from excel
	    String principal=Assign3writeexcelfile.getCellData(excelFilePath, "maturityValCalculator", i, 0);
	    String roi=Assign3writeexcelfile.getCellData(excelFilePath, "maturityValCalculator", i, 1);
	    String periodval=Assign3writeexcelfile.getCellData(excelFilePath, "maturityValCalculator", i, 2);
	    String periodtype=Assign3writeexcelfile.getCellData(excelFilePath, "maturityValCalculator", i, 3);
	    String frequency=Assign3writeexcelfile.getCellData(excelFilePath, "maturityValCalculator", i, 4);
	    String mvalue=Assign3writeexcelfile.getCellData(excelFilePath, "maturityValCalculator", i, 5);
	    String interestearned=Assign3writeexcelfile.getCellData(excelFilePath, "maturityValCalculator", i, 6);	
	    
	    
	    // pass above data into application
	    

	     
	    //validation
	     
	    String act_mv=driver.findElement(By.xpath("//span[@id='resp_matval']//strong")).getText();
	    Assign3writeexcelfile.setCellData(excelFilePath, "maturityValCalculator", i, 5,act_mv);
	    
	     
	    if (Double.parseDouble(mvalue)==Double.parseDouble(act_mv))
	    {
	    	System.out.println("act_mv");
	    	Assign3writeexcelfile.setCellData(excelFilePath,"maturityValCalculator", 1, 5, act_mv); 
	     
	    }
	    else
	    {
	    	System.out.println("failed");
	    	Assign3writeexcelfile.setCellData(excelFilePath,"maturityValCalculator", 1, 6, mvalue);
//	    }
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//img[@class='PL5']")).click(); // clicked on the clear button
	}
	    driver.quit();
	}
	
	}
	
}



