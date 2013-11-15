package com.epam.preproduction.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {
	
	public Page(){
		
	}
	protected WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void refreshLocators() {
		PageFactory.initElements(getDriver(), CataloguePage.class);
	}

	public void goBack() {
		driver.navigate().back();
		refreshLocators();
	}
}
