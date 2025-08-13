package com.darent.tests;
import com.darent.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeScreenElementsTest extends BaseTestNG {

    @Test(description = "Verify Home screen core UI elements")
    public void verifyHomeScreenElements() {
        ensureHomeCoreElements();
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.coreElementsVisible(), "Home core elements should be visible");
    }
}


