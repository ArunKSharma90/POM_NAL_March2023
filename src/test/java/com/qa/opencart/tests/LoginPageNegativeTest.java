package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] loginWrongTestData() {
		return new Object[][] {
			{"abc1@gmail.com" ,  "abdsnf@345435"},
			{"xyz1@gmail.com" ,  "a@rt35"},
			{"ramkumar1@gmail.com" ,  "abd@345435"},
			{"", "df@456"},
			{"abf@outlook.com", ""},
		};
	}

	@Test (dataProvider = "loginWrongTestData")
	public void loginNegativeTest(String uname, String pwd) {
		Assert.assertFalse(loginPage.doLoginWithNegativeInput(uname, pwd));
	}

}
