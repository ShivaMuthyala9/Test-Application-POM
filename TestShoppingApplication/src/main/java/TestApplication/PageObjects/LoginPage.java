package TestApplication.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestApplication.AbstractComponents.ConfigProperties;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement emailInput;

    @FindBy(id = "userPassword")
    WebElement passwordInput;

    @FindBy(id = "login")
    WebElement loginButton;

    public HomePage loginToApplication() {
        type(emailInput, ConfigProperties.MAIL_ID);
        type(passwordInput, ConfigProperties.PASSWORD);
        click(loginButton);
        return new HomePage(driver);
    }
}
