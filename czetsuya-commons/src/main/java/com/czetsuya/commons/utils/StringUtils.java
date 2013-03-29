package com.czetsuya.commons.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author Edward P. Legaspi
 * @since Sep 30, 2012
 **/
public class StringUtils {
	/**
	 * Checks if string is in array of strings.
	 * 
	 * @param value
	 *            String value to look for.
	 * @param stringArray
	 *            String array where value is searched.
	 * @return True if array contain string.
	 */
	public static boolean isArrayContainString(String value,
			String[] stringArray) {
		for (int i = 0; i < stringArray.length; i++) {
			if (value != null && value.equals(stringArray[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if an object is null.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isBlank(Object value) {
		return ((value == null)
				|| ((value instanceof String) && ((String) value).trim()
						.length() == 0) || ((value instanceof String) && ((String) value)
				.equals("")));
	}

	/**
	 * Checks if a string object is blank.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isBlank(String value) {
		return (value == null || value.trim().length() == 0 || value
				.matches("\\s+"));
	}

	/**
	 * Concatenates an array of string.
	 * 
	 * @param values
	 * @return
	 */
	public static String concatenate(String... values) {
		return concatenate(" ", values);
	}

	/**
	 * Concatenates an array of string with a given separator.
	 * 
	 * @param separator
	 * @param values
	 * @return
	 */
	public static String concatenate(String separator, String[] values) {
		return concatenate(separator, Arrays.asList(values));
	}

	/**
	 * Concatenates a list of string given a separator.
	 * 
	 * @param separator
	 * @param values
	 * @return
	 */
	public static String concatenate(String separator, List<String> values) {
		StringBuilder sb = new StringBuilder();
		for (String s : values)
			if (!isBlank(s)) {
				if (sb.length() != 0)
					sb.append(separator);
				sb.append(s);
			}
		return sb.toString();
	}

	/**
	 * Concatenates a list of objects.
	 * 
	 * @param values
	 * @return
	 */
	public static String concatenate(Object... values) {
		StringBuilder sb = new StringBuilder();
		for (Object s : values)
			if (!isBlank(s)) {
				if (sb.length() != 0)
					sb.append(" ");
				sb.append(s);
			}
		return sb.toString();
	}

	/**
	 * Reads a file as string.
	 * 
	 * @param filePath
	 * @return
	 * @throws java.io.IOException
	 */
	public static String readFileAsString(String filePath)
			throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}

	/**
	 * Truncates a string.
	 * 
	 * @param s
	 * @param length
	 * @param indicator
	 * @return
	 */
	public static String truncate(String s, int length, boolean indicator) {
		if (isBlank(s) || s.length() <= length)
			return s;

		if (indicator)
			return s.substring(0, length - 3) + "...";
		else
			return s.substring(0, length);
	}

	/**
	 * @param value
	 * @param nbChar
	 * @return
	 */
	public static String getStringAsNChar(String value, int nbChar) {
		if (value == null) {
			return null;
		}
		String buildString = value;
		while (buildString.length() < nbChar) {
			buildString = buildString + " ";
		}
		return buildString;
	}

	/**
	 * @param value
	 * @param nbChar
	 * @return
	 */
	public static String getLongAsNChar(long value, int nbChar) {
		String firstChar = "0";
		if (value < 0) {
			firstChar = "-";
			value = value * -1;
		}
		String buildString = "" + value;
		while (buildString.length() < nbChar) {
			buildString = "0" + buildString;
		}
		buildString = buildString.replaceFirst("0", firstChar);
		return buildString;
	}

	/**
	 * Converts an array of string into a comma delimited string.
	 * 
	 * @param t
	 * @return
	 */
	public static String getArrayElements(String[] t) {
		String str = "";
		for (String s : t) {
			if (str.length() != 0) {
				str += ",";
			}
			str += "'" + s + "'";
		}
		return str;
	}

	/**
	 * Works like concatenate, without extra spaces.
	 * 
	 * @param values
	 *            Objects to concatenate
	 * @return
	 */
	public static String concat(Object... values) {
		StringBuilder sb = new StringBuilder();
		for (Object s : values)
			if (!isBlank(s)) {
				sb.append(s);
			}
		return sb.toString();
	}

	/**
	 * Returns the application name. Usually suffixed by .ear.
	 * 
	 * @param s
	 * @return
	 */
	public static String getAppName(String appName) {
		if (!StringUtils.isBlank(appName)) {
			appName = appName.replaceAll("-SNAPSHOT", "");
			appName = appName.replaceAll("ear", "");
			appName = appName.substring(0, appName.lastIndexOf('-'));
			if (appName.endsWith("-")) {
				StringBuilder sb = new StringBuilder(appName);
				sb.setLength(sb.length() - 1);
				return sb.toString();
			}
		}
		return appName;
	}

	public static String randomAlphanumeric() {
		return randomAlphanumeric(16);
	}

	public static String randomAlphanumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static String mask(String str, int length, String c) {
		StringBuilder mask = new StringBuilder(c);
		for (int i = 0; i < length - 1; i++) {
			mask.append(c);
		}
		String temp = str.substring(0, length);
		str = str.replace(temp, mask);
		return str;
	}

	public static String getEmailDomain(String a) {
		String b = a.substring(a.indexOf('@') + 1);
		return b;
	}

	/**
	 * Will take a url such as http://www.stackoverflow.com and return
	 * www.stackoverflow.com
	 * 
	 * @param url
	 * @return
	 */
	public static String getHost(String url) {
		if (url == null || url.length() == 0)
			return "";

		int doubleslash = url.indexOf("//");
		if (doubleslash == -1)
			doubleslash = 0;
		else
			doubleslash += 2;

		int end = url.indexOf('/', doubleslash);
		end = end >= 0 ? end : url.length();

		return url.substring(doubleslash, end);
	}

	/**
	 * Based on :
	 * http://grepcode.com/file/repository.grepcode.com/java/ext/com.google
	 * .android/android/2.3
	 * .3_r1/android/webkit/CookieManager.java#CookieManager.getBaseDomain%28java.lang.String%
	 * 2 9 Get the base domain for a given host or url. E.g. mail.google.com
	 * will return google.com
	 * 
	 * @param host
	 * @return
	 */
	public static String getBaseDomain(String url) {
		String host = getHost(url);

		int startIndex = 0;
		int nextIndex = host.indexOf('.');
		int lastIndex = host.lastIndexOf('.');
		while (nextIndex < lastIndex) {
			startIndex = nextIndex + 1;
			nextIndex = host.indexOf('.', startIndex);
		}
		if (startIndex > 0) {
			return host.substring(startIndex);
		} else {
			return host;
		}
	}
}
