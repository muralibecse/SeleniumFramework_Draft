package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import wrappers.WrapperMethods;

public class HomePage extends WrapperMethods{
	
	WebDriver driver ;

	public HomePage(WebDriver driver) throws IOException {
		this.driver = driver;
	}
	
	public HomePage VerifyThePageTitle(String title) {
		
		
		return this;
	}


}
