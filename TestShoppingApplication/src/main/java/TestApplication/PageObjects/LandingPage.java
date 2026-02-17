package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestApplication.AbstractComponents.ConfigProperties;

public class LandingPage {
    public WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openApplication() {
        driver.get(ConfigProperties.PAGE_URL);
    }

    public DashBoardPage loginToapplication() {
        driver.findElement(By.id("userEmail")).sendKeys(ConfigProperties.MAIL_ID);
        driver.findElement(By.id("userPassword")).sendKeys(ConfigProperties.PASSWORD);
        driver.findElement(By.id("login")).click();
        return new DashBoardPage(driver);
    }
}
