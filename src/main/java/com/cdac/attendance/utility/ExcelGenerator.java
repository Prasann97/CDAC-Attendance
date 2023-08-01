package com.cdac.attendance.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.attendance.dao.StudentDao;
import com.cdac.attendance.model.Attendance;
import com.cdac.attendance.model.StudentAttendance;

@Service
public class ExcelGenerator {

	public List<StudentAttendance> studentList;
	public XSSFWorkbook workbook;
	public XSSFSheet CPOS_KH_sheet;
	public XSSFSheet CPOS_JH_sheet;
	public XSSFSheet OOPJ_KH_sheet;
	public XSSFSheet OOPJ_JH_sheet;
	public XSSFSheet ADS_KH_sheet;
	public XSSFSheet ADS_JH_sheet;
	public XSSFSheet DBT_KH_sheet;
	public XSSFSheet DBT_JH_sheet;
	public XSSFSheet SE_KH_sheet;
	public XSSFSheet SE_JH_sheet;
	public XSSFSheet WPT_KH_sheet;
	public XSSFSheet WPT_JH_sheet;
	public XSSFSheet WJP_KH_sheet;
	public XSSFSheet WJP_JH_sheet;
	public XSSFSheet MS_NET_KH_sheet;
	public XSSFSheet MS_NET_JH_sheet;
	public XSSFSheet SDM_KH_sheet;
	public XSSFSheet SDM_JH_sheet;
	public XSSFSheet KH_sheet;
	public XSSFSheet JH_sheet;

	@Autowired
	private StudentDao studentDao;

	public ExcelGenerator(List<StudentAttendance> list) {
		this.studentList = list;
		workbook = new XSSFWorkbook();
		CPOS_KH_sheet = workbook.createSheet(ApplicationConstants.CPOS_KHARGHAR);
		CPOS_JH_sheet = workbook.createSheet(ApplicationConstants.CPOS_JUHU);
		OOPJ_KH_sheet = workbook.createSheet(ApplicationConstants.OOPJ_KHARGHAR);
		OOPJ_JH_sheet = workbook.createSheet(ApplicationConstants.OOPJ_JUHU);
		ADS_KH_sheet = workbook.createSheet(ApplicationConstants.ADS_KHARGHAR);
		ADS_JH_sheet = workbook.createSheet(ApplicationConstants.ADS_JUHU);
		DBT_KH_sheet = workbook.createSheet(ApplicationConstants.DBT_KHARGHAR);
		DBT_JH_sheet = workbook.createSheet(ApplicationConstants.DBT_JUHU);
		SE_KH_sheet = workbook.createSheet(ApplicationConstants.SE_KHARGHAR);
		SE_JH_sheet = workbook.createSheet(ApplicationConstants.SE_JUHU);
		WPT_KH_sheet = workbook.createSheet(ApplicationConstants.WPT_KHARGHAR);
		WPT_JH_sheet = workbook.createSheet(ApplicationConstants.WPT_JUHU);
		WJP_KH_sheet = workbook.createSheet(ApplicationConstants.WJP_KHARGHAR);
		WJP_JH_sheet = workbook.createSheet(ApplicationConstants.WJP_JUHU);
		MS_NET_KH_sheet = workbook.createSheet(ApplicationConstants.MS_NET_KHARGHAR);
		MS_NET_JH_sheet = workbook.createSheet(ApplicationConstants.MS_NET_JUHU);
		SDM_KH_sheet = workbook.createSheet(ApplicationConstants.SDM_KHARGHAR);
		SDM_JH_sheet = workbook.createSheet(ApplicationConstants.SDM_JUHU);
		KH_sheet = workbook.createSheet(ApplicationConstants.KHARGHAR_BATCH);
		JH_sheet = workbook.createSheet(ApplicationConstants.JUHU_BATCH);
	}

