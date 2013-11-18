package com.epam.preproduction.helpers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.epam.preproduction.components.CompareBlock;
import com.epam.preproduction.entities.Item;
import com.epam.preproduction.entities.Microwave;
import com.epam.preproduction.pages.CataloguePage;
import com.epam.preproduction.pages.ComparePage;
import com.epam.preproduction.pages.ItemPage;

public class CompareItemsTestHelper {

	private static CataloguePage cataloguePage;
	private static ItemPage itemPage;
	private static ComparePage comparePage;

	public static ComparePage getComparePage() {
		return comparePage;
	}

	public static ItemPage getItemPage() {
		return itemPage;
	}

	public void setPages(CataloguePage cataloguePage, ItemPage itemPage,
			ComparePage comparePage) {
		CompareItemsTestHelper.cataloguePage = cataloguePage;
		CompareItemsTestHelper.itemPage = itemPage;
		CompareItemsTestHelper.comparePage = comparePage;
	}

	public void checkParameters(ComparePage comparePage, ItemPage itemPage) {
		Reporter.log("Started to check items parameters <br>");
		cataloguePage.getCompareBlock().getFirstCompareItem().click();
		Reporter.log("Adding the first item to compare <br>");
		cataloguePage.getCompareBlock().getCompareItemsLink().click();
		Reporter.log("Adding the second item to compare <br>");

		Item firstItem = grabAllCharacteristics();
		Reporter.log("Grabbing all characteristics for first item <br>");
		cataloguePage.goBack();

		cataloguePage.getCompareBlock().getSecondCompareItem().click();
		cataloguePage.getCompareBlock().getCompareItemsLink().click();

		Item secondItem = grabAllCharacteristics();
		Reporter.log("Grabbing all characteristics for second item <br>");
		cataloguePage.getCompareBlock().getCompareGoods().click();

		Set<String> paramsNames = grabAllParamNames();
		Set<String> names1 = firstItem.getCharacteristics().keySet();
		Set<String> names2 = secondItem.getCharacteristics().keySet();

		if (!paramsNames.containsAll(names1)) {
			Assert.fail();
		}
		if (!paramsNames.containsAll(names2)) {
			Assert.fail();
		}

		WebElement table = cataloguePage.getCompareBlock().getClassCompare();

		Reporter.log("Comparing characteristics of chosen items");
		List<WebElement> differentItems = table.findElements(By.className(CompareBlock.DIFFERENT));
		Reporter.log("Getting list of different characteristics <br>");
		for (WebElement item : differentItems) {
			List<WebElement> tds = item.findElements(By.tagName(CompareBlock.TD_COMPARE));
			for (WebElement td : tds) {
				if (!td.getCssValue(CompareBlock.BACKGROUND_COLOR).equalsIgnoreCase(CompareBlock.BG_VALUE)) {
					Assert.fail();
					Reporter.log("The characteristics of the items chosen to compare don't match <br>");
				}
			}

		}
	}

	public Set<String> grabAllParamNames() {
		Set<String> characteristicsNames = new HashSet<String>();
		List<WebElement> comparePageCharacteristics = cataloguePage.getCompareBlock().getTableClassCompare();
		for (WebElement element : comparePageCharacteristics) {
			String characteristicName = cataloguePage.getCompareBlock().getTdForCompare().getText();
			characteristicsNames.add(characteristicName);
		}

		comparePageCharacteristics = cataloguePage.getCompareBlock()
				.getTableClassDifferent();
		for (WebElement element : comparePageCharacteristics) {
			String characteristicName = cataloguePage.getCompareBlock().getTdForCompare().getText();
			characteristicsNames.add(characteristicName);
		}
		return characteristicsNames;
	}

	public Item grabAllCharacteristics() {
		Item item = new Microwave();
		Map<String, String> itemMap = new HashMap<String, String>();

		List<WebElement> listOfCharacteristics = cataloguePage.getCompareBlock().getCharacteristicValueList();
		for (WebElement element : listOfCharacteristics) {
			String charateristicName = cataloguePage.getCompareBlock().getCharacteristicType().getText();
			String charateristicValue =  cataloguePage.getCompareBlock().getCharacteristicValue().getText();
			itemMap.put(charateristicName, charateristicValue);
		}
		item.setCharacteristics(itemMap);
		return item;
	}

	public String getCurrentLinks() {
		return itemPage.getDriver().getCurrentUrl();
	}

}
