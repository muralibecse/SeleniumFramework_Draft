package PageObjects;

import org.openqa.selenium.By;

public interface HomePageObjects {
	
	String btnAddProfileDetails = "xpath#//button[normalize-space(text())='Add profile details']";
	String txtCountry_Personal = "xpath#(//span[contains(text(),'- Address')]//following::input[@formcontrolname='country'])[1]";
	String txtAddress_Personal = "xpath#(//span[contains(text(),'- Address')]//following::input[@formcontrolname='address'])[1]";
	String txtCity_Personal = "xpath#(//span[contains(text(),'- Address')]//following::input[@formcontrolname='city'])[1]";
	String txtState_Personal = "xpath#(//span[contains(text(),'- Address')]//following::input[@formcontrolname='state'])[1]";
	String txtZIPCode_Personal = "xpath#(//span[contains(text(),'- Address')]//following::input[@formcontrolname='zipcode'])[1]";
	String btnSave = "xpath#((//span[contains(text(),'Preffered mode of contact')]//following::label[@for='Chat'])[1]//following::button//span[text()='Save'])[1]]";
	String personalTab = "xpath#(//span[contains(text(),'Personal')])[1]";
}
