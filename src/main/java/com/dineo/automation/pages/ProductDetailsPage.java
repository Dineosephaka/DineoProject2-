package com.dineo.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dineo.automation.base.BasePage;

public class ProductDetailsPage extends BasePage {

    private final By productName =
            By.xpath("//div[contains(@class,'product-information')]//h2");

    private final By productPrice =
            By.xpath("//div[contains(@class,'product-information')]//span/span");

    private final By availability =
            By.xpath("//div[contains(@class,'product-information')]//p[contains(normalize-space(.),'Availability:')]");

    private final By condition =
            By.xpath("//div[contains(@class,'product-information')]//p[contains(normalize-space(.),'Condition:')]");

    private final By brand =
            By.xpath("//div[contains(@class,'product-information')]//p[contains(normalize-space(.),'Brand:')]");

    private final By addToCartButton =
            By.cssSelector("button.btn.btn-default.cart");

    private final By cartModal =
            By.xpath("//div[contains(@class,'modal-content')]");

    private final By continueShoppingButton =
            By.xpath("//button[contains(text(),'Continue Shopping')]");

    private final By viewCartLink =
            By.xpath("//div[contains(@class,'modal-content')]//a[contains(@href,'/view_cart')]");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);

        // Wait for product details page to load
        waitForElement(productName);
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public String getAvailability() {
        return getText(availability);
    }

    public String getCondition() {
        return getText(condition);
    }

    public String getBrand() {
        return getText(brand);
    }

    public void addToCart() {
        click(addToCartButton);
        waitForElement(cartModal);
    }

    public ProductsPage continueShopping() {
    click(continueShoppingButton);

    wait.until(driver ->
            driver.getCurrentUrl().contains("/products")
    );

    return new ProductsPage(driver);
}
    public CartPage viewCart() {
        click(viewCartLink);
        return new CartPage(driver);
    }
}