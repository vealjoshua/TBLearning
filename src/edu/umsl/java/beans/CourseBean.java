package edu.umsl.java.beans;

import java.sql.Timestamp;

public class CourseBean {
	private Integer courseId;
	private String courseCode;
	private String courseTitle;
	private String year;
	private String Semester;
	private Timestamp dateCourseWasCreated;
	private String instructorId;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSemester() {
		return Semester;
	}
	public void setSemester(String semester) {
		Semester = semester;
	}
	public Timestamp getDateCourseWasCreated() {
		return dateCourseWasCreated;
	}
	public void setDateCourseWasCreated(Timestamp dateCourseWasCreated) {
		this.dateCourseWasCreated = dateCourseWasCreated;
	}
	public String getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}
}
