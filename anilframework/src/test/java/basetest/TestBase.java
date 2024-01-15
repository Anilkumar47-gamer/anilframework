package basetest;

import java.awt.Desktop;
import java.io.File;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import mobileUtility.MobileUtil;

public class TestBase {

	protected static MobileUtil utilityObj = MobileUtil.getobj();
	protected AppiumDriver driver;
 	protected Properties prop;
	private String deviceName;
	private String version;
	private String udid;
	private String apkpath;
	private String uiAutomator;
	
	
	@BeforeSuite
	public void beforeSuite() {
		
		utilityObj.initHtmlReport();
		prop = utilityObj.loadPropertyFile("config");
		deviceName = prop.getProperty("DeviceName");
		version = prop.getProperty("Version");
		uiAutomator = prop.getProperty("UIAutomator");
		
		udid = prop.getProperty("udid");
		 

	}

	@BeforeMethod
	public void launchApplication(ITestResult result) {
	
		utilityObj.setExtentLogger(result.getMethod().getMethodName());

		utilityObj.getExtentLogger().info(
				MarkupHelper.createLabel("Scenario -:- " + result.getMethod().getDescription(), ExtentColor.GREEN));
		driver=utilityObj.launchPhone("Android", version, deviceName, udid,uiAutomator, "app");
//		utilityObj.getElement("//android.widget.TextView[@text='GET STARTED']", "get Started").click();

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			utilityObj.getExtentLogger().log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " Test is Failed", ExtentColor.RED));
			utilityObj.getExtentLogger().log(Status.FAIL, "TEST  FAILED  " + result.getThrowable());
			utilityObj.getExtentLogger().addScreenCaptureFromPath(utilityObj.takeSnapshot(result.getName()));
		} else if (result.getStatus() == ITestResult.SKIP) {
			utilityObj.getExtentLogger().log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test is Skipped", ExtentColor.YELLOW));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			utilityObj.getExtentLogger().log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test is Passed", ExtentColor.GREEN));
			
		}
		 utilityObj.quitDriver();
	}

	@AfterSuite()
	public void afterSuite() {
		utilityObj.flushReport();
	}

}