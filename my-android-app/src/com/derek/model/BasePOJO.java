package com.derek.model;

import java.io.IOException;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.agson.stream.JsonReader;

public abstract class BasePOJO <T extends BasePOJO> implements Parcelable{
	
	
	public abstract T parse(JsonReader reader) throws IOException;
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
	}

}
