package com.darent.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
	private final AndroidDriver driver;
	private final WebDriverWait wait;

	// Core UI elements from XML dumps
    private final By searchBox = MobileBy.xpath("//*[@content-desc and (contains(@content-desc,'Search') or contains(@content-desc,'search'))]");
    private final By mainBanner = MobileBy.xpath("//*[@content-desc and (contains(@content-desc,'Home') or contains(@content-desc,'Explore') or contains(@content-desc,'Discover'))]");
    private final By propertyList = MobileBy.xpath("//*[@class='android.widget.ScrollView' or @class='androidx.recyclerview.widget.RecyclerView']");
    private final By chaletsFilter = MobileBy.xpath("//*[@content-desc='Chalet' or contains(@content-desc,'Chalet') or contains(@content-desc,'Chalets')]");
    private final By firstPropertyCard = MobileBy.xpath("(//android.view.View[contains(@content-desc,'Chalet') or contains(@content-desc,'Villa') or contains(@content-desc,'Apartment')])[1]");

	public HomePage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public boolean coreElementsVisible() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).isDisplayed()
				&& wait.until(ExpectedConditions.visibilityOfElementLocated(mainBanner)).isDisplayed()
				&& wait.until(ExpectedConditions.visibilityOfElementLocated(propertyList)).isDisplayed();
	}

	public void filterChalets() {
		wait.until(ExpectedConditions.elementToBeClickable(chaletsFilter)).click();
	}

	public void openFirstVisibleProperty() {
		List<WebElement> cards = driver.findElements(firstPropertyCard);
		if (!cards.isEmpty()) {
			cards.get(0).click();
		}
	}
}


