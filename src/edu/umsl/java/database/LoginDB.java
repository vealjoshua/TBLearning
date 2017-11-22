package edu.umsl.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB {
	// Database credentials
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tblearning_db";
    private static final String USER = "root";
    private static final String PASS = "";
    private Connection connection;
   
    public LoginDB() {	    
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
    
    public Boolean isUserVerified(String ssoId) {
    		Boolean verifiedUser = false;
		try {			
			String query = "SELECT sso_id, password FROM user WHERE sso_id = ?";
	        
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, ssoId);
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        // Check if username is in database
	        while (resultSet.next())
	        		if (resultSet.getString("sso_id").equalsIgnoreCase(ssoId))
	        			verifiedUser = true;
	        
	        preparedStatement.close();
	        resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return verifiedUser;
    }
    
    public Boolean testPassword(String ssoId, String password) {
    		Boolean loginSuccessful = false;
			try {
	    			String query = "SELECT password FROM user WHERE sso_id = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
	    	        preparedStatement.setString(1, ssoId);
	    	        ResultSet resultSet = preparedStatement.executeQuery();
	    	        
	    	        while(resultSet.next())
	    	        		if (resultSet.getString("password").equals(password))
	    	        			loginSuccessful = true;
	    	        
	    	        preparedStatement.close();
	    	        resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

    		return loginSuccessful;
    }
    
    public void refreshLoginAttempts(String ssoId) {
		try {
    			String query = "UPDATE user SET login_attempts = 0 WHERE sso_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ssoId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    
    public void failedLoginAttempt(String ssoId) {
	    	try {
			String query = "SELECT login_attempts FROM user WHERE sso_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ssoId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			int attempts = resultSet.getInt("login_attempts");
			attempts += 1;
			
			query = "UPDATE user SET login_attempts = ? WHERE sso_id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, attempts);
			preparedStatement.setString(2, ssoId);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    
    public Integer getLoginAttempts(String ssoId) {
		try {
			String query = "SELECT login_attempts FROM user WHERE sso_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ssoId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int attempts = resultSet.getInt("login_attempts");
			preparedStatement.close();
			resultSet.close();
			
			return attempts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (Integer) null;
    }
    
    public Boolean isAdmin(String ssoId) {
        Boolean admin = false;
    		try {				
				// Check user privileges
    			String query = "SELECT sso_id FROM admin WHERE sso_id = ?";
		        
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setString(1, ssoId);
		        ResultSet resultSet = preparedStatement.executeQuery();
		        
		        // Check if user has admin privileges
		        while (resultSet.next())
		        	if (resultSet.getString("sso_id").equalsIgnoreCase(ssoId))
	        			admin = true;
		        
		        preparedStatement.close();
		        resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        
        return admin;
    }
    
    public Boolean isInstructor(String ssoId) {
        Boolean instructor = false;
    		try {				
				// Check user privileges
    			String query = "SELECT sso_id FROM instructor WHERE sso_id = ?";

		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setString(1, ssoId);
		        ResultSet resultSet = preparedStatement.executeQuery();
		        
		        // Check if user has admin privileges
		        while (resultSet.next()) {
		        		if (resultSet.getString("sso_id").equalsIgnoreCase(ssoId))
		        				instructor = true;
		        }
		        
		        preparedStatement.close();
		        resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        
        return instructor;
    }
    
    public Boolean isStudent(String ssoId) {
        Boolean student = false;
    		try {
    			// Check user privileges
    			String query = "SELECT sso_id FROM student WHERE sso_id = ?";
		        
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setString(1, ssoId);
		        ResultSet resultSet = preparedStatement.executeQuery();
		        
		        // Check if user has admin privileges
		        while (resultSet.next()) {
		        		if (resultSet.getString("sso_id").equalsIgnoreCase(ssoId))
		        				student = true;
		        }
		        
		        preparedStatement.close();
		        resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        
        return student;
    }
    
    public void close() {
    		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
