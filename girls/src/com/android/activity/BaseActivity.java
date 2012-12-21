package com.android.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
	
}
