package wrappers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSetup {
	protected static WebDriver driver = null;
	public static String browserName = "chrome";
	public static Properties config_prop ;
	
	
	public WebDriverSetup() {
		try {
			loadConfigurationfiles();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static synchronized WebDriver getWebDriver() {
		
		if(driver == null) {
			if(browserName.equalsIgnoreCase("chrome")) {	
				System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriverv80.exe");			
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			}
		}
		return driver;
	}

	public static synchronized void quitDriver() {
		try {
			driver.quit();
			driver= null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized void closeBrowser() {
		try { 
			driver.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	
	public static synchronized void loadConfigurationfiles() throws FileNotFoundException, IOException {
		
		config_prop = new Properties();
		config_prop.load(new FileInputStream(new File("./configuration.properties")));
		
	}

}


