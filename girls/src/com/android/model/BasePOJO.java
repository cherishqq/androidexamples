package com.android.model;

import java.io.IOException;
import java.util.List;

import com.android.rest.RestVocabulary;
import com.google.agson.stream.JsonReader;

public abstract class BasePOJO <T extends BasePOJO>{
	
	
	public abstract T parse(JsonReader reader) throws IOException;

}
