package com.android.activity.listview;

import android.app.ListActivity;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 下载处理类
 * 
 * 實現下拉下載更多
 * @author Derek.pan
 *
 */
public class DownloadListView  extends ListActivity {
	
	
	private DownAdapter mAdapter;
	
	private AdapterDataSetObserver observer;
	
	

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		observer = new AdapterDataSetObserver();
		mAdapter.registerDataSetObserver(observer);
		
		
	}
	
	
	
	
	
	public class DownloadAsyncTask extends AsyncTask<String[],Integer,String>{

		
		public DownloadAsyncTask() {
		}
		
		@Override
		protected String doInBackground(String[]... params) {
			// TODO Auto-generated method stub
			return null;
		}
	
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		
	}
	
	
	
	public class DownAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
	public class AdapterDataSetObserver extends DataSetObserver{
		
		@Override
		public void onChanged() {
			// TODO Auto-generated method stub
			super.onChanged();
			mAdapter.notifyDataSetChanged();
		}
		
		@Override
		public void onInvalidated() {
			// TODO Auto-generated method stub
			super.onInvalidated();
			mAdapter.notifyDataSetInvalidated();
		}
		
	}
	
	
	

}
