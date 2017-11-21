package edu.umsl.java.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.umsl.java.beans.UsersBean;
import edu.umsl.java.database.RegistrationDB;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String ssoId = request.getParameter("ssoId");
		String department = request.getParameter("department");
		String userType = request.getParameter("optradio");
		
		RegistrationDB registrationBean = new RegistrationDB();
		// Add user to database
		registrationBean.addUser(ssoId, firstName, lastName);
		
		System.out.println(firstName + " " + lastName + " " + ssoId + " " + department);
		
		// Add user types to database
		switch(userType) {
		case "instructor":
			registrationBean.addInstructor(ssoId, department);
			break;
		case "admin":
			registrationBean.addAdmin(ssoId);
			break;
		case "both":
			registrationBean.addInstructor(ssoId, department);
			registrationBean.addAdmin(ssoId);
			break;
		}
		
		registrationBean.close();
		request.getRequestDispatcher("/admin.jsp").forward(request, response); 
//		response.sendRedirect(request.getContextPath() + "/admin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
