package edu.umsl.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicDB {
	// Database credentials
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tblearning_db";
    private static final String USER = "root";
    private static final String PASS = "";
    private Connection connection;
    
    public TopicDB() {
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
    
    public void addTopic(String topicTitle, String courseId, String ssoId) {
    		try {
        		String query = "INSERT INTO topic (topic_title, course_id, instructor_id) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, topicTitle);
			preparedStatement.setString(2, courseId);
			preparedStatement.setString(3, ssoId);
			preparedStatement.executeUpdate();
    		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void updateTopic(String topicTitle, String newTopicTitle, String courseId, String ssoId) {
		try {
    		String query = "UPDATE topic set topic_title = ?, course_id = ?, instructor_id = ? WHERE topic_title = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, newTopicTitle);
		preparedStatement.setString(2, courseId);
		preparedStatement.setString(3, ssoId);
		preparedStatement.setString(4, topicTitle);
		preparedStatement.executeUpdate();
		} catch (SQLException e) {
		e.printStackTrace();
	}
}
    
    public ResultSet getTopicsData() {
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM topic";
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
