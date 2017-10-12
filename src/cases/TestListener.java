package cases;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import org.openqa.selenium.WebDriver;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestListener implements ITestListener {
	
	@Override
	public void onTestStart(ITestResult result) { // before each @Test method 
		System.out.println("onTestStart kicks off for " + result.getMethod());
	}

	@Override
	public void onTestSuccess(ITestResult result) { // after each successful @Test method
		System.out.println("onTestSuccess kicks off for " + result.getMethod());
	}

	@Override
	public void onTestFailure(ITestResult result) { // after each failed @Test method
		System.out.println("onTestFailure kicks off for " + result.getMethod());
		Object currentClass = result.getInstance();
		WebDriver driver = ((BaseCase)currentClass).getDriver();
		TakesScreenshot driverWithScrShot = (TakesScreenshot)driver; // cast driver obj as driver obj with screenshot
		File scrShot = driverWithScrShot.getScreenshotAs(OutputType.FILE); // get the actual screenshot
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy_hh-mm-ss-SSS"); // format for date
		Date date = new Date(); // get current datetime
		String timestamp = new String(dateFormat.format(date));
		try {
			FileUtils.copyFile(scrShot, new File("C:\\Users\\Richard\\Desktop\\screenshots\\Selenium_fun_times_" + //parameterize this 		 
				timestamp + ".png")); 
				//"_with_" + result.getMethod() + ".png")); // get the @Test method that failed
		} catch(Exception e) {
			System.out.println("screenshot of " + timestamp + " failed.");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("onTestSkipped kicks off for " + result.getMethod());
		Object currentClass = result.getInstance();
		WebDriver driver = ((CheckBoxCase)currentClass).getDriver();
		TakesScreenshot driverWithScrShot = (TakesScreenshot)driver; // cast driver obj as driver obj with screenshot
		File scrShot = driverWithScrShot.getScreenshotAs(OutputType.FILE); // get the actual screenshot
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy_hh-mm-ss-SSS"); // format for date
		Date date = new Date(); // get current datetime
		String timestamp = new String(dateFormat.format(date));
		try {
			FileUtils.copyFile(scrShot, new File("C:\\Users\\Richard\\Desktop\\screenshots\\Selenium_fun_times" +
				timestamp + ".png")); //parameterize this
		} catch(Exception e) {
			System.out.println("screenshot of " + timestamp + " failed.");
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("onTestFailedPercentage kicks off for " + result.getMethod());
	}

	@Override
	public void onStart(ITestContext context) { // for everything in a <test/>
		System.out.println("onStart kicks off for " + context.getName());
	}

	@Override 
	public void onFinish(ITestContext context) { 
		System.out.println("onFinish kicks off for " + context.getName());
	}
}
