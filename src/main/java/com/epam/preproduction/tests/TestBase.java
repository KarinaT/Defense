package com.epam.preproduction.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;

import com.epam.preproduction.configuration.PropertyReader;
import com.epam.preproduction.helpers.core.WebDriverFactory;
import com.epam.preproduction.pages.MainPage;

public class TestBase {

	protected static WebDriver driver;

	public static WebDriver getDriver() {
		return driver;
	}

	protected String baseUrl;

	@BeforeClass
	public void setUp() throws Exception {
		System.out.println("==start==>setUp");

		// for Jenkins ====== >
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName(System.getProperty("webdriver.browser", "firefox"));
		System.out.println(System.getProperty("webdriver.browser"));
		driver = WebDriverFactory.getDriver(caps);

		// driver = WebDriverFactory.getDriver(DesiredCapabilities.firefox());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("===end===>setUp");

	}

	protected MainPage goToMainPage() throws Exception {
		driver.manage().window().maximize();
		driver.get(PropertyReader.getMainPageUrl() + "/");
		MainPage mainPage = new MainPage(driver);
		return mainPage;
	}
}
