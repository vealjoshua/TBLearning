package edu.umsl.java.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class QuestionsBean {
	ArrayList<Integer> questionIds = new ArrayList<Integer>();
	ArrayList<String> contents = new ArrayList<String>();
	ArrayList<Integer> options = new ArrayList<Integer>();
	ArrayList<String> optionAs = new ArrayList<String>();
	ArrayList<String> optionBs = new ArrayList<String>();
	ArrayList<String> optionCs = new ArrayList<String>();
	ArrayList<String> optionDs = new ArrayList<String>();
	ArrayList<String> optionEs = new ArrayList<String>();
	ArrayList<String> optionFs = new ArrayList<String>();
	ArrayList<String> answers = new ArrayList<String>();
	ArrayList<Timestamp> datesQuestionsWereCreated = new ArrayList<Timestamp>();
	ArrayList<String> instructorIds = new ArrayList<String>();

	public QuestionsBean(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				questionIds.add(new Integer(resultSet.getInt("question_id")));
				contents.add(resultSet.getString("content"));
				options.add(resultSet.getInt("option"));
				optionAs.add(resultSet.getString("option_a"));
				optionBs.add(resultSet.getString("option_b"));
				optionCs.add(resultSet.getString("option_c"));
				optionDs.add(resultSet.getString("option_d"));
				optionEs.add(resultSet.getString("option_e"));
				optionFs.add(resultSet.getString("option_f"));
				answers.add(resultSet.getString("answer"));
				datesQuestionsWereCreated.add(resultSet.getTimestamp("date_question_was_created"));
				instructorIds.add(resultSet.getString("instructor_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> getQuestionIds() {
		return questionIds;
	}
	
	public ArrayList<String> getContents() {
		return contents;
	}
	
	public ArrayList<Integer> getOptions() {
		return options;
	}
	
	public ArrayList<String> optionAs() {
		return optionAs;
	}
	
	public ArrayList<String> optionBs() {
		return optionBs;
	}
	
	public ArrayList<String> optionCs() {
		return optionCs;
	}
	
	public ArrayList<String> optionDs() {
		return optionDs;
	}
	
	public ArrayList<String> optionEs() {
		return optionEs;
	}
	
	public ArrayList<String> optionFs() {
		return optionFs;
	}
	
	public ArrayList<String> answers() {
		return answers;
	}
	
	public ArrayList<Timestamp> getDatesQuestionsWereCreated() {
		return datesQuestionsWereCreated;
	}
	
	public ArrayList<String> getInstructorIds() {
		return instructorIds;
	}
}
