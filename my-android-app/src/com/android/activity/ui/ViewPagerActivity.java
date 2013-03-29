package com.android.activity.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.android.R;

/**
 * 
 * @author Derek.pan
 *
 */
public class ViewPagerActivity  extends Activity {
	
	
	private ViewPager mViewPager;
	
	private PagerTitleStrip mPagerTitleStrip;
	
	private ArrayList<View> views;
	private  ArrayList<String> titles;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pager);
		 mViewPager = (ViewPager)findViewById(R.id.viewpager);
		 mPagerTitleStrip = (PagerTitleStrip)findViewById(R.id.pagetitle);
		 
		 /**
		  * 这个只是非常简单的View的展示
		  */
		 LayoutInflater mLi = LayoutInflater.from(this);
		 View view1 = mLi.inflate(R.layout.main, null);
		 View view2 = mLi.inflate(R.layout.main, null);
		 View view3 = mLi.inflate(R.layout.main, null);
		 
		 views  = new ArrayList<View>();
		 
		 views.add(view1);
		 views.add(view2);
		 views.add(view3);
		 
		 titles = new ArrayList<String>();
		 titles.add("tab1");
		 titles.add("tab2");
		 titles.add("tab3");
		 
		 mViewPager.setAdapter(new myPagerAdapter() );
	}
	
	class myPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			return titles.get(position);
		}
		
		@Override
	    public void destroyItem(View container, int position, Object object) {
			((ViewPager)container).removeView(views.get(position));
		}

		
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager)container).addView(views.get(position));
			                return views.get(position);

		}
		
	}
	
	
	

}
