package com.android.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BaseActivity extends Activity{
	
	// ��������һЩ��������ת,֪ͨ�ȷ���
	
	private NotificationManager notificationManager;
	private Context context;
//	private BroadcastReceiver receiver = new BroadcastReceiver(this);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// �����˳�ʱ�Ľ���
		// ���紦��
		this.notificationManager = (NotificationManager)getSystemService("notification");
		this.context = this;
		
	}
	
}
