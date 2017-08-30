package mobile_odt.pom.appium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class Cases {

	public enum AppQuickFacts {
		NAME, DIAGNOSIS, DECISION, IMAGES, VIDEO
	}

	@FindBy(id = "android:id/list")
	WebElement container;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/buttonNewSession1")
	WebElement addNewPatient;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/sessionIcon")
	List<WebElement> sessionIcons;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/sessionTitle")
	List<WebElement> sessionIDs;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/sessionDecision")
	List<WebElement> sessionDecisions;

	@FindBy(id = "com.mobileoct.android.colposcope.staging:id/sessionCardBottom")
	List<WebElement> sessionsCardBottom;

	AppiumDriver<AndroidElement> appiumDriver;

	public Cases(AppiumDriver<AndroidElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(appiumDriver, 10), this);
	}

	public boolean clickAddNewPatient() {
		try {
			addNewPatient.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isAddNewPatientDisplayed() {
		try {
			return addNewPatient.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean areDecisionsConsult() {
		try {
			for (int i = 0; i < sessionDecisions.size(); i++) {
				if (sessionDecisions.get(i).getText().equals("Waiting for consultation") || sessionDecisions.get(i).getText().equals("Consultation Completed"))
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean isCaseWaitingForConsultation(String patientID) {
		try {
			for (int i = 0; i < sessionIDs.size(); i++) {
				if (sessionIDs.get(i).getText().equals(patientID))
					return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public boolean clickOnSession(int index) {
		try {
			sessionIDs.get(index).click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getPatientIDofPatient(int index) {
		try {
			return sessionIDs.get(index).getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isQuickFactDisplayed(String appQuickFact, int caseIndex, String value) {

		try {
			AppQuickFacts fact = getQuickFact(appQuickFact);

			switch (fact) {
			case NAME:
				if (sessionsCardBottom.get(caseIndex).findElement(By.id("com.mobileoct.android.colposcope.staging:id/sessionName")).getText().equals(""))
					return false;
				else
					return sessionsCardBottom.get(caseIndex).findElement(By.id("com.mobileoct.android.colposcope.staging:id/sessionName")).getText().contains(value);

			case DIAGNOSIS:
				if (sessionsCardBottom.get(caseIndex).findElement(By.id("com.mobileoct.android.colposcope.staging:id/sessionDiagnosis")).getText().equals(""))
					return false;

				else
					return sessionsCardBottom.get(caseIndex).findElement(By.id("com.mobileoct.android.colposcope.staging:id/sessionDiagnosis")).getText().contains(value);

			case DECISION:
				if (sessionsCardBottom.get(caseIndex).findElement(By.id("com.mobileoct.android.colposcope.staging:id/sessionDecision")).getText().equals("")
						|| sessionsCardBottom.get(caseIndex).findElement(By.id("com.mobileoct.android.colposcope.staging:id/sessionDecision")).getText().contains("Not entered"))
					return false;

				else
					return sessionsCardBottom.get(caseIndex).findElement(By.id("com.mobileoct.android.colposcope.staging:id/sessionDecision")).getText().contains(value);

			case IMAGES:
				String temp = sessionsCardBottom.get(caseIndex).findElement(By.id("com.mobileoct.android.colposcope.staging:id/sessionSubject")).getText();

				if (temp.equals(""))
					return false;

				else {
					if (temp.contains("Video")) {
						return temp.substring(7, 9).trim().equals(value);
					} else if (temp.contains("Images"))
						return temp.split("Images:")[1].trim().equals(value);
					else
						return temp.split("Image: ")[1].trim().equals(value);
				}

			case VIDEO:
				String tempVideo = sessionsCardBottom.get(caseIndex).findElement(By.id("com.mobileoct.android.colposcope.staging:id/sessionSubject")).getText();
				return tempVideo.split("Video: ")[1].equals(value);
			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	private AppQuickFacts getQuickFact(String quickFactsLabel) {

		if (quickFactsLabel.equalsIgnoreCase(AppQuickFacts.DIAGNOSIS.name())) {
			return AppQuickFacts.DIAGNOSIS;
		}

		if (quickFactsLabel.equalsIgnoreCase(AppQuickFacts.DECISION.name())) {
			return AppQuickFacts.DECISION;
		}

		if (quickFactsLabel.equalsIgnoreCase(AppQuickFacts.NAME.name())) {
			return AppQuickFacts.NAME;
		}

		if (quickFactsLabel.equalsIgnoreCase(AppQuickFacts.IMAGES.name())) {
			return AppQuickFacts.IMAGES;
		}

		if (quickFactsLabel.equalsIgnoreCase(AppQuickFacts.VIDEO.name())) {
			return AppQuickFacts.VIDEO;
		}

		return null;
	}

	public void waitPageForLoad() {
		new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(1, TimeUnit.SECONDS).until((ExpectedConditions.visibilityOf(container)));
	}

	public boolean isPageDisplayed() {
		try {
			return new FluentWait<AppiumDriver<AndroidElement>>(appiumDriver).withTimeout(1, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/list")))
					.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getCasesNumber() {
		try {
			return sessionsCardBottom.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getIndexOfCaseByPatientID(String patientID) {
		int count = getCasesNumber();

		for (int i = 0; i < count; i++) {
			if (getPatientIDofPatient(i).equalsIgnoreCase(patientID))
				return i;
		}
		return -1;
	}

}
