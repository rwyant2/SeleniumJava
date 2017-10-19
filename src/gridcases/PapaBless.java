package gridcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeOptions;

// The original example I plagarized had an abstract class. This caused adventure because the childlins
// didn't have a parent driver to refer to by the time annotations involving the driver were being kicked off.
// Using getters didn't help. I had to revert to shenanigans that made the point of a parent class m00t.
// I want all this stuff in one place so I can encapsulate parnemamitrazation logic. With this as something real,
// the kids can refer to the driver here once I figure out which driver to use.
public class PapaBless {
	// pernaminate these
	private static String localUrl = "http://localhost:8080";
	private static int timeout = 10;
	protected WebDriverWait wait;  //for the child classes
	
	private DesiredCapabilities capability; 
	// private static String remoteUrl = "http://localhost:4444/wd/hub"; // for localhost
	private static String hubUrl = "http://10.40.98.44";
	private static String nodeUrl = "http://10.40.98.44"; 
	private static String connectToHubUrl = hubUrl + ":4444/wd/hub"; 
	private static String landingPageUrl = hubUrl+ ":8080";
	private URL url;
	
	protected WebDriver driver; //for the child classes
	
	private boolean onGrid = true;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverWait getWait() {
		return wait;
	}
	
	public String getLocalURL() {
		return localUrl;
	}
	
	@BeforeSuite // before each <suite> in the xml
	public void beforeSuite() {
		System.out.println("@BeforeSuite kicks off");
	}

	@BeforeTest // before each <test> in the xml
	public void beforeTest() {
		System.out.println("@BeforeTest kicks off");
	}
	
	@BeforeClass //before each <class> in the xml
	public void beforeClass() {
		System.out.println("@BeforeClass kicks off for " + this.getClass().getName());
		// parmeticize this, for now hard coding it to onGrid
		// then let parameters decide which flavor of driver we're using
		if(onGrid) {
			capability = new DesiredCapabilities();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.VISTA);
			try {
				url = new URL(connectToHubUrl);
			} catch(Exception e) {
				//i dunno, lol
			}
			driver = new RemoteWebDriver(url,capability);
			driver.get(landingPageUrl);
		} else {	
			System.setProperty("webdriver.gecko.driver","C:\\webdrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.get(localUrl);
		}
		wait = new WebDriverWait(driver, timeout);
	}
	
	@BeforeMethod // before each @Test method in this class
	public void beforeMethod() {
		System.out.println("@BeforeMethod kicks off");
		driver.findElement(By.xpath("//a[@href='/html5']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkBoxSelection1']")));
	}
	
	@AfterMethod // after each @Test method in this class
	public void afterMethod() {
		System.out.println("@AfterMethod kicks off");
	}
	
	@AfterTest // after each </test> in the xml
	public void afterTest() {
		System.out.println("@AfterTest kicks off");
	}
	
	@AfterClass // after each </class> in the xml
	public void afterClass() {
		System.out.println("@AfterClass kicks off");
		driver.close();
	}
	
	@AfterSuite // after each </suite> in the xml
	public void afterSuite() {
		System.out.println("@AfterSuite kicks off");
	}
}
