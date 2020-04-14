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
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import reporters.ExtentManager;
import reporters.ExtentTestManager;

public class WrapperMethods extends WebDriverSetup{
	
	
	static WebDriverWait wait;
	static WebDriver jsWaitDriver;
	static WebDriver driver;
	public synchronized void launchURL(String url) {
		try {
			driver = getDriver() ;
		//	driver = WebDriverSetup.getWebDriver();
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
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
	
	public synchronized WebElement waitForElement(WebDriver driver,long time,WebElement element){
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
		getDriver().findElement(By.xpath(xpath)).sendKeys(data);
	}
	
	public synchronized void clickByXpath(String xpath,String objectname) {
		getDriver().findElement(By.xpath(xpath)).click();
	}
	
	
	public synchronized void WebElementClick(String locator,String objectName) {
		try {
			String []loc = locator.split("#",2);
			
			switch(loc[0].toLowerCase()) {
			
			case "id":
				getDriver().findElement(By.id(loc[1])).click();
				break;
			case "xpath":
				getDriver().findElement(By.xpath(loc[1])).click();
				break;
			case "css":
				getDriver().findElement(By.cssSelector(loc[1])).click();
				break;
			case  "name":
				getDriver().findElement(By.name(loc[1])).click();
				break;
			default:
				throw new RuntimeException("Invalid Locator Type."+locator);
			}
			
			testReport("Element with name '"+objectName+"' has been clicked successfully.", "PASS");
		}catch(StaleElementReferenceException stale) {
			System.out.println("StaleL:Webelement click:"+stale.getMessage());
			WebElementClick(locator,objectName);
			
		}catch (Exception e) {
			testReport("Element with name '"+objectName+"' not able to click successfully.", "FAIL");

		// TODO Auto-generated catch block
			
			if(e.getMessage().contains("Other element would receive the click")) {
				WebElementClick(locator,objectName);

			}else {
			System.out.println("Exception:"+e.getMessage());
			}
		}
		
	}
	
	
	public synchronized void WebEditEnterText(String locator,String datatoEnter,String objectName) {
		try {
			String []loc = locator.split("#",2);
			switch(loc[0].toLowerCase()) {
			
			case "id":
				getDriver().findElement(By.id(loc[1])).sendKeys(datatoEnter);
				break;
			case "xpath":
				getDriver().findElement(By.xpath(loc[1])).sendKeys(datatoEnter);
				break;
			case "css":
				getDriver().findElement(By.cssSelector(loc[1])).sendKeys(datatoEnter);
				break;
			case  "name":
				getDriver().findElement(By.name(loc[1])).sendKeys(datatoEnter);
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
	
	public synchronized void clickBy(String xpath,String objectname) {
		getDriver().findElement(By.xpath(xpath)).click();
	}
	
	
	public synchronized void testReport(String log,String status) {
		if(status.equalsIgnoreCase("pass")) {
		ExtentTestManager.getTest().log(LogStatus.PASS, log+attachScreenshot());
		}else if(status.equalsIgnoreCase("fail")){
			ExtentTestManager.getTest().log(LogStatus.FAIL, log+attachScreenshot());
		}else {
			ExtentTestManager.getTest().log(LogStatus.INFO, log);
		}
	}
	
	public synchronized void WebElementClickByAction(String locator,String objectName) {
		try {
			
			Actions action  = new Actions(getDriver());
			String []loc = locator.split("#",2);
			switch(loc[0].toLowerCase()) {
			case "id":
				action.click(getDriver().findElement(By.id(loc[1]))).build().perform();;
				break;
			case "xpath":
				action.click(getDriver().findElement(By.xpath(loc[1]))).build().perform();
				break;
			case "css":
				action.click(getDriver().findElement(By.cssSelector(loc[1])));
				break;
			case  "name":
				action.click(getDriver().findElement(By.name(loc[1]))).build().perform();
				break;
			default:
				throw new RuntimeException("Invalid Locator Type."+locator);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getStackTrace();
			System.out.println("Exception:"+e.getMessage());
		}
		
	}
	
	public synchronized void clickByJScriptExecutor(String locator,String objectName) {
		WebElement element = null;
		
		try {
			String []loc = locator.split("#",2);
			
			switch(loc[0].toLowerCase()) {
			case "id":
				element = getDriver().findElement(By.id(loc[1]));
				break;
			case "xpath":
				element = getDriver().findElement(By.xpath(loc[1]));
				break;
			case "css":
				element = getDriver().findElement(By.cssSelector(loc[1]));
				break;
			case  "name":
				element = getDriver().findElement(By.name(loc[1]));
				break;
			default:
				throw new RuntimeException("Invalid Locator Type."+locator);
			}
			JavascriptExecutor js = (JavascriptExecutor) getDriver();  
			js.executeScript("arguments[0].click();",element);
			testReport("Element with name '"+objectName+"' has been clicked successfully.", "PASS");
		}catch(StaleElementReferenceException e) {
			clickByJScriptExecutor( locator, objectName) ;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			testReport("Element with name '"+objectName+"' not able click successfully."+e.getMessage(), "FAIL");

		}
		
	}
	
	
	public synchronized void tcgSelectDropdown(String strLabelReference,String strFormControlName,String strValuetoselect,String objectName) {
		
		WebElementClick("xpath#(.//label[text()='"+strLabelReference+"']//following::mat-select[@formcontrolname='"+strFormControlName+"'])[1]", strFormControlName);
		ScrollIntoElement("xpath#//mat-option//span[normalize-space(text())='"+strValuetoselect+"']",strValuetoselect);
		poll(2000);
		WebElementClick("xpath#//mat-option//span[normalize-space(text())='"+strValuetoselect+"']","Value Selected - "+strValuetoselect);
	}
	
	
	public void ScrollIntoElement(String locator,String objectName) {
		WebElement element = null;
		try {
			String []loc = locator.split("#",2);
			switch(loc[0].toLowerCase()) {
			case "id":
				element = getDriver().findElement(By.id(loc[1]));
				break;
			case "xpath":
				element = getDriver().findElement(By.xpath(loc[1]));
				break;
			case "css":
				element = getDriver().findElement(By.cssSelector(loc[1]));
				break;
			case  "name":
				element =getDriver().findElement(By.name(loc[1]));
				break;
			default:
				throw new RuntimeException("Invalid Locator Type."+locator);
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
	}
	

	//***************************ANGULAR JS WRAPPER METHODS***************************************************//
	
	/*
	 * public synchronized void waitForJQueryLoad() { JavascriptExecutor jsExec =
	 * (JavascriptExecutor) getDriver(); WebDriverWait jsWait=new
	 * WebDriverWait(getDriver(), 10);;
	 * 
	 * try { jsWait = new WebDriverWait(getDriver(),30); ExpectedCondition<Boolean>
	 * jQueryLoad = driver -> ((Long) jsExec .executeScript("return jQuery.active")
	 * == 0);
	 * 
	 * boolean jqueryReady = (Boolean)
	 * jsExec.executeScript("return jQuery.active==0");
	 * 
	 * if (!jqueryReady) { jsWait.until(jQueryLoad); } } catch (WebDriverException
	 * ignored) {
	 * System.out.println("Exception in waitForJQueryLoad:"+ignored.getMessage());
	 * }catch(Exception e) {
	 * System.out.println("Exception in waitForJQueryLoad:"+e.getMessage());
	 * 
	 * } }
	 */
   
   public void waitUntilAngular5Ready() {
	   JavascriptExecutor jsExec = (JavascriptExecutor) getDriver();  

       try {
           Object angular5Check = jsExec.executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
           if (angular5Check != null) {
               Boolean angularPageLoaded = (Boolean) jsExec.executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
               if (!angularPageLoaded) {
                   poll(20);

                   waitForAngular5Load();

                   poll(20);
               }
           }
       } catch (WebDriverException ignored) {
       }
   }

   private void poll(long milis) {
       try {
           Thread.sleep(milis);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }
   
   private void waitForAngular5Load() {
       String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
       angularLoads(angularReadyScript);
   }
   
   private void angularLoads(String angularReadyScript) {
	   WebDriverWait jsWait=new WebDriverWait(getDriver(), 10);;
	   JavascriptExecutor jsExec = (JavascriptExecutor) getDriver();  

       try {
           ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) getDriver())
               .executeScript(angularReadyScript).toString());

           boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

           if (!angularReady) {
               jsWait.until(angularLoad);
           }
       } catch (WebDriverException ignored) {
       }
   }
   
   private void waitUntilJSReady() {
 	   WebDriverWait jsWait=new WebDriverWait(getDriver() , 10);

	   JavascriptExecutor jsExec = (JavascriptExecutor) getDriver() ;  
       try {
   

           ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) jsWait)
               .executeScript("return document.readyState").toString().equals("complete");

           boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");

           if (!jsReady) {
               jsWait.until(jsLoad);
           }
       } catch (WebDriverException ignored) {
       }
   }
   
   private void ajaxComplete() {
	   JavascriptExecutor jsExec = (JavascriptExecutor) getDriver() ;  

       jsExec.executeScript("var callback = arguments[arguments.length - 1];"
           + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
           + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
           + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
   }
   
	/*
	 * private void waitUntilJQueryReady() { JavascriptExecutor jsExec =
	 * (JavascriptExecutor) getDriver() ;
	 * 
	 * Boolean jQueryDefined = (Boolean)
	 * jsExec.executeScript("return typeof jQuery != 'undefined'"); if
	 * (jQueryDefined) { poll(20);
	 * 
	 * waitForJQueryLoad();
	 * 
	 * poll(20); } }
	 */
   
   public void waitUntilAngularReady() {
	   JavascriptExecutor jsExec = (JavascriptExecutor) getDriver() ;  

       try {
           Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
           if (!angularUnDefined) {
               Boolean angularInjectorUnDefined = (Boolean) jsExec.executeScript("return angular.element(document).injector() === undefined");
               if (!angularInjectorUnDefined) {
                   poll(20);

                   waitForAngularLoad();

                   poll(20);
               }
           }
       } catch (WebDriverException ignored) {
       }
   }
   
   private void waitForAngularLoad() {
       String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
       angularLoads(angularReadyScript);
   }


   public void waitAllRequest() {
       waitUntilJSReady();
       ajaxComplete();
      // waitUntilJQueryReady();
       waitUntilAngularReady();
       waitUntilAngular5Ready();
   }
	//***************************ANGULAR JS WRAPPER METHODS***************************************************//


}
