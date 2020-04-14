package reporters;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
    private static ExtentReports extent;
    public synchronized static ExtentReports getReporter() {
    	String projectDirectory=null;
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	if (extent == null) {
    		 projectDirectory = System.getProperty("user.dir");
    		 if (System.getProperty("os.name").toLowerCase().contains("win")) {
                 extent = new ExtentReports(projectDirectory + "\\Reports\\ExtentReportResults"+timeStamp+".html", true);    		 }
    	} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            extent = new ExtentReports(projectDirectory + "/Reports/ExtentReportResults"+timeStamp+".html", true);
        }
    	return extent;
    }
    
    
    

}
