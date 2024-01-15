package mobileUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

@SuppressWarnings({ "unused", "deprecation" })
public class MobileUtil {

	private static AppiumDriver driver;
	private DesiredCapabilities capabilities;
	private URL url;
	private ExtentReports extReport;
	private ExtentTest extentLogger;
	private static MobileUtil utils;
	private WebDriverWait wait;
	private MobileUtil() {

	}

	public static MobileUtil getobj() {
		if (utils == null) {
			utils = new MobileUtil();
		}
		return utils;
	}

	public AppiumDriver launchPhone(String deviceType, String version, String deviceName, String deviceId,
			String uiAutomator, String appName) {

		if (deviceType.equalsIgnoreCase("Android")) {
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, uiAutomator);
			capabilities.setCapability(MobileCapabilityType.UDID, deviceId);

			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
			capabilities.setCapability("adbExecTimeout", "200000");
			capabilities.setCapability(MobileCapabilityType.APP,
					new File(System.getProperty("user.dir") + "//" + loadPropertyFile("config").getProperty(appName))
							+ ".apk");
			try {
				url = new URL("http://127.0.0.1:4723/wd/hub");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver = new AndroidDriver(url, capabilities);
			extentLogger.log(Status.INFO, deviceType + " Driver is launched successFully  with " + appName + " apk"
					+ " Device(" + deviceName + ") , Version (" + version + ")");
		} else if (deviceName.equalsIgnoreCase("IOS")) {

			try {
				url = new URL("http://127.0.0.1:4723/wd/hub");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			capabilities = new DesiredCapabilities();
			driver = new IOSDriver(url, capabilities);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));

			extentLogger.log(Status.INFO, deviceType + " Driver is launched successFully");
		}
		holdOn(5);
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// extentLogger.log(Status.INFO, "ImplicitlyWait is applied for 20 seconds");

		return driver;

	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getElement(String xpath, String element) {
		WebElement web = null;
		if (xpath.equalsIgnoreCase("xpath")) {
			web = driver.findElement(AppiumBy.xpath(xpath));
			System.out.println(element + " element is find by xpath ");
		} else if (xpath.equalsIgnoreCase("id")) {
			web = driver.findElement(AppiumBy.id(xpath));
			System.out.println(element + "  element is find by id ");
		}
		return web;

	}

