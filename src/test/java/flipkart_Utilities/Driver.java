package flipkart_Utilities;

import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Driver {
	
	private static WebDriver driver;
	public static WebDriver getDriver() {
		if (driver == null) {
			if (System.getProperty("browser") == null) {
				System.setProperty("browser", "chrome");
			}
			switch (System.getProperty("browser")) {

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "chrome":
				WebDriverManager.chromedriver().setup();
				System.setProperty("webdriver.chrome.silentOutput", "true");
				ChromeOptions localChromeOptions = new ChromeOptions();
				//Maximized Window
				localChromeOptions.addArguments("--start-maximized");
				localChromeOptions.addArguments("--disable-cache");
				LoggingPreferences loggingPreferences = new LoggingPreferences();
				loggingPreferences.enable(LogType.PERFORMANCE, Level.ALL);
				localChromeOptions.setCapability("goog:loggingPrefs", loggingPreferences);
				driver = new ChromeDriver(localChromeOptions);
				break;
			default:
				Assert.fail("browser  = " + System.getProperty("browser") + " | Please check spelling");
			}
			driver.get(configurationReader.getProperty("flipkartURL"));
		}
		return driver;
	}

	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
