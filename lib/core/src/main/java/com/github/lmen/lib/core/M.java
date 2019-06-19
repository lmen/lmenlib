package com.github.lmen.lib.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.Function;

import com.github.lmen.lib.core.datetimeformater.DateParserFormatThreadSafe;

/**
 * Mapper methods data api
 * 
 * @author mendeslu
 *
 */
public final class M {

    private M () {        
    }
    
    public static String trimOrNull(String txt) {
    	return txt == null ? null : txt.trim(); 
    }
    
    public static String strOrEmpty(String txt) {
        return objOr( txt, "" );
    }
    
    public static <T> T objOr(T obj, T defaultValue) {
        return obj == null ?  defaultValue : obj;
    }
    
    /**
     * if value function:
     * 
     * If an object has value then returns in a lazy another value diri.
     *  
     * Example:
     * It allows to replace the normal java code 
     *  
     * String currCode = null;
     * if (netPos.getCurrency() != null) 
     *   res = netPostCurrency().getCode();
     *   
     * with a more functional (lambda) way, like: 
     * 
     *   String currCode = ifv(netPos.getCurrency(), Currency::getCode);  
     * 
     * @param obj
     * @param convertFn
     * @return
     */
    public static <T, R> R ifv(T obj, Function<T,R> convertFn ) {
        return obj != null ?  convertFn.apply(obj) : null;
    }
    
    public static String toStr(BigDecimal bdec, int scale) {
        return bdec == null ? null : bdec.setScale( scale, BigDecimal.ROUND_HALF_EVEN ).toPlainString();
    }
    
    public static String toStr(BigDecimal bdec) {
        return bdec == null ? null : bdec.toPlainString();
    }
    
    public static String toStr(Enum<?> bdec) {
        return bdec == null ? null : bdec.toString();
    }
    
    public static String toStr(Double d) {
        return d == null ? null : d.toString();
    }
    
    public static String toYesNo(boolean bool) {
        return bool ? "yes" : "no";
    }
    
    public static String toYesNo(Boolean d) {
        return d == null ? null : toYesNo(d.booleanValue());
    }
    
    public static String toStr(int num) {
        return Integer.toString( num);
    }
    
    public static String toStr(long num) {
        return Long.toString( num);
    }
    
    public static String toStr(LocalDate date,  DateTimeFormatter formatter) {
        return date == null ? null : date.format( formatter );
    }
    
    public static String toStr(LocalDateTime date,  DateTimeFormatter formatter) {
        return date == null ? null :  date.format( formatter );
    }
    
    public static String toStr(Date date, DateParserFormatThreadSafe formatter) {
        return date == null ? null :  formatter.format(date);
    }
    
    public static BigDecimal toBDec(String txt)  {        
        try {
            return new BigDecimal( txt );
        } catch (Exception e) {
            throw new InputDataException( "Bad bigDecimal " + txt, e );
        }
    }
    
    public static LocalDate toLocalDate(String txt, DateTimeFormatter formatter )  {
        try {            
            return LocalDate.parse( txt, formatter );            
        } catch (Exception e) {
            throw new InputDataException( "Bad LocalDate " + txt, e );
        }
    }

    public static int toInt( String txt )  {
        try {            
            return Integer.valueOf( txt );            
        } catch (Exception e) {
            throw new InputDataException( "Bad Integer " + txt, e );
        }
    }
    
}
