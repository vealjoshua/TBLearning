package edu.umsl.java.beans;

import java.sql.Time;
import java.sql.Timestamp;

public class QuizBean {
	private Integer quizId;
	private Integer courseId;
	private int quizNumber;
	private Time timeLimit;
	private Timestamp startTime;
	private Timestamp endTime;
	private String instructorId;
	
	public Integer getQuizId() {
		return quizId;
	}
	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public int getQuizNumber() {
		return quizNumber;
	}
	public void setQuizNumber(int quizNumber) {
		this.quizNumber = quizNumber;
	}
	public Time getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Time timeLimit) {
		this.timeLimit = timeLimit;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}
}
