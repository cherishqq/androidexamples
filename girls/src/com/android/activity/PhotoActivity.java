package com.android.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.R;
import com.android.adapter.ImageAdapter;
import com.android.common.Constants;
import com.android.rest.PhotoListRestRequest;
import com.android.utils.HttpClientHelp;

public class PhotoActivity extends BaseActivity 
	implements View.OnClickListener{
	
	private GridView gv = null;
	
	private Handler mloadingHandler = new loadImageHandler();
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		setContentView(R.layout.photoviewgridview);
//		gv = (GridView)findViewById(R.id.filtergrid);
		gv.setAdapter(new ImageAdapter(this));//Ìí¼ÓÔªËØ 
		gv.setOnItemClickListener(new photoItemClickListener());
		gv.clearFocus();
		gv.setFocusable(false);
		gv.setFocusableInTouchMode(false);
		
	}
	
	
	public void loadImage(String param, Bitmap bitMap){
		if( bitMap == null){
		}
	}
	
	
	private final class loadImageHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				
			}
		}
	}
	
	public class LoadingPhotoReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
			if(intent.getAction().equals(Constants.PHOTO_LOADING_RESULT)){
				int result = intent.getIntExtra(Constants.PHOTO_LOADING_RESULT,Constants.STATE_FAILED);
				mloadingHandler.sendEmptyMessage(result);
			} else if( true ){
				
			}
			
			
		}
		
	}
	
	
	
	

	
	public class photoItemClickListener implements AdapterView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

	public void sendRequest() {
		PhotoListRestRequest request = new PhotoListRestRequest(1, 1l);
		HttpClientHelp.sendRequest(request);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
