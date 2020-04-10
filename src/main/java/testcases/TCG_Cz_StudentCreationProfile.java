package testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.SkipException;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import ListenersImpl.ImplementListener;
import pages.LoginPage;
//import pages.LoginPage;
import reporters.ExtentTestManager;
import wrappers.TestDataProvider;
import wrappers.WebDriverSetup;

public class TCG_Cz_StudentCreationProfile extends ImplementListener {
	
	
	public void setTestCaseData() {
		
		
		
	}

	@Test(dataProvider = "testdata", dataProviderClass = TestDataProvider.class)
	public void TC_Chopras_CreateStudentProfile1(Map<Object, Object> mapData) throws IOException {
		String url = WebDriverSetup.config_prop.getProperty("app_url");
		
		//***********************************************************************************************************************************
		ExtentTestManager.startTest(mapData.get("TestCaseName").toString(), "Login and verify the page title");
		//***********************************************************************************************************************************
		LoginPage t1 = new LoginPage(wrappers.WebDriverSetup.getWebDriver());
		t1.launchURL(url);
		t1.enterMailID(mapData.get("Email").toString())
		.enterPassword(mapData.get("Password").toString())
		.LogMeIn()
		.verifyTitle("TC Global");;
	
		ExtentTestManager.endTest();
		//***********************************************************************************************************************************
	}
	
	

	@AfterMethod
	public void tearDown() {
		System.out.println("Driver has been quit");
		wrappers.WebDriverSetup.quitDriver();
	}

}
