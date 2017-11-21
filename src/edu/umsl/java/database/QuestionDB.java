package edu.umsl.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDB {
	// Database credentials
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tblearning_db";
    private static final String USER = "root";
    private static final String PASS = "";
    private Connection connection;
    
	public QuestionDB() {
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
	
	public void addQuestion (String content, String optionA, String optionB, String answer, String instructorId) {
		String query = "INSERT INTO question (content, options, option_a, option_b, answer, instructor_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, content);
			preparedStatement.setInt(2, 2);
	        preparedStatement.setString(3, optionA);
	        preparedStatement.setString(4, optionB);
	        preparedStatement.setString(5, answer);
	        preparedStatement.setString(6, instructorId);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addQuestion (String content, String optionA, String optionB, String optionC, String answer, String instructorId) {
		String query = "INSERT INTO question (content, options, option_a, option_b, option_c, answer, instructor_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, content);
			preparedStatement.setInt(2, 3);
	        preparedStatement.setString(3, optionA);
	        preparedStatement.setString(4, optionB);
	        preparedStatement.setString(5, optionC);
	        preparedStatement.setString(6, answer);
	        preparedStatement.setString(7, instructorId);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addQuestion (String content, String optionA, String optionB, String optionC, String optionD, String answer, String instructorId) {
		String query = "INSERT INTO question (content, options, option_a, option_b, option_c, option_d, answer, instructor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, content);
			preparedStatement.setInt(2, 4);
	        preparedStatement.setString(3, optionA);
	        preparedStatement.setString(4, optionB);
	        preparedStatement.setString(5, optionC);
	        preparedStatement.setString(6, optionD);
	        preparedStatement.setString(7, answer);
	        preparedStatement.setString(8, instructorId);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addQuestion (String content, String optionA, String optionB, String optionC, String optionD, String optionE, String answer, String instructorId) {
		String query = "INSERT INTO question (content, options, option_a, option_b, option_c, option_d, option_e, answer, instructor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, content);
			preparedStatement.setInt(2, 5);
	        preparedStatement.setString(3, optionA);
	        preparedStatement.setString(4, optionB);
	        preparedStatement.setString(5, optionC);
	        preparedStatement.setString(6, optionD);
	        preparedStatement.setString(7, optionE);
	        preparedStatement.setString(8, answer);
	        preparedStatement.setString(9, instructorId);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addQuestion (String content, String optionA, String optionB, String optionC, String optionD, String optionE, String optionF, String answer, String instructorId) {
		String query = "INSERT INTO question (content, options, option_a, option_b, option_c, option_d, option_e, option_f, answer, instructor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, content);
			preparedStatement.setInt(2, 6);
	        preparedStatement.setString(3, optionA);
	        preparedStatement.setString(4, optionB);
	        preparedStatement.setString(5, optionC);
	        preparedStatement.setString(6, optionD);
	        preparedStatement.setString(7, optionE);
	        preparedStatement.setString(8, optionF);
	        preparedStatement.setString(9, answer);
	        preparedStatement.setString(10, instructorId);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateQuestion (String content, String optionA, String optionB, String answer, String instructorId, int questionId) {
		String query = "UPDATE question SET content = ?, options = ?, option_a = ?, option_b = ?, answer = ?, instructor_id = ? WHERE question_id = ?";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, content);
			preparedStatement.setInt(2, 2);
	        preparedStatement.setString(3, optionA);
	        preparedStatement.setString(4, optionB);
	        preparedStatement.setString(5, answer);
	        preparedStatement.setString(6, instructorId);
	        preparedStatement.setInt(7, questionId);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateQuestion (String content, String optionA, String optionB, String optionC, String answer, String instructorId, int questionId) {
		String query = "UPDATE question SET content = ?, options = ?, option_a = ?, option_b = ?, option_c = ?, answer = ?, instructor_id = ? WHERE question_id = ?";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, content);
			preparedStatement.setInt(2, 3);
	        preparedStatement.setString(3, optionA);
	        preparedStatement.setString(4, optionB);
	        preparedStatement.setString(5, optionC);
	        preparedStatement.setString(6, answer);
	        preparedStatement.setString(7, instructorId);
	        preparedStatement.setInt(8, questionId);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateQuestion (String content, String optionA, String optionB, String optionC, String optionD, String answer, String instructorId, int questionId) {
		String query = "UPDATE question SET content = ?, options = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, answer = ?, instructor_id = ? WHERE question_id = ?";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, content);
			preparedStatement.setInt(2, 4);
	        preparedStatement.setString(3, optionA);
	        preparedStatement.setString(4, optionB);
	        preparedStatement.setString(5, optionC);
	        preparedStatement.setString(6, optionD);
	        preparedStatement.setString(7, answer);
	        preparedStatement.setString(8, instructorId);
	        preparedStatement.setInt(9, questionId);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateQuestion (String content, String optionA, String optionB, String optionC, String optionD, String optionE, String answer, String instructorId, String questionId) {
		String query = "UPDATE question SET content = ?, options = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, option_e = ?, answer = ?, instructor_id = ? WHERE question_id = ?";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, content);
			preparedStatement.setInt(2, 5);
	        preparedStatement.setString(3, optionA);
	        preparedStatement.setString(4, optionB);
	        preparedStatement.setString(5, optionC);
	        preparedStatement.setString(6, optionD);
	        preparedStatement.setString(7, optionE);
	        preparedStatement.setString(8, answer);
	        preparedStatement.setString(9, instructorId);
	        preparedStatement.setString(10, questionId);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateQuestion (String content, String optionA, String optionB, String optionC, String optionD, String optionE, String optionF, String answer, String instructorId, String questionId) {
		String query = "UPDATE question SET content = ?, options = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, option_e = ?, option_f = ?, answer = ?, instructor_id = ? WHERE question_id = ?";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, content);
			preparedStatement.setInt(2, 6);
	        preparedStatement.setString(3, optionA);
	        preparedStatement.setString(4, optionB);
	        preparedStatement.setString(5, optionC);
	        preparedStatement.setString(6, optionD);
	        preparedStatement.setString(7, optionE);
	        preparedStatement.setString(8, optionF);
	        preparedStatement.setString(9, answer);
	        preparedStatement.setString(10, instructorId);
	        preparedStatement.setString(11, questionId);
	        preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getQuestionsData() {
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM question";
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
