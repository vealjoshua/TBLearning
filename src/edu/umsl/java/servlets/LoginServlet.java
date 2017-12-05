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

import edu.umsl.java.beans.LoginBean;
import edu.umsl.java.beans.SendEmail;

@WebServlet("/Servlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    // A GET request results from a normal request for a URL or from an HTML form that has no METHOD specified 
    // and it should be handled by doGet() method.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		System.out.println("task is:" + task);
		if (task.equals("login")) { // Login
			String ssoId = request.getParameter("ssoId");
			String password = request.getParameter("password");		
			
			/* Check user name against the database */
			LoginBean loginBean = new LoginBean();
			String query = "SELECT * FROM tblusers WHERE TblUsers_SSO = '"+ssoId+"';";
			ResultSet rs = loginBean.getAllData(query);
			try {
				if (rs.next()) {
					System.out.println("User verified.");

					String passQuery = "SELECT PasswordTracking_SSO, PasswordTracking_TryNO FROM passwordtracking WHERE PasswordTracking_SSO = '"+ssoId+"';";
					ResultSet rs2 = loginBean.getAllData(passQuery);
					rs2.next();
							
					if(rs2.getInt("PasswordTracking_TryNO") < 3) {
						
						
						if(rs.getString("TblUsers_UserPass").equals(password)) {
							System.out.println("Login successful.");
							loginBean.UpdatePasswordTrack(ssoId, 0);
							
							rs2.close();
							// Start session
							HttpSession session = createSession(request, response, ssoId);
							request.setAttribute("session", session);

							if (rs.getString("TblUsers_UsertypeID").equals("ADM")) {
								System.out.println("User is admin.");
								String query2 = "SELECT * FROM tblusertype";
								ResultSet rs3 = loginBean.getAllData(query2);
								request.setAttribute("resultSet", rs3);						
								request.getRequestDispatcher("/admin.jsp").forward(request, response);
							}
							else if(rs.getString("TblUsers_UsertypeID").equals("INS")) {
								response.sendRedirect(request.getContextPath() + "/instructor.jsp");
							}
						}
						else {
							System.out.println("Failed login attempt.");
							Integer tryNO = rs2.getInt("PasswordTracking_TryNO");
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
						rs2.close();					
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
		else if (task.equals("logout")) { // Logout
			HttpSession session = request.getSession(false);
			if(session != null)
				session.invalidate();
			
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		else if (task.equals("userCreation")) { // User Creation
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String ssoId = request.getParameter("ssoId");
			String department = request.getParameter("department");
			String userType = request.getParameter("optradio");
			String email = request.getParameter("email");
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			
			// Add user to database
			LoginBean loginBean = new LoginBean();
			loginBean.CreateUser(ssoId, firstName, lastName, userType, department, userId, 0, email);
			loginBean.InsertPasswordTrack(ssoId);
			System.out.println("ssoID: " + ssoId + " firstName: " + firstName + " lastName: " + lastName + " password: " + loginBean.getSaltString() + " userType: " + userType + " department: " + department + " email: " + email + " userID: " + userId);
			
			request.getRequestDispatcher("/admin.jsp").forward(request, response); 
//			response.sendRedirect(request.getContextPath() + "/admin.jsp");
		}
		else if (task.equals("changePasswordLink")) { // Topic
			String email = request.getParameter("email");
			String ssoId = request.getParameter("ssoId");

			HttpSession session = createSession(request, response, ssoId);
			request.setAttribute("session", session);
			

			SendEmail newEmail = new SendEmail();
			try {
				String subject = "Please create your new password!";
				String body = "Click the link to reset your password.";
				newEmail.email(subject, body, email);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		else if (task.equals("changePassword")) {
			HttpSession session = request.getSession(false);
			if (session != null)
			{
				String password = request.getParameter("password");
				String TblUsers_SSO = (String) session.getAttribute("userName");
				
				String query = "UPDATE tblusers SET TblUsers_UserPass = '"+password+"' WHERE TblUsers_SSO = '"+TblUsers_SSO+"';";
				LoginBean loginBean = new LoginBean();
				loginBean.Update_Func(query);
				
				request.getRequestDispatcher("/change_password.jsp").forward(request, response);
			}
			else
				response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		else if (task.equals("topicCreation")) { // Topic
				String Tobics_Desc = request.getParameter("Tobics_Desc");
				HttpSession session = request.getSession(false);
				String Tobics_UserID = (String) session.getAttribute("userName");
				Integer Tobics_Course_ID = Integer.parseInt(request.getParameter("Tobics_Course_ID"));
				Integer Department_ID = Integer.parseInt(request.getParameter("Department_ID"));
				LoginBean loginBean = new LoginBean();
				loginBean.InsertTobics(Tobics_Desc, Tobics_Course_ID, Tobics_UserID, Department_ID, 0);
				
				request.getRequestDispatcher("/instructor.jsp").forward(request, response);
		}
		else if (task.equals("quizCreation")) { // Quiz Creation
			try {
				String Quiz_Desc = request.getParameter("Quiz_Desc");
				Integer Quiz_Course_ID = Integer.parseInt(request.getParameter("Quiz_Course_ID"));
				HttpSession session = request.getSession(false);
				String Quiz_User_ID = (String) session.getAttribute("userName");
				String timeLimitString = request.getParameter("timeLimit");
				String startTimeString = request.getParameter("startTime");
				String endTimeString = request.getParameter("endTime");
				
				// Conversions
				DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
				DateFormat timestampFormat = new SimpleDateFormat("yyyy:mm:dd hh:mm:ss");
				Time timeLimit = (Time) timeFormat.parse(timeLimitString);
				Timestamp startTime = (Timestamp) timestampFormat.parse(startTimeString);
				Timestamp endTime = (Timestamp) timestampFormat.parse(endTimeString);
				
				LoginBean loginBean = new LoginBean();
				loginBean.InsertQuizzes(Quiz_Desc, Quiz_Course_ID, Quiz_User_ID, 0, timeLimit, startTime, endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else if (task.equals("questionCreation")) { // Question Creation
			String Question_Desc = request.getParameter("Question_Desc");
			String Question_Type = request.getParameter("Question_Type");
			Integer Question_Tobics_ID = Integer.parseInt(request.getParameter("Question_Tobics_ID"));
			Integer Question_Correct_Answer = Integer.parseInt(request.getParameter("Question_Correct_Answer"));
			Integer Question_Course_ID = Integer.parseInt(request.getParameter("Question_Course_ID"));
			HttpSession session = request.getSession(false);
			String Question_UserID = (String) session.getAttribute("userName");
			
			LoginBean loginBean = new LoginBean();
			loginBean.InsertQuestions(Question_Desc, Question_Type, Question_Course_ID, Question_Tobics_ID, Question_Correct_Answer, Question_UserID, 0);
		}
		else if (task.equals("toCreateUser")) {
			LoginBean loginBean = new LoginBean();
			String query = "SELECT * FROM tblusers WHERE TblUsers_Deleted = 0";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/registrationform.jsp").forward(request, response);
		}
		else if (task.equals("toCreateCourse")) {
			LoginBean loginBean = new LoginBean();
			String query = "SELECT * FROM course WHERE Course_Deleted = 0";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/course.jsp").forward(request, response);
		}
		else if (task.equals("toStudent")) {
			LoginBean loginBean = new LoginBean();
			String query = "SELECT * FROM tblusers WHERE TblUsers_UserTypeID = 'STD' AND TblUsers_Deleted = 0";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/student_creation.jsp").forward(request, response);
		}
		else if (task.equals("toCreateTopic")) {
			LoginBean loginBean = new LoginBean();
			String query = "SELECT * FROM tobics INNER JOIN course ON tobics.Tobics_Course_ID = course.Course_ID WHERE Tobics_Deleted = 0";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/topics.jsp").forward(request, response);
		}
		else if (task.equals("selectTopicsFromCourse")) {
			Integer Course_ID = Integer.parseInt(request.getParameter("Course_ID"));
			LoginBean loginBean = new LoginBean();
			String query = "SELECT * FROM tobics WHERE Tobics_Course_ID	= "+Course_ID+";";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/topics/.jsp").forward(request, response);
		}
		else if (task.equals("toCreateQuiz")) {
			LoginBean loginBean = new LoginBean();
			String query = "SELECT * FROM quizzes INNER JOIN course ON quizzes.Quiz_Course_ID = course.Course_ID WHERE Quiz_Deleted = 0";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/quiz_creation.jsp").forward(request, response);
		}
		else if (task.equals("toCreateQuestion")) {
			LoginBean loginBean = new LoginBean();
			String query = "SELECT * FROM questions WHERE Question_Deleted = 0";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/questions.jsp").forward(request, response);
		}
		else if (task.equals("departmentCreation")) {
			String Department_Code = request.getParameter("departmentCode");
			String Department_Desc = request.getParameter("departmentDesc");
			HttpSession session = request.getSession(false);
			String Department_UserID = (String) session.getAttribute("userName");
			LoginBean loginBean = new LoginBean();
			loginBean.createDepartments(Department_Code, Department_Desc, Department_UserID, 0);
			
			String query = "SELECT * FROM departments WHERE Department_Deleted = 0";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/department.jsp").forward(request, response);
		}
		else if (task.equals("toDepartment")) {
			LoginBean loginBean = new LoginBean();
			String query = "SELECT * FROM departments WHERE Department_Deleted = 0";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/department.jsp").forward(request, response);
		}
		else if (task.equals("userTypeCreation")) {
			String UsertypeID = request.getParameter("UsertypeID");
			String UserTypeDesc = request.getParameter("UserTypeDesc");
			HttpSession session = request.getSession(false);
			String UserType_UserID = (String) session.getAttribute("userName");
			LoginBean loginBean = new LoginBean();
			loginBean.CreateUserType(UsertypeID, UserTypeDesc, UserType_UserID, 0);
		}
		else if (task.equals("toAdmin")) {
			LoginBean loginBean = new LoginBean();
			String query = "SELECT * FROM tblusertype";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
		}
		else if (task.equals("groupCreation")) {
			String Group_Desc = request.getParameter("Group_Desc");
			HttpSession session = request.getSession(false);
			String Group_UserId = (String) session.getAttribute("userName");
			LoginBean loginBean = new LoginBean();
			loginBean.InsertGroups(Group_Desc, Group_UserId, 0);
		}
		else if (task.equals("toGroup")) {
			LoginBean loginBean = new LoginBean();
			String query = "SELECT * FROM groups WHERE Group_Deleted = 0";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/instructor.jsp").forward(request, response);
		}
		else if (task.equals("toInstructorCourse")) {
			LoginBean loginBean = new LoginBean();
			String query = "SELECT * FROM course WHERE Course_Deleted = 0 LIMIT 5";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/instructor_course.jsp").forward(request, response);
		}
		else if (task.equals("courseCreation")) {
			String Course_code = request.getParameter("Course_code");
			String Course_Desc = request.getParameter("Course_Desc");
			Integer Course_year = Integer.parseInt(request.getParameter("Course_year"));
			String Course_Semester = request.getParameter("Course_Semster");
			String Course_Time = request.getParameter("Course_Time");
			System.out.println(Course_code + " " + Course_Desc + " " + Course_year + " " + Course_Semester + " " + Course_Time + " " + request.getParameter("Course_Department_ID"));
			Integer Couse_Department_ID = Integer.parseInt(request.getParameter("Course_Department_ID"));
			HttpSession session = request.getSession(false);
			String Course_UserID = (String) session.getAttribute("userName");
			System.out.println(Course_UserID);
			LoginBean loginBean = new LoginBean();
			loginBean.InsertCourse(Course_code, Course_Desc, Course_year, Course_Semester, Course_Time, Couse_Department_ID, Course_UserID, 0);
			
			System.out.println("Course Created.");
			
			String query = "SELECT * FROM course WHERE Course_Deleted = 0";
			ResultSet rs = loginBean.getAllData(query);
			request.setAttribute("resultSet", rs);
			request.getRequestDispatcher("/course.jsp").forward(request, response);
		}
		else if (task.equals("insertCustomQuiz")) {
			Integer CustomQuiz_Status = Integer.parseInt(request.getParameter("CustomQuiz_Status"));
			Integer CustomQuiz_QuisID = Integer.parseInt(request.getParameter("CustomQuiz_QuisID"));
			Integer CustomQuiz_QustionID = Integer.parseInt(request.getParameter("CustomQuiz_QustionID"));
			HttpSession session = request.getSession(false);
			String CustomQuiz_UserID = (String) session.getAttribute("userName");

			LoginBean loginBean = new LoginBean();
			loginBean.InsertCustomQuiz(CustomQuiz_Status, CustomQuiz_QuisID, CustomQuiz_QustionID, CustomQuiz_UserID, 0);
		}
		else if (task.equals("InsertTables_Description")) {
			String Table_Name = request.getParameter("Table_Name");
			HttpSession session = request.getSession(false);
			String Table_UserID = (String) session.getAttribute("userName");

			LoginBean loginBean = new LoginBean();
			loginBean.InsertTables_Description(Table_Name, Table_UserID, 0);
		}
		else if (task.equals("InsertLinkedTables")) {
			Integer LinkedTables_Master = Integer.parseInt(request.getParameter("LinkedTables_Master"));
			Integer LinkedTables_Details = Integer.parseInt(request.getParameter("LinkedTables_Details"));
			HttpSession session = request.getSession(false);
			String LinkedTables_UserID = (String) session.getAttribute("userName");

			LoginBean loginBean = new LoginBean();
			loginBean.InsertLinkedTables(LinkedTables_Master, LinkedTables_Details, LinkedTables_UserID, 0);
		}
		else if (task.equals("InsertActionLinks")) {
			Integer Actions_Links_Master = Integer.parseInt(request.getParameter("Actions_Links_Master"));
			Integer Actions_Links_Details = Integer.parseInt(request.getParameter("Actions_Links_Details"));
			Integer Actions_Links_LinkedTables_ID = Integer.parseInt(request.getParameter("Actions_Links_LinkedTables_ID"));
			HttpSession session = request.getSession(false);
			String Actions_Links_Instructor_ID = (String) session.getAttribute("userName");

			LoginBean loginBean = new LoginBean();
			loginBean.InsertActions_Links(Actions_Links_Instructor_ID, Actions_Links_Master, Actions_Links_Details, Actions_Links_LinkedTables_ID, 0);
		}
		else if (task.equals("updateTopic")) {
			String updateDesc = request.getParameter("updateDesc");
			Integer Tobics_ID = Integer.parseInt(request.getParameter("Tobics_ID"));

			String query = "UPDATE tobics SET Tobics_Desc = " + updateDesc + " WHERE Tobics_ID = " + Tobics_ID;
			LoginBean loginBean = new LoginBean();
			loginBean.Update_Func(query);
		}
		else if (task.equals("deleteUser")) {
			String TblUsers_SSO = request.getParameter("TblUsers_SSO");
			String query = "UPDATE tblusers SET TblUsers_Deleted = 1 WHERE TblUsers_SSO = '"+TblUsers_SSO+"';";
			LoginBean loginBean = new LoginBean();
			loginBean.Update_Func(query);
		}
		else if (task.equals("deleteUserType")) {
			String TblUserType_UsertypeID = request.getParameter("TblUserType_UsertypeID");
			String query = "UPDATE tblusertype SET TblUserType_Deleted = 1 WHERE TblUserType_UsertypeID = '"+TblUserType_UsertypeID+"';";
			LoginBean loginBean = new LoginBean();
			loginBean.Update_Func(query);
		}
		else if (task.equals("deleteDepartment")) {
			String Department_ID = request.getParameter("Department_ID");
			String query = "UPDATE departments SET Department_Deleted = 1 WHERE Department_ID = '"+Department_ID+"';";
			LoginBean loginBean = new LoginBean();
			loginBean.Update_Func(query);
		}
		else if (task.equals("deleteCourse")) {
			String Course_code = request.getParameter("Course_code");
			String query = "UPDATE course SET Course_Deleted = 1 WHERE Course_code = '"+Course_code+"';";
			LoginBean loginBean = new LoginBean();
			loginBean.Update_Func(query);
		}
		else if (task.equals("deleteTobic")) {
			String Tobics_ID = request.getParameter("Tobics_ID");
			String query = "UPDATE tobics SET Tobics_Deleted = 1 WHERE Tobics_ID = '"+Tobics_ID+"';";
			LoginBean loginBean = new LoginBean();
			loginBean.Update_Func(query);
		}
		else if (task.equals("deleteQuiz")) {
			String Quiz_ID = request.getParameter("Quiz_ID");
			String query = "UPDATE quizzes SET Quiz_Deleted = 1 WHERE Quiz_ID = '"+Quiz_ID+"';";
			LoginBean loginBean = new LoginBean();
			loginBean.Update_Func(query);
		}
		else if (task.equals("deleteCustomQuiz")) {
			String CustomQuiz_ID = request.getParameter("CustomQuiz_ID");
			String query = "UPDATE customquiz SET CustomQuiz_Deleted = 1 WHERE CustomQuiz_ID = '"+CustomQuiz_ID+"';";
			LoginBean loginBean = new LoginBean();
			loginBean.Update_Func(query);
		}
		else if (task.equals("deleteQuestion")) {
			String Question_ID = request.getParameter("Question_ID");
			String query = "UPDATE questions SET Question_Deleted = 1 WHERE Question_ID = '"+Question_ID+"';";
			LoginBean loginBean = new LoginBean();
			loginBean.Update_Func(query);
		}
		else if (task.equals("deleteGroup")) {
			String Group_ID = request.getParameter("Group_ID");
			String query = "UPDATE groups SET Group_Deleted = 1 WHERE Group_ID = '"+Group_ID+"';";
			LoginBean loginBean = new LoginBean();
			loginBean.Update_Func(query);
		}
		else if (task.equals("deleteActionsLink")) {
			String Actions_Links_Instructor_ID = request.getParameter("Actions_Links_Instructor_ID");
			String query = "UPDATE actions_links SET Actions_Links_Deleted = 1 WHERE Actions_Links_Instructor_ID = '"+Actions_Links_Instructor_ID+"';";
			LoginBean loginBean = new LoginBean();
			loginBean.Update_Func(query);
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
