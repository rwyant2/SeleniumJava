package cases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver; 
import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;

public class textFieldCase {
	
	private static String url = "http://localhost:8080/html5";
	
	public static void main(String[] args) {
		// 
    	System.setProperty("webdriver.gecko.driver","C:\\webdrivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get(url);
	}

}
