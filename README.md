"# SeleniumJava" 

04Aug2018

<b>Accomplishments:</b><br>
☑ Graceful error handling out when goofy params are in the .csv. I was banging my head against issues with SkipException. It kept skipping then reporting a failure. After I found this:<br>
<br>
https://github.com/cbeust/testng/issues/1632<br>
<br>
I took it out and it's skipping without failing exactly as I want. This was me most of last weekend:<br>
<br>
https://jimmyleo85.files.wordpress.com/2015/01/code-works.jpg<br>
<br>
☑ New package organization for easier implementation of TestNG classes:<br>
* cases: Classes with @Test methods. The idea is if I add a new testing class, I add it here and encapsulate it from...<br>
* bricks: Building bricks that the cases and GrandpaBless will use like listeners(suite, test, etc) and the PapaBless class, which classes in the cases package extend.<br>
* exe: What is actually ran plus configuration files:<br>
  multiple.csv: multiple configurations to run against<br>
  options.txt: options for the environment (w.i.p.)<br>
* recyclingbin: stuff I'm afraid to delete yet<br>
* resources: original .xml files for the suites<br>
<br>
☑ Screenshots are now included with default TestNG reports. When going to the index.html of testng's reporter output, they are under the "Reporter output" link<br>
<br>
☑ Also under that link are error messages as to why tests may be getting skipped. If I'm running this in reality, I'm not going to be staring at a console. I want to kick it off, spend time getting mad at XCOM 2, then come back to see what happened.<br>
<br>
Reporter.log isn't designed to work with @Before and @After annotations, which is where a lot of pre-emptive error handling occurs in PapaBless. I was already using getters to detect whether a webdriver was kicked off via PapaBless, so I did the same thing to pass a string with an approriate error message via a getter to the TestListener where Reporter.log can put it where I want it.<br>
<br>
I did it and I'm proud.<br>
<br>
☑ Merging all this into master.<br>
<br>
☑ A .gitignore that makes sense now, thanks be to https://github.com/github/gitignore/blob/master/Global/Eclipse.gitignore<br>
<br>
☑ Cleaning up old screenshots and test report output generated because I didn't have something like this sooner.<br>
<br>
<b>Major to dos:</b><br>
☐ Fix an Ubuntu 18.04 node I'm using. Windows is fine. I've tried re-installing Ubuntu and two different ways of installing Linux. I think this is a node issue and not a code issue.<br>
<br>
☐ Set up configurations on nodes. I ran across these and did a cringeworthy hard-coded work-arounds for now:<br>
https://github.com/SeleniumHQ/selenium/issues/5084<br>
https://stackoverflow.com/questions/34093547/selenium-opening-the-wrong-firefox-based-browser<br>
<br>
☐ Finding which the internal IP address of a device with certain capabilities was kind of a pain. I wish there was a way for Selenium can figure this out for itself. Like a web page with all of this on it or something. ( ͡° ͜ʖ ͡°)<br>
<br>
☐ Various minor to dos maked in-code.<br>
<br>
<b>Upcoming features I would like to include someday before I retire and/or die. Take all of this with a grain of salt and change depending on the needs of who might be hiring me:</b><br>
☐ SQL/ETL demos using PostgreSQL. I'm thinking something like a simple data entry screen in the SpringBoot project that saves to a table with ETL functionality to a .csv. Then Selenium to validate back-end data and the .csv.<br>
<br>
☐ Selenium integration with SoapUI projects. Maybe after getting the above done, break the SpringBoot project down into RESTful services so I can fiddle with request/response testing.<br>
<br>
☐ Creating an Appium demo. I am currently in gentlemanly disagreements with npm and NodeJs over getting it installed.<br>
<br>
☐ A recent TCTAG presentation gave me an idea for a testing tool I could take from job to job using what I've already learned.<br>
<br>
☐ Using the Dell DVD Store to mimick an on-line shopping app via the browser and a mobile app, including an API that works with the sample database.<br>
<br>
☐ Selenium/Appium demos in C++, Ruby, and Python.<br>
<br>
☐ Use Jenkins and/or Chef as an CI/CD tool. I change code, check it in, it drops and runs smokes automagically.<br>
<br>
☐ Learning how to performance test. I suck at that.<br>
<br>
☐ IoT fun tiems with NodeRed.
