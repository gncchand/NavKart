package tests;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import excel.Shopping_Data;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Checkout_Page;
import pages.Shopping_Page;

public class Shopping_Chrome_Test extends ExtentReportTest {
	
	public Shopping_Page scp;
	public Checkout_Page ccp;
	  
  @Test(dataProvider = "getexcel_data")  
  public void test_cart(String title, String price, String quantity, String name, String total) throws Exception {
	  WebDriverManager.chromedriver().setup();
	  driver=new ChromeDriver();
	  extentReportResults();
	  	et=er.createTest("shopping website check on Chrome", "cart checkout validaton using Chrome"); 
	  	
	  driver.get("http://automationpractice.com/index.php");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		 scp=new Shopping_Page(driver);
		 ccp=new Checkout_Page(driver);
	  Thread.sleep(3000);
	  System.out.println("Title : "+title+" ; Price : "+price+" ; Quantity : "+quantity+" ; Name : "+name+" ; Total : "+total);
	  scp.click_best_Seller();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("(//*[contains(text(),'"+name+"')])[2]")).click();
	  
	  if(ccp.price.getText().equalsIgnoreCase(price))//validate price
	  {
		  et.log(Status.PASS, "Price validation test case passed for Chrome");
		  Thread.sleep(3000);		  
	  }
	  else
	  {
	   et.log(Status.FAIL, "Price validation test case failed for Chrome");
	   Thread.sleep(3000);
	  }

	  if(driver.getTitle().equalsIgnoreCase(title))
	  {
		    et.log(Status.PASS, "Dress title test case passed for Chrome");
		    Thread.sleep(3000);
		    ccp.checkout.click();
		    Thread.sleep(6000);
		    if(ccp.success_msg.getText().equalsIgnoreCase("Product successfully added to your shopping cart"))
		    {
		    et.log(Status.PASS, "Successfull message test case passed for Chrome");
		    Thread.sleep(3000);				  
		    }
			else
		    {
		    et.log(Status.FAIL, "Successfull message test case failed for Chrome");
		    Thread.sleep(3000);
		    }   
		   
		   if(ccp.cart_price.getText().equalsIgnoreCase(price))
		   {
		   et.log(Status.PASS, "Cart price test case passed for Chrome");
		   Thread.sleep(3000);	  
		   }
		   else
			{
			et.log(Status.FAIL, "Cart price test case failed for Chrome");
			Thread.sleep(3000);
			}
		   
		    if(ccp.cart_quantity.getText().equalsIgnoreCase(quantity))
		    {
			  et.log(Status.PASS, "Cart quantity test case passed for Chrome");
			   Thread.sleep(3000);
			  
		    }
		   else
			{
			  et.log(Status.FAIL, "Cart quantity test case failed for Chrome");
			   Thread.sleep(3000);
			 }
		  
		    if(ccp.cart_total.getText().equalsIgnoreCase(total))
		    {
			  et.log(Status.PASS, "Cart total test case passed for Chrome");
			   Thread.sleep(3000);	  
		    }
		    else
			{
			  et.log(Status.FAIL, "Cart total test case failed for Chrome");
			   Thread.sleep(3000);
			}
		 
		  ccp.click_cont_shoppin();
	  }
	  else
	  {
	   et.log(Status.FAIL, "Dress title test case failed for Chrome");
	   Thread.sleep(3000);
	  }
	  driver.quit();
	}
   
	 @DataProvider 
	 public String[][] getexcel_data() throws IOException
	 {
	   Shopping_Data excel=new Shopping_Data();
	   return excel.get_data_from_excel("shopping_data.xlsx", "Chrome");
	 }

}
