package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver; 
	private ElementUtil eleUtil;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);	
	}
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages  = By.cssSelector("ul.thumbnails li");
	private By productMetadata = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPricedata = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");

	private Map<String, String> productInfoMap;
	
	public String getProductHeaderText() {
		return eleUtil.doGetText(productHeader);
	}
	public int getProductImagesCount() {
		int imgCount = eleUtil.getElements(productImages).size();
		System.out.println("The total img count is :  " + imgCount);
		return imgCount;
	}
	
	public Map<String, String> getProductInfo() {
		productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getProductHeaderText());  // adding product name to Map
		
		List<WebElement> metaDataList =   eleUtil.getElements(productMetadata);
		//		Brand: Apple
		//		Product Code: Product 15
		//		Reward Points: 100
		//		Availability: In Stock
		System.out.println("Total no. of product meta data list " +  metaDataList.size());
		for(WebElement e: metaDataList) {
			String meta[] = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim(); 
			productInfoMap.put(metaKey, metaValue);       // adding meta data to Map
		}
		
		//Price data 
		List<WebElement> priceList =   eleUtil.getElements(productPricedata);

		String price = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().trim();
		productInfoMap.put("Price", price);				// adding price data to Map
		productInfoMap.put("ExTaxPrice", exTaxPrice);
		return 	productInfoMap;                         // now returning Map with all data - Name,MetaData and Price info          	
	}
		
}
