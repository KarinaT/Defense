package com.epam.preproduction.helpers.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.epam.preproduction.configuration.PropertyReader;
import com.opera.core.systems.OperaDriver;

public class WebDriverFactory {

	private static String defaultHub = null;

	private static int restartFrequency = Integer.MAX_VALUE;

	public static void setDefaultHub(String newDefaultHub) {
		defaultHub = newDefaultHub;
	}

	public static void setRestartFrequency(int newRestartFrequency) {
		restartFrequency = newRestartFrequency;
	}

	private static String key = null;
	private static int count = 0;
	private static WebDriver driver;

	public static WebDriver getDriver(String hub, Capabilities capabilities) {
		count++;

		if (driver == null) {
			return newWebDriver(hub, capabilities);
		}

		String newKey = capabilities.toString() + ":" + hub;
		if (!newKey.equals(key)) {
			dismissDriver();
			key = newKey;
			return newWebDriver(hub, capabilities);
		}

		try {
			driver.getCurrentUrl();
		} catch (Throwable t) {
			t.printStackTrace();
			return newWebDriver(hub, capabilities);
		}

		if (count >= restartFrequency) {
			dismissDriver();
			return newWebDriver(hub, capabilities);
		}

		return driver;
	}

	public static WebDriver getDriver(Capabilities capabilities) {
		return getDriver(defaultHub, capabilities);
	}

	public static void dismissDriver() {
		if (driver != null) {
			try {
				driver.quit();
				driver = null;
				key = null;
			} catch (WebDriverException ex) {

			}
		}
	}

	private static WebDriver newWebDriver(String hub, Capabilities capabilities) {
		driver = (hub == null) ? createLocalDriver(capabilities)
				: createRemoteDriver(hub, capabilities);
		key = capabilities.toString() + ":" + hub;
		count = 0;
		return driver;
	}

	private static WebDriver createRemoteDriver(String hub,
			Capabilities capabilities) {
		try {
			return new RemoteWebDriver(new URL(hub), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new Error("Could not connect to WebDriver hub", e);
		}
	}

	private static WebDriver createLocalDriver(Capabilities capabilities) {
		String browserType = capabilities.getBrowserName();
		if (browserType.equals("firefox"))
			return new FirefoxDriver(capabilities);
		if (browserType.startsWith("internet explorer"))
			return new InternetExplorerDriver(capabilities);
		if (browserType.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", PropertyReader.getChromeBinaryPath());
			return new ChromeDriver(capabilities);
		}
		if (browserType.equals("opera")) {
			System.setProperty("opera.binary", PropertyReader.getOperaBinaryPath());
			return new OperaDriver(capabilities);
		}
		throw new Error("Unrecognized browser type: " + browserType);
	}

	static {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				dismissDriver();
			}
		});
	}
}
