package demoGuruPages;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import common.Constant;

public class NewAccountPage extends AbstractPage {
	private WebDriver driver;

	public NewAccountPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void inputCustomerID(String customerID) {
		waitForElement(driver, com.guru.interfaces.EditCustomerPage.CUSTOMER_ID_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.EditCustomerPage.CUSTOMER_ID_INPUT, customerID);
		sleep(1);
	}
	public void selectAccountType(String accountType){
		waitForElement(driver, com.guru.interfaces.NewAccountPage.ACCOUNT_TYPE_COMBOBOX, Constant.longWaitTime);
		selectItemCombobox(driver, com.guru.interfaces.NewAccountPage.ACCOUNT_TYPE_COMBOBOX, accountType);
	}
	public void inputInitialDeposit(String deposit_amount) {
		waitForElement(driver, com.guru.interfaces.NewAccountPage.INITIAL_DEPOSIT_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.NewAccountPage.INITIAL_DEPOSIT_INPUT, deposit_amount);
		sleep(1);
	}
	public void clickSubmitButton() {
		waitForElement(driver, com.guru.interfaces.NewAccountPage.SUBMIT_BUTTON, Constant.longWaitTime);
		click(driver, com.guru.interfaces.NewAccountPage.SUBMIT_BUTTON);
	}
	public boolean isNewAccountDisplay(String messageContent) {
		waitForElement(driver, com.guru.interfaces.NewAccountPage.CHECK_ACCOUNT_ADD, messageContent, Constant.longWaitTime);
		return isControlDisplayed(driver, com.guru.interfaces.NewAccountPage.CHECK_ACCOUNT_ADD, messageContent);
	}
	public String getAccountID() {
		waitForElement(driver, com.guru.interfaces.NewAccountPage.GET_ACCOUNT_ID , Constant.longWaitTime);
		String AccountID = getText(driver, com.guru.interfaces.NewAccountPage.GET_ACCOUNT_ID);
		return AccountID;
	}	
	public String getCurrentAmount() {
		waitForElement(driver, com.guru.interfaces.NewAccountPage.GET_CURRENT_AMOUNT, Constant.longWaitTime);
		String currentAmount = getText(driver, com.guru.interfaces.NewAccountPage.GET_CURRENT_AMOUNT);
		return currentAmount;
	}
}
