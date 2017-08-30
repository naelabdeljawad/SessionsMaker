package mobile_odt.pom.appium;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import mobile_odt.com.utils.AppiumUtils;

public class SessionCardHeader {

	public enum SessionOptions {
		ARCHIVE_TO_PORTAL, PERMANENTLY_DELETE, CASE_PENDING_SYNC
	}

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/syncingStatus")
	private List<WebElement> syncStatus;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/sessionTitle")
	private List<WebElement> sessionsTitles;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/sessionOptions")
	private List<WebElement> sessionOptions;

	@FindBy(name = "Archive to portal")
	private WebElement archiveToPortal;

	@FindBy(name = "Permanently delete")
	private WebElement delete;

	@FindBy(name = "Case pending sync")
	private WebElement pendingSync;

	AppiumDriver<AndroidElement> appiumDriver;

	public SessionCardHeader(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
	}

	public boolean clickOnSessionOptionsButton() {
		try {
			sessionOptions.get(0).click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSessionOptionAvailable(SessionOptions option) {
		try {
			switch (option) {
			case ARCHIVE_TO_PORTAL:
				return archiveToPortal.isDisplayed();

			case PERMANENTLY_DELETE:
				return delete.isDisplayed();

			case CASE_PENDING_SYNC:
				return pendingSync.isDisplayed();

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickSessionOption(String option) {
		try {
			AppiumUtils.clickElementByName(option, appiumDriver);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getSessionCardTitle(int index) {
		try {
			return sessionsTitles.get(index).getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean clickSync(int index) {
		try {
			syncStatus.get(index).click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickSessionOptions(int index) {
		try {
			sessionOptions.get(index).click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
