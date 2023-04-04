package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
//	1. Declare Private driver
	private WebDriver driver;
	private ElementUtil eleUtil;

//	2. create login page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}

//	3. Create By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By loginErrorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

//	4. Page Actions/Behaviors/Functionalities
	@Step("getting login page title.....")
	public String getLoginPageTitle() {
//		return driver.getTitle();
		return eleUtil.waitForTitleContains(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	@Step("getting login page header text....")
	public String getLoginPageURL() {
//		return driver.getCurrentUrl();
		return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	@Step("checking forgot pwd link is displayed on login page....")
	public boolean isForgotPwdLinkExist() {
//		return driver.findElement(forgotPwdLink).isDisplayed();  // don't use this rather u have to use your Actions/ElementUtil/Keywords class.
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	@Step("Checking if Registrstion Link is dosplayed or Not")
	public boolean isRegistrationLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}
	
	@Step("login to application with username {0} and password {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Logging in with: " + un + ":" + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("login to application with invalid username {0} and password {1}")
	public boolean doLoginWithNegativeInput(String un, String pwd) {
		System.out.println("Logging in with Wrong Creadentials: " + un + ":" + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		String errorMsg = eleUtil.doGetText(loginErrorMsg);
		System.out.println("Error in Login: " + errorMsg);
		if(errorMsg.contains(Constants.LOGIN_ERROR_MSG)) {
			System.out.println("Login is not done as expected for wrong credentials ..validation passed !");
			return false;
		}
		return true;
	}
	
	@Step("navigating to reg page....")
	public RegistrationPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
		
	}
	
}
