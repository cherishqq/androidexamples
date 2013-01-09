package com.derek.activity.adapter;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.derek.R;
import com.derek.activity.DbActivity;
import com.derek.model.User;
import com.derek.store.db.provider.DBContentProvider;
import com.derek.store.db.provider.DBContentProviderHelper;
import com.derek.store.db.provider.DataStore;
import com.derek.store.db.provider.DataStore.UserTable;
import com.derek.store.db.provider.UriHelper;


/**
 * 
 * @author Derek.pan
 * ������Ҫ��ʾ�� CursorAdapter ,���ҵ����ݱ仯ʱ,ǰ̨����֮�ı�
 * ���Ҳ����ڲ�ͬ�����,�Ƿ�����µ��߳���ִ��
 */

public class CursorAdapterDemo extends ListActivity{
	
	private MyCursorAdapter adapter;
	private MyDatasetObserver observer;
	
	private ListView listView;
	
	private Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cursoradapter);
		
		listView = (ListView) findViewById(android.R.id.list);
		
		handler = new Handler();
		Cursor cursor  = getContentResolver().query(UriHelper.getUri(DBContentProvider.USER), 
				new String []{DataStore.UserTable._ID,
			DataStore.UserTable.USER_NAME,
				DataStore.UserTable.USER_PASSWORD},
				null, null,null);
		
		Button addUser = (Button)findViewById(R.id.add);
		Button show = (Button)findViewById(R.id.show);
		
		show.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Toast(CursorAdapterDemo.this).makeText(CursorAdapterDemo.this,"show", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		addUser.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Runnable t =  new addUserThread();
				handler.post(t);
				
				// �����������µ��߳���ִ��..
			// 	new addUserThread().start();
				
				
				
				
/*				handler.post(new Runnable(){
					@Override
					public void run() {
						User u = new User();
						u.setName("derek");
						u.setmEmail("haijinme@qq.com");
						u.setPhotoUri("http://www.baidu.com");
						u.setPassword("123321");
						try {
							Thread.sleep(3000l);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						DBContentProviderHelper.addUser(CursorAdapterDemo.this,u);	
					}
					
				});*/
				
				/**
				 * ����һ�η��뵽һ���߳���ִ��
				 */
				
/*				User u = new User();
				u.setName("derek");
				u.setmEmail("haijinme@qq.com");
				u.setPhotoUri("http://www.baidu.com");
				u.setPassword("123321");
				DBContentProviderHelper.addUser(CursorAdapterDemo.this,u);*/
				// ���������notifyDataSetChanged
//				adapter.notifyDataSetChanged();
			}
		});
		
		
		

		adapter = new MyCursorAdapter(this,cursor);
		 
		observer = new MyDatasetObserver();
//		adapter.registerDataSetObserver(observer);
		listView.setAdapter(adapter);
//		listView.setListAdapter(adapter);
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
			try {
				Thread.sleep(3000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DBContentProviderHelper.addUser(CursorAdapterDemo.this,u);	
			
			Message msg = new Message();
			msg.what = 0;
			msg.obj = new Integer(1);
		
			handler.sendMessage(msg);
			
		}
		
	}
	
	public class addUserHanlder extends Handler {
		@Override
		public void handleMessage(Message msg) {
			
			switch(  msg.what) {
			case 0:
				System.out.print("success");
			}
			
		}
	}
	
	
	
	class MyCursorAdapter extends CursorAdapter{

		public MyCursorAdapter(Context context, Cursor c) {
			super(context, c);
		}
			
		/**
		 * ʲôʱ������newView ��
		 */

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			
			View view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.cursoradapter_item,null);	
			return view;
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
	
	public class MyDatasetObserver extends DataSetObserver{
		@Override
		public void onChanged() {
			adapter.notifyDataSetChanged();
		}
		
		@Override
		public void onInvalidated() {
			super.onInvalidated();
		}
	}

}
