package com.darent.tests;
import org.testng.annotations.Test;

public class OpenPropertyFromFilteredListTest extends BaseTestNG {

    @Test(description = "Open any property from the filtered 'Chalets' list")
    public void openPropertyFromFilteredList() {
        ensureHomeCoreElements();
        filterChalets();
        openFirstProperty();
    }
}


