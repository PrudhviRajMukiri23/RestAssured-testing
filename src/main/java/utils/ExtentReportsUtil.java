package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsUtil {
	
	ExtentSparkReporter sparkReporter;
	
	ExtentReports extentReport;
	
	ExtentTest extentTest;
	
	public void extentreportCreator(String filename) {
		
		sparkReporter = new ExtentSparkReporter(filename);
		
		extentReport = new ExtentReports();
		
		extentReport.attachReporter(sparkReporter);
	}
	
	public void createExtentTest(String testName) {
		
		extentTest = extentReport.createTest(testName);
	}
	
	public void addLogToTest(Status status, String comment) {
		
		extentTest.log(status, comment);
	}
	
	public void closeExtentReport() {
		
		extentReport.flush();
	}

}
