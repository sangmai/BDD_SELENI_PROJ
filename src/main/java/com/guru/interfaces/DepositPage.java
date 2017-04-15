package com.guru.interfaces;

public class DepositPage {

	public static final String ACCOUNT_NUMBER_INPUT = "//input[@name='accountno']";
	public static final String AMOUNT_INPUT = "//input[@name='ammount']";
	public static final String DESCRIPTION_INPUT = "//input[@name='desc']";
	public static final String SUBMIT_BUTTON = "//input[@name='AccSubmit']";
	public static final String GET_AMOUNT_DEPOSIT = "//table[@id='deposit']//td[contains(.,'Amount Credited')]/following-sibling::td";
	public static final String GET_CURRENT_AMOUNT = "//table[@id='deposit']//td[contains(.,'Current Balance')]/following-sibling::td";
	public static final String CHECK_TRANSACTION_DETAIL="//table[@id='deposit']//p[contains(.,'%s')]";

}
