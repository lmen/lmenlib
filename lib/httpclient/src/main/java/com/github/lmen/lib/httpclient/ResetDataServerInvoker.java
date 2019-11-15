package com.github.lmen.lib.httpclient;

import java.net.URLEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.lmen.lib.httpclient.client.HttpRequesterWithFormAuthentication;


public class ResetDataServerInvoker {

    private static final String FORM_AUTENTICATION_ACTION = "/app/j_security_check";

    private static final String WS_DESTINATION_ENDPOINT = "/app/ws/dostuf";

    private static final String UTF_8 = "UTF-8";

    private static final Logger logger = LogManager.getLogger( ResetDataServerInvoker.class );

    private WSConfig wSConfig;

    public ResetDataServerInvoker( WSConfig wSConfig ) {
        this.wSConfig = wSConfig;
    }

    public void requestHistoryReset( String table, String fileName ) throws Exception {

        String param = String.format( "mic=%s&f=%s", URLEncoder.encode( table, UTF_8 ),
            URLEncoder.encode( fileName, UTF_8 ) );
        String url = String.format( "%s%s?%s", wSConfig.getAppBaseUrl(), WS_DESTINATION_ENDPOINT, param );

        String urlFormAuthencticationPage = String.format( "%s%s", wSConfig.getAppBaseUrl(),
            FORM_AUTENTICATION_ACTION );
        
        logger.info( "Posting the request to the server using the url: {} formAuthUrl: {}", url, urlFormAuthencticationPage );        

        String u = wSConfig.getUser();
        String p = wSConfig.getPwd();
        HttpRequesterWithFormAuthentication.post( url, urlFormAuthencticationPage, u, p );

    }

    
    public static void main( String[] args ) throws Exception {
        WSConfig wSConfig = new WSConfig();
        wSConfig.setAppBaseUrl( "http://localhost:8081" );
        wSConfig.setUser( "admin" );
        wSConfig.setPwd( "1" );
        
        ResetDataServerInvoker request = new ResetDataServerInvoker( wSConfig );
        request.requestHistoryReset( "table2", "file.csv" );
    }

}
