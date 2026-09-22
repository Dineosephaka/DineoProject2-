package com.dineo.automation.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    protected WebElement waitForElement(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    protected void click(By locator) {

        WebElement element =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(locator)
                );

        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(locator)
            ).click();

        } catch (Exception e) {

            JavascriptExecutor js =
                    (JavascriptExecutor) driver;

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    element
            );

            js.executeScript(
                    "arguments[0].click();",
                    element
            );
        }
    }

    protected void type(By locator, String text) {

        WebElement element = waitForElement(locator);

        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {

        return waitForElement(locator).getText();
    }

    protected boolean isDisplayed(By locator) {

        try {

            return waitForElement(locator).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }
}