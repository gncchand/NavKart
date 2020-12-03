package tests;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
//SoftAssert don't throw an exception when an assert fails. 
//The test execution will continue with the next step after the assert statement
//And to see assertions result at the end of the test, we have to invoke assertAll()
//We should instantiate a SoftAssert object within a @Test method. Scope of SoftAssert should only be within the Test method
//Else we will see previous test output in current test. Instantiate SoftAssert in every test
public class SoftAssert_GoogleTitle {
	
		public static WebDriver driver;
		@Test
		public void testcase1() throws Exception {
			SoftAssert softAssert=new SoftAssert();
			driver=new ChromeDriver();
			driver.get("http://www.google.com");
			softAssert.assertEquals(driver.getTitle(), "boogleee");//soft assert
			take_screenshot();		
			System.out.println("*** test case1 executed successfully ***");//this will execute even soft assert fails in line 26		
			softAssert.assertAll();
		}
		@Test
		public void testcase2() throws Exception {//hard assert
			Assert.assertEquals(driver.getTitle(), "boogleee"); //Hard Assert
			take_screenshot();		
			System.out.println("*** test case2 executed successfully ***"); //this will not execute as hard assert fails in line 33		
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
			Reporter.log("<br><img src='"+dest+"' height='500' width='800'/><br>");  
		}
	
}
