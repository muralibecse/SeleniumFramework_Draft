package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import PageObjects.HomePageObjects;
import wrappers.WrapperMethods;

public class HomePage extends WrapperMethods implements HomePageObjects{
	
	WebDriver driver ;

	public HomePage(WebDriver driver) throws IOException {
		this.driver = driver;
	}
	
	public HomePage VerifyThePageTitle(String title) {
		
		
		return this;
	}

	
	public HomePage AddProfileDetails() {
		WebElementClick(btnAddProfileDetails,"Add Profile details");
		waitForJQueryLoad();
		return this;
	}
	
	public HomePage SelectDate(String day,String month,String year) {
		tcgSelectDropdown("Date of Birth", "date", day, "date");
		tcgSelectDropdown("Date of Birth", "month", month, "month");
		tcgSelectDropdown("Date of Birth", "year", year, "year");
		return this;
	}

}
