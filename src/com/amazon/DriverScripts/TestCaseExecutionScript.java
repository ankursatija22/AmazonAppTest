package com.amazon.DriverScripts;



import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.MalformedURLException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.amazon.GenericFunctions.GenericFunctions;
import com.amazon.PagesClass.LoginPage;
import com.amazon.PagesClass.ProductSearchPage;
import com.amazon.PagesClass.VerifypriceofproductatcheckoutPage;

public class TestCaseExecutionScript extends Thread {
	
	public AndroidDriver driver = null;
	static String workingDir = System.getProperty("user.dir");
	GenericFunctions genLib=new GenericFunctions();
	LoginPage login = new LoginPage();
	ProductSearchPage search=new ProductSearchPage();
	VerifypriceofproductatcheckoutPage verify=new VerifypriceofproductatcheckoutPage();



	// This method is calling the launch APP method and taking driver value.
	@BeforeTest
	public void setUp() throws MalformedURLException {
		try {
			driver = genLib.launchAmazonApp();
		} catch (Exception e) {
			System.out.println("Driver Value in catch====");
			System.out.println("Exception------->" + e.getMessage());
		}
	}


	
	
	// This TestCase is for logging into the Amazon APP.
	@Test
	public void loginIntoAmazonApp()
	{
		try {
			System.out.println("************************** LOGIN TestCase Started***********************************");
			login.loginFunctionality(driver);
		}
		catch(Exception e){		
			System.out.println("Exception------->"+e.toString());
		}
	}
	
	
	// This TestCase is for Verifying the LoggedIN user is correct or not through its Initial Name appearing from Hamburger.
	@Test
	public void verifylogiFunctionality()
	{
		try {
			System.out.println("************************** VERIFY LOGIN FUNCTIONALITY TestCase Started***********************************");
			login.verifyLoginFunctionality(driver);
		}
		catch(Exception e){		
			System.out.println("Exception------->"+e.toString());
		}
	}
	
	
	// This TestCase if for Searching the Product from the APP.
	@Test
	public void searchProductFromAppFunctionality()
	{
		try {
			System.out.println("************************** SEARCH PRODUCT FROM APP TestCase Started***********************************");
			search.searchProductFunctionality(driver);
		}
		catch(Exception e){		
			System.out.println("Exception------->"+e.toString());
		}
	}
	
	
	//This TestCase is selecting the search product and going to checkout screen.
	@Test
	public void selectRandomProductFromLListFunctionality()
	{
		try {
			System.out.println("************************** SEARCH RANDOM PRODUCT FROM LIST OF PRODUCT ITEMS DISPLAYED AND PROEEDING TO CHECKOUT TestCase Started***********************************");
			search.selectRandomSearchProductFunctionality(driver);
		}
		catch(Exception e){		
			System.out.println("Exception------->"+e.toString());
		}
	}
	
	
	// This TestCase is verifying the Product Amount at Checkout screen of APP.
	@Test
	public void verifyProductPriceAtCheckoutFunctionality()
	{
		try {
			System.out.println("************************** VERIFYING SELECTED PRODUCT PRICE AT CHECKOUT TestCase Started***********************************");
			verify.verifyProductPriceAtCheckoutFunctionality(driver);
		}
		catch(Exception e){		
			System.out.println("Exception------->"+e.toString());
		}
	}
	
	
	//This TestCase is To place selected Product .
	 // This is not included in TS as After placing order, it redirect user to Bank Page to Pay amount.  ( Currently My card is active).
	@Test
	public void placingOrderFunctionality()
	{
		try {
			System.out.println("************************** PLLACING ORDER FROM THE APP TestCase Started***********************************");
			verify.placeOrderFunctionality(driver);
		}
		catch(Exception e){		
			System.out.println("Exception------->"+e.toString());
		}
	}

	
	// This method is quieting the driver session at class level.
	@AfterClass
	public void tearDown(ITestResult result) throws IOException, InterruptedException {
		try {
		
			driver.quit();

		} catch (Exception e) {
			System.out.println("teardown");
			System.out.println("Exception------->" + e.toString());
		}

	}




}
