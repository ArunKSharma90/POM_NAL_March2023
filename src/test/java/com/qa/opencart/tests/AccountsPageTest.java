package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String title = accPage.getAccPageTitle();
		System.out.println("acc page title is: " + title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}

//	@Test(priority = 2)
//	public void accPageHeaderTest() {
//		String header = accPage.getAccPageHeader();
//		System.out.println("acc page header is: " + header);
//		Assert.assertEquals(header, Constants.PAGE_HEADER);
//	}

	@Test(priority = 3)
	public void accSectionsListTest() {
		List<String> actAccSecList = accPage.getAccountSectionsList();
		System.out.println("actual sections: " + actAccSecList);
		Assert.assertEquals(actAccSecList, Constants.getExpectedAccSecList());
	}

	@Test(priority = 4)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"Macbook Pro"}, 
			{"Macbook Air"}, 
			{"Apple"}
			};
	}

	@Test(priority = 5, dataProvider = "productData" )
	public void searchTest(String productName) {
		searchResultPage = accPage.doSearch(productName);
		Assert.assertTrue(searchResultPage.getSearchProductListsCount() > 0);
		
	}

//   Adding the Test cases for searchResultPage also in this same class. no need for separate class. 
	
	@DataProvider
	public Object[][] getProductSelectData(){
		return new Object[][] {
			{"Macbook", "MacBook Air"},
			{"Macbook", "MacBook Pro"},
			{"Apple", "Apple Cinema 30\""}
		};
	}
	
	@Test (priority = 6, dataProvider = "getProductSelectData")
	public void selectProductTest(String productCategory, String mainProduct) {
		searchResultPage =  accPage.doSearch(productCategory);
		productInfoPage = searchResultPage.selectProduct(mainProduct);
		Assert.assertEquals(productInfoPage.getProductHeaderText(), mainProduct);
	}
	
}
