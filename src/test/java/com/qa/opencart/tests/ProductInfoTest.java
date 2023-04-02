package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void getImagesCountTest() {
		searchResultPage = accPage.doSearch("MacBook");
		productInfoPage  = searchResultPage.selectProduct("MacBook Air");
		int imgCount =    productInfoPage.getProductImagesCount();
		Assert.assertEquals(imgCount, 4);
	}

	@Test
	public void productInfoTest() {
		searchResultPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Air");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v) -> System.out.println(k + ": " + v));
		
//		Set<String> keySet = actProductInfoMap.keySet();
//		Iterator itr = keySet.iterator();
//		while(itr.hasNext()) {
//		Object key	= itr.next();
//		System.out.println("Key---> " + key + " value ---> "+ actProductInfoMap.get(key) );
//		}
//		
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Air");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 17");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "700");
		softAssert.assertEquals(actProductInfoMap.get("Price"), "$1,202.00");
		softAssert.assertAll();
	}
}
