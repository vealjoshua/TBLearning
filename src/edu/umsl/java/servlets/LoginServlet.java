package edu.umsl.java.servlets;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import edu.umsl.java.beans.LoginBean2;
import edu.umsl.java.beans.SendEmail;
import edu.umsl.java.beans.UserBean;
import edu.umsl.java.database.CourseDB;
import edu.umsl.java.database.LoginDB;
import edu.umsl.java.database.QuestionDB;
import edu.umsl.java.database.QuizDB;
import edu.umsl.java.database.RegistrationDB;
import edu.umsl.java.database.TopicDB;

@WebServlet("/Servlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    // A GET request results from a normal request for a URL or from an HTML form that has no METHOD specified 
    // and it should be handled by doGet() method.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		if (task == "login") { // Login
			String ssoId = request.getParameter("ssoId");
			String password = request.getParameter("password");		
			
			/* Check user name against the database */
			LoginBean2 loginBean = new LoginBean2();
			String query = "SELECT PasswordTracking_SSO, PasswordTracking_TryNO FROM passwordtracking WHERE PasswordTracking_SSO = '"+ssoId+"';";
			ResultSet rs = loginBean.getAllData(query);
			try {
				rs.next();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				if (rs.getString("PasswordTracking_SSO").equalsIgnoreCase(ssoId)) {
					System.out.println("User verified.");
					if(rs.getInt("PasswordTracking_TryNO") < 3) {
						String passQuery = "SELECT * FROM tblusers WHERE TblUsers_SSO = '"+ssoId+"';";
						ResultSet rs2 = loginBean.getAllData(passQuery);
						rs2.next();
						
						if(rs2.getString("TblUsers_UserPass").equals(password)) {
							System.out.println("Login successful.");
							loginBean.UpdatePasswordTrack(ssoId, 0);
							
							rs.close();
							// Start session
							HttpSession session = createSession(request, response, ssoId);						
							request.setAttribute("session", session);

							if (rs2.getString("TblUsers_UsertypeID").equals("ADM")) {
								System.out.println("User is admin.");
								
//							request.getRequestDispatcher("/admin.jsp").forward(request, response);
								
								response.sendRedirect(request.getContextPath() + "/admin.jsp");
							}
							else if(rs.getString("TblUsers_UsertypeID").equals("INS")) {
								response.sendRedirect(request.getContextPath() + "/instructor(1).jsp");
							}
						}
						else {
							System.out.println("Failed login attempt.");
							Integer tryNO = rs.getInt("PasswordTracking_TryNO");
							tryNO += 1;
							loginBean.UpdatePasswordTrack(ssoId, tryNO);
							rs.close();
							rs2.close();
							response.sendRedirect(request.getContextPath() + "/login.jsp");
						}
					}
					else {
						System.out.println("User reached max login attempts.");
						request.setAttribute("reachedFailedLoginAttempts", true);
						rs.close();					
						response.sendRedirect(request.getContextPath() + "/login.jsp");
					}
				}
				else {
					System.out.println("User unknown.");
					rs.close();
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (task == "logout") { // Logout
			HttpSession session = request.getSession(false);
			session.invalidate();
			
//			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//			dispatcher.forward(request, response);
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		else if (task == "userCreation") { // User Creation
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String ssoId = request.getParameter("ssoId");
			String department = request.getParameter("department");
			String userType = request.getParameter("optradio");
			String email = request.getParameter("email");
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			Integer deleted = 0;
			
			// Add user to database
			LoginBean2 loginBean = new LoginBean2();
			loginBean.CreateUser(ssoId, email, firstName, lastName, userType, department, userId, 0);
			
			System.out.println(firstName + " " + lastName + " " + ssoId + " " + department);
			
			SendEmail newEmail = new SendEmail();
			try {
				String subject = "Please create your new password!";
				String body = "Click the link to reset your password.";
				newEmail.email(subject, body, email);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("/admin.jsp").forward(request, response); 
//			response.sendRedirect(request.getContextPath() + "/admin.jsp");
		}
		else if (task == "topicCreation") { // Topic
				String Tobics_Desc = request.getParameter("Tobics_Desc");
				String Tobics_UserID = request.getParameter("Tobics_UserID");
				Integer Tobics_Course_ID = Integer.parseInt(request.getParameter("Tobics_Course_ID"));
				Integer Department_ID = Integer.parseInt(request.getParameter("Department_ID"));
				LoginBean2 loginBean = new LoginBean2();
				loginBean.InsertTobics(Tobics_Desc, Tobics_Course_ID, Tobics_UserID, Department_ID, 0);
				
				request.getRequestDispatcher("/instructor.jsp").forward(request, response);
		}
		else if (task == "quizCreation") { // Quiz Creation
			try {
				String Quiz_Desc = request.getParameter("Quiz_Desc");
				Integer Quiz_Course_ID = Integer.parseInt(request.getParameter("Quiz_Course_ID"));
				String Quiz_User_ID = request.getParameter("Quiz_User_ID");
				String timeLimitString = request.getParameter("timeLimit");
				String startTimeString = request.getParameter("startTime");
				String endTimeString = request.getParameter("endTime");
				
				// Conversions
				DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
				DateFormat timestampFormat = new SimpleDateFormat("yyyy:mm:dd hh:mm:ss");
				Time timeLimit = (Time) timeFormat.parse(timeLimitString);
				Timestamp startTime = (Timestamp) timestampFormat.parse(startTimeString);
				Timestamp endTime = (Timestamp) timestampFormat.parse(endTimeString);
				
				LoginBean2 loginBean = new LoginBean2();
				loginBean.InsertQuizzes(Quiz_Desc, Quiz_Course_ID, Quiz_User_ID, 0, timeLimit, startTime, endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else if (task == "questionCreation") { // Question Creation
			String Question_Desc = request.getParameter("Question_Desc");
			String Question_Type = request.getParameter("Question_Type");
			Integer Question_Tobics_ID = Integer.parseInt(request.getParameter("Question_Tobics_ID"));
			Integer Question_Correct_Answer = Integer.parseInt(request.getParameter("Question_Correct_Answer"));
			Integer Question_Course_ID = Integer.parseInt(request.getParameter("Question_Course_ID"));
			String Question_UserID = request.getParameter("Question_UserID");
			
			LoginBean2 loginBean = new LoginBean2();
			loginBean.InsertQuestions(Question_Desc, Question_Type, Question_Course_ID, Question_Tobics_ID, Question_Correct_Answer, Question_UserID, 0);
		}
		else if (task == "toCreateCourse") {
			LoginBean2 loginBean = new LoginBean2();
			String query = "SELECT * FROM course";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/createcourse.jsp").forward(request, response);
		}
		else if (task == "toCreateTopic") {
			LoginBean2 loginBean = new LoginBean2();
			String query = "SELECT * FROM tobics";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/.jsp").forward(request, response);
		}
		else if (task == "toCreateQuiz") {
			LoginBean2 loginBean = new LoginBean2();
			String query = "SELECT * FROM quizzes";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/.jsp").forward(request, response);
		}
		else if (task == "toCreateQuestion") {
			LoginBean2 loginBean = new LoginBean2();
			String query = "SELECT * FROM questions";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/.jsp").forward(request, response);
		}
		else if (task == "departmentCreation") {
			String Department_Code = request.getParameter("departmentCode");
			String Department_Desc = request.getParameter("departmentDesc");
			String Department_UserID = request.getParameter("departmentUserID");
			LoginBean2 loginBean = new LoginBean2();
			loginBean.InsertDepartments(Department_Code, Department_Desc, Department_UserID, 0);
		}
		else if (task == "toDepartment") {
			LoginBean2 loginBean = new LoginBean2();
			String query = "SELECT * FROM departments";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/.jsp").forward(request, response);
		}
		else if (task == "userTypeCreation") {
			String UsertypeID = request.getParameter("UsertypeID");
			String UserTypeDesc = request.getParameter("UserTypeDesc");
			String UserType_UserID = request.getParameter("UserType_UserID");
			LoginBean2 loginBean = new LoginBean2();
			loginBean.CreateUserType(UsertypeID, UserTypeDesc, UserType_UserID, 0);
		}
		else if (task == "toUserType") {
			LoginBean2 loginBean = new LoginBean2();
			String query = "SELECT * FROM tblusertype";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/.jsp").forward(request, response);
		}
		else if (task == "groupCreation") {
			String Group_Desc = request.getParameter("Group_Desc");
			String Group_UserId = request.getParameter("Group_UserId");
			LoginBean2 loginBean = new LoginBean2();
			loginBean.InsertGroups(Group_Desc, Group_UserId, 0);
		}
		else if (task == "toGroup") {
			LoginBean2 loginBean = new LoginBean2();
			String query = "SELECT * FROM groups";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/.jsp").forward(request, response);
		}
		else if (task == "courseCreation") {
			String Course_code = request.getParameter("Course_code");
			String Course_Desc = request.getParameter("Course_Desc");
			Integer Course_year = Integer.parseInt(request.getParameter("Course_year"));
			String Course_Semster = request.getParameter("Course_Semster");
			String Course_Time = request.getParameter("Course_Time");
			String Course_UserID = request.getParameter("Course_UserID");
			LoginBean2 loginBean = new LoginBean2();
			loginBean.InsertCourse(Course_code, Course_Desc, Course_year, Course_Semster, Course_Time, Course_UserID, 0);
		}
		else if (task == "insertCustomQuiz") {
			Integer CustomQuiz_Status = Integer.parseInt(request.getParameter("CustomQuiz_Status"));
			Integer CustomQuiz_QuisID = Integer.parseInt(request.getParameter("CustomQuiz_QuisID"));
			Integer CustomQuiz_QustionID = Integer.parseInt(request.getParameter("CustomQuiz_QustionID"));
			String CustomQuiz_UserID = request.getParameter("CustomQuiz_UserID");

			LoginBean2 loginBean = new LoginBean2();
			loginBean.InsertCustomQuiz(CustomQuiz_Status, CustomQuiz_QuisID, CustomQuiz_QustionID, CustomQuiz_UserID, 0);
		}
		else if (task == "insertTables_Description") {
			String Table_Name = request.getParameter("Table_Name");
			String Table_UserID = request.getParameter("Table_UserID");

			LoginBean2 loginBean = new LoginBean2();
			loginBean.InsertTables_Description(Table_Name, Table_UserID, 0);
		}
		else if (task == "InsertLinkedTables") {
			Integer LinkedTables_Master = Integer.parseInt(request.getParameter("LinkedTables_Master"));
			Integer LinkedTables_Details = Integer.parseInt(request.getParameter("LinkedTables_Details"));
			String LinkedTables_UserID = request.getParameter("LinkedTables_UserID");

			LoginBean2 loginBean = new LoginBean2();
			loginBean.InsertLinkedTables(LinkedTables_Master, LinkedTables_Details, LinkedTables_UserID, 0);
		}
		else if (task == "InsertActions_Links") {
			Integer Actions_Links_Master = Integer.parseInt(request.getParameter("Actions_Links_Master"));
			Integer Actions_Links_Details = Integer.parseInt(request.getParameter("Actions_Links_Details"));
			Integer Actions_Links_LinkedTables_ID = Integer.parseInt(request.getParameter("Actions_Links_LinkedTables_ID"));
			String Actions_Links_Instructor_ID = request.getParameter("Actions_Links_Instructor_ID");

			LoginBean2 loginBean = new LoginBean2();
			loginBean.InsertActions_Links(Actions_Links_Instructor_ID, Actions_Links_Master, Actions_Links_Details, Actions_Links_LinkedTables_ID, 0);
		}
	}
	
	private HttpSession createSession(HttpServletRequest request, HttpServletResponse response, String ssoId) throws IOException {
		// Create a session object if it is already not created.
	    HttpSession session = request.getSession(true);
	    
	    // This method specifies the time, in seconds, between client requests before the servlet container will invalidate this session.
	    // The default timeout is 30 minutes in Tomcat.
	    session.setMaxInactiveInterval(60);
	    
	    // This method binds an object to this session, using the name specified.
	    session.setAttribute("userName", ssoId);
	    
	    if(session != null) {
		    System.out.println("Login servlet: Session ID: " + session.getId());
	    }
	    
	    return session;
	}

	// A POST request results from an HTML form that specifically lists POST as the METHOD 
	// and it should be handled by doPost() method.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}