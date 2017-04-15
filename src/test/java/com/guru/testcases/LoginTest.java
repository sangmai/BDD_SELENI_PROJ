package com.guru.testcases;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.galenframework.reports.GalenTestInfo;

import demoGuruPages.BalanceEnquiryPage;
import demoGuruPages.DepositPage;
import demoGuruPages.EditCustomerPage;
import demoGuruPages.FundTransferPage;
import demoGuruPages.HomePage;
import demoGuruPages.LoginPage;
import demoGuruPages.NewAccountPage;
import demoGuruPages.NewCustomerPage;
import demoGuruPages.PageFactory;
import demoGuruPages.WithdrawallPage;
import common.AbstractTest;
import common.Constant;

public class LoginTest extends AbstractTest {
	protected WebDriver driver;
	private String userID = "mngr73146";
	private String password = "dAsazYt";
	private String customerName, dateOfBirth, address, addressNew, city, cityNew, state, stateNew, pin, pinNew, mobile,
			sex, mobileNew, email, emailNew, amountTransfer, balance;
	private String successAddNewCustomer, successEditCustomer, successNewAccount, initialDeposit, accountID,
			currentAmount, successDeposit, successWithdrawal, successBalanceDetails, amountCredit, amountDebited;
	private LoginPage loginPage;
	private String customerID;
	private HomePage homepage;
	private NewCustomerPage newCustomer;
	private EditCustomerPage editCustomer;
	private NewAccountPage newAccount;
	private DepositPage deposit;
	private WithdrawallPage withdrawal;
	private FundTransferPage fundTransfer;
	private BalanceEnquiryPage balanceEnquiry;
	private int payeerAccountNumber;
	private String device = "desktop";
	private List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "browser" })
	public void setup(String browser) {
		driver = openBrowser(browser, Constant.Homepage_url);
		// driver = remoteBrowser("browserStack.json",
		// "bs",Constant.Homepage_url);
		// driver.get(Constant.Homepage_url);
		loginPage = PageFactory.getLoginPage(driver);
		log.info("Pre-condition - Step 01: Login to HomePage");
		homepage = loginPage.LoginAtLoginPage(driver, userID, password);
		customerName = "Sang Mai Xuan " + getRandomString(8);
		dateOfBirth = "10/10/1990";
		address = "03 Ly Chinh Thang";
		addressNew = "110 Dien Bien Phu";
		city = "Da Nang";
		sex = "f";
		cityNew = "Quang Nam";
		state = "Lien Chieu";
		stateNew = "Thanh Khue";
		pin = "550000";
		pinNew = "560000";
		mobile = "123456789";
		mobileNew = "987654321";
		email = RandomEmail();
		emailNew = RandomEmail() + "new";
		initialDeposit = "50000";
		successAddNewCustomer = "Customer Registered Successfully!!!";
		successEditCustomer = "Customer details updated Successfully!!!";
		successNewAccount = "Account Generated Successfully!!!";
		successDeposit = "Transaction details of Deposit for Account ";
		successWithdrawal = "Transaction details of Withdrawal for Account ";
		successBalanceDetails = "Balance Details for Account ";
	}

	@Test
	public void TC_01_CreateNewCustomerAccountAndGetID() {

		log.info("Check UI Home Page");
		// verifyTrue(homepage.checkUI(tests, device));
		homepage.checkUI(tests, device);

		log.info("TC_01 - Step 01: Go to New Customer Page");
		newCustomer = homepage.goToNewCustomerPage(driver);

		log.info("TC_01 - Check UI New Customer Page");
		// verifyTrue(newCustomer.checkUI(tests, device));
		newCustomer.checkUI(tests, device);

		log.info("TC_01 - Step 02: Input 'CUSTOMER NAME'");
		newCustomer.inputCustomerName(customerName);

		log.info("TC_01 - Step 03: Choose 'GENDER'");
		newCustomer.clickGenderRadioButton(sex);

		log.info("TC_01 - Step 04: Input 'DATE OF BIRTH'");
		newCustomer.inputDateOfBirthForChrome(dateOfBirth);
		// if (browser.equals("Chrome")) {
		// newCustomer.inputDateOfBirthForChrome(dateOfBirth);
		// } else {
		// newCustomer.inputDateOfBirth(dateOfBirth);
		// }

		log.info("TC_01 - Step 05: Input 'ADDRESS'");
		newCustomer.inputAddress(address);

		log.info("TC_01 - Step 06: Input 'CITY'");
		newCustomer.inputCity(city);

		log.info("TC_01 - Step 07: Input 'STATE'");
		newCustomer.inputState(state);

		log.info("TC_01 - Step 08: Input 'PIN'");
		newCustomer.inputPIN(pin);

		log.info("TC_01 - Step 09: Input 'MOBILE'");
		newCustomer.inputMobileNumber(mobile);

		log.info("TC_01 - Step 10: Input 'EMAIL'");
		newCustomer.inputEmail(email);

		log.info("TC_01 - Step 11: Input 'PASSWORD'");
		newCustomer.inputPassword(password);

		log.info("TC_01 - Step 12: Click 'SUBMIT' button");
		newCustomer.clickSubmitButton();

		log.info("TC_01 - Step 13: Get value 'CUSTOMER ID'");
		customerID = newCustomer.getCustomerID();

		log.info("TC_01 - Step 14: Verify message displayed");
		verifyTrue(newCustomer.isAddNewCustomerDisplay(successAddNewCustomer));

	}

	@Test
	public void TC_02_EditCustomerAccountAndCheck() {
		log.info("Running TC_02: Edit customer account and check edited successfully");
		log.info("TC_02 - Step 01: Go to Edit Customer Page");
		editCustomer = newCustomer.gotoEditCustomerPage(driver);

		log.info("TC_02 - Step 02: Input 'CUSTOMER NAME'");
		editCustomer.inputCustomerID(customerID);

		log.info("TC_02 - Step 03: Click 'SUBMIT' button");
		editCustomer.clickSubmitButton();

		log.info("TC_02 - Step 04: Input 'NEW ADDRESS'");
		editCustomer.inputAddress(addressNew);

		log.info("TC_02 - Step 05: Input 'NEW CITY'");
		editCustomer.inputCity(cityNew);

		log.info("TC_02 - Step 06: Input 'NEW STATE'");
		editCustomer.inputState(stateNew);

		log.info("TC_02 - Step 07: Input 'NEW PIN'");
		editCustomer.inputPIN(pinNew);

		log.info("TC_02 - Step 08: Input 'NEW MOBILE'");
		editCustomer.inputMobileNumber(mobileNew);

		log.info("TC_02 - Step 09: Input 'NEW EMAIL'");
		editCustomer.inputEmail(emailNew);

		log.info("TC_02 - Step 10: Click 'SUBMIT' button");
		editCustomer.clickSubmitEditButton();

		log.info("TC_02 - Step 11: Verify message displayed");
		verifyTrue(editCustomer.isEditCustomerDisplay(successEditCustomer));
	}

	@Test
	public void TC_03_AddNewAccountAndGetAccountID() {
		log.info(
				"Running TC_03: Add new account and check created successfully, then verify 'Current Amount' and 'Initial Deposit' are equal");
		log.info("TC_03 - Step 01: Go to New Account Page");
		newAccount = newCustomer.gotoNewAccountPage(driver);

		log.info("TC_03 - Step 02: Input 'CUSTOMER NAME'");
		newAccount.inputCustomerID(customerID);

		log.info("TC_03 - Step 03: Select 'CUSTOMER NAME'");
		newAccount.selectAccountType("Current");

		log.info("TC_03 - Step 04: Input 'INITIAL DEPOSIT'");
		newAccount.inputInitialDeposit(initialDeposit);

		log.info("TC_03 - Step 05: Click 'SUBMIT' button");
		newAccount.clickSubmitButton();

		log.info("TC_03 - Step 06: Verify message displays with content 'Account Generated Successfully!!!'");
		verifyTrue(newAccount.isNewAccountDisplay(successNewAccount));

		log.info("TC_03 - Step 07: Get value 'ACCOUNT ID'");
		accountID = newAccount.getAccountID();
		payeerAccountNumber = Integer.parseInt(accountID) - 1;

		log.info("TC_03 - Step 08: Verify 'CURRENT AMOUNT' and 'INITIAL DEPOSIT' are equal");
		currentAmount = newAccount.getCurrentAmount();
		verifyEquals(currentAmount, "50000");
	}

	@Test
	public void TC_04_TransferMoneyIntoCurrentAccount() {
		log.info("Running TC_04: Transfer money into a current account and check account balance equal 55,000");
		log.info("TC_04 - Step 01: Go to Deposite Page");
		deposit = newAccount.gotoDepositPage(driver);

		log.info("TC_04 - Step 02: Input 'ACCOUNT NUMBER'");
		deposit.inputAccountNumber(accountID);

		log.info("TC_04 - Step 03: Input 'AMOUNT'");
		deposit.inputAmount("5000");

		log.info("TC_04 - Step 04: Input 'DESCRIPTION'");
		deposit.inputDescription("Deposit");

		log.info("TC_04 - Step 05: Click 'SUBMIT' button");
		deposit.clickSubmitButton();

		log.info("TC_04 - Step 06: Verify message displays");
		verifyTrue(deposit.isTransactionDetailsDisplay(successDeposit + accountID));

		log.info("TC_04 - Step 07: Verify 'AMOUNT CREDITED' equal 5,000");
		amountCredit = deposit.getAmountCredited();
		verifyEquals(amountCredit, "5000");

		log.info("TC_04 - Step 08: Verify 'CURRENT BALANCE' equal 55,000");
		currentAmount = deposit.getCurrentAmount();
		verifyEquals(currentAmount, "55000");
	}

	@Test
	public void TC_05_WithdrawMoneyFromCurrentAccount() {
		log.info("Running TC_05: Withdraw money from current account and check account balance equal 40,000");
		log.info("TC_05 - Step 01: Go to Withdraw Page");
		withdrawal = deposit.gotoWithdrawallPage(driver);

		log.info("TC_05 - Step 02: Input 'ACCOUNT NUMBER'");
		withdrawal.inputAccountNumber(accountID);

		log.info("TC_05 - Step 03: Input 'AMOUNT'");
		withdrawal.inputAmount("15000");

		log.info("TC_05 - Step 04: Input 'DESCRIPTION'");
		withdrawal.inputDescription("Withdrawal");

		log.info("TC_05 - Step 05: Click 'SUBMIT' button");
		withdrawal.clickSubmitButton();

		log.info("TC_05 - Step 06: Verify message displays");
		verifyTrue(withdrawal.isAddNewCustomerDisplay(successWithdrawal + accountID));

		log.info("TC_05 - Step 07: Verify 'AMOUNT CREDITED' equal 15,000");
		amountDebited = withdrawal.getAmountDebited();
		verifyEquals(amountDebited, "150001");

		log.info("TC_05 - Step 08: Verify 'CURRENT BALANCE' equal 40,000");
		currentAmount = withdrawal.getCurrentAmount();
		verifyEquals(currentAmount, "400003");
	}

	@Test
	public void TC_06_TransferTheMoneyIntoOtherAccountAccount() {
		log.info("Running TC_06: Transfer the money into another account and check amount equal 10,000");
		log.info("TC_06 - Step 01: Go to Fund Transfer Page");
		fundTransfer = withdrawal.gotoFundTransferPage(driver);

		log.info("TC_06 - Step 02: Input 'PAYER ACCOUNT NUMBER'");
		fundTransfer.inputPayerAccountNumber(accountID);

		log.info("TC_06 - Step 03: Input 'PAYEER ACCOUNT NUMBER'");
		fundTransfer.inputPayeerAccountNumber(String.valueOf(payeerAccountNumber));

		log.info("TC_06 - Step 04: Input 'AMOUNT'");
		fundTransfer.inputAmount("10000");

		log.info("TC_06 - Step 05: Input 'DESCRIPTION'");
		fundTransfer.inputDescription("Transfer");

		log.info("TC_06 - Step 06: Click 'SUBMIT' button");
		fundTransfer.clickSubmitButton();

		log.info("TC_06 - Step 07: Verify 'AMOUNT' equal 10,000");
		amountTransfer = fundTransfer.getAmountConfirm();
		verifyEquals(amountTransfer, "10000");

	}

	@Test
	public void TC_07_CheckCurrentAccountBalanceEqual30000() {
		log.info("Running TC_07: Transfer the money into another account and check amount equal 10,000");
		log.info("TC_07 - Step 01: Go to Balance Enquiry Page");
		balanceEnquiry = fundTransfer.gotoBalanceEnquiryPage(driver);

		log.info("TC_07 - Step 02: Input 'ACCOUNT NUMBER'");
		balanceEnquiry.inputAccountNumber(accountID);

		log.info("TC_07 - Step 03: Click 'SUBMIT' button");
		balanceEnquiry.clickSubmitButton();

		log.info("TC_07 - Step 04: Verify message displays with content 'Balance Details for Account Number'");
		verifyTrue(balanceEnquiry.isBalanceDetailsDisplay(successBalanceDetails));

		log.info("TC_07 - Step 05: Verify 'BALANCE' equal 30,000");
		balance = balanceEnquiry.getBalance();
		verifyEquals(balance, "30000");
	}

	@AfterClass(alwaysRun = true)
	public void setupAfterSuite() {
		// close the browser
		generateUIReport(tests);
		closeBrowser(driver);

	}
}
