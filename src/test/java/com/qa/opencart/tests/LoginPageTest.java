package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("JIRA EPIC ID 102: Desing Open cart App - login Page")
@Story("JIRA US 1022:  OpenCart Login Design with multiple features"  )
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {
	
	@Description("Login Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority=1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, Errors.TITLE_ERROR_MESSG);	
	}
	
	@Description("Login Page URL Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageURL();
		System.out.println("actual url is: " + actUrl);
		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));	
	}
	
	@Description("forgot Pwd Link Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("Register Link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void registerLinkTest() {
		Assert.assertTrue(loginPage.isRegistrationLinkExist());
	}
	
	@Description("Login functionality Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=5)
	public void loginTest() {
		AccountsPage accPage =  loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
	}
	
}
