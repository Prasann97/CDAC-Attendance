package com.cdac.attendance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String prn;
	private String StudentName;
	private String centerName;
//	private String subjectName;
//	@OneToOne
//	private Attendance attendance;

//	@OneToMany
//	private List<Attendance> dateWiseStudentAttendance;

	public String getPrn() {
		return prn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPrn(String prn) {
		this.prn = prn;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", prn=" + prn + ", StudentName=" + StudentName + ", centerName=" + centerName
				+ "]";
	}

}
