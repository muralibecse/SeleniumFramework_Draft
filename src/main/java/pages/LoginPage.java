package pages;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PageObjects.LoginPageObjects;
import wrappers.WrapperMethods;

public class LoginPage extends WrapperMethods implements LoginPageObjects {
	WebDriver driver ;

	public LoginPage(WebDriver driver) throws IOException{
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


	public HomePage LogMeIn() throws IOException {
     		clickByJScriptExecutor(btnLogMeIn, "LOG ME IN");
			waitAllRequest();
		return new HomePage(driver);
	}

	public LoginPage DontHaveAnAccountLink(String mailID) {

		return this;
	}



}
