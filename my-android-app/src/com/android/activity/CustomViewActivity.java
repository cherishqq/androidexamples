package com.android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.android.model.Course;
import com.android.model.CourseTime;
import com.android.model.CourseTime.SectionTime;
import com.android.model.CourseTime.WeekDate;
import com.android.ui.view.CalendarView;
import com.android.util.StringUtils;
import com.android.R;


/** 
 *  自定义View
 * 
 * @author Derek.pan
 *
 */
public class CustomViewActivity extends Activity{
	
	private String tag = "CustomViewActivity";
	
	private CalendarView calendarView = null;
	
	private List<Course> courseList = null;
	
	private LinearLayout mainlayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		int screenWidth = display.getWidth();
		
		Log.d(tag, "Activity get width: " + screenWidth);
		
		mainlayout = (LinearLayout) getLayoutInflater().inflate(R.layout.customview,null);
		
		setContentView(mainlayout);
		
		
		List<CourseTime> courseTimeList1 = new ArrayList<CourseTime>();
		
		CourseTime courseTime1 = new CourseTime("东二301",WeekDate.Friday , SectionTime.ONE, SectionTime.TWO );
		CourseTime courseTime2 = new CourseTime("西二401",WeekDate.Wednesday , SectionTime.SEVEN, SectionTime.EIGHT );
		String s = String.valueOf( WeekDate.Friday ) + String.valueOf(SectionTime.ONE) ;
		
		
		courseTimeList1.add(courseTime1);
		courseTimeList1.add(courseTime2);
		Course course1 = new Course("大学英语","derek.pan","1",new Integer(1),"福州大学",new Integer(1),courseTimeList1);
		courseList = new ArrayList<Course>();
	//	courseMap.put( String.valueOf(WeekDate.Friday) + String.valueOf(SectionTime.ONE ), course1);
		courseList.add(course1);
		calendarView = new CalendarView(this,courseList);
		mainlayout.addView(calendarView);
		
	}
	
	
}
