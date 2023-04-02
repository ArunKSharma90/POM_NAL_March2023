package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class searchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public searchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By searchHeader = By.cssSelector("div#content h1");
	private By productResults = By.cssSelector("div.caption a");
	
	public String getSearchPageHeader() {
		return eleUtil.doGetText(searchHeader);
	}
	
	
	public int getSearchProductListsCount() {
	int resultCount  =  eleUtil.getElements(productResults).size();
	System.out.println("search product count is : " + resultCount);
	return resultCount;
}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("main product name is : " + mainProductName );
		List<WebElement> searchList = eleUtil.getElements(productResults);
		for(WebElement e : searchList) {
			if(e.getText().trim().equalsIgnoreCase(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}		
}
