package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestApplication.AbstractComponents.ConfigProperties;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//input[@class='input txt'])[1]")
    WebElement cvvInput;

    @FindBy(xpath = "(//input[@class='input txt'])[2]")
    WebElement nameOnCardInput;

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countryInput;

    @FindBy(xpath = "//button[normalize-space()='" + ConfigProperties.COUNTRY + "']")
    WebElement countryOption;

    @FindBy(xpath = "//a[contains(@class,'action__submit')]")
    WebElement placeOrderBtn;

    public void fillCardDetails() {
        type(cvvInput, ConfigProperties.CVV_CODE);
        type(nameOnCardInput, ConfigProperties.NAME_ON_CARD);
    }

    public void fillShippingDetails() {
        type(countryInput, ConfigProperties.COUNTRY);
        waitForInvisibility(By.cssSelector(".ta-backdrop"));
        click(countryOption);
    }

    public OrderSuccessPage placeOrder() {

        waitForAngularOverlays();
        WebElement placeOrder = waitForClickable(this.placeOrderBtn);

        actions.moveToElement(placeOrder).perform();
        placeOrder.click();

        return new OrderSuccessPage(driver);
    }

}
