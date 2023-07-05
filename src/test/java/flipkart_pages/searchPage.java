package flipkart_pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import flipkart_Utilities.*;

public class searchPage {

	private WebDriver driver;

	public searchPage() {
		this.driver = Driver.getDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@title=\"Search for products, brands and more\"]")
	public WebElement searchText;
	
	@FindBy(xpath="//div[contains(text(),\"Price -- Low to High\")]")
	public WebElement priceLowToHigh;
	
	@FindBy(xpath="//div[@class=\"_30jeq3\"]")
	public List<WebElement> elementsRow; 
	
	@FindBy(xpath="//span[text()=\"Next\"]")
	public WebElement nextButton;
	
	@FindBy(xpath="//button[text()=\"Add to cart\"]")
	public WebElement addCartButton;
	
	@FindBy(xpath="//span[text()=\"Cart\"]")
	public WebElement cart;
	
	@FindBy(xpath = "//span[@class=\"_2-ut7f _1WpvJ7\"]")
    public List<WebElement> selectedProductSize;
	
	@FindBy(xpath="//div[@class=\"_2-uG6-\"]")
	public List<WebElement> selectedProductName;
	
	@FindBy(xpath="//a[@class=\"IRpwTa _2-ICcC\"]")
	public List<WebElement> elementName;
	
	@FindBy(xpath="//div[@class=\"_30jeq3 _16Jk6d\"]")
	public WebElement productPrice;
	
	@FindBy(xpath="//span[@class=\"B_NuCI\"]")
	public WebElement productName;
	
	@FindBy(xpath="//div[@class=\"_3I9_wc\"]")
	public List<WebElement> actualProductAmount;
}
