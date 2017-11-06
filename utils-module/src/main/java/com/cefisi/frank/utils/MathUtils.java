package com.cefisi.frank.utils;

import java.math.BigDecimal;

/**
 * An utility math class.
 */
public final class MathUtils {

    private MathUtils() {
	// Ensures non-instantiability
    }

    /**
     * Indicates whether or not given {@code BigDecimal} is positive.
     *
     * @param value
     *        a {@code BigDecimal} value to tests positiveness against
     * @return {@code true} if positive; {@code false} otherwise
     * @throws NullPointerException
     *         if {@code value} is {@code null}
     */
    public static boolean isPositive(BigDecimal value) {
	return 0 < value.compareTo(BigDecimal.ZERO);
    }
    
    /**
     * Indicates whether or not given {@code BigDecimal} is negative.
     *
     * @param value
     *        a {@code BigDecimal} value to tests negativeness against
     * @return {@code true} if negative; {@code false} otherwise
     * @throws NullPointerException
     *         if {@code value} is {@code null}
     */
    public static boolean isNegative(BigDecimal value) {
	return 0 > value.compareTo(BigDecimal.ZERO);
    }
}
