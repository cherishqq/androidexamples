package com.derek.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class PinnedHeaderListView extends ListView {
	
	 /** 最大的alpha */
    private static final int MAX_ALPHA = 255;
    /** adapter */
    private PinnedHeaderAdapter mAdapter;
    /** headerview */
    private View mHeaderView;
    /** 设置headerview的可见性 */
    private boolean mHeaderViewVisible;
    /** view的宽度 */
    private int mHeaderViewWidth;
    /** view的高度 */
    private int mHeaderViewHeight;
    
	   /**
     * 构造函数
     * 
     * @param context
     *            Context
     */
    public PinnedHeaderListView(Context context) {
        super(context);
    }

    /**
     * 构造函数
     * 
     * @param context
     *            Context
     * @param attrs
     *            AttributeSet
     */
    public PinnedHeaderListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    /**
     * Adapter interface.  The list adapter must implement this interface.
     */
    public interface PinnedHeaderAdapter {
    	   /**
         * Pinned header state: don't show the header.
         */
        int PINNED_HEADER_GONE = 0;

        /**
         * Pinned header state: show the header at the top of the list.
         */
        int PINNED_HEADER_VISIBLE = 1;

        /**
         * Pinned header state: show the header. If the header extends beyond
         * the bottom of the first shown element, push it up and clip.
         */
        int PINNED_HEADER_PUSHED_UP = 2;

        /**
         * Computes the desired state of the pinned header for the given
         * position of the first visible list item. Allowed return values are
         * {@link #PINNED_HEADER_GONE}, {@link #PINNED_HEADER_VISIBLE} or
         * {@link #PINNED_HEADER_PUSHED_UP}.
         * 
         * @param position
         *            当前listview位置
         * @return 返回pinned header 状态
         */
        int getPinnedHeaderState(int position);

        /**
         * Configures the pinned header view to match the first visible list item.
         *
         * @param header pinned header view.
         * @param position position of the first visible list item.
         * @param alpha fading of the header view, between 0 and 255.
         */
        void configurePinnedHeader(View header, int position, int alpha);
    }
    
    /**
     * 设置headerview
     * 
     * @param view
     *            headerview
     */
    public void setPinnedHeaderView(View view) {
        mHeaderView = view;

        // Disable vertical fading when the pinned header is present
        // TODO change ListView to allow separate measures for top and bottom fading edge;
        // in this particular case we would like to disable the top, but not the bottom edge.
        if (mHeaderView != null) {
            setFadingEdgeLength(0);
        }
        requestLayout();
    }
    
    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        mAdapter = (PinnedHeaderAdapter) adapter;
    }
    
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHeaderView != null) {
            measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
            mHeaderViewWidth = mHeaderView.getMeasuredWidth();
            mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mHeaderView != null) {
            mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
            configureHeaderView(getFirstVisiblePosition());
        }
    }
    
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mHeaderViewVisible) {
            drawChild(canvas, mHeaderView, getDrawingTime());
        }
    }
    
    
    /**
     * 配置对应位置的headerview
     * 
     * @param position
     *            listview
     */
    public void configureHeaderView(int position) {
        if (mHeaderView == null) {
            return;
        }

        int state = mAdapter.getPinnedHeaderState(position);
        switch (state) {
        case PinnedHeaderAdapter.PINNED_HEADER_GONE:
            mHeaderViewVisible = false;
            break;

        case PinnedHeaderAdapter.PINNED_HEADER_VISIBLE:
            mAdapter.configurePinnedHeader(mHeaderView, position, MAX_ALPHA);
            if (mHeaderView.getTop() != 0) {
                mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
            }
            mHeaderViewVisible = true;
            break;

        case PinnedHeaderAdapter.PINNED_HEADER_PUSHED_UP:
            View firstView = getChildAt(0);
            int bottom = firstView.getBottom();
            // int itemHeight = firstView.getHeight();
            int headerHeight = mHeaderView.getHeight();
            int y;
            int alpha;
            if (bottom < headerHeight) {
                y = (bottom - headerHeight);
                alpha = MAX_ALPHA * (headerHeight + y) / headerHeight;
            } else {
                y = 0;
                alpha = MAX_ALPHA;
            }
            mAdapter.configurePinnedHeader(mHeaderView, position, alpha);
            if (mHeaderView.getTop() != y) {
                mHeaderView.layout(0, y, mHeaderViewWidth, mHeaderViewHeight + y);
            }
            mHeaderViewVisible = true;
            break;

        default:
        }
    }
    
}
