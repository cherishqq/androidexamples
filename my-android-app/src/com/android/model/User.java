package com.android.model;

import java.io.IOException;

import com.android.common.Constants.Gender;
import com.google.agson.stream.JsonReader;

public class User extends BasePOJO<User>{
	
	private String mEmail;
	private String mId;
	private String name;
	private int sex;
	private String photoUri;
	private Gender mGender;
	private String password;
	

	/**
	 * 
	 */
	public User() {
		super();
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getmEmail() {
		return mEmail;
	}




	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}




	public String getmId() {
		return mId;
	}




	public void setmId(String mId) {
		this.mId = mId;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public int getSex() {
		return sex;
	}




	public void setSex(int sex) {
		this.sex = sex;
	}




	public String getPhotoUri() {
		return photoUri;
	}




	public void setPhotoUri(String photoUri) {
		this.photoUri = photoUri;
	}




	public Gender getmGender() {
		return mGender;
	}




	public void setmGender(Gender mGender) {
		this.mGender = mGender;
	}




	@Override
	public User parse(JsonReader reader) throws IOException {
		return null;
	}

}
