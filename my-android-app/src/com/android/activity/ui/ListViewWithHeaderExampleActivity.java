package com.android.activity.ui;

import java.util.ArrayList;
import java.util.HashSet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.TextView;

import com.android.ui.view.NameSortableAdapter;
import com.android.ui.view.PinnedHeaderListView;
import com.android.ui.view.SectionIndexerView;
import com.android.ui.view.SequenceNumSortableList;
import com.android.R;


public class ListViewWithHeaderExampleActivity extends Activity {
    /** Called when the activity is first created. */
	
	  /** 带有顶部section显示的listview */
    private PinnedHeaderListView listView;
    
    /** SectionIndexer 用于用户快速定位 */
    private SectionIndexerView mSectionIndexerView = null;
    
    /** 按照名字排序的adapter */
    private NameSortableAdapter mNameSortableAdapter;
    
    /** Section text */
    private TextView mSectionText = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_header);
        initLoad();
    }
    
    
    
    
    private static ArrayList<String> constructDataSource(){
    	
    	String str = "China is a country with a very early civilization and a long and rich history. The compass, gunpowder, the art of paper-making and block printing invented by the ancient Chinese have contributed immensely to the progress of mankind. The Great Wall, Grand Canal and other projects built by the Chinese people are regarded as engineering feats in the world." +
				"Man has lived for a very long time in what is now China, according to archaeological finds. In many parts of the country, for instance, fossil remains of primitive ape men have been unearthed. Among them are the fossil remains of the Yuanmou Ape Man who lived in Yunnan Province some million years ago." +
				"show that the Peking Man, who lived about years ago, was able to make and use simple implements and knew the use of fire";
		str = str.toLowerCase();
    	String[] array = str.split(" ");
		ArrayList<String> list = new ArrayList<String>(array.length);
		HashSet<String> set = new HashSet<String>();
		for(int i=0;i < array.length; i++) {
			array[i] = array[i].replaceAll("[^\\w]", "");
			if(set.add(array[i])){
				list.add(array[i]);
			}
		}
	
		return list;
    }
    
    private static ArrayList<String> getSortedData(){
    	SequenceNumSortableList sortedList = new SequenceNumSortableList(constructDataSource());
    	return sortedList.getAppsList();
    }
    
    
    private void initLoad(){
    	   listView = (PinnedHeaderListView) findViewById(R.id.section_list_view);
           listView.setOnScrollListener(mScrollListener);
           listView.setDivider(null);
           listView.setOnItemClickListener(mNameSortableAdapter);
           listView.setPinnedHeaderView(getLayoutInflater().inflate(R.layout.list_section,
                   listView, false));
           mSectionIndexerView = (SectionIndexerView) findViewById(R.id.section_indexer_view);
           mNameSortableAdapter = new NameSortableAdapter(
        		   ListViewWithHeaderExampleActivity.this.getLayoutInflater(), getSortedData(),ListViewWithHeaderExampleActivity.this);
           // listView.setOnScrollListener(mNameSortableAdapter);
           listView.setPinnedHeaderView(getLayoutInflater().inflate(R.layout.list_section,
                   listView, false));
           mSectionText = (TextView) findViewById(R.id.section_text);
           mSectionIndexerView.init(listView, mNameSortableAdapter,mSectionText);

           mSectionIndexerView.setVisibility(View.VISIBLE);
           mSectionText.setVisibility(View.INVISIBLE);
           listView.setAdapter(mNameSortableAdapter);
           listView.setOnItemClickListener(mNameSortableAdapter);
           listView.setOnItemLongClickListener(mNameSortableAdapter);
           mNameSortableAdapter.notifyDataSetChanged();
    }
    
    /** 当scrollListener滑动时，判断是否需要给出跳到页首的用户教育 */
    private OnScrollListener mScrollListener = new OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                int totalItemCount) {
        	
        	Log.d("RC","====onScrollListener view="+view);
            if (view instanceof PinnedHeaderListView) {
                ((PinnedHeaderListView) view).configureHeaderView(firstVisibleItem);
            }
        	
        }
    };

}