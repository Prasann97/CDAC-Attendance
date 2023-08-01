package com.cdac.attendance.model;

import java.util.HashMap;
import java.util.List;

public class StudentAttendance {

	private Student student;
	private String subjectName;
	private Attendance attendance;
	private List<Attendance> dateWiseStudentAttendance;
	private HashMap<String, Integer> totalStudentwiseAttendance;
	private int totalAttendance;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	public List<Attendance> getDateWiseStudentAttendance() {
		return dateWiseStudentAttendance;
	}

	public void setDateWiseStudentAttendance(List<Attendance> dateWiseStudentAttendance) {
		this.dateWiseStudentAttendance = dateWiseStudentAttendance;
	}

	public HashMap<String, Integer> getTotalStudentwiseAttendance() {
		return totalStudentwiseAttendance;
	}

	public void setTotalStudentwiseAttendance(HashMap<String, Integer> totalStudentwiseAttendance) {
		this.totalStudentwiseAttendance = totalStudentwiseAttendance;
	}

	public int getTotalAttendance() {
		return totalAttendance;
	}

	public void setTotalAttendance(int totalAttendance) {
		this.totalAttendance = totalAttendance;
	}

	@Override
	public String toString() {
		return "StudentAttendance [student=" + student + ", subjectName=" + subjectName + ", attendance=" + attendance
				+ ", dateWiseStudentAttendance=" + dateWiseStudentAttendance + ", totalStudentwiseAttendance="
				+ totalStudentwiseAttendance + ", totalAttendance=" + totalAttendance + "]";
	}

}
