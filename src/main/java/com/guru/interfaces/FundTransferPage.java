package com.guru.interfaces;

public class FundTransferPage {
	public static final String PAYER_ACCOUNT_NUMBER_INPUT = "//input[@name='payersaccount']";
	public static final String PAYEER_ACCOUNT_NUMBER_INPUT = "//input[@name='payeeaccount']";
	public static final String AMOUNT_INPUT = "//input[@name='ammount']";
	public static final String DESCRIPTION_INPUT = "//input[@name='desc']";
	public static final String CONTINUE_BUTTON = "//a[@href='Managerhomepage.php' and contains(.,'Continue')] ";
	public static final String CHECK_FUN_TRANSFER_DISPLAY = "//tbody/tr/td/p[contains(.,'%s')]";
	public static final String GET_AMOUNT_DEPOSIT_COMFIRM = "//table[@class='layout']//td[contains(.,'Amount')]/following-sibling::td";
}
