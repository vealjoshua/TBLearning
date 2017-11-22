package edu.umsl.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class RegistrationDB {
	// Database credentials
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tblearning_db";
    private static final String USER = "root";
    private static final String PASS = "";
    private Connection connection;
        
	public RegistrationDB() {
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
	
	// Add user to database
	public void addUser(String ssoId, String firstName, String lastName) {
		try {
			String query = "INSERT INTO user (sso_id, password, first_name, last_name) VALUES (?, ?, ?, ?)";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ssoId.toLowerCase());
			preparedStatement.setString(2, getSaltString());
			preparedStatement.setString(3, firstName);
	        preparedStatement.setString(4, lastName);
			preparedStatement.executeUpdate();
			System.out.println("User successfully added.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random rnd = new Random();
        char[] pass = new char[17];
        for (int i = 0; i < 17; i++) {
        		int index = (int) (rnd.nextFloat() * SALTCHARS.length());
        		pass[i] = SALTCHARS.charAt(index);
        }
        
        String saltStr = new String(pass);
        return saltStr;
    }
	
	// Add instructor to database
	public void addInstructor(String ssoId, String department) {
		try {
			String query = "INSERT INTO instructor (sso_id, department) VALUES (?, ?)";
	        PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ssoId.toLowerCase());
			preparedStatement.setString(2, department);
			preparedStatement.executeUpdate();
			
			System.out.println("Instructor status given.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Add admin to database
	public void addAdmin(String ssoId) {
		try {
			String query = "INSERT INTO admin (sso_id) VALUES (?)";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ssoId.toLowerCase());
			preparedStatement.executeUpdate();
			
			System.out.println("Admin status given.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getUsersData() {
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM user";
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
