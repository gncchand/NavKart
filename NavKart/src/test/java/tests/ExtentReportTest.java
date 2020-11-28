package tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportTest
{
	public static WebDriver driver;
	public static ExtentSparkReporter es; //extent html reporter deprecated in 4.4
	public static ExtentReports er;//extent reports migrated to com.aventstack
	public static ExtentTest et;
	
	public static void extentReportResults()
	{
	es=new ExtentSparkReporter(System.getProperty("user.dir")+"\\Shopping_ReportResults.html");
	er=new ExtentReports();
	er.attachReporter(es);
	
	es.config().setReportName("My Shopping Cart Report");
	es.config().setTheme(Theme.DARK);
	}
	
	@AfterTest
	public void flushrep()
	{
		System.out.println("At AfterTest method...in ExtenReport class");
	  	er.flush();
	}
	
	public static void take_screenshot() throws Exception
	{
		File src=  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
		Date d =new Date();
		String fname=sf.format(d)+".png";
		File dest=new File("C:\\Users\\tanvi\\Downloads\\"+fname);
		System.out.println(dest);
		FileHandler.copy(src, dest);
		et.log(Status.FAIL, "<br><img src='"+dest+"' height='500' width='700'/><br>");  
	}

}