package demoGuruPages;

import org.openqa.selenium.WebDriver;

public class PageFactory {

	/**
	 * @author Sang Mike
	 * @param driver
	 * @return
	 */
	public static LoginPage getLoginPage(WebDriver driver) {
		return new LoginPage(driver);

	}

	public static HomePage getHomePage(WebDriver driver) {
		return new HomePage(driver);
	}

	public static NewCustomerPage getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPage(driver);
	}

	public static NewAccountPage getNewAccountPage(WebDriver driver) {
		return new NewAccountPage(driver);
	}

	public static EditCustomerPage getEditCustomerPage(WebDriver driver) {
		return new EditCustomerPage(driver);
	}

	public static DepositPage getDepositPage(WebDriver driver) {
		return new DepositPage(driver);
	}

	public static WithdrawallPage getWithdrawallPage(WebDriver driver) {
		return new WithdrawallPage(driver);
	}

	public static FundTransferPage getFundTransferPage(WebDriver driver) {
		return new FundTransferPage(driver);
	}

	public static BalanceEnquiryPage getBalanceEnquiryPage(WebDriver driver) {
		return new BalanceEnquiryPage(driver);
	}
}
