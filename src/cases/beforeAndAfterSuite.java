package cases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class beforeAndAfterSuite {
	@BeforeSuite
	public void beforeSuite() {System.out.println("Suite started");}
	@AfterSuite
	public void afterSuite() {System.out.println("Suite ended");}
}
