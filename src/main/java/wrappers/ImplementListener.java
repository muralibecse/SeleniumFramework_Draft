package wrappers;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;		
import org.testng.ITestListener;		
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import reporters.ExtentManager;
import reporters.ExtentTestManager;


public class ImplementListener implements ITestListener	{

	public void onFinish(ITestContext iTestContext) {					
		System.out.println("I am in onFinish method " + iTestContext.getName());
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}		

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public void onStart(ITestContext iTestContext) {	
		System.out.println("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", wrappers.WebDriverSetup.getWebDriver());
	}		

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {					
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}		

	public void onTestFailure(ITestResult iTestResult) {					
	        WebDriver webDriver = WebDriverSetup.getWebDriver();
	        //Take base64Screenshot screenshot.
	        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).
	            getScreenshotAs(OutputType.BASE64);
	 
	        //ExtentReports log and screenshot operations for failed tests.
	        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
	        ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
		 
	}		

	public void onTestSkipped(ITestResult iTestResult) {					
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	}		

	public void onTestStart(ITestResult iTestResult) {					
		System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
	}		

	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
	}

}
