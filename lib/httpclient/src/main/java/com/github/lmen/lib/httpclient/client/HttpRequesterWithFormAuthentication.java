package com.github.lmen.lib.httpclient.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HttpRequesterWithFormAuthentication {

    private HttpRequesterWithFormAuthentication() {        
    }
    
    private static final Logger logger = LogManager.getLogger( HttpRequesterWithFormAuthentication.class );
        
    public static void post( String url, String utlPostData, String u, String p ) throws Exception {

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            HttpClientContext context = HttpClientContext.create();

            // invoke que URL but expect to be redirect to the form page
            // and retrieve the session cookies 
            HttpGet httpget = new HttpGet( url );
            CloseableHttpResponse response = httpclient.execute( httpget, context );
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                EntityUtils.consumeQuietly( response.getEntity() );
                if ( statusCode != 200 ) {
                    throw new Exception( "Response with the form authentication page was not ok" );
                }
            } finally {
                response.close();
            }

            // post the authentication form back to the server

            HttpPost httpost = new HttpPost( utlPostData );

            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add( new BasicNameValuePair( "j_username", u ) );
            nvps.add( new BasicNameValuePair( "j_password", p ) );

            httpost.setEntity( new UrlEncodedFormEntity( nvps, HTTP.UTF_8 ) );

            response = httpclient.execute( httpost, context );
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                EntityUtils.consumeQuietly( response.getEntity() );
                if ( statusCode != 200 && statusCode != 302 ) {
                    EntityUtils.consumeQuietly( response.getEntity() );
                    throw new Exception( "Server Response with the form authentication page was not found" );
                }
            } finally {
                response.close();
            }

            // Invoke the web service again. Now it should work             
            httpost = new HttpPost( url );
            response = httpclient.execute( httpost, context );

            try {
                int statusCode = response.getStatusLine().getStatusCode();

                String res = EntityUtils.toString( response.getEntity() );
                if ( statusCode != 200 ) {
                    logger.error( "Server server has returned the response {} -  {}", statusCode, res );
                    throw new Exception( "Server Response with web service was not ok" );
                } else {
                    // Just check if the reply is from the webservice method it self
                    if ( !res.contains( "[WS_HIST_RESTET]" ) ) {
                        logger.error( "Server server has returned the response {} -  {}", statusCode, res );
                        throw new Exception( "Response with the form authentication page was not found" );
                    }
                }
            } finally {
                response.close();
            }

        }

    }

}
