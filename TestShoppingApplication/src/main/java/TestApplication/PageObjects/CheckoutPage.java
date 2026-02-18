package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        waitForInvisibility(By.cssSelector(".ta-backdrop"));
        click(By.xpath("//button[normalize-space()='" + ConfigProperties.COUNTRY + "']"));
    }

    public OrderSuccessPage placeOrder() {

        waitForAngularOverlays();
        WebElement placeOrderBtn = waitForClickable(
                By.xpath("//a[contains(@class,'action__submit')]"));

        actions.moveToElement(placeOrderBtn).perform();
        placeOrderBtn.click();

        return new OrderSuccessPage(driver);
    }

}
