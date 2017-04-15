package demoGuruPages;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import common.Constant;

public class WithdrawallPage extends AbstractPage{
	WebDriver driver;
	public WithdrawallPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	public void inputAccountNumber(String accountNumber) {
		waitForElement(driver, com.guru.interfaces.WithdrawallPage.ACCOUNT_NUMBER_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.WithdrawallPage.ACCOUNT_NUMBER_INPUT, accountNumber);
		sleep(1);
	}	
	public void inputAmount(String amount) {
		waitForElement(driver, com.guru.interfaces.WithdrawallPage.AMOUNT_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.WithdrawallPage.AMOUNT_INPUT, amount);
		sleep(1);
	}	
	public void inputDescription(String description) {
		waitForElement(driver, com.guru.interfaces.WithdrawallPage.DESCRIPTION_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.WithdrawallPage.DESCRIPTION_INPUT, description);
		sleep(1);
	}
	public void clickSubmitButton() {
		waitForElement(driver, com.guru.interfaces.DepositPage.SUBMIT_BUTTON, Constant.longWaitTime);
		click(driver, com.guru.interfaces.DepositPage.SUBMIT_BUTTON);
	}
	public boolean isAddNewCustomerDisplay(String messageContent) {
		waitForElement(driver, com.guru.interfaces.WithdrawallPage.CHECK_WITHDRAWALL_DETAIL, messageContent, Constant.longWaitTime);
		return isControlDisplayed(driver, com.guru.interfaces.WithdrawallPage.CHECK_WITHDRAWALL_DETAIL, messageContent);
	}
	public String getAmountDebited() {
		waitForElement(driver, com.guru.interfaces.WithdrawallPage.GET_AMOUNT_DEBITED, Constant.longWaitTime);
		String amount = getText(driver, com.guru.interfaces.WithdrawallPage.GET_AMOUNT_DEBITED);
		return amount;
	}
	public String getCurrentAmount() {
		waitForElement(driver, com.guru.interfaces.WithdrawallPage.GET_CURRENT_AMOUNT, Constant.longWaitTime);
		String currentAmount = getText(driver, com.guru.interfaces.WithdrawallPage.GET_CURRENT_AMOUNT);
		return currentAmount;
	}
}
