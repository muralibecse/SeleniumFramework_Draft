package testcases;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
//import pages.LoginPage;
import reporters.ExtentTestManager;
import wrappers.ImplementListener;
import wrappers.TestDataProvider;
import wrappers.WebDriverSetup;

public class MyFirstTestCase extends ImplementListener{

	String url = WebDriverSetup.config_prop.getProperty("app_url");
	
	@Test(dataProvider = "testdata" ,dataProviderClass = TestDataProvider.class )
	public void myFirstTest(Map<Object, Object> mapData) {

		ExtentTestManager.startTest(mapData.get("TestCaseName").toString(),"Login and verify the page title");

		System.out.println("Firsttestcase:"+mapData.get("TestCaseName"));
		
		System.out.println("Firsttestcase:"+mapData.get("TestCaseDescription"));

		LoginPage t1 = new LoginPage(wrappers.WebDriverSetup.getWebDriver());
		 t1.launchURL(url);
		/*
		 * t1.
		 * verifyingPageTitle("Personal, Business, Enterprise & Government solutions - Australia Post"
		 * ) .enterMailID("") .enterPassword("") .LogMeIn() .sampleclilck();
		 */
		ExtentTestManager.endTest();
		
	}
	
	//@Test
	/*
	 * public void mySecondTest(Method method,ITestContext textContext) {
	 * 
	 * HashMap<?, ?> hm = new HashMap();
	 * 
	 * hm =(HashMap<?, ?>) textContext.getAttribute("name");
	 * 
	 * System.out.println("value retrieved from iTextContext:name2:"+hm.get("name2")
	 * );
	 * System.out.println("value retrieved from iTextContext:name1:"+hm.get("name1")
	 * );
	 * 
	 * 
	 * ExtentTestManager.startTest(method.getName(),
	 * "Login and verify the page title- for second test");
	 * 
	 * System.out.println(method.getName()); LoginPage t1 = new LoginPage(driver);
	 * t1.launchURL(url); t1.verifyingPageTitle("Google");
	 * 
	 * ExtentTestManager.endTest(); }
	 * 
	 * //@Test public void myThirdTest(Method method) {
	 * ExtentTestManager.startTest(method.getName(),
	 * "Login and verify the page title- for third test");
	 * 
	 * System.out.println(method.getName()); LoginPage t1 = new LoginPage(driver);
	 * t1.launchURL(url); t1.
	 * verifyingPageTitle("Personal, Business, Enterprise & Government Solutions - Australia Post"
	 * );
	 * 
	 * ExtentTestManager.endTest(); }
	 * 
	 * 
	 */
	
	@AfterMethod
	public void tearDown() {
		System.out.println("Driver has been quit");
		wrappers.WebDriverSetup.quitDriver();
		}


}
