package com.android.adapter;

import com.android.R;
import com.android.log.EngLog;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter{

	private EngLog log = new EngLog(ImageAdapter.class);
	
	private Handler mHandler = new Handler();
	
	private Context mContext;
	
	private Integer [] imageIds;
	
    private LayoutInflater mInflater; 
	
	public ImageAdapter(Context c) {
		super();
		this.mContext = c;
		mInflater = (LayoutInflater) c  
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
		
		
		imageIds = new Integer[20];
		imageIds[0] =  R.drawable.item6;
		imageIds[1] =  R.drawable.item6;
		imageIds[2] =  R.drawable.item6;
		imageIds[3] =  R.drawable.item6;
		imageIds[4] =  R.drawable.item6;
		imageIds[5] =  R.drawable.item6;
		imageIds[6] =  R.drawable.item6;
		imageIds[7] =  R.drawable.item6;
		imageIds[8] =  R.drawable.item6;
		imageIds[9] =  R.drawable.item6;
		imageIds[10] =  R.drawable.item6;
		imageIds[11] =  R.drawable.item6;
		imageIds[12] =  R.drawable.item6;
		imageIds[13] =  R.drawable.item6;
		imageIds[14] =  R.drawable.item6;
		imageIds[15] =  R.drawable.item6;
		imageIds[16] =  R.drawable.item6;
		imageIds[17] =  R.drawable.item6;
		imageIds[18] =  R.drawable.item6;
		imageIds[19] =  R.drawable.item6;
	}
	

	@Override
	public int getCount() {
		return imageIds.length;
	}

	@Override
	public Object getItem(int position) {
		return imageIds[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		/*LinearLayout imageLinearLayout = (LinearLayout)mInflater.inflate(R.layout.photoimage, null);
		ImageView iView = (ImageView)imageLinearLayout.findViewById(R.id.imageView);
			
		//ImageView iView = new ImageView(mContext);
		iView.setImageResource(imageIds[position]);
		convertView = iView;
		return convertView;*/
		
		ViewHolder holder;
		
		
		ImageView imageView;
		if( convertView == null) {
			imageView = new ImageView(mContext);
			holder = new ViewHolder();
			imageView.setLayoutParams(new GridView.LayoutParams(100,100));
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			convertView.setTag(holder);
		} else {
			imageView = (ImageView)convertView;
			holder = (ViewHolder)convertView.getTag();
		}
		
		imageView.setImageResource(imageIds[position]);
		return imageView;
	}


public class ViewHolder {
	ImageView image;
	TextView description;
	String commentUrl; // 指向评论的url
}

}
