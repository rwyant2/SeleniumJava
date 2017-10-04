package cases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class checkBoxCase {
	// parameterize these
	private static String url = "http://localhost:8080";
	private static int timeout = 10;
	WebDriver driver = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(driver, timeout);
	
	private static String actualText;
	
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
	public void checkBoxOneTwo() {			
		driver.findElement(By.xpath("//input[@id='checkBoxSelection1']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection2']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		Assert.assertTrue(actualText.contains("one"));
		Assert.assertTrue(actualText.contains("two"));
		Assert.assertFalse(actualText.contains("three"));
	}

	@Test
	public void checkBoxTwoThree () {
//		driver.findElement(By.xpath("//a[@href='/html5']")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkBoxSelection1']")));
		driver.findElement(By.xpath("//input[@id='checkBoxSelection2']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection3']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertFalse(actualText.contains("one"));
		assertTrue(actualText.contains("two"));
		assertTrue(actualText.contains("three"));
	}

	@Test
	public void checkBoxOneThree() {
		driver.findElement(By.xpath("//input[@id='checkBoxSelection1']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection3']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		// reminder: assert with TestNG fails and stops, verify with JUnit fails and continues
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertTrue(actualText.contains("one"));
		assertFalse(actualText.contains("two"));
		assertTrue(actualText.contains("three"));
	}

	@Test
	public void checkBoxAll() {
		driver.findElement(By.xpath("//input[@id='checkBoxSelection1']")).click();
		driver.findElement(By.xpath("//input[@id='checkBoxSelection2']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection3']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertTrue(actualText.contains("one"));
		assertTrue(actualText.contains("two"));
		assertTrue(actualText.contains("three"));
	}
	
	@Test
	public void checkBoxNone() {	
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//This is going to empty, hence not visible.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@id='checkBoxResult']"))); 
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertFalse(actualText.contains("one"));
		assertFalse(actualText.contains("two"));
		assertFalse(actualText.contains("three"));
	}
}
