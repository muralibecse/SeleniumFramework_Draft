package wrappers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import reporters.ExtentTestManager;

public class WrapperMethods extends WebDriverSetup{
		
	public synchronized void launchURL(String url) {
		driver.get(url);
		System.out.println("URL with link '"+url+"' has been opened successfully."+Thread.currentThread().getName());
		Reporter.log("URL with link '"+url+"' has been opened successfully.");
	}
	
	public synchronized void verifyTitle(String title) {
		try {
			Assert.assertEquals(title,driver.getTitle());
			Reporter.log("Page title with name '"+title+"' has been verified successfully.");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Title verified successfully ."+title);
		}catch(AssertionError e) {
			 String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).
			            getScreenshotAs(OutputType.BASE64);
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Title not verified successfully ."+e.getMessage()+"."+ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
			System.out.println(e.getMessage());
		}catch(Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Title not verified successfully ."+e.getMessage()+"-");
			Reporter.log("Page title with name '"+title+"' has not been verified successfully."+e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}

}
