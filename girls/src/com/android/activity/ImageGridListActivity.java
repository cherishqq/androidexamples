package com.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.android.R;

public class ImageGridListActivity  extends Activity {
	
	private LayoutInflater inflater ;
	
	ImageGridListAdapter mAdapter;
	private Context mContext;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setContentView(R.layout.photoviewgridview);
 /*       getListView().setEmptyView(findViewById(R.id.empty));
        // Set up our adapter
        mAdapter = new ImageGridListAdapter(this);
        setListAdapter(mAdapter);*/
        
        
    }

    
	
	public class ImageGridListAdapter extends BaseAdapter {
		
		private List<Integer> gvList = new ArrayList<Integer>();
		private View v ;

		public ImageGridListAdapter(ImageGridListActivity imageGridListActivity) {
			super();
			v = inflater.inflate(R.layout.photoviewgridview, null);
			gvList.add(new Integer(1));
			gvList.add(new Integer(1));
			gvList.add(new Integer(1));
			gvList.add(new Integer(1));
			gvList.add(new Integer(1));
			gvList.add(new Integer(1));
			gvList.add(new Integer(1));
		}
		

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return gvList.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		
		

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
/*			if( convertView == null) {				
				convertView = v;
			}	*/
//			v.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
			convertView = v;
			return convertView;
		}
		public void addGridView(){
			
		}
		
	}
    
    
    
    

}
