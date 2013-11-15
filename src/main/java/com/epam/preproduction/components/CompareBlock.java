package com.epam.preproduction.components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompareBlock {

	// === LOCATORS FOR EXTRACTING ITEM PARAMETERS === //

	private static final String CHARACTERISTIC_VALUE = "//div[@class='val']";
	private static final String CHARACTERISTIC_TYPE = "//div[@class='pr']";
	private static final String CHARACTERISTIC_ROW = "row";

	private static final String FIRST_ITEM = "//div[@class='item'][1]/div[@class='name']/a";
	private static final String SECOND_ITEM = "//div[@class='item'][2]/div[@class='name']/a";
	private static final String COMPARE = "//div[@class='compare-links']/span[1]";

	// === LOCATORS TO COMPARE ITEMS === //

	private static final String FIRST_COMPARE = "//div[@class='item'][1]/div[@class='compare-links']/span[1]";
	private static final String SECOND_COMPARE = "//div[@class='item'][2]/div[@class='compare-links']/span[1]";
	private static final String COMPARE_GOODS = "//div[@class='compare-links']/span[3]/a";
	public final String DIV_CLASS_ITEM_PART_2 = "]/div/a";
	public final String DIV_CLASS_ITEM_PART_1 = "//div[@class='item'][";

	// === LOCATORS FOR PRICE PAGE === //

	private static final String PRODUCT_TO_COMPARE = "//div[@class='item']/div/a";
	private static final String PRICE_PAGE_LINK = ".link>a";
	private static final String EDIT_FIELD = "edit-name-1";
	private static final String SEARCH_FIELD = "edit-submit-1";
	private static final String TD_CLASS_N = "//td[@class='n']/a[1]";
	public static final String HREF = "href";

	// === LOCATORS TO FIND DIFFERENT PARAMETERS === //

	public static final String TD_COMPARE = "td";
	public static final String BG_VALUE = "rgba(255, 255, 225, 1)";
	public static final String BACKGROUND_COLOR = "background-color";
	public static final String DIFFERENT = "different";
	public static final String CLASS_COMPARE = "compare";

	public static final String TD_COMPARE_3 = "//td[3]";
	public static final String TD_COMPARE_2 = "//td[2]";
	public static final String TD_COMPARE_1 = "//td[1]";
	public static final String TABLE_CLASS_DIFFERENT = "//table[@class='compare']/tbody/tr[@class='different']";
	public static final String TABLE_CLASS_COMPARE = "//table[@class='compare']/tbody/tr[@class='']";
	private static final String ITEMS_TO_COMPARE = "//div[@class='item']/div/a";

	// === FIELDS USING LOCATORS === //

	@FindBy(className = CLASS_COMPARE)
	WebElement classCompare;

	@FindBy(xpath = TD_COMPARE_1)
	WebElement tdCompare1;

	@FindBy(xpath = TD_COMPARE_2)
	WebElement tdCompare2;

	@FindBy(xpath = TD_COMPARE_3)
	WebElement tdCompare3;

	@FindBy(xpath = TABLE_CLASS_DIFFERENT)
	List<WebElement> tableClassDifferent;

	@FindBy(xpath = TABLE_CLASS_COMPARE)
	List<WebElement> tableClassCompare;

	@FindBy(xpath = CHARACTERISTIC_VALUE)
	By characteristicValue;

	@FindBy(xpath = CHARACTERISTIC_TYPE)
	By characteristicType;

	@FindBy(className = CHARACTERISTIC_ROW)
	List<WebElement> characteristicRow;

	@FindBy(xpath = TD_CLASS_N)
	List<WebElement> tdPricePage;

	@FindBy(id = EDIT_FIELD)
	WebElement editField;

	@FindBy(id = SEARCH_FIELD)
	WebElement searchField;

	@FindBy(css = PRICE_PAGE_LINK)
	WebElement pricePageLink;

	@FindBy(xpath = ITEMS_TO_COMPARE)
	List<WebElement> itemsToCompare;

	@FindBy(xpath = PRODUCT_TO_COMPARE)
	WebElement productToCompare;

	@FindBy(xpath = FIRST_COMPARE)
	WebElement firstCompareElement;

	@FindBy(xpath = SECOND_COMPARE)
	WebElement secondCompareElement;

	@FindBy(xpath = COMPARE_GOODS)
	WebElement compareGoods;

	@FindBy(xpath = FIRST_ITEM)
	WebElement firstCompareItem;

	@FindBy(xpath = SECOND_ITEM)
	WebElement secondCompareItem;

	@FindBy(xpath = COMPARE)
	WebElement compareItemsLink;
	
	@FindBy(xpath = HREF)
	WebElement href;

	@FindBy(className = CLASS_COMPARE)
	List<WebElement> compareClass;
	
	// === GETTERS === //

	public By getCharacteristicValue() {
		return characteristicValue;
	}

	public By getCharacteristicType() {
		return characteristicType;
	}

	public List<WebElement> getCharacteristicRow() {
		return characteristicRow;
	}

	public WebElement getTdCompare1() {
		return tdCompare1;
	}

	public WebElement getTdCompare2() {
		return tdCompare2;
	}

	public WebElement getTdCompare3() {
		return tdCompare3;
	}

	public List<WebElement> getTableClassDifferent() {
		return tableClassDifferent;
	}

	public List<WebElement> getTableClassCompare() {
		return tableClassCompare;
	}

	public WebElement getFirstCompareItem() {
		return firstCompareItem;
	}

	public WebElement getSecondCompareItem() {
		return secondCompareItem;
	}

	public WebElement getCompareItemsLink() {
		return compareItemsLink;
	}

	public WebElement getFirstCompareElement() {
		return firstCompareElement;
	}

	public WebElement getSecondCompareElement() {
		return secondCompareElement;
	}

	public WebElement getCompareGoods() {
		return compareGoods;
	}

	public List<WebElement> getCompareClass() {
		return compareClass;
	}

	public List<WebElement> getItemsToCompare() {
		return itemsToCompare;
	}

	public WebElement getProductToCompare() {
		return productToCompare;
	}

	public WebElement getPricePageLink() {
		return pricePageLink;
	}

	public WebElement getEditField() {
		return editField;
	}

	public WebElement getSearchField() {
		return searchField;
	}

	public List<WebElement> getTdPricePage() {
		return tdPricePage;
	}

	public WebElement getClassCompare() {
		return classCompare;
	}
	
	public WebElement getHref() {
		return href;
	}

}
