package com.derek.activity.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseListAdapter<T>  extends BaseAdapter {

	private List<T> lists;
	
	@Override
	public int getCount() {
		
		return (lists == null) ? 0:lists.size();
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
	@Override
	public boolean isEmpty(){
		return (lists == null) ? true: lists.isEmpty();
	}
	
    public void setList(List<T> l){
    	lists = l;
    	notifyDataSetInvalidated();
    }
    
    public void registerDataSetObserver(android.database.DataSetObserver observer) {};
    
    /**
     * 针对文件等其他的变化做的操作
     */
    public void registerDataObserver(){
    	
    }
	
}
