package mobile_odt.pom.appium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class Details {

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/editPatientId")
	WebElement patientID;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/editPatientName")
	WebElement patientName;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/editPatientAge")
	WebElement patientAge;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/editPatientPhone")
	WebElement patientPhone;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/editPatientHivStatus")
	WebElement patientHIV;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/spinnerNumberOfChildren")
	WebElement patientNumberOfChildren;

	@FindBy(id = "android:id/text1")
	WebElement patient;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/editPatientTown")
	WebElement patientTown;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/buttonShowAddNote")
	WebElement addNewNote;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/editTextNote")
	WebElement note;

	@FindBy(name = "Save Details")
	WebElement saveDetails;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/buttonSaveDetails")
	WebElement camera;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/buttonAddNote")
	WebElement addNoteButton;

	AppiumDriver<AndroidElement> appiumDriver;

	public Details(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
	}

	public boolean setPatientID(String text) {
		try {
			if (!patientID.getText().isEmpty())
				patientID.clear();

			patientID.sendKeys(text);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean setPatientName(String text) {
		try {
			if (!patientName.getText().isEmpty())
				patientName.clear();

			patientName.sendKeys(text);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean setPatientAge(String text) {
		try {
			if (!patientAge.getText().isEmpty())
				patientAge.clear();

			patientAge.sendKeys(text);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean setPatientPhone(String text) {
		try {
			if (!patientPhone.getText().isEmpty())
				patientPhone.clear();

			patientPhone.sendKeys(text);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean setPatientTown(String text) {
		try {
			if (!patientTown.getText().isEmpty())
				patientTown.clear();

			patientTown.sendKeys(text);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean setNotes(String text) {
		try {
			if (!note.getText().isEmpty())
				note.clear();

			note.sendKeys(text);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickAddNewNotes() {
		try {
			AppiumUtils.scrollDownToHiddenElementByName("+ Add New Note", appiumDriver);
			addNewNote.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickAddNoteButton() {
		try {
			addNoteButton.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clickSaveDetails() {
		try {
			saveDetails.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getPatientDetail(String field) {
		try {

			AppiumUtils.scrollTo(field + ":", appiumDriver);

			if (field.equals("Village/Town"))
				field = "Village";

			field = field.toLowerCase();
			AppiumUtils.sleep(1, TimeUnit.SECONDS);

			switch (field) {
			case "patientid":
				return patientID.getText();

			case "name":
				return patientName.getText();

			case "age":
				return patientAge.getText();

			case "phone":
				return patientPhone.getText();

			case "number of children":
				return appiumDriver.findElements(By.id("android:id/text1")).get(1).getText();

			case "Village":
				return patientTown.getText();

			case "hiv status":
				return appiumDriver.findElements(By.id("android:id/text1")).get(0).getText();

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	protected void waitPageForLoad() {
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(10, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).pollingEvery(2, TimeUnit.SECONDS)
				.until(ExpectedConditions.visibilityOf(patientID));
	}

	public boolean isPageDisplayed() {
		return patientID.isDisplayed();
	}

	public String getPatientID() {
		try {
			AppiumUtils.scrollTo("Patient ID:", appiumDriver);
			return patientID.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean removePatientIdTextField() {
		try {
			patientID.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
