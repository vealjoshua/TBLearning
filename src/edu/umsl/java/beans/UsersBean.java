package edu.umsl.java.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class UsersBean {
	private ArrayList<String> ssoIds = new ArrayList<String>();
	private ArrayList<String> firstNames = new ArrayList<String>();
	private ArrayList<String> lastNames = new ArrayList<String>();
	private ArrayList<String> adminStatus = new ArrayList<String>();
	private ArrayList<String> instructorStatus = new ArrayList<String>();
	private ArrayList<String> studentStatus = new ArrayList<String>();
	private ArrayList<String> departments = new ArrayList<String>();
	private ArrayList<String> studentNumbers = new ArrayList<String>();
	private ArrayList<Timestamp> dateAccountsWereCreated = new ArrayList<Timestamp>();
	
	public UsersBean(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				ssoIds.add(resultSet.getString("sso_id"));
				firstNames.add(resultSet.getString("first_name"));
				lastNames.add(resultSet.getString("last_name"));
				adminStatus.add(resultSet.getString("admin"));
				instructorStatus.add(resultSet.getString("instructor"));
				studentStatus.add(resultSet.getString("student"));				
				dateAccountsWereCreated.add(resultSet.getTimestamp("date_account_was_created"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getSsoIds() {
		return ssoIds;
	}

	public ArrayList<String> getAdmins() {
		return adminStatus;
	}

	public ArrayList<String> getInstructors() {
		return instructorStatus;
	}

	public ArrayList<String> getStudents() {
		return studentStatus;
	}

	public ArrayList<String> getDepartments() {
		return departments;
	}

	public ArrayList<String> getStudentNumbers() {
		return studentNumbers;
	}

	public ArrayList<Timestamp> getDateAccountsWereCreated() {
		return dateAccountsWereCreated;
	}

	public ArrayList<String> getFirstNames() {
		return firstNames;
	}

	public ArrayList<String> getLastNames() {
		return lastNames;
	}

}
