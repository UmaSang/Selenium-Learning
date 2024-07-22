package selenium.Learning;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class Demo {
	public static ExtentSparkReporter sparkReporter;

	public static void main(String[] args) throws IOException{
		
		ExtentReports extent = new ExtentReports();
	    File file=new File(System.getProperty("user.dir")+"\\Reports\\extentSparkReport.html");	
	    extent.attachReporter(sparkReporter);
	   
	    extent.flush();
	    Desktop.getDesktop().browse(file.toURI());;
	}
	
	
}
