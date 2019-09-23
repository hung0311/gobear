package automationLibrary.actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automationLibrary.drivers.DriverWeb;
import automationLibrary.executions.ExecutionWeb;
import automationLibrary.initiations.Configurations;

public class ActionGen
{
	/**
	 * Calculate the pixel to move on a slider
	 * 
	 * @return
	 */
	public static int getPixelsToMove(WebElement slider, double amount, double sliderMax, double sliderMin)
    {
        int pixels = 0;
        double tempPixels = slider.getSize().getWidth();
        tempPixels = tempPixels / (sliderMax - sliderMin);
        tempPixels = tempPixels * (amount - sliderMin);
        pixels =  (int)tempPixels;
        return pixels;
    }
	
	/**
	 * Move a slider from left to right to a Specific value
	 * 
	 * @return
	 */
	public static void moveSliderToRight(WebElement slider, double amount, double sliderMax, double sliderMin)
    {
		Actions SliderAction = new Actions(DriverWeb.instance);
		int pixelsToSlide = ActionGen.getPixelsToMove(slider,amount,sliderMax,sliderMin);
		SliderAction.clickAndHold(slider)
		    .moveByOffset(((int)slider.getSize().getWidth()/ 2), 0)
		    .moveByOffset(pixelsToSlide, 0).release().perform();
    }
	
	/**
	 * Move a slider from right to left to a Specific value
	 * 
	 * @return
	 */
	public static void moveSliderToLeft(WebElement slider, double amount, double sliderMax, double sliderMin)
    {
		Actions SliderAction = new Actions(DriverWeb.instance);
		int pixelsToSlide = ActionGen.getPixelsToMove(slider,amount,sliderMax,sliderMin);
		SliderAction.clickAndHold(slider)
		    .moveByOffset((-(int)slider.getSize().getWidth()/ 2), 0)
		    .moveByOffset(pixelsToSlide, 0).release().perform();
    }
	
	/**
	 * Verify an logic value is True
	 * 
	 * @return
	 */
	public static void verifyTrue(boolean value,String message)
    {
		if (!value) {
			ExecutionWeb.setTestFail(message);
		}
    }
	
