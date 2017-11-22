package edu.umsl.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public class QuizDB {
	// Database credentials
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tblearning_db";
    private static final String USER = "root";
    private static final String PASS = "";
    private Connection connection;
    
	public QuizDB() {
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
	
	public void addQuiz (String courseId, int quizNumber, Time timeLimit, Timestamp startTime, Timestamp endTime, String instructorId) {
		String query = "INSERT INTO course (course_id, quiz_number, time_limit, start_time, end_time, instructor_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, courseId);
	        preparedStatement.setInt(2, quizNumber);
	        preparedStatement.setTime(3, timeLimit);
	        preparedStatement.setTimestamp(4, startTime);
	        preparedStatement.setTimestamp(5, endTime);
	        preparedStatement.setString(6, instructorId);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateQuiz (String courseId, int newQuizNumber, Time timeLimit, Timestamp startTime, Timestamp endTime, String instructorId, int quizNumber) {
		String query = "UPDATE course set course_id = ?, quiz_number = ?, time_limit = ?, start_time = ?, end_time = ?, instructor_id = ? WHERE quiz_number = ?";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, courseId);
	        preparedStatement.setInt(2, newQuizNumber);
	        preparedStatement.setTime(3, timeLimit);
	        preparedStatement.setTimestamp(4, startTime);
	        preparedStatement.setTimestamp(5, endTime);
	        preparedStatement.setString(6, instructorId);
	        preparedStatement.setInt(7, quizNumber);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getQuizzesData() {
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
