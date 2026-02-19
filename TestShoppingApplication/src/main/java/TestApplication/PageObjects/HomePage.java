package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String homePageURL = "https://rahulshettyacademy.com/client/#/dashboard/dash";

    public void addToCartByProductName(String productName) {
        click(By.xpath(
                "//div[@class='card-body']/h5[normalize-space()='" + productName + "']/following-sibling::button[2]"));
        waitForSpinnerToDisappear();
    }

    public void viewProductByVisibleText(String productName) {
        click(By.xpath(
                "//div[@class='card-body']/h5[normalize-space()='" + productName + "']/following-sibling::button[1]"));
    }

    public void setPriceFilter(int minPrice, int maxPrice) {
        type(By.xpath("(//input[@name=\"minPrice\"])[2]"), String.valueOf(minPrice));
        type(By.xpath("(//input[@name=\"maxPrice\"])[2]"), String.valueOf(maxPrice) + Keys.ENTER);
    }

    public String[] getPriceFilterValues() {
        String minPrice = getAttribute(
                By.xpath("(//input[@name=\"minPrice\"])[2]"), "value");

        String maxPrice = getAttribute(
                By.xpath("(//input[@name=\"maxPrice\"])[2]"), "value");

        return new String[] { minPrice, maxPrice };
    }
}
