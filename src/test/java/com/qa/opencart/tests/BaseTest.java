package com.qa.opencart.tests;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.searchResultsPage;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	AccountsPage accPage;
	searchResultsPage searchResultPage;
	ProductInfoPage productInfoPage;
	SoftAssert softAssert;
	RegistrationPage registrationPage;
	
	@Parameters({ "browser", "browserversion" })
	@BeforeTest
	public void setUp(String browserName, String browserVersion) {
		df = new DriverFactory();
		prop = df.init_prop();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
		}
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();  // bcoz we are using this obj in all test classes for assertion. 
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
