package com.android.activity.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.model.User;
import com.android.R;

public class FriendsAdapter extends BaseListAdapter<User> {

	private LayoutInflater mInflater;
	
	private Handler mHandler;
	
	
	public FriendsAdapter(Context context){
		
		super();
		mInflater = LayoutInflater.from(context);
	}
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.friend_list_item, null);
			holder = new ViewHolder();
            holder.photo = (ImageView) convertView.findViewById(R.id.friendListItemPhoto);
            holder.name = (TextView) convertView.findViewById(R.id.friendListItemName);
            convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		User user = (User)getItem(position);
		holder.name.setText(user.getName());
		
		
		return null;
	}
	
	private static class ViewHolder {
		ImageView photo;
		TextView name;		
	}

}
