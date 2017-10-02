package cases;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

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

public class dropDownCase {
	// parameterize these two
	private static String url = "http://localhost:8080/html5";
	private static int timeout = 10;
	
	private static String inputText = "I can't think of anything clever to say here.";
	private static String actualText;
	
	public static void main(String[] args) {
		// parameterize this
    	System.setProperty("webdriver.gecko.driver","C:\\webdrivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		// Test 1st drop down selection as default
		driver.get(url);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='dropDownSelection']")));
		Select dropDown = new Select(driver.findElement(By.xpath("//select[@id='dropDownSelection']")));  
		
		dropDown.selectByVisibleText("mustard");
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='dropDownResult']")));
		// reminder: assert with TestNG fails and stops, verify with JUnit fails and continues
		actualText = driver.findElement(By.xpath("//p[@id='dropDownResult']")).getText();
		assertEquals("yellow",actualText);

		// Test 2nd drop down selection
		driver.findElement(By.xpath("//a[@href='/html5']")).click();
		Select dropDown2 = new Select(driver.findElement(By.xpath("//select[@id='dropDownSelection']")));  
		dropDown2.selectByVisibleText("tomato");
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='dropDownResult']")));
		// reminder: assert with TestNG fails and stops, verify with JUnit fails and continues
		actualText = driver.findElement(By.xpath("//p[@id='dropDownResult']")).getText();
		assertEquals("red",actualText);
				
		// Test 3rd drop down selection
		driver.findElement(By.xpath("//a[@href='/html5']")).click();
		Select dropDown3 = new Select(driver.findElement(By.xpath("//select[@id='dropDownSelection']")));  
		dropDown3.selectByVisibleText("onions");
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='dropDownResult']")));
		// reminder: assert with TestNG fails and stops, verify with JUnit fails and continues
		actualText = driver.findElement(By.xpath("//p[@id='dropDownResult']")).getText();
		assertEquals("white",actualText);

		// Test defaulting to 1st drop down selection
		driver.findElement(By.xpath("//a[@href='/html5']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='dropDownResult']")));
		// reminder: assert with TestNG fails and stops, verify with JUnit fails and continues
		actualText = driver.findElement(By.xpath("//p[@id='dropDownResult']")).getText();
		assertEquals("yellow",actualText);		
		
		driver.close();
	}
}
