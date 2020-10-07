package com.amazon.DriverScripts;




import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.amazon.PagesClass.LoginPage;
import com.amazon.PagesClass.ProductSearchPage;
import com.amazon.PagesClass.VerifypriceofproductatcheckoutPage;

public class TestCaseExecutionScript extends TestReusable {
	
	static String workingDir = System.getProperty("user.dir");
	LoginPage login = new LoginPage();
	ProductSearchPage search=new ProductSearchPage();
	VerifypriceofproductatcheckoutPage verify=new VerifypriceofproductatcheckoutPage();
	Logger logger=Logger.getLogger(LoginPage.class);



	
	
	/* This TestCase is for logging into the Amazon APP.
	 * 
	 */
	@Test
	public void loginIntoAmazonApp()
	{
		try {
			logger.info("************************** LOGIN TestCase Started***********************************");
			login.loginFunctionality(driver);
		}
		catch(Exception e){	
			  logger.info("Exception------->"+e.toString());
		}
	}
	
	
	/*This TestCase is for Verifying the LoggedIN user is correct or not through its Initial Name appearing from Hamburger.
	 * 
	 */
	@Test
	public void verifylogiFunctionality()
	{
		try {
			logger.info("************************** VERIFY LOGIN FUNCTIONALITY TestCase Started***********************************");
			login.verifyLoginFunctionality(driver);
		}
		catch(Exception e){		
			  logger.info("Exception------->"+e.toString());
		}
	}
	
	
	/* This TestCase if for Searching the Product from the APP.
	 * 
	 */
	@Test
	public void searchProductFromAppFunctionality()
	{
		try {
			logger.info("**************************SEARCH PRODUCT FROM APP TestCase Started***********************************");
			search.searchProductFunctionality(driver);
		}
		catch(Exception e){		
			  logger.info("Exception------->"+e.toString());
		}
	}
	
	
	/*This TestCase is selecting the search product and going to checkout screen.
	 * 
	 */
	@Test
	public void selectRandomProductFromLListFunctionality()
	{
		try {
			logger.info("************************** SEARCH RANDOM PRODUCT FROM LIST OF PRODUCT ITEMS DISPLAYED AND PROEEDING TO CHECKOUT TestCase Started***********************************");
			search.selectRandomSearchProductFunctionality(driver);
		}
		catch(Exception e){		
			  logger.info("Exception------->"+e.toString());
		}
	}
	
	
	/* This TestCase is verifying the Product Amount at Checkout screen of APP.
	 * 
	 */
	@Test
	public void verifyProductPriceAtCheckoutFunctionality()
	{
		try {
			logger.info("**************************VERIFYING SELECTED PRODUCT PRICE AT CHECKOUT TestCase Started***********************************");
			verify.verifyProductPriceAtCheckoutFunctionality(driver);
		}
		catch(Exception e){		
			  logger.info("Exception------->"+e.toString());
		}
	}
	
	
	/*This TestCase is To place selected Product .
	*  This is not included in TS as After placing order, it redirect user to Bank Page to Pay amount.  ( Currently My card is active).
	  */
	@Test
	public void placingOrderFunctionality()
	{
		try {
			logger.info("**************************PLLACING ORDER FROM THE APP TestCase Started***********************************");
			verify.placeOrderFunctionality(driver);
		}
		catch(Exception e){		
			  logger.info("Exception------->"+e.toString());
		}
	}

	


}
