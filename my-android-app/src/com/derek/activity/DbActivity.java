package com.derek.activity;

import com.derek.R;
import com.derek.store.db.provider.DBContentProvider;
import com.derek.store.db.provider.DBContentProviderHelper;
import com.derek.store.db.provider.DataStore;
import com.derek.store.db.provider.DataStore.UserTable;
import com.derek.store.db.provider.DbHelper;
import com.derek.store.db.provider.UriHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DbActivity  extends Activity{
	
	private Button adduserButton;
	private Button modifyButton;
	private Button deleteButton;	
	private Button showButton;
	DbHelper  db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbtest);
		adduserButton = (Button)findViewById(R.id.add_user);
		modifyButton = (Button)findViewById(R.id.modify_user);
		deleteButton = (Button)findViewById(R.id.delete_user);
		showButton = (Button)findViewById(R.id.showUser);
		db= new DbHelper(this);
		adduserButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DBContentProviderHelper.addUser(DbActivity.this);
			}
		});
		
		modifyButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ContentValues c = new ContentValues();
				c.put(UserTable.USER_NAME, "nick");
				c.put(UserTable.USER_PASSWORD	, "nick");
				getContentResolver().update(UriHelper.getUri(DBContentProvider.USER), c, DataStore.UserTable._ID +  "=2", null);
				
			}
		});
		
		deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getContentResolver().delete(UriHelper.getUri(DBContentProvider.USER), DataStore.UserTable._ID + "=3", null);
			}
		});
		
		showButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String userName = DBContentProviderHelper.simpleQueryByUserId(DbActivity.this, 1, DBContentProvider.USER, DataStore.UserTable.USER_NAME);
				Cursor cursor = getContentResolver().query(UriHelper.getUri(DBContentProvider.USER), 
															new String []{DataStore.UserTable._ID,
																DataStore.UserTable.USER_NAME,
																	DataStore.UserTable.USER_PASSWORD},
					
																	null, null,null);
				String tip = "";
				if(cursor == null){
					return;
				} else if (!cursor.moveToFirst())
				{
					cursor.close();
					return;
				}
				do{
					int id = cursor.getInt(cursor.getColumnIndex(UserTable._ID));
					String userName1 = cursor.getString(cursor.getColumnIndex(UserTable.USER_NAME));
					String password = cursor.getString(cursor.getColumnIndex(UserTable.USER_PASSWORD));
					tip = tip  + id + ";" + userName1 + ";" + password + "\n";
				}while(cursor.moveToNext());
				
				cursor.close();
				
				Toast.makeText(DbActivity.this, tip, 3).show();
			}
		});

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	

}
