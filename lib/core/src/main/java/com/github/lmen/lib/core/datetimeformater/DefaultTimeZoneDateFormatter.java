package com.github.lmen.lib.core.datetimeformater;

import java.text.SimpleDateFormat;

/**
 * Thread safe Date  formatter configured with the provided pattern and
 * using the VM default time Zone 
 *  
 * @author mendeslu
 *
 */
public final class DefaultTimeZoneDateFormatter extends AbstractSimpleDateFormatThreadSafe {

    private String pattern;

    public DefaultTimeZoneDateFormatter( String pattern ) {
        this.pattern = pattern;
    }

    @Override
    protected SimpleDateFormat createSimpleDateFormatter() {
        return new SimpleDateFormat( pattern );
    }

}
