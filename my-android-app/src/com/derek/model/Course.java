package com.derek.model;

import java.io.Serializable;
import java.util.List;

public class Course  implements Serializable, Cloneable{
	
	private String courseName;
	
	private String teacher;
	
	private String courseType;
	
	private Integer schoolId;
	
	private String schoolName;
	
	private Integer startWeek;
	
	private List<CourseTime> courseTimeList;
	
	public Course(){
		super();
	}
	

	public Course(String courseName, String teacher, String courseType,
			Integer schoolId, String schoolName, Integer startWeek,
			List<CourseTime> courseTimeList) {
		super();
		this.courseName = courseName;
		this.teacher = teacher;
		this.courseType = courseType;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.startWeek = startWeek;
		this.courseTimeList = courseTimeList;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getStartWeek() {
		return startWeek;
	}

	public void setStartWeek(Integer startWeek) {
		this.startWeek = startWeek;
	}

	public List<CourseTime> getCourseTimeList() {
		return courseTimeList;
	}

	public void setCourseTimeList(List<CourseTime> courseTimeList) {
		this.courseTimeList = courseTimeList;
	}
	
	
	
	
	
	
	

}
