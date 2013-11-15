package com.epam.preproduction.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.preproduction.configuration.DataProviderLayer;
import com.epam.preproduction.helpers.CompareItemsTestHelper;
import com.epam.preproduction.helpers.ItemPageHelper;
import com.epam.preproduction.helpers.SortingTestHelper;
import com.epam.preproduction.pages.CataloguePage;
import com.epam.preproduction.pages.ComparePage;
import com.epam.preproduction.pages.ItemPage;
import com.epam.preproduction.pages.MainPage;

public class CompareItemsTest extends TestBase {

	/**
	 * 2. Сравнение товаров. Выбрать 2 первых товара из раздела "микроволновки"
	 * и сравнить их. Написать тест, который проверит функциональность сравнения
	 * (а именно: все параметры из каждого товара показываются на странице
	 * сравнения, а также проверить, что только отличающиеся характеристики
	 * выделены цветом)
	 **/
	CataloguePage cataloguePage;
	CompareItemsTestHelper helper;
	ComparePage comparePage;
	ItemPageHelper itemPageHelper;
	ItemPage itemPage;
	MainPage mainPage;

	@BeforeMethod
	public void configuration() {
		cataloguePage = new CataloguePage(driver);
		comparePage = new ComparePage(driver);
		itemPage = new ItemPage(driver);
		mainPage = new MainPage(driver); 
		helper = new CompareItemsTestHelper();
		itemPageHelper = new ItemPageHelper();
		helper.setPages(cataloguePage, itemPage, comparePage);
	}

	@Test(dataProvider = "readFromExcel", dataProviderClass = DataProviderLayer.class)
	public void testCompareItems(String categoryName, String productType)
			throws Exception {

		goToMainPage();
		mainPage.selectProductType(productType);
		helper.checkParameters(comparePage, itemPage);

	}

}