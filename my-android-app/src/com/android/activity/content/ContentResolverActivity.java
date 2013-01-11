package com.android.activity.content;

import com.android.R;
import com.android.model.User;
import com.android.store.db.provider.DBContentProvider;
import com.android.store.db.provider.DBContentProviderHelper;
import com.android.store.db.provider.DataStore;
import com.android.store.db.provider.UriHelper;
import com.android.store.db.provider.DataStore.UserTable;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContentResolverActivity extends ListActivity {
	
	ContentResolver contentResolver;
	
	private Handler handler;
	
	private CursorAdapter adapter;
	
	private ListView listView;
	
	private String tag = "ContentResolverActivity";
	
	private MyContentObserver observer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		contentResolver = getContentResolver();
		handler = new Handler();
		
		/**
		 * 这里notifyForDescendents 若为false,则增加一行是没有的
		 */
		
		observer = new MyContentObserver(handler);
		
//		contentResolver.registerContentObserver(UriHelper.getUri(DBContentProvider.USER), true, observer );
		setContentView(R.layout.contentresolver);
		
		listView = (ListView) findViewById(android.R.id.list);
		Button addUser = (Button)findViewById(R.id.add);
		Button show = (Button)findViewById(R.id.show);
		
		show.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Toast(ContentResolverActivity.this).makeText(ContentResolverActivity.this,"show", Toast.LENGTH_SHORT).show();
			}
		});
		
		addUser.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Runnable t =  new addUserThread();
				handler.post(t);

			}
		});
		
		Cursor cursor  = contentResolver.query(UriHelper.getUri(DBContentProvider.USER), 
				new String []{DataStore.UserTable._ID,
			DataStore.UserTable.USER_NAME,
				DataStore.UserTable.USER_PASSWORD},
				null, null,null);
		
		
		adapter = new MyCursorAdapter(this,cursor);
		listView.setAdapter(adapter);
		
		
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if(contentResolver != null){
//			contentResolver.unregisterContentObserver(observer);
		}
	}
	
	public class addUserThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			User u = new User();
			u.setName("derek");
			u.setmEmail("haijinme@qq.com");
			u.setPhotoUri("http://www.baidu.com");
			u.setPassword("123321");

			DBContentProviderHelper.addUser(ContentResolverActivity.this,u);	
			
			Message msg = new Message();
			msg.what = 0;
			msg.obj = new Integer(1);
		
			handler.sendMessage(msg);
			
		}
		
	}
	
	public class MyContentObserver extends ContentObserver{

		public MyContentObserver(Handler handler) {
			super(handler);
		}
		
		@Override
		public void onChange(boolean selfChange) {
			Log.e(tag, "data has changed");
		}
		
	}
	
	class MyCursorAdapter extends CursorAdapter{

		public MyCursorAdapter(Context context, Cursor c) {
			super(context, c);
		}
			
		/**
		 * 什么时候会调用newView 呢
		 */

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			
			Log.e(tag, "newView");
			View view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.cursoradapter_item,null);	
			return view;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			
			Log.e(tag, "bindView");
			LinearLayout layout = (LinearLayout)view;
			TextView nameView = (TextView)layout.findViewById(R.id.name);
			TextView psdView = (TextView)layout.findViewById(R.id.password);
			
			nameView.setText(cursor.getString(cursor.getColumnIndex(UserTable.USER_NAME)));
			psdView.setText(cursor.getString(cursor.getColumnIndex(UserTable.USER_PASSWORD)));
			
		}
		
	}
	

}
