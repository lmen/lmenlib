package com.github.lmen.lib.core.datetimeformater;

import java.text.SimpleDateFormat;

/**
 * Thread safe Date  formatter configured with the provided pattern and
 * using the VM default time Zone 
 *  
 * @author mendeslu
 *
 */
public final class DefaultTimeZoneLenientDateFormatter extends AbstractSimpleDateFormatThreadSafe {

    private String pattern;
    private boolean lenient;

    public DefaultTimeZoneLenientDateFormatter( String pattern, boolean lenient) {
        this.pattern = pattern;
        this.lenient = lenient;
    }

    @Override
    protected SimpleDateFormat createSimpleDateFormatter() {
        SimpleDateFormat sdf = new SimpleDateFormat( pattern );
        sdf.setLenient( lenient );
        return sdf;
    }

}
