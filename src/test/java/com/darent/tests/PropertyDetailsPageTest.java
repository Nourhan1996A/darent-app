package com.darent.tests;
import org.testng.annotations.Test;

public class PropertyDetailsPageTest extends BaseTestNG {

    @Test(description = "Verify key elements on Property Details page")
    public void verifyPropertyDetailsPageElements() {
        ensureHomeCoreElements();
        filterChalets();
        openFirstProperty();
        assertPropertyDetails();
    }
}


