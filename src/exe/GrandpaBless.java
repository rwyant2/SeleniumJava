//package exe;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.*;

import org.apache.commons.io.IOUtils;

import cases.TestListener;
import cases.SuiteListener;


// The purpose of this is the one that drives everything else for onGrid.
// Ran from the hub machine, it reads the .csv file and loops through
// until end of file.

public class GrandpaBless {
//	private String csvFile = new String();
	private String browser = new String();
	private String nodeOS = new String();
	private String nodeIP = new String();
	private static boolean everythingsSwell = true;
	private boolean eofFlag = false;
	private static String absPath = new File("").getAbsolutePath();
	private static String csvPath;
	private static String csvStream;
	private static Scanner sc;
	private static TestNG tng = new TestNG();
	private static List<XmlSuite> suites = new ArrayList<XmlSuite>();
	private static ITestNGListener sListener = new SuiteListener();
	private static ITestNGListener tListener = new TestListener();
	
	public static void main (String[] args) {
		System.out.println("Is this even running?");
		
		// For now, I'm assuming the hub machine will be my dev env on Ubutnu
		csvPath = absPath + "/src/exe/multiple.csv";
		
		try(FileInputStream fis = new FileInputStream(csvPath)) {  
			csvStream = IOUtils.toString(fis,"UTF-8");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			everythingsSwell = false;
		}
		
		if(everythingsSwell) {
			sc = new Scanner(csvStream);
			System.out.println(sc.nextLine()); // assuming 1st line is header
			while(sc.hasNext()) {
				String params = sc.nextLine();
				
//				ignore comments and empty lines
				if(params.isEmpty()) {continue;}
				if(params.substring(0,2).equals("//")) {continue;}
				
				
				int first = params.indexOf(",");
				int second = params.indexOf(",",first+1);
				int third = params.indexOf(",",second+1);				
				String nodeOS = params.substring(0, first);
				String nodeURL = params.substring(first+1, second);
				String browser = params.substring(second+1, third);
				int timeout = Integer.parseInt(params.substring(third+1, params.length()));
				suites.add(buildSuite(nodeOS,nodeURL,browser,timeout));
				TestNG tng = new TestNG();
				tng.addListener(tListener);
				tng.addListener(sListener);
				tng.setXmlSuites(suites);
				tng.run();
			}
		}
	}
	
	private static XmlSuite buildSuite(String nodeOS, String nodeURL, String browser, int timeout) {
		XmlSuite xs = new XmlSuite();
//		List<XmlTest> xTests = new ArrayList<XmlTest>();
		
		// equivalient of <parameters> tag
		Map <String, String> xp = new HashMap <String, String>();
		xp.put("nodeOSP", nodeOS);
		xp.put("nodeURLP", nodeURL);
		xp.put("browserP", browser);
		xp.put("timeoutP", Integer.toString(timeout)); // you are here
		xs.setParameters(xp);
		
		// equivalent to <class> tag
		List<XmlClass> xClasses = new ArrayList<XmlClass>();
		XmlClass xCCheckBox = new XmlClass(cases.CheckBoxCase.class);
		xClasses.add(xCCheckBox);
		
		XmlClass xCTextField = new XmlClass(cases.TextFieldCase.class);
		xClasses.add(xCTextField);

		// equivalent to <test> tag
		XmlTest xt1 = new XmlTest();
		xt1.setName("dynamic test 1"); 
		xt1.setSuite(xs);
		xt1.setClasses(xClasses); 		
		
		List<XmlClass> xClasses2 = new ArrayList<XmlClass>();
		XmlClass xCRadioButton = new XmlClass(cases.RadioButtonCase.class);
		xClasses2.add(xCRadioButton);

		XmlClass xCDropDown = new XmlClass(cases.DropDownCase.class);
		xClasses2.add(xCDropDown);

		XmlClass xCTextArea = new XmlClass(cases.TextAreaCase.class);
		xClasses2.add(xCTextArea);
		
		XmlTest xt2 = new XmlTest();
		xt2.setName("dynamic test 2"); 
		xt2.setSuite(xs);
		xt2.setClasses(xClasses2); 		
		
		xs.setName("dynamic suite for " + nodeOS + " and " + browser);
//		xs.setParallel(xs.DEFAULT_PARALLEL);
		xs.addTest(xt1);
		xs.addTest(xt2);

		return xs;
	}
}				
				//				tng.addListener();
				
				
				
				// make a virtual testng.xml
//				<suite name="text and checkbox">
//				<listeners>
//					<listener class-name="cases.SuiteListener" />
//					<listener class-name="cases.TestListener" />
//				</listeners>
//				<test name="text field and check box">
//					<classes>
//						<class name="cases.TextFieldCase" />
//						<class name="cases.CheckBoxCase" />
//					</classes>
//				</test>
//				<test name="everything else">
//					<classes>
//						<class name="cases.RadioButtonCase" />
//						<class name="cases.DropDownCase" />
//						<class name="cases.TextAreaCase" />
//					</classes>
//				</test>
//			</suite> 
				
				
				
				
				
				
				
				
				
				
				
//				System.out.println(nodeOS + nodeURL + browser + timeout); // for debugging
//				System.out.println(); // for debugging
				
			
			
			 
			// the first row is assumed to be a header
			// create new scanner object
			// set deleimiter
			// read.ant@riseupnext see what happens

		
	
//	open the cvs
//	do until eof or not swell {
//		read the file
//		pas params to xml suite
//		execute xml suite
//		read next
