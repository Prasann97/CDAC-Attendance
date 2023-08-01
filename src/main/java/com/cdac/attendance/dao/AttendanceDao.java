package com.cdac.attendance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.attendance.model.Attendance;

public interface AttendanceDao extends JpaRepository<Attendance, Long>{

}
