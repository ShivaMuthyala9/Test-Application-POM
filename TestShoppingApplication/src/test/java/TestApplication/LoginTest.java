package TestApplication;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestApplication.AbstractComponents.DriverManager;
import TestApplication.PageObjects.HomePage;
import TestApplication.PageObjects.LoginPage;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.initializeDriver();
        loginPage = new LoginPage(driver);
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            DriverManager.quitDriver(driver);
        }
    }

    @Test(description = "Verify user can login with valid credentials")
    public void testUserLogin() {
        homePage = loginPage.loginToApplication();
        homePage.waitForPageLoad("dashboard");
        Assert.assertEquals(homePage.homePageURL, homePage.getPageURL(), "User Login failed");
        System.out.println("User login successful");
    }
}