	/**
	 * This action is used to convert a date to another date format
	 **/
	public static String convertDateFormat(String dateInOriginalFormat, String originalFormat,String targetFormat)
	{
		DateFormat originalFmt = new SimpleDateFormat(originalFormat, Locale.ENGLISH);
		DateFormat targetFmt = new SimpleDateFormat(targetFormat);
		Date date = null;
		try {
			date = originalFmt.parse(dateInOriginalFormat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String formattedDate = targetFmt.format(date);
		return formattedDate;
	}
	
	
	/**
	 * This action is used to click on an element
	 **/
	public static void click(WebElement element) {
		if (element != null) {
			try {
				if(isElementPresent(By.id("closeopup"))) {
					getElement(By.id("closeopup")).click();
				}
				
				if(isElementPresent(By.xpath("//button[contains(@class,'CloseButton')]"))) {
					getElement(By.xpath("//button[contains(@class,'CloseButton')]")).click();
				}
				
				element.click();
			} catch (Exception e) {
				JavascriptExecutor executor = (JavascriptExecutor) DriverWeb.instance;
				executor.executeScript("arguments[0].click();", element);
			}
		}
		waitForPageLoad(Configurations.pageWait);
	}
	
	
	
	/**
	 * This action is used to get an instance of the WebElement from the input By object
	 **/
	public static WebElement getElement(By by) {
		// Store a string as element name for reporting on failure

		WebElement element = null;
		try {
			DriverWeb.setElementWait(0);
			element = DriverWeb.instance.findElement(by);
		} catch (Exception e) {
			
			System.out.println("Exception: " + e.getMessage());
			//captureScreenshot("NoSuchElementException");
			return null;
		} finally {
			DriverWeb.setElementWait(Configurations.implicitWait);
		}
		return element;
	}

	/**
	 * Wait for an element to be loaded
	 */
	public static void waitForElement(WebElement element, int maxWaitTimeInSeconds) {
		try {
			int i = 0;
			while (!(element.isEnabled() & !element.isEnabled() & element.isDisplayed())
					&& i < maxWaitTimeInSeconds * 1000) {
				Thread.sleep(10);
				i += 10;
			}
		} catch (Exception e) {
			return;
		}
	}
	
	/**
	 * Wait for the page fully loaded
	 * 
	 * @param maxWaitTimeInSeconds
	 */
	public static void waitForPageLoad(int timeout) {
		String state = null;
	    String oldstate = null;
	    try {
	        int i = 0;
	        while (i < 5) {
	            Thread.sleep(1000);
	            state = ((JavascriptExecutor) DriverWeb.instance).executeScript("return document.readyState;").toString();
	            //System.out.print("." + Character.toUpperCase(state.charAt(0)) + ".");
	            if (state.equals("interactive") || state.equals("loading"))
	                break;
	            
	             //* If browser in 'complete' state since last X seconds. Return.
	             

	            if (i == 1 && state.equals("complete")) {
	                //System.out.println();
	                return;
	            }
	            i++;
	        }
	        i = 0;
	        oldstate = null;
	        Thread.sleep(2000);

	        
	         //* Now wait for state to become complete
	         
	        while (true) {
	            state = ((JavascriptExecutor) DriverWeb.instance).executeScript("return document.readyState;").toString();
	            //System.out.print("." + state.charAt(0) + ".");
	            if (state.equals("complete"))
	                break;

	            if (state.equals(oldstate))
	                i++;
	            else
	                i = 0;
	            
	             //* If browser state is same (loading/interactive) since last 60
	             //* secs. Refresh the page.
	             
	            if (i == 15 && state.equals("loading")) {
	                System.out.println("\nBrowser in " + state + " state since last 60 secs. So refreshing browser.");
	                DriverWeb.instance.navigate().refresh();
	                System.out.print("Waiting for browser loading to complete");
	                i = 0;
	            } else if (i == 6 && state.equals("interactive")) {
	                System.out.println(
	                        "\nBrowser in " + state + " state since last 30 secs. So starting with execution.");
	                return;
	            }

	            Thread.sleep(4000);
	            oldstate = state;

	        }
	        System.out.println();
	        
	        if(isElementPresent(By.xpath("//span[contains(@class,'loader')]"))) {
				waitForElementNotPresent(By.xpath("//span[contains(@class,'loader')]"));
			}
	        
	        if(isElementPresent(By.xpath("//div[contains(@class,'skeleton')]"))) {
	        	waitForElementNotPresent(By.xpath("//div[contains(@class,'skeleton')]"));
	        }
	        
	        if(isElementPresent(By.xpath("//div[@id='ajax_loader'][@style='display: block;']"))) {
				waitForElementPresent(By.xpath("//div[@id='ajax_loader'][@style='display: none;']"));
			}
		
		/*ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            WebDriverWait wait = new WebDriverWait(DriverWeb.instance, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }*/

	    } catch (InterruptedException ie) {
	        ie.printStackTrace();
	    }
	}
	
	/**
	 * Launch an url
	 */
	public static void launch(String url)
	{
		DriverWeb.instance.get(url);
		//DriverWeb.instance.manage().window().maximize();
		waitForPageLoad(Configurations.pageWait);
	}
	
	/**
	 * Pause the execution in a period time stamp
	 */
	public static void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) DriverWeb.instance;
		executor.executeScript("arguments[0].scrollIntoView()", element);
		sleep(1000);
	}
	
	
	/**
	 * This action is used to verify all the verification points of a test method
	 **/
	public static void verifyTest() {
		// ------- Add test result to TestRail--------------------------
		Method method = ExecutionWeb.method;
		if (ExecutionWeb.length == 0) {
			Test tstAnnotation = method.getAnnotation(Test.class);
			Class<?> testClass = method.getDeclaringClass();
			for (Method me : testClass.getDeclaredMethods()) {
				if (me.getAnnotation(DataProvider.class) != null) {
					if (tstAnnotation.dataProvider().equals(me.getAnnotation(DataProvider.class).name())) {
						Object[][] ob = null;
						try {
							ob = (Object[][]) me.invoke(null, new Object[] { method });
							ExecutionWeb.length = ob.length;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		if (ExecutionWeb.count == 0) {
			ExecutionWeb.arrayTestResult = new ArrayList<Boolean>();
		}

		if (!ExecutionWeb._result) {
			Assert.assertTrue(false);
		} else {
			Assert.assertTrue(true);
		}
	}
	
	
	/**
	 * Check if the element is present
	 * 
	 **/
	public static Boolean isElementPresent(By by) {
		DriverWeb.setElementWait(0);
		try {
			DriverWeb.instance.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			DriverWeb.setElementWait(Configurations.implicitWait);
		}
	}
	
	
	
	public static void waitForElementPresent(By locator) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<>(DriverWeb.instance)
					.withTimeout(Configurations.maxWaitTimeInSecond, TimeUnit.SECONDS)
					.pollingEvery(Configurations.pollingTime, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class, NullPointerException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (TimeoutException e) {
			ExecutionWeb.setTestFail(e.getMessage());
		}
	}
	
	public static void waitForElementNotPresent(By locator) {
		int currentWaitTime = 0;
		do {
			if(currentWaitTime <= Configurations.waitTime) {
				sleep(Configurations.sleepTime);
				currentWaitTime = currentWaitTime + Configurations.sleepTime;
			} else {
				ExecutionWeb.setTestFail("Element with locator " + locator + " is still present!");
			}
		} while(isElementPresent(locator));
	}	
	
	/**
	 * SCroll screen follow direction
	 * 
	 * @param direction
	 * @param count
	 * @param wait
	 */
	public static void scroll(String direction, int count, int wait) {
		JavascriptExecutor jse = (JavascriptExecutor) DriverWeb.instance;
		for (int i = 0; i < count; i++) {
			if (direction.equals("down")) {
				jse.executeScript("window.scrollBy(0,100)", "");
			} else {
				jse.executeScript("window.scrollBy(0,-100)", "");
			}
			sleep(wait);
		}
		waitForPageLoad(Configurations.pageWait);
	}
	
	/**
	 * This action is used to get the value of a key in a property file
	 **/
	public static String getPropertyFileValue(String filename, String key) {
		Properties prop = new Properties();
		InputStream input = null;
		String value = "";
		try {
			input = new FileInputStream(filename);
			prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
			value = prop.getProperty(key);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
	
}
