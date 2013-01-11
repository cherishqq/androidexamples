package com.android.activity.listview;

import com.android.store.db.provider.DBContentProvider;
import com.android.store.db.provider.DataStore;
import com.android.store.db.provider.UriHelper;
import com.android.store.db.provider.DataStore.UserTable;
import com.android.ui.view.SearchBar;
import com.android.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class SearchListViewActivity  extends ListActivity{
	
	private SearchTask searchTask;
	
	private CursorAdapter cursorAdapter;
	private SearchBar searchBar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		 
		 setContentView(R.layout.usersearch);
		 searchBar = (SearchBar)findViewById(R.id.searchBar);
		 
		 
		 Cursor cursor  = getContentResolver().query(UriHelper.getUri(DBContentProvider.USER), 
					new String []{DataStore.UserTable._ID,
				DataStore.UserTable.USER_NAME,
					DataStore.UserTable.USER_PASSWORD},
					null, null,null);
		 
		 cursorAdapter = new UserCursorAdapter(this,R.layout.cursoradapter_item,cursor);
		 
		 setListAdapter(cursorAdapter);
		 searchBar.setCursorAdapter(cursorAdapter);
		 
		
		searchTask = new SearchTask();
	}
	
	
	class SearchTask extends AsyncTask<String,String,String>{

		@Override
		protected void onPreExecute() {
			
			setProgressBarVisibility(true);
			super.onPreExecute();
		}
		
		
		@Override
		protected String doInBackground(String... params) {
			return null;
		}
	
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}
		
		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
		}
		
	}
	
	class UserCursorAdapter extends ResourceCursorAdapter{

		public UserCursorAdapter(Context context, int layout, Cursor c) {
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
