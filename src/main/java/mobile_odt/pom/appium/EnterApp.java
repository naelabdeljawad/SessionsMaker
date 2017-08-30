package mobile_odt.pom.appium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import mobile_odt.com.utils.AppiumUtils;

public class EnterApp {

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/editTextLoginPin")
	WebElement PIN;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/textViewForgotPIN")
	WebElement forgotPIN;

	@FindBy(id = "android:id/text1")
	private WebElement usernameDropdown;

	AppiumDriver<AndroidElement> appiumDriver;

	public EnterApp(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
	}

	public boolean selectUserName(String userName) {
		try {
			clickUserName();
			AppiumUtils.clickElementByName(userName.toLowerCase(), appiumDriver);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickUserName() {
		try {
			this.usernameDropdown.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean setPassword(String pin) {
		try {
			this.PIN.sendKeys(pin);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void enterApp(String PIN) {
		this.PIN.sendKeys(PIN);
		AppiumUtils.clickOnPhone("enter", appiumDriver);
	}

	public void avoidEulaAlert() {
		try {
			Alert alert = new Alert(appiumDriver);
			alert.acceptEulaAlert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitPageForLoad() {
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(1, TimeUnit.SECONDS).until((ExpectedConditions.visibilityOf(PIN)));
	}

	public boolean isPageDisplayed() {
		try {
			return PIN.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickForgotPassword() {
		try {
			forgotPIN.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
