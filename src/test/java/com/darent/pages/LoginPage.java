package com.darent.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
	private final AndroidDriver driver;
	private final WebDriverWait wait;

    // Generic locators that match common login UIs
    private final By phoneField = MobileBy.xpath("//*[@class='android.widget.EditText' or contains(@content-desc,'phone') or contains(@content-desc,'mobile') or @text='966' or contains(@text,'966')]");
    private final By continueButton = MobileBy.xpath("//*[@content-desc='Continue' or contains(@content-desc,'Next') or contains(@content-desc,'Continue')]");
    private final By otpField = MobileBy.xpath("(//*[@class='android.widget.EditText' or contains(@content-desc,'OTP') or contains(@content-desc,'code')])[last()]");
    private final By submitOtpButton = MobileBy.xpath("//*[@content-desc='Submit' or contains(@content-desc,'Verify') or contains(@content-desc,'Confirm') or contains(@content-desc,'Continue')]");

	public LoginPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

    public void ensureOnLoginScreen() {
        try {
            // If phone field not visible quickly, try tapping a common "Login" CTA or the Book flow might have switched to a different layout
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(phoneField));
        } catch (Exception e) {
            By loginCta = MobileBy.xpath("//*[@content-desc='Login' or contains(@content-desc,'Sign in') or contains(@content-desc,'Log in')]");
            try { driver.findElement(loginCta).click(); } catch (Exception ignored) {}
        }
    }

	public void enterPhone(String phone) {
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField));
		el.clear();
		el.sendKeys(phone);
	}

	public void tapContinue() {
		wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
	}

	public void enterOtp(String otp) {
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(otpField));
		el.clear();
		el.sendKeys(otp);
	}

	public void submitOtp() {
		wait.until(ExpectedConditions.elementToBeClickable(submitOtpButton)).click();
	}
}


