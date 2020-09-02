package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static org.testng.Assert.*;

public class AddItem extends BasePage {

    By imageLocator = By.xpath("//img[@src='https://i.ebayimg.com/thumbs/images/g/ahkAAOSwmnFd3Xli/s-l225.webp']");
    By itemTitle = By.id("itemTitle");
    By cartButton = By.id("isCartBtn_btn");
    By cartTitle = By.xpath("//div[@class='listsummary-content-itemdetails']//span[@class='BOLD']");



    public AddItem(WebDriver driver) {
        super( driver );
    }

    public void cartingItem() throws InterruptedException {
        Thread.sleep(2000);
        findElement(imageLocator);
        click(imageLocator);
        findElement(itemTitle);
        String itemName = getText(itemTitle);
        findElement(cartButton);
        click(cartButton);
        findElement(cartTitle);
        Assert.assertEquals(getText(cartTitle), itemName);
        System.out.println("the Description is the same");
    }
}
