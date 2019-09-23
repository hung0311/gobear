package GoBear.initiations;

import java.io.File;

import automationLibrary.actions.ActionGen;

public class TestConfigurations
{
	private static final String configFileStr = System.getProperty("user.dir") + "//Config.properties";
	static File configFile = new File(configFileStr);	
	//Initiate the configurable variables
	public static final String url = ActionGen.getPropertyFileValue(configFile.getAbsolutePath(), "url");
	public static final String browser = ActionGen.getPropertyFileValue(configFile.getAbsolutePath(), "browser");
	public static final String runMode = ActionGen.getPropertyFileValue(configFile.getAbsolutePath(), "run_mode");
	}
