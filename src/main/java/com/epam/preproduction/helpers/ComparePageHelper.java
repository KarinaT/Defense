package com.epam.preproduction.helpers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.epam.preproduction.entities.Item;
import com.epam.preproduction.entities.Microwave;
import com.epam.preproduction.pages.CataloguePage;

public class ComparePageHelper {

	private static CataloguePage cataloguePage;

	public static void setCataloguePage(CataloguePage cataloguePage) {
		ComparePageHelper.cataloguePage = cataloguePage;
	}

	public List<Item> grabAllParams() {
		List<Item> microwaves = new ArrayList<Item>();
		Item item1 = new Microwave();
		Item item2 = new Microwave();
		microwaves.add(item1);
		microwaves.add(item2);

		List<WebElement> comparePageCharacteristics = cataloguePage
				.getCompareBlock().getTableClassCompare();
		for (WebElement element : comparePageCharacteristics) {
			String characteristicName = cataloguePage.getCompareBlock()
					.getTdCompare1().getText();
			String firstItemValues = cataloguePage.getCompareBlock()
					.getTdCompare2().getText();
			String secondItemValues = cataloguePage.getCompareBlock()
					.getTdCompare3().getText();
			item1.getCharacteristics().put(characteristicName,
					firstItemValues);
			item2.getCharacteristics().put(characteristicName,
					secondItemValues);
		}
		return microwaves;
	}
}