	public void writeHeader(List<StudentAttendance> StudentAttendanceList) {
//		OOPJ_KH_sheet = workbook.createSheet(ApplicationConstants.OOPJ_KHARGHAR);
//		CellStyle style = workbook.createCellStyle();
//		XSSFFont font = workbook.createFont();
//		font.setBold(true);
//		font.setFontHeight(16);
//		style.setFont(font);
//		Row row0 = OOPJ_KH_sheet.createRow(0);
//		Row row1 = OOPJ_KH_sheet.createRow(1);
//		createCell(row1, 0, "Web Java Programming", style,OOPJ_KH_sheet);
//		Row row2 = OOPJ_KH_sheet.createRow(2);
//		createCell(row2, 0, "For Reference : P - Present, AB  -Absent , L- P Joined, A-AB Granted", style,OOPJ_KH_sheet);
//		Row row3 = OOPJ_KH_sheet.createRow(3);
//		createCell(row3, 0, "Kharghar List", style,OOPJ_KH_sheet);
//        Row row4 = OOPJ_KH_sheet.createRow(4);
//        int columnCount=0;
//        createCell(row4, columnCount++, "Sr. No.", style,OOPJ_KH_sheet);
//        createCell(row4, columnCount++, "Student Id", style,OOPJ_KH_sheet);
//        createCell(row4, columnCount++, "Student Name", style,OOPJ_KH_sheet);
//        List<Attendance> attendanceList=(List<Attendance>) StudentAttendanceList.get(0).getDateWiseStudentAttendance();
//        for(Attendance attendance:attendanceList)
//        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,OOPJ_KH_sheet);
//        createCell(row4, columnCount++, "Total("+StudentAttendanceList.get(0).getDateWiseStudentAttendance().size()+")", style,OOPJ_KH_sheet);
//        createCell(row4, columnCount++, "%", style,OOPJ_KH_sheet);
		writeSubjectHeaders(StudentAttendanceList,CPOS_KH_sheet);
		writeSubjectHeaders(StudentAttendanceList,CPOS_JH_sheet);
		writeSubjectHeaders(StudentAttendanceList,OOPJ_KH_sheet);
		writeSubjectHeaders(StudentAttendanceList,OOPJ_JH_sheet);
		writeSubjectHeaders(StudentAttendanceList,ADS_KH_sheet);
		writeSubjectHeaders(StudentAttendanceList,ADS_JH_sheet);
		writeSubjectHeaders(StudentAttendanceList,DBT_KH_sheet);
		writeSubjectHeaders(StudentAttendanceList,DBT_JH_sheet);
		writeSubjectHeaders(StudentAttendanceList,SE_KH_sheet);
		writeSubjectHeaders(StudentAttendanceList,SE_JH_sheet);
		writeSubjectHeaders(StudentAttendanceList,WPT_KH_sheet);
		writeSubjectHeaders(StudentAttendanceList,WPT_JH_sheet);
		writeSubjectHeaders(StudentAttendanceList,WJP_KH_sheet);
		writeSubjectHeaders(StudentAttendanceList,WJP_JH_sheet);
		writeSubjectHeaders(StudentAttendanceList,MS_NET_KH_sheet);
		writeSubjectHeaders(StudentAttendanceList,MS_NET_JH_sheet);
		writeSubjectHeaders(StudentAttendanceList,SDM_KH_sheet);
		writeSubjectHeaders(StudentAttendanceList,SDM_JH_sheet);
		writeSubjectHeaders(StudentAttendanceList,KH_sheet);
		writeSubjectHeaders(StudentAttendanceList,JH_sheet);
	}
	
