package com.epam.preproduction.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.epam.preproduction.components.CompareBlock;
import com.epam.preproduction.components.FiltersBlock;
import com.epam.preproduction.components.MainBlock;
import com.epam.preproduction.components.NavigationLine;
import com.epam.preproduction.components.SortingLine;

public class CataloguePage extends Page {

	// ==== COMPONENTS ==== //

	private SortingLine sortLineBlock;
	private MainBlock mainBlock;
	private FiltersBlock filterBlock;
	private NavigationLine navigationBlock;
	private CompareBlock compareBlock;

	public CataloguePage(WebDriver driver) {
		super(driver);
		initiateComponents(driver);
	}

	private void initiateComponents(WebDriver driver) {
		this.sortLineBlock = PageFactory
				.initElements(driver, SortingLine.class);
		this.mainBlock = PageFactory.initElements(driver, MainBlock.class);
		this.navigationBlock = PageFactory.initElements(driver,
				NavigationLine.class);
		this.compareBlock = PageFactory
				.initElements(driver, CompareBlock.class);
		this.filterBlock = PageFactory.initElements(driver, FiltersBlock.class);
	}

	// ==== GETTERS ==== //

	public SortingLine getSortLineBlock() {
		return sortLineBlock;
	}

	public MainBlock getMainBlock() {
		return mainBlock;
	}

	public FiltersBlock getFilterBlock() {
		return filterBlock;
	}

	public NavigationLine getNavigationBlock() {
		return navigationBlock;
	}

	public CompareBlock getCompareBlock() {
		return compareBlock;
	}

	// ==== GETTING PRODUCT TYPE, CATEGORY ==== //

	public void selectItemCategory(String categoryName) {
		driver.findElement(By.linkText(categoryName)).click();
	}

	public void clickFilterParameter(String searchParameter) {
		Reporter.log("<b><h2>" + "Taking filter parameter from excel"+"</b></h2>");
		getFilterBlock().getFunction(searchParameter);

	}

}
