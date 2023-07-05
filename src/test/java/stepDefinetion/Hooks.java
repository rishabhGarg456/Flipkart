package stepDefinetion;

import java.util.concurrent.TimeUnit;
import flipkart_Utilities.BrowserUtils;
import flipkart_Utilities.Driver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import flipkart_pages.searchPage;

import io.cucumber.java.*;
import io.cucumber.java.Scenario;

public class Hooks extends BrowserUtils {
	searchPage SearchPage = new searchPage();
	
	private BrowserUtils base;
	public Hooks(BrowserUtils base) {
		this.base=base;
	}
    @Before
    public void setUp(Scenario scenario) {
    	
    	//passing a dummy webdrive instance
    	base.stepinfo="chromeDriver";
    	
        WebDriver driver = Driver.getDriver();
        System.out.println("Lunch Browser");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        }
        

    @After
    public void tearDown(Scenario scenario) {
    	if(isElementDisplayed(SearchPage.searchText)) {
    		
    	}
    	if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");  
        }   
        Driver.closeDriver();
    }
}

