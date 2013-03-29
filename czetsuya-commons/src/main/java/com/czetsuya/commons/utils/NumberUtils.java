package com.czetsuya.commons.utils;

import java.math.BigDecimal;

/**
 * @author Edward P. Legaspi
 * @since Nov 25, 2012
 **/
public class NumberUtils {
	/**
	 * Usage: round(yourNumber, 3, BigDecimal.ROUND_HALF_UP);
	 * @param unrounded
	 * @param precision
	 * @param roundingMode
	 * @return
	 */
	public static double round(double unrounded, int precision, int roundingMode) {
		BigDecimal bd = new BigDecimal(unrounded);
		BigDecimal rounded = bd.setScale(precision, roundingMode);
		return rounded.doubleValue();
	}

	/**
	 * Usage: round(new BigDecimal("12.390"), 2, true); // => 12.39 
	 * @param d
	 * @param scale
	 * @param roundUp
	 * @return
	 */
	public static BigDecimal round(BigDecimal d, int scale, boolean roundUp) {
		int mode = (roundUp) ? BigDecimal.ROUND_UP : BigDecimal.ROUND_DOWN;
		return d.setScale(scale, mode);
	}
}
