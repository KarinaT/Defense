package com.epam.preproduction.helpers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.epam.preproduction.components.NavigationLine;
import com.epam.preproduction.entities.BreadMaker;
import com.epam.preproduction.entities.Item;
import com.epam.preproduction.pages.CataloguePage;

public class FilterParameterTestHelper {

	private static CataloguePage cataloguePage;

	public static void setCataloguePage(CataloguePage cataloguePage) {
		FilterParameterTestHelper.cataloguePage = cataloguePage;
	}

	public void verifyFilterWeightControlParameter(String searchParameter) {

		cataloguePage.clickFilterParameter(searchParameter);
		List<Item> filteredBreadMakersList = new ArrayList<Item>();
		while (true) {
			filteredBreadMakersList.addAll(grabItems());
			if (hasNext()) {
				next();
			} else {
				break;
			}
		}
		System.out.println(filteredBreadMakersList);

		for (Item breadMaker : filteredBreadMakersList) {
			if (!breadMaker.getDescription().contains(searchParameter)) {
				Assert.fail();
			}
		}
	}

	private boolean hasNext() {
		WebElement element = cataloguePage.getNavigationBlock()
				.getNextItemPage();
		cataloguePage.getNavigationBlock();
		if (!CollectionUtils.isEmpty(element.findElements(By
				.className(NavigationLine.ACTIVE_LINK)))) {
			return true;
		}
		return false;
	}

	private void next() {
		WebElement element = cataloguePage.getNavigationBlock()
				.getNextItemPage();
		if (!CollectionUtils.isEmpty(element.findElements(By
				.className("active")))) {
			element.findElement(By.className(NavigationLine.ACTIVE_LINK))
					.click();
		}
	}

	public List<Item> grabItems() {
		List<Item> result = new ArrayList<Item>();
		List<WebElement> items = cataloguePage.getMainBlock().getDivClassItem();

		for (WebElement item : items) {
			BreadMaker breadMaker = new BreadMaker();
			String name = cataloguePage.getMainBlock().getProductNames()
					.getText();
			int price = extratNumbers(cataloguePage.getMainBlock()
					.getPriceStrong().getText());
			String description = cataloguePage.getMainBlock().getDescription()
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

	public void verifyManufacturerInNames() {
		List<Item> allBreadMakersList = new ArrayList<Item>();
		while (true) {
			allBreadMakersList.addAll(grabItems());
			if (hasNext()) {
				next();
			} else {
				break;
			}
		}
		System.out.println(allBreadMakersList);

		List<String> manufacturerNames = getAllManufacturesFromFilter();
		boolean exist = false;
		for (Item breadMaker : allBreadMakersList) {
			for (String manufacturer : manufacturerNames) {
				if (breadMaker.getName().toLowerCase()
						.contains(manufacturer.toLowerCase())) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				Assert.fail();
			} else {
				exist = false;
			}
		}
	}

	private List<String> getAllManufacturesFromFilter() {
		List<String> result = new ArrayList<String>();
		List<WebElement> manufactures = cataloguePage.getFilterBlock()
				.getProducerFilter();
		System.out.println(manufactures);
		for (WebElement manufacture : manufactures) {
			result.add(manufacture.getText());
		}

		cataloguePage.getFilterBlock().getShowCommonProducer().click();

		List<WebElement> hiddenManufactures = cataloguePage.getFilterBlock()
				.getRestProducerFilter();
		System.out.println(hiddenManufactures);
		for (WebElement manufacture : hiddenManufactures) {
			result.add(manufacture.getText());
		}
		return result;

	}

}
