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

public class Images {

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/photoGrid")
	WebElement container;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/camera")
	WebElement camera;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/galleryPhotoItem")
	List<WebElement> images;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/photoDelete")
	List<WebElement> deleteImages;

	@FindBy(name = "Delete")
	WebElement deleteImage;

	AppiumDriver<AndroidElement> appiumDriver;

	public Images(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
		waitPageForLoad();
	}

	public boolean clickImageByIndex(int index) {
		try {
			images.get(index).click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickDeleteImageByIndex(int index) {
		try {
			deleteImages.get(index).click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickDelete() {
		try {
			deleteImage.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickCamera() {
		try {
			camera.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getImagesCount() {
		try {
			return images.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	protected void waitPageForLoad() {
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(10, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).pollingEvery(2, TimeUnit.SECONDS)
				.until(ExpectedConditions.visibilityOf(container));
	}

	protected boolean isPageDisplayed() {
		return container.isDisplayed();

	}

}
