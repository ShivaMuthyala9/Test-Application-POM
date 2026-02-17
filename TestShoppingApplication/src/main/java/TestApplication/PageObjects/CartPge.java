package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPge extends BasePage {
    WebDriver Driver;

    public CartPge(WebDriver driver) {
        super(driver);
        this.Driver = driver;
    }

    public CheckoutPage checkOut() {
        click(By.xpath("//button[text()='Checkout']"));
        return new CheckoutPage(Driver);
    }

    public HomePage continueShopping() {
        click(By.xpath("//button[text()='Continue Shopping']"));
        return new HomePage(Driver);
    }
}
