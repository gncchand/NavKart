package tests;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import excel.Shopping_Data;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Checkout_Page;
import pages.Shopping_Page;

public class Shopping_Chrome_Test extends ExtentReportTest {
	
	public Shopping_Page scp;
	public Checkout_Page ccp;

	  
  @Test(dataProvider = "getexcel_data", priority = 1)  
  public void validate_price(String title, String price, String quantity, String name, String total) throws Exception 
  {
	  SoftAssert sa=new SoftAssert();
	  WebDriverManager.chromedriver().setup();
	  driver=new ChromeDriver();
	  test=extent.createTest("shopping website check on Chrome"); 	  	
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
	  sa.assertEquals(ccp.price.getText(),price);
	  sa.assertAll();
	  Thread.sleep(3000);	
  }
  @Test(dataProvider = "getexcel_data", priority = 2)  
  public void validate_dress_title(String title, String price, String quantity, String name, String total) throws InterruptedException
  {
	  SoftAssert sa=new SoftAssert();
	  sa.assertEquals(driver.getTitle(),title);
	  sa.assertAll();
	  Thread.sleep(3000);
	  ccp.checkout.click();
	  Thread.sleep(3000);	
  }  
  @Test(priority = 3)  
  public void validate_success_message() throws InterruptedException
  {
	  SoftAssert sa=new SoftAssert();
	  sa.assertEquals(ccp.success_msg.getText(),"Product successfully added to your shopping cart");
	  sa.assertAll();
	  Thread.sleep(3000);	
  }
  @Test(dataProvider = "getexcel_data", priority = 4)  
  public void validate_cart_price(String title, String price, String quantity, String name, String total) throws InterruptedException
  {
	  SoftAssert sa=new SoftAssert();
	 sa.assertEquals(ccp.cart_price.getText(),price);
	 sa.assertAll();
	 Thread.sleep(3000);
  }
  @Test(dataProvider = "getexcel_data", priority = 5)  
  public void validate_cart_quantity(String title, String price, String quantity, String name, String total) throws InterruptedException
  {
	  SoftAssert sa=new SoftAssert();
	  sa.assertEquals(ccp.cart_quantity.getText(),quantity);
	  sa.assertAll();
	  Thread.sleep(3000);
  }  
  @Test(dataProvider = "getexcel_data", priority = 6)  
  public void validate_cart_total(String title, String price, String quantity, String name, String total) throws InterruptedException
  {
	  SoftAssert sa=new SoftAssert();
	  sa.assertEquals(ccp.cart_total.getText(),total);
	  sa.assertAll();
	  Thread.sleep(3000);	
	  ccp.click_cont_shoppin();
	  driver.quit();	  	  
  }
   
 @DataProvider 
 public String[][] getexcel_data() throws IOException
 {
	  Shopping_Data excel=new Shopping_Data();
	  return excel.get_data_from_excel("shopping_data.xlsx", "Chrome");
 }

}
