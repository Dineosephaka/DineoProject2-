
package com.dineo.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dineo.automation.base.BasePage;

public class ProductsPage extends BasePage {

    private final By searchBox =
            By.id("search_product");

    private final By searchButton =
            By.id("submit_search");

    private final By productsTitle =
            By.xpath("//h2[contains(text(),'All Products')]");

    private final By blueTopViewProduct =
            By.xpath(
                    "//div[contains(@class,'product-image-wrapper')]" +
                    "[.//p[normalize-space()='Blue Top']]" +
                    "//a[contains(@href,'/product_details/1')]"
            );

    public ProductsPage(WebDriver driver) {
        super(driver);

        // Wait for the Products page to load
        waitForElement(productsTitle);
    }

    public ProductsPage searchProduct(String productName) {

        type(searchBox, productName);
        click(searchButton);

        return this;
    }

    public boolean isProductsPageDisplayed() {

        return isDisplayed(productsTitle);
    }

    public boolean isProductDisplayed(String productName) {

        By product =
                By.xpath(
                        "//div[@class='productinfo text-center']//p[text()='"
                                + productName
                                + "']"
                );

        return isDisplayed(product);
    }

    public ProductDetailsPage clickBlueTopViewProduct() {

        click(blueTopViewProduct);

        return new ProductDetailsPage(driver);
    }

  public ProductDetailsPage clickProduct(String productName) {

    By viewProduct =
            By.xpath(
                    "//div[contains(@class,'product-image-wrapper')]" +
                    "[.//p[normalize-space()='" + productName + "']]" +
                    "//a[contains(@href,'/product_details/')]"
            );

    // Make sure the product's View Product link is visible
    waitForElement(viewProduct);

    // Click the product
    click(viewProduct);

    // Wait for the product details URL
    wait.until(driver ->
            driver.getCurrentUrl().contains("/product_details/")
    );

    return new ProductDetailsPage(driver);
        }}