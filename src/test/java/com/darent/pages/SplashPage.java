package com.darent.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SplashPage {
	private final AndroidDriver driver;
	private final WebDriverWait wait;

    // Using content root as a proxy for splash visibility
    private final By splashRoot = MobileBy.id("android:id/content");
    private final By skipButton = MobileBy.AccessibilityId("Skip");
    private final By skipFallback = MobileBy.xpath("//*[@content-desc='Skip' or @text='Skip' or contains(@content-desc,'Skip')]");

	public SplashPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public boolean isDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(splashRoot)).isDisplayed();
	}

    public void dismissIfSkipPresent() {
        try {
            WebElement el = null;
            if (!driver.findElements(skipButton).isEmpty()) {
                el = driver.findElements(skipButton).get(0);
            } else {
                el = new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(ExpectedConditions.visibilityOfElementLocated(skipFallback));
            }
            if (el != null) {
                wait.until(ExpectedConditions.elementToBeClickable(el)).click();
            }
        } catch (Exception ignored) {}
    }
}


