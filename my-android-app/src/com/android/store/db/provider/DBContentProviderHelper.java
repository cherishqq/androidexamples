package com.android.store.db.provider;

import com.android.model.User;
import com.android.store.log.EngLog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DBContentProviderHelper {
	
    private static final String TAG = "DBContentProviderHelper";
    
    
    public static void addUser(Context context,User u){
        ContentValues values = new ContentValues();
/*        values.put(DataStore.UserTable.USER_NAME, "derek");
        values.put(DataStore.RCMColumns.USER_ID,1);
        values.put(DataStore.UserTable.USER_PASSWORD, "derek");*/
        
        values.put(DataStore.UserTable.USER_NAME, u.getName());
        values.put(DataStore.RCMColumns.USER_ID, 1);
        values.put(DataStore.UserTable.USER_PASSWORD, u.getPassword());
        
        context.getContentResolver().insert(UriHelper.getUri(DBContentProvider.USER), values);
  //  	insertSingleValue(context,DBContentProvider.USER,DataStore.UserTable.USER_NAME,"derek");
    }
    
    public static void getAllUser(){
    	
    	
    }

    public static String simpleQueryByUserId(Context context, long userId, String uri_path, String column) {
        Uri uri = UriHelper.getUri(uri_path, userId);
        return simpleQuery(context, uri, column, null);
    }
    
    public static String simpleQuery(Context context, String uri_path, String column ) {
        Uri uri = UriHelper.getUri(uri_path);
        return simpleQuery(context, uri, column, null);
    }

    private static String simpleQuery(Context context, Uri uri, String column, String selection) {
    	if (context == null) {
    		return "";    		
    	}
    	
        Cursor cursor = context.getContentResolver().query(uri, new String[]{column}, selection, null, null);
        if (cursor == null) {
                EngLog.d(TAG, "simpleQuery(): null cursor received; return \"\"");
            return "";
        }

        if (!cursor.moveToFirst()) {
                EngLog.d(TAG, "simpleQuery(): empty cursor received; return \"\", count: " + cursor.getCount());
            cursor.close();
            return "";
        }

        String result = cursor.getString(0);
        if (result == null) {
                EngLog.d(TAG, "simpleQuery(): cursor returned null; return \"\"");
            result = "";
        }

        cursor.close();
        return result;
    }
    
    public static int updateSingleValue(Context context, String uri_path, String column, String value, String where) {
        Uri uri = UriHelper.getUri(uri_path);
        ContentValues values = new ContentValues();
        values.put(column, value);
        return context.getContentResolver().update(uri, values, where, null);   //returns the number of updated rows
    }
    
    private static void insertSingleValue(Context context, String uri_path, String column, String value) {
        ContentValues values = new ContentValues();
        values.put(column, value);
        context.getContentResolver().insert(UriHelper.getUri(uri_path), values);
    }
    
    private static int getRecordsCount(Context context, String uri_path, String selection) {
        Cursor cursor = context.getContentResolver().query(UriHelper.getUri(uri_path ),
                new String[] {BaseColumns._ID}, selection, null, null);
        int count = (null != cursor ? cursor.getCount() : 0);
        if(null != cursor) {
            cursor.close();
        }
        return count;
    }
}
