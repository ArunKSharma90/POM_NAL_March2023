package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * @author arunk
 *This class contains all the browser options which user/tester want to use while running the scripts
 *These options should not be used directly in DriverFactory class as will violate the SRP (Single responsibility Principal) 
 *Driver factory is used to initialize the driver with given prop parameters 
 *OptionsManager will manage all browser related capabilities/options, create an object of this class and use it in Driver Factory
 *
 */ 
public class OptionsManager {

	public Properties prop ; 
	public ChromeOptions co; 
	public FirefoxOptions fo;
	public EdgeOptions eo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").toLowerCase())) {
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").toLowerCase())) {
			co.addArguments("--incognito");
		}
//		co.addArguments("--remote-allow-origins=*");   // this was used for issue with Chrome version 111 - march 2023, fixed in latest Selenium 4.8.2 onwards
		return co;
	}
	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").toLowerCase())) {
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").toLowerCase())) {
			fo.addArguments("--incognito");
		}
		return fo;
		
	}
	public EdgeOptions getEdgeOptions() {
		EdgeOptions eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").toLowerCase())) {
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").toLowerCase())) {
			eo.addArguments("--incognito");
		}
		return eo;
		
	}

}
