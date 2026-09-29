package com.dineo.automation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dineo.automation.base.BasePage;

public class CartPage extends BasePage {

    private final By cartPageTitle =
            By.xpath("//section[@id='cart_items']//h2[contains(text(),'Shopping Cart')]");

    private final By cartTable =
            By.id("cart_info_table");

    private final By proceedToCheckoutButton =
            By.xpath("//a[contains(text(),'Proceed To Checkout')]");

    public CartPage(WebDriver driver) {
        super(driver);

        waitForElement(cartTable);
    }

    public boolean isCartDisplayed() {
        return isDisplayed(cartPageTitle)
                || isDisplayed(cartTable);
    }

    public boolean isProductInCart(String productName) {

        By productLocator = By.xpath(
                "//tr[contains(@id,'product-')]" +
                "[.//td[contains(@class,'cart_description')]" +
                "//h4/a[contains(normalize-space(),'" +
                productName +
                "')]]"
        );

        try {
            return waitForElement(productLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getProductQuantity(String productName) {

        By quantityLocator = By.xpath(
                "//tr[contains(@id,'product-')]" +
                "[.//td[contains(@class,'cart_description')]" +
                "//h4/a[contains(normalize-space(),'" +
                productName +
                "')]]" +
                "//td[contains(@class,'cart_quantity')]//button"
        );

        return getText(quantityLocator);
    }

    public String getProductPrice(String productName) {

        By priceLocator = By.xpath(
                "//tr[contains(@id,'product-')]" +
                "[.//td[contains(@class,'cart_description')]" +
                "//h4/a[contains(normalize-space(),'" +
                productName +
                "')]]" +
                "//td[contains(@class,'cart_price')]//p"
        );

        return getText(priceLocator);
    }

    public void removeProduct(String productName) {

        By removeButton = By.xpath(
                "//tr[contains(@id,'product-')]" +
                "[.//td[contains(@class,'cart_description')]" +
                "//h4/a[contains(normalize-space(),'" +
                productName +
                "')]]" +
                "//td[contains(@class,'cart_delete')]//a"
        );

        click(removeButton);

        // Wait until the product row disappears
        WebDriverWait removalWait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        By productRow = By.xpath(
                "//tr[contains(@id,'product-')]" +
                "[.//td[contains(@class,'cart_description')]" +
                "//h4/a[contains(normalize-space(),'" +
                productName +
                "')]]"
        );

        removalWait.until(
                ExpectedConditions.invisibilityOfElementLocated(productRow)
        );
    }

    public boolean isProductRemoved(String productName) {

        By productRow = By.xpath(
                "//tr[contains(@id,'product-')]" +
                "[.//td[contains(@class,'cart_description')]" +
                "//h4/a[contains(normalize-space(),'" +
                productName +
                "')]]"
        );

        try {
            return driver.findElements(productRow)
                    .stream()
                    .noneMatch(WebElement::isDisplayed);

        } catch (Exception e) {
            return true;
        }
    }

    public void proceedToCheckout() {
        click(proceedToCheckoutButton);
    }
}