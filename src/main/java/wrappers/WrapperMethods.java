package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import reporters.ExtentManager;
import reporters.ExtentTestManager;

public class WrapperMethods extends WebDriverSetup{

	public synchronized void launchURL(String url) {
		try {
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("URL with link '"+url+"' has been opened successfully."+Thread.currentThread().getName());
			Reporter.log("URL with link '"+url+"' has been opened successfully.");
			testReport("URL with link '"+url+"' has been opened successfully.", "PASS");
		}catch(WebDriverException we) {
			testReport("URL with link '"+url+"' has been opened successfully."+we.getMessage(), "FAIL");
		}
		catch (Exception e) {
			Reporter.log("Exception occured during URL launch."+e.getMessage());

		}
	}

	/**
	 * @param window_title
	 * @author Muralimohan Murugesan
	 * @category Selenium Reusable
	 * @description This method takes the input and compares it against the window Title
	 */
	public synchronized void verifyTitle(String title) {
		try {
			Assert.assertEquals(title,driver.getTitle());
			Reporter.log("Page title with name '"+title+"' has been verified successfully.");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Title verified successfully ."+title+attachScreenshot());
		}catch(AssertionError e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Title not verified successfully ."+e.getMessage()+"."+attachScreenshot());
			System.out.println(e.getMessage());
		}catch(Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Title not verified successfully ."+e.getMessage()+"-");
			Reporter.log("Page title with name '"+title+"' has not been verified successfully."+e.getMessage());

		}finally {
			System.out.println("Finalalyyyyyyyy");
		}
	}

	public String attachScreenshot() {
		String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).
				getScreenshotAs(OutputType.BASE64);
		return ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot);

	}
	
	public WebElement waitForElement(WebDriver driver,long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/*
	 * public WebElement waitForElementWithPollingInterval(WebDriver driver,long
	 * time,WebElement element){ WebDriverWait wait = new WebDriverWait(driver,
	 * time); wait.pollingEvery(5, TimeUnit.SECONDS);
	 * wait.ignoring(NoSuchElementException.class); return
	 * wait.until(ExpectedConditions.elementToBeClickable(element)); }
	 */
	public synchronized HashMap<String, String> ReadExcelDataForTestCase(String SheetName) {
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
			wb.close();
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
	
	public synchronized void clickByXpath(String xpath,String objectname) {
		getWebDriver().findElement(By.xpath(xpath)).click();
	}
	
	
	public synchronized void WebElementClick(String locator,String objectName) {
		try {
			String []loc = locator.split("#",2);
			
			switch(loc[0].toLowerCase()) {
			
			case "id":
				getWebDriver().findElement(By.id(loc[1])).click();
				break;
			case "xpath":
				getWebDriver().findElement(By.xpath(loc[1])).click();
				break;
			case "css":
				getWebDriver().findElement(By.cssSelector(loc[1])).click();
				break;
			case  "name":
				getWebDriver().findElement(By.name(loc[1])).click();
				break;
			default:
				throw new RuntimeException("Invalid Locator Type."+locator);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception:"+e.getMessage());
		}
		
	}
	
	
	public synchronized void WebEditEnterText(String locator,String datatoEnter,String objectName) {
		try {
			String []loc = locator.split("#",2);
			switch(loc[0].toLowerCase()) {
			
			case "id":
				getWebDriver().findElement(By.id(loc[1])).sendKeys(datatoEnter);
				break;
			case "xpath":
				getWebDriver().findElement(By.xpath(loc[1])).sendKeys(datatoEnter);
				break;
			case "css":
				getWebDriver().findElement(By.cssSelector(loc[1])).sendKeys(datatoEnter);
				break;
			case  "name":
				getWebDriver().findElement(By.name(loc[1])).sendKeys(datatoEnter);
				break;
			default:
				throw new RuntimeException("Invalid Locator Type."+locator);
			}
			testReport("Field '"+objectName+"' with value '"+datatoEnter+"' has been entered successfully","PASS");
		} catch (Exception e) {
			testReport("Field '"+objectName+"' with value '"+datatoEnter+"' is not able to enter","FAIL");
			// TODO Auto-generated catch block
			System.out.println("Exception:"+e.getMessage());
		}
		
	}
	
	
	public synchronized void testReport(String log,String status) {
		if(status.equalsIgnoreCase("pass")) {
		ExtentTestManager.getTest().log(LogStatus.PASS, log);
		}else if(status.equalsIgnoreCase("fail")){
			ExtentTestManager.getTest().log(LogStatus.FAIL, log);
		}else {
			ExtentTestManager.getTest().log(LogStatus.INFO, log);
		}
	}


}
