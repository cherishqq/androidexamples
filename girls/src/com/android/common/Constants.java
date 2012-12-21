package com.android.common;

public class Constants {

	// 10.32.50.101  不能使用 192.168.56.1
//	public static final String REST_REQUEST_PATH_MAIN = "http://192.168.56.1:8080/javaweb/";
	public static final String REST_REQUEST_PATH_MAIN = "http://10.32.50.101:8080/javaweb/";

	
	public static final String SYNC_COMMAND = "sync_command";
	public static final int SYNC_PICTURE_TAG = 1;
	
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String COOKIE = "Cookie";
    public static final String JSON_CONTENT_TYPE       = "application/json";
    public static final String PLAIN_TEXT_CONTENT_TYPE = "text/plain";
    public static final String XML_TEXT_CONTENT_TYPE   = "text/xml";
    public static final String XML_APP_CONTENT_TYPE    = "application/xml";
    
    
    public static final String PHOTO_LOADING_RESULT = "photo_loading_result";
    public static final int STATE_FAILED = 1;
    public static final int STATE_COMPLETED = 0;
    public static final int STATE_LOADING = -1;
    
    
    public static final String ACTION_SYNC_PHOTO_LIST = "com.android.intent.action.sync_photo_list";
    public static final String ACTION_SYNC_PHOTO_ATTACHMENT = "com.android.intent.action.sync_photo_list";

}
