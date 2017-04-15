package com.guru.interfaces;

public class WithdrawallPage {

	public static final String ACCOUNT_NUMBER_INPUT = "//input[@name='accountno']";
	public static final String AMOUNT_INPUT = "//input[@name='ammount']";
	public static final String DESCRIPTION_INPUT = "//input[@name='desc']";
	public static final String GET_AMOUNT_DEBITED = "//table[@id='withdraw']//td[contains(.,'Amount Debited')]/following-sibling::td";
	public static final String GET_CURRENT_AMOUNT = "//table[@id='withdraw']//td[contains(.,'Current Balance')]/following-sibling::td";
	public static final String CHECK_WITHDRAWALL_DETAIL = "//table[@id='withdraw']//p[contains(.,'%s')]";
	
	

}
