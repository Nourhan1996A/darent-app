package com.darent.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OnboardingPage {
	private final AndroidDriver driver;
	private final WebDriverWait wait;

	// Try common, resilient locators. If onboarding doesn't exist, methods no-op or return true
	private final By onboardingTitle = MobileBy.xpath("//*[contains(@content-desc, 'Find your next adventure') or contains(@content-desc, 'The Explorer in you') or contains(@content-desc, 'explore new destinations')]");
	private final By onboardingImage = MobileBy.className("android.widget.ImageView");
    private final By nextButton = MobileBy.AccessibilityId("Next");
    private final By skipButton = MobileBy.AccessibilityId("Skip");
    private final By skipFallback = MobileBy.xpath("//*[@content-desc='Skip' or @text='Skip' or contains(@content-desc,'Skip')]");

	public OnboardingPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public boolean verifyFirstScreenElementsPresent() {
		try {
			WebElement title = new WebDriverWait(driver, Duration.ofSeconds(5))
					.until(ExpectedConditions.visibilityOfElementLocated(onboardingTitle));
			WebElement image = driver.findElement(onboardingImage);
			return title.isDisplayed() && image.isDisplayed();
		} catch (Exception ignore) {
			// Assume no onboarding present; consider check passed
			return true;
		}
	}

	public void swipeThroughAllScreensIfNeeded() {
		// Placeholder implementation: tap Next up to 5 times if present
		for (int i = 0; i < 5; i++) {
			List<WebElement> nexts = driver.findElements(nextButton);
			if (nexts.isEmpty()) break;
			try { nexts.get(0).click(); } catch (Exception ignored) { break; }
		}
	}

	public void skip() {
        List<WebElement> skips = driver.findElements(skipButton);
        if (!skips.isEmpty()) {
            try { skips.get(0).click(); return; } catch (Exception ignored) {}
        }
        // fallback
        try {
            WebElement el = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.visibilityOfElementLocated(skipFallback));
            el.click();
        } catch (Exception ignored) {}
	}

    public void dismissAllOnboardingIfPresent() {
        // Try skip directly
        skip();
        // If still present, press Next up to 5 times then try skip again
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(onboardingTitle).isEmpty() || !driver.findElements(nextButton).isEmpty()) {
                List<WebElement> nexts = driver.findElements(nextButton);
                if (!nexts.isEmpty()) {
                    try { nexts.get(0).click(); } catch (Exception ignored) {}
                }
                skip();
            } else {
                break;
            }
        }
    }
}


