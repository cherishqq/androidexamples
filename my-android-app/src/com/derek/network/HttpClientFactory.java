
package com.derek.network;


import java.util.concurrent.TimeUnit;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;


public class HttpClientFactory {

    private static final String TAG = "HttpClientFactory";

    private static HttpClient httpClient;
    private static ClientConnectionManager connectionManager;
    
    /**
     * Defines:
     *  - the timeout until a connection is established
     *  - the timeout in milliseconds used when retrieving a ManagedClientConnection from the ClientConnectionManager.
     */
    private static final int CONNECTION_TIMEOUT = 30 * 1000;  // 30 sec
    
    /**
     * Defines the default socket timeout (SO_TIMEOUT) in milliseconds which is the timeout for waiting for data.
     */
    private static final int DATA_WAITING_TIMEOUT = 180 * 1000; // 3 minutes
    
    private static final long IDLE_CONNECTIONS_CLEAN_UP = 10 * 60; // 10 minutes
    
    /**
     * Return HttpClient instance that controlled via ThreadSafeClientConnManager.
     * Singleton class implemented.
     *
     * @return HttpClient thread-safe instance
     */
    public static HttpClient getHttpClient() {
    	
        if (httpClient != null) {
            connectionManager.closeExpiredConnections();
            connectionManager.closeIdleConnections(IDLE_CONNECTIONS_CLEAN_UP, TimeUnit.SECONDS);
            return httpClient;
        }

        HttpParams params = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(params, 10);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, DATA_WAITING_TIMEOUT);
        ConnManagerParams.setTimeout(params, CONNECTION_TIMEOUT);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
  
        
        connectionManager = new ThreadSafeClientConnManager(params, schemeRegistry);
        httpClient = new DefaultHttpClient(connectionManager, params);

//    	httpClient = new DefaultHttpClient();
        return httpClient;
    }

    /**
     * Shutdown all connections (instances at HttpClient) and release memory.
     */
    public static void shutdown() {

        if (connectionManager != null) {
            connectionManager.shutdown();
            httpClient = null;
        }
    }

    /**
     * Try to close expired connections and force close idle connections to prepare for the call.
     */
    public static void prepareForCall() {
        if (connectionManager != null) {
            connectionManager.closeExpiredConnections();
            connectionManager.closeIdleConnections(IDLE_CONNECTIONS_CLEAN_UP, TimeUnit.MILLISECONDS);
        }
    }
}
