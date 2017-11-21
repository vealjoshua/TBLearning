package edu.umsl.java.beans;

import java.sql.Timestamp;

public class TopicBean {
	private Integer topicId;
	private String topicTitle;
	private String courseId;
	private Timestamp dateTopicWasCreated;
	private String instructorId;
	
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getTopicTitle() {
		return topicTitle;
	}
	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public Timestamp getDateTopicWasCreated() {
		return dateTopicWasCreated;
	}
	public void setDateTopicWasCreated(Timestamp dateTopicWasCreated) {
		this.dateTopicWasCreated = dateTopicWasCreated;
	}
	public String getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}
}