	private void writeSubjectHeaders(List<StudentAttendance> StudentAttendanceList,XSSFSheet sheet)
	{
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(12);
		font.setFontName("Times New Roman");
		style.setFont(font);
//		style.setAlignment(HorizontalAlignment.CENTER);
//	    style.setVerticalAlignment(VerticalAlignment.CENTER);
		sheet.createRow(0);
		Row row2 = sheet.createRow(2);
		createCell(row2, 0, "For Reference : P - Present, AB  -Absent , L- P Joined, A-AB Granted", style,sheet);
		Row row4 = sheet.createRow(4);
        createCell(row4, 0, "Sr. No.", style,sheet);
        createCell(row4, 1, "Student Id", style,sheet);
        createCell(row4, 2, "Student Name", style,sheet);
        if(sheet.equals(CPOS_KH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Logic Building Sessions", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Kharghar List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Kharghar"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("CPOS")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
        else if(sheet.equals(CPOS_JH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Logic Building Sessions", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Juhu List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Juhu"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("CPOS")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
        if(sheet.equals(OOPJ_KH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Object Oriented Programming", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Kharghar List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Kharghar"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("OOPJ")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(OOPJ_JH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Object Oriented Programming", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Juhu List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Juhu"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("OOPJ")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(ADS_KH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Algorithms and Data Strucutres", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Kharghar List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Kharghar"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("ADS")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(ADS_JH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Algorithms and Data Strucutres", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Juhu List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Juhu"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("ADS")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(DBT_KH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Database Management System", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Kharghar List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Kharghar"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("DBT")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(DBT_JH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Database Management System", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Juhu List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Juhu"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("DBT")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(SE_KH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Software Engineering", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Kharghar List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Kharghar"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("SE")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(SE_JH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Software Engineering", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Juhu List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Juhu"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("SE")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(WPT_KH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Web Programming Technology", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Kharghar List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Kharghar"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("WPT")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(WPT_JH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Web Programming Technology", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Juhu List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Juhu"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("WPT")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(WJP_KH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Web Java Programming ", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Kharghar List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Kharghar"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("WJP")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(WJP_JH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Web Java Programming ", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Juhu List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Juhu"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("WJP")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(MS_NET_KH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "MS .NET Technologies", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Kharghar List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Kharghar"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("MS.NET")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(MS_NET_JH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "MS .NET Technologies", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Juhu List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Juhu"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("MS.NET")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(SDM_KH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Software Engineering", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Kharghar List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Kharghar"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("SDM")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(SDM_JH_sheet))
		{
			Row row1 = sheet.createRow(1);
			createCell(row1, 0, "Software Engineering", style,sheet);
			Row row3 = sheet.createRow(3);
			createCell(row3, 0, "Juhu List", style,sheet);
			int columnCount=3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Juhu"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
		        List<Attendance> attendanceList=(List<Attendance>) studentAttendance.getDateWiseStudentAttendance()!=null?studentAttendance.getDateWiseStudentAttendance():new ArrayList<Attendance>();
		        List<Attendance> filteredList=attendanceList.stream().filter(x->x.getSubjectName().equalsIgnoreCase("SDM")).collect(Collectors.toList());
		        for(Attendance attendance:filteredList)
		        	createCell(row4, columnCount++, attendance.getAttendanceDate().toString(), style,sheet);
		        createCell(row4, columnCount++, "Total("+filteredList.size()+")", style,sheet);
		        createCell(row4, columnCount, "%", style,sheet);
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(2,2,0,columnCount));
		        sheet.addMergedRegion(new CellRangeAddress(3,3,0,columnCount));
			}
		}
		else if(sheet.equals(KH_sheet))
		{
			int columnCount = 3;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Kharghar"))
				{
					studentAttendance=s;
					break;
				}
			}
			
			int total=0;
			
			if(studentAttendance!=null)
			{
				createCell(row4, columnCount++, ApplicationConstants.CPOS+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.CPOS)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.OOPJ+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.OOPJ)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.ADS+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.ADS)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.SDM+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.SDM)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.DBT+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.DBT)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.WPT+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.WPT)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.WJP+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.WJP)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.MS_NET+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.MS_NET)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.SE+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.SE)).collect(Collectors.toList()).size()+")", style, sheet);
				total=studentAttendance.getDateWiseStudentAttendance().size();
				
			}
			createCell(row4, columnCount++, "Total(" + total + ")", style, sheet);
			createCell(row4, columnCount, "%", style, sheet);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnCount));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, columnCount));
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, columnCount));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, columnCount));
		}
		else if(sheet.equals(JH_sheet))
		{
			int columnCount = 3;
			int total=0;
			StudentAttendance studentAttendance=null;
			for(StudentAttendance s:StudentAttendanceList)
			{
				if(s.getStudent().getCenterName().equalsIgnoreCase("Juhu"))
				{
					studentAttendance=s;
					break;
				}
			}
			if(studentAttendance!=null)
			{
				createCell(row4, columnCount++, ApplicationConstants.CPOS+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.CPOS)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.OOPJ+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.OOPJ)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.ADS+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.ADS)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.SDM+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.SDM)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.DBT+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.DBT)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.WPT+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.WPT)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.WJP+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.WJP)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.MS_NET+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.MS_NET)).collect(Collectors.toList()).size()+")", style, sheet);
				createCell(row4, columnCount++, ApplicationConstants.SE+"("+studentAttendance.getDateWiseStudentAttendance().stream().filter(x->x.getSubjectName().equalsIgnoreCase(ApplicationConstants.SE)).collect(Collectors.toList()).size()+")", style, sheet);
				total=studentAttendance.getDateWiseStudentAttendance().size();
			}
			createCell(row4, columnCount++, "Total(" + total + ")", style, sheet);
			createCell(row4, columnCount, "%", style, sheet);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnCount));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, columnCount));
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, columnCount));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, columnCount));
		}
	}

	public void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style, XSSFSheet sheet) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
