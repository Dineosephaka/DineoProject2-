package com.dineo.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dineo.automation.pages.AccountPage;
import com.dineo.automation.pages.CartPage;
import com.dineo.automation.pages.CheckoutPage;
import com.dineo.automation.pages.HomePage;
import com.dineo.automation.pages.LoginPage;
import com.dineo.automation.pages.ProductDetailsPage;
import com.dineo.automation.pages.ProductsPage;
import com.dineo.automation.pages.SignupPage;
import com.dineo.automation.utils.TestData;

public class EndToEndTest extends BaseTest {

    @Test
    public void completeECommerceWorkflow() {

        // =========================================================
        // 1. REGISTER CUSTOMER ONCE
        // =========================================================

        HomePage homePage = new HomePage(driver);

        LoginPage loginPage = homePage.clickSignupLogin();

        String email = TestData.generateUniqueEmail();
        String password = TestData.getPassword();

        loginPage.enterSignupDetails(
                TestData.getFirstName() + " " + TestData.getLastName(),
                email
        );

        SignupPage signupPage = loginPage.startSignup();

        signupPage.selectMr();
        signupPage.enterPassword(password);

        signupPage.enterDateOfBirth(
                "10",
                "May",
                "2000"
        );

        signupPage.subscribeToNewsletter();

        signupPage.enterPersonalDetails(
                TestData.getFirstName(),
                TestData.getLastName(),
                TestData.getCompany(),
                TestData.getAddress()
        );

        signupPage.enterLocationDetails(
                TestData.getState(),
                TestData.getCity(),
                TestData.getZipCode(),
                TestData.getMobileNumber()
        );

        AccountPage accountPage = signupPage.createAccount();

        Assert.assertTrue(
                accountPage.isAccountCreated(),
                "Account was not created."
        );

        System.out.println("Scenario 1 - Account created successfully.");

        accountPage.clickContinue();

        homePage = new HomePage(driver);

        Assert.assertTrue(
                homePage.isUserLoggedIn(),
                "User should be logged in after registration."
        );


        // =========================================================
        // 2. VALID LOGIN
        // =========================================================

        homePage.logout();

        homePage = new HomePage(driver);

        loginPage = homePage.clickSignupLogin();

        loginPage.login(email, password);

        Assert.assertTrue(
                loginPage.isLoggedIn(),
                "Valid login failed."
        );

        System.out.println("Scenario 2 - Valid login passed.");


        // =========================================================
        // 3. INVALID LOGIN
        // =========================================================

        homePage = new HomePage(driver);

        homePage.logout();

        homePage = new HomePage(driver);

        loginPage = homePage.clickSignupLogin();

        loginPage.login(
                "invalid@test.com",
                "WrongPassword123"
        );

        Assert.assertTrue(
                loginPage.isLoginErrorDisplayed(),
                "Invalid login error was not displayed."
        );

        System.out.println("Scenario 2 - Invalid login passed.");


        // =========================================================
        // 4. LOGIN AGAIN WITH THE SAME VALID ACCOUNT
        // =========================================================

        loginPage.login(email, password);

        Assert.assertTrue(
                loginPage.isLoggedIn(),
                "User could not log in again with valid credentials."
        );

        System.out.println("Logged in again with the original account.");


        // =========================================================
        // 5. SEARCH BLUE TOP
        // =========================================================

        homePage = new HomePage(driver);

        ProductsPage productsPage = homePage.clickProducts();

        productsPage.searchProduct("Blue Top");

        Assert.assertTrue(
                productsPage.isProductDisplayed("Blue Top"),
                "Blue Top was not displayed."
        );

        ProductDetailsPage blueTop =
                productsPage.clickProduct("Blue Top");

        Assert.assertEquals(
                blueTop.getProductName(),
                "Blue Top",
                "Incorrect product name."
        );

        Assert.assertEquals(
                blueTop.getProductPrice(),
                "Rs. 500",
                "Incorrect product price."
        );

        System.out.println("Scenario 3 - Blue Top verified.");


        // =========================================================
        // 6. ADD BLUE TOP
        // =========================================================

        blueTop.addToCart();

        System.out.println("Blue Top added to cart.");


        // =========================================================
        // 7. ADD MEN TSHIRT
        // =========================================================

        driver.get("https://automationexercise.com/products");

        productsPage = new ProductsPage(driver);

        ProductDetailsPage menTshirt =
                productsPage.clickProduct("Men Tshirt");

        menTshirt.addToCart();

        System.out.println("Men Tshirt added to cart.");


        // =========================================================
        // 8. ADD LONG MAXI TULLE
        // =========================================================

        driver.get("https://automationexercise.com/products");

        productsPage = new ProductsPage(driver);

        ProductDetailsPage longMaxiTulle =
                productsPage.clickProduct(
                        "Long Maxi Tulle Fancy Dress Up Outfits -Pink"
                );

        longMaxiTulle.addToCart();

        System.out.println("Long Maxi Tulle added to cart.");


        // =========================================================
        // 9. NAVIGATE TO CART
        // =========================================================

        driver.get("https://automationexercise.com/view_cart");

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(
                cartPage.isCartDisplayed(),
                "Shopping cart was not displayed."
        );

        Assert.assertTrue(
                cartPage.isProductInCart("Blue Top"),
                "Blue Top should be in cart."
        );

        Assert.assertTrue(
                cartPage.isProductInCart("Men Tshirt"),
                "Men Tshirt should be in cart."
        );

        Assert.assertTrue(
                cartPage.isProductInCart(
                        "Long Maxi Tulle Fancy Dress Up Outfits -Pink"
                ),
                "Long Maxi Tulle should be in cart."
        );

        System.out.println("All three products verified in cart.");


        // =========================================================
        // 10. REMOVE MEN TSHIRT
        // =========================================================

        cartPage.removeProduct("Men Tshirt");

        Assert.assertTrue(
                cartPage.isProductRemoved("Men Tshirt"),
                "Men Tshirt was not removed."
        );

        Assert.assertTrue(
                cartPage.isProductInCart("Blue Top"),
                "Blue Top should remain in cart."
        );

        Assert.assertTrue(
                cartPage.isProductInCart(
                        "Long Maxi Tulle Fancy Dress Up Outfits -Pink"
                ),
                "Long Maxi Tulle should remain in cart."
        );

        System.out.println("Men Tshirt removed successfully.");


        // =========================================================
        // SCENARIO 5 — CHECKOUT
        // =========================================================

        // Press Proceed To Checkout
        cartPage.proceedToCheckout();

        CheckoutPage checkoutPage =
                new CheckoutPage(driver);

        // 3. Verify checkout page
        Assert.assertTrue(
                checkoutPage.isCheckoutDisplayed(),
                "Checkout page was not displayed."
        );

        // 4. Verify address
        Assert.assertTrue(
                checkoutPage.isDeliveryAddressDisplayed(),
                "Delivery address was not displayed."
        );

        Assert.assertTrue(
                checkoutPage.isBillingAddressDisplayed(),
                "Billing address was not displayed."
        );

        // 5. Verify order details
        Assert.assertTrue(
                checkoutPage.isOrderDetailsDisplayed(),
                "Order details were not displayed."
        );

        System.out.println("Checkout address and order details verified.");


        // 6. Enter payment information
        checkoutPage.enterOrderComment(
                "Automated test order."
        );

        // 7. Place order
        checkoutPage.placeOrder();

        checkoutPage.enterPaymentDetails(
                "Dineo Tester",
                "4111111111111111",
                "123",
                "12",
                "2030"
        );

        checkoutPage.payAndConfirmOrder();

        // 8. Verify order confirmation
        Assert.assertTrue(
                checkoutPage.isOrderConfirmed(),
                "Order confirmation was not displayed."
        );

        System.out.println(
                "Scenario 5 - Checkout completed successfully."
        );


        // =========================================================
        // SCENARIO 6 — DELETE ACCOUNT
        // =========================================================

        homePage = new HomePage(driver);

        AccountPage deletedAccountPage =
                homePage.deleteAccount();

        // Verify account deleted
        Assert.assertTrue(
                deletedAccountPage.isAccountDeleted(),
                "Account was not deleted."
        );

        System.out.println(
                "Scenario 6 - Account deleted successfully."
        );

        // Return to home page
        deletedAccountPage.clickContinue();

        homePage = new HomePage(driver);

        Assert.assertTrue(
                homePage.isHomePageDisplayed(),
                "User did not return to the home page."
        );

        System.out.println(
                "Scenario 6 - Returned to home page successfully."
        );

        System.out.println(
                "========== COMPLETE END-TO-END WORKFLOW PASSED =========="
        );
    }
}