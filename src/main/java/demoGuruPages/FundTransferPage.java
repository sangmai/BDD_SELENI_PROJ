package demoGuruPages;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import common.Constant;

public class FundTransferPage extends AbstractPage{
	WebDriver driver;
	public FundTransferPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	public void inputPayerAccountNumber(String payerAccount) {
		waitForElement(driver, com.guru.interfaces.FundTransferPage.PAYER_ACCOUNT_NUMBER_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.FundTransferPage.PAYER_ACCOUNT_NUMBER_INPUT, payerAccount);
		sleep(1);
	}	
	public void inputPayeerAccountNumber(String payeerAccount) {
		waitForElement(driver, com.guru.interfaces.FundTransferPage.PAYEER_ACCOUNT_NUMBER_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.FundTransferPage.PAYEER_ACCOUNT_NUMBER_INPUT, payeerAccount);
		sleep(1);
	}	
	public void inputAmount(String amount) {
		waitForElement(driver, com.guru.interfaces.FundTransferPage.AMOUNT_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.FundTransferPage.AMOUNT_INPUT, amount);
		sleep(1);
	}		
	public void inputDescription(String description) {
		waitForElement(driver, com.guru.interfaces.FundTransferPage.DESCRIPTION_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.FundTransferPage.DESCRIPTION_INPUT, description);
		sleep(1);
	}	
	public void clickSubmitButton() {
		waitForElement(driver, com.guru.interfaces.DepositPage.SUBMIT_BUTTON, Constant.longWaitTime);
		click(driver, com.guru.interfaces.DepositPage.SUBMIT_BUTTON);
	}
	
	public String getAmountConfirm() {
		waitForElement(driver, com.guru.interfaces.FundTransferPage.GET_AMOUNT_DEPOSIT_COMFIRM, Constant.longWaitTime);
		String amount = getText(driver, com.guru.interfaces.FundTransferPage.GET_AMOUNT_DEPOSIT_COMFIRM);
		return amount;
	}
	public void clickContinueButton() {
		waitForElement(driver, com.guru.interfaces.FundTransferPage.CONTINUE_BUTTON, Constant.longWaitTime);
		click(driver, com.guru.interfaces.FundTransferPage.CONTINUE_BUTTON);
	}
	
}
