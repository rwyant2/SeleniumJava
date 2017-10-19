package localcases;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		System.out.println("onStart in SuiteListener kicks off for " + suite.getName());
	}
	
	@Override
	public void onFinish(ISuite suite) {
		System.out.println("onFinish in SuiteListener kicks off for " + suite.getName());
	}
	
}
