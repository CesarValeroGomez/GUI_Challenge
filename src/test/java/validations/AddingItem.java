package validations;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.AddItem;
import pages.SelectItem;

public class AddingItem {

    private String url = "https://www.ebay.com/";
    private WebDriver driver;

    private SelectItem selectItem;
    private AddItem addItem;

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
    public void addProduct() throws InterruptedException {
        selectItem.searchItem("motorola g8 plus new buy");
        addItem.cartingItem();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After the Method Test Adding Item");
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

}
