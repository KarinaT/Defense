package com.epam.preproduction.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.epam.preproduction.entities.Item;
import com.epam.preproduction.entities.Microwave;

public class ItemPage extends Page {
	WebDriver driver;

	public ItemPage(WebDriver driver) {
		super(driver);

	}

	public Item grabAllCharacteristics() {
		Item item = new Microwave();
		Map<String, String> itemMap = new HashMap<String, String>();

		List<WebElement> listOfCharacteristics = getDriver().findElements(
				By.className("row"));
		for (WebElement element : listOfCharacteristics) {
			String charateristicName = element.findElement(By.className("pr"))
					.getText();
			String charateristicValue = element
					.findElement(By.className("val")).getText();
			itemMap.put(charateristicName, charateristicValue);
		}
		item.setCharacteristics(itemMap);
		return item;
	}

	public String getCurrentLinks() {
		return getDriver().getCurrentUrl();
	}
}