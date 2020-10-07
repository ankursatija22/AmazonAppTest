package com.amazon.PagesClass;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.GenericFunctions.BaseClass;
import io.appium.java_client.android.AndroidDriver;

public class VerifypriceofproductatcheckoutPage extends BaseClass{
	
	Logger logger=Logger.getLogger(ProductSearchPage.class);
	
	/*============================Elements of the Page======================
	 * 
	 */
	
			
	@FindBy (xpath="//*[@resource-id='subtotals-marketplace-table']//following::android.view.View//following::android.view.View//following::android.view.View")
	public  WebElement amazon_FinalProductPrice;
	
	@FindBy (xpath="//android.view.View[@resource-id='placeYourOrder']")  
	public WebElement amazon_PlaceYourOrderButton;
			
			
			/*This method verify the price of the Product at checkkoutScreen
			 * 			 
			 */
			public void verifyProductPriceAtCheckoutFunctionality(AndroidDriver driver) throws InterruptedException, IOException{
			try{
			
				
				PageFactory.initElements(driver, this);
				logger.info("****************** Verify The product price at checkout Screen******************");
				
				String checkoutPrice = amazon_FinalProductPrice.getText();
				 logger.info("Actual Price"+checkoutPrice);
				String expectedPrice=ProductSearchPage.productPrice;
				logger.info("Expected price"+expectedPrice);
				if(checkoutPrice.contains(expectedPrice))
				{
				Assert.assertTrue(true);	
				}else {
					Assert.assertTrue(false);
				}
			  }
			  catch(Exception e){
				  logger.info("Exception------->" + e.getMessage());
				 
			  }
			}
	
			
			
			/*This method place the Order by tapping Place the Order.			 
			* This method will not be included while executing the TestCase.
			 * 
			 */
			public void placeOrderFunctionality(AndroidDriver driver) throws InterruptedException, IOException{
			try{
				PageFactory.initElements(driver, this);
				logger.info("****************** Placing the Order which customer is buying******************");
				amazon_PlaceYourOrderButton.click();
			  }
			  catch(Exception e){
				  logger.info("Exception------->" + e.getMessage());
				 
			  }
			}
			
		
}
