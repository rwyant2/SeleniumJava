package env;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver; 
import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;

// This class is meant to see if the app itself is up and ready for testing.

public class checkIfAppIsUp {
	public static void main(String[] args) {
		// can i habe firefox please?
    	System.setProperty("webdriver.gecko.driver","C:\\webdrivers\\geckodriver.exe");
		WebDriver ffDriver = new FirefoxDriver(); 
		
		// ey b0ss, can u gibe de chrome please?
		System.setProperty("webdriver.chrome.driver","C:\\webdrivers\\2_32\\chromedriver.exe");
		WebDriver chDriver = new ChromeDriver();
		
		// b0ss! i habe esplorer, b0ss!
		System.setProperty("webdriver.ie.driver", "C:\\webdrivers\\IEDriverServer.exe");
		WebDriver ieDriver = new InternetExplorerDriver();
    	
        String baseUrl = "http://localhost:8080/";
        String expectedTitle = "This is my title";
        String actualTitle = "";

        // launch the browser and direct it to the Base URL
        ffDriver.get(baseUrl);

        // get the actual value of the title
        actualTitle = ffDriver.getTitle();

        /*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Firefox Passed!");
        } else {
            System.out.println("Firefox Failed");
        }
       
        //close browser
        ffDriver.close();
        
        //do the same thing for chrome
        chDriver.get(baseUrl);
        actualTitle = chDriver.getTitle();
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Chrome Passed!");
        } else {
            System.out.println("Chrome Failed");
        }
        chDriver.close();
        
        //and IE
        ieDriver.get(baseUrl);
        actualTitle = ieDriver.getTitle();
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("IE Passed!");
        } else {
            System.out.println("IE Failed");
        }
        ieDriver.close();
    }
}
