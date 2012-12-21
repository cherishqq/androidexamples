package com.android.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.http.Header;

public class PhotoAttachmentRestRequest extends RestRequest{

	public PhotoAttachmentRestRequest(HttpMethod method) {
		super(method);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCompletion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResponse(Reader response, InputStream responseStream,
			String contentType, String contentEncoding, long length,
			Header[] headers) throws IOException {
		// TODO Auto-generated method stub
		
	}


}
