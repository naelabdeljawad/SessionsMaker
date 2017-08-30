package mobile_odt.pom.appium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import mobile_odt.com.utils.AppiumUtils;

public class Settings {

	@FindBy(name = "Settings")
	WebElement container;

	AppiumDriver<AndroidElement> appiumDriver;

	public Settings(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
		waitPageForLoad();
	}

	public boolean clickOnList(String elementText) {
		try {
			AppiumUtils.clickElementByName(elementText, appiumDriver);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Settings() {
		waitPageForLoad();
	}

	protected void waitPageForLoad() {
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(10, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).pollingEvery(2, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(container));
	}

	public boolean isPageDisplayed() {
		return container.isDisplayed();
	}

}
