package com.amazon.DriverScripts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import com.amazon.GenericFunctions.BaseClass;
import com.amazon.PagesClass.LoginPage;

import io.appium.java_client.android.AndroidDriver;

public class TestReusable {
	
	public AndroidDriver driver;
	BaseClass base= new BaseClass();
	Logger logger=Logger.getLogger(LoginPage.class);
	
	
	/* This function will intilizile the Appium driver and will start the App on Mobile Device.
	 * 
	 */
	@BeforeTest
	public void setUp() throws MalformedURLException {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("noReset", "false");
			capabilities.setCapability("platformName ", "Android");
			capabilities.setCapability("deviceName",base.readDataFromConfigFile("deviceName") );   // Device value is given through Properties File.
			capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
			capabilities.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
			  capabilities.setCapability("automationName", "uiautomator2"); 
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			logger.info("Driver while Launching" + driver);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		} catch (Exception e) {
			  logger.info("Exception------->" + e.getMessage());
		}
	}
	
	
	
	
	
	/*This method is quieting the driver session at class level.
	 * 
	 */
		@AfterClass
		public void tearDown() throws IOException, InterruptedException {
			try {
			
				driver.quit();

			} catch (Exception e) {
				  logger.info("teardown");
				  logger.info("Exception------->" + e.getMessage());
			}

		}

}
