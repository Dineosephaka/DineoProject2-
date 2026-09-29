
package com.dineo.automation.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.dineo.automation.pages.HomePage;
import com.dineo.automation.utils.DriverFactory;
import com.dineo.automation.utils.ScreenshotUtil;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeMethod
    public void setUp() {

        DriverFactory.initializeDriver(getBrowser());

        driver = DriverFactory.getDriver();

        driver.get("https://automationexercise.com/");

        homePage = new HomePage(driver);
    }

    protected String getBrowser() {
        return "chrome";
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
        }

        DriverFactory.quitDriver();
    }
}

