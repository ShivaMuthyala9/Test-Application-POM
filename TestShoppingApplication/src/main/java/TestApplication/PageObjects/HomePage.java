package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String homePageURL = "https://rahulshettyacademy.com/client/#/dashboard/dash";
    @FindBy(xpath = "(//input[@name=\"minPrice\"])[2]")
    WebElement minPriceInput;
    @FindBy(xpath = "(//input[@name=\"maxPrice\"])[2]")
    WebElement maxPriceInput;

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
        type(minPriceInput, String.valueOf(minPrice));
        type(maxPriceInput, String.valueOf(maxPrice) + Keys.ENTER);
    }

    public String[] getPriceFilterValues() {
        String minPrice = getAttribute(minPriceInput, "value");

        String maxPrice = getAttribute(maxPriceInput, "value");

        return new String[] { minPrice, maxPrice };
    }
}
