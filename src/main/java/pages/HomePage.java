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
		//waitForJQueryLoad();
		return this;
	}
	
	public HomePage selectPersonaltab(String personal) {
		WebElementClick(personalTab,"PersonalTab");
		return this;
	}
	
	public HomePage enterAddress(String address) {
		WebEditEnterText(txtAddress_Personal,address,"Address");
		return this;
	}
	
	public HomePage enterCountry(String country) {
		WebEditEnterText(txtCountry_Personal,country,"Country");
		return this;
	}
	
	public HomePage enterCity(String city) {
		WebEditEnterText(txtCity_Personal,city,"City");
		return this;
	}
	
	public HomePage enterState(String state) {
		WebEditEnterText(txtState_Personal,state,"State");
		return this;
	}
	
	public HomePage enterZIPCode(String zipcode) {
		WebEditEnterText(txtZIPCode_Personal,zipcode,"ZIPCode");
		return this;
	}
	
	public HomePage selectGender(String gender) {
		strselectGender(gender,"Gender");
		return this;
	}
	
	public HomePage selectMaritalstatus(String maritalstatus) {
		strselectMaritalstatus(maritalstatus,"Maritalstatus");
		return this;
	}
	
	
	public HomePage selectSave(String Save) {
		WebElementClick(btnSave,"Save");
		return this;
	}
	
	public HomePage SelectDate(String day,String month,String year) {
		tcgSelectDropdown("Date of Birth", "date", day, "date");
		tcgSelectDropdown("Date of Birth", "month", month, "month");
		tcgSelectDropdown("Date of Birth", "year", year, "year");
		return this;
	}
	
	public HomePage SelectCountry(String country) {
		tcgSelectDropdown("Date of Birth", "country_citi", country, "Country");
		return this;
	}
	
	public HomePage SelectEthnicity(String ethnicity) {
		tcgSelectDropdown("Date of Birth", "nationality", ethnicity, "Nationality");
		return this;
	}
	
	public HomePage SelectFamilyBackground(String family) {
		tcgSelectDropdown("Date of Birth", "family_background", family, "Family Background");
		return this;
	}
	
	
	
	public HomePage SelectPTC(String timetocontact) {
		strSelectLabel("- Preffered time of contact", timetocontact, "Time to contact");
		return this;
	}
	
	public HomePage SelectPMC(String modetocontact) {
		strSelectLabel("- Preffered mode of contact", modetocontact,"Mode to contact");
		return this;
	}

}
