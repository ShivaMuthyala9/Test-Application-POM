package TestApplication.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {
    
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkout;

    @FindBy(xpath = "//button[text()='Continue Shopping']")
    WebElement continueShopping;

    public CheckoutPage checkOut() {
        click(checkout);
        return new CheckoutPage(driver);
    }

    public HomePage continueShopping() {
        click(continueShopping);
        return new HomePage(driver);
    }

}
