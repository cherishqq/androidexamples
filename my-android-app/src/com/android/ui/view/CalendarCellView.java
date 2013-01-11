package com.android.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.model.Course;
import com.android.model.CourseTime.SectionTime;


public class CalendarCellView  extends FrameLayout {
	
	private SectionTime sectionTime;
	
	private TextView textView;
	
	private int mWidth;
	
	private int mHeight;
	
	private int day = 0;
	private int section = 0;
	
	private Context mContext ;
	
	private Paint pt = new Paint();
	private RectF rect = new RectF();
	
	
	
	public CalendarCellView(Context context) {
		super(context);
		init(context);
	}

	public CalendarCellView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CalendarCellView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	public CalendarCellView(Context context, int iWidth, int iHeight, int day , int section ){
		super(context);
		this.day = day;
		this.section = section;
/*		String id = "cell" + String.valueOf(this.day) + String.valueOf(this.section);
		setId((new Integer(id)).intValue());*/
		init(context);
		setFocusable(true);	
		setLayoutParams(new LayoutParams(iWidth, iHeight));
	}
	
	private void init(Context context){
		this.mContext = context;
//		setCourseView();
		setWillNotDraw(false);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);		
		rect.set(0, 0, this.getWidth(), this.getHeight());		
		rect.inset(1, 1);
		canvas.drawRect(rect, pt);		
	}
	
	
	public void setCourseView(Course course){
		textView = new TextView(mContext);
		textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		textView.setText(course.getCourseName());
		addView(textView);
	}
	
	
	public void setCourseView(){
		textView = new TextView(mContext);
		textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		textView.setText("大学英语");
		addView(textView);
	}
	
	


}
