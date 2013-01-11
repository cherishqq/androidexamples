package com.android.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;



public class SequenceNumSortableList extends SortableListDecorator{

	
	
	static class StrItemComparator implements Comparator<String>,Serializable{

		@Override
		public int compare(String item1, String item2) {
			// TODO Auto-generated method stub
			return item1.compareTo(item2);
		}
		
	}
	
	
	public SequenceNumSortableList(ArrayList<String> list) {
		super(list);
		// TODO Auto-generated constructor stub
	    mSortComparator = new StrItemComparator();
	}

}
