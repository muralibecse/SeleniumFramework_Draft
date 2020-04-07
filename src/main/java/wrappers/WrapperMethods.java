package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

	public synchronized HashMap ReadExcelDataForTestCase(String SheetName) {
		HashMap<String, String> hm = new HashMap<String, String>();
		String path = "./data/TestData.xlsx";
		int executeColumnNumber = 0;
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new File(path));	
			XSSFSheet ws = wb.getSheet("Sheet1");

			for(int i = 0;i<ws.getRow(0).getLastCellNum();i++) {
				//Find Execute column Numer
				if(ws.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase("execute")) {
					executeColumnNumber = i;
				}
			}

			for(int i = 0;i<ws.getLastRowNum();i++) {

				for(int j = 0;j<ws.getRow(i).getLastCellNum()-1;j++) {

					if(ws.getRow(i).getCell(executeColumnNumber).getStringCellValue().equalsIgnoreCase("y")) {

						hm.put(ws.getRow(0).getCell(j).getStringCellValue(),ws.getRow(i+1).getCell(j).getStringCellValue());

					}

				}
			}
		}catch(Exception e) {

		}
		return hm;

	}

	public static synchronized void loadConfigurationfiles() throws FileNotFoundException, IOException {
		config_prop = new Properties();
		config_prop.load(new FileInputStream(new File("./configuration.properties")));
	}


}
