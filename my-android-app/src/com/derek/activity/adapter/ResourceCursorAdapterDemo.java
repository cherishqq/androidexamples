package com.derek.activity.adapter;

import com.derek.R;
import com.derek.store.db.provider.DBContentProvider;
import com.derek.store.db.provider.DataStore;
import com.derek.store.db.provider.UriHelper;
import com.derek.store.db.provider.DataStore.UserTable;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class ResourceCursorAdapterDemo extends ListActivity{
	
	private MyResourceCursorAdapter resourceAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cursoradapter);
		
		Cursor cursor  = getContentResolver().query(UriHelper.getUri(DBContentProvider.USER), 
				new String []{DataStore.UserTable._ID,
			DataStore.UserTable.USER_NAME,
				DataStore.UserTable.USER_PASSWORD},
				null, null,null);
		
		
		resourceAdapter = new MyResourceCursorAdapter(this,R.layout.cursoradapter_item,cursor);
		setListAdapter(resourceAdapter);
		
		
	}
	
	
	
	class MyResourceCursorAdapter extends ResourceCursorAdapter{

		public MyResourceCursorAdapter(Context context, int layout, Cursor c) {
			super(context, layout, c);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			LinearLayout layout = (LinearLayout)view;
			TextView nameView = (TextView)layout.findViewById(R.id.name);
			TextView psdView = (TextView)layout.findViewById(R.id.password);
			nameView.setText(cursor.getString(cursor.getColumnIndex(UserTable.USER_NAME)));
			psdView.setText(cursor.getString(cursor.getColumnIndex(UserTable.USER_PASSWORD)));
		}
		
	}

}
