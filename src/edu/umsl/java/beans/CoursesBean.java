package edu.umsl.java.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class CoursesBean {
	ArrayList<Integer> courseIds = new ArrayList<Integer>();
	ArrayList<String> courseCodes = new ArrayList<String>();
	ArrayList<String> courseTitles = new ArrayList<String>();
	ArrayList<String> years = new ArrayList<String>();
	ArrayList<String> semesters = new ArrayList<String>();
	ArrayList<Timestamp> datesCoursesWereCreated = new ArrayList<Timestamp>();
	
	public CoursesBean(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				courseIds.add(new Integer(resultSet.getInt("course_id")));
				courseCodes.add(resultSet.getString("course_code"));
				courseTitles.add(resultSet.getString("course_title"));
				years.add(resultSet.getString("years"));
				semesters.add(resultSet.getString("semesters"));
				datesCoursesWereCreated.add(resultSet.getTimestamp("dateCourseWasCreated"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> getCourseIds() {
		return courseIds;
	}
	
	public ArrayList<String> getCourseCodes() {
		return courseCodes;
	}
	
	public ArrayList<String> getCourseTitles() {
		return courseTitles;
	}
	
	public ArrayList<String> getYears() {
		return years;
	}
	
	public ArrayList<String> getSemesters() {
		return semesters;
	}
	
	public ArrayList<Timestamp> getDatesCoursesWereCreated() {
		return datesCoursesWereCreated;
	}
}
