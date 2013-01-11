package com.android.ui.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 观察者模式，可以加入不同的比较器
 * @author nick.gao
 *
 */
public class SortableListDecorator extends SortableList{

	
   
    /** 按照sequence比较 */
    protected Comparator<String> mSortComparator = null;
    
    /**
     * 构造函数
     * 
     * @param sortblelist
     *            AbstractSortableList
     */
    public SortableListDecorator(ArrayList<String> list) {
    	mList = list;
    }
    
	@Override
	public ArrayList<String> getAppsList() {
		// TODO Auto-generated method stub
	    ArrayList<String> localArrayList = mList;
        Collections.sort(localArrayList, this.mSortComparator);
        return localArrayList;
	}

}
