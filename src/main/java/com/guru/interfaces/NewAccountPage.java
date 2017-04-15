package com.guru.interfaces;

public class NewAccountPage {
	public static final String ACCOUNT_TYPE_COMBOBOX = "//select[@name='selaccount']";
	public static final String CUSTOMERID_INPUT = "//input[@name='cusid']";
	public static final String INITIAL_DEPOSIT_INPUT = "//input[@name='inideposit']";
	public static final String SUBMIT_BUTTON = "//input[@name='button2']";
	public static final String RESET_BUTTON = "//input[@name='reset']";
	public static final String GET_CURRENT_AMOUNT = "//td[contains(.,'Current Amount')]/following-sibling::td";
	public static final String GET_ACCOUNT_ID = "//td[contains(.,'Account ID')]/following-sibling::td";
	public static final String CHECK_ACCOUNT_ADD = "//table[@id='account']//p[contains(.,'%s')]";

}
