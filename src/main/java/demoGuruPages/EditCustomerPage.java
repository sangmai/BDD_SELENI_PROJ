package demoGuruPages;

import org.openqa.selenium.WebDriver;


import common.Constant;
import common.AbstractPage;

public class EditCustomerPage extends AbstractPage {
	private WebDriver driver;

	public EditCustomerPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void inputCustomerID(String customerID) {
		waitForElement(driver, com.guru.interfaces.EditCustomerPage.CUSTOMER_ID_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.EditCustomerPage.CUSTOMER_ID_INPUT, customerID);
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

	public void clickSubmitButton() {
		waitForElement(driver, com.guru.interfaces.EditCustomerPage.SUBMIT_BUTTON, Constant.longWaitTime);
		click(driver, com.guru.interfaces.EditCustomerPage.SUBMIT_BUTTON);
	}

	public void clickSubmitEditButton() {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.SUBMIT_BUTTON, Constant.longWaitTime);
		click(driver, com.guru.interfaces.NewCustomerPage.SUBMIT_BUTTON);
	}

	public boolean isEditCustomerDisplay(String messageContent) {
		waitForElement(driver, com.guru.interfaces.NewCustomerPage.CHECK_CUSTOMER_ADD, messageContent, Constant.longWaitTime);
		return isControlDisplayed(driver, com.guru.interfaces.NewCustomerPage.CHECK_CUSTOMER_ADD, messageContent);
	}
}
