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

	//....................STUDY PLANS MODULE...................................//
	//objects under  Study Aspirations
	String btnStdPlans = "xpath#//span[text()='Study Plans']";
	String btnStdAspi  = "xpath#//span[text()=' Study Aspirations'][1]";
	String btnSavestdPlan = "xpath#//button[text()='Save']";
	String dropdownSelect = "xpath#(//label[text()='Desired Specialization (optional)'])[1]//following::span[@class='dropdown-btn'][1]";

	//Objects under Desired Destinations
	String btnDesAspi  = "xpath#//span[text()=' Desired Destinations'][1]";
	String btnSaveDesireDes = "xpath#//button[text()='Save'][1]";

	//Objects under short listed universities
	String btnShortListedUniv  = "xpath#//span[text()=' Shortlisted universities'][1]";
	String btnSaveShortListUni = "xpath#//span[text()='Save'][1]";


	//Objects under Study Budget
	String btnStudyBudget  = "xpath#//span[text()=' Study Budget'][1]";






}




