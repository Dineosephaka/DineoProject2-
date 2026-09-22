package com.dineo.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dineo.automation.pages.AccountPage;
import com.dineo.automation.pages.HomePage;
import com.dineo.automation.pages.LoginPage;
import com.dineo.automation.pages.SignupPage;
import com.dineo.automation.utils.TestData;

public class RegisterTest extends BaseTest {

    @Test
    public void registerNewCustomer() {

        HomePage homePage = new HomePage(driver);

        // Open Signup / Login
        LoginPage loginPage = homePage.clickSignupLogin();

        // Generate unique customer email
        String email = TestData.generateUniqueEmail();

        // Enter signup details
        loginPage.enterSignupDetails(
                TestData.getFirstName() + " " + TestData.getLastName(),
                email
        );

        // Continue to account information
        SignupPage signupPage = loginPage.startSignup();

        // Select title
        signupPage.selectMr();

        // Enter password
        signupPage.enterPassword(
                TestData.getPassword()
        );

        // Enter date of birth
        signupPage.enterDateOfBirth(
                "10",
                "May",
                "2000"
        );

        // Subscribe to newsletter
        signupPage.subscribeToNewsletter();

        // Enter personal details
        signupPage.enterPersonalDetails(
                TestData.getFirstName(),
                TestData.getLastName(),
                TestData.getCompany(),
                TestData.getAddress()
        );

        // Enter address details
        signupPage.enterLocationDetails(
                TestData.getState(),
                TestData.getCity(),
                TestData.getZipCode(),
                TestData.getMobileNumber()
        );

        // Create account
        AccountPage accountPage =
                signupPage.createAccount();

        // Verify account was created
        Assert.assertTrue(
                accountPage.isAccountCreated(),
                "Account was not created successfully"
        );

        System.out.println("Account created successfully.");

        /*
         * The website may leave an advertisement fragment
         * (#google_vignette) on the account-created page.
         * Navigate directly back to the home page.
         */
        driver.get("https://automationexercise.com/");

        // Verify user is logged in
        HomePage loggedInHomePage =
                new HomePage(driver);

        Assert.assertTrue(
                loggedInHomePage.isUserLoggedIn(),
                "User is not logged in after registration"
        );

        System.out.println(
                "User successfully registered and logged in."
        );
    }
}