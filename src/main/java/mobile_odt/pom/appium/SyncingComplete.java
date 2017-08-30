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

public class SyncingComplete {

	@FindBy(name = "Connected to cloud and all data synced")
	private WebElement allSyncedMessage;

	@FindBy(name = "Syncing...")
	private WebElement syncing;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/textSync")
	private WebElement pending;

	AppiumDriver<AndroidElement> appiumDriver;

	public SyncingComplete(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
		waitPageForLoad();
	}

	public boolean isAllSyncedMessageDisplayed() {
		try {
			System.out.println("Started syncing...");
			return new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(150, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).pollingEvery(5, TimeUnit.SECONDS)
					.until(ExpectedConditions.visibilityOfElementLocated(By.name("Connected to cloud and all data synced"))).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Syncing Failed!");
		}
		return false;
	}

	protected void waitPageForLoad() {
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(10, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).pollingEvery(2, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(syncing));
	}

	protected boolean isPageDisplayed() {
		try {
			return syncing.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
