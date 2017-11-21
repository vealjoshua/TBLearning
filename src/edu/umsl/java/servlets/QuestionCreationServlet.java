package edu.umsl.java.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.database.QuestionDB;

@WebServlet("/QuestionCreationServlet")
public class QuestionCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");
		String numberOfQuestionsString = request.getParameter("numberOfQuestions");
		int numberOfQuestions = Integer.parseInt(numberOfQuestionsString);
		String answer = request.getParameter("answer");
		HttpSession session = request.getSession(false);
		UserBean userBean = (UserBean) session.getAttribute("user");
		String optionA = request.getParameter("optionA");
		String optionB = request.getParameter("optionB");
		String optionC = request.getParameter("optionC");
		String optionD = request.getParameter("optionD");
		String optionE = request.getParameter("optionE");
		String optionF = request.getParameter("optionF");
		
		QuestionDB questionDB = new QuestionDB();
		
		switch (numberOfQuestions) {
			case 2:
				questionDB.addQuestion(content, optionA, optionB, answer, userBean.getSsoId());
				break;
			case 3:
				questionDB.addQuestion(content, optionA, optionB, optionC, answer, userBean.getSsoId());
				break;
			case 4:
				questionDB.addQuestion(content, optionA, optionB, optionC, optionD, answer, userBean.getSsoId());
				break;
			case 5:
				questionDB.addQuestion(content, optionA, optionB, optionC, optionD, optionE, answer, userBean.getSsoId());
				break;
			case 6:
				questionDB.addQuestion(content, optionA, optionB, optionC, optionD, optionE, optionF, answer, userBean.getSsoId());
				break;
		}
		
		questionDB.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
