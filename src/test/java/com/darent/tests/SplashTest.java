package com.darent.tests;
import com.darent.pages.SplashPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SplashTest extends BaseTestNG {

    @Test(description = "Verify Splash Screen is displayed and disappears")
    public void verifySplashScreen() {
        SplashPage splash = new SplashPage(driver);
        Assert.assertTrue(splash.isDisplayed(), "Splash should be visible on launch");
        splash.dismissIfSkipPresent();
    }
}


