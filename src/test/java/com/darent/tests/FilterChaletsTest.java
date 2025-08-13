package com.darent.tests;
import com.darent.pages.HomePage;
import org.testng.annotations.Test;

public class FilterChaletsTest extends BaseTestNG {

    @Test(description = "Apply filter for 'Chalets'")
    public void filterForChalets() {
        ensureHomeCoreElements();
        HomePage home = new HomePage(driver);
        home.filterChalets();
    }
}


