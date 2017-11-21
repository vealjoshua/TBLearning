package edu.umsl.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDB {
	// Database credentials
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tblearning_db";
    private static final String USER = "root";
    private static final String PASS = "";
    private Connection connection;
    
	public CourseDB() {
		try {
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// Open a connection
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} 
		catch(SQLException se) {
	     //Handle errors for JDBC
	     se.printStackTrace();
		} 
		catch(Exception e) {
	     //Handle errors for Class.forName
	     e.printStackTrace();
		}
	}
	
	public void addCourse(String courseCode, String courseTitle, String year, String semester, String instructorId) {
		String query = "INSERT INTO course (course_code, course_title, year, semester, instructor_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, courseCode);
	        preparedStatement.setString(2, courseTitle);
	        preparedStatement.setString(3, year);
	        preparedStatement.setString(4, semester);
	        preparedStatement.setString(5, instructorId);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCourse(String courseCode, String newCourseCode, String courseTitle, String year, String semester, String instructorId) {
		String query = "UPDATE course SET course_code = ?, course_title = ?, year = ?, semester = ?, instructor_id = ? WHERE course_code = ?";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, newCourseCode);
	        preparedStatement.setString(2, courseTitle);
	        preparedStatement.setString(3, year);
	        preparedStatement.setString(4, semester);
	        preparedStatement.setString(5, instructorId);
	        preparedStatement.setString(6, courseCode);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getCoursesData() {
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM course";
	        PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
