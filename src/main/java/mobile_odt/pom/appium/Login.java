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
import mobile_odt.com.utils.AppiumUtils;

public class Login {

	public enum LoginTabs {
		ENTER_APP, ADD_USER
	}

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/tabText")
	WebElement loginContainer;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/tabText")
	List<WebElement> loginTabs;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/textViewDeviceNotSupported")
	WebElement deviceNotSupported;

	AppiumDriver<AndroidElement> appiumDriver;

	public Login(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
		waitPageForLoad();
	}

	public void login(String username, String password) {
		switchTabs(LoginTabs.ADD_USER);
		AddUser addUser = new AddUser(appiumDriver);
		addUser.login(username, password);
	}

	public void loginWithoutAvoidingEulaAlert(String username, String password) {
		AddUser addUser = new AddUser(appiumDriver);
		addUser.loginWithoutAvoidingEula(username, password);
	}

	public void enterApp(String PIN) {
		switchTabs(LoginTabs.ENTER_APP);
		EnterApp enterApp = new EnterApp(appiumDriver);
		enterApp.enterApp(PIN);
	}

	public void waitPageForLoad() {
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(10, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).pollingEvery(2, TimeUnit.SECONDS)
				.until(ExpectedConditions.visibilityOf(loginContainer));
	}

	public boolean isPageDisplayed() {
		try {
			return new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(2, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(loginContainer)).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void clickForgotPasswordOfAddUser() {
		AddUser addUser = new AddUser(appiumDriver);
		addUser.clickForgotPassword();
	}

	public void avoidEulaAlert() {
		AddUser addUser = new AddUser(appiumDriver);
		addUser.avoidEulaAlert();
	}

	public void switchTabs(LoginTabs loginTab) {
		loginTabs.get(loginTab.ordinal()).click();
		AppiumUtils.sleep(1, TimeUnit.SECONDS);
	}

	public boolean isDeviceNotSupportedMessageDisplayed() {
		try {
			return deviceNotSupported.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
