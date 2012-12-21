package com.android.activity;

import java.util.zip.Inflater;

import com.android.R;
import com.android.common.Images;
import com.android.log.LogSettings;
import com.android.utils.ImageCache;
import com.android.utils.ImageFetcher;
import com.android.utils.ImageResizer;
import com.android.utils.Utils;
import com.android.utils.ImageCache.ImageCacheParams;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageGridView extends LinearLayout implements
		AdapterView.OnItemClickListener {

	private static final String IMAGE_CACHE_DIR = "thumbs";

	private int mImageThumbSize;
	private int mImageThumbSpacing;
	private ImageAdapter mAdapter;
	private ImageResizer mImageWorker;
	private String TAG = "ImageGridView";
	private Context mContext;
	private GridView mGridView;
	private LayoutInflater inflater;
	
	
	

	public ImageGridView(Context context, AttributeSet attrs,int defStyle) {  
		super(context, attrs);
		init(context);
        
} 
	
	public ImageGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public ImageGridView(Context context) {
		super(context);
		init(context);

		// The ImageWorker takes care of loading images into our ImageView
		// children asynchronously
	}
	
	

	public void init(Context context){
		this.mContext = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.image_grid_fragment,this,true);
		 mGridView = (GridView) v.findViewById(R.id.gridView);
		
/*		 addView(mGridView, new LinearLayout.LayoutParams(
                   LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));*/
		ImageCacheParams cacheParams = new ImageCacheParams(IMAGE_CACHE_DIR);

		// Allocate a third of the per-app memory limit to the bitmap memory
		// cache. This value
		// should be chosen carefully based on a number of factors. Refer to the
		// corresponding
		// Android Training class for more discussion:
		// http://developer.android.com/training/displaying-bitmaps/
		// In this case, we aren't using memory for much else other than this
		// activity and the
		// ImageDetailActivity so a third lets us keep all our sample image
		// thumbnails in memory
		// at once.
		cacheParams.memCacheSize = 1024 * 1024 * Utils
				.getMemoryClass(mContext) / 3;
		mImageWorker = new ImageFetcher(mContext, mImageThumbSize);
		mImageWorker.setAdapter(Images.imageThumbWorkerUrlsAdapter);
		mImageWorker.setLoadingImage(R.drawable.empty_photo);
		mImageWorker.setImageCache(ImageCache.findOrCreateCache(mContext,
				cacheParams));
		
		
		mAdapter = new ImageAdapter(mContext);
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(this);

		// This listener is used to get the final width of the GridView and then
		// calculate the
		// number of columns and the width of each column. The width of each
		// column is variable
		// as the GridView has stretchMode=columnWidth. The column width is used
		// to set the height
		// of each view so we get nice square thumbnails.
		mGridView.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						if (mAdapter.getNumColumns() == 0) {
							final int numColumns = (int) Math.floor(mGridView
									.getWidth()
									/ (mImageThumbSize + mImageThumbSpacing));
							if (numColumns > 0) {
								final int columnWidth = (mGridView.getWidth() / numColumns)
										- mImageThumbSpacing;
								mAdapter.setNumColumns(numColumns);
								mAdapter.setItemHeight(columnWidth);
								if (LogSettings.DEBUG) {
									Log.d(TAG,
											"onCreateView - numColumns set to "
													+ numColumns);
								}
							}
						}
					}
				});

		mImageThumbSize = getResources().getDimensionPixelSize(
				R.dimen.image_thumbnail_size);
		mImageThumbSpacing = getResources().getDimensionPixelSize(
				R.dimen.image_thumbnail_spacing);
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}


	/**
	 * The main adapter that backs the GridView. This is fairly standard except
	 * the number of columns in the GridView is used to create a fake top row of
	 * empty views as we use a transparent ActionBar and don't want the real top
	 * row of images to start off covered by it.
	 */
	private class ImageAdapter extends BaseAdapter {

		private final Context mContext;
		private int mItemHeight = 0;
		private int mNumColumns = 0;
		private int mActionBarHeight = -1;
		private GridView.LayoutParams mImageViewLayoutParams;

		public ImageAdapter(Context context) {
			super();
			mContext = context;
			mImageViewLayoutParams = new GridView.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		}

		@Override
		public int getCount() {
			// Size of adapter + number of columns for top empty row
			return mImageWorker.getAdapter().getSize() + mNumColumns;
		}

		@Override
		public Object getItem(int position) {
			return position < mNumColumns ? null : mImageWorker.getAdapter()
					.getItem(position - mNumColumns);
		}

		@Override
		public long getItemId(int position) {
			return position < mNumColumns ? 0 : position - mNumColumns;
		}

		@Override
		public int getViewTypeCount() {
			// Two types of views, the normal ImageView and the top row of empty
			// views
			return 2;
		}

		@Override
		public int getItemViewType(int position) {
			return (position < mNumColumns) ? 1 : 0;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup container) {
			// First check if this is the top row
			if (position < mNumColumns) {
				if (convertView == null) {
					convertView = new View(mContext);
				}
				// Calculate ActionBar height
				if (mActionBarHeight < 0) {

					// derek
					/*
					 * TypedValue tv = new TypedValue(); if
					 * (mContext.getTheme().resolveAttribute(
					 * android.R.attr.actionBarSize, tv, true)) {
					 * mActionBarHeight =
					 * TypedValue.complexToDimensionPixelSize( tv.data,
					 * mContext.getResources().getDisplayMetrics()); } else { //
					 * No ActionBar style (pre-Honeycomb or ActionBar not in
					 * theme) mActionBarHeight = 0; }
					 */

					mActionBarHeight = 0;
				}
				// Set empty view with height of ActionBar
				convertView.setLayoutParams(new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, mActionBarHeight));
				return convertView;
			}

			// Now handle the main ImageView thumbnails
			ImageView imageView;
			if (convertView == null) { // if it's not recycled, instantiate and
										// initialize
				imageView = new ImageView(mContext);
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setLayoutParams(mImageViewLayoutParams);
			} else { // Otherwise re-use the converted view
				imageView = (ImageView) convertView;
			}

			// Check the height matches our calculated column width
			if (imageView.getLayoutParams().height != mItemHeight) {
				imageView.setLayoutParams(mImageViewLayoutParams);
			}

			// Finally load the image asynchronously into the ImageView, this
			// also takes care of
			// setting a placeholder image while the background thread runs
			mImageWorker.loadImage(position - mNumColumns, imageView);
			return imageView;
		}

		/**
		 * Sets the item height. Useful for when we know the column width so the
		 * height can be set to match.
		 * 
		 * @param height
		 */
		public void setItemHeight(int height) {
			if (height == mItemHeight) {
				return;
			}
			mItemHeight = height;
			mImageViewLayoutParams = new GridView.LayoutParams(
					LayoutParams.MATCH_PARENT, mItemHeight);
			mImageWorker.setImageSize(height);
			notifyDataSetChanged();
		}

		public void setNumColumns(int numColumns) {
			mNumColumns = numColumns;
		}

		public int getNumColumns() {
			return mNumColumns;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long id) {
		final Intent i = new Intent(mContext, ImageDetailActivity.class);
		i.putExtra(ImageDetailActivity.EXTRA_IMAGE, (int) id);
		mContext.startActivity(i);
	}

}
