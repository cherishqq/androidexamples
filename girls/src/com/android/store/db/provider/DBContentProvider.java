/** 
 * Copyright (C) 2010-2012, RingCentral, Inc. 
 * All Rights Reserved.
 */
package com.android.store.db.provider;

import com.android.log.EngLog;
import com.android.store.db.provider.DataStore.RCMColumns;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

public class DBContentProvider extends ContentProvider {

	private static final boolean DEBUG = true;

	static final boolean DEBUG_ENBL = true; // LogSettings.ENGINEERING && true;

	static final String TAG = "DBContentProvider";

	/* URI authority string */
	public static final String AUTHORITY = "com.derek.provider.dbprovider";

	/**
	 * 此处定义的是uri,不是表名,可以任意的,主要要对应
	 */
	/* URI paths names */
	public static final String USER = "user";
	
	
	public static final String PHOTO_PATH = "photo";
	
	/* UriMatcher codes */
	private static final int USER_MATCH = 11;
	private static final int PHOTO_MATCH = 12;

	private static final UriMatcher sUriMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);

	static {
		sUriMatcher.addURI(AUTHORITY, USER, USER_MATCH);
		sUriMatcher.addURI(AUTHORITY,PHOTO_PATH, PHOTO_MATCH);
	}

	private DbHelper dbHelper;

	@Override
	public boolean onCreate() {

		dbHelper = DbHelper.getInstance(getContext());
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		EngLog.d(TAG, "query(" + uri + ",...)");

		int match = sUriMatcher.match(uri);
		if (match == UriMatcher.NO_MATCH) {
			EngLog.e(TAG, "query(): Wrong URI");
			throw new IllegalArgumentException("Wrong URI: " + uri);
		}

		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		int where_append_count = 0;

		qb.setTables(tableName(match));
		// if have a unique id
		if (uriWithID(match)) {
			qb.appendWhere((where_append_count++ == 0 ? "" : " AND ")
					+ (BaseColumns._ID + "=" + uri.getLastPathSegment()));
		}

		if (projection == null) {
			projection = defaultProjection(match);
		}

		if (TextUtils.isEmpty(sortOrder)) {
			sortOrder = RCMColumns.DEFAULT_SORT_ORDER;
		}

		SQLiteDatabase db;
		try {
			db = dbHelper.getReadableDatabase();
		} catch (SQLiteException e) {
			EngLog.e(TAG, "query(): Error opening readable database", e);
			throw e;
		}

		Cursor cursor;
		synchronized (dbHelper) {
			try {
				cursor = qb.query(db, projection, selection, selectionArgs,
						null, null, sortOrder);
			} catch (Throwable e) {
				EngLog.e(TAG, "query(): Exception at db query", e);
				throw new RuntimeException("Exception at db query: "
						+ e.getMessage());
			}
		}

		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		if (cursor == null) {
			EngLog.e(TAG, "query(): null cursor returned from db query");
		}

		EngLog.d(TAG, "query(): Cursor has " + cursor.getCount() + " rows");
		return cursor;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if (DBContentProvider.DEBUG_ENBL) {
			EngLog.d(TAG, "insert(" + uri + ", ...)");
		}

		int match = sUriMatcher.match(uri);

		if (match == UriMatcher.NO_MATCH) {
			EngLog.e(TAG, "insert(): Wrong URI: " + uri);
			throw new IllegalArgumentException("Wrong URI: " + uri);
		}

		if (uriWithID(match)) {
			EngLog.e(TAG, "insert(): Insert not allowed for this URI: " + uri);
			throw new IllegalArgumentException(
					"Insert not allowed for this URI: " + uri);
		}

		SQLiteDatabase db;
		long rowId;

		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			// TODO Implement proper error handling
			EngLog.e(TAG, "insert(): Error opening writeable database", e);

			throw e;
		}

		synchronized (dbHelper) {
			try {
				rowId = db.insert(tableName(match), null, values);
			} catch (SQLException e) {
				// TODO Implement proper error handling
				EngLog.e(TAG, "Insert() failed", e);
				throw e;
			}
		}

		if (rowId <= 0) {
			EngLog.e(TAG, "insert(): Error: insert() returned " + rowId);
			throw new RuntimeException("DB insert failed");
		}

		uri = ContentUris.withAppendedId(UriHelper.removeQuery(uri), rowId);
		if (DBContentProvider.DEBUG_ENBL) {
			EngLog.d(TAG, "insert(): new uri with rowId: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);

		if (DBContentProvider.DEBUG_ENBL) {
			EngLog.d(TAG, "insert(): return " + uri);
		}
		return uri;
	}

	@Override
	public int bulkInsert(Uri uri, ContentValues[] values) {
		if (DBContentProvider.DEBUG_ENBL) {
			EngLog.d(TAG, "bulkInsert(" + uri + ", ...)");
		}
		int match = sUriMatcher.match(uri);

		if (match == UriMatcher.NO_MATCH) {
			EngLog.e(TAG, "bulkInsert(): Wrong URI: " + uri);
			throw new IllegalArgumentException("Wrong URI: " + uri);
		}

		if (uriWithID(match)) {
			EngLog.e(TAG, "bulkInsert(): Insert not allowed for this URI: "
					+ uri);
			throw new IllegalArgumentException(
					"Insert not allowed for this URI: " + uri);
		}

		SQLiteDatabase db;
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			// TODO Implement proper error handling
			EngLog.e(TAG, "bulkInsert(): Error opening writable database", e);
			throw e;
		}

		int added = 0;
		long rowId = 0;
		String table = tableName(match);

		synchronized (dbHelper) {
			try {
				db.beginTransaction();
				for (int i = 0; i < values.length; i++) {
					try {
						rowId = db.insert(table, null, values[i]);
					} catch (SQLException e) {
						EngLog.e(TAG, "bulkInsert() P1", e);
						continue;
					}

					if (rowId <= 0) {
						EngLog.e(TAG, "bulkInsert() P2: " + rowId);
						continue;
					}
					added = added + 1;
				}
				db.setTransactionSuccessful();
			} catch (SQLException e) {
				EngLog.e(TAG, "bulkInsert() P3", e);
				throw new RuntimeException("bulkInsert(): DB insert failed: "
						+ e.getMessage());
			} finally {
				db.endTransaction();
			}
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return added;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		if (DBContentProvider.DEBUG_ENBL) {
			EngLog.d(TAG, "update(" + uri + ", ...)");
		}
		int match = sUriMatcher.match(uri);
		if (match == UriMatcher.NO_MATCH) {
			EngLog.e(TAG, "update(): Wrong URI: " + uri);
			throw new IllegalArgumentException("Wrong URI: " + uri);
		}

		if (uriWithID(match)) {
			selection = BaseColumns._ID
					+ "="
					+ uri.getLastPathSegment()
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
		}

		String userId = uri.getQueryParameter(RCMColumns.USER_ID);
		if (!TextUtils.isEmpty(userId)) {
			selection = RCMColumns.USER_ID
					+ "="
					+ userId
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
		}

		SQLiteDatabase db;
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			EngLog.e(TAG, "update(): Error opening writable database", e);
			throw e;
		}

		int count;
		synchronized (dbHelper) {
			try {
				count = db.update(tableName(match), values, selection,
						selectionArgs);
			} catch (SQLException e) {
				// TODO Implement proper error handling
				EngLog.e(TAG, "update() failed", e);

				throw e;
			}
		}

		if (DBContentProvider.DEBUG_ENBL) {
			EngLog.d(TAG, "update(): " + count + " rows updated");
		}

		if (count > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return count;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		if (DBContentProvider.DEBUG_ENBL) {
			EngLog.d(TAG, "delete(" + uri + ", ...)");
		}
		int match = sUriMatcher.match(uri);
		if (match == UriMatcher.NO_MATCH) {
			EngLog.e(TAG, "delete(): Wrong URI: " + uri);
			throw new IllegalArgumentException("Wrong URI: " + uri);
		}

		if (uriDerived(match)) {
			EngLog.e(TAG, "delete(): Row delete not allowed for this URI: "
					+ uri);
			throw new IllegalArgumentException(
					"Row delete not allowed for this URI: " + uri);
		}

		if (uriWithID(match)) {
			selection = BaseColumns._ID
					+ "="
					+ uri.getLastPathSegment()
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
		}

		String mailbox_id_string = uri.getQueryParameter(RCMColumns.USER_ID);
		if (!TextUtils.isEmpty(mailbox_id_string)) {
			selection = RCMColumns.USER_ID
					+ "="
					+ mailbox_id_string
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
		}

		SQLiteDatabase db;
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			EngLog.e(TAG, "delete(): Error opening writable database", e);
			throw e;
		}

		int count;
		synchronized (dbHelper) {
			try {
				count = db.delete(tableName(match), selection, selectionArgs);
			} catch (SQLException e) {
				EngLog.e(TAG, "delete(): DB rows delete error", e);
				throw e;
			}
		}

		if (DBContentProvider.DEBUG_ENBL)
			EngLog.d(TAG, "delete(): " + count + " rows deleted");

		if (count > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return count;
	}

	@Override
	public String getType(Uri uri) {
		if (DBContentProvider.DEBUG_ENBL) {
			EngLog.d(TAG, "getType(" + uri + ')');
		}

		return null;
	}

	private boolean uriWithID(int uri_match) {
		switch (uri_match) {

		default:
			return false;
		}
	}

	/**
	 * view // no actual table
	 * 
	 * @param uri_match
	 * @return
	 */

	private boolean uriDerived(int uri_match) {
		switch (uri_match) {

		default:
			return false;
		}
	}

	private boolean userIdRequired(int uri_match) {
		if (uriWithID(uri_match)) {
			return false;
		}
		switch (uri_match) {
		default:
			return true;
		}
	}

	private String tableName(int uri_match) {
		switch (uri_match) {

		case USER_MATCH:
			return DataStore.UserTable.getInstance().getName();
		
		case PHOTO_MATCH:
			return DataStore.PhotoTable.getInstance().getName();

		default:
			throw new Error(TAG + " No table defined for #" + uri_match);
		}
	}

	private static final String[] UserInfoProjection = {
			DataStore.RCMColumns.USER_ID, DataStore.UserTable.USER_NAME,
			DataStore.UserTable.USER_PASSWORD };
	
	private static final String[] PhotoProjection = {
			DataStore.PhotoTable.TITLE, DataStore.PhotoTable.description,
			DataStore.PhotoTable.filePath, DataStore.PhotoTable.fileExt,
			DataStore.PhotoTable.restAttachmentUri, DataStore.PhotoTable.userId
	};

	private String[] defaultProjection(int uri_matcher) {
		switch (uri_matcher) {

		case USER_MATCH:
			return UserInfoProjection;
			
		case PHOTO_MATCH:
			return PhotoProjection;

		default:
			return null;
		}
	}



}
