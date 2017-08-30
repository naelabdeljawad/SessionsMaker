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

public class AddUser {

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/autoCompleteLoginName")
	WebElement userName;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/editTextLoginPassword")
	WebElement password;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/textViewForgotPIN")
	WebElement forgotPassword;

	AppiumDriver<AndroidElement> appiumDriver;

	public AddUser(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
	}

	public void login(String username, String password) {
		// avoidGPSAlert();

		if (!this.userName.getText().isEmpty())
			this.userName.clear();

		if (!this.password.getText().isEmpty())
			this.password.clear();

		this.userName.sendKeys(username);
		this.password.sendKeys(password);

		AppiumUtils.clickOnPhone("enter", appiumDriver);

		avoidEulaAlert();
	}

	public void loginWithoutAvoidingEula(String username, String password) {
		// avoidGPSAlert();

		if (!this.userName.getText().isEmpty())
			this.userName.clear();

		if (!this.password.getText().isEmpty())
			this.password.clear();

		this.userName.sendKeys(username);
		this.password.sendKeys(password);

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

	public boolean clickForgotPassword() {
		try {
			forgotPassword.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void avoidGPSAlert() {
		Alert alert = new Alert(appiumDriver);
		alert.skipGPSAlert();
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
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(10, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).pollingEvery(2, TimeUnit.SECONDS)
				.until(ExpectedConditions.visibilityOf(password));
	}

	public boolean isPageDisplayed() {
		try {
			return password.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
