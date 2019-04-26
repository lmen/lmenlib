package com.slib.rmt.backtesting.externalsystems.webservices;

import java.net.URLEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.slib.rmt.backtesting.externalsystems.webservices.client.HttpRequesterWithFormAuthentication;

public class VarHistoryResetServerInvoker {

    private static final String FORM_AUTENTICATION_ACTION = "/var/admin/j_security_check";

    private static final String WS_HISTORY_RESET = "/var/admin/ws/history/reset";

    private static final String UTF_8 = "UTF-8";

    private static final Logger logger = LogManager.getLogger( VarHistoryResetServerInvoker.class );

    private VarWSConfig varWSConfig;

    public VarHistoryResetServerInvoker( VarWSConfig varWSConfig ) {
        this.varWSConfig = varWSConfig;
    }

    public void requestHistoryReset( String market, String fileName ) throws Exception {

        String param = String.format( "mic=%s&f=%s", URLEncoder.encode( market, UTF_8 ),
            URLEncoder.encode( fileName, UTF_8 ) );
        String url = String.format( "%s%s?%s", varWSConfig.getVarBaseUrl(), WS_HISTORY_RESET, param );

        String urlFormAuthencticationPage = String.format( "%s%s", varWSConfig.getVarBaseUrl(),
            FORM_AUTENTICATION_ACTION );
        
        logger.info( "Posting the request to the Var server using the url: {} formAuthUrl: {}", url, urlFormAuthencticationPage );        

        String u = varWSConfig.getUser();
        String p = varWSConfig.getPwd();
        HttpRequesterWithFormAuthentication.post( url, urlFormAuthencticationPage, u, p );

    }

    
    public static void main( String[] args ) throws Exception {
        VarWSConfig varWSConfig = new VarWSConfig();
        varWSConfig.setVarBaseUrl( "http://localhost:8081" );
        varWSConfig.setUser( "admin-gui" );
        varWSConfig.setPwd( "admin-gui" );
        
        VarHistoryResetServerInvoker request = new VarHistoryResetServerInvoker( varWSConfig );
        request.requestHistoryReset( "XPAR", "bt_hist_file.csv" );
    }

}
