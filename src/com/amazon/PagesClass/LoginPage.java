package com.amazon.PagesClass;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.amazon.GenericFunctions.BaseClass;
import com.amazon.GenericFunctions.TestUtil;

import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends BaseClass{
	
	public static TestUtil excel = new TestUtil(System.getProperty("user.dir")+"//XLFile//Amazon_Details.xlsx");
	private static String Pathoftestdata =TestUtil.path;
	Logger logger=Logger.getLogger(LoginPage.class);
	private String username;
	private String password;
	
	/*============================Elements of the Page======================
	 * 
	 */
	
			
			
			@FindBy (id="com.amazon.mShop.android.shopping:id/sign_in_button")
			public  WebElement amazon_LoginButton;
			
			@FindBy (xpath="//android.widget.EditText[@resource-id='ap_email_login']")
			public  WebElement amazon_EmailField;
			
			@FindBy (xpath="//android.widget.Button[@resource-id='continue']")
			public  WebElement amazon_ContinueButton;
			
			@FindBy (xpath="//android.widget.EditText[@resource-id='ap_password']")  
			public  WebElement amazon_PasswordField;
			
			@FindBy (xpath="//android.widget.Button[@resource-id='signInSubmit']")
			public  WebElement amazon_SubmitButton;
			
			@FindBy (id="com.amazon.mShop.android.shopping:id/chrome_action_bar_burger")  
			public  WebElement amazon_HamburgerIcon;
			
			@FindBy (xpath="//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/gno_greeting_text_view']") // This will give the name of User with whom it's loggedIn.  For example:  Hello, Ankur
			public  WebElement amazon_UserName;
			
			@FindBy (xpath="//android.widget.TextView[@text='Home']")
			public  WebElement amazon_HamburgerHomeButton;
			
			
			
			
			
			
			/*Login Into the Amazon App.
			 * 		 
			 */
			public void loginFunctionality(AndroidDriver driver) throws InterruptedException, IOException{
			try{
				logger.info("****************** Loggin into the App******************");
				logger.info("****************** Reading Data from excel Sheet******************");
				String sheetname="Credentials";
				
				    String[][] Username =TestUtil.GetValue(Pathoftestdata, sheetname,1); 
				    username = Username[0][0]; 
				    String[][] Password =TestUtil.GetValue(Pathoftestdata, sheetname,1); 
				    password = Password[0][1]; 
				    logger.info("****************** Credentials--->\"+username +password******************");
			        /* Data from Excel Sheet
			         * 
			         */
			        PageFactory.initElements(driver, this);
				amazon_LoginButton.click();
				amazon_EmailField.sendKeys(username);  
				amazon_ContinueButton.click();
				amazon_PasswordField.sendKeys(password);
				amazon_SubmitButton.click();
				logger.info("User is loggeed into the App");
			
			  }
			  catch(Exception e){
				  logger.info("Exception------->" + e.getMessage());
				 
			  }
			}
			
			
			/*Verify the Logged IN user into the APP through hamburger. Verify its Initial Name which is displayed inside hamburger.
			 * 		 
			 */
			public void verifyLoginFunctionality(AndroidDriver driver) throws InterruptedException, IOException{
			try{
			
				
				PageFactory.initElements(driver, this);
				logger.info("****************** Verifying the logged IN user for the App******************");
				amazon_HamburgerIcon.click();
				String Username= amazon_UserName.getText();
				logger.info("Username ::::: " + Username);
				Assert.assertEquals(Username,"Hello, "+readDataFromConfigFile("Username"));   // Name is user is taken from Properties file
			
				logger.info("****************** User Verified******************");
			
			  }
			  catch(Exception e){
				  logger.info("Exception------->" + e.getMessage());
				 
			  }
			}

}
