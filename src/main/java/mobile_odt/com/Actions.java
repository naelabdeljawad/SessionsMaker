package mobile_odt.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import mobile_odt.com.utils.AppiumUtils;
import mobile_odt.pom.appium.Alert;
import mobile_odt.pom.appium.Cases;
import mobile_odt.pom.appium.Header;
import mobile_odt.pom.appium.Header.HeaderButtons;
import mobile_odt.pom.appium.Images;
import mobile_odt.pom.appium.Login;
import mobile_odt.pom.appium.Login.LoginTabs;
import mobile_odt.pom.appium.PIN;
import mobile_odt.pom.appium.SessionTopNavigationBar;
import mobile_odt.pom.appium.SessionTopNavigationBar.NavigationBar;
import mobile_odt.pom.appium.SyncingComplete;
import mobile_odt.pom.appium.TakeImages;

public class Actions {

	Cases cases;
	SessionTopNavigationBar navigationBar;
	Images images;
	TakeImages takeImages;
	String patientId;
	static String patientConsultID;
	static String exportMessage;
	String patientName;
	Login login;
	Cases casespage;
	PIN pinPage;
	Alert alert;
	AppiumDriver<AndroidElement> appiumDriver;

	public Actions(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
	}

	public void production_add_new_session_with_images(int times) {
		cases = new Cases(appiumDriver);

		appiumDriver.findElement(By.id("com.mobileoct.android.colposcope:id/buttonNewSession1")).click();
		navigationBar = new SessionTopNavigationBar(appiumDriver);
		navigationBar.clickOnNavigationBar(NavigationBar.IMAGES);

		AppiumUtils.sleep(2, TimeUnit.SECONDS);

		prodclickImagesCameraButton();
		takeImages = new TakeImages(appiumDriver);

		int attemps = times;

		for (int i = 0; i < attemps; i++) {
			prodclickCaptureCamerButton();
		}

		takeImages.clickExit();

		Header header = new Header(appiumDriver);
		header.clickOnNavigationBar(HeaderButtons.EXIT);
	}

	public void production_session_is_synced() {
		prodclickOnSyncHeader();

		SyncingComplete syncingComplete = new SyncingComplete(appiumDriver);
		syncingComplete.isAllSyncedMessageDisplayed();
	}

	public void add_new_session_with_images(int times) {
		cases = new Cases(appiumDriver);
		cases.clickAddNewPatient();

		navigationBar = new SessionTopNavigationBar(appiumDriver);
		navigationBar.clickOnNavigationBar(NavigationBar.IMAGES);

		images = new Images(appiumDriver);
		images.clickCamera();

		takeImages = new TakeImages(appiumDriver);

		int attemps = times;

		for (int i = 0; i < attemps; i++) {
			takeImages.clickCamera();
			AppiumUtils.sleep(500, TimeUnit.MILLISECONDS);
		}

		takeImages.clickExit();

		Header header = new Header(appiumDriver);
		header.clickOnNavigationBar(HeaderButtons.EXIT);
	}

	public void avoid_alerts() {
		login.avoidEulaAlert();
	}

	public void logout_app(AppiumDriver<AndroidElement> appiumDriver) {
		login = new Login(appiumDriver);
		while (!login.isPageDisplayed())
			AppiumUtils.clickOnPhone("back", appiumDriver);
	}

	public void app_user_is_logged_in() {
		casespage = new Cases(appiumDriver);
		casespage.waitPageForLoad();
		casespage.isPageDisplayed();
	}

	public void is_login_page_displayed() {
		login = new Login(appiumDriver);
		login.isPageDisplayed();
	}

	public void app_is_not_displayed() {
		login.isPageDisplayed();
	}

	public void is_Eula_Alert_Displayed(String status) {
		alert = new Alert(appiumDriver);

		if (status.equalsIgnoreCase("TRUE"))
			alert.isEulaAlertDisplayed();
		else
			alert.isEulaAlertDisplayed();

		alert.acceptEulaAlert();
	}

	public void login_page_is_displayed(AppiumDriver<AndroidElement> appiumDriver) {
		AppiumUtils.clickOnPhone("eva", appiumDriver);
		Login login = new Login(appiumDriver);
		login.isPageDisplayed();
		login.switchTabs(LoginTabs.ADD_USER);
		login.switchTabs(LoginTabs.ENTER_APP);
		login.switchTabs(LoginTabs.ADD_USER);
	}

	public void prodclickImagesCameraButton() {
		appiumDriver.findElement(By.id("com.mobileoct.android.colposcope:id/camera")).click();

	}

	public void prodclickImagesVideoButton() {
		appiumDriver.findElement(By.id("com.mobileoct.android.colposcope:id/buttonVideo")).click();

	}

	public void prodclickImagesStopButton() {
		appiumDriver.findElement(By.id("com.mobileoct.android.colposcope:id/buttonCapture")).click();

	}

	public void prodclickCaptureCamerButton() {
		appiumDriver.findElement(By.id("com.mobileoct.android.colposcope:id/buttonCapture")).click();
		AppiumUtils.sleep(500, TimeUnit.MILLISECONDS);
	}

	public void prodclickOnSessionImage(int index) {
		appiumDriver.findElements(By.id("com.mobileoct.android.colposcope:id/galleryPhotoItem")).get(index).click();
	}

	public boolean setPatientID(String text) {
		try {
			AndroidElement patientID = appiumDriver.findElement(By.id("com.mobileoct.android.colposcope:id/editPatientId"));
			if (!patientID.getText().isEmpty())
				patientID.clear();

			patientID.sendKeys(text);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void prodclickOnCaseImage(int index) {
		appiumDriver.findElements(By.id("com.mobileoct.android.colposcope:id/sessionIcon")).get(index).click();
	}

	public void prodclickOnSyncHeader() {
		appiumDriver.findElement(By.id("com.mobileoct.android.colposcope:id/action_sync_status")).click();
	}

	public void prodclickOnAddAnnotation() {
		appiumDriver.findElements(By.className("android.widget.ImageButton")).get(1).click();
	}
}
