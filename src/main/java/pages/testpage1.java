package pages;
import org.openqa.selenium.WebDriver;
import wrappers.WrapperMethods;

public class testpage1 extends WrapperMethods {
	WebDriver driver ;
	
	public testpage1(WebDriver driver) {
		this.driver = driver;
	}
	

	public testpage1 teststeURL(String url) {
		launchURL(url);
		return this;
	}
	
	public testpage1 verifyingPageTitle(String title) {
		verifyTitle(title);
		return this;
	}

}