//		if(row.getRowNum()==0)
//			style.setFillBackgroundColor(IndexedColors.RED.getIndex());
//		else if(row.getRowNum()==1)
//			style.setFillBackgroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
//		else if(row.getRowNum()==2)
//			style.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());
//		else if(row.getRowNum()==3)
//			style.setFillBackgroundColor(IndexedColors.INDIGO.getIndex());
		
//		cell.setCellStyle(style);
		if (valueOfCell instanceof Integer) {
			cell.setCellValue((Integer) valueOfCell);
		} else if (valueOfCell instanceof Long) {
			cell.setCellValue((Long) valueOfCell);
		} else if (valueOfCell instanceof String) {
			cell.setCellValue((String) valueOfCell);
		} else if (valueOfCell instanceof Date) {
			cell.setCellValue((String) valueOfCell);
		} else {
			cell.setCellValue((Boolean) valueOfCell);
		}
		cell.setCellStyle(style);
	}

	public void write() {
//		int rowCount = 5;
//		CellStyle style = workbook.createCellStyle();
//		XSSFFont font = workbook.createFont();
//		font.setFontHeight(14);
//		style.setFont(font);
//		int count=1;
////		for (StudentAttendance record : studentList) {
//			for(int i=0;i<studentList.size();i++)
//			{
//				Row row = OOPJ_KH_sheet.createRow(rowCount++);
//				int columnCount = 0;
//				createCell(row, columnCount++, count++, style, OOPJ_KH_sheet);
//				createCell(row, columnCount++, studentList.get(i).getStudent().getPrn(), style, OOPJ_KH_sheet);
//				createCell(row, columnCount++, studentList.get(i).getStudent().getStudentName(), style, OOPJ_KH_sheet);
//				System.err.println(studentList.get(i));
//				int presentCounter = 0;
//				int totalDaysCount = 0;
//				for (Attendance attendance : studentList.get(i).getDateWiseStudentAttendance()) {
//					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, OOPJ_KH_sheet);
//					if (attendance.isStatus())
//						presentCounter++;
//					totalDaysCount++;
//				}
//				createCell(row, columnCount++, presentCounter, style, OOPJ_KH_sheet);
//				if (presentCounter != 0 && totalDaysCount != 0)
//					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, OOPJ_KH_sheet);
//				else
//					createCell(row, columnCount++, 0, style, OOPJ_KH_sheet);
//
////            createCell(row, columnCount++, record.getId(), style);
////            createCell(row, columnCount++, record.getStudentName(), style);
////            createCell(row, columnCount++, record.getEmail(), style);
////            createCell(row, columnCount++, record.getMobileNo(), style);
////		}
//			}
		writeData(CPOS_KH_sheet);
		writeData(CPOS_JH_sheet);
		writeData(OOPJ_KH_sheet);
		writeData(OOPJ_JH_sheet);
		writeData(ADS_KH_sheet);
		writeData(ADS_JH_sheet);
		writeData(DBT_KH_sheet);
		writeData(DBT_JH_sheet);
		writeData(SE_KH_sheet);
		writeData(SE_JH_sheet);
		writeData(WPT_KH_sheet);
		writeData(WPT_JH_sheet);
		writeData(WJP_KH_sheet);
		writeData(WJP_JH_sheet);
		writeData(MS_NET_KH_sheet);
		writeData(MS_NET_JH_sheet);
		writeData(SDM_KH_sheet);
		writeData(SDM_JH_sheet);
		writeData(KH_sheet);
		writeData(JH_sheet);
	}
	
	private void writeData(XSSFSheet sheet)
	{
		int rowCount = 5;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(11);
		font.setFontName("Times New Roman");
		style.setFont(font);
//		style.setAlignment(HorizontalAlignment.CENTER);
//	    style.setVerticalAlignment(VerticalAlignment.CENTER);
		int count=1;
		if(sheet.equals(OOPJ_KH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Kharghar")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("JAVA")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(OOPJ_JH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Juhu")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("JAVA")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(CPOS_KH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Kharghar")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("CPOS")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(CPOS_JH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Juhu")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("CPOS")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(ADS_KH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Kharghar")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("ADS")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(ADS_JH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Juhu")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("ADS")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(DBT_KH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Kharghar")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("DBT")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(DBT_JH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Juhu")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("DBT")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(SE_KH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Kharghar")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("SE")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(SE_JH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Juhu")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("SE")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(WPT_KH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Kharghar")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("WPT")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(WPT_JH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Juhu")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("WPT")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(WJP_KH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Kharghar")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("WJP")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(WJP_JH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Juhu")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("WJP")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(MS_NET_KH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Kharghar")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("MS.NET")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(MS_NET_JH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Juhu")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("MS.NET")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(SDM_KH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Kharghar")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("SDM")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(SDM_JH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Juhu")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				int presentCounter = 0;
				int totalDaysCount = 0;
				List<Attendance> filteredAttendanceList = filteredStudentAttendanceList.get(i)
						.getDateWiseStudentAttendance().stream()
						.filter(x -> x.getSubjectName().equalsIgnoreCase("SDM")).collect(Collectors.toList());
				for (Attendance attendance : filteredAttendanceList) {
					createCell(row, columnCount++, attendance.isStatus() ? "P" : "AB", style, sheet);
					if (attendance.isStatus())
						presentCounter++;
					totalDaysCount++;
				}
				createCell(row, columnCount++, presentCounter, style, sheet);
				if (presentCounter != 0 && totalDaysCount != 0)
					createCell(row, columnCount++, (presentCounter * 100) / totalDaysCount, style, sheet);
				else
					createCell(row, columnCount++, 0, style, sheet);
			}
		}
		else if(sheet.equals(KH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Kharghar")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.CPOS), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.OOPJ), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.ADS), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.SDM), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.DBT), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.WPT), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.WJP), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.MS_NET), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.SE), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalAttendance(), style, sheet);
				createCell(row, columnCount++, (filteredStudentAttendanceList.get(i).getTotalAttendance()*100)/filteredStudentAttendanceList.get(i).getDateWiseStudentAttendance().size(), style, sheet);
			}
		}
		else if(sheet.equals(JH_sheet))
		{
			List<StudentAttendance> filteredStudentAttendanceList =studentList.stream().filter(x->x.getStudent().getCenterName().equalsIgnoreCase("Juhu")).collect(Collectors.toList());
			for (int i = 0; i < filteredStudentAttendanceList.size(); i++) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, count++, style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getPrn(), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getStudent().getStudentName(), style, sheet);
				System.err.println(filteredStudentAttendanceList.get(i));
				
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.CPOS), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.OOPJ), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.ADS), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.SDM), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.DBT), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.WPT), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.WJP), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.MS_NET), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalStudentwiseAttendance().get(ApplicationConstants.SE), style, sheet);
				createCell(row, columnCount++, filteredStudentAttendanceList.get(i).getTotalAttendance(), style, sheet);
				createCell(row, columnCount++, (filteredStudentAttendanceList.get(i).getTotalAttendance()*100)/filteredStudentAttendanceList.get(i).getDateWiseStudentAttendance().size(), style, sheet);
			}
		}
	}


	public ByteArrayInputStream load() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		return in;
	}
}
