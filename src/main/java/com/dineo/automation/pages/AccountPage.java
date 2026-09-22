package com.dineo.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dineo.automation.base.BasePage;

public class AccountPage extends BasePage {

    private By accountCreatedMessage =
            By.xpath("//b[contains(text(),'Account Created!')]");

    private By accountDeletedMessage =
            By.xpath("//b[contains(text(),'Account Deleted!')]");

    private By continueButton =
            By.xpath("//a[contains(text(),'Continue')]");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountCreated() {

        return isDisplayed(accountCreatedMessage);
    }

    public boolean isAccountDeleted() {

        return isDisplayed(accountDeletedMessage);
    }

    public void clickContinue() {

        click(continueButton);
    }
}