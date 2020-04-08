package pages;

import org.openqa.selenium.WebDriver;

import wrappers.WrapperMethods;

public class HomePage extends WrapperMethods{
	
	WebDriver driver ;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePage sampleclilck() {
		
		return this;
	}

}
