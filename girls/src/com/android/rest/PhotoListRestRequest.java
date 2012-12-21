package com.android.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpMessage;

import android.content.ContentValues;
import android.content.Context;

import com.android.common.Constants;
import com.android.context.GirlApp;
import com.android.model.BasePOJO;
import com.android.model.Photo;
import com.android.model.RestResult;
import com.android.rest.RestRequest.HttpMethod;
import com.android.store.db.provider.DBContentProvider;
import com.android.store.db.provider.UriHelper;
import com.google.agson.stream.JsonReader;

public class PhotoListRestRequest extends RestRequest {
	
	private static final HttpMethod HTTP_METHOD = RestRequest.HttpMethod.GET;
	
	/**
	private static final String REQUEST_URL_PATH_TEMPLATE = Constants.REST_REQUEST_PATH_MAIN + 
			"picture?syncType=FSync&recordCount=%d&dateFrom=2011-12-01";
	
	private static final String REQUEST_URL_PATH_TEMPLATE_PART = Constants.REST_REQUEST_PATH_MAIN + 
			"picture?syncType=ISync&syncToken=%s&recordCount=%s";
	*/
	
	private static final String REQUEST_URL_PATH_TEMPLATE = Constants.REST_REQUEST_PATH_MAIN + 
			"photo/getPictureList?recordCount=%d&pictureId=%d";
	
	private String mRequestUrlPath;
	private int pageSize;
	
	private List<Photo> mPictureList;
 	
	private RestResult result;
	
	
	
	
	// REQUEST_URL_PATH_TEMPLATE /picture/
	
	

	public PhotoListRestRequest(HttpMethod method) {
		super(method);
	}
	
	/**
	 *  可能是向前取或者像后取
	 */
	public PhotoListRestRequest(int recordCount,long pictureId){
		
		super(HTTP_METHOD);
		mRequestUrlPath = String.format(REQUEST_URL_PATH_TEMPLATE,pageSize,pictureId);
	}

	@Override
	public void onCompletion() {
		
		//todo 还需要做存储到 数据库 中
		 Context context = null;
		 ArrayList<ContentValues> new_photos = new ArrayList<ContentValues>();
		 List<BasePOJO> list = result.getmList();
		 List<ContentValues> listToAdd = new ArrayList<ContentValues>();
		 for(BasePOJO b : list){
			 Photo p = (Photo)b;
			 ContentValues c = p.fillContentValues();
			 listToAdd.add(c);
		 }
		 list.toArray();
		 
		 GirlApp.getGrilApplicationContext().getContentResolver().bulkInsert(UriHelper.getUri(DBContentProvider.PHOTO_PATH),listToAdd.toArray(new ContentValues[listToAdd.size()]));
		
	}

	@Override
	public void onResponse(Reader response, InputStream responseStream,
			String contentType, String contentEncoding, long length,
			Header[] headers) throws IOException {
		response = read(response);
		JsonReader reader = new JsonReader(response);
		
		result = new RestResult(new Photo());
		result = result.parse(reader);
//		mPictureList = new Photo().parsePicturList(reader);
		
	}
	
	

	@Override
	public String getUri() {
		return mRequestUrlPath;
	}

	@Override
	public void onHeaderForming(HttpMessage request) {
		super.onHeaderForming(request);
		request.addHeader(Constants.CONTENT_TYPE ,Constants.JSON_CONTENT_TYPE);
	}


}
