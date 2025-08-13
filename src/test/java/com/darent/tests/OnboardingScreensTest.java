package com.darent.tests;

import com.darent.pages.OnboardingPage;
import com.darent.pages.SplashPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OnboardingScreensTest extends BaseTestNG {

    @Test(description = "Verify onboarding screens show expected elements")
    public void verifyOnboardingScreens() {
        // Ensure we pass splash first
        SplashPage splash = new SplashPage(driver);
        Assert.assertTrue(splash.isDisplayed(), "Splash should be visible on launch");
        splash.dismissIfSkipPresent();

        OnboardingPage onboarding = new OnboardingPage(driver);
        Assert.assertTrue(onboarding.verifyFirstScreenElementsPresent(), "Onboarding title/image should be visible");
    }
}


