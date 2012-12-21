package com.android.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.http.Header;

import com.android.common.Constants;



public class UserRestRequest extends RestRequest{
	
	
	
	private static final HttpMethod HTTP_METHOD = RestRequest.HttpMethod.GET;
	private static final String REQUEST_URL_PATH_TEMPLATE = Constants.REST_REQUEST_PATH_MAIN + "user/show";
	
	private String mRequestUrlPath;
	private long id;
	private String userName;
	private String password;
	private String email;

	public UserRestRequest(HttpMethod method) {
		super(method);
		// TODO Auto-generated constructor stub
	}
	
	public UserRestRequest(long id){
		super(HTTP_METHOD);
		this.id = id;
	}
	
	public UserRestRequest(String email){
		super(HTTP_METHOD);
		this.email = email;
	}
	
	

	@Override
	public void onCompletion() {		
	}

	@Override
	public void onResponse(Reader response, InputStream responseStream,
			String contentType, String contentEncoding, long length,
			Header[] headers) throws IOException {
		
	}



	@Override
	public String getUri() {
		// TODO Auto-generated method stub
		return null;
	}

}
