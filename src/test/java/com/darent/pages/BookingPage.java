package com.darent.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingPage {
	private final AndroidDriver driver;
	private final WebDriverWait wait;

    // More forgiving booking locators
    private final By discountSection = MobileBy.xpath("//*[contains(@content-desc,'Wallet') or contains(@content-desc,'Discount') or contains(@content-desc,'voucher') or contains(@content-desc,'Promo') or contains(@content-desc,'Total')]");
    private final By bookingRoot = MobileBy.xpath("//*[contains(@content-desc,'Reservation') or contains(@content-desc,'Book') or contains(@content-desc,'Checkout') or contains(@content-desc,'Confirm')]");

	public BookingPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

    public boolean discountVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(discountSection)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

	public boolean isLoaded() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(bookingRoot)).isDisplayed();
	}
}


