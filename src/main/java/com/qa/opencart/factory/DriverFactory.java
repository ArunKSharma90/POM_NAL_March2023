package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * @author arunk This method is used to initialize the browser
	 * @return This will return the driver
	 */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser");
		String browserVersion = prop.getProperty("browserversion");
		
		System.out.println("The browser name is: " + prop.getProperty("browser"));
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote code - for grid
				init_remoteDriver("chrome", browserVersion );
			} else {
				// local
				WebDriverManager.chromedriver().setup();
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote code - for grid
				init_remoteDriver("edge", browserVersion);
			} else {
				// local
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote code - for grid
				init_remoteDriver("firefox", browserVersion);
			} else {
				// local
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
		} else {
			System.out.println(" Kindly pass a valid browserName in order to continue...." + prop.getProperty("browser")
			+ " is not valid");
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	private void init_remoteDriver(String browserName, String browserVersion) {
		System.out.println("Running tests on remote grid server: on browser- " + browserName + ":" + browserVersion);
		if(browserName.equalsIgnoreCase("chrome")) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(browserName.equalsIgnoreCase("firefox")) {
			
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(browserName.equalsIgnoreCase("edge")) {
			
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
	/**
	 * getDriver() - this will return a thread Local copy of WebDriver
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	/**
	 * this method is used to initialize the properties
	 * 
	 * @return this is returning the Properties prop reference
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream fis = null;

		String envName = System.getProperty("env"); // qa/dev/stage/uat/prod
		if (envName == null) {
			System.out.println("Running in prod env: ");
			try {
				fis = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		} else {
			System.out.println("Running on env: " + envName);
			switch (envName.toLowerCase()) {
			case "qa":
				try {
					fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				break;
			case "dev":
				try {
					fis = new FileInputStream("./src/test/resources/config/dev.config.properties");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				break;
			case "uat":
				try {
					fis = new FileInputStream("./src/test/resources/config/uat.config.properties");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				break;
			case "stage":
				try {
					fis = new FileInputStream("./src/test/resources/config/stage.config.properties");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				break;
			default:
				try {
					fis = new FileInputStream("./src/test/resources/config/config.properties");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				break;
			}
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * take screenshot
	 */
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
