package TestApplication;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestApplication.PageObjects.CartPage;
import TestApplication.PageObjects.CheckoutPage;
import TestApplication.PageObjects.HomePage;
import TestApplication.PageObjects.LoginPage;
import TestApplication.PageObjects.OrderSuccessPage;
import TestApplication.PageObjects.ViewOrdersHistoryPage;

public class OrderHistoryTest extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void prepare() {
        loginPage = new LoginPage(driver);
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
