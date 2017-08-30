package mobile_odt.pom.appium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class SyncStatus {

	@FindBy(id = "android:id/title")
	List<WebElement> list;

	@FindBy(name = "Connectivity")
	WebElement syncStatus;

	AppiumDriver<AndroidElement> appiumDriver;

	public SyncStatus(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
	}

	public boolean areAllImagesSynced(int count) {
		try {
			if (new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(60, TimeUnit.SECONDS)
					.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(By.name("Images Synced : " + count)))).isDisplayed())
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getValueOf(String textToCompare) {
		for (WebElement element : list) {
			if (element.getText().contains(textToCompare)) {
				return element.getText().split(":")[1].trim();
			}
		}
		return null;
	}

	public boolean isPageDisplayed() {
		try {
			return syncStatus.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
