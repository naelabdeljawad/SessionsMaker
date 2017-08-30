package mobile_odt.com.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AppiumUtils {

	public enum AppAction {
		CLOSE, OPEN
	}

	private static final String OUTPUT_PATH = System.getProperty("user.dir") + "//target//surefire-reports//screenshots";

	/**
	 * clickOnPhone
	 * 
	 * @param button
	 */
	@SuppressWarnings("rawtypes")
	public static void clickOnPhone(String button, AppiumDriver<AndroidElement> appiumDriver) {
		switch (button) {
		case "back":
		case "Back":
			((AndroidDriver) appiumDriver).pressKeyCode(4);
			break;
		case "home":
		case "Home":
			((AndroidDriver) appiumDriver).pressKeyCode(3);
			break;
		case "apps":
		case "Apps":
			((AndroidDriver) appiumDriver).pressKeyCode(187);
			break;
		case "enter":
		case "Enter":
			((AndroidDriver) appiumDriver).pressKeyCode(66);
			break;

		case "eva":
		case "EVA":
			try {
				((AndroidDriver) appiumDriver).pressKeyCode(187);
				sleep(8, TimeUnit.SECONDS);
				appiumDriver.findElementsByName("CervDx Staging").get(0).click();
				sleep(3, TimeUnit.SECONDS);
			} catch (Exception e) {
				e.printStackTrace();
				appiumDriver.findElementsById("com.android.systemui:id/activity_description").get(0).click();
			}
			break;
		case "language":
		case "Language":
			((AndroidDriver) appiumDriver).startActivity("com.android.settings", "com.android.settings.LanguageSettings");
			break;
		case "MyFiles":
		case "myfiles":
			((AndroidDriver) appiumDriver).startActivity("com.sec.android.app.myfiles", "com.sec.android.app.myfiles.common.MainActivity");
			break;

		default:
			break;
		}
	}

	/**
	 * sleep
	 * 
	 * @param time
	 * @param unit
	 */
	public static void sleep(int time, TimeUnit unit) {
		try {
			switch (unit) {
			case MILLISECONDS:
				Thread.sleep(time);
				break;

			case SECONDS:
				Thread.sleep(time * 1000);
				break;

			case MINUTES:
				Thread.sleep(time * 1000 * 60);
				break;

			default:
				break;
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * scrollTo
	 * 
	 * @param text
	 */
	@SuppressWarnings("deprecation")
	public static void scrollTo(String text, AppiumDriver<AndroidElement> appiumDriver) {
		try {
			((AndroidDriver<AndroidElement>) appiumDriver).hideKeyboard();
			((AndroidDriver<AndroidElement>) appiumDriver).scrollTo(text);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sleep(1, TimeUnit.SECONDS);
	}

	/**
	 * scrollDownToHiddenElementByName
	 * 
	 * @param elementName
	 */
	public static void scrollDownToHiddenElementByName(String elementName, AppiumDriver<AndroidElement> appiumDriver) {
		Dimension dimens = appiumDriver.manage().window().getSize();
		int x = (int) (dimens.getWidth() * 0.5);
		int startY = (int) (dimens.getHeight() * 0.5);
		int endY = (int) (dimens.getHeight() * 0.2);

		sleep(1, TimeUnit.SECONDS);

		try {
			if (appiumDriver.findElement(By.name(elementName)).isDisplayed()) {
				scrollTo(elementName, appiumDriver);
				return;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
			try {
				appiumDriver.swipe(x, startY, x, endY, 800);

				if (appiumDriver.findElement(By.name(elementName)).isDisplayed()) {
					scrollTo(elementName, appiumDriver);
					break;
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * takeScreenShot
	 * 
	 * @param failureImageFileName
	 * @param appiumDriver
	 * @return
	 */
	public static File takeScreenShot(String failureImageFileName, AppiumDriver<AndroidElement> appiumDriver) {
		File scrFile = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
		File destFile = null;
		try {
			destFile = new File(OUTPUT_PATH + "//" + failureImageFileName + ".png");
			FileUtils.copyFile(scrFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile;
	}

	/**
	 * isElementDisplayedByName
	 * 
	 * @param elementName
	 * @return
	 */
	public static boolean isElementDisplayedByName(String elementName, AppiumDriver<AndroidElement> appiumDriver) {
		try {
			return appiumDriver.findElement(By.name(elementName)).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * clickElementByName
	 * 
	 * @param element
	 */
	public static void clickElementByName(String element, AppiumDriver<AndroidElement> appiumDriver) {

		try {
			sleep(2, TimeUnit.SECONDS);
			appiumDriver.findElement(By.name(element)).click();
			sleep(1, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			scrollDownToHiddenElementByName(element, appiumDriver);
			appiumDriver.findElement(By.name(element)).click();
		}
	}

	/**
	 * getDimensionOfElement
	 * 
	 * @param element
	 * @return
	 */
	public static Dimension getDimensionOfElement(WebElement element) {
		Dimension dimension = element.getSize();
		return dimension;
	}

	/**
	 * executeADBCommand
	 * 
	 * @param command
	 * @return
	 */
	public static String executeADBCommand(String command) {
		CommandPrompt commandPrompt = new CommandPrompt();
		String output = commandPrompt.runCommand(System.getProperty("user.dir") + "\\resources\\adb\\adb.exe" + command);
		return output;
	}

	/**
	 * getImageWidth
	 * 
	 * @param file
	 * @return
	 */
	public static int getImageWidth(File file) {
		try {
			BufferedImage image = ImageIO.read(file);
			return image.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static void waitforElementByName(int timeout, String element, AppiumDriver<AndroidElement> appiumDriver) {
		try {
			new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(timeout, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).pollingEvery(2, TimeUnit.SECONDS)
					.until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
