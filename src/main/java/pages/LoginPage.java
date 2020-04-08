package pages;
import org.openqa.selenium.WebDriver;
import wrappers.WrapperMethods;

public class LoginPage extends WrapperMethods {
	WebDriver driver ;
	
	String txtEmail_XPath = "//input[@formcontrolname='email']";
	String txtPassword_XPath = "//input[@formcontrolname='password']";
	String btnLogMeIn_XPath= "//button[contains(text(),'LOG')]";

	public LoginPage(WebDriver driver) {
		this.driver = driver;
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
		return this;
	}


	public LoginPage enterPassword(String mailID) {

		return this;
	}


	public HomePage LogMeIn() {

		return new HomePage(driver);
	}
	
	public LoginPage DontHaveAnAccountLink(String mailID) {

		return this;
	}
	


}
