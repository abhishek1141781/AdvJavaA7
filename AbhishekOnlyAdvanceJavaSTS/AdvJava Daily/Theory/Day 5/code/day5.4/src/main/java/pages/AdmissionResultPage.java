package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojos.Student;

/**
 * Servlet implementation class AdmissionResultPage
 */
@WebServlet("/result")
public class AdmissionResultPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do-post of " + getClass());
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
			// retrieve student details from CURRENT request scope
			Student student1 = (Student) request.getAttribute("student_dtls");
			// greet the user
			pw.print("<h4> Hello ," + student1.getFirstName() + " " + student1.getLastName() + "</h4>");
			if (student1.isAdmissionStatus())
				pw.print("<h4> Congratulations !!!, Welcome to " + student1.getChosenCourse() + "</h4>");
			else
				pw.print("<h4> Sorry ! Admission rejected for  " + student1.getChosenCourse() + "</h4>");
		
	}

}
