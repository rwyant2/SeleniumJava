package cases;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.File;
import java.net.URL;

import java.net.InetAddress;

//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebElement;
//import java.io.InputStreamReader;

// The original example I plagarized had an abstract class. This caused adventure because the childlins
// didn't have a parent driver to refer to by the time annotations involving the driver were being kicked off.
// Using getters didn't help. I had to revert to shenanigans that made the point of a parent class m00t.
// I want all this stuff in one place so I can encapsulate parnemamitrazation logic. With this as something real,
// the kids can refer to the driver here once I figure out which driver to use.
public class PapaBless {
	private String localUrl;
	private static int timeout;
	private static String timeoutValue;
	protected WebDriverWait wait;  //for the child classes
	
	private DesiredCapabilities capability; 
	private static String hubUrl;
	private static String nodeUrl;
	private static String landingPageUrl;
	private static String browser;
	private static String hubOS;
	private static String nodeOS;
	private String absPath = new File("").getAbsolutePath();
	private String optionsPath;
	private static String OS;
	
	protected boolean everythingsSwell = true;
	
	private URL url;
	
	protected WebDriver driver; //for the child classes
	
	private static boolean onGrid = false;
	
	private String options;
	private String multiple;
		
	private void someoneSetUsUpTheDriver(String nodeOSP, String nodeUrlP, String browserP, String timeoutValue) {
		
		hubOS = System.getProperty("os.name");
		
		if(hubOS.equals("Linux")) {
			optionsPath = absPath + "/src/cases/options.txt";
		} else {
			optionsPath = absPath + "\\src\\cases\\options.txt";
		}
		
		try(FileInputStream optStream = new FileInputStream(optionsPath)) {  
			options = IOUtils.toString(optStream,"UTF-8");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			everythingsSwell = false;
		}

	    if(everythingsSwell) {
	    	try { 
	    		timeout = Integer.parseInt(timeoutValue);
	    	} catch (Exception e) {
	    		System.out.println(timeoutValue + " is not a nubmer for timeout. You are silly.");
	    		everythingsSwell = false;
	    	}
	    }
	    
	    if(everythingsSwell) {
	    	if(validateIPAddr(nodeUrlP)) {
	    		if(nodeUrlP.matches("127.0.0.1|localhost")) {
	    			onGrid = false;
	    			nodeUrl = nodeUrlP;
	    		} else {
	    			onGrid = true;
	    			nodeUrl = "http://" + nodeUrlP + ":5555/wd/hub";
	    		}	
	    	} else {
	    		everythingsSwell = false;
	    	}
		}
	    
	    if(everythingsSwell) {
	    	if(!browserP.equals("firefox") && !browserP.equals("chrome") && !browserP.equals("internet explorer")) {
	    		System.out.println(browser + " is not a recognized browser. Defaulting to firefox");
	    		browser = "firefox";
	    	} else {
	    		browser = browserP;
	    	}
	    }
	    
	    if(everythingsSwell) {
	    	if(onGrid) {
	    		if(!nodeOSP.equals("win7") && !nodeOSP.equals("win10") && !nodeOSP.equals("linux")) {
	    			System.out.println(nodeOS + " is not a recognized nodeOS. Defaulting to win 7");
	    			nodeOS = "win7";
	    		} else {
	    		nodeOS = nodeOSP;
	    		}
	    	}
	    	
	    	if(!onGrid) {nodeOS = hubOS;}
	    }
	    	    
	    if(everythingsSwell) {
	    	try {
	    		InetAddress localhost = InetAddress.getLocalHost();
	    		landingPageUrl = localhost.getHostAddress().trim() + ":8080";
	    	} catch (Exception e) {
	    		System.out.println(e.getMessage());
	    		everythingsSwell = false;
	    	}

	    	
//	    if(everythingsSwell) {
//	    	if(onGrid && (validateIPAddr(hubUrl)) {
////	    		landingPageUrl = "http://" + hubUrl + ":8080";
//	    	
	    }
	    	
//	    	} 
		
		
//	    if(everythingsSwell && onGrid) {


	    if(everythingsSwell) {
	    	System.out.println("**************************** Fun tiems for all");
	    	if(onGrid) {
	    		System.out.println("nodeOS:" + nodeOS + " browser:" + browser + " timeout:" + timeoutValue + " onGrid:" + onGrid);
//	    		System.out.println("hubUrl = " + hubUrl);
	    		System.out.println("landingPageUrl = " + landingPageUrl);
	    		System.out.println("nodeUrl = " + nodeUrl);
	    	} else {
	    		System.out.println("hubOS:" + hubOS + " browser:" + browser + " timeout:" + timeoutValue + " onGrid:" + onGrid);
	    	}
	    	System.out.println("****************************");
	    }
	}

