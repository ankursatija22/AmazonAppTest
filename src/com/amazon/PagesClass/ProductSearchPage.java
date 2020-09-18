package com.amazon.PagesClass;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.amazon.GenericFunctions.GenericFunctions;
import io.appium.java_client.android.AndroidDriver;
public class ProductSearchPage {
	
	
	GenericFunctions genLib=new GenericFunctions();
	Logger logger=Logger.getLogger(ProductSearchPage.class);
	
	public static String productPrice;
	
	//============================Elements Of the Page======================//
	
			
			
			@FindBy (id="com.amazon.mShop.android.shopping:id/rs_search_src_text")
			public  WebElement amazon_Searchproduct;
			
			
			
			@FindBys(@FindBy(id="com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text"))  //65-inch tv
			public List<WebElement> amazon_SearchProductListFromSearchBar;
			
			
			@FindBys(@FindBy(id="com.amazon.mShop.android.shopping:id/list_product_linear_layout"))  //65-inch tv
			public List<WebElement> amazon_ProductList;
			
			@FindBy (xpath="//android.view.View[@resource-id='tp_price_block_total_price_in']")
			public  WebElement amazon_ProductPrize;
			
			@FindBy (xpath="//*[@resource-id='title_feature_div']//following::android.view.View")
			public  WebElement amazon_ProductName;
			
			@FindBy (xpath="//android.widget.TextView[@text='Home']")
			public  WebElement amazon_HamburgerHomeButton;
			
			
			@FindBy (xpath="//android.view.View[@resource-id='buyNow']")   
			public  WebElement amazon_BuyNowButton;
			
			@FindBy (xpath="//android.view.View[@resource-id='pinnedContinueButton']")  
			public  WebElement amazon_ContinueButton;
			
			@FindBys(@FindBy(xpath="//android.widget.Button[@text='No Thanks']"))  
			public List<WebElement> amazon_NoThanksButton;
			
			
			@FindBy (xpath="//*[@resource-id='subtotals-marketplace-table']//following::android.view.View//following::android.view.View//following::android.view.View")
			public  WebElement amazon_FinalProductPrice;
			
			@FindBys(@FindBy (xpath="//android.view.View[@resource-id='s-1600662600000']"))  
			public List<WebElement> amazon_TimeSlot;
			
			
			//This method search the Product from search field and product is given from Properties file.			 
			public void searchProductFunctionality(AndroidDriver driver) throws InterruptedException, IOException{
			try{
			
				
				PageFactory.initElements(driver, this);
				logger.info("****************** Searching the Product******************");
				System.out.println("Product Search");
				amazon_HamburgerHomeButton.click();
				amazon_Searchproduct.click();
				genLib.explicitWaitTillPageLoad(2000);
				amazon_Searchproduct.sendKeys(genLib.readDataFromConfigFile("Product"));    // Product Name is coming from Properties file.
				for(int i=0;i<amazon_SearchProductListFromSearchBar.size();i++)
				{
					if(amazon_SearchProductListFromSearchBar.get(i).getText().equalsIgnoreCase(genLib.readDataFromConfigFile("Product")))
					{
						amazon_SearchProductListFromSearchBar.get(i).click();
						System.out.println(" Searched product Found");
						Assert.assertTrue(true);
						break;
					}

				}
			
			  }
			  catch(Exception e){
				  System.out.println("Exception------->" + e.getMessage());
				 
			  }
			}
			
			
			
			//This Method Select the Product and Move to Checkout Screen.
			// It captures product price and name.
			public void selectRandomSearchProductFunctionality(AndroidDriver driver) throws InterruptedException, IOException{
			try{
			
				
				PageFactory.initElements(driver, this);
				logger.info("****************** Selecting Random Searched Product******************");
				genLib.explicitWaitTillPageLoad(3000);
				genLib.scrollUp(driver);
				amazon_ProductList.get(2).click();
				
				String ProductName=amazon_ProductName.getText();
				System.out.println("Product Name :::  "+ProductName);
				genLib.scrollToExactText(driver, "Add to Cart");  // This is scrolling the page to the Text
				productPrice=amazon_ProductPrize.getText();
				System.out.println("Product Prize :::  "+productPrice);
				amazon_BuyNowButton.click();
				amazon_ContinueButton.click();
				if(amazon_TimeSlot.size()>0)                     // This check if user have to select slot time or not
				{
					driver.findElementByXPath("//android.view.View[@resource-id='s-1600662600000']").click();
					amazon_ContinueButton.click();
				}
				
// Here given Explicit wait as Thread Sleep, in order to bypass the payment Page manually.				
				genLib.explicitWaitTillPageLoad(20000);
			
			  }
			  catch(Exception e){
				  System.out.println("Exception------->" + e.getMessage());
				 
			  }
			}
			
			

}
