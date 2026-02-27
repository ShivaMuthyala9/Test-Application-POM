package TestApplication;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestApplication.AbstractComponents.DriverManager;
import TestApplication.PageObjects.CartPage;
import TestApplication.PageObjects.CheckoutPage;
import TestApplication.PageObjects.HomePage;
import TestApplication.PageObjects.LoginPage;
import TestApplication.PageObjects.OrderSuccessPage;

public class CheckoutTest {
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

    @Test(description = "Verify user can complete checkout and place order")
    public void testCompleteCheckoutFlow() {
        homePage = loginPage.loginToApplication();

        String productName = "iphone 13 pro";
        homePage.addToCartByProductName(productName);

        CartPage cartPage = homePage.CartMenu();

        CheckoutPage checkoutPage = cartPage.checkOut();
        checkoutPage.fillCardDetails();
        checkoutPage.fillShippingDetails();
        checkoutPage.waitForAngularOverlays();

        OrderSuccessPage orderSuccessPage = checkoutPage.placeOrder();
        Assert.assertNotNull(orderSuccessPage, "Order success page should load after placing order");
        System.out.println("Order placed successfully");
    }
}
