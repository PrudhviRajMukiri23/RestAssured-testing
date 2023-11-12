package tests;

import java.io.IOException;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import request.RequestFactory;
import utils.ConfigReader;
import utils.ExtentReportsUtil;

public class BaseStep {

	Properties prop;

	String currentDirectory;

	String fileName;

	RequestFactory requestFactory;

	ExtentReportsUtil extentrep;

	@BeforeSuite
	public void preSetup() throws IOException {

		currentDirectory = System.getProperty("user.dir");
		fileName = currentDirectory + "/src/test/resources/config/config.properties";

		prop = ConfigReader.readConfigFile(fileName);

		extentrep = new ExtentReportsUtil();

		extentrep.extentreportCreator(currentDirectory+ "/src/test/resources/reports/report.html");
	}

	@BeforeClass
	public void setUp() {

		extentrep.createExtentTest("SetUp--");
		RestAssured.baseURI = prop.getProperty("baseURI");
		RestAssured.basePath = prop.getProperty("basePath");

		extentrep.addLogToTest(Status.INFO	, "URI is "+prop.getProperty("baseURI"));
		extentrep.addLogToTest(Status.INFO	, "URI is "+prop.getProperty("basePath"));
		requestFactory = new RequestFactory();
	}

	@AfterMethod
	public void checkMethodStatus(ITestResult result) {

		//result.getName();

		if(result.getStatus() == ITestResult.SUCCESS) {
			extentrep.addLogToTest(Status.PASS, "All test step get passed");
		} else if(result.getStatus() == ITestResult.FAILURE) {
			extentrep.addLogToTest(Status.FAIL, "One or more test steps get failed");
		} else {
			extentrep.addLogToTest(Status.SKIP, "One or more test steps get skipped");
		}
	}

	@AfterSuite
	public void reSet() {

		RestAssured.reset();
		extentrep.closeExtentReport();
	}
}
