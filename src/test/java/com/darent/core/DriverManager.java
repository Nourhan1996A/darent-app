package com.darent.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void start() throws MalformedURLException {
        String appiumUrl = System.getProperty("appium.url", "http://127.0.0.1:4723");

        UiAutomator2Options options = new UiAutomator2Options();
        // Use ONLY the requested capabilities
        options.amend("platformName", "Android");
        options.amend("appium:automationName", "UiAutomator2");
        options.amend("appium:app_package", "com.darent");
        options.amend("appium:app_activity", ".MainActivity");

        driver = new AndroidDriver(new URL(appiumUrl), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public static void stop() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}


