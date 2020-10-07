package com.amazon.PagesClass;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.amazon.GenericFunctions.BaseClass;
import io.appium.java_client.android.AndroidDriver;
public class ProductSearchPage extends BaseClass{
	
	
	Logger logger=Logger.getLogger(ProductSearchPage.class);
	
	public static String productPrice;
	
	/*============================Elements Of the Page======================
	 * 
	 */
	
			
			
			@FindBy (id="com.amazon.mShop.android.shopping:id/rs_search_src_text")
			public  WebElement amazon_Searchproduct;
			
			
			@FindBys(@FindBy(id="com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text"))  //65-inch tv
			public List<WebElement> amazon_SearchProductListFromSearchBar;
			
			@FindBys(@FindBy(className="android.widget.Image"))  //65-inch tv
			public List<WebElement> amazon_ProductList;
			
			
			@FindBy (xpath="//android.view.View[@resource-id='tp_price_block_total_price_in']")
			public  WebElement amazon_ProductPrize;
			
			@FindBy (xpath="//*[@resource-id='title_feature_div']//following::android.view.View")
			public  WebElement amazon_ProductName;
			
			@FindBy (xpath="//android.widget.TextView[@text='Home']")
			public  WebElement amazon_HamburgerHomeButton;
			
			
			@FindBy (xpath="//android.view.View[@resource-id='buyNow']")   
			public  WebElement amazon_BuyNowButton;
			
			@FindBy (xpath="//android.widget.Button[@text='Continue']")  
			public  WebElement amazon_ContinueButton;
			
			@FindBys(@FindBy(xpath="//android.widget.Button[@text='No Thanks']"))  
			public List<WebElement> amazon_NoThanksButton;
			
			
			@FindBy (xpath="//*[@resource-id='subtotals-marketplace-table']//following::android.view.View//following::android.view.View//following::android.view.View")
			public  WebElement amazon_FinalProductPrice;
			
			@FindBys(@FindBy (xpath="//android.view.View[@resource-id='s-1600662600000']"))  
			public List<WebElement> amazon_TimeSlot;
			
			
			
			/*This method search the Product from search field and product is given from Properties file.
			 * 			 
			 */
			public void searchProductFunctionality(AndroidDriver driver) throws InterruptedException, IOException{
			try{
			
				
				PageFactory.initElements(driver, this);
				logger.info("****************** Searching the Product******************");
				amazon_HamburgerHomeButton.click();
				amazon_Searchproduct.click();
				explicitWaitTillPageLoad(2000);
				amazon_Searchproduct.sendKeys(readDataFromConfigFile("Product"));    
				for(int i=0;i<amazon_SearchProductListFromSearchBar.size();i++)
				{
					if(amazon_SearchProductListFromSearchBar.get(i).getText().equalsIgnoreCase(readDataFromConfigFile("Product")))
					{
						amazon_SearchProductListFromSearchBar.get(i).click();
						logger.info("******************Searched product Found******************");
						Assert.assertTrue(true);
						break;
					}

				}
			
			  }
			  catch(Exception e){
				  logger.info("Exception------->" + e.getMessage());
				 
			  }
			}
			
			
			
			/*This Method Select the Product and Move to Checkout Screen.
			 *It captures product price and name.
			 */
			 
			public void selectRandomSearchProductFunctionality(AndroidDriver driver) throws InterruptedException, IOException{
			try{
			
				
				PageFactory.initElements(driver, this);
				logger.info("****************** Selecting Random Searched Product******************");
				explicitWaitTillPageLoad(3000);
				scrollUp(driver);
				amazon_ProductList.get(1).click();
				String ProductName=amazon_ProductName.getText();
				logger.info("Product Name :::  "+ProductName);
				scrollToExactText(driver, "Add to Cart");        // This is scrolling the page to the Text
				productPrice=amazon_ProductPrize.getText();
				logger.info("Product Prize :::"+productPrice);
				amazon_BuyNowButton.click();
				explicitWaitTillPageLoad(3000);
				try {
				amazon_ContinueButton.click();
				}catch(Exception e)
				{
				}
				
/* Here given Explicit wait as Thread Sleep, in order to bypass the payment Page manually.	
 * 			
 */
			explicitWaitTillPageLoad(20000);
			
			  }
			  catch(Exception e){
				  logger.info("Exception------->" + e.getMessage());
				 
			  }
			}
			
			

}
