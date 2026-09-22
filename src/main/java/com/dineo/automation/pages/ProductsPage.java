package com.dineo.automation.pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
 
import com.dineo.automation.base.BasePage;
 
public class ProductsPage extends BasePage {
 
    private By searchBox =
            By.id("search_product");
 
    private By searchButton =
            By.id("submit_search");
 
    private By productsTitle =
            By.xpath("//h2[contains(text(),'All Products')]");
 
    public ProductsPage(WebDriver driver) {
        super(driver);
    }
 
    public ProductsPage searchProduct(String productName) {
 
        type(searchBox, productName);
        click(searchButton);
 
        return this;
    }
 
    public boolean isProductsPageDisplayed() {
 
        return isDisplayed(productsTitle);
    }
}