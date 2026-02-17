package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPge {
    WebDriver Driver;

    public CartPge(WebDriver driver) {
        this.Driver = driver;
    }

    public CheckoutPage checkOut() {
        Driver.findElement(By.xpath("//button[text()='Checkout']")).click();
        return new CheckoutPage(Driver);
    }

    public DashBoardPage continueShopping() {
        Driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
        return new DashBoardPage(Driver);
    }
}
