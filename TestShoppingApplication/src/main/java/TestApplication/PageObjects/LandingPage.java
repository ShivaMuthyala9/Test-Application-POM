package TestApplication.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestApplication.AbstractComponents.ConfigProperties;

public class LandingPage extends BasePage {
    public WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public HomePage loginToapplication() {
        type(By.id("userEmail"), ConfigProperties.MAIL_ID);
        type(By.id("userPassword"), ConfigProperties.PASSWORD);
        click(By.id("login"));
        return new HomePage(driver);
    }
}
