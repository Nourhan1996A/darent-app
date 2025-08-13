package com.darent.tests;
import com.darent.core.DriverManager;
import com.darent.pages.*;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DarentFlowTest {

	private AndroidDriver driver;

	@BeforeMethod
	public void setUp() throws Exception {
		DriverManager.start();
		driver = DriverManager.getDriver();
	}

	@AfterMethod
	public void tearDown() {
		DriverManager.stop();
	}

	@Test(description = "E2E: Splash -> Onboarding -> Home -> Details -> Book -> Login -> Discount")
	public void fullE2EFlow() {
		// 1) Splash
		SplashPage splash = new SplashPage(driver);
		Assert.assertTrue(splash.isDisplayed(), "Splash should be visible on launch");
		splash.dismissIfSkipPresent();

		// 2) Onboarding
		OnboardingPage onboarding = new OnboardingPage(driver);
		Assert.assertTrue(onboarding.verifyFirstScreenElementsPresent(), "Onboarding title and image should be visible");
		onboarding.swipeThroughAllScreensIfNeeded();
		onboarding.skip();

		// 3) Home and Filter + Open property
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.coreElementsVisible(), "Home core elements should be visible");
		home.filterChalets();
		home.openFirstVisibleProperty();

		// 4) Details and Book
		PropertyDetailsPage details = new PropertyDetailsPage(driver);
		Assert.assertTrue(details.keyElementsVisible(), "Property details key elements should be visible");
		details.tapBook();

		// 5) Login
		LoginPage login = new LoginPage(driver);
		login.enterPhone("000001255");
		login.tapContinue();
		login.enterOtp("8888");
		login.submitOtp();

		// 6) Booking discount
		BookingPage booking = new BookingPage(driver);
		Assert.assertTrue(booking.discountVisible(), "Discount section should be visible on booking page");
	}
}


