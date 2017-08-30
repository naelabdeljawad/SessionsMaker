package mobile_odt.com;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class DriverTearDown {

	public static void closeAndKillWebDrivers(WebDriver webDriver) {

		try {
			if (webDriver != null) {
				System.err.println("Closing WebDriver with ID:         " + webDriver.toString());
				System.out.println("Closing browser...");
				webDriver.quit();
				System.out.println("Browser Closed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeAppiumDrivers(AppiumDriver<AndroidElement> appiumDriver) {
		try {
			if (appiumDriver != null) {
				System.err.println("Closing Appium Driver with ID:         " + appiumDriver.toString());
				System.out.println("Closing Appium Driver...");
				appiumDriver.quit();
				System.out.println("App Closed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
