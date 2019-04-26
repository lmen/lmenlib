package pt.lmen.lib.core.datetimeformater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class has the same methods as a SimpleDateFormat by it is implemented in a 
 * threadSafe way, by using a different instance of SimpleDateFormat for 
 * each thread that uses this class.
 *  
 * Uses a Threadlocal data structure.
 * 
 * To Use this class, extends it and implement the abstract method @{code createSimpleDateFormatter}
 *  
 * @author mendeslu
 *
 */
public abstract class AbstractSimpleDateFormatThreadSafe implements DateParserFormatThreadSafe {

    private ThreadLocal<SimpleDateFormat> local;

    public AbstractSimpleDateFormatThreadSafe() {
        this.local = ThreadLocal.withInitial( this::createSimpleDateFormatter );
    }

    public String toString() {
        return getFormatter().toString();
    }

    public Date parse( String text ) throws ParseException {
        return getFormatter().parse( text );
    }

    public String format( Date date ) {
        return getFormatter().format( date );
    }

    protected SimpleDateFormat getFormatter() {
        return local.get();
    }

    /**
     * Factory method for creating a new  simpleDateFormater when a new thread needs 
     * to use this class
     * @return
     */
    protected abstract SimpleDateFormat createSimpleDateFormatter();

}
