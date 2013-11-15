package com.epam.preproduction.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.epam.preproduction.components.FiltersBlock;

public class PricePage extends Page {

	private FiltersBlock filterBlock;

	public FiltersBlock getFilterBlock() {
		return filterBlock;
	}

	public void setFilterBlock(FiltersBlock filterBlock) {
		this.filterBlock = filterBlock;
	}

	public PricePage(WebDriver driver) {
		super(driver);
		initiateComponents(driver);

	}

	private void initiateComponents(WebDriver driver) {

		this.filterBlock = PageFactory.initElements(driver, FiltersBlock.class);
	}

	public FiltersBlock clickPriceFilters(int maxPrice, int minPrice) {
		getDriver().findElement(
				By.xpath(getFilterBlock().getMaxPrice(maxPrice))).click();
		getDriver().findElement(
				By.xpath(getFilterBlock().getMinPrice(minPrice))).click();
		refreshLocators();
		Reporter.log("Refreshing locators");
		return PageFactory.initElements(driver, FiltersBlock.class);
	}

}
