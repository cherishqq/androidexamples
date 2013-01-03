package com.derek.ui.view;


import java.util.ArrayList;

import com.derek.R;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;


public class NameSortableAdapter extends AppManagerAdapter implements SectionIndexer,
					OnScrollListener {

	/** SectionIndexer */
    private SectionIndexer mIndexer;
    /** mSections */
    private String[] mSections;
    /** 所有分组的个数 */
    private int[] mCounts;
    /** section个数 */
    private int mSectionCounts = 0;
    /** LayoutInflater */
    protected final LayoutInflater mInflater;
   
    /** 设置当前itme的数量，在Activity显示后，再加载itemview */
    private boolean mIsEmpty = true;
    /** Handler */
    private final Handler mHandler = new Handler();
    
    private static final String TAG = NameSortableAdapter.class.getSimpleName();
    

    /**
     * NameSortableAdapter 构造函数
     * 
     * @param inflater
     *            LayoutInflater
     * @param context
     *            Context
     */
    public NameSortableAdapter(final LayoutInflater inflater,ArrayList<String> data, Context context) {
    	  super(context, new ArrayList<String>());
    	  this.mInflater = inflater;
    	  dataset = data;
    	  if (mIsEmpty) {
    		  new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					   updateTotalCount();
	                   mIndexer = new AppSectionIndexer(mSections, mCounts);
	                   mIsEmpty = false;
	                   mHandler.post(new Runnable() {
	                        @Override
	                        public void run() {
//	                            AppManager.getInstance(mContext).registerInstallAppChangedListener(
//	                                    mInstalledAppChangedListener);
	                            notifyDataSetChanged();
	                        }
	                    });
	                    
				}}).start();
    	  }
    }
    


    /**
     * 更新section的数量，遍历一边应用的名字，找出所有的应用名字的第一个字母。
     */
    private void updateTotalCount() {
        String currentSection = null;
        mSectionCounts = 0;
        final int count = dataset.size();
        for (int i = 0; i < count; i++) {
            final String item = dataset.get(i);
            if (!isEqual(currentSection, item.substring(0, 1).toUpperCase())) {
                mSectionCounts++;
                currentSection = item.substring(0, 1).toUpperCase();
            }
        }
        fillSections();
    }
    
    /**
     * 判断两个字符串是否是相同的
     * 
     * @param s1
     *            第一个字符串
     * @param s2
     *            第二个字符串
     * @return true相等，false不相等
     */
    private boolean isEqual(final String s1, final String s2) {
        if (s1 == null) {
            return s2 == null;
        } else {
            return s1.equals(s2);
        }
    }
    
    /**
     * 填充Sections及每个section对应的应用的个数count.
     */
    private void fillSections() {
        mSections = new String[mSectionCounts];
        mCounts = new int[mSectionCounts];
        final int count = dataset.size();
        String currentSection = null;
        int newSectionIndex = 0;
        int newSectionCounts = 0;
        String previousSection = null;
        for (int i = 0; i < count; i++) {
            newSectionCounts++;
            currentSection = dataset.get(i).substring(0, 1).toUpperCase();
            if (!Character.isLetter(currentSection.charAt(0))) {
                // 非字母的设置为#
                currentSection = "#";
            }
            if (!isEqual(previousSection, currentSection)) {
                mSections[newSectionIndex] = currentSection;
                previousSection = currentSection;
                if (newSectionIndex == 1) {
                    mCounts[0] = newSectionCounts - 1;
                    if (i == count - 1) {
                        mCounts[newSectionIndex] = 1;
                    }
                } else if (newSectionIndex != 0) {
                    mCounts[newSectionIndex - 1] = newSectionCounts;
                    if (i == count - 1) {
                        mCounts[newSectionIndex] = 1;
                    }
                }
                if (i != 0) {
                    newSectionCounts = 0;
                }
                newSectionIndex++;
            } else if (i == count - 1) {
                mCounts[newSectionIndex - 1] = newSectionCounts;
            }
            //处理只有一个应用的情况
            if (count == 1) {
                mCounts[newSectionIndex - 1] = newSectionCounts;
            }
        }
    }

    
	@Override
	public int getPinnedHeaderState(int position) {
		// TODO Auto-generated method stub
		  int realPosition = position;

	        if (mIndexer == null) {
	            return PINNED_HEADER_GONE;
	        }
	        if (realPosition < 0) {
	            return PINNED_HEADER_GONE;
	        }
	        int section = getSectionForPosition(realPosition);
	        int nextSectionPosition = getPositionForSection(section + 1);
	        if (nextSectionPosition != -1 && realPosition == nextSectionPosition - 1) {
	            return PINNED_HEADER_PUSHED_UP;
	        }
	        return PINNED_HEADER_VISIBLE;
	}

	@Override
	public void configurePinnedHeader(View header, int position, int alpha) {
		// TODO Auto-generated method stub
		  int realPosition = position;
	        int section = getSectionForPosition(realPosition);
	        if (section >= 0 && section < mIndexer.getSections().length) {
	            String title = (String) mIndexer.getSections()[section];
	            ((TextView) header.findViewById(R.id.header_text)).setText(title);
	        }
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized int getCount() {
		// TODO Auto-generated method stub
//		 if (isEmpty()) {
//	            return 0;
//	        }
	     return dataset.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		 return dataset.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		 return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		  View view = convertView;
	        if (view == null) {
	            view = mInflater.inflate(R.layout.listitem, null);
	        }
	     
	       String name = dataset.get(position);
	       if (name != null && !"".equals(name)) {
	            final TextView header = (TextView) view.findViewById(R.id.header);
	           // Log.d("RC","===header="+header+"view="+view);
	            final TextView textView = (TextView) view.findViewById(R.id.appname_text_view);
	       
	            if (textView != null) {
	                textView.setText(name);        
	            }
	            
	            if (header != null && mSections != null) {
	                int sectionposition = getSectionForPosition(position);
	                if (sectionposition >= 0 && sectionposition < mSections.length) {
	                	Log.d("RC","===sectionposition="+sectionposition+",mSections[sectionposition]="+mSections[sectionposition]);
	                    header.setText(mSections[sectionposition]);
	                } else {
	                    if (sectionposition < 0) {
	                        header.setText(mSections[0]);
	                    } else if (sectionposition >= mSections.length) {
	                        header.setText(mSections[mSections.length - 1]);
	                    }
	                }
	            }
	            
	            int section = getSectionForPosition(position);
	           // Log.d("RC","===header="+header+"section="+section+"getPositionForSection(section)="+getPositionForSection(section));
	            if (getPositionForSection(section) == position) {
	                // 显示标题
	            	if (header != null) {
	            		Log.d("RC","===position="+position);
	                header.setVisibility(View.VISIBLE);
	            	 }
	            } else {
	                // 隐藏标题
	                if (header != null) {
	                    header.setVisibility(View.GONE);
	                }
	            }
	            
	            
	       }
	       
	       
           
		return view;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		  if (view instanceof PinnedHeaderListView) {
	            ((PinnedHeaderListView) view).configureHeaderView(firstVisibleItem);
	        }
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		 if (mIndexer == null) {
	            return new String[] { "" };
	        } else {
	            return mIndexer.getSections();
	        }
	}

	@Override
	public int getPositionForSection(int section) {
		// TODO Auto-generated method stub
		  if (mIndexer == null) {
	            return -1;
	        }
	        return mIndexer.getPositionForSection(section);
	}

	@Override
	public int getSectionForPosition(int position) {
		// TODO Auto-generated method stub
		  if (mIndexer == null) {
	            return -1;
	        }
	        return mIndexer.getSectionForPosition(position);
	}

}
