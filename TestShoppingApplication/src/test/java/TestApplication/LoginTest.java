package TestApplication;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestApplication.PageObjects.HomePage;
import TestApplication.PageObjects.LoginPage;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void prepare() {
        // baseUrl and driver initialization handled in BaseTest
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Verify user can login with valid credentials")
    public void testUserLogin() {
        homePage = loginPage.loginToApplication();
        homePage.waitForPageLoad("dashboard");
        Assert.assertEquals(homePage.homePageURL, homePage.getPageURL(), "User Login failed");
        System.out.println("User login successful");
    }
}
