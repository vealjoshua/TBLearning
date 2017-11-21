package edu.umsl.java.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TopicsBean {
	private ArrayList<Integer> topicIds = new ArrayList<Integer>();
	private ArrayList<String> topicTitles = new ArrayList<String>();
	private ArrayList<String> courseIds = new ArrayList<String>();
	private ArrayList<Timestamp> dateTopicsWereCreated = new ArrayList<Timestamp>();
	private ArrayList<String> instructorIds = new ArrayList<String>();
	
	public TopicsBean(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				topicIds.add(new Integer(resultSet.getInt("topic_id")));
				topicTitles.add(resultSet.getString("topic_title"));
				courseIds.add(resultSet.getString("course_id"));
				instructorIds.add(resultSet.getString("instructor_id"));
				dateTopicsWereCreated.add(resultSet.getTimestamp("date_topic_was_created"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> getTopicIds() {
		return topicIds;
	}
	
	public ArrayList<String> getTopicTitles() {
		return topicTitles;
	}
	
	public ArrayList<String> getCourseIds() {
		return courseIds;
	}
	
	public ArrayList<Timestamp> getDateTopicsWereCreated() {
		return dateTopicsWereCreated;
	}
	
	public ArrayList<String> getInstructorIds() {
		return instructorIds;
	}
}
