package com.derek.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;

public class BaseActivity extends Activity{
	
	// 可以增加一些公共的跳转,通知等方法
	
	private NotificationManager notificationManager;
	private Context context;
//	private BroadcastReceiver receiver = new BroadcastReceiver(this);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// 处理退出时的接收
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
