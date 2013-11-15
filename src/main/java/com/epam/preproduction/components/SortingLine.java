package com.epam.preproduction.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SortingLine extends Components {

	public static final String SORT_PRICE = "//a[contains(@href,'sort=price')]";
	public static final String SORT_NAME = "//div[@class='order']/a[contains(@href,'sort=name')]";

	@FindBy(xpath = SORT_PRICE)
	private WebElement sortPrice;

	@FindBy(xpath = SORT_NAME)
	private WebElement sortName;

	public void sortByPrice() {
		sortPrice.click();
	}

	public void sortByName() {
		sortName.click();
	}

}
