package localcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class BeforeAndAfterSuite {
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("@BeforeSuite kicks off");
	}
	
	// Can't depend on this. onTestFailre and onTestSkip will pass this
	@AfterSuite
	public void afterSuite() {
		System.out.println("@AfterSuite kicks off");
	}
	
}
