package slib.rmt.tools.injector;

import java.io.Closeable;
import java.io.IOException;

import javax.json.JsonObject;
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

public class BulkHttpPostInjector implements Closeable {

    private CloseableHttpClient closeableHttpClient;
    
    private String url;    
    
    public BulkHttpPostInjector( String url ) {
        super();
        this.url = url;
    }

    public void start() throws Exception {
        closeableHttpClient = newHttpClientA();
    }
    
    private static CloseableHttpClient newHttpClientA() throws Exception {

        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial( null, ( certificate, authType ) -> true ).build();

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials( AuthScope.ANY, new UsernamePasswordCredentials( "admin", "admin" ) );

        CloseableHttpClient client = HttpClients.custom().setSSLContext( sslContext ).setSSLHostnameVerifier( new NoopHostnameVerifier() )
            .setDefaultCredentialsProvider( credentialsProvider ).build();

        return client;
    }

    public void insert(  String index, String type, String id, JsonObject obj ) throws Exception {
        String url = String.format( "%s/%s/%s", index, type, id );
        post( url, obj.toString() );
    }

    public void post( String path, String contentToPut ) throws IOException, Exception {
        
        HttpPut httpPut = new HttpPut( url + "/" + path );
        httpPut.setHeader( "Content-Type", "application/json" );
        httpPut.setEntity( new StringEntity( contentToPut, "UTF-8" ) );
        CloseableHttpResponse response = closeableHttpClient.execute( httpPut );
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println( "The status code from Response from Var server about history reset is " + statusCode );
            if ( statusCode != 200 && statusCode != 201 ) {
                throw new Exception( "Response from Var is not ok: " + EntityUtils.toString( response.getEntity() ) );
            }
            String content = EntityUtils.toString( response.getEntity() );
            System.out.println( "Response from Var server about history reset is " + content );
        }
        finally {
            response.close();
        }
    }


    @Override
    public void close() throws IOException {
        if (closeableHttpClient != null) {
            closeableHttpClient.close();
        }
    }
    
    public static void main( String[] args ) throws IOException, Exception {
        try (BulkHttpPostInjector in = new BulkHttpPostInjector("https://vmxpocdlk06.uat.slib-exp.com:9200"  )) {
            in.start();
            in.post( "rmt_expositions/type_rmt_expositions/dsd2", "{\"ds\":\"ds\"}" );
            in.post( "rmt_expositions/type_rmt_expositions/dsd3", "{\"ds\":\"ds\"}" );
        }
                
    }
}
