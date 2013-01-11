package com.android.rest;

import com.android.model.User;
import com.android.network.HttpClientHelp;
import com.android.rest.request.UserRestRequest;

public class HttpApi {
	
	
	public void register(){
		
	}
	
	public void login(){
		
	}
	
	public void getFriends(){
	}
	
	public User getUser(String uid){
		
		UserRestRequest request = new UserRestRequest(uid);
		
		HttpClientHelp.sendRequest(request);
		
		
		return null;
	}

}
