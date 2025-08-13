package com.darent.tests;
import org.testng.annotations.Test;

public class DiscountSectionTest extends BaseTestNG {

    @Test(description = "Verify discount section appears on Booking page after login")
    public void verifyDiscountSection() {
        ensureHomeCoreElements();
        filterChalets();
        openFirstProperty();
        assertPropertyDetails();
        tapBook();
        login("000001255", "8888");
        assertDiscountSection();
    }
}


