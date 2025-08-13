# 📱 Appium POM Automation Framework

## 📌 Project Description
This project is an **automated testing framework** built with **Java** and  **Maven** using the **Page Object Model (POM)** design pattern.  
It is designed to automate **mobile app testing** with **Appium**, making tests more **maintainable, reusable, and readable**.

The framework separates:
- **Test logic** (in `tests` folder)
- **Page locators & actions** (in `pages` folder)

This structure ensures that if the app’s UI changes, only the page classes need to be updated, not the test cases.

### Key Features
- Supports **Android mobile automation** via Appium
- **Page Object Model (POM)** for cleaner test structure
- Compatible with both **real devices** and **emulators**
- Scalable for adding more test cases and pages

---
## ✅ Scope Automated
- Splash screen visibility
- Onboarding screens (text/images)
- Home screen elements (search, banners, listings)
- Filter "Chalets" and open property
- Property details elements and Book
- Login with 000001255 / OTP 8888
- Verify discount section

## 🧱 Structure
- `src/test/java/com/darent/core/DriverManager.java`
- `src/test/java/com/darent/pages/` (Page Objects with placeholders)
- `src/test/java/com/darent/tests/DarentFlowTest.java` (TestNG)
- `src/test/resources/testng.xml`

## ▶️ Run (with APK)
```
mvn -Dapp.path="C:\path\to\darent.apk" test
```

Or run installed app and override properties if needed:
```
mvn -Dappium.url=http://127.0.0.1:4723 -Ddevice.name="Android Emulator" -Dapp.package=com.darent -Dapp.activity=.MainActivity test
```

Reports: `target/surefire-reports`.

## 🔎 Locators to fill
Edit under `src/test/java/com/darent/pages/`:
- `<SPLASH_ROOT_ID>`
- `<ONBOARDING_TITLE_ID>`, `<ONBOARDING_IMAGE_ID>`, `<ONBOARDING_NEXT_AI>`, `<ONBOARDING_SKIP_AI>`
- `<HOME_SEARCH_ID>`, `<HOME_BANNER_ID>`, `<HOME_PROPERTY_LIST_ID>`, `<FILTER_CHALETS_AI>`, `<FIRST_PROPERTY_XPATH>`
- `<DETAILS_IMAGE_CAROUSEL_ID>`, `<DETAILS_TITLE_ID>`, `<DETAILS_PRICE_ID>`, `<DETAILS_DESCRIPTION_ID>`, `<DETAILS_FEATURES_ID>`, `<DETAILS_MAP_ID>`, `<DETAILS_BOOK_AI>`
- `<LOGIN_PHONE_ID>`, `<LOGIN_CONTINUE_AI>`, `<LOGIN_OTP_ID>`, `<LOGIN_SUBMIT_AI>`
- `<BOOKING_DISCOUNT_SECTION_ID>`
