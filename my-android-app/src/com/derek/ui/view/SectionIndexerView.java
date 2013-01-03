package com.derek.ui.view;



import com.derek.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class SectionIndexerView extends View{

	   /** TAG to Log */
    public static final String TAG = SectionIndexerView.class.getSimpleName();
    /** �Ƿ��debug */
    private static final boolean DEBUG = true;
    /** д���ֵĻ��� */
    private Paint mTextPaint;
    /** ָ��view�Ŀ�� */
    private int mViewWidth = 0;
    /** ָ��view�ĸ߶� */
    private int mViewHeight = 0;
    /** ÿ�����ֵĸ߶� */
    private float mPerTextHeight;
    /** �Ƿ��ǰ���״̬ */
    private boolean mIsPressed = false;
    /** ������listview */
    private ListView mListview = null;
    /** ������indexer */
    private SectionIndexer mIndexer;
    /** ������textview */
    private TextView mView = null;
    /** ����A-Z�����section */
    private String[] mSectionIndexerText = { "#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
    /**ÿ�����ֵĴ�С��ռ5 / 6*/
    private static final float TEXTSIZE_RATIO = 0.84f;
    /** Ĭ�ϵ������С���� */
    private static final int CHAR_WIDTH = 12;
    /** ������͸���� */
    private static final int BG_ALPHA = 153;
    /** ������Բ�ǻ��� */
    private static final float BG_RADIAN = 20f;
    /** Ĭ�ϵ������С */
    private int mCharwidth = CHAR_WIDTH;
    /** Context */
    private Context mContext = null;
    
    /**
     * ���캯��
     * 
     * @param context
     *            Context
     */
    public SectionIndexerView(Context context) {
        super(context);
        init(context);
    }

    /**
     * ���캯��
     * 
     * @param context
     *            Context
     * @param attributeSet
     *            AttributeSet
     */
    public SectionIndexerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    /**
     * ��ʼ��������Ϣ
     * 
     * @param context
     *            Context
     */
    private void init(Context context) {
        mContext = context;
        mCharwidth = (int) (mCharwidth * mContext.getResources().getDisplayMetrics().density);
        this.mTextPaint = new Paint();
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setColor(-1);
    }
    
    

    /**
     * ��ʼ��SectionIndexer,ָ��listview,indexer����ʾsection��textview
     * 
     * @param listview
     *            ������listview
     * @param indexer
     *            ������SectionIndexer
     * @param view
     *            ������TextView
     */
    public void init(ListView listview, SectionIndexer indexer, TextView view) {
        this.mListview = listview;
        this.mIndexer = indexer;
        this.mView = view;
    }
    
    
    @Override
    protected void onDraw(Canvas paramCanvas) {
        Paint localPaint = new Paint();
        localPaint.setAntiAlias(true);
        this.mTextPaint.setColor(Color.parseColor("#75797d"));
        if (this.mIsPressed) {
            localPaint.setColor(Color.parseColor("#838a98"));
            localPaint.setAlpha(BG_ALPHA);
        } else {
        	//0��ʾ��ȫ͸��
            localPaint.setAlpha(0);
        }
       
        this.mTextPaint.setTextSize(this.mViewHeight * TEXTSIZE_RATIO / mSectionIndexerText.length);
        int backgroundPointX = this.mViewWidth - getPaddingRight() - mCharwidth * 3// SUPPRESS CHECKSTYLE:
                - getPaddingLeft();
        paramCanvas.drawRoundRect(new RectF(50, 0.0F, this.mViewWidth,
                this.mViewHeight), BG_RADIAN, BG_RADIAN,
                localPaint);
        int textPointX = mCharwidth + (backgroundPointX + getPaddingLeft());
      //  Log.d(TAG,"mViewWidth="+mViewWidth+",getPaddingRight()="+getPaddingRight()+",getPaddingLeft()="+getPaddingLeft()+",backgroundPointX="+backgroundPointX+"this.mTextPaint.ascent()="+this.mTextPaint.ascent());
        float textPointY = (this.mPerTextHeight - this.mTextPaint.ascent()) / 2.0F;
     //   Log.d(TAG,"textPointY="+textPointY+"mPerTextHeight="+mPerTextHeight);
        int sectionslength = this.mSectionIndexerText.length;
        int currentSection = 0;
        int currentHeight = 0;
        while (true) {
            if (currentSection >= sectionslength) {
                break;
            }
            paramCanvas.drawText(
                    this.mSectionIndexerText[currentSection],
                    textPointX
                            + (mCharwidth - (int) this.mTextPaint
                                    .measureText(this.mSectionIndexerText[currentSection])) / 2,
                    textPointY + (3.0F + currentHeight * this.mPerTextHeight), this.mTextPaint);// SUPPRESS CHECKSTYLE : magic number
            
//            Log.d(TAG,"x="+textPointX
//                    + (mCharwidth - (int) this.mTextPaint
//                            .measureText(this.mSectionIndexerText[currentSection])) / 2+",y="+(textPointY + (3.0F + currentHeight * this.mPerTextHeight))+",sectionslength="+sectionslength);
            ++currentHeight;
            ++currentSection;
        }
        
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        this.mViewWidth = width;

        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        this.mViewHeight = height;
        setMeasuredDimension(this.mViewWidth, this.mViewHeight);
        this.mPerTextHeight = (this.mViewHeight / this.mSectionIndexerText.length);
    }
    
    
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean returnvalue = false;
        switch (motionEvent.getAction()) {
        case MotionEvent.ACTION_DOWN:
            if (DEBUG) {
                Log.d(TAG, "action down!");
            }
            mListview.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
                    SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
            returnvalue = processTouchEvent(motionEvent);
            break;
        case MotionEvent.ACTION_MOVE:
            if (DEBUG) {
                Log.d(TAG, "action move!");
            }
            returnvalue = processTouchEvent(motionEvent);
            break;
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL:
            if (DEBUG) {
                Log.d(TAG, "action up!");
            }
            setPressed(false);
            mView.setText("");
            mView.setVisibility(View.GONE);
            returnvalue = true;
            
            break;
        default:
            break;
        }
        invalidate();
        return returnvalue;
    }

    /**
     * ����onTouch�¼�
     * 
     * @param motionEvent
     *            MotionEvent
     * @return true �����˴����¼���falseû�д���
     */
    private boolean processTouchEvent(MotionEvent motionEvent) {
        String selcected = "";

        int backgroundPointX = this.mViewWidth - getPaddingRight() - mCharwidth * 3// SUPPRESS CHECKSTYLE 
                - getPaddingLeft();
        if (motionEvent.getX() < backgroundPointX) {
            mView.setVisibility(View.GONE);
            setPressed(false);
            return false;
        }
        setPressed(true);
        int curruntSection = (int) (motionEvent.getY() / this.mPerTextHeight);
        if (curruntSection >= 0 && curruntSection <= mSectionIndexerText.length - 1) {
            selcected = mSectionIndexerText[curruntSection];
            mView.setVisibility(View.VISIBLE);
        } else {
            if (curruntSection < 0) {
                selcected = mSectionIndexerText[0];
            } else if (curruntSection > mSectionIndexerText.length) {
                selcected = mSectionIndexerText[mSectionIndexerText.length - 1];
            }
            mView.setVisibility(View.GONE);
        }
        boolean foundedPosition = false;
        for (int i = 0; i < mIndexer.getSections().length; i++) {

            if (selcected.equals(mIndexer.getSections()[i])) {
                mListview.setSelection(mIndexer.getPositionForSection(i));
                foundedPosition = true;
                break;
            }
        }

        if (foundedPosition) {
            mView.setBackgroundResource(R.drawable.section_text_bg);
            mView.setText(selcected);
        } else {
            mView.setBackgroundResource(R.drawable.section_text_gray_bg);
            mView.setText(selcected);
        }
        return true;
    }
    @Override
    public void setPressed(boolean pressed) {
        this.mIsPressed = pressed;
    }

}
