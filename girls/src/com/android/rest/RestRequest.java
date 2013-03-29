package com.android.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import org.apache.http.Header;
import org.apache.http.HttpMessage;

import android.util.Log;

import com.android.common.Constants;
import com.android.model.RestResult;

public abstract class RestRequest {
	
	public static enum HttpMethod {
		GET, POST, PUT, DELETE
	}
	
	public HttpMethod mMethod;
	private volatile int mResult = RestErrorCodes.UNKNOWN_STATUS_CODE;
	
	private String tag = "RestRequest";
	
	private RestResult result;
	
	
	public Reader read(Reader response){
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(response);
		String line;
		try{
			while( (line = reader.readLine()) != null){
				builder.append(line).append('\n');
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				
			}
			Log.d(tag,builder.toString());
			response = new StringReader(builder.toString());
		}
		
		return response;
		
	}
	
	public void setResult(int result){
		if(mResult != result) {
			mResult = result;
		}
		
	}
	
	public RestRequest(HttpMethod method){
		mMethod = method;
	}
	
	
	
	public void onHeaderForming(HttpMessage request){
		request.addHeader("Accept",Constants.JSON_CONTENT_TYPE);
	}
	
	public String getBody() {
		return "";
	}
	
	public String getUri(){
		return null;
	}
	
	/**
	 * called on request completion
	 */
	
	public void showResponse(Reader response){
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(response);
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append('\n');
                }
            } catch (IOException e) {
            	Log.e(tag, e.toString());
				e.printStackTrace();
			} finally {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            Log.d(tag, builder.toString());

	}
	
	public abstract void onCompletion();
	
	
	public abstract void onResponse(Reader response, InputStream responseStream,
              String contentType, String contentEncoding, long length, Header[] headers)
            		  throws java.io.IOException;
	
	
	

}
