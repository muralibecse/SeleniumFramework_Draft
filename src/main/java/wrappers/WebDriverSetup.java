package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ListenersImpl.ImplementListener;

public class WebDriverSetup extends ImplementListener {
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public String browserName = config_prop.getProperty("browser");
	public static Properties config_prop;
	public CapabilityFactory capabilityFactory = new CapabilityFactory();

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

	@BeforeMethod
	public void setup() throws MalformedURLException {
		// Set Browser to ThreadLocalMap
		if(browserName.equalsIgnoreCase("chrome")) {
			if(config_prop.getProperty("grid_enabled").equalsIgnoreCase("no")) {
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver.set(new ChromeDriver());
			
			}else {
				driver.set(new RemoteWebDriver(new URL(config_prop.getProperty("grid_url")),
						  capabilityFactory.getCapabilities(browserName)));
			}
			System.out.println("Browser setup has been initialized....!");
		}

	}

	public WebDriver getDriver() {

		return driver.get();
	}

	@AfterMethod
	public void tearDown() {
		getDriver().close();
		getDriver().quit();
	}

	@AfterClass
	void terminate() {
		// Remove the ThreadLocalMap element
		driver.remove();
	}

	/*
	 * public static synchronized WebDriver getWebDriver() { // if(driver == null) {
	 * if(browserName.equalsIgnoreCase("chrome")) {
	 * System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
	 * driver = new ThreadLocal<>(); driver.set(new ChromeDriver());
	 * 
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * driver.manage().window().maximize();
	 * 
	 * } // } return driver; }
	 */
	/*
	 * public static synchronized void quitDriver() { try { if(driver!=null) {
	 * driver.quit(); driver= null; } } catch (Exception e) { e.printStackTrace(); }
	 * }
	 * 
	 * public static synchronized void closeBrowser() { try { driver.close(); }
	 * catch (Exception e) { e.getMessage(); } }
	 */

	public static synchronized void loadConfigurationfiles() throws FileNotFoundException, IOException {

		config_prop = new Properties();
		config_prop.load(new FileInputStream(new File("./configuration.properties")));

	}

}
