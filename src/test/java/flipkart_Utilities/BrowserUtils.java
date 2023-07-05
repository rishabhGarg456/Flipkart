package flipkart_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;
public class BrowserUtils {
	private static WebDriver driver;

	public String stepinfo;

	public BrowserUtils() {
		this.driver = Driver.getDriver();
		PageFactory.initElements(driver, this);
	}

	public static void waitFor(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public boolean isElementDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void jsClick(WebElement element) {
		try {
			waitFor(2);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			System.out.println(" Came to exception on jsClick - " + e.getMessage());
			System.out.println(" Trying to click using webdriver ");
			element.click();
		}
	}

	public static void scrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
		jse.executeScript("scrollTo(0, 3700);");
	}

}
