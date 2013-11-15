package com.epam.preproduction.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.epam.preproduction.components.NavigationLine;
import com.epam.preproduction.entities.BreadMaker;
import com.epam.preproduction.entities.Item;
import com.epam.preproduction.pages.CataloguePage;

public class SortingTestHelper {

	private static CataloguePage cataloguePage;

	public static void setCataloguePage(CataloguePage cataloguePage) {
		SortingTestHelper.cataloguePage = cataloguePage;
	}

	public void verifySortingItemsByPrices() {
		cataloguePage.getSortLineBlock().sortByPrice();

		List<Item> data = new ArrayList<Item>();
		int pageCount = 0;
		while (true && pageCount++ < 3) {
			data.addAll(grabItems());
			if (hasNext()) {
				next();
			} else {
				break;
			}
		}
		System.out.println(data);

		int prevPrice = 0;
		for (Item refrigirator : data) {
			if (prevPrice > refrigirator.getPrice()) {
				Assert.fail();
			}
			prevPrice = refrigirator.getPrice();
		}
	}

	public void verifySortingItemsByNames() {
		cataloguePage.getSortLineBlock().sortByName();
		List<Item> data = new ArrayList<Item>();
		int pageCount = 0;
		while (true && pageCount++ < 3) {
			data.addAll(grabItems());
			if (hasNext()) {
				next();
			} else {
				break;
			}
		}
		System.out.println(data);
		List<Item> namesNew = new ArrayList<Item>(data);
		Collections.sort(namesNew);
		Assert.assertEquals(data, namesNew);

	}

	public List<Item> grabItems() {
		List<Item> result = new ArrayList<Item>();
		List<WebElement> items = cataloguePage.getMainBlock().getDivClassItem();

		for (WebElement item : items) {
			BreadMaker breadMaker = new BreadMaker();
			String name = cataloguePage.getMainBlock().getDivClassName()
					.getText();
			int price = extratNumbers(cataloguePage.getMainBlock()
					.getPriceStrong().getText());

			String description = item.findElement(
					By.xpath(cataloguePage.getMainBlock().CLASS_DESCRIPTION))
					.getText();
			breadMaker.setName(name);
			breadMaker.setPrice(price);
			breadMaker.setDescription(description);
			result.add(breadMaker);
		}
		return result;
	}

	private Integer extratNumbers(String price) {
		Integer pr = Integer.valueOf(price.substring(0, price.length() - 4)
				.replace(" ", ""));
		return pr;
	}

	private boolean hasNext() {
		WebElement element = cataloguePage.getMainBlock().getNextPage();
		if (!CollectionUtils.isEmpty(element.findElements(By
				.className(NavigationLine.ACTIVE_LINK)))) {
			return true;
		}
		return false;
	}

	private void next() {

		WebElement element = cataloguePage.getMainBlock().getNextPage();
		if (!CollectionUtils.isEmpty(element.findElements(By
				.className(NavigationLine.ACTIVE_LINK)))) {
			element.findElement(By.className(NavigationLine.ACTIVE_LINK))
					.click();
		}
	}

}
