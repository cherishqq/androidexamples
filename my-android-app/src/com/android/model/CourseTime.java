package com.android.model;


/**
 * �?��的说就是  星期几第几节在哪个教室上�?.
 * @author Derek.pan
 *
 */

public class CourseTime {
	
	public static enum WeekDate {
		Monday, Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
	}
	
	public static enum SectionTime {
		ONE	, TWO, THREE , FOUR, FIVE, SIX, SEVEN, EIGHT,
	}
	
	private String room;
	
	private WeekDate weekDate;
	
	private SectionTime [] sectinTime;
	
	

	 
	public CourseTime(String room, WeekDate courseTime , SectionTime ... sectinTime) {
		super();
		this.room = room;
		this.weekDate = courseTime;
		this.sectinTime = sectinTime;
	}




	public String getRoom() {
		return room;
	}




	public void setRoom(String room) {
		this.room = room;
	}




	public WeekDate getWeekDate() {
		return weekDate;
	}




	public void setWeekDate(WeekDate weekDate) {
		this.weekDate = weekDate;
	}




	public SectionTime[] getSectinTime() {
		return sectinTime;
	}




	public void setSectinTime(SectionTime[] sectinTime) {
		this.sectinTime = sectinTime;
	}

	
	

}
