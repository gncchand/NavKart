package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout_Page {//check out dress

public WebDriver driver;
	
	//locators - elements
	@FindBy(xpath="//div/p/span[@id=\"our_price_display\"]")
	public WebElement price;
	
	@FindBy(xpath="//*[text()='Add to cart']")
	public WebElement checkout;
	
	@FindBy(xpath="//div/h2[contains(text()[2],'success')]")
	public WebElement success_msg;
	
	@FindBy(xpath="//div[@class='layer_cart_row'][1]/span")
	public WebElement cart_price;
	
	@FindBy(xpath="//div/span[@id='layer_cart_product_quantity']")
	public WebElement cart_quantity;
	
	@FindBy(xpath="//div/span[@id='layer_cart_product_price']")
	public WebElement cart_total;
	
	@FindBy(xpath="//div[@class=\"button-container\"]/span/span[contains(text()[2],'Continue shopping')]")
	public WebElement cont_shoppin_button;
	
	//methods - constructor methods
	public Checkout_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//methods- operational methods
	
		public void click_cont_shoppin()
		{
			cont_shoppin_button.click();		
		}	
			
}
