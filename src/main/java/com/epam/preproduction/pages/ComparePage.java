package com.epam.preproduction.pages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ComparePage extends Page {

	public ComparePage(WebDriver driver) {
		super(driver);
	}

	public Set<String> grabAllParamNames() {
		Set<String> characteristicsNames = new HashSet<String>();

		List<WebElement> comparePageCharacteristics = getDriver().findElements(By.xpath("//table[@class='compare']/tbody/tr[@class='']"));
		for (WebElement element : comparePageCharacteristics) {
			String characteristicName = element.findElement(
					By.xpath(".//td[1]")).getText();
			characteristicsNames.add(characteristicName);
		}
		comparePageCharacteristics = getDriver()
				.findElements(
						By.xpath("//table[@class='compare']/tbody/tr[@class='different']"));
		for (WebElement element : comparePageCharacteristics) {
			String characteristicName = element.findElement(
					By.xpath(".//td[1]")).getText();
			characteristicsNames.add(characteristicName);
		}
		return characteristicsNames;
	}
}
