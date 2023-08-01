package com.cdac.attendance.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ApplicationConstants {

	public static final String CPOS_KHARGHAR = "CPOS_KH";
	public static final String CPOS_JUHU = "CPOS_JH";
	public static final String OOPJ_KHARGHAR = "OOPJ_KH";
	public static final String OOPJ_JUHU = "OOPJ_JH";
	public static final String ADS_KHARGHAR = "ADS_KH";
	public static final String ADS_JUHU = "ADS_JH";
	public static final String DBT_KHARGHAR = "DBT_KH";
	public static final String DBT_JUHU = "DBT_JH";
	public static final String SE_KHARGHAR = "SE_KH";
	public static final String SE_JUHU = "SE_JH";
	public static final String WPT_KHARGHAR = "WPT_KH";
	public static final String WPT_JUHU = "WPT_JH";
	public static final String WJP_KHARGHAR = "WJP_KH";
	public static final String WJP_JUHU = "WJP_JH";
	public static final String MS_NET_KHARGHAR = "MS_NET_KH";
	public static final String MS_NET_JUHU = "MS_NET_JH";
	public static final String SDM_KHARGHAR = "SDM_KH";
	public static final String SDM_JUHU = "SDM_JH";
	public static final String KHARGHAR_BATCH = "KH";
	public static final String JUHU_BATCH = "JH";
	
	public static final String CPOS = "CPOS";
	public static final String OOPJ = "OOPJ";
	public static final String ADS = "ADS";
	public static final String SDM = "SDM";
	public static final String DBT = "DBT";
	public static final String WPT = "WPT";
	public static final String WJP = "WJP";
	public static final String MS_NET = "MS.NET";
	public static final String SE = "SE";
	
	public static final int CPOS_TOTAL_LECTURES = 0;
	public static final int OOPJ_TOTAL_LECTURES = 0;
	public static final int ADS_TOTAL_LECTURES = 0;
	public static final int SDM_TOTAL_LECTURES = 0;
	public static final int DBT_TOTAL_LECTURES = 0;
	public static final int WPT_TOTAL_LECTURES = 0;
	public static final int WJP_TOTAL_LECTURES = 0;
	public static final int MS_NET_TOTAL_LECTURES = 0;
	public static final int SE_TOTAL_LECTURES = 0;

	public static String[] SUBJECT_NAME = { "ADS", "CPOS", "DBT", "OOPJ", "MS.NET", "SDM", "SE", "WPT", "WJP" };

	public static String[] CENTER_NAME = { "Kharghar", "Juhu" };
	
//	public static String[] MONTH_LIST = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

	public static List<String> MONTH_LIST = new ArrayList<>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
	
	public static Date attendanceDate;
	
	public static String USER = "ROLE_USER";
	public static String ADMIN = "ROLE_ADMIN";

}