	public void inputData(WebElement element, String value) {
		try {
			
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
			for(int i=0;i<80;i++) {
				try {
					element.sendKeys(value);
					break;
				}catch(NoSuchElementException e) {
					Thread.sleep(500);
				}
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			
			extentLogger.log(Status.INFO, "Value(" + value + ") is  inputed SuccesFully in " + element.toString());

		} catch (Exception e) {
			extentLogger.log(Status.INFO, "Value(" + value + ") is  inputed SuccesFully in " + element.toString());
			e.getStackTrace();
		}
	}

	// ========================close and quit=========================\\

	public void click(WebElement we, String elementname) {

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
			for(int i=0;i<80;i++) {
				try {
					we.click();
					break;
				}catch(NoSuchElementException e) {
					Thread.sleep(500);
				}
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
			extentLogger.log(Status.INFO, "Clicked performed on " + elementname);
		} catch (Exception e) {
			extentLogger.log(Status.WARNING, "Clicked is not able to performed on " + elementname);
		}
	}

	public void dynamicWait(long time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public Properties loadPropertyFile(String Filename) {

		Properties propObj = new Properties();
		File fle = new File(System.getProperty("user.dir") + "\\" + "src\\main\\resources\\propertiesFiles\\" + Filename
				+ ".properties");
		FileInputStream fis = null;

		try {

			fis = new FileInputStream(fle);
			propObj.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propObj;
	}

	public void holdOn(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pressBackButton() {
		try {
			driver.navigate().back();
			extentLogger.log(Status.INFO, " Navigate back action performed successFully");
		} catch (Exception e) {
			extentLogger.log(Status.WARNING, " not able to perform  back action ");
		}
	}

	public void scrollHorizontal(String text) {
		try {
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
							+ text + "\").instance(0))"));
			extentLogger.log(Status.INFO, "Scroll Action performed successfully on " + text);

		} catch (Exception e) {
			extentLogger.log(Status.WARNING, "Not able to perform Scroll Action  on " + text);
		}
	}

	// ========================close and quit=========================\\

	public void quitDriver() {
		try {
			driver.quit();
			extentLogger.log(Status.INFO, "Driver has been closed successfully");
		} catch (Exception e) {
			extentLogger.log(Status.WARNING, "Driver has not been closed successfully");
		}
	}

	// ===============================ScreenShot======================================\\

	public String takeSnapshot(String methodname) {

		TakesScreenshot tcc = (TakesScreenshot) driver;
		File srcFile = tcc.getScreenshotAs(OutputType.FILE);
		File destFile = new File("ExtentReport/" + methodname + SimpleDateTimeFormate() + ".png");
		String path = destFile.getAbsolutePath();

		try {
			Files.copy(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	public void verifyText(WebElement element, String expectedText) {
		waitForVisible(element, 30);
		String actualText = element.getText();
		if (actualText.equalsIgnoreCase(expectedText)) {
			extentLogger.log(Status.PASS,
					MarkupHelper.createLabel(
							"Actual Text (" + actualText + ") is matched with Expected Text(" + expectedText + ")",
							ExtentColor.GREEN));
		} else {
			extentLogger.log(Status.PASS,
					MarkupHelper.createLabel(
							"Actual Text (" + actualText + ") is missmatched with Expected Text(" + expectedText + ")",
							ExtentColor.RED));
			Assert.assertEquals(actualText, expectedText);
		}

	}

	public void verifyAttributeValue(WebElement element, String Atttribute, String ExpectedattributeValue) {
		waitForVisible(element, 30);
		String actualAttributeVal = element.getAttribute(Atttribute);
		if (actualAttributeVal.contains(ExpectedattributeValue)) {
			extentLogger.log(Status.PASS,
					MarkupHelper.createLabel(
							"TestCase is Passed :-  Actual Value(" + actualAttributeVal
									+ ") is matched with Expected Value(" + ExpectedattributeValue + ")",
							ExtentColor.GREEN));
		} else {
			extentLogger.log(Status.FAIL,
					MarkupHelper.createLabel(
							"TestCase Is Failed :-Actual Value(" + actualAttributeVal
									+ ") is Mismatched with Expected Value(" + ExpectedattributeValue + ")",
							ExtentColor.GREEN));
			Assert.assertEquals(actualAttributeVal, ExpectedattributeValue);
		}
	}

	public void waitForVisible(WebElement element, int time) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void verifyElement_IsVisible(WebElement element, String elementname) {
		waitForVisible(element, 30);
		boolean actual = element.isDisplayed();
		if (actual == true) {
			extentLogger.log(Status.PASS, MarkupHelper
					.createLabel("TestCase is Passed :- " + elementname + " is Visible On Page", ExtentColor.GREEN));
		} else {
			extentLogger.log(Status.FAIL, MarkupHelper
					.createLabel("TestCase is Failed :- " + elementname + " is InVisible On Page", ExtentColor.RED));
			Assert.assertEquals(actual, true);

		}

	}

	public void verifyElement_IsEnable(WebElement element, String elementname) {
		boolean actual = element.isEnabled();

		if (actual == true) {
			extentLogger.log(Status.FAIL, MarkupHelper
					.createLabel("TestCase is Passed :- " + elementname + " is Enable ", ExtentColor.GREEN));
		} else {
			extentLogger.log(Status.FAIL,
					MarkupHelper.createLabel("TestCase is Failed :- " + elementname + " is Dible ", ExtentColor.RED));
			Assert.assertEquals(actual, true);

		}

	}

	public void verifyElement_IsSelected(WebElement element, String elementname) {
		boolean actual = element.isDisplayed();

		if (actual == true) {
			extentLogger.log(Status.FAIL, MarkupHelper
					.createLabel("TestCase is Passed :- " + elementname + " is Selected ", ExtentColor.GREEN));
		} else {
			extentLogger.log(Status.FAIL, MarkupHelper
					.createLabel("TestCase is Failed :- " + elementname + " is UnSelected ", ExtentColor.RED));
			Assert.assertEquals(actual, true);

		}

	}

	public String SimpleDateTimeFormate() {
		SimpleDateFormat timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss");
		Date date = new Date();
		return timestamp.format(date);

	}

	public void initHtmlReport() {

		String path = System.getProperty("user.dir") + "//ExtentReport// ";
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(path + "Mobilereport.html");
		htmlReport.config().setReportName(" Mobile Automation");
		htmlReport.config().setDocumentTitle("Mobile Automation Report");
		htmlReport.config().setTheme(Theme.DARK);
		extReport = new ExtentReports();
		extReport.attachReporter(htmlReport);
		extReport.setSystemInfo("Username", System.getProperty("user.name"));
		extReport.setSystemInfo("Envoirment", "QA");
		extReport.setSystemInfo("OS", System.getProperty("os.name"));

	}

	public ExtentTest getExtentLogger() {
		return extentLogger;
	}

	public void setExtentLogger(String testCaseName) {

		extentLogger = extReport.createTest(testCaseName);
	}

	public void flushReport() {
		extReport.flush();
	}
}
