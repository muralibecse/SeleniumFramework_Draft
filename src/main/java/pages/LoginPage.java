package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wrappers.WrapperMethods;

public class LoginPage extends WrapperMethods {
	WebDriver driver ;
	
	String txtEmail_XPath = "//input[@formcontrolname='email']";
	String txtPassword_XPath = "//input[@formcontrolname='password']";
	String btnLogMeIn = ".//button[contains(text(),'LOG')]";
	
	@FindBy(xpath = "//input[@formcontrolname='email']")
	WebElement email;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		//PageFactory.initElements(driver, this);
	}


	public LoginPage teststeURL(String url) {
		launchURL(url);
		return this;
	}

	public LoginPage verifyingPageTitle(String title) {
		verifyTitle(title);
		return this;
	}

	public LoginPage enterMailID(String mailID) {
		enterTextByXpath(txtEmail_XPath, mailID, "email");
	
		//email.sendKeys(mailID);
		return this;
	}


	public LoginPage enterPassword(String password) {
		enterTextByXpath(txtPassword_XPath, password, "password");
		return this;
	}


	public HomePage LogMeIn() {
		clickByXpath(btnLogMeIn, "LOGMEIN");
		return new HomePage(driver);
	}
	
	public LoginPage DontHaveAnAccountLink(String mailID) {

		return this;
	}
	


}
