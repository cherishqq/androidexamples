package com.derek.rest.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.http.Header;

import com.derek.common.Constants;
import com.derek.model.RestResult;
import com.derek.model.User;
import com.derek.rest.RestRequest;
import com.derek.rest.RestRequest.HttpMethod;
import com.google.agson.stream.JsonReader;




public class UserRestRequest extends RestRequest<User>{
	
	
	
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
	public User onResponse(Reader response, InputStream responseStream,
			String contentType, String contentEncoding, long length,
			Header[] headers) throws IOException {
		response = read(response);
		JsonReader reader = new JsonReader(response);
		
		result = new RestResult(new User());
		result = result.parse(reader);
		return null;
		
	}
	




	@Override
	public String getUri() {
		// TODO Auto-generated method stub
		return null;
	}

}
