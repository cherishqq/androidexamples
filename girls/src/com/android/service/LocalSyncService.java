package com.android.service;

import com.android.common.Constants;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalSyncService extends Service {

	private static final String TAG = "PictureSyncService";
	private final LocalSyncBinder mBinder = new LocalSyncBinder();
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		if(intent == null){
			return;
		}
		try{
			int command = intent.getExtras().getInt(Constants.SYNC_COMMAND);
			if( command == Constants.SYNC_PICTURE_TAG){
				mBinder.syncPicture();
			}
			
			
		}catch (Exception e) {
			Log.d(TAG, e.toString());
		}
		
	}
	
	
	public class LocalSyncBinder extends Binder{
		
		public LocalSyncBinder getService() {
			return LocalSyncBinder.this;
		}
		
		
		public void syncPicture(){
			
		}
	
	}
	
	private class PictureSyncTask extends AsyncTask<String, Void, Void>{

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
		
	}
	
	
	

}
