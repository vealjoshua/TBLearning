package edu.umsl.java.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.database.CourseDB;

@WebServlet("/CreateCourseServlet")
public class CourseCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseCode = request.getParameter("courseCode");
		String courseTitle = request.getParameter("courseTitle");
		String year = request.getParameter("year");
		String semester = request.getParameter("semester");
		HttpSession session = request.getSession(false);
		UserBean user = (UserBean) session.getAttribute("user");
		String instructorID = user.getSsoId();
		
		CourseDB createCourse = new CourseDB();
		createCourse.addCourse(courseCode, courseTitle, year, semester, instructorID);
		System.out.println("Course successfully created.");
		
		createCourse.close();
		
		response.sendRedirect(request.getContextPath() + "/createcourse.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
