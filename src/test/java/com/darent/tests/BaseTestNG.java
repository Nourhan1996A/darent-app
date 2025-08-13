package com.darent.tests;
import com.darent.core.DriverManager;
import com.darent.pages.BookingPage;
import com.darent.pages.HomePage;
import com.darent.pages.LoginPage;
import com.darent.pages.OnboardingPage;
import com.darent.pages.PropertyDetailsPage;
import com.darent.pages.SplashPage;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTestNG {

    protected AndroidDriver driver;

    @BeforeMethod
    public void baseSetUp() throws Exception {
        DriverManager.start();
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void baseTearDown() {
        DriverManager.stop();
    }

    protected void passSplashAndOnboarding() {
        SplashPage splash = new SplashPage(driver);
        Assert.assertTrue(splash.isDisplayed(), "Splash should be visible on launch");
        splash.dismissIfSkipPresent();

        OnboardingPage onboarding = new OnboardingPage(driver);
        Assert.assertTrue(onboarding.verifyFirstScreenElementsPresent(), "Onboarding elements should be visible");
        onboarding.swipeThroughAllScreensIfNeeded();
        onboarding.skip();
    }

    protected void ensureHomeCoreElements() {
        passSplashAndOnboarding();
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.coreElementsVisible(), "Home core elements should be visible");
    }

    protected void filterChalets() {
        HomePage home = new HomePage(driver);
        home.filterChalets();
    }

    protected void openFirstProperty() {
        HomePage home = new HomePage(driver);
        home.openFirstVisibleProperty();
    }

    protected void assertPropertyDetails() {
        PropertyDetailsPage details = new PropertyDetailsPage(driver);
        Assert.assertTrue(details.keyElementsVisible(), "Property details key elements should be visible");
    }

    protected void tapBook() {
        PropertyDetailsPage details = new PropertyDetailsPage(driver);
        details.tapBook();
    }

    protected void login(String phone, String otp) {
        // If already on booking page, skip login
        try {
            if (new BookingPage(driver).isLoaded()) {
                return;
            }
        } catch (Exception ignored) {}

        LoginPage login = new LoginPage(driver);
        login.ensureOnLoginScreen();
        login.enterPhone(phone);
        login.tapContinue();
        login.enterOtp(otp);
        login.submitOtp();
    }

    protected void assertBookingPageLoaded() {
        BookingPage booking = new BookingPage(driver);
        Assert.assertTrue(booking.isLoaded(), "Booking page should be visible");
    }

    protected void assertDiscountSection() {
        BookingPage booking = new BookingPage(driver);
        Assert.assertTrue(booking.discountVisible(), "Discount section should be visible");
    }

    protected void skipIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            List<WebElement> skips = driver.findElements(MobileBy.AccessibilityId("Skip"));
            if (!skips.isEmpty()) {
                skips.get(0).click();
                return;
            }
            By skipXpath = MobileBy.xpath("//*[@content-desc='Skip' or @text='Skip' or contains(@content-desc,'Skip')]");
            WebElement el = shortWait.until(ExpectedConditions.visibilityOfElementLocated(skipXpath));
            el.click();
        } catch (Exception ignored) {
        }
    }

    protected void handleOptionalOnboardingAfterLogin() {
        // Try page object skip first
        try { new OnboardingPage(driver).skip(); } catch (Exception ignored) {}
        // Fallback generic
        skipIfPresent();
    }
}


