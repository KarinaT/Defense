package com.epam.preproduction.helpers;

import com.epam.preproduction.pages.CataloguePage;
import com.epam.preproduction.pages.ItemPage;

public class ItemPageHelper {

	private static CataloguePage cataloguePage;
	private static ItemPage itemPage;

	public static void setCataloguePage(CataloguePage cataloguePage,
			ItemPage itemPage) {
		ItemPageHelper.cataloguePage = cataloguePage;
		ItemPageHelper.itemPage = itemPage;
	}
}
