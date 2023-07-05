package stepDefinetion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import flipkart_Utilities.BrowserUtils;
import flipkart_Utilities.Driver;
import flipkart_pages.searchPage;
import io.cucumber.java.en.*;

public class searchProduct_StepDef extends BrowserUtils {
	searchPage SearchPage = new searchPage();
	private Actions action = new Actions(Driver.getDriver());
	String productName1;
	String productName;	
	int ProductAmount,ProductAmount1,productCharge,discountCharge,diliveryCharge,totalProductAmountOnCart,totalSumOnCart;
	
	
	@Given("Search with product as {string}")
	public void search_With_Product(String item) {
		waitFor(2);
		action.sendKeys(Keys.ESCAPE).build().perform();
		waitFor(3);
		SearchPage.searchText.click();
		SearchPage.searchText.sendKeys(item, Keys.ENTER);
		waitFor(2);
	}

	@Then("i will click on price Low to High")
	public void i_Will_Click_On_Price_Low_To_High() {
		jsClick(SearchPage.priceLowToHigh);
		waitFor(2);
	}

	@Then("I should see the prices for all products till Page {int} are in ascending order")
	public void validatePricesAscendingOrder(int pageLimit) {
		List<Integer> prices = new ArrayList<>();

		for (int page = 1; page <= pageLimit; page++) {
			waitFor(5);
			for (WebElement priceElement : SearchPage.elementsRow) {
				String priceText = priceElement.getText();
				Integer price = Integer.parseInt(priceText.replaceFirst("₹", "").replaceFirst(",", ""));
				prices.add(price);
			}
			if (page < pageLimit) {
				scrollDown();
				SearchPage.nextButton.click();
			}
		}

		// Validate the prices are in ascending order
		for (int i = 0; i < prices.size() - 1; i++) {
			if (prices.get(i) > prices.get(i + 1)) {
				throw new AssertionError("Prices are not in ascending order.");
			}
		}
	}
	 @When("Click on the second available product in the list, and click on Add to cart")
	    public void addToCartSecondProduct() {
	        WebElement secondProduct = SearchPage.elementsRow.get(1);
	        String mainWindow = Driver.getDriver().getWindowHandle(); // Store the main window handle
	        secondProduct.click();
	        productName=SearchPage.elementName.get(1).getText();
	        ProductAmount=Integer.parseInt(SearchPage.elementsRow.get(1).getText().replaceFirst("₹", "").replaceFirst(",", ""));
	        // Switch to the new window
	        Set<String> windowHandles = Driver.getDriver().getWindowHandles();
	        for (String windowHandle : windowHandles) {
	            if (!windowHandle.equals(mainWindow)) {
	                Driver.getDriver().switchTo().window(windowHandle);
	                break;
	            }
	        }

	        SearchPage.addCartButton.click();
	        waitFor(3);

	        // Close the current window and switch back to the main window
	        Driver.getDriver().close();
	        Driver.getDriver().switchTo().window(mainWindow);
	 }

	    @When("Click on the third available product in the list, and click on Add to cart")
	    public void addToCartThirdProduct() {
	        // Close the current window and switch back to the main window
	    	String mainWindow = Driver.getDriver().getWindowHandle(); 

	        WebElement thirdProduct = SearchPage.elementsRow.get(2);
	        thirdProduct.click();
	        productName1=SearchPage.elementName.get(2).getText();
	        ProductAmount1=Integer.parseInt(SearchPage.elementsRow.get(2).getText().replaceFirst("₹", "").replaceFirst(",", ""));
	        // Switch to the new window opened by clicking on the product
	        for (String handle : Driver.getDriver().getWindowHandles()) {
	            if (!handle.equals(mainWindow)) {
	                Driver.getDriver().switchTo().window(handle);
	                break;
	            }
	        }
	        
	        // Add the product to the cart
	        SearchPage.addCartButton.click();
	        waitFor(3);
	        Driver.getDriver().close();
	        Driver.getDriver().switchTo().window(mainWindow);
	    }
	    
	    @Then("I will click on Cart and move to Cart page")
	    public void i_Will_Move_To_Cart_Page(){
	    	jsClick(SearchPage.cart);
	    }
	    @Then("validate the correct products are added with correct Price")
	    public void validate_Correct_Produce_Are_Added_With_Correct_Price() {
	    	String cartProductName= SearchPage.selectedProductName.get(0).getText();
	    	int cartProductAmount= Integer.parseInt(SearchPage.selectedProductSize.get(0).getText().replaceFirst("₹", "").replaceFirst(",", ""));
	    	String cartProductName1= SearchPage.selectedProductName.get(1).getText();
	    	int cartProductAmount1= Integer.parseInt(SearchPage.selectedProductSize.get(1).getText().replaceFirst("₹", "").replaceFirst(",", ""));
	    	System.out.println("ProductName-> "+cartProductName);
	    	Assert.assertEquals(ProductAmount,cartProductAmount1,"Product Name is incorrect");
	    	System.out.println("ProductName1-> "+cartProductName1);
	    	Assert.assertEquals(ProductAmount1,cartProductAmount,"Product Name is incorrect");
	    }
	    @And("validate the Total sum")
	    public void validate_The_Total_Sum() {
	    	int totalSum=ProductAmount+ProductAmount1;
	    	WebElement amount=Driver.getDriver().findElement(By.xpath("//div[@class=\"z4Ha90\"]//span"));
	    	List<WebElement> diliveryChargeAmount=Driver.getDriver().findElements(By.xpath("//span[@class=\"_1YQFQF\"]"));
	    	productCharge=Integer.parseInt(diliveryChargeAmount.get(0).getText().replaceFirst("₹", "").replaceFirst(",", "").replaceFirst("− ", ""));
	    	discountCharge=Integer.parseInt(diliveryChargeAmount.get(1).getText().replaceFirst("₹", "").replaceFirst(",", "").replaceFirst("− ", ""));
	    	diliveryCharge=Integer.parseInt(diliveryChargeAmount.get(2).getText().replaceFirst("₹", "").replaceFirst(",", ""));
	    	totalProductAmountOnCart=Integer.parseInt(amount.getText().replaceFirst("₹", "").replaceFirst(",", ""));
	    	totalSumOnCart=productCharge-discountCharge+diliveryCharge;
	    	System.out.println("Total Sum-> "+totalSumOnCart);
	    	int CartTotalSun=productCharge-discountCharge;
	    	Assert.assertEquals(totalSum, CartTotalSun,"Total Sum is not same");
	    	Assert.assertEquals(totalProductAmountOnCart, totalSumOnCart,"Total Sum before discount is not same");
	    	
	    	
	    }

}
