package com.dineo.automation.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.dineo.automation.utils.DriverFactory;
import com.dineo.automation.utils.ScreenshotUtil;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        DriverFactory.initializeDriver("chrome");

        driver = DriverFactory.getDriver();

        driver.get("https://automationexercise.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        // Take screenshot if the test fails
        if (result.getStatus() == ITestResult.FAILURE) {

            ScreenshotUtil.takeScreenshot(
                    driver,
                    result.getName()
            );
        }

        // Always close the browser
        DriverFactory.quitDriver();
    }
}