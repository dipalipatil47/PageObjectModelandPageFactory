package com.makemytrip.util;

import java.util.Calendar;

import org.openqa.selenium.By;

import com.makemytrip.base.BaseTest;

public class TestUtil extends BaseTest {

	public static String departureDate;
	public static String returnDate;

	public static TestUtil getCurrentDateandReturnDate() {

		TestUtil date = new TestUtil();
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, 1);

		String[] dateArr = cal.getTime().toString().split(" ");
		TestUtil.departureDate = dateArr[0] + "" + dateArr[1] + "" + dateArr[2] + "" + dateArr[5];

		cal.add(Calendar.DATE, 2);
		dateArr = cal.getTime().toString().split(" ");
		TestUtil.returnDate = dateArr[0] + "" + dateArr[1] + "" + dateArr[2] + "" + dateArr[5];
		return date;

	}
	
	public static By customXpath(String xpath, String parm) {
		
		String rawpath= xpath.replaceAll("%replace%", parm);
		return By.xpath(rawpath);
		
	}

}
