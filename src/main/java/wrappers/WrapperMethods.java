package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	
	public WebElement waitForElement(WebDriver driver,long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public WebElement waitForElementWithPollingInterval(WebDriver driver,long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
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

	public synchronized String getScreenShot(String imageName) throws IOException{

		if(imageName.equals("")){
			imageName = "blank";
		}
		File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imagelocation = System.getProperty("user.dir")+"/src/main/java/com/hybridFramework/screenshot/";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actualImageName = imagelocation+imageName+"_"+formater.format(calendar.getTime())+".png";
		File destFile = new File(actualImageName);
		FileUtils.copyFile(image, destFile);
		return actualImageName;
	}
	
	
	
	public synchronized void enterTextByXpath(String xpath,String data,String objectname) {
		
		getWebDriver().findElement(By.xpath(xpath)).sendKeys(data);
		
		
	}


}