	private String getParmValue(String parm) {
		int start = options.indexOf(parm);
		start = options.indexOf("=",start);
		start = options.indexOf("'",start) + 1;
	    int end = options.indexOf("'",start);
	    return options.substring(start , end).toLowerCase();
	}
	
	private static boolean validateIPAddr(String parm) {
		
		if(parm.equals("localhost")) { return true; }
		
		if(parm.contains(":")) {
			System.out.println("ey b0ss, no http or port, please: " + parm);
			return false;
		}
		
		int firstDot = parm.indexOf(".",1);
		int secondDot = parm.indexOf(".",firstDot + 1);
		int thirdDot = parm.indexOf(".",secondDot + 1);
		try {
			int number1 = Integer.parseInt(parm.substring(0,firstDot));
			int number2 = Integer.parseInt(parm.substring(firstDot + 1,secondDot));
			int number3 = Integer.parseInt(parm.substring(secondDot + 1,thirdDot));
			int number4 = Integer.parseInt(parm.substring(thirdDot + 1));
			if((number1 < 256) && (number2 < 256) && (number3 < 256) && (number4 < 256) ) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(parm + " is not a valid IP address. You are silly.");
			return false;
		}
		return false; // default
	}	
		
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
	@Parameters ({"nodeOSP", "nodeURLP", "browserP", "timeoutP"})
	public void beforeSuite(String nodeOSP, String nodeURLP, String browserP, String timeoutP) {
		System.out.println("@BeforeSuite kicks off for " + this.getClass().getName());
		someoneSetUsUpTheDriver(nodeOSP, nodeURLP, browserP, timeoutP);
	}

	@BeforeTest // before each <test> in the xml
	public void beforeTest() {
		System.out.println("@BeforeTest kicks off");
	}


	@BeforeClass //before each <class> in the xml
	public void beforeClass() {
		System.out.println("@BeforeClass kicks off for " + this.getClass().getName());

		if(onGrid) {
			capability = new DesiredCapabilities();
			capability.setBrowserName(browser);
			switch(nodeOS) {
				case "win7": capability.setPlatform(Platform.VISTA); break;
				case "win10": capability.setPlatform(Platform.WIN10); break;
				case "linux": capability.setPlatform(Platform.LINUX); break;
				default: capability.setPlatform(Platform.VISTA); break;
			}

			capability.setVersion("latest");
			
			try {
				url = new URL(nodeUrl);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			try {
				System.out.println("*****************************Trying to connect to " +
						nodeUrl + " " + nodeOS + " " + browser);
				driver = new RemoteWebDriver(url,capability);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			driver.get(landingPageUrl);
		}
		
		if (!onGrid) {
			String exeExt = new String();
			String driverPath = new String();
			if(hubOS.equals("Linux")) {
				exeExt = "";
				driverPath = "/webdrivers/";
			} else {
				exeExt = ".exe";
				driverPath = "C:\\webdrivers\\";
			}
			switch(browser) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver",driverPath + "geckodriver" + exeExt);
				driver = new FirefoxDriver();
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver",driverPath + "chromedriver" + exeExt);
				ChromeOptions ugh = new ChromeOptions();
				ugh.addArguments("disable-infobars");
//				// On Linux start-maximized does not expand browser window to max screen size. Always set a window size and position.
//				if (platform_name.equalsIgnoreCase("linux")) {
//					options.addArguments(Arrays.asList("--window-position=0,0"));
//					options.addArguments(Arrays.asList("--window-size=1920,1080"));	
//					} else {
//					options.addArguments(Arrays.asList("--start-maximized"));
//					}
//				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//				} 
				driver = new ChromeDriver(ugh);
				break;
			case "internet explorer":
				System.setProperty("webdriver.internetexplorer.driver","C:\\webdrivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;
			}
			
			driver.get("http://localhost:8080");
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
