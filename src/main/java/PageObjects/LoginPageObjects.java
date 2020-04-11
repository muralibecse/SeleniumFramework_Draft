package PageObjects;

import org.openqa.selenium.By;

public interface LoginPageObjects {
	
	By EmailID = By.xpath("//input[@formcontrolname='email']");
	String txtEmail_XPath = "xpath#//input[@formcontrolname='email']";
	String txtPassword_XPath = "xpath#//input[@formcontrolname='password']";
	String btnLogMeIn = "xpath#.//button[contains(text(),'LOG ME IN')]";

}
