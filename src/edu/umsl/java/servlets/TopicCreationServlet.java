package edu.umsl.java.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.database.TopicDB;

@WebServlet("/TopicCreationServlet")
public class TopicCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topicTitle = request.getParameter("topicTitle");
		String courseId = request.getParameter("courseId");
		
		HttpSession session = request.getSession(false);
		UserBean userBean = (UserBean) session.getAttribute("user");
		
		TopicDB topicBean = new TopicDB();
		topicBean.addTopic(topicTitle, courseId, userBean.getSsoId());
		
		topicBean.close();
		
		request.getRequestDispatcher("/instructor.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
