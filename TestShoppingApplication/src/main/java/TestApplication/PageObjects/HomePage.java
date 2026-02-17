package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void addTOCartByVisibleText(String productName) {
        click(By.xpath(
                "//div[@class='card-body']/h5[normalize-space()='" + productName + "']/following-sibling::button[2]"));
    }

    public void viewProductByVisibleText(String productName) {
        click(By.xpath(
                "//div[@class='card-body']/h5[normalize-space()='" + productName + "']/following-sibling::button[1]"));
    }

    public void setPriceFilter(int minPrice, int maxPrice) {
        type(By.name("minPrice"), String.valueOf(minPrice));
        type(By.name("maxPrice"), String.valueOf(maxPrice) + Keys.ENTER);
    }
}
