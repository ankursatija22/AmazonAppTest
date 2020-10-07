package com.amazon.GenericFunctions;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BaseClass {
	
	/* Function for Reading from config.Property File
	 * 
	 */
			public String readDataFromConfigFile(String StringTobeRead) {
				Properties prop = new Properties();
				InputStream input = null;
				String valueTotheCorrespondingKey = null;
				String propFileName = "config.properties";

				try {

					input = getClass().getClassLoader().getResourceAsStream(propFileName);
					// load a properties file
					prop.load(input);

					// get the property value and print it out
					valueTotheCorrespondingKey = (prop.getProperty(StringTobeRead));

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
				return valueTotheCorrespondingKey;
			}
			
			/* This Function is for scrolling into the App dynamically by calculating screen size.
			 */
			public void scrollUp(AndroidDriver driver) {

				Dimension dim = driver.manage().window().getSize();
				int x=dim.getWidth()/2;
				int startY = (int)(dim.getHeight() * 0.8);
				int endY = (int)(dim.getHeight() * 0.2);
				
				TouchAction action = new TouchAction(driver);
				action.press(PointOption.point(x,startY))
				        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(5000))) //you can change wait durations as per your requirement
				 .moveTo(PointOption.point(x,endY))
				        .release()
				        .perform();
				
				

			}
			
			
			/*This Function for wait till screen load
			 * 
			 */
			public void waitForElementToLoad(AndroidDriver driver, WebElement element, int seconds){
		        WebDriverWait wait = new WebDriverWait(driver,seconds);
		        wait.until(ExpectedConditions.elementToBeClickable(element));

		}
			
			/*This Function is to wait for an element.
			 * 
			 */
			public void explicitWaitTillPageLoad(int i) throws InterruptedException {
		    	Thread.sleep(i);
		    }
			
			
			
			/*This Function is for scroll to exact text visible on Screen.
			 * 
			 */
			public void scrollToExactText(AndroidDriver driver,String text)
			{
				
	            String uiSelectorinfo = "new UiSelector().textMatches(\"" + text+ "\")";
	            String commandinfo = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("+ uiSelectorinfo + ");";
	            driver.findElementByAndroidUIAutomator(commandinfo);
			}
			
			

}
