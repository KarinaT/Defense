package com.epam.preproduction.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class MainPage extends Page {

	public MainPage(WebDriver driver) {
		super(driver);

	}
	public CataloguePage selectProductType(String productType) {
		driver.findElement(By.linkText(productType)).click();
		Reporter.log("Clicking at product type");
		return PageFactory.initElements(driver, CataloguePage.class);
	}

}