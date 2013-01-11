package com.android.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;

public class BaseActivity extends Activity{
	
	// 可以增加�?��公共的跳�?通知等方�?
	
	private NotificationManager notificationManager;
	private Context context;
//	private BroadcastReceiver receiver = new BroadcastReceiver(this);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// 处理�?��时的接收
		// 网络处理
		this.notificationManager = (NotificationManager)getSystemService("notification");
		this.context = this;		
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
	}

}
