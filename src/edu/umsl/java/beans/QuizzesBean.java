package edu.umsl.java.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class QuizzesBean {
	private ArrayList<Integer> quizIds = new ArrayList<Integer>();
	private ArrayList<Integer> courseIds = new ArrayList<Integer>();
	private ArrayList<Integer> quizNumbers = new ArrayList<Integer>();
	private ArrayList<Time> timeLimits = new ArrayList<Time>();
	private ArrayList<Timestamp> startTimes = new ArrayList<Timestamp>();
	private ArrayList<Timestamp> endTimes = new ArrayList<Timestamp>();
	private ArrayList<String> instructorIds = new ArrayList<String>();
	
	public QuizzesBean(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				quizIds.add(new Integer(resultSet.getInt("quiz_id")));
				courseIds.add(new Integer(resultSet.getInt("course_id")));
				quizNumbers.add(resultSet.getInt("quiz_number"));
				timeLimits.add(resultSet.getTime("time_limit"));
				startTimes.add(resultSet.getTimestamp("start_time"));
				endTimes.add(resultSet.getTimestamp("end_time"));
				instructorIds.add(resultSet.getString("instructor_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> getQuizIds() {
		return quizIds;
	}
	public ArrayList<Integer> getCourseIds() {
		return courseIds;
	}
	public ArrayList<Integer> getQuizNumbers() {
		return quizNumbers;
	}
	public ArrayList<Time> getTimeLimits() {
		return timeLimits;
	}
	public ArrayList<Timestamp> getStartTimes() {
		return startTimes;
	}
	public ArrayList<Timestamp> getEndTimes() {
		return endTimes;
	}
	public ArrayList<String> getInstructorIds() {
		return instructorIds;
	}
}
