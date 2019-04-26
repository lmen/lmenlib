package pt.lmen.lib.core;

public class InputDataException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public InputDataException() {
        super();
    }

    public InputDataException( String arg0, Throwable arg1, boolean arg2, boolean arg3 ) {
        super( arg0, arg1, arg2, arg3 );
    }

    public InputDataException( String arg0, Throwable arg1 ) {
        super( arg0, arg1 );
    }

    public InputDataException( String arg0 ) {
        super( arg0 );
    }

    public InputDataException( Throwable arg0 ) {
        super( arg0 );
    }

}
