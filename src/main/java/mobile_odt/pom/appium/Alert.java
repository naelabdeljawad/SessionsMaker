package mobile_odt.pom.appium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class Alert {

	@FindBy(id = "android:id/alertTitle")
	WebElement eulaAlert;

	@FindBy(id = "android:id/button1")
	WebElement alertAccept;

	@FindBy(id = "android:id/button2")
	WebElement alertCancel;

	@FindBy(name = "Location Provider is DISABLED")
	WebElement gpsAlert;

	AppiumDriver<AndroidElement> appiumDriver;

	public Alert(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
		waitPageForLoad();
	}

	public void skipGPSAlert() {
		try {
			if (gpsAlert.isDisplayed())
				alertCancel.click();
		} catch (Exception e) {
			System.err.println("GPS Alert is not Displayed!");
		}
	}

	public void acceptEulaAlert() {
		try {
			if (new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(2, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class)
					.ignoring(StaleElementReferenceException.class).pollingEvery(2, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(eulaAlert)).isDisplayed())
				alertAccept.click();
		} catch (Exception e) {
			System.err.println("Alert is not Displayed!");
		}
	}

	public boolean isEulaAlertDisplayed() {
		try {
			if (new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(5, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class)
					.pollingEvery(2, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(eulaAlert)).isDisplayed())
				return true;
		} catch (Exception e) {
			System.err.println("Alert is not Displayed!");
		}
		return false;
	}

	public void waitPageForLoad() {
		try {
			new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(2, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).ignoring(StaleElementReferenceException.class)
					.pollingEvery(1, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle")));
		} catch (Exception e) {
		}
	}

	public boolean isPageDisplayed() {
		try {
			return eulaAlert.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
