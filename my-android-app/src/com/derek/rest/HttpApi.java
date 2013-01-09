package com.derek.rest;

import com.derek.model.User;
import com.derek.network.HttpClientHelp;
import com.derek.rest.request.UserRestRequest;

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
