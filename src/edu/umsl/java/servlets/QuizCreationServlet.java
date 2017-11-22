package edu.umsl.java.servlets;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.database.QuizDB;

@WebServlet("/QuizCreationServlet")
public class QuizCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String courseId = request.getParameter("courseNumber");
			String quizNumberString = request.getParameter("quizNumber");
//			String newQuizNumberString = request.getParameter("newQuizNumber");
			String timeLimitString = request.getParameter("timeLimit");
			String startTimeString = request.getParameter("startTime");
			String endTimeString = request.getParameter("endTime");
			HttpSession session = request.getSession(false);
			UserBean userBean = (UserBean) session.getAttribute("user");
			
			// Conversions
			int quizNumber = Integer.parseInt(quizNumberString);
//			int newQuizNumber = Integer.parseInt(newQuizNumberString);
			DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
			DateFormat timestampFormat = new SimpleDateFormat("yyyy:mm:dd hh:mm:ss");
			Time timeLimit = (Time) timeFormat.parse(timeLimitString);
			Timestamp startTime = (Timestamp) timestampFormat.parse(startTimeString);
			Timestamp endTime = (Timestamp) timestampFormat.parse(endTimeString);
			
			QuizDB quizDB = new QuizDB();
			quizDB.addQuiz(courseId, quizNumber, timeLimit, startTime, endTime, userBean.getSsoId());
			quizDB.close();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
