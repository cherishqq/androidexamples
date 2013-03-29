package com.android.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.os.Parcel;
import android.util.Log;

import com.android.rest.RestVocabulary;
import com.android.store.db.provider.DataStore.PhotoTable;
import com.google.agson.stream.JsonReader;


public class Photo extends BasePOJO{
	
	private int id;
	private String title;
	private String description;
	private String filePath;
	private String fileExt;
	private Date createDate;
	private String restAttachmentUri;
	private Long userId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRestAttachmentUri() {
		return restAttachmentUri;
	}
	public void setRestAttachmentUri(String restAttachmentUri) {
		this.restAttachmentUri = restAttachmentUri;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	private ArrayList<Photo> mPictureList = new ArrayList<Photo>();
	
	/**
	 * 这里要用表的字段...so 
	 * must use table field
	 * @param p
	 */
	
	public ContentValues fillContentValues(){
		ContentValues c = new ContentValues();
		c.put(PhotoTable.TITLE, getTitle());
		c.put(PhotoTable.description, getDescription());
		c.put(PhotoTable.filePath, getFilePath());
		c.put(PhotoTable.restAttachmentUri, getFilePath());
		return c;
	}
	
	
	/**
	 *  
	 * @param reader
	 * @return
	 * @throws IOException
	 * <PictureList>
	 * 	<Picture>
	 * 	  <id>1122</id>
	 *    <title>测试</id>
	 *    <title>
	 *  </Picture>
	 * </PictureList>
	 *  
	 */
/*	
	public List<Photo> parsePicturList(JsonReader reader) throws IOException{
		
		reader.beginObject();
		String name;
		while(reader.hasNext()) {
			name = reader.nextName();
			if( name.equalsIgnoreCase(RestVocabulary.PICTURELIST)){
				reader.beginArray();
				while(reader.hasNext()){
					mPictureList.add(new Photo().parse(reader));
				}
				reader.endObject();
				
			}
			
		}
		reader.endObject();
		return mPictureList;
	}*/
	
	
	
	@Override
	public Photo parse(JsonReader reader) throws IOException{
		reader.beginObject();
		String name;
		while (reader.hasNext()){			
			name = reader.nextName();		
			if( name.equalsIgnoreCase(RestVocabulary.ID)){
				id = reader.nextInt();
			} else if ( name.equalsIgnoreCase(RestVocabulary.PictureKey.TITLE)){
				title = reader.nextString();
			} else if (name.equalsIgnoreCase(RestVocabulary.PictureKey.DESCRIPTION)){
				description = reader.nextString();
			} else if (name.equalsIgnoreCase(RestVocabulary.PictureKey.restAttachmentUri)){
				restAttachmentUri = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		
		return this;
	}
	
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("id:").append(id).append(";")
		.append("title:").append(title).append(";")
		.append("description:").append(description).append(";")
		.append("fileExt:").append(fileExt).append(";")
		.append("createDate:").append(createDate).append(";")
		.append("restAttachmentUri:").append(restAttachmentUri).append(";")
		.append("userId:").append(userId);
		return b.toString();
	}


	 
}
