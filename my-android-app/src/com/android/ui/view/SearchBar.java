package com.android.ui.view;

import com.android.store.db.provider.DBContentProvider;
import com.android.store.db.provider.DataStore;
import com.android.store.db.provider.UriHelper;
import com.android.R;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchBar  extends LinearLayout {
	
	private String tag = "SearchBar";
	private Context mContext;
	private EditText searchView;
	
	private CursorAdapter curAdapter;
	
	private int QUERY_TOKEN = 1;
	
	private AsyncQueryHandler queryHandler;
	
	
	
	public CursorAdapter getCursorAdapter() {
		return curAdapter;
	}

	
	/**
	 * must invoke this method , when want to filter
	 * @param curAdapter
	 */
	public void setCursorAdapter(CursorAdapter curAdapter) {
		this.curAdapter = curAdapter;
	}


	private String tip;  // 提示信息

	public SearchBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public SearchBar(Context context){
		super(context);
		init(context);
	}
	
	public void  init(Context context){
		 this.mContext = context;		 
		 LinearLayout searchBarView = (LinearLayout)inflate(context, R.layout.search_bar, this);	
		 searchView = (EditText)searchBarView.findViewById(R.id.search_bar_query);
		 searchView.addTextChangedListener(new SearchViewTextWatcher());
		 
		 queryHandler = new UserQueryHandler(context);
		
	}


	class SearchViewTextWatcher implements TextWatcher{

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			Log.d(tag,"beforeTextChanged:" + s + "start:"+ start + "count:"+ count + "after:" + after );
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
			
			
			Log.d(tag,"onTextChanged:" + s + "start:"+ start + "before:"+ before+ "count:"+ count );
		}

		@Override
		public void afterTextChanged(Editable s) {
			Log.d(tag,"afterTextChanged:" + s  );
			if(curAdapter != null){
				curAdapter.getFilter().filter(s);
				curAdapter.setFilterQueryProvider(new UserFilterQuery());
				curAdapter.notifyDataSetChanged();
			} else {
				Log.e(tag, "you need set cursorAdapter before filter");
				throw  new NullPointerException();
			}
		}
		
		
		
	}
	
	class UserFilterQuery implements FilterQueryProvider{

		@Override
		public Cursor runQuery(CharSequence constraint) {
			
			 Cursor cursor = null;
			 queryHandler.startQuery(QUERY_TOKEN, null, UriHelper.getUri(DBContentProvider.USER), 
					new String []{DataStore.UserTable._ID,
				DataStore.UserTable.USER_NAME,
					DataStore.UserTable.USER_PASSWORD}, null,null,null);
			 
			
			return cursor;
			
			
		}
		
	}
	
	class UserQueryHandler extends AsyncQueryHandler {

		private Cursor cursor;
		
		public UserQueryHandler(Context context) {
			super(context.getContentResolver());
		}
		
		
		@Override
		public void startQuery(int token, Object cookie, Uri uri,
				String[] projection, String selection, String[] selectionArgs,
				String orderBy) {
			
			
			super.startQuery(token, cookie, uri, projection, selection, selectionArgs,
					orderBy);
		}
		
		@Override
		protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
			if( cursor == null || cursor.isClosed()){
				return;
			}
			curAdapter.changeCursor(cursor);
			
			this.cursor = cursor;
		}
		
	}
	
	
	

	

}
