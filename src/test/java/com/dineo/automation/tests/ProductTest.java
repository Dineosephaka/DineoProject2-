package com.dineo.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dineo.automation.pages.HomePage;
import com.dineo.automation.pages.ProductsPage;

public class ProductTest extends BaseTest {

    @Test
    public void verifyProductsPageIsDisplayed() {

        // Open the Products page
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickProducts();

        // Verify the Products page is displayed
        Assert.assertTrue(
                productsPage.isProductsPageDisplayed(),
                "Products page was not displayed."
        );
    }
}