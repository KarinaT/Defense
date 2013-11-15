package com.epam.preproduction.helpers;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.epam.preproduction.components.CompareBlock;
import com.epam.preproduction.entities.Item;
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
		cataloguePage.getCompareBlock().getFirstCompareItem().click();
		cataloguePage.getCompareBlock().getCompareItemsLink().click();

		Item firstItem = itemPage.grabAllCharacteristics();
		System.out.println(firstItem.getCharacteristics());
		cataloguePage.goBack();

		cataloguePage.getCompareBlock().getSecondCompareItem().click();
		cataloguePage.getCompareBlock().getCompareItemsLink().click();

		Item secondItem = itemPage.grabAllCharacteristics();

		System.out.println(secondItem.getCharacteristics());
		cataloguePage.getCompareBlock().getCompareGoods().click();

		Set<String> paramsNames = comparePage.grabAllParamNames();
		Set<String> names1 = firstItem.getCharacteristics().keySet();
		Set<String> names2 = secondItem.getCharacteristics().keySet();

		System.out.println(paramsNames);
		System.out.println(names1);
		System.out.println(names2);
		if (!paramsNames.containsAll(names1)) {
			Assert.fail();
		}
		if (!paramsNames.containsAll(names2)) {
			Assert.fail();
		}

		WebElement table = cataloguePage.getCompareBlock().getClassCompare();

		List<WebElement> differentItems = table.findElements(By
				.className(CompareBlock.DIFFERENT));
		for (WebElement item : differentItems) {
			List<WebElement> tds = item.findElements(By
					.tagName(CompareBlock.TD_COMPARE));
			for (WebElement td : tds) {
				if (!td.getCssValue(CompareBlock.BACKGROUND_COLOR)
						.equalsIgnoreCase(CompareBlock.BG_VALUE)) {
					Assert.fail();
				}
			}

		}
	}

}
