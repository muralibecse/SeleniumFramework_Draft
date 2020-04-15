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

	
	//'***********************        Education & Work - Sai Sankar D      ***********************
	
		public HomePage EducationAndWork() {
			WebElementClick(sectionEducationAndWork, "Educatoin & Work");
			return this;
		}
		
		public HomePage EducationSummary() {
			WebElementClick(subsecEducationSummary, " Education Summary");
			return this;
		}
		
		public HomePage HighestLevelofEducation(String HighestLevelofEducation) {
			WebEditEnterText(drpHighestLevelofEducation,HighestLevelofEducation,"Highest Level of Education");
			return this;
		}
		
		public HomePage CountryofEducation(String CountryofEducation) {
			WebEditEnterText(drpCountryofEducation,CountryofEducation,"CountryofEducation");
			return this;
		}
		
		public HomePage InstituteName(String InstituteName) {
			WebEditEnterText(txtInstituteName,InstituteName,"InstituteName");
			return this;
		}
		
		public HomePage StudyArea(String StudyArea) {
			WebEditEnterText(drpStudyArea,StudyArea,"StudyArea");
			return this;
		}
		
		public HomePage CourseName(String CourseName) {
			WebEditEnterText(drpCourseName,CourseName,"CourseName");
			return this;
		}
		
		public HomePage SelectStatus(String strStartus) {
			strSelectStatus("Status",strStartus);
			return this;
		}
		
		public HomePage selectEASave(String Save) {
			WebElementClick(btnEASave,"Save");
			return this;
		}
		
	//'******************************************************************************************

		

//......................RAJA STUDY PLANS METHODS.....................//
	
	

	public HomePage StdyPlan() {
		WebElementClick(btnStdPlans, "Study Plans");
		//return new HomePage(driver);
		return this;
	}
	
//...............................STUDY ASPIRATIONS.............................//
	
	public HomePage stdyaspiration() {
		WebElementClick(btnStdAspi, " Study Aspirations");
		//return new HomePage(driver);
		return this;
	}

	
	public  HomePage selectStudyLevelSought(String studylevel) {
			WebElementClick("xpath#(//label[text()='Study Level Sought'])[1]//following-sibling::div//input//following-sibling::label[text()='"+studylevel+"']", "studylevel");
			return this;
	}

	
	public HomePage selectYearofAdmission(String yearofadmmis) {
		    WebElementClick("xpath#(//label[text()='Year of Admission'])[1]//following-sibling::div//label[text()='"+yearofadmmis+"']", "yearofadmmis");
		    return this;
     }
	

	public HomePage intake(String intak) {
	    WebElementClick("xpath#(//label[text()='Intake'])[1]//following-sibling::div//label[text()='"+intak+"']", "intak");
	    return this;
     }
	
	
	public HomePage studyareas(String stdarea) {
	    WebElementClick("xpath#(//label[text()='Study Areas'])[1]//following-sibling::div//label[text()='"+stdarea+"']", "stdarea");
	    return this;
     }
	
	
//.................Desired Specialization....................//
	public HomePage desiredspeciselect() {
		WebElementClick(dropdownSelect, "Select...");
		//return new HomePage(driver);
		return this;
	}
	
	public HomePage selectdropdownvalues(String selectfromlist) {
	    WebElementClick("xpath#(//label[text()='Desired Specialization (optional)'])[1]//following::span[@class='dropdown-btn'][1]//following::div[text()='"+selectfromlist+"']", "selectfromlist");
	    return this;
     }
	
//..........................................................//
	
	
	
	public HomePage furtherstudyobjects(String furtherstdobjs) {
	  WebElementClick("xpath#(//label[text()='Further Study Objectives']//following::label[text()='"+furtherstdobjs+"'])[1]", "furtherstdobjs");
	    return this;                                                                           
     }
	
	
	
	public HomePage savebutton() {
		WebElementClick(btnSave, "Save");
		//return new HomePage(driver);
		return this;
	}
	
	
	
	
	
	
//.........................Desired Aspirations..................................//	
	
	
	public HomePage desiredaspiration() {
		WebElementClick(btnDesAspi, " Desired Destinations");
		//return new HomePage(driver);
		return this;
	}
	

	public HomePage desireddestination(String desiredest) {
	    WebElementClick("xpath#(//span[text()=' Desired Destinations'])[1]//following::form//label[text()='"+desiredest+"']", "desiredest");
	    return this;
     }
  

	public HomePage savebuttondesirdes() {
		WebElementClick(btnSaveDesireDes, "Save");
		//return new HomePage(driver);
		return this;
	}
	

//.........................Short listed Universities..................................//	

	
	public HomePage shortlisteduniver() {
		WebElementClick(btnShortListedUniv, " Shortlisted universities");
		//return new HomePage(driver);
		return this;
	}
	
	
	//......short listed potential universities.........//
	
	public HomePage SelectHighPrioroties() {
		DragAndDrop("xpath#.//div[@class='card-header' and text()='Priorities']//following-sibling::div[@class='card-body']//span[text()='Course ranking']","xpath#.//div[@class='card-header' and text()='High Priority']//following-sibling::div[@class='card-body']");
		return this;
	}
	
	public HomePage SelectModeratePrioroties() {
		DragAndDrop("xpath#.//div[@class='card-header' and text()='Priorities']//following-sibling::div[@class='card-body']//span[text()='University location']","xpath#//div[text()='Moderate Priority'][1]");
		return this;
	}
	
	public HomePage SelectLowPrioroties() {
		DragAndDrop("xpath#.//div[@class='card-header' and text()='Priorities']//following-sibling::div[@class='card-body']//span[text()='Faculty/Course content']","xpath#//div[text()='Low Priority']");
		return this;
	}
	
  
	public HomePage savebuttonshortlistuniv() {
		WebElementClick(btnSaveShortListUni, "Save");
		//return new HomePage(driver);
		return this;
	}
	
	
	
//.........................Study Budget..................................//	
	
	public HomePage studybudget() {
		WebElementClick(btnStudyBudget, " Study Budget");
		//return new HomePage(driver);
		return this;
	}
	
    //...................Range currency converter.........//
	
    //...................................................//
	
	
	
	
	
	
	
	
	
	
	
	
}
