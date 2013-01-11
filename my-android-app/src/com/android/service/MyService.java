package com.android.service;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service
{
    private MyBinder myBinder = new MyBinder();
    
    
     /**
      * 这里的  Inten 更多的是充当一个 传递数据的功能..
      * 在onBind 里面可以做很多东西，比如说 Binder 的初始化可能依赖于 Activity 传递过来的数据等等
      */
    
	@Override
	public IBinder onBind(Intent intent)
	{
		Log.d("MyService", "onBind");
		return myBinder;
	}
	@Override
	public void onCreate()
	{
		Log.d("MyService", "onCreate");
		super.onCreate();
	}

	@Override
	public void onDestroy()
	{
		Log.d("MyService", "onDestroy");
		super.onDestroy();
	}

	@Override
	public void onRebind(Intent intent)
	{
		Log.d("MyService", "onRebind");
		super.onRebind(intent);
	}

	@Override
	public void onStart(Intent intent, int startId)
	{
		Log.d("MyService", "onStart");
		super.onStart(intent, startId);
	}

	@Override
	public boolean onUnbind(Intent intent)
	{
		Log.d("MyService", "onUnbind");
		
		return super.onUnbind(intent);
	}
	
	
	/**
	 * 
	 * 在这个binder 里面可以实现自己的很多方法..实现实际意义上的处理。。
	 * @author Derek.pan
	 *
	 */
	public class MyBinder extends Binder
	{
		MyService getService()
		{
			return MyService.this;
		}
	}
}
