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

public class Header {

	public enum HeaderButtons {
		EXIT, DELETE, SETTINGS, SYNC_COMPLETE, EXPORT
	}

	@FindBy(className = "android.widget.ImageView")
	private List<WebElement> delete;

	@FindBy(id = "android:id/up")
	private WebElement up;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/action_settings")
	private WebElement settings;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/action_sync_status")
	private WebElement syncComplete;

	@FindBy(className = "android.widget.ImageView")
	private List<WebElement> export;

	AppiumDriver<AndroidElement> appiumDriver;

	public Header(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
	}

	public boolean clickOnNavigationBar(HeaderButtons button) {
		try {
			AppiumUtils.sleep(500, TimeUnit.MILLISECONDS);

			switch (button) {
			case EXIT:
				up.click();
				break;
			case DELETE:
				delete.get(2).click();
				break;
			case SETTINGS:
				settings.click();
				break;
			case SYNC_COMPLETE:
				syncComplete.click();
				break;
			case EXPORT:
				export.get(2).click();
				break;
			default:
				break;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void waitPageForLoad() {
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(10, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).pollingEvery(2, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(up));
	}

	public boolean isPageDisplayed() {
		return up.isDisplayed();
	}

}
