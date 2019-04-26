package slib.rmt.tools;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.SSLContext;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;


public class A {

    public static void main( String[] args ) throws IOException, Exception {
        HttpRequesterWithoutAuthentication.post( "https://vmxpocdlk06.uat.slib-exp.com:9200/rmt_expositions/type_rmt_expositions/23", "{\"ds\":\"ds\"}" );
    } 
    
    private static final class HttpRequesterWithoutAuthentication {

        private HttpRequesterWithoutAuthentication() {
        }
                    
        public static CloseableHttpClient newHttpClientA() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
            
            SSLContext sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(null, (certificate, authType) -> true).build();
               
                CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("admin", "admin"));
                
                  CloseableHttpClient client = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .setDefaultCredentialsProvider(credentialsProvider)
                    .build();
                          
                  return client;
        }
        
        public static void post( String url, String contentToPut ) throws IOException,  Exception {
            //new HttpClients.createDefault( )
            try (CloseableHttpClient httpclient = newHttpClientA()) {                
                HttpPut httpPut = new HttpPut( url );
                httpPut.setHeader( "Content-Type", "application/json" );
                httpPut.setEntity( new StringEntity( contentToPut ) );
                CloseableHttpResponse response = httpclient.execute( httpPut );
                try {
                    int statusCode = response.getStatusLine().getStatusCode();
                    System.out.println(  "The status code from Response from Var server about history reset is " + statusCode );
                    if ( statusCode != 200 && statusCode != 201 ) {
                        throw new Exception( "Response from Var is not ok: " + EntityUtils.toString(  response.getEntity() ));
                    }
                    String content = EntityUtils.toString( response.getEntity() );
                    System.out.println( "Response from Var server about history reset is " +  content );
                } finally {
                    response.close();
                }
            }
        }
        
    }

       
}
