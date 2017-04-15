package com.guru.interfaces;

public class BalanceEnquiryPage {
	public static final String GET_AMOUNT = "//table[@id='balenquiry']//td[contains(.,'Balance')]/following-sibling::td";
	public static final String CHECK_BALANCE_DETAIL = "//table[@id='balenquiry']//p[contains(.,'%s')]";
}
