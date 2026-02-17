package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestApplication.AbstractComponents.ConfigProperties;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillCardDetails() {
        driver.findElement(By.xpath("(//input[@class='input txt'])[1]")).sendKeys(ConfigProperties.CVV_CODE);
        driver.findElement(By.xpath("(//input[@class='input txt'])[2]")).sendKeys(ConfigProperties.NAMEONCARD);
    }

    public void fillShippingDetails() {
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys(ConfigProperties.NAMEONCARD);
    }

    public OrderSuccessPage placeOrder() {
        driver.findElement(By.linkText("Place Order")).click();
        return new OrderSuccessPage(driver);
    }
}
