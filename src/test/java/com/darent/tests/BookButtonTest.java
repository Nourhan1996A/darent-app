package com.darent.tests;
import org.testng.annotations.Test;

public class BookButtonTest extends BaseTestNG {

    @Test(description = "Tap 'Book' from Property Details page")
    public void tapBookButton() {
        ensureHomeCoreElements();
        filterChalets();
        openFirstProperty();
        assertPropertyDetails();
        tapBook();
    }
}


