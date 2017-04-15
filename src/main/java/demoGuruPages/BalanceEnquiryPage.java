package demoGuruPages;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import common.Constant;

public class BalanceEnquiryPage extends AbstractPage{
	WebDriver driver;

	public BalanceEnquiryPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	public void inputAccountNumber(String accountNumber) {
		waitForElement(driver, com.guru.interfaces.WithdrawallPage.ACCOUNT_NUMBER_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.WithdrawallPage.ACCOUNT_NUMBER_INPUT, accountNumber);
		sleep(1);
	}	
	public void clickSubmitButton() {
		waitForElement(driver, com.guru.interfaces.DepositPage.SUBMIT_BUTTON, Constant.longWaitTime);
		click(driver, com.guru.interfaces.DepositPage.SUBMIT_BUTTON);
	}
	public String getBalance() {
		waitForElement(driver, com.guru.interfaces.BalanceEnquiryPage.GET_AMOUNT, Constant.longWaitTime);
		String balance = getText(driver, com.guru.interfaces.BalanceEnquiryPage.GET_AMOUNT);
		return balance;
	}
	public boolean isBalanceDetailsDisplay(String messageContent) {
		waitForElement(driver, com.guru.interfaces.BalanceEnquiryPage.CHECK_BALANCE_DETAIL, messageContent, Constant.longWaitTime);
		return isControlDisplayed(driver, com.guru.interfaces.BalanceEnquiryPage.CHECK_BALANCE_DETAIL, messageContent);
	}
}
