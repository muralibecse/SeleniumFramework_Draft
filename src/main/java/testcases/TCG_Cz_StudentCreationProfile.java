package testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.LoginPage;
import reporters.ExtentTestManager;
import wrappers.TestDataProvider;
import wrappers.WebDriverSetup;

public class TCG_Cz_StudentCreationProfile extends WebDriverSetup {

	@Test(dataProvider = "testdata", dataProviderClass = TestDataProvider.class)
	public  void TC_Chopras_CreateStudentProfile1(Map<Object, Object> mapData) throws IOException {
		if(mapData.get("Execute").toString().equalsIgnoreCase("N")||mapData.get("Execute")==null ) {
			throw new SkipException("Test Case Skipped");
			
		}
		String url = WebDriverSetup.config_prop.getProperty("app_url");
		
		//***********************************************************************************************************************************
		ExtentTestManager.startTest(mapData.get("TestCaseName").toString(),"Login and verify the page title");
		//***********************************************************************************************************************************
		LoginPage t1 = new LoginPage(getDriver());
			t1.launchURL(url);
			t1.enterMailID(mapData.get("Email").toString())
			.enterPassword(mapData.get("Password").toString())
			.LogMeIn()
			.AddProfileDetails()
			.SelectDate("22","October","1995");
		
		ExtentTestManager.endTest();
		//***********************************************************************************************************************************
	}
	/*
	 * @Test(dataProvider = "testdata", dataProviderClass = TestDataProvider.class)
	 * public void TC_Chopras_CreateStudentProfile2(Map<Object, Object> mapData)
	 * throws IOException { System.out.println("test 2 started");
	 * 
	 * String url = WebDriverSetup.config_prop.getProperty("app_url");
	 * 
	 * //***************************************************************************
	 * ********************************************************
	 * ExtentTestManager.startTest("Login and verify the page title");
	 * //***************************************************************************
	 * ******************************************************** LoginPage t2 = new
	 * LoginPage(getDriver()); t2.launchURL(url);
	 * t2.enterMailID(mapData.get("Email").toString())
	 * .enterPassword(mapData.get("Password").toString()) .LogMeIn()
	 * .AddProfileDetails() .SelectDate("22","October","1995");
	 * 
	 * ExtentTestManager.endTest();
	 * //***************************************************************************
	 * ******************************************************** }
	 */


}
