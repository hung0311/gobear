package automationLibrary.drivers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.lang3.SystemUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import automationLibrary.actions.ActionGen;
import automationLibrary.executions.ExecutionWeb;
import automationLibrary.initiations.Configurations;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;

public class DriverWeb
{
	
	public static WebDriver instance;
	public static String browser;
	public static int pageWait;
	public static String driverPath = System.getProperty("user.dir") + "//Externals//";
	public static String imageFilePath;
	public static String imageUrl;
	public static String imageFilePathLogControl;
	public static String imageFileUrlLogControl;
	public static BrowserMobProxyServer server;

    public static void close() {
    	instance.quit();           
    }
        
    //Web
    public static void initBrowser(String browser)
    {
    	File file64 = null;
    	File file32 = null;
        switch (browser)
        {
            case "firefox":
            	file64 = new File(driverPath + "geckodriver_linux64.tar");
            	file32 = new File(driverPath + "geckodriver_linux32.tar");
            	if(SystemUtils.IS_OS_LINUX) {
            		file64.setExecutable(true);
            		file32.setExecutable(true);
            		if(SystemUtils.OS_ARCH.contains("64")) {
            			System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver_linux64.tar");
            		} else {
            			System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver_linux32.tar");
            		}
            	} else if (SystemUtils.IS_OS_WINDOWS) {
            		if(SystemUtils.OS_ARCH.contains("64")) {
            			System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver_64.exe");
            		} else {
            			System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver_32.exe");
            		}
            	}
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.manager.focusWhenStarting", true);
                profile.setPreference("browser.download.useDownloadDir", false);
                profile.setPreference("browser.helperApps.alwaysAsk.force", true);
                profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                profile.setPreference("browser.download.manager.closeWhenDone", true);
                profile.setPreference("browser.download.manager.showAlertOnComplete", false);
                profile.setPreference("browser.download.manager.useWindow", false);
                profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
                break;
            case "chrome":
            	file64 = new File(driverPath + "chromedriver_linux64");
            	file32 = new File(driverPath + "chromedriver_linux32");
            	//killProcess(browser);
            	ChromeOptions options = new ChromeOptions();
            	options.addArguments("--disable-web-security");
            	options.addArguments("--disable-popup-blocking");
				//options.addArguments("--headless");
            	Map<String, Object> prefs = new HashMap<String, Object>();
            	prefs.put("credentials_enable_service", false);
            	prefs.put("profile.password_manager_enabled", false);
            	prefs.put("default_content_setting.popups", 1);
            	options.setExperimentalOption("prefs", prefs);
            	            	
            	
            	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            	
        		LoggingPreferences logPrefs = new LoggingPreferences();
        		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        		
            	
            	if(SystemUtils.IS_OS_LINUX) {
            		file64.setExecutable(true);
            		file32.setExecutable(true);
            		if(SystemUtils.OS_ARCH.contains("64")) {
            			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver_linux64");
            		} else {
            			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver_linux32");
            		}
            	} else if (SystemUtils.IS_OS_WINDOWS) {
            		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
            	} else if (SystemUtils.IS_OS_MAC) {
					System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
				}
            	
            	
            		instance = new ChromeDriver(capabilities);
                break;
            case "iexplorer":
            	System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
            	instance = new InternetExplorerDriver();
                break;
        }
        
        setWaitTime();
        deleteCookies(); 
    }
   
    public static void deleteCookies()
    {
        if (instance == null) return;
        instance.manage().deleteAllCookies();
    }

	public static void deleteCookie(WebDriver driver, String domain) {
		String currentURL = driver.getCurrentUrl();
		if (!currentURL.contains(domain)) {
			driver.get("http://" + domain);
		}
		driver.manage().deleteAllCookies();
		driver.get(currentURL);
		ActionGen.sleep(Configurations.pageWait);
	}

	public static void setElementWait(int millisecond) {
		// Wait for element
		DriverWeb.instance.manage().timeouts().implicitlyWait(millisecond, TimeUnit.MILLISECONDS);
	}

	public static void setPageWait(int millisecond) {
		// Wait for page load
		DriverWeb.instance.manage().timeouts().pageLoadTimeout(millisecond, TimeUnit.MILLISECONDS);
	}
	
	public static void setWaitTime()
    {
		// Wait for element
		DriverWeb.setElementWait(30000);
		
		// Wait for page load
		DriverWeb.setPageWait(Configurations.pageWait);
		//DriverWeb.instance.manage().window().maximize();
       
    }
	
	public static void killProcess(String browser) {
		try {
			if (browser.equals("chrome")) {
				Runtime.getRuntime().exec("TASKKILL /F /IM chromedriver.exe");
				Runtime.getRuntime().exec("TASKKILL /F /IM chrome.exe");
			} else if (browser.equals("firefox")) {
				Runtime.getRuntime().exec("TASKKILL /F /IM geckodriver.exe");
				Runtime.getRuntime().exec("TASKKILL /F /IM firefox.exe");
			} else if (browser.equals("iexplorer")) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
