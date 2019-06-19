package com.github.lmen.lib.core.datetimeformater;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Thread safe Date  formatter configured with the provided pattern and
 * using the VM default time Zone 
 *  
 * @author mendeslu
 *
 */
public final class TimeZoneDateFormatter extends AbstractSimpleDateFormatThreadSafe {

    private String pattern;
    private boolean lenient;
    private TimeZone timeZone;

    public TimeZoneDateFormatter( String pattern, boolean lenient, TimeZone timeZone ) {
        super();
        this.pattern = pattern;
        this.lenient = lenient;
        this.timeZone = timeZone;
    }
    
    @Override
    protected SimpleDateFormat createSimpleDateFormatter() {
        SimpleDateFormat sdf = new SimpleDateFormat( pattern );
        sdf.setLenient( lenient );
        sdf.setTimeZone( timeZone );
        return sdf;
    }

}
