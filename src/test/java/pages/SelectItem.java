package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SelectItem extends BasePage {

	By languageLocator = By.cssSelector("span.gh-eb-Geo-txt");
    By englishSelect = By.xpath("//span[contains(.,'English')]");
    
    By searchBox = By.xpath("//input[@id='gh-ac']");
    By searchButton = By.id("gh-btn");

    By priceLabel = By.xpath("//h3[contains(.,'Price')]");
    By priceMin	= By.xpath("//div[3]/div/div/div/input");
	By priceMax	= By.xpath("//div[2]/div/input");
	By priceButton = By.xpath("//button[@class='btn--states x-refine__block-button  icon-btn icon-btn--no-text icon-btn--secondary']");//button[@class='btn--states x-refine__block-button  icon-btn icon-btn--no-text icon-btn--secondary']
	By filerLocation;
	By optionLocation;

    By conditionItems = By.xpath("//span[@class='SECONDARY_INFO']");
    By priceItems = By.xpath("//span[@class='s-item__price']");

	private String minPrice = "200000";
	private String maxPrice = "600000";

    
	public SelectItem(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void setToEnglish() throws InterruptedException {
			findElement(languageLocator);

		if (!getText(languageLocator).equals("English")) {
			click(languageLocator);
			click(englishSelect);
		} else {  ; 	}
		Thread.sleep(8000);
 	    Assert.assertEquals(getText(languageLocator),"English");
 	   
	}
	
	public void searchItem(String item) throws InterruptedException {
    //	if(isVisible(searchBox)) {
			wait(searchBox);
			click(searchBox);
			type(item, searchBox);
			click(searchButton);
	/*	} else  		{
			System.out.println("the Search Locator is not visible yet");
		} */
    	Thread.sleep(2000);
	}

	public void filterByPrice() throws InterruptedException {
		click(priceLabel);
		type(minPrice, priceMin );
		type(maxPrice, priceMax );
		click(priceButton);
		Thread.sleep(3000);
	}

	public void filterByDecision(String filter, String option) throws InterruptedException {

		filerLocation = By.xpath(String.format("//h3[starts-with(.,'%s')]",filter));  ////h3[starts-with(.,'Condition')]
		optionLocation = By.xpath(String.format("//input[@aria-label='%s']",option)); ////input[@aria-label='New']

		if(isDisplayed(optionLocation)) {
			click(optionLocation);
		}
		else {
			click(filerLocation);
			click(optionLocation);
		}
		Thread.sleep(2000);
	}

	public void validateFilters()
	{
		List<WebElement> conditionList = findElements(conditionItems);
		List<WebElement> priceList = findElements(priceItems);

		for(int i=1; i<priceList.size(); i++) {
			String condition = conditionList.get(i).getText();
			double price = Double.parseDouble(priceList.get(i).getText().substring(5).replace(",",""));
			validateCondition(condition, i);
			validateRangePrice(price, i);
		}
		System.out.println("Las Condiciones fueron cumplidas en todos los items");
	}

	public void validateCondition(String condition, int i)
	{
		if(!condition.equals("Brand New")) {
			System.out.println("The item in the position "+i+" is not New");
		}
	}
	public void validateRangePrice(double price, int i)
	{
		if(price<Double.parseDouble(minPrice) || price>Double.parseDouble(maxPrice)) {
			System.out.println("The item in the position "+i+" is Out of the Price Range");
		}
	}
}
