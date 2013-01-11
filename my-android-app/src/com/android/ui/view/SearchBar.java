package com.android.ui.view;

import com.android.store.db.provider.DBContentProvider;
import com.android.store.db.provider.DataStore;
import com.android.store.db.provider.UriHelper;
import com.android.R;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
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



/**
 * 
 * @author Derek.pan
 * 这里有个问题,就是数据库查询是通过异步查询的,但是被封装的方法里面又要求立即返回..不知道怎么处理
 * adapter 已经决定了列表中的要展现的。。可以增加模糊查询
 */

public class SearchBar  extends LinearLayout {
	
	private String tag = "SearchBar";
	private Context mContext;
	private EditText searchView;
	
	private CursorAdapter curAdapter;
	
	private int QUERY_TOKEN = 1;
	
	private UserQueryHandler queryHandler;
	
	private String tip;  // 提示信息
	
	private Uri uri;
	private String [] queryCondition;
	
	/**
	 * 可能存在着复杂的查询,故不做统一的封装,不过,可以抽象出一些公共方法
	 */
	private FilterQuery mFilterQuery;
 	
	
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


	public SearchBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
		setCustomAttrs(context,attrs);
	}
	
	public SearchBar(Context context){
		super(context);
		init(context);
	}
	
	
	
	public FilterQuery getFilterQuery() {
		return mFilterQuery;
	}


	public void setFilterQuery(FilterQuery mFilterQuery) {
		this.mFilterQuery = mFilterQuery;
	}


	private void  init(Context context){
		 this.mContext = context;		 
		 LinearLayout searchBarView = (LinearLayout)inflate(context, R.layout.search_bar, this);	
		 searchView = (EditText)searchBarView.findViewById(R.id.search_bar_query);
		 searchView.addTextChangedListener(new SearchViewTextWatcher()); 
		 queryHandler = new UserQueryHandler(context);	
	}
	
	private void setCustomAttrs(Context context, AttributeSet attrs){
		if(attrs !=null){
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.searchbar);
			final int indexCount = a.getIndexCount();
			for(int i=0; i<indexCount; i++){
				 int attr = a.getIndex(i);
				 switch (attr){
				 case R.styleable.searchbar_searchbartip:
					  String myText = a.getString(attr);
					  if( myText!= null){
						  tip = myText;
						  searchView.setHint(myText);
					  }
					  break;
				  default:
					  break;
				 }
			}
			a.recycle();
		}
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
			if(curAdapter != null ){
				curAdapter.getFilter().filter(s);
				curAdapter.setFilterQueryProvider(new UserFilterQuery());
//				curAdapter.notifyDataSetChanged();
			} else {
				Log.e(tag, "you need set cursorAdapter before filter");
				throw  new NullPointerException();
			}
		}
		
		
		
	}
	
	class UserFilterQuery implements FilterQueryProvider{

		@Override
		public Cursor runQuery(CharSequence constraint) {
			 
			constraint.toString();
			 Cursor cursor = null;
			 String selection = getSelection(constraint);
			 String[] selectionArgs = selection == null ? null : new String[]{constraint.toString()};
			 queryHandler.startQuery(QUERY_TOKEN, null, UriHelper.getUri(DBContentProvider.USER), 
					new String []{DataStore.UserTable._ID,
				DataStore.UserTable.USER_NAME,
					DataStore.UserTable.USER_PASSWORD}, selection,selectionArgs,null);
			 while( queryHandler.getCursor() != null){
				 Log.e(tag, "wait ...");
				 return queryHandler.getCursor();
			 }
			
			return null;
			
		}
		
	}
	
	private String getSelection(CharSequence constraint) {
		if(constraint == null || TextUtils.isEmpty(constraint)) {
			return null;
		}
		
		
		return DataStore.UserTable.USER_NAME + " LIKE ? || '%'";
	}
	
	class UserQueryHandler extends AsyncQueryHandler {

		private Cursor cursor = null;
		
		public Cursor getCursor() {
			return cursor;
		}


		public void setCursor(Cursor cursor) {
			this.cursor = cursor;
		}


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
//			curAdapter.changeCursor(cursor);		
			this.cursor = cursor;
		}
		
	}
	
	public interface FilterQuery {
		public void setQueryCondition();
	}
	


}
