package automationLibrary.initiations;

import java.io.File;

public class Configurations
{
	private static String configFileStr = System.getProperty("user.dir") + "\\src\\main\\java\\automationLibrary\\initiations\\Config.properties";

	static File configFile = new File(configFileStr);
	
	//public static int pageWait = Integer.parseInt(ActionGenWeb.getPropertyFileValue(configFile.getAbsolutePath(), "pageWait"));
	//public static int implicitWait = Integer.parseInt(ActionGenWeb.getPropertyFileValue(configFile.getAbsolutePath(), "implicitWait"));
	//public static int waitTime = Integer.parseInt(ActionGenWeb.getPropertyFileValue(configFile.getAbsolutePath(), "waitTime"));
	
	public static int pageWait = 120000;
	public static int implicitWait = 30000;
	public static int waitTime = 30000;
	public static int maxWaitTimeInSecond = 30;
	public static int sleepTime = 1000;
	public static long waitTimeInSecond = 20;
	public static int pollingTime = 200;
}
