package com.guru.interfaces;

public class NewCustomerPage {
	public static final String CUSTOMER_NAME_INPUT = "//input[@name='name']";
	public static final String MALE_GENDER_OPTION = "//input[@name='rad1' and @value='m']";
	public static final String FEMALE_GENDER_OPTION = "//input[@name='rad1' and @value='f']";
	public static final String DOB_INPUT = "//input[@id='dob']";
	public static final String ADDRESS_INPUT = "//textarea[@name='addr']";
	public static final String CITY_INPUT = "//input[@name='city']";
	public static final String STATE_INPUT = "//input[@name='state']";
	public static final String PIN_INPUT = "//input[@name='pinno']";
	public static final String MOBILE_NUMBER_INPUT = "//input[@name='telephoneno']";
	public static final String EMAIL_INPUT = "//input[@name='emailid']";
	public static final String PASSWORD_INPUT = "//input[@name='password']";
	public static final String SUBMIT_BUTTON = "//input[@name='sub']";
	public static final String RESET_BUTTON = "//input[@name='res']";
	public static final String CONTINUE_BUTTON = "//a[@href='Managerhomepage.php' and contains(.,'Continue')]";
	public static final String GET_CUSTOMER_ID = "//table[@id='customer']//td[contains(.,'Customer ID')]/following-sibling::td";
	public static final String CHECK_CUSTOMER_ADD = "//table[@id='customer']//p[contains(.,'%s')]";

}
