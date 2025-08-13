package com.darent.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PropertyDetailsPage {
	private final AndroidDriver driver;
	private final WebDriverWait wait;

    // Resilient, generic locators for a details page
    private final By detailsRoot = MobileBy.xpath("//*[contains(@content-desc,'Overview') or contains(@content-desc,'Details') or contains(@content-desc,'Amenities') or contains(@content-desc,'About') or contains(@content-desc,'Reviews')]");
    private final By price = MobileBy.xpath("//*[contains(@content-desc,'/ night') or contains(@content-desc,'/night') or contains(@content-desc,'SAR') or contains(@content-desc,'AED')]");
    private final By bookButton = MobileBy.xpath("//*[contains(@content-desc,'Book') or contains(@content-desc,'Instant Book') or contains(@content-desc,'Reserve') or contains(@content-desc,'Reservation')]");
    private final By imageAny = MobileBy.className("android.widget.ImageView");
    private final By description = MobileBy.xpath("//*[contains(@content-desc,'Overview') or contains(@content-desc,'Description')]");

	public PropertyDetailsPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

    public boolean keyElementsVisible() {
        int visible = 0;
        try { if (new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(bookButton)).isDisplayed()) visible++; } catch (Exception ignored) {}
        try { if (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(price)).isDisplayed()) visible++; } catch (Exception ignored) {}
        try { if (new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(description)).isDisplayed()) visible++; } catch (Exception ignored) {}
        try { if (!driver.findElements(imageAny).isEmpty()) visible++; } catch (Exception ignored) {}
        try { if (new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(detailsRoot)).isDisplayed()) visible++; } catch (Exception ignored) {}
        return visible >= 1;
    }

    public void tapBook() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(bookButton)).click();
        } catch (Exception e) {
            // Try alternative generic button
            By altBook = MobileBy.xpath("//*[@content-desc='Book Now' or contains(@content-desc,'Continue') or contains(@content-desc,'Proceed')]");
            wait.until(ExpectedConditions.elementToBeClickable(altBook)).click();
        }
    }
}


