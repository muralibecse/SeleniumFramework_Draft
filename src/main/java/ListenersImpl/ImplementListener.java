package ListenersImpl;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;		
import org.testng.ITestListener;		
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import reporters.ExtentManager;
import reporters.ExtentTestManager;
import wrappers.WebDriverSetup;
import wrappers.WrapperMethods;


public class ImplementListener implements ITestListener	{

	/*
	 * public ImplementListener() {
	 * System.out.println("ImplementListener loaded::::"+Thread.currentThread().
	 * getStackTrace()[3].getMethodName());
	 * 
	 * try { WrapperMethods.loadConfigurationfiles(); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } }
	 */

	public void onFinish(ITestContext iTestContext) {					
		try {
			System.out.println("I am in onFinish method " + iTestContext.getName());
			ExtentTestManager.endTest();
			ExtentManager.getReporter().flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		

	private static String getTestMethodName(ITestResult iTestResult) {
		System.out.println("getTestMethodName loaded");
		
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public void onStart(ITestContext iTestContext) {	
		System.out.println("I am in onStart method ");
		iTestContext.setAttribute("WebDriver", wrappers.WebDriverSetup.getWebDriver());
	}		

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {	
		System.out.println("onTestFailedButWithinSuccessPercentage loaded");

		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}		

	public void onTestFailure(ITestResult iTestResult) {					
		try {
			WebDriver webDriver = WebDriverSetup.getWebDriver();
			//Take base64Screenshot screenshot.
			String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).
					getScreenshotAs(OutputType.BASE64);

			//ExtentReports log and screenshot operations for failed tests.
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
					ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}		

	public void onTestSkipped(ITestResult iTestResult) {					
		try {
			System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
			ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		

	public void onTestStart(ITestResult iTestResult) {					
		try {
			System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		

	public void onTestSuccess(ITestResult iTestResult) {
		try {
			System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
