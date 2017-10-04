package cases;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.junit.Assert;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//can I paramaterize imports? or maybe make opening the driver it's own class and pass a browser object?
//import org.openqa.selenium.chrome.ChromeDriver; 
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import java.util.concurrent.TimeUnit;

public class radioButtonCase {
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='radioButtonSelection1']")));
	}

	@Test
	public void radioChoiceA() {
		driver.findElement(By.xpath("//input[@id='radioButtonSelection1']")).click();  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='radioResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='radioResult']")).getText();
		Assert.assertEquals("apple",actualText);
	}
	
	@Test
	public void radioChoiceB() {
		driver.findElement(By.xpath("//input[@id='radioButtonSelection2']")).click();  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@id='radioResult']"))); 
		actualText = driver.findElement(By.xpath("//p[@id='radioResult']")).getText();
		Assert.assertEquals("banana",actualText);
	}
	
	@Test
	public void radioChoiceC() {
		driver.findElement(By.xpath("//input[@id='radioButtonSelection3']")).click();  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='radioResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='radioResult']")).getText();
		Assert.assertEquals("cherry",actualText);
	}
	
	@Test // default
	public void radioDefault() {  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@id='radioResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='radioResult']")).getText();
		Assert.assertEquals("",actualText);
	}
}
