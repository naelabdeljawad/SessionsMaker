package mobile_odt.com;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import mobile_odt.com.utils.AppiumUtils;
import mobile_odt.pom.appium.Alert;
import mobile_odt.pom.appium.Cases;
import mobile_odt.pom.appium.Login;
import mobile_odt.pom.appium.PIN;
import mobile_odt.pom.appium.Login.LoginTabs;

public class SessionsCreator {

	static Login login;
	static Cases casespage;
	static PIN pinPage;
	Alert alert;
	static Actions actions;
	public static AppiumDriver<AndroidElement> appiumDriver;
	private static DesiredCapabilities androidCapabilities;

	public static void main(String[] args) {
		createSessions();
	}

	public static void createSessions() {

		String env = PropertiesReader.getProperty("appium.env");

		int sessionsCount = Integer.parseInt(PropertiesReader.getProperty("sessions.count"));
		int imagesCount = Integer.parseInt(PropertiesReader.getProperty("images.count"));

		if (env.equalsIgnoreCase("STAGING")) {
			open_staging_app();
			actions = new Actions(appiumDriver);
			for (int i = 0; i < sessionsCount; i++) {
				addStagingSessions(imagesCount);
			}

		} else {
			open_production_app();
			actions = new Actions(appiumDriver);
			for (int i = 0; i < sessionsCount; i++) {
				addProductionSessions(imagesCount);
			}
		}

		closeDriver();
	}

	private static void addProductionSessions(int times) {
		actions.production_add_new_session_with_images(times);

	}

	private static void addStagingSessions(int times) {
		actions.add_new_session_with_images(times);
	}

	private static void open_staging_app() {
		appiumDriver = createAndroidDriver();
		System.err.println("Driver ID:  " + appiumDriver.toString());
		appiumDriver.isAppInstalled("com.mobileoct.android.colposcope.staging");
		login = new Login(appiumDriver);
		login.isPageDisplayed();

		if (PropertiesReader.getProperty("app.reset").equalsIgnoreCase("FALSE"))
			login.enterApp(PropertiesReader.getProperty("PIN"));
		else {
			login.login(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
			pinPage = new PIN(appiumDriver);
			pinPage.setPIN(PropertiesReader.getProperty("PIN"), PropertiesReader.getProperty("PIN"));
		}
		casespage = new Cases(appiumDriver);
		casespage.waitPageForLoad();
	}

	private static void open_production_app() {
		appiumDriver = createAndroidDriver();
		appiumDriver.isAppInstalled("com.mobileoct.android.colposcope");

		if (PropertiesReader.getProperty("app.reset").equalsIgnoreCase("FALSE")) {
			appiumDriver.findElements(By.id("com.mobileoct.android.colposcope:id/tabText")).get(LoginTabs.ENTER_APP.ordinal()).click();
			appiumDriver.findElement(By.id("com.mobileoct.android.colposcope:id/editTextLoginPin")).sendKeys(PropertiesReader.getProperty("PIN"));
			AppiumUtils.clickOnPhone("enter", appiumDriver);
		} else {
			prodlogin(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"), PropertiesReader.getProperty("PIN"));
		}
		casespage = new Cases(appiumDriver);
		casespage.waitPageForLoad();

	}

	public static void prodlogin(String userName, String password, String PIN) {
		appiumDriver.findElement(By.id("com.mobileoct.android.colposcope:id/autoCompleteLoginName")).sendKeys(userName);
		appiumDriver.findElement(By.id("com.mobileoct.android.colposcope:id/editTextLoginPassword")).sendKeys(password);
		AppiumUtils.clickOnPhone("enter", appiumDriver);
		AppiumUtils.sleep(6, TimeUnit.SECONDS);
		appiumDriver.findElement(By.id("com.mobileoct.android.colposcope:id/editTextSelectPin")).sendKeys(PIN);
		appiumDriver.findElement(By.id("com.mobileoct.android.colposcope:id/editTextConfirmPin")).sendKeys(PIN);
		AppiumUtils.clickOnPhone("enter", appiumDriver);
		avoidEulaAlert();
	}

	public static void avoidEulaAlert() {
		try {
			Alert alert = new Alert(appiumDriver);
			alert.acceptEulaAlert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static AppiumDriver<AndroidElement> createAndroidDriver() {

		DesiredCapabilities desiredCapabilities = null;
		try {
			desiredCapabilities = setAppiumLocalCapabilities();
			return new AndroidDriver<AndroidElement>(new URL(PropertiesReader.getProperty("appium.url")), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			try {
				return new AndroidDriver<AndroidElement>(new URL(PropertiesReader.getProperty("appium.url")), desiredCapabilities);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}

		}

		return null;
	}

	public static DesiredCapabilities setAppiumLocalCapabilities() {
		androidCapabilities = new DesiredCapabilities();
		androidCapabilities.setCapability("deviceName", PropertiesReader.getProperty("appium.devicename").toString());
		androidCapabilities.setCapability("platformVersion", PropertiesReader.getProperty("appium.platform.version").toString());
		File app = new File(System.getProperty("user.dir") + File.separatorChar + PropertiesReader.getProperty("app.apk").toString());
		androidCapabilities.setCapability("app", app.getAbsolutePath());
		if (PropertiesReader.getProperty("app.reset").equalsIgnoreCase("FALSE"))
			androidCapabilities.setCapability("noReset", true);
		return androidCapabilities;
	}

	public static void closeDriver() {
		if (appiumDriver != null) {
			DriverTearDown.closeAppiumDrivers(appiumDriver);
		}
	}
}
