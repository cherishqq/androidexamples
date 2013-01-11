package com.android.rest;

public interface RestVocabulary {
	
	public static final String ID = "id";
	public static final String URI = "uri";
	
	public static final String PICTURELIST = "pictureList";
	
	public interface PictureKey {
		public static final String TITLE = "title";
		public static final String DESCRIPTION = "description";
		public static final String fileExt = "fileExt";
		public static final String createdate = "createdate";
		public static final String restAttachmentUri = "restAttachmentUri";
		public static final String userId = "userId";
			
	}
	
	public interface RestResultKey {
		
		public static final String SUCCESS = "success";
		public static final String errorCode = "errorCode";
		public static final String message = "message";
		public static final String object = "object";
		
	}
	
}
