package com.slib.rmt.backtesting.externalsystems.webservices.client;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HttpRequesterWithoutAuthentication {

    private static final Logger logger = LogManager.getLogger( HttpRequesterWithoutAuthentication.class );
    
    private HttpRequesterWithoutAuthentication() {
    }
        
    public static void post( String url ) throws IOException,  Exception {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost( url );
            CloseableHttpResponse response = httpclient.execute( httpPost );
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                logger.info( "The status code from Response from Var server about history reset is {}", statusCode );
                if ( statusCode != 200 ) {
                    EntityUtils.consumeQuietly( response.getEntity() );
                    throw new Exception( "Response from Var is not ok" );
                }
                String content = EntityUtils.toString( response.getEntity() );
                logger.debug( "Response from Var server about history reset is {}", content );
            } finally {
                response.close();
            }
        }
    }
    
}
