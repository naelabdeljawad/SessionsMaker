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

public class PIN {

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/container")
	WebElement PIN_Container;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/editTextSelectPin")
	WebElement PINSelection;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/editTextConfirmPin")
	WebElement confirmPIN;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/editTextLoginPassword")
	WebElement password;

	AppiumDriver<AndroidElement> appiumDriver;

	public PIN(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
		waitPageForLoad();
	}

	public void setPIN(String pin1, String pin2) {
		this.PINSelection.sendKeys(pin1);
		this.confirmPIN.sendKeys(pin2);
		AppiumUtils.clickOnPhone("enter", appiumDriver);
	}

	public boolean setPassword(String password) {
		try {
			this.password.sendKeys(password);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void waitPageForLoad() {
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(10, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).pollingEvery(2, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(PIN_Container));
	}

	public boolean isPageDisplayed() {
		return PIN_Container.isDisplayed();
	}

}
