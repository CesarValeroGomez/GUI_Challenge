package validations;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.SelectItem;


public class SearchProduct {

    private String url = "https://www.ebay.com/";
    private WebDriver driver;
    
    private SelectItem selectItem; 

    
    @BeforeClass
    public void beforeClass() {
        selectItem = new SelectItem(driver);
        driver = selectItem.chromeDriverConnection();
        selectItem.visit(url);
    }
    
    @BeforeMethod
    public void setEnglish() throws InterruptedException {
    	selectItem.setToEnglish();    	
    }
    
    @Test
    public void searchingProduct() throws InterruptedException {
        selectItem.searchItem("PS4");
        selectItem.filterByPrice();
        selectItem.filterByDecision("Condition","New");
        selectItem.validateFilters();
    }
    
    @AfterMethod
    public void afterMethod() {
    	System.out.println("After the Method Test Search Producto");
    }
    
    @AfterClass
    public void afterClass() {
        driver.close();
    } 
    
}
