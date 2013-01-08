package com.derek.rest.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.http.Header;

import com.derek.common.Constants;
import com.derek.model.ListBasePOJO;
import com.derek.model.User;
import com.derek.rest.RestRequest;



public class UserListRestRequest extends RestRequest<ListBasePOJO<User>>{
	
	
	
	private static final HttpMethod HTTP_METHOD = RestRequest.HttpMethod.GET;
	private static final String REQUEST_URL_PATH_TEMPLATE = Constants.REST_REQUEST_PATH_MAIN + "user/show";
	
	private String mRequestUrlPath;
    private ListBasePOJO<User>  uList = new ListBasePOJO<User>();

	public UserListRestRequest(HttpMethod method) {
		super(method);
		// TODO Auto-generated constructor stub
	}
	
	public UserListRestRequest(String id){
		super(HTTP_METHOD);
		
	}
	
	
	

	@Override
	public void onCompletion() {		
	}

	@Override
	public ListBasePOJO<User> onResponse(Reader response, InputStream responseStream,
			String contentType, String contentEncoding, long length,
			Header[] headers) throws IOException {
			return null;
	}



	@Override
	public String getUri() {
		// TODO Auto-generated method stub
		return null;
	}

}
