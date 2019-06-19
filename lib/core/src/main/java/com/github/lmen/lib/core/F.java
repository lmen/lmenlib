package com.github.lmen.lib.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory class for common classes 
 * @author mendeslu
 *
 */
public final class F {

    private F() {
    }

    public static final BigDecimal ZERO = BigDecimal.ZERO;

    public static <T> List<T> alist() {
        return new ArrayList<>();
    }

    public static <K,V> Map<K,V> hmap() {
        return new HashMap<>();
    }

    public static BigDecimal bdec( String value ) {
        return new BigDecimal( value );
    }

    public static BigDecimal bdec( float value ) {
        return BigDecimal.valueOf( value );
    }

    public static BigDecimal bdec( double value ) {
        return BigDecimal.valueOf( value );
    }

    public static BigDecimal bdec( long value ) {
        return BigDecimal.valueOf( value );
    }

    public static <T> List<T> l( T... val ) {
        return new ArrayList<T>( Arrays.asList( val ) );
    }

    public static LocalDate d( int year, int month, int day ) {
        return LocalDate.of( year, month, day );
    }

}
