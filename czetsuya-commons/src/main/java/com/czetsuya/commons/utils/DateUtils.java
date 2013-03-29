package com.czetsuya.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static Date addDaysToDate(Date date, int days) {
		Date result = null;

		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, days);
			result = calendar.getTime();
		}

		return result;
	}

	public static Date createDate(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, day);

		return c.getTime();
	}

	public static Date createDate(int year, int month, int day, int hour, int min, int sec) {
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, day, hour, min, sec);

		return c.getTime();
	}

	public static String getDateDifference(long from, long to) {
		long difference = to - from;

		long x = difference / 1000;
		int seconds = (int) x % 60;
		x /= 60;
		int minutes = (int) x % 60;
		x /= 60;
		int hours = (int) x % 24;
		x /= 24;
		int days = (int) x;

		StringBuilder sb = new StringBuilder();
		if (days != 0)
			sb.append(days + "days ");
		if (hours != 0)
			sb.append(hours + "hrs ");
		if (minutes != 0)
			sb.append(minutes + "mins ");
		if (seconds != 0)
			sb.append(seconds + "secs");

		return sb.toString();
	}

	public static String format(String format, Date date) {
		SimpleDateFormat dt = new SimpleDateFormat(format);
		String str = dt.format(date.getTime());
		return str;
	}

	public static Date parse(String format, String str) throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat(format);
		Date date = dt.parse(str);
		return date;
	}
}
