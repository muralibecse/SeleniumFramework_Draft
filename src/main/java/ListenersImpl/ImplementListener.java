package ListenersImpl;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;		
import org.testng.ITestListener;		
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import reporters.ExtentManager;
import reporters.ExtentTestManager;
import wrappers.WebDriverSetup;
import wrappers.WrapperMethods;


public class ImplementListener implements ITestListener	{
	WebDriverSetup driver = null;
	
	public ImplementListener() {
		System.out.println("ImplementListener loaded::::"+Thread.currentThread().getStackTrace()[3].getMethodName());
	
		try {
			WrapperMethods.loadConfigurationfiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


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
	
	public void onTestSkipped(ITestResult result) {
		
		
		System.out.println("Test Skipped");
	}

	private static String getTestMethodName(ITestResult iTestResult) {
		System.out.println("getTestMethodName loaded:"+iTestResult.getMethod().getConstructorOrMethod().getName());
		
		return iTestResult.getMethod().getConstructorOrMethod().getName();
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
