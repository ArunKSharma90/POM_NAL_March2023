package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author arunk
 * this class is for maintaining the Constants values required for testing  
 */
public class Constants {
	public static final String LOGIN_PAGE_TITLE = "Account Login" ;
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login" ;
	public static final int DEFAULT_TIME_OUT = 5 ;
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";
//	public static final String PAGE_HEADER = "Your Store";
	public static final String PRODUCT_INFO_HEADER = "MacBook Pro";
	public static final String LOGIN_ERROR_MSG = "Warning: No match for E-Mail Address and/or Password.";
	public static final CharSequence REGISTER_SUCCESS_MESSG = "Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME = "Register";
	
	public static List<String> getExpectedAccSecList() {
		 List<String> expSecList = new ArrayList<String>();
		 expSecList.add("My Account");
		 expSecList.add("My Orders");
		 expSecList.add("My Affiliate Account");
		 expSecList.add("Newsletter");
		 return expSecList;	
	}
	
}
