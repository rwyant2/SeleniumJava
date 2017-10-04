package cases;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//can I paramaterize imports? or maybe make opening the driver it's own class and pass a browser object?
//import org.openqa.selenium.chrome.ChromeDriver; 
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import java.util.concurrent.TimeUnit;

public class textFieldCase {
	// parameterize this
	private static String url = "http://localhost:8080";
	private static int timeout = 10;
	WebDriver driver = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(driver, timeout);
	private static String actualText;
	private static String inputText = "I can't think of anything clever to say here.";
	
	@BeforeTest // before everything else here
	public void startBrowser() {
		// parameterize this
    	System.setProperty("webdriver.gecko.driver","C:\\webdrivers\\geckodriver.exe");
		driver.get(url);
	}

	@AfterTest // after everything else here
	public void closeBrowser() {
		driver.close();
	}
	
	@BeforeMethod
	public void goToHTML5Page() {
		driver.findElement(By.xpath("//a[@href='/html5']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkBoxSelection1']")));
	}
	
	@Test
	public void testTextField() {	
		String inputText = "It's just a prank, bro.";
		driver.findElement(By.name("textField")).sendKeys(inputText);  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='textResult']")));
		// reminder: assert with TestNG fails and stops, verify with JUnit fails and continues
		actualText = driver.findElement(By.xpath("//p[@id='textResult']")).getText();
		Assert.assertEquals(inputText,actualText);
	}
}
