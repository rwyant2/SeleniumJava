//package exe;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import bricks.MethodListener;
import bricks.SuiteListener;
import bricks.TestListener;


// This is the one that drives everything and is ran from the hub machine.
// * Reads the .csv file and loops through until no more configs.
// * TODO: Reads options.txt to determine environmental options.

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
//	private static ITestNGListener mListener = new MethodListener();
	
	public static void main (String[] args) {
	
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
				String timeout = params.substring(third+1, params.length());
				suites.add(buildSuite(nodeOS,nodeURL,browser,timeout));
			}
			
			TestNG tng = new TestNG();
//			tng.addListener(mListener);
			tng.addListener(tListener);
			tng.addListener(sListener);
			tng.setXmlSuites(suites);
			tng.run();
		}
	}
	
	private static XmlSuite buildSuite(String nodeOS, String nodeURL, String browser, String timeout) {
		XmlSuite xs = new XmlSuite();
		//TODO: automate this so I just dump a case in the cases.package and not have to edit this
		
		// equivalient of <parameters> tag
		Map <String, String> xp = new HashMap <String, String>();
		xp.put("nodeOSP", nodeOS);
		xp.put("nodeURLP", nodeURL);
		xp.put("browserP", browser);
		xp.put("timeoutP", timeout); // you are here
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
		
		xs.setName("dynamic suite with "
			+ nodeOS + "/"
			+ nodeURL + "/"
			+ browser + "/"
			+ timeout
		);
		xs.addTest(xt1);
		xs.addTest(xt2);
		xs.setParallel(XmlSuite.ParallelMode.NONE);// TestNG 6.13 version
//		xs.setParallel(XmlSuite.PARALLEL_NONE); // TestNG 6.8 version
		
		return xs;
	}
}				
