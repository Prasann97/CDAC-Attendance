package com.cdac.attendance.dao;

import java.util.Date;
import java.util.List;

import com.cdac.attendance.model.Student;
import com.cdac.attendance.model.StudentAttendance;


public interface StudentDao{

	public List<StudentAttendance> getAllStudents();
	
	public List<StudentAttendance> getAllStudentsForAttendance(String subjectName,Date attendanceDate,String centerName);
	
	public boolean saveAllStudnets(List<Student> studentList);
	
}
