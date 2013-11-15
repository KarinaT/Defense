package com.epam.preproduction.components;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FiltersBlock {

	private static final String FILTERED_ITEMS_COUNT = "//div[@class='total']/b";
	private static final String PRODUCER_FILTER = "//*[@id='page-content-wrap']/div[3]/div[1]/div[1]/div/div[2]/div[5]/div[2]/a[@class=' active']";
	private static final String SHOW_COMMON_PRODUCER = "//*[@id='page-content-wrap']/div[3]/div[1]/div[1]/div/div[2]/div[5]/div[2]/a[17]";
	private static final String REST_PRODUCER_FILTER = "//*[@id='page-content-wrap']/div[3]/div[1]/div[1]/div/div[2]/div[5]/div[2]/div[3]/a[@class=' active']";
	
	@FindBy(xpath = FILTERED_ITEMS_COUNT)
	WebElement filteredItemsCount;

	@FindBy(xpath = PRODUCER_FILTER)
	List<WebElement> producerFilter;

	@FindBy(xpath = SHOW_COMMON_PRODUCER)
	WebElement showCommonProducer;

	@FindBy(xpath = REST_PRODUCER_FILTER)
	List<WebElement> restProducerFilter;
	

	public String getMinPrice(int minPrice) {
		return "//div[contains(text(),'Минимальная цена')]/../div[2]/a[text()='"
				+ minPrice + "']";
	}

	public String getMaxPrice(int maxPrice) {
		return "//div[contains(text(),'Максимальная цена')]/../div[2]/a[text()='"
				+ maxPrice + "']";
	}

	public String getFunction(String functionName) {
		return "//a[contains(text(),'" + functionName + "')]";

	}

	public WebElement getFilteredItemsCount() {
		return filteredItemsCount;
	}

	public List<WebElement> getProducerFilter() {
		return producerFilter;
	}

	public WebElement getShowCommonProducer() {
		return showCommonProducer;
	}

	public List<WebElement> getRestProducerFilter() {
		return restProducerFilter;
	}

}
