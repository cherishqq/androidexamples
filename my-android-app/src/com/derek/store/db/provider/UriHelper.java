package com.derek.store.db.provider;

import java.net.URLEncoder;

import com.derek.store.log.EngLog;

import android.net.Uri;

public class UriHelper {

	private static String TAG = "UriHelper";

	public static Uri getUri(String path) {

		EngLog.d(TAG, "getUri(" + path + ')');

		Uri uri = prepare(path).build();

		EngLog.d(TAG, "uri: " + uri);

		return uri;
	}

	public static Uri getMessageUri(String path) {
		return new Uri.Builder().scheme("content")
				.authority(DBContentProvider.AUTHORITY).path(path).query("")
				.fragment("").build();
	}

	public static Uri getMessageUri(String path, long item_id) {
		return new Uri.Builder().scheme("content")
				.authority(DBContentProvider.AUTHORITY).path(path).query("")
				.fragment("").appendPath(String.valueOf(item_id)).build();
	}

	public static Uri getMessageUri(String path, String pathSegment) {
		return new Uri.Builder().scheme("content")
				.authority(DBContentProvider.AUTHORITY).path(path).query("")
				.fragment("").appendPath(URLEncoder.encode(pathSegment))
				.build();
	}

	public static Uri getUri(String path, long mailbox_id) {
		EngLog.d(TAG, "getUri(" + path + ", " + mailbox_id + ')');

		Uri uri = prepare(path, mailbox_id).build();

		EngLog.d(TAG, "uri: " + uri);

		return uri;
	}

	static Uri removeQuery(Uri uri) {
		EngLog.d(TAG, "removeQuery(" + uri + ")");

		Uri newUri = uri.buildUpon().query("").build();

		EngLog.d(TAG, "new uri: " + newUri);

		return newUri;
	}

	private static Uri.Builder prepare(String path) {
		return new Uri.Builder().scheme("content")
				.authority(DBContentProvider.AUTHORITY).path(path).query("") // This
																				// is
																				// a
																				// workaround
																				// for
																				// Android
																				// 1.5
																				// bug:
				.fragment(""); // NullPointerException at
								// android.net.Uri$HierarchicalUri.writeToParcel(Uri.java:1117,1118)
								// (called during ContentProvider.notifyChange()
								// execution)

	}

	private static Uri.Builder prepare(String path, long userId) {
		return prepare(path).appendQueryParameter(DataStore.RCMColumns.USER_ID,
				String.valueOf(userId));
	}

}
