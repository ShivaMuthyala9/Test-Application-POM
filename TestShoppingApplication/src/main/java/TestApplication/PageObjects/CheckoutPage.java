package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestApplication.AbstractComponents.ConfigProperties;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillCardDetails() {
        type(By.xpath("(//input[@class='input txt'])[1]"), ConfigProperties.CVV_CODE);
        type(By.xpath("(//input[@class='input txt'])[2]"), ConfigProperties.NAME_ON_CARD);
    }

    public void fillShippingDetails() {
        type(By.xpath("//input[@placeholder='Select Country']"), ConfigProperties.COUNTRY);
    }

    public OrderSuccessPage placeOrder() {
        click(By.linkText("Place Order"));
        return new OrderSuccessPage(driver);
    }
}
