<%@page import="pojos.Course"%>
<%@page import="java.time.LocalDate"%>
<%@page import="pojos.Student"%>
<%@page import="java.time.Period"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Admission Form</title>
</head>
<body>
<%
// create student object: by reading, parsing req params
	
	LocalDate date = LocalDate.parse(request.getParameter("dob"));
	Course selectedCourse = Course.valueOf(request.getParameter("course").toUpperCase());
	int score = Integer.parseInt(request.getParameter("score"));
	String firstName = request.getParameter("fn");
	String lastName = request.getParameter("ln");
	
	Student student = new Student(firstName,lastName,selectedCourse,score,date);
	//validate age and score
	int ageInYears = Period.between(date,LocalDate.now()).getYears();
	if(ageInYears<30 && score >= selectedCourse.getMinScore())
		student.setAdmissionStatus(true);
	
// session made once details entered but not needed in jsp
//	HttpSession hs = request.getSession();

	
	session.setAttribute("studentKey", student);
//	request.setAttribute(name, o)
	response.sendRedirect("result.jsp");
		
	
%>
</body>
</html>