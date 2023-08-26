package com.cdac.attendance.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.attendance.dao.AttendanceDao;
import com.cdac.attendance.dao.StudentDao;
import com.cdac.attendance.model.Attendance;
import com.cdac.attendance.model.Student;
import com.cdac.attendance.model.StudentAttendance;
import com.cdac.attendance.service.EmployeeService;
import com.cdac.attendance.service.StudentService;
import com.cdac.attendance.utility.ApplicationConstants;
import com.cdac.attendance.utility.ExcelGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EmployeeController {

	@Autowired(required = true)
	private EmployeeService employeeService;

	@Autowired(required = true)
	private ExcelGenerator excelGenerator;

	@Autowired(required = true)
	private StudentService studentService;

	@Autowired
	private StudentDao studentDao;
	
	@Autowired(required = true)
	private AttendanceDao attendanceDao;

	@GetMapping(value={"/home-page","/"})
	public String getHomePage(Model model) {
		return "home-page";
	}

	@GetMapping("/download-excel-sheet")
	public ResponseEntity<Resource> generateExcelSheet() throws IOException {
		employeeService.generateExcelSheet();
		String filename = "Attendance.xlsx";
		List<StudentAttendance> studentAttendanceList = studentService.getAllStudents();
		ExcelGenerator excelGenerator = new ExcelGenerator(studentAttendanceList);
//		ExcelGenerator excelGenerator = new ExcelGenerator(new ArrayList<StudentAttendance>());
		excelGenerator.writeHeader(studentAttendanceList);
		excelGenerator.write();
		InputStreamResource file = new InputStreamResource(excelGenerator.load());
//		System.err.println(excelGenerator.workbook);
//		FileOutputStream fileOut = new FileOutputStream("X:\\Sping\\CDAC-Attendance\\src\\main\\resources\\static\\Excel-sheet\\attendance.xlsx");
//		excelGenerator.workbook.write(fileOut);
//		return null;
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
	        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
	        .body(file);
	}
//	
//	@GetMapping("/download")
//	  public ResponseEntity<Resource> getFile() {
//	    String filename = "tutorials.xlsx";
//	    InputStreamResource file = new InputStreamResource(fileService.load());
//
//	    return ResponseEntity.ok()
//	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//	        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//	        .body(file);
//	  }

	@GetMapping("attendance-home-page")
	public String AttendanceHomePage(Model model) {
		model.addAttribute("subjectList", ApplicationConstants.SUBJECT_NAME);
		model.addAttribute("centerList", ApplicationConstants.CENTER_NAME);
		return "attendance-home-page";
	}

	@PostMapping("mark-attendance")
	public String getAccountInfo(@RequestParam("subjectName") String subjectName,
			@RequestParam("date") String date, @RequestParam("centerName") String centerName, Model model)
			throws ParseException {
//		System.err.println("subject = " + subjectName);
//		System.err.println("date = " + date);
//		System.err.println("centerName = " + centerName);
		ApplicationConstants.attendanceDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		List<StudentAttendance> StudentAttendanceList = studentService.getAllStudentsForAttendance(subjectName,
				ApplicationConstants.attendanceDate, centerName);
//		System.err.println(StudentAttendanceList);
		model.addAttribute("studentList", StudentAttendanceList);
		Map<String, StudentAttendance> studentMap = new LinkedHashMap<String, StudentAttendance>();
		int i = 0;
		for (StudentAttendance student : StudentAttendanceList) {
			studentMap.put(Integer.toString(i++), student);
		}
//		System.err.println(studentMap);
		model.addAttribute("studentMap", studentMap);
		model.addAttribute("subjectName", subjectName);
		model.addAttribute("attendanceDate", date);
		model.addAttribute("centerName", centerName);
//		System.err.println(subjectDao.findAll());
		model.addAttribute("subjectList", ApplicationConstants.SUBJECT_NAME);
		return "mark-student-attendance";
	}

	@GetMapping("/search-absent-students")
	public String searchAbsentStudents(Model model) {
		model.addAttribute("subjectList", ApplicationConstants.SUBJECT_NAME);
		model.addAttribute("centerList", ApplicationConstants.CENTER_NAME);
		return "search-absent-students";
	}

	@PostMapping("/absent-student-list")
	public String absentStudentList(@RequestParam("subjectName") String subjectName, @RequestParam("date") String date,
			@RequestParam("centerName") String centerName, Model model) throws ParseException {
//		System.err.println("subject = " + subjectName);
//		System.err.println("date = " + date);
//		System.err.println("centerName = " + centerName);
		ApplicationConstants.attendanceDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		List<StudentAttendance> StudentAttendanceList = studentService.getAllStudentsForAttendance(subjectName,
				ApplicationConstants.attendanceDate, centerName);
//		System.err.println(StudentAttendanceList);
		List<StudentAttendance> filteredstudentAttendanceList = null;
		if (StudentAttendanceList != null) {
			filteredstudentAttendanceList = ((Stream<StudentAttendance>) StudentAttendanceList.stream()
					.filter(x -> x.getAttendance().isStatus() == false)).collect(Collectors.toList());
		}
		if(StudentAttendanceList.size()!=filteredstudentAttendanceList.size())
		{
			model.addAttribute("studentList", filteredstudentAttendanceList);
			model.addAttribute("attendanceRemark", "");
		}
		else
			model.addAttribute("attendanceRemark", "Attendance Of Subject :"+subjectName+"  Not Marked for Date :"+date);
//		System.err.println(filteredstudentAttendanceList);
		model.addAttribute("subjectName", subjectName);
		model.addAttribute("attendanceDate", date);
		model.addAttribute("centerName", centerName);
		return "absent-student-list";
	}

	@GetMapping("/upload-student-sheet")
	public String uploadStudentSheet(Model model) {
		model.addAttribute("monthList", ApplicationConstants.MONTH_LIST);
		return "upload-student-list";
	}

//	@GetMapping("/login-page")
//	public String getLoginPage(Model model)
//	{
//		return "login";
//	}

	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public String uploadMultipart(Model model, @RequestParam("file") MultipartFile file,
			@RequestParam("month") String month, @RequestParam("year") String year) throws IOException {
//        try {
//        	transactionList= CsvUtils.read(Transaction.class, file.getInputStream());
//		System.err.println(file + " " + month + " " + year);
		List<Student> studentList = new ArrayList<Student>();
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);

		for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) {
			Student student = new Student();

			XSSFRow row = worksheet.getRow(i);
//			        student.setPrn( Integer.toString(new Date().getYear()+1900).substring(2)+(Integer.toString(new Date().getMonth()).length()==1 ? "0"+Integer.toString(new Date().getMonth()) : Integer.toString(new Date().getMonth())) +Integer.toString((int) row.getCell(0).getNumericCellValue()));
			student.setPrn(year.substring(2) + "0" + (ApplicationConstants.MONTH_LIST.indexOf(month) + 1)
					+ Integer.toString((int) row.getCell(0).getNumericCellValue()));
			student.setStudentName(row.getCell(1).getStringCellValue());
			if (Integer.toString((int) row.getCell(0).getNumericCellValue()) != null
					&& Integer.toString((int) row.getCell(0).getNumericCellValue()).startsWith("405"))
				student.setCenterName("Juhu");
			else
				student.setCenterName("Kharghar");
			studentList.add(student);
		}
		studentService.saveAllStudnets(studentList);
