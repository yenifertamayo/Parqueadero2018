package com.parking2018;

import java.util.Calendar;

public class DateBuilder {
	
	private DateBuilder() {
		throw new IllegalStateException("Utility class");
	}
	
	public static Calendar dateDiferentToMonday() {
		Calendar ingressDate = Calendar.getInstance();
		ingressDate.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		ingressDate.set(Calendar.MONTH,  Calendar.AUGUST);
		ingressDate.set(Calendar.YEAR, 2018);
		ingressDate.set(Calendar.HOUR, 10);
		ingressDate.set(Calendar.MINUTE, 59);
		ingressDate.set(Calendar.SECOND, 40);
		ingressDate.set(Calendar.MILLISECOND, 30);
		
		return ingressDate;
	}
	
	public static Calendar dateEqualToSunday() {
		
		Calendar ingressDate = Calendar.getInstance();
		ingressDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		ingressDate.set(Calendar.MONTH,  Calendar.AUGUST);
		ingressDate.set(Calendar.YEAR, 2018);
		ingressDate.set(Calendar.HOUR, 10);
		ingressDate.set(Calendar.MINUTE, 59);
		ingressDate.set(Calendar.SECOND, 40);
		ingressDate.set(Calendar.MILLISECOND, 30);
		
		return ingressDate;
	}
	
	public static Calendar exitDate() {
		
		Calendar ingressDate = Calendar.getInstance();
		ingressDate.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		ingressDate.set(Calendar.MONTH,  Calendar.AUGUST);
		ingressDate.set(Calendar.YEAR, 2018);
		ingressDate.set(Calendar.HOUR, 12);
		ingressDate.set(Calendar.MINUTE, 59);
		ingressDate.set(Calendar.SECOND, 40);
		ingressDate.set(Calendar.MILLISECOND, 30);
		
		return ingressDate;
	}
	
	public static Calendar exitDateMore24Hours() {
		
		Calendar ingressDate = Calendar.getInstance();
		ingressDate.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		ingressDate.set(Calendar.MONTH,  Calendar.AUGUST);
		ingressDate.set(Calendar.YEAR, 2018);
		ingressDate.set(Calendar.HOUR, 13);
		ingressDate.set(Calendar.MINUTE, 59);
		ingressDate.set(Calendar.SECOND, 40);
		ingressDate.set(Calendar.MILLISECOND, 30);
		
		return ingressDate;
	}

	public static Calendar exitDateMore24And9Hours() {
		
		Calendar ingressDate = Calendar.getInstance();
		ingressDate.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		ingressDate.set(Calendar.MONTH,  Calendar.AUGUST);
		ingressDate.set(Calendar.YEAR, 2018);
		ingressDate.set(Calendar.HOUR, 20);
		ingressDate.set(Calendar.MINUTE, 59);
		ingressDate.set(Calendar.SECOND, 40);
		ingressDate.set(Calendar.MILLISECOND, 30);
		
		return ingressDate;
	}

}
