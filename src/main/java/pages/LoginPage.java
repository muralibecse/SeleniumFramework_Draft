package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wrappers.WrapperMethods;

public class LoginPage extends WrapperMethods {
	WebDriver driver ;
	//Format of Location type =>  locatortype:locatorvalue
	String txtEmail_XPath = "xpath#//input[@formcontrolname='email']";
	String txtPassword_XPath = "xpath#//input[@formcontrolname='password']";
	String btnLogMeIn = "xpath#.//button[contains(text(),'LOG')]";
	
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
		WebEditEnterText(txtEmail_XPath, mailID, "Email");
		return this;
	}


	public LoginPage enterPassword(String password) {
		WebEditEnterText(txtPassword_XPath, password, "password");
		return this;
	}


	public HomePage LogMeIn() {
		WebElementClick(btnLogMeIn, "LOGMEIN");
		return new HomePage(driver);
	}
	
	public LoginPage DontHaveAnAccountLink(String mailID) {

		return this;
	}
	


}
