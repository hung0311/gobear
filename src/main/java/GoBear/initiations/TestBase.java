package GoBear.initiations;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import automationLibrary.drivers.DriverWeb;

public class TestBase {
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void beforeSuite(ITestContext context) {
		
	}

	@BeforeMethod
	public void beforeBaseMethod(Method method) {
			DriverWeb.initBrowser("chrome");
	}

	@Parameters("browser")
	@BeforeClass
	public void beforeBaseClass(@Optional("NOT_PAR") String browser) throws MalformedURLException {
		// DriverWeb.initBrowser(TestConfigurations.browser);
		if (TestConfigurations.runMode.equals("normal")) {
			// Handle to run Realtime mode
			if (!browser.equals("NOT_PAR")) {
				DriverWeb.killProcess(browser);
			} else {
				DriverWeb.killProcess(TestConfigurations.browser);
			}
		}
	}

	@Parameters("environment")
	@AfterMethod
	public void afterMethodClass(Method method, ITestResult result, ITestContext context, @Optional("NOT_PAR") String environment) {
		
		//DriverWeb.close();
		
	}

	@AfterSuite
	public void afterSuite(ITestContext testSuite) {
		
	}
}
