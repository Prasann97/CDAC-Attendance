package com.cdac.attendance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.attendance.model.Student;


public interface StudentDao extends JpaRepository<Student, Long>{

//	public List<StudentAttendance> getAllStudents();
//	
//	public List<StudentAttendance> getAllStudentsForAttendance(String subjectName,Date attendanceDate,String centerName);
//	
//	public boolean saveAllStudnets(List<Student> studentList);
//	
	public List<Student> findByCenterName(String centerName);
	
}
