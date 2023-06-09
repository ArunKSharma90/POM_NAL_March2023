package com.qa.opencart.tests;

import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void setupRegistration() {
		registrationPage = loginPage.goToRegisterPage();
	}

	public String getRandomEmail() {
		Random random = new Random();
		String email = "testautomation_" + random.nextInt(5000) + "@gmail.com";
		System.out.println(email);
		return email;
	}

	@DataProvider
	public Object[][] getRegTestData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}

	@Test(dataProvider = "getRegTestData")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(registrationPage.accountRegistration(firstName, lastName, getRandomEmail(), telephone,
				password, subscribe));
	}

}
