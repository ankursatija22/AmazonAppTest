package com.amazon.PagesClass;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.amazon.GenericFunctions.GenericFunctions;
import io.appium.java_client.android.AndroidDriver;

public class VerifypriceofproductatcheckoutPage {
	
	GenericFunctions genLib=new GenericFunctions();
	Logger logger=Logger.getLogger(ProductSearchPage.class);
	
	//============================Elements of the Page======================//
	
			
			
	@FindBy (xpath="//*[@resource-id='subtotals-marketplace-table']//following::android.view.View//following::android.view.View//following::android.view.View")
	public  WebElement amazon_FinalProductPrice;
	
	@FindBy (xpath="//android.view.View[@resource-id='placeYourOrder']")  
	public WebElement amazon_PlaceYourOrderButton;
			
			
			//This method verify the price of the Product at checkkoutScreen			 
			public void verifyProductPriceAtCheckoutFunctionality(AndroidDriver driver) throws InterruptedException, IOException{
			try{
			
				
				PageFactory.initElements(driver, this);
				logger.info("****************** Verify The product price at checkout Screen******************");
				
				String checkoutPrice = amazon_FinalProductPrice.getText();
				String Price=checkoutPrice.replaceAll("\\s",""); 
				System.out.println("Actual Price"+Price);
				String expectedPrice=ProductSearchPage.productPrice;
				System.out.println("Expected price"+expectedPrice);
				
				
				Assert.assertEquals(expectedPrice,Price);
			
			
			  }
			  catch(Exception e){
				  System.out.println("Exception------->" + e.getMessage());
				 
			  }
			}
	
			//This method place the Order by tapping Place the Order.			 
			// This method will not be inclued while executing the TestCase.
			public void placeOrderFunctionality(AndroidDriver driver) throws InterruptedException, IOException{
			try{
				PageFactory.initElements(driver, this);
				logger.info("****************** Placing the Order which customer is buying******************");
				amazon_PlaceYourOrderButton.click();
			  }
			  catch(Exception e){
				  System.out.println("Exception------->" + e.getMessage());
				 
			  }
			}

}
