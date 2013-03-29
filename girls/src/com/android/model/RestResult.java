package com.android.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.os.Parcel;
import android.util.Log;

import com.android.rest.RestErrorCodes;
import com.android.rest.RestVocabulary;
import com.google.agson.JsonObject;
import com.google.agson.stream.JsonReader;

public class RestResult<T extends BasePOJO> extends BasePOJO {
	
	private String tag = "RestResult";
	
	private boolean success = true;

	private int errorCode = RestErrorCodes.OK;
	
	private String message;
	
	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}



	private List<BasePOJO> mList = new ArrayList<BasePOJO>();
	
	public List<BasePOJO> getmList() {
		return mList;
	}

	public void setmList(List<BasePOJO> mList) {
		this.mList = mList;
	}



	private T object;
		
	public RestResult(T object) {
		super();
		this.object = object;
	}



	// if the field return null, it will throws Exception.so not 
	public RestResult parse(JsonReader reader) throws IOException{
		
		
		reader.beginObject();
		String name;
		
		while (reader.hasNext()){
			
			name = reader.nextName();
			
			if( name.equalsIgnoreCase(RestVocabulary.RestResultKey.SUCCESS)){
				success = reader.nextBoolean();
			} else if ( name.equalsIgnoreCase(RestVocabulary.RestResultKey.errorCode)){
				errorCode = reader.nextInt();
			} else if (name.equalsIgnoreCase(RestVocabulary.RestResultKey.message)){
				message = reader.nextString();
			} else if (name.equalsIgnoreCase(RestVocabulary.RestResultKey.object)){
				reader.beginArray();
				while(reader.hasNext()){
					if( object == null) {
						Log.e(tag, "Exception: object is null ");
						return null;
					}
					mList.add(object.parse(reader));
				}
				reader.endArray();
			}
			
		}
		reader.endObject();
		System.out.println(this.toString());
		return this;
	}
	
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("success:").append(success).append(",")
		 .append("errorCode:").append(errorCode).append(",")
		 .append("message:").append(message).append(",")
		 .append("mList:").append(mList.toString());
		return b.toString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
	
	

}
