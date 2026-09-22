
package com.dineo.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.dineo.automation.base.BasePage;

public class SignupPage extends BasePage {

    private By titleMr =
            By.id("id_gender1");

    private By titleMrs =
            By.id("id_gender2");

    private By passwordField =
            By.id("password");

    private By dayDropdown =
            By.id("days");

    private By monthDropdown =
            By.id("months");

    private By yearDropdown =
            By.id("years");

    private By newsletterCheckbox =
            By.id("newsletter");

    private By firstNameField =
            By.id("first_name");

    private By lastNameField =
            By.id("last_name");

    private By companyField =
            By.id("company");

    private By addressField =
            By.id("address1");

    private By stateField =
            By.id("state");

    private By cityField =
            By.id("city");

    private By zipCodeField =
            By.id("zipcode");

    private By mobileNumberField =
            By.id("mobile_number");

    private By createAccountButton =
            By.xpath("//button[@data-qa='create-account']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public void selectMr() {

        click(titleMr);
    }

    public void selectMrs() {

        click(titleMrs);
    }

    public void enterPassword(String password) {

        type(passwordField, password);
    }

    public void enterDateOfBirth(
            String day,
            String month,
            String year) {

        Select daySelect =
                new Select(waitForElement(dayDropdown));

        Select monthSelect =
                new Select(waitForElement(monthDropdown));

        Select yearSelect =
                new Select(waitForElement(yearDropdown));

        daySelect.selectByVisibleText(day);
        monthSelect.selectByVisibleText(month);
        yearSelect.selectByVisibleText(year);
    }

    public void subscribeToNewsletter() {

        click(newsletterCheckbox);
    }

    public void enterPersonalDetails(
            String firstName,
            String lastName,
            String company,
            String address) {

        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(companyField, company);
        type(addressField, address);
    }

    public void enterLocationDetails(
            String state,
            String city,
            String zipCode,
            String mobileNumber) {

        type(stateField, state);
        type(cityField, city);
        type(zipCodeField, zipCode);
        type(mobileNumberField, mobileNumber);
    }

    public AccountPage createAccount() {

        click(createAccountButton);

        return new AccountPage(driver);
    }
}

