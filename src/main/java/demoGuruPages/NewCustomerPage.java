package demoGuruPages;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.galenframework.reports.GalenTestInfo;

import common.AbstractPage;
import common.Constant;

public class NewCustomerPage extends AbstractPage {
	private WebDriver driver;

	public NewCustomerPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void checkUI(List<GalenTestInfo> tests, String device) {
//		boolean pass = true;
		try {
			checkUIpage(driver, tests, "src/test/resources/newCustomePageUI.gspec", device, "New Customer Page");
		} catch (Throwable e) {
			e.printStackTrace();
//			pass = false;
		}
//		return pass;
	}

	public void inputCustomerName(String customerName) {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.CUSTOMER_NAME_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.NewCustomerPage.CUSTOMER_NAME_INPUT, customerName);
		sleep(1);
	}

	public void inputDateOfBirth(String dateOfBirth) {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.DOB_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.NewCustomerPage.DOB_INPUT, dateOfBirth);
		sleep(1);
	}

	public void inputDateOfBirthForChrome(String dateOfBirth) {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.DOB_INPUT, Constant.longWaitTime);
		executeJavaScript(driver, "document.getElementById('dob').removeAttribute('type','date');");
		type(driver, com.guru.interfaces.NewCustomerPage.DOB_INPUT, dateOfBirth);
		sleep(1);
	}

	public void inputAddress(String address) {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.ADDRESS_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.NewCustomerPage.ADDRESS_INPUT, address);
		sleep(1);
	}

	public void inputCity(String city) {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.CITY_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.NewCustomerPage.CITY_INPUT, city);
		sleep(1);
	}

	public void inputState(String state) {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.STATE_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.NewCustomerPage.STATE_INPUT, state);
		sleep(1);
	}

	public void inputPIN(String pin) {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.PIN_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.NewCustomerPage.PIN_INPUT, pin);
		sleep(1);
	}

	public void inputMobileNumber(String mobilenum) {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.MOBILE_NUMBER_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.NewCustomerPage.MOBILE_NUMBER_INPUT, mobilenum);
		sleep(1);
	}

	public void inputEmail(String email) {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.EMAIL_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.NewCustomerPage.EMAIL_INPUT, email);
		sleep(1);
	}

	public void inputPassword(String password) {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.PASSWORD_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.NewCustomerPage.PASSWORD_INPUT, password);
		sleep(1);
	}

	public void clickGenderRadioButton(String sex) {
		if (sex.equals("m")) {
			waitForElement(driver, com.guru.interfaces.NewCustomerPage.MALE_GENDER_OPTION, Constant.longWaitTime);
			click(driver, com.guru.interfaces.NewCustomerPage.MALE_GENDER_OPTION);
			sleep(1);
		} else if (sex.equals("f")) {
			waitForElement(driver, com.guru.interfaces.NewCustomerPage.FEMALE_GENDER_OPTION, Constant.longWaitTime);
			click(driver, com.guru.interfaces.NewCustomerPage.FEMALE_GENDER_OPTION);
			sleep(1);
		}
	}

	public void clickSubmitButton() {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.SUBMIT_BUTTON, Constant.longWaitTime);
		click(driver, com.guru.interfaces.NewCustomerPage.SUBMIT_BUTTON);
	}

	public String getCustomerID() {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.GET_CUSTOMER_ID, Constant.longWaitTime);
		String customerID = getText(driver, com.guru.interfaces.NewCustomerPage.GET_CUSTOMER_ID);
		return customerID;
	}

	public boolean isAddNewCustomerDisplay(String messageContent) {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.CHECK_CUSTOMER_ADD, messageContent,
				Constant.longWaitTime);
		return isControlDisplayed(driver, com.guru.interfaces.NewCustomerPage.CHECK_CUSTOMER_ADD, messageContent);
	}
}
