package com.epam.preproduction.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.preproduction.configuration.DataProviderLayer;
import com.epam.preproduction.helpers.FilterParameterTestHelper;
import com.epam.preproduction.pages.CataloguePage;
import com.epam.preproduction.pages.MainPage;

/*
 * 5. Проверить корректность работы фильтра «регулировка веса» у хлебопечек, а
 * именно: проверить, что, выбрав наличие функции "регулировка веса" в фильтре,
 * результаты показываются правильно (показываются все товары, у которых есть
 * эта функция, и не показывается ничего лишнего)
 */
public class CheckFilterParameters extends TestBase {

	CataloguePage cataloguePage;
	MainPage mainPage;
	FilterParameterTestHelper helper;

	@BeforeMethod
	public void configuration() {
		cataloguePage = new CataloguePage(driver);
		mainPage = new MainPage(driver);
		helper = new FilterParameterTestHelper();
		FilterParameterTestHelper.setCataloguePage(cataloguePage);
	}

	@Test(dataProvider = "readFromExcel", dataProviderClass = DataProviderLayer.class)
	public void testFilterParameter(String productType, String searchParameter)
			throws Exception {

		goToMainPage();
		mainPage.selectProductType(productType);
		cataloguePage.clickFilterParameter(searchParameter);

	}

	@Test(dataProvider = "readFromExcel", dataProviderClass = DataProviderLayer.class)
	public void testFilterByProducer(String productType) throws Exception {

		goToMainPage();
		mainPage.selectProductType(productType);
		helper.verifyManufacturerInNames();
	}

}
