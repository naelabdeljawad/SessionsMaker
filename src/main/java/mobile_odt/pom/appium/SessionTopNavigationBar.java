package mobile_odt.pom.appium;

import java.util.List;
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

public class SessionTopNavigationBar {

	public enum NavigationBar {
		DETAILS, IMAGES, DECISION
	}

	@FindBy(className = "android.app.ActionBar$Tab")
	List<WebElement> topBar;

	AppiumDriver<AndroidElement> appiumDriver;

	public SessionTopNavigationBar(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
		waitPageForLoad();
	}

	public boolean clickOnNavigationBar(NavigationBar tab) {
		try {
			topBar.get(tab.ordinal()).click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void waitPageForLoad() {
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(10, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).pollingEvery(2, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfAllElements(topBar));
	}

	public boolean isPageDisplayed() {
		return topBar.get(0).isDisplayed();
	}

}
