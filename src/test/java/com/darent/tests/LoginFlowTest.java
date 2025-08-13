package com.darent.tests;
import org.testng.annotations.Test;

public class LoginFlowTest extends BaseTestNG {

    @Test(description = "Login using phone and OTP from Booking flow")
    public void loginFlow() {
        ensureHomeCoreElements();
        filterChalets();
        openFirstProperty();
        assertPropertyDetails();
        tapBook();
        login("000001255", "8888");
        // Aggressive dismissal just in case onboarding reappears after login
        try { new com.darent.pages.OnboardingPage(driver).dismissAllOnboardingIfPresent(); } catch (Exception ignored) {}
        handleOptionalOnboardingAfterLogin();
        assertBookingPageLoaded();
    }
}


