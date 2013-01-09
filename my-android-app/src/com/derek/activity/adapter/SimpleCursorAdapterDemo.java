package com.derek.activity.adapter;

import com.derek.R;
import com.derek.store.db.provider.DBContentProvider;
import com.derek.store.db.provider.DataStore;
import com.derek.store.db.provider.UriHelper;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class SimpleCursorAdapterDemo  extends ListActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Cursor cursor  = getContentResolver().query(UriHelper.getUri(DBContentProvider.USER), 
				new String []{DataStore.UserTable._ID,
			DataStore.UserTable.USER_NAME,
				DataStore.UserTable.USER_PASSWORD},
				null, null,null);
		
		
		
		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
							this,R.layout.simplecursoradapter,
							cursor,
							new String[]{DataStore.UserTable.USER_NAME},
							new int[]{R.id.name});
		
		
		setListAdapter(simpleCursorAdapter);
		
		
	}

}
