package com.dineo.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static WebDriver driver;

    public static void initializeDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();

        } else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        }

        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}