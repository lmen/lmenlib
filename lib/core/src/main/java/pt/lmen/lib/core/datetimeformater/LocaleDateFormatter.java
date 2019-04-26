package pt.lmen.lib.core.datetimeformater;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Thread safe Date  formatter configured with the provided pattern and locale
 *  
 *  
 * @author mendeslu
 *
 */
public final class LocaleDateFormatter extends AbstractSimpleDateFormatThreadSafe {

    private String pattern;
    private  Locale dateLocale;

    public LocaleDateFormatter( String pattern, Locale dateLocale ) {
        this.pattern = pattern;
        this.dateLocale = dateLocale;
    }

    @Override
    protected SimpleDateFormat createSimpleDateFormatter() {
        return new SimpleDateFormat( pattern, dateLocale );
    }

}
