package cases;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.Keys;

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

public class textAreaCase {
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='textArea']")));
	}	
		
	@Test
	public void textAreaLongString() {
		String inputText = "I can't think of anything clever to say here, either."
				+ "I wish the bus wasn't so rough to ride on. They're all drove like the General Lee."
				+ "It's hard to type when the bus is all over the place like that";
		driver.findElement(By.name("textArea")).sendKeys(inputText);  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='textAreaResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='textAreaResult']")).getText();
		Assert.assertEquals(inputText,actualText);
	}
		
	@Test
	public void textAreaWithEnter() {
		String[] inputTextWithEnter = new String [3];
		inputTextWithEnter[0] = "I can't think of anything clever to say here, either.";
		inputTextWithEnter[1] = "I wish the bus wasn't so rough to ride on. They're all drove like the General Lee.";
		inputTextWithEnter[2] = "It's hard to type when the bus is all over the place like that";  
		driver.findElement(By.name("textArea")).sendKeys(inputTextWithEnter[0]);
		driver.findElement(By.name("textArea")).sendKeys(Keys.RETURN);
		driver.findElement(By.name("textArea")).sendKeys(inputTextWithEnter[1]);
		driver.findElement(By.name("textArea")).sendKeys(Keys.RETURN);
		driver.findElement(By.name("textArea")).sendKeys(inputTextWithEnter[2]);
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='textAreaResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='textAreaResult']")).getText();
		Assert.assertEquals(inputTextWithEnter[0] + " "
				+ inputTextWithEnter[1] + " " 
				+ inputTextWithEnter[2],actualText);		
	}
}
