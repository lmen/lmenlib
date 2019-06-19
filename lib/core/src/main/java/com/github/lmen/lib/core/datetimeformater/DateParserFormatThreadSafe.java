package com.github.lmen.lib.core.datetimeformater;

import java.text.ParseException;
import java.util.Date;

/**
 *  
 * @author mendeslu
 *
 */
public  interface DateParserFormatThreadSafe {
    
    public Date parse( String text ) throws ParseException;

    public String format( Date date );
    
}
