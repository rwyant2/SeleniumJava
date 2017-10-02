package cases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.openqa.selenium.support.ui.WebDriverWait;

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

public class checkBoxCase {
	// parameterize these two
	private static String url = "http://localhost:8080/html5";
	private static int timeout = 10;
	
	private static String actualText;
	
	
	public static void main(String[] args) {
		// parameterize this
    	System.setProperty("webdriver.gecko.driver","C:\\webdrivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		driver.get(url);		
		driver.findElement(By.xpath("//input[@id='checkBoxSelection1']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection2']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		// reminder: assert with TestNG fails and stops, verify with JUnit fails and continues
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertTrue(actualText.contains("one"));
		assertTrue(actualText.contains("two"));
		assertFalse(actualText.contains("three"));

		driver.findElement(By.xpath("//a[@href='/html5']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkBoxSelection1']")));
		driver.findElement(By.xpath("//input[@id='checkBoxSelection2']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection3']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		// reminder: assert with TestNG fails and stops, verify with JUnit fails and continues
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertFalse(actualText.contains("one"));
		assertTrue(actualText.contains("two"));
		assertTrue(actualText.contains("three"));
		
		driver.findElement(By.xpath("//a[@href='/html5']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkBoxSelection1']")));
		driver.findElement(By.xpath("//input[@id='checkBoxSelection1']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection3']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		// reminder: assert with TestNG fails and stops, verify with JUnit fails and continues
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertTrue(actualText.contains("one"));
		assertFalse(actualText.contains("two"));
		assertTrue(actualText.contains("three"));

		driver.findElement(By.xpath("//a[@href='/html5']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkBoxSelection1']")));
		driver.findElement(By.xpath("//input[@id='checkBoxSelection1']")).click();
		driver.findElement(By.xpath("//input[@id='checkBoxSelection2']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection3']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		// reminder: assert with TestNG fails and stops, verify with JUnit fails and continues
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertTrue(actualText.contains("one"));
		assertTrue(actualText.contains("two"));
		assertTrue(actualText.contains("three"));

		driver.findElement(By.xpath("//a[@href='/html5']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkBoxSelection1']")));
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@id='checkBoxResult']"))); //This is going to empty, hence not visible.
		// reminder: assert with TestNG fails and stops, verify with JUnit fails and continues
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertFalse(actualText.contains("one"));
		assertFalse(actualText.contains("two"));
		assertFalse(actualText.contains("three"));
		
		driver.close();
	}
}
