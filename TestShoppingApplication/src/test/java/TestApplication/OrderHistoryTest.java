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
import TestApplication.PageObjects.ViewOrdersHistoryPage;

public class OrderHistoryTest {
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

    @Test(description = "Verify user can view order history")
    public void testViewOrderHistory() {
        homePage = loginPage.loginToApplication();

        String productName = "iphone 13 pro";
        homePage.addToCartByProductName(productName);

        CartPage cartPage = homePage.CartMenu();

        CheckoutPage checkoutPage = cartPage.checkOut();
        checkoutPage.fillCardDetails();
        checkoutPage.fillShippingDetails();
        checkoutPage.waitForAngularOverlays();
        OrderSuccessPage orderSuccessPage = checkoutPage.placeOrder();

        ViewOrdersHistoryPage ordersPage = orderSuccessPage.viewOrdersHistory();
        Assert.assertNotNull(ordersPage, "Orders history page should load");
        System.out.println("Order history page accessed successfully");
    }

    @Test(description = "Verify orders are displayed in order history")
    public void testOrderHistoryNotEmpty() {
        homePage = loginPage.loginToApplication();

        homePage.addToCartByProductName("iphone 13 pro");
        CartPage cartPage = homePage.CartMenu();
        CheckoutPage checkoutPage = cartPage.checkOut();
        checkoutPage.fillCardDetails();
        checkoutPage.fillShippingDetails();
        checkoutPage.waitForAngularOverlays();
        OrderSuccessPage orderSuccessPage = checkoutPage.placeOrder();

        ViewOrdersHistoryPage ordersPage = orderSuccessPage.viewOrdersHistory();
        ordersPage.waitForPageLoad("myorders");
        java.util.List<String> orders = ordersPage.getOrderIds();

        Assert.assertNotNull(orders, "Orders list should not be null");
        Assert.assertTrue(!orders.isEmpty(), "At least one order should be present");
        System.out.println("Order history contains " + orders.size() + " order(s)");
    }
}
