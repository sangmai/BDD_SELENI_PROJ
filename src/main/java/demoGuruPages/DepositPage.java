package demoGuruPages;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import common.Constant;

public class DepositPage extends AbstractPage {

	private WebDriver driver;

	public DepositPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void inputAccountNumber(String accountNumber) {
		sleep(1);
//		try {
			waitForElement(driver, com.guru.interfaces.DepositPage.ACCOUNT_NUMBER_INPUT, Constant.longWaitTime);
			type(driver, com.guru.interfaces.DepositPage.ACCOUNT_NUMBER_INPUT, accountNumber);
			sleep(1);
//		} catch (Exception e) {
//			e.getStackTrace();
//		}
	}

	public void inputAmount(String amount) {
		waitForElement(driver, com.guru.interfaces.DepositPage.AMOUNT_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.DepositPage.AMOUNT_INPUT, amount);
		sleep(1);
	}

	public void inputDescription(String description) {
		waitForElement(driver, com.guru.interfaces.DepositPage.DESCRIPTION_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.DepositPage.DESCRIPTION_INPUT, description);
		sleep(1);
	}

	public void clickSubmitButton() {
		waitForElement(driver, com.guru.interfaces.DepositPage.SUBMIT_BUTTON, Constant.longWaitTime);
		click(driver, com.guru.interfaces.DepositPage.SUBMIT_BUTTON);
	}

	public boolean isTransactionDetailsDisplay(String messageContent) {
		waitForElement(driver, com.guru.interfaces.DepositPage.CHECK_TRANSACTION_DETAIL, messageContent,
				Constant.longWaitTime);
		return isControlDisplayed(driver, com.guru.interfaces.DepositPage.CHECK_TRANSACTION_DETAIL, messageContent);
	}

	public String getAmountCredited() {
		waitForElement(driver, com.guru.interfaces.DepositPage.GET_AMOUNT_DEPOSIT, Constant.longWaitTime);
		String amount = getText(driver, com.guru.interfaces.DepositPage.GET_AMOUNT_DEPOSIT);
		return amount;
	}

	public String getCurrentAmount() {
		waitForElement(driver, com.guru.interfaces.DepositPage.GET_CURRENT_AMOUNT, Constant.longWaitTime);
		String currentAmount = getText(driver, com.guru.interfaces.DepositPage.GET_CURRENT_AMOUNT);
		return currentAmount;
	}
}
