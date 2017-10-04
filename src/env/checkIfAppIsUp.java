package env;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver; 
import org.testng.annotations.Test;
import org.testng.Assert;

// This class is meant to see if the app itself is up and ready for testing.

public class checkIfAppIsUp {
	
	@Test
	public void isAppUp() {
    	System.setProperty("webdriver.gecko.driver","C:\\webdrivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();   	
        driver.get("http://localhost:8080/");
        Assert.assertEquals(driver.getTitle(),"This is my title");
        driver.close();
	}
}
