package edu.umsl.java.beans;

import java.sql.Timestamp;

public class UserBean {
	private String ssoId;
	private Boolean admin;
	private Boolean instructor;
	private Boolean student;
	private String department;
	private String studentNumber;
	private Timestamp dateAccountWasCreated;
	
	public String getSsoId() {
		return ssoId;
	}
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	public Boolean isAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public Boolean isInstructor() {
		return instructor;
	}
	public void setInstructor(Boolean instructor) {
		this.instructor = instructor;
	}
	public Boolean isStudent() {
		return student;
	}
	public void setStudent(Boolean student) {
		this.student = student;
	}
	public Timestamp getDateAccountWasCreated() {
		return dateAccountWasCreated;
	}
	public void setDateAccountWasCreated(Timestamp dateAccountWasCreated) {
		this.dateAccountWasCreated = dateAccountWasCreated;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		if (instructor)
			this.department = department;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		if (student)
			this.studentNumber = studentNumber;
	}
}
