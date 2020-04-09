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

public class MyFirstTestCase_Murali extends ImplementListener {

	String url = WebDriverSetup.config_prop.getProperty("app_url");

	@Test(dataProvider = "testdata", dataProviderClass = TestDataProvider.class)
	public void TC_Chopras_CreateStudentProfile(Map<Object, Object> mapData) {

		ExtentTestManager.startTest(mapData.get("TestCaseName").toString(), "Login and verify the page title");

		LoginPage t1 = new LoginPage(wrappers.WebDriverSetup.getWebDriver());
		t1.launchURL(url);
		t1.enterMailID(mapData.get("Email").toString())
		.enterPassword(mapData.get("Password").toString())
		.LogMeIn();
	
		ExtentTestManager.endTest();

	}

	@AfterMethod
	public void tearDown() {
		System.out.println("Driver has been quit");
		wrappers.WebDriverSetup.quitDriver();
	}

}
