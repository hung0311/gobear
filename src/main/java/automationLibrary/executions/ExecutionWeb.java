package automationLibrary.executions;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class ExecutionWeb
{
	public static Method method;
	public static String configuration;
	public static String className;

	// Variables for tracking test results
	public static boolean _result;
	public static  int length = 0;
	public static  int count = 0;
	public static  List<Boolean> arrayTestResult;
	public static  String result = "";
    public static WebDriverWait webDriverWait;

	public static ByteArrayOutputStream outputStream = null;
	
	public static void setTestFail(String log) {
		Reporter.log(log);
		//String methodname = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (method != null) {
			System.out.println(
					"[FAILED] " + method.getDeclaringClass().getName() + "." + method.getName() + " >>> " + log);
		} else
			System.out.println("[FAILED] >>> " + log);
		_result = false;
	}
	
	public static void runBeforeMethod(Method method) {
		System.out.println("----------------------\n******** STARTED RUNNING: " + method.getName()
				+ " ********\n----------------------");
		_result = true;
	}
	
	public void setTestFail(Method method, String log) {
		//Reporter.log(log);
		if (method != null) {
			System.out.println(
					"[FAILED] " + method.getDeclaringClass().getName() + "." + method.getName() + " >>> " + log);
		} else
			System.out.println("[FAILED] >>> " + log);
		_result = false;
	}
	
}
