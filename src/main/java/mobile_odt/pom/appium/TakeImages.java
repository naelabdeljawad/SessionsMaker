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

public class TakeImages {

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/buttonCapture")
	WebElement camera;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/zoomRatio")
	WebElement zoomRatio;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/textTimer")
	WebElement timer;

	@FindBy(id = "android:id/up")
	WebElement exit;

	@FindBy(className = "android.widget.ImageButton")
	WebElement addAnotation;

	@FindBy(id = "com.mobileoct.android.colposcope:id/imgDisplay")
	WebElement addAnnotationOnImage;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/addAnnotationButton")
	WebElement annotationButton;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/buttonVideo")
	WebElement video;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/buttonCapture")
	WebElement stop;

	@FindBy(id = "android:id/action_bar_title")
	private WebElement patientIDTitle;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/buttonFilter")
	private WebElement filterButton;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/layoutFilterLeft")
	private WebElement motoFilterButton;

	AppiumDriver<AndroidElement> appiumDriver;

	public TakeImages(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
		waitPageForLoad();
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

	public boolean clickZoom() {
		try {
			zoomRatio.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickExit() {
		try {
			exit.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickTimer() {
		try {
			timer.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getTimerText() {
		try {
			return timer.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPatientIDText() {
		try {
			return patientIDTitle.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getZoomRatioText() {
		try {
			return zoomRatio.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean clickAddAnotation() {
		try {
			addAnotation.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isTimerDisplayed() {
		return timer.isDisplayed();
	}

	public void clickImagesVideoButton() {
		try {
			video.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickStopButton() {
		try {
			stop.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitPageForLoad() {
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(10, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).pollingEvery(2, TimeUnit.SECONDS)
				.until(ExpectedConditions.visibilityOf(exit));
	}

	public boolean isPageDisplayed() {
		return camera.isDisplayed();
	}
}