//		System.err.println("studentList = " + studentList);

//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		model.addAttribute("studentList", studentList);
		return "student-list";
	}

	@GetMapping("/get-account-information")
	public String getAccountInfo(Model model) {
		List<StudentAttendance> StudentAttendanceList = studentService.getAllStudents();
//		System.err.println(StudentAttendanceList);
		model.addAttribute("studentList", StudentAttendanceList);
		Map<String, StudentAttendance> studentMap = new LinkedHashMap<String, StudentAttendance>();
		int i = 0;
		for (StudentAttendance student : StudentAttendanceList) {
			studentMap.put(Integer.toString(i++), student);
		}
//		System.err.println(studentMap);
		model.addAttribute("studentMap", studentMap);
//		System.err.println(subjectDao.findAll());
		model.addAttribute("subjectList", ApplicationConstants.SUBJECT_NAME);
		return "get-account-information";
	}

	@PostMapping("save-attendance")
	@ResponseBody
	public String saveAttendance(@RequestBody String studentListObject, Model model) {
		try {
//			System.err.println(studentListObject);
			ObjectMapper mapper = new ObjectMapper();
			List<StudentAttendance> studentAttendanceList = new ArrayList<>();
			try {
				studentAttendanceList = Arrays.asList(mapper.readValue(studentListObject, StudentAttendance[].class));
			} catch (Exception e) {
			}

//			System.err.println(studentAttendanceList);
			List<Attendance> attendanceList = new ArrayList<Attendance>();
			for (StudentAttendance studentAttendance : studentAttendanceList) {
				studentAttendance.getAttendance().setAttendanceDate(ApplicationConstants.attendanceDate);
				attendanceList.add(studentAttendance.getAttendance());
			}

			attendanceDao.saveAll(attendanceList);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Falied";
		}
	}
	
	@GetMapping("student-list")
	public String studentList(Model model)
	{
		model.addAttribute("centerList", ApplicationConstants.CENTER_NAME);
		return "search-student-list";
	}
	
	@PostMapping("student-list")
	public String studentList(@RequestParam("centerName") String centerName,Model model)
	{
//		System.err.println(centerName);
		List<Student> studentList=studentDao.findByCenterName(centerName);
//		System.err.println(studentList);
		model.addAttribute("studentList", studentList);
		return "student-list";
	}

}
