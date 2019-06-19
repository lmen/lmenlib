package com.github.lmen.lib.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class StrParser {

    private StrParser() {        
    }
    
    public static String toNotNullStr(String txt) throws InputDataException {
        txt = M.trimOrNull( txt );
        if (txt == null) {
            throw new InputDataException( "Must have content" + txt);
        }
        return txt;
    }
    
    public static BigDecimal toBDec(String txt) throws InputDataException {        
        try {
            return new BigDecimal( txt );
        } catch (Exception e) {
            throw new InputDataException( "Bad bigDecimal " + txt, e );
        }
    }
    
    public static LocalDate toLocalDate(String txt, DateTimeFormatter formatter ) throws InputDataException {
        try {            
            return LocalDate.parse( txt, formatter );            
        } catch (Exception e) {
            throw new InputDataException( "Bad LocalDate " + txt, e );
        }
    }

    public static int toInt( String txt ) throws InputDataException {
        try {            
            return Integer.valueOf( txt );            
        } catch (Exception e) {
            throw new InputDataException( "Bad Integer " + txt, e );
        }
    }
    
}
