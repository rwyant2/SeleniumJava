package localcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.*;

public abstract class BaseCase {
	// parameterize these
	private static String url = "http://localhost:8080";
	private static int timeout = 10;
	private WebDriver driver = new FirefoxDriver();
	private WebDriverWait wait = new WebDriverWait(driver, timeout);
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverWait getWait() {
		return wait;
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("@BeforeClass kicks off");
		// parameterize this
		System.out.println("@BeforeTest kicks off");  //paramaterize this
		System.setProperty("webdriver.gecko.driver","C:\\webdrivers\\geckodriver.exe");
		driver.get(url);
	}
	
	@BeforeTest // before every @Test in this suite
	public void beforeTest() {
		System.out.println("@BeforeTest kicks off");
	}
	
	@BeforeMethod // before each @Test method in this class
	public void beforeMethod() {
		System.out.println("@BeforeMethod kicks off");
		driver.findElement(By.xpath("//a[@href='/html5']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkBoxSelection1']")));
	}
	
	@AfterMethod // before each @Test method in this class
	public void afterMethod() {
		System.out.println("@AfterMethod kicks off");
	}
	
	// Can't depend on this. If I get an onTestFailure or onTestSkip, this can fail to kick off.
	@AfterTest
	public void afterTest() {
		System.out.println("@AfterTest kicks off");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("@AfterClass kicks off");
		driver.close();
	}
}
