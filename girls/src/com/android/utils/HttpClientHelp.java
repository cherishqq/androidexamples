package com.android.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.android.common.Constants;
import com.android.factory.HttpClientFactory;
import com.android.rest.RestErrorCodes;
import com.android.rest.RestRequest;

import android.text.TextUtils;
import android.util.Log;

/**
 * 
 * @author Derek.pan
 * 
 */
public class HttpClientHelp {

	private static String tag = "HttpClientHelp";
	private static final String KEEP_ALIVE = "Keep-Alive";
	private static final String CONNECTION = "Connection";
	private static final String SET_COOKIE = "set-cookie";
	private static final String TEXT_XML_CHARSET_UTF_8 = "text/xml; charset=utf-8";

	private static volatile ExecutorService sExecutorService = Executors.newCachedThreadPool();

	public static boolean sendRequest(final RestRequest request) {

		HttpResponse response = executeHttpRequest(request);
		if (response != null) {

		}

		return false;
	}

	public static HttpResponse makeRequest(String url, String body) {

		if (url == null || body == null || url.length() <= 0) {
			Log.d(tag, " url is null or body is null");
			return null;
		}

		return executeHttpRequest(url, body);

		// return null;

	}

	private static HttpResponse executeHttpRequest(final RestRequest request) {

		String uri = null;

		uri = request.getUri();
		if (uri == null) {
			Log.d(tag, " get uri is null ");
			return null;
		}
		
		HttpRequestBase http = null;
		boolean requestBody = false;
		HttpResponse response = null;
		HttpClient client = null;
		try {
		switch (request.mMethod) {
		case GET:
			http = new HttpGet(uri);
			break;
		case POST:
			http = new HttpPost(uri);
			requestBody = true;
			break;
		case DELETE:
			http = new HttpDelete(uri);
			break;
		case PUT:
			http = new HttpPut(uri);
			requestBody = true;
			break;
		default:
			break;
		}
		
		/*
		String body = null;
		if(requestBody) {
			body = request.getBody();
		}
		
		request.onHeaderForming(http);
		if(!TextUtils.isEmpty(body)) {
			try {
				((HttpEntityEnclosingRequestBase)http).setEntity(new StringEntity(body));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		*/
		
		client = HttpClientFactory.getHttpClient();
		response = client.execute(http);		
		
		
		int statusCode = response.getStatusLine().getStatusCode();
		Log.d(tag, " statusCode " + String.valueOf(statusCode));
		if(statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			
			HttpEntity respEntity = response.getEntity();
			if (respEntity == null ) {
				
				Log.d(tag, " No response entity");
				request.setResult(RestErrorCodes.NO_ERROR);
				return null;
			}
			
			Header h = respEntity.getContentType();
			 String contentType = null;     if (h != null) {contentType = h.getValue();}
             h = respEntity.getContentEncoding();
             String contentEncoding = null; if (h != null) {contentEncoding = h.getValue();}
             long length = respEntity.getContentLength();  
			
			InputStream respInpStream = null;
			Reader iReader = null;
			respInpStream = respEntity.getContent();
			
			iReader = new InputStreamReader(respInpStream);

			request.onResponse(iReader, respInpStream, contentType, contentEncoding, length, response.getAllHeaders());
			
			sExecutorService.execute( new Runnable() {

				@Override
				public void run() {
					try{
						request.onCompletion();
					} catch(Throwable th){
						Log.d(tag, " request to completion : exception: " + th.toString());
					}
				}
				
			});	
		}	
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(tag, e.toString());
			Log.e(tag, e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(tag, e.toString());
		}
		
		
		
		return response;
	}

	/**
	 * 
	 * 
	 * @param url
	 * @param body
	 *            body 的格式
	 * @return
	 */

	private static HttpResponse executeHttpRequest(String url, String body) {
		Log.d(tag, "request body" + body);
		HttpClient client = HttpClientFactory.getHttpClient();
		HttpResponse response = null;
		try {
			HttpEntity entity = new StringEntity(body);
			final HttpPost post = new HttpPost(url);
			post.addHeader(Constants.CONTENT_TYPE, TEXT_XML_CHARSET_UTF_8);
			post.addHeader(CONNECTION, KEEP_ALIVE);
			// todo 缺少了一个判断网络状态的
			post.setEntity(entity);

			response = client.execute(post);

			if (response != null
					&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				Log.d(tag, "response status equal OK");

			} else {

			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

}
