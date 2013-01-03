package com.derek.ui.view;

import java.util.Arrays;

import android.widget.SectionIndexer;

public class AppSectionIndexer implements SectionIndexer{

	 /** ���е�sections */
   private final String[] mSections;
   /** section��Ӧ���б��е�λ�� */
   private final int[] mPositions;
   /** ���е��б����С */
   private final int mCount;
   
   
   /**
    * ���캯��
    * 
    * @param sections
    *            �ܵ�sections
    * @param counts
    *            section��Ӧ��count
    */
   public AppSectionIndexer(String[] sections, int[] counts) {
       if (sections == null || counts == null) {
           throw new NullPointerException();
       }
       if (sections.length != counts.length) {
           throw new IllegalArgumentException(
                   "The sections and counts arrays must have the same length");
       }
       this.mSections = sections;
       mPositions = new int[counts.length];
       int position = 0;
       for (int i = 0; i < counts.length; i++) {
           if (mSections[i] == null) {
               mSections[i] = "";
           } else {
               mSections[i] = mSections[i].trim();
           }
           mPositions[i] = position;
           position += counts[i];
       }
       mCount = position;
   }
   
   
	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return mSections;
	}

	@Override
	public int getPositionForSection(int section) {
		// TODO Auto-generated method stub
		if (section < 0 || section > mSections.length - 1) {
           return -1;
       }
       return mPositions[section];
	}

	@Override
	public int getSectionForPosition(int position) {
		// TODO Auto-generated method stub
		  if (position < 0 || position >= mCount) {
	            return -1;
	        }
	        int index = Arrays.binarySearch(mPositions, position);
	        if (index >= 0) {
	            return index;
	        } else {
	            return -index - 2;
	        }
	}

}
