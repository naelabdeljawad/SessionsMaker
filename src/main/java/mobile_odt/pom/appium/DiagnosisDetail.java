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

public class DiagnosisDetail {

	public enum DiagnosisTree {
		PRECANCEROUS_LESION, SUSPECTED_CANCER, CERVICITIS, OTHER
	}

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/layoutNodes")
	private WebElement container;

	@FindBy(name = "Precancerous Lesion")
	private WebElement precancerousLesion;

	@FindBy(name = "Suspected Cancer")
	private WebElement suspectedCancer;

	@FindBy(name = "Cervicitis")
	private WebElement cervicitis;

	@FindBy(name = "Other")
	private WebElement other;

	AppiumDriver<AndroidElement> appiumDriver;

	public DiagnosisDetail(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
		waitPageForLoad();
	}

	public boolean clickPrecancerousLesion() {
		try {
			precancerousLesion.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickSuspectedCancer() {
		try {
			suspectedCancer.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickCervicitis() {
		try {
			cervicitis.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickOther() {
		try {
			other.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	protected void waitPageForLoad() {
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(10, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).pollingEvery(2, TimeUnit.SECONDS)
				.until(ExpectedConditions.visibilityOf(container));
	}

	protected boolean isPageDisplayed() {
		return container.isDisplayed();
	}

}
