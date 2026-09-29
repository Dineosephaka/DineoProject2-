package com.dineo.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dineo.automation.pages.HomePage;
import com.dineo.automation.pages.ProductDetailsPage;
import com.dineo.automation.pages.ProductsPage;

public class ProductTest extends BaseTest {

    @Test
    public void verifyProductSearchAndDetails() {

        // Navigate to Products
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickProducts();

        // Verify Products page is displayed
        Assert.assertTrue(
                productsPage.isProductsPageDisplayed(),
                "Products page was not displayed."
        );

        // Search for Blue Top
        productsPage.searchProduct("Blue Top");

        // Verify Blue Top is displayed in search results
        Assert.assertTrue(
                productsPage.isProductDisplayed("Blue Top"),
                "Blue Top was not displayed in the search results."
        );

        // Open Blue Top
        ProductDetailsPage productDetailsPage =
                productsPage.clickProduct("Blue Top");

        // Verify product name
        Assert.assertEquals(
                productDetailsPage.getProductName(),
                "Blue Top",
                "Product name was incorrect."
        );

        // Verify product price
        Assert.assertEquals(
                productDetailsPage.getProductPrice(),
                "Rs. 500",
                "Product price was incorrect."
        );

        System.out.println(
                "Product search and details verification passed."
        );
    }
}