package com.android.ui.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * �۲���ģʽ�����Լ��벻ͬ�ıȽ���
 * @author nick.gao
 *
 */
public class SortableListDecorator extends SortableList{

	
   
    /** ����sequence�Ƚ� */
    protected Comparator<String> mSortComparator = null;
    
    /**
     * ���캯��
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
