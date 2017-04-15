package com.guru.interfaces;

public class HomePage {
	public static String HOME_PAGE_TITLE = "//td[contains(text(),'Manger Id : %s')]";
	public static String GET_USERID = "//td[@class='accpage' and contains(.,'User ID :')]/following-sibling::td";
	public static String GET_PASSWORD = "//td[@class='accpage' and contains(.,'Password :')]/following-sibling::td";
}
