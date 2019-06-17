package pt.lmen.lib.simplemvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import j2html.tags.DomContent;

public class PageState {

    private Map<String,String> state = new HashMap<>();
    private String[] params;

    public PageState( String... params ) {
        super();
        this.params = params;
    }

    public void load( HttpServletRequest req ) {
        for ( String pName : params ) {
            String action = req.getParameter( pName );
            state.put( pName, action );
        }
    }

    public String getValue( String key ) {
        return state.get( key );
    }

    public void put( String key, String value ) {
        state.put( key, value );
    }

    public DomContent renderA() {
        Sequence seq = Partials.seq();
        for ( Entry<String, String> entry : state.entrySet() ) {
            String value = entry.getValue();
            if ( value != null ) {
                String key = entry.getKey();
                seq.with( Partials.inputHidden( key, value ) );
            }
        }
        return seq;
    }
}
