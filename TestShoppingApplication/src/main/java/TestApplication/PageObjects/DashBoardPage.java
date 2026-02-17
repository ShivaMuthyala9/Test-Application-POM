package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class DashBoardPage {
    WebDriver driver;

    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addTOCartByVisibleText(String productName) {
        driver.findElement(By.xpath(
                "//div[@class='card-body']/h5[normalize-space()='" + productName + "']/following-sibling::button[2]"))
                .click();
    }

    public void viewProductByVisibleText(String productName) {
        driver.findElement(By.xpath(
                "//div[@class='card-body']/h5[normalize-space()='" + productName + "']/following-sibling::button[1]"))
                .click();
    }

    public void setPriceFilter(int minPrice, int maxPrice) {
        driver.findElement(By.name("minPrice")).sendKeys(String.valueOf(minPrice));
        driver.findElement(By.name("maxPrice")).sendKeys(String.valueOf(maxPrice), Keys.ENTER);
    }
}
