package testcases;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import reporters.ExtentTestManager;
import wrappers.ImplementListener;

public class MyFirstTestCase extends ImplementListener{

	String url = "https://auspost.com.au/";
	WebDriver driver= wrappers.WebDriverSetup.getWebDriver();

	@Test
	public void myFirstTest(Method method,ITestContext textContext) {
//      HashMap<String, String> hm = new HashMap<String, String>();
//		hm.put("name","1111111");
//		hm.put("name1","2222222");
//		hm.put("name2","3333333");
		
//		if method name and condition match yes will execute
//		
//		textContext.setAttribute("name", hm);
//		
		
		
		
		
		ExtentTestManager.startTest(method.getName(), "Login and verify the page title");
		LoginPage t1 = new LoginPage(driver);
		t1.launchURL(url);
		t1.verifyingPageTitle("Personal, Business, Enterprise & Government solutions - Australia Post")
		.enterMailID("")
		.enterPassword("")
		.LogMeIn()
		.sampleclilck();
		ExtentTestManager.endTest();
		
	}
	
	@Test
	public void mySecondTest(Method method,ITestContext textContext) {
		
		HashMap<?, ?> hm = new HashMap();
		
		hm =(HashMap<?, ?>) textContext.getAttribute("name");
		
		System.out.println("value retrieved from iTextContext:name2:"+hm.get("name2"));
		System.out.println("value retrieved from iTextContext:name1:"+hm.get("name1"));

		
        ExtentTestManager.startTest(method.getName(), "Login and verify the page title- for second test");
        
		System.out.println(method.getName());
		LoginPage t1 = new LoginPage(driver);
		t1.launchURL(url);
		t1.verifyingPageTitle("Google");
		
		ExtentTestManager.endTest();
	}
	
	@Test
	public void myThirdTest(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login and verify the page title- for third test");
        
        System.out.println(method.getName());
		LoginPage t1 = new LoginPage(driver);
		t1.launchURL(url);
		t1.verifyingPageTitle("Personal, Business, Enterprise & Government Solutions - Australia Post");
		
		ExtentTestManager.endTest();
	}
	
	

	
	@AfterClass public void tearDown() {
		System.out.println("Driver has been quit");
		wrappers.WebDriverSetup.quitDriver();
		}


}
