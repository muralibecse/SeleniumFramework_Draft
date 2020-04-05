package reporters;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
    private static ExtentReports extent;
    public synchronized static ExtentReports getReporter() {
    	String projectDirectory=null;
    	if (extent == null) {
    		 projectDirectory = System.getProperty("user.dir");
    		 if (System.getProperty("os.name").toLowerCase().contains("win")) {
                 extent = new ExtentReports(projectDirectory + "\\Reports\\ExtentReportResults.html", true);    		 }
    	} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            extent = new ExtentReports(projectDirectory + "/Reports/ExtentReportResults.html", true);
        }
    	return extent;
    }
    
    
    

}
