package com.cdac.attendance.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.cdac.attendance.model.Attendance;
import com.cdac.attendance.model.Student;
import com.cdac.attendance.model.StudentAttendance;
import com.cdac.attendance.utility.ApplicationConstants;

@Service
public class StudentService {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<StudentAttendance> getAllStudents() {
		List<StudentAttendance> StudentAttendanceList=namedParameterJdbcTemplate.query("select * from student", new RowMapper<StudentAttendance>() {

			@Override
			public StudentAttendance mapRow(ResultSet rs, int rowNum) throws SQLException {
				StudentAttendance studentAttendance = new StudentAttendance();
				Student s=new Student();  
		        s.setPrn(rs.getString("prn"));  
		        s.setStudentName(rs.getString("student_name"));  
		        s.setCenterName(rs.getString("center_name"));  
		        studentAttendance.setStudent(s);
		        Attendance a=new Attendance();
		        a.setPrn(rs.getString("prn"));
		        a.setAttendanceDate(new Date());
		        a.setStatus(false);
		        studentAttendance.setAttendance(a);
				return studentAttendance;
			}
		});
		for(StudentAttendance studentAttendance:StudentAttendanceList)
		{
			List<Attendance> attendanceList = namedParameterJdbcTemplate.query("select * from attendance where prn="+studentAttendance.getStudent().getPrn()+" order by attendance_date", new RowMapper<Attendance>() {
				@Override
				public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
					Attendance attendance = new Attendance();
					attendance.setAttendanceId(rs.getLong("attendance_id"));
					attendance.setAttendanceDate(rs.getDate("attendance_date"));
					attendance.setStatus(rs.getBoolean("status"));
					attendance.setSubjectName(rs.getString("subject_name"));
					attendance.setPrn(rs.getString("prn"));
					return attendance;
				}
			});
			studentAttendance.setDateWiseStudentAttendance(attendanceList);
			HashMap<String, Integer> totalStudentwiseAttendance = new HashMap<String, Integer>();
			int totalAttendance=0;
			totalStudentwiseAttendance.put(ApplicationConstants.CPOS,studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.CPOS)).filter(x->x.isStatus()==true).collect(Collectors.toList()).size());
			totalStudentwiseAttendance.put(ApplicationConstants.OOPJ,studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.OOPJ)).filter(x->x.isStatus()==true).collect(Collectors.toList()).size());
			totalStudentwiseAttendance.put(ApplicationConstants.ADS,studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.ADS)).filter(x->x.isStatus()==true).collect(Collectors.toList()).size());
			totalStudentwiseAttendance.put(ApplicationConstants.SDM,studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.SDM)).filter(x->x.isStatus()==true).collect(Collectors.toList()).size());
			totalStudentwiseAttendance.put(ApplicationConstants.DBT,studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.DBT)).filter(x->x.isStatus()==true).collect(Collectors.toList()).size());
			totalStudentwiseAttendance.put(ApplicationConstants.WPT,studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.WPT)).filter(x->x.isStatus()==true).collect(Collectors.toList()).size());
			totalStudentwiseAttendance.put(ApplicationConstants.WJP,studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.WJP)).filter(x->x.isStatus()==true).collect(Collectors.toList()).size());
			totalStudentwiseAttendance.put(ApplicationConstants.MS_NET,studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.MS_NET)).filter(x->x.isStatus()==true).collect(Collectors.toList()).size());
			totalStudentwiseAttendance.put(ApplicationConstants.SE,studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.SE)).filter(x->x.isStatus()==true).collect(Collectors.toList()).size());
			studentAttendance.setTotalStudentwiseAttendance(totalStudentwiseAttendance);
			for (Map.Entry<String, Integer> entry : totalStudentwiseAttendance.entrySet())
				totalAttendance=totalAttendance+totalStudentwiseAttendance.get(entry.getKey());
			studentAttendance.setTotalAttendance(totalAttendance);
		}
		return StudentAttendanceList;
	}

	public boolean saveAllStudnets(List<Student> studentList) {
		jdbcTemplate.batchUpdate("INSERT INTO student (prn, student_name, center_name) VALUES (?, ?, ?)",
				studentList, studentList.size(), (PreparedStatement ps, Student student) -> {
					ps.setString(1, student.getPrn());
					ps.setString(2, student.getStudentName());
					ps.setString(3, student.getCenterName());
				});
		return false;
	}

	public List<StudentAttendance> getAllStudentsForAttendance(String subjectName, Date attendanceDate,String centerName) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("centerName", centerName);
		List<StudentAttendance> StudentAttendanceList=namedParameterJdbcTemplate.query("select * from student where center_name =:centerName", param, new RowMapper<StudentAttendance>() {

			@Override
			public StudentAttendance mapRow(ResultSet rs, int rowNum) throws SQLException {
				StudentAttendance studentAttendance = new StudentAttendance();
				Student s=new Student();  
		        s.setPrn(rs.getString("prn"));  
		        s.setStudentName(rs.getString("student_name"));  
		        s.setCenterName(rs.getString("center_name"));  
		        studentAttendance.setStudent(s);
//		        Attendance a=new Attendance();
//		        a.setPrn(rs.getString("prn"));
//		        a.setAttendanceDate(new Date());
//		        a.setStatus(false);
//		        studentAttendance.setAttendance(a);
				return studentAttendance;
			}
		});
		
		for(StudentAttendance studentAttendance:StudentAttendanceList)
		{
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("prn", studentAttendance.getStudent().getPrn());
			paramSource.addValue("attendance_date", attendanceDate);
			paramSource.addValue("subject_name", subjectName);
			Attendance attendance;
			try {
				attendance = namedParameterJdbcTemplate.queryForObject("select * from attendance where prn=:prn and attendance_date =:attendance_date and subject_name =:subject_name",paramSource, new RowMapper<Attendance>() {
					@Override
					public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
						Attendance attendance = new Attendance();
						attendance.setAttendanceId(rs.getLong("attendance_id"));
						attendance.setAttendanceDate(rs.getDate("attendance_date"));
						attendance.setStatus(rs.getBoolean("status"));
						attendance.setSubjectName(rs.getString("subject_name"));
						attendance.setPrn(rs.getString("prn"));
						return attendance;
					}
				});
				studentAttendance.setAttendance(attendance);
			} catch (Exception e) {
				Attendance a = new Attendance();
				a.setPrn(studentAttendance.getStudent().getPrn());
		        a.setAttendanceDate(attendanceDate);
		        a.setSubjectName(subjectName);
		        a.setStatus(false);
		        studentAttendance.setAttendance(a);
			}
		}
		
		return StudentAttendanceList;
	}

}
