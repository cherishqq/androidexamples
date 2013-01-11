package com.android.model;

import java.io.IOException;
import java.util.List;

import com.google.agson.stream.JsonReader;

public class ListBasePOJO<T extends BasePOJO>  extends BasePOJO{

	
	private List<T> list;
	
	
	@Override
	public T parse(JsonReader reader) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<T> getList() {
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	
	
	
	

}
