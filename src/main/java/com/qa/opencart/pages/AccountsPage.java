package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;


public class AccountsPage  { 

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By accSections = By.cssSelector("div#content h2");
//	private By header = By.xpath("//a[text()='Account']");      // 
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		return elementUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, 5);
	}

	public String getAccpageUrl() {
		return elementUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, 5);
	}

//	public String getAccPageHeader() {
//		return elementUtil.doGetText(header);
//	}
 
	public List<String> getAccountSectionsList() {
		List<String> accSecValueList = new ArrayList<String>();
		List<WebElement> accSecList = elementUtil.getElements(accSections);
		for (WebElement e : accSecList) {
			accSecValueList.add(e.getText());
		}
		// Collections.sort(accSecValueList);
		System.out.println("printing all the test cases here");
		return accSecValueList;
	}

	public boolean isLogoutLinkExist() {
		return elementUtil.doIsDisplayed(logoutLink);
	}
	
	public searchResultsPage doSearch(String productName) {
		System.out.println("searching the product: " + productName);
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchButton);
		return new searchResultsPage(driver);
	}
}
