package com.android.ui.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.App;
import com.android.model.Course;
import com.android.model.CourseTime;
import com.android.model.CourseTime.SectionTime;
import com.android.model.CourseTime.WeekDate;

public class CalendarView extends  ScrollView {
	
	private List<Course> courseList ;
	private Context mContext;
	private LinearLayout layContentMain = null;
	private LinearLayout layContent;
	
	
	private String tag = "CalendarView";
	

	
	private int mCellWidth = 0;
	private int mCellHeight = 0;
	
	private int mColumnTitleWidth = 35;
	
	private  String []  weeks = {"一","二","三","四","五","六","日"};
	
	private String [] sections = {"1","2","3","4","5","6","7","8"};
	
	public static Map<WeekDate,Integer> weeksMap = new HashMap<WeekDate,Integer>();
	public static Map<SectionTime,Integer> sectionsMap = new HashMap<SectionTime,Integer>();
	
	static {
		weeksMap.put( WeekDate.Monday ,1 );
		weeksMap.put( WeekDate.Tuesday ,2 );
		weeksMap.put( WeekDate.Wednesday ,3 );
		weeksMap.put( WeekDate.Thursday ,4 );
		weeksMap.put( WeekDate.Friday ,5 );
		weeksMap.put( WeekDate.Saturday ,6 );
		weeksMap.put( WeekDate.Sunday , 7 );
		
		sectionsMap.put(SectionTime.ONE , 1);
		sectionsMap.put(SectionTime.TWO , 2);
		sectionsMap.put(SectionTime.THREE , 3);
		sectionsMap.put(SectionTime.FOUR , 4);
		sectionsMap.put(SectionTime.FIVE , 5);
		sectionsMap.put(SectionTime.SIX , 6);
		sectionsMap.put(SectionTime.SEVEN , 7);
		sectionsMap.put(SectionTime.EIGHT , 8);
	}
	
	
	public CalendarView(Context context) {
		
		super(context);
		init(context);
	}

	public CalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	
	
	public CalendarView(Context context, List<Course> courseList ) {
		super(context);
		this.courseList = courseList;
		init(context);
	
	}
	
	private void init(Context context ){
		this.mContext = context;
		mCellWidth = App.getmWidth()/7 + 1;
		mCellHeight = App.getmHeight()/8;
		this.addView(generateCalendarMain());
	}
	
	private View generateCalendarMain() {
		
		layContentMain = createLayout(LinearLayout.VERTICAL,LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
		layContentMain.addView(generateCalendarHeader());
		layContent = createLayout(LinearLayout.HORIZONTAL,LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		layContent.setWeightSum(1);
		
		layContent.addView(generateCalendarColumnTitle());		
		layContentMain.setBackgroundColor(Color.argb(255, 105, 105, 103));
		for(int i= 1; i<= weeks.length; i++){
			
			SectionTime [] sctionTime = null;
			Course course = null;
			if(courseList != null){
				for( Course c : courseList){
					
					List<CourseTime> courseTimeList = c.getCourseTimeList();
					for(CourseTime ct : courseTimeList ){
						int day = weeksMap.get(ct.getWeekDate()).intValue();
						if( i == day){
							 sctionTime = ct.getSectinTime();
							 course = c;
						}
					}
					}
			}

			
			layContent.addView(generateCalendarColumn(i,sctionTime,course));
		}
		
/*		if(courseList != null ){
			for( Course c : courseList){
				List<CourseTime> courseTimeList = c.getCourseTimeList();
				for(CourseTime ct : courseTimeList ){
					 String weekDate =  String.valueOf(weeksMap.get(ct.getWeekDate()));
					 SectionTime [] sctionTimes = ct.getSectinTime();
					 for(SectionTime time : sctionTimes ){						 
						 String sctionTime = String.valueOf(sectionsMap.get(time));
					 } 
				}
			}
		}*/
		
		layContentMain.addView(layContent);
		
		return layContentMain;
	}
	
	
	/**
	 * 鐢熸垚姣忎竴涓垪
	 * @return
	 */
	private View generateCalendarColumn(int day, SectionTime [] sctionTimes,Course course){
		LinearLayout caleColumnLayout =  createLayout(LinearLayout.VERTICAL,LayoutParams.WRAP_CONTENT,LayoutParams.FILL_PARENT);
		for(int section=1;section<=sections.length; section++ ){
			 
			boolean hasCourse = false;
			  
			 if(sctionTimes != null){
				 for(SectionTime time : sctionTimes ){						 
					 int sctionTime = sectionsMap.get(time).intValue();
					 if(sctionTime == section){
						 hasCourse = true;
					 }
				 } 
			 }

			CalendarCellView cellView = new CalendarCellView(mContext, mCellWidth, mCellHeight,day, section);
			if( hasCourse){
				cellView.setCourseView(course);
			}
			caleColumnLayout.addView(cellView);

		}
		return caleColumnLayout;
	}
	
	/**
	 * 鐢熸垚澶撮儴
	 * @return
	 */
	private View generateCalendarHeader(){
		
		LinearLayout layRow = createLayout(LinearLayout.HORIZONTAL, LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		// layRow.setBackgroundColor(Color.argb(255, 207, 207, 205));
		layRow.setPadding(mColumnTitleWidth, 0 , 0, 0);
		for (int iDay = 0; iDay < weeks.length; iDay++) {
			TextView tv = new TextView(mContext);
			tv.setGravity(Gravity.CENTER);
			tv.setLayoutParams(new LayoutParams(mCellWidth,LayoutParams.WRAP_CONTENT));
			tv.setText(weeks[iDay]);
			layRow.addView(tv);
		}
		
		return layRow;
	}
	
	private View generateCalendarColumnTitle(){
		LinearLayout layColumn = createLayout(LinearLayout.VERTICAL,LayoutParams.WRAP_CONTENT,LayoutParams.FILL_PARENT);
		// layRow.setBackgroundColor(Color.argb(255, 207, 207, 205));
		
		for (int i = 0; i < sections.length; i++) {
			TextView tv = new TextView(mContext);
			tv.setGravity(Gravity.CENTER);
			tv.setLayoutParams(new LayoutParams(mColumnTitleWidth,mCellHeight));
			tv.setText(sections[i]);
			layColumn.addView(tv);
		}
		
		return layColumn;
	}
	
	
	
	// 鐢熸垚甯冨眬
	private LinearLayout createLayout(int iOrientation, int layoutWidth, int layoutHeight) {
					
		LinearLayout lay = new LinearLayout(this.mContext);
		lay.setLayoutParams(new LayoutParams(layoutWidth,layoutHeight));
		lay.setOrientation(iOrientation);
		
		return lay;
	}
	
	
	

}
