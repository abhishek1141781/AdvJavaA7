package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojos.Course;
import pojos.Student;

/**
 * Servlet implementation class ProcessAdmissionForm
 */
@WebServlet("/process_form")
public class ProcessAdmissionForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do-post of "+getClass());
		// 1 set resp cont type
		response.setContentType("text/html");
		// 2 : pw
		try (PrintWriter pw = response.getWriter()) {
			// get req params n validate
			String fName = request.getParameter("fn");
			String lName = request.getParameter("ln");
			Course selectedCourse = Course.valueOf(request.getParameter("course").toUpperCase());
			int achievedScore = Integer.parseInt(request.getParameter("score"));
			boolean admitted = false;
			if (achievedScore >= selectedCourse.getMinScore())
				admitted = true;
			// encap details in Student class instance
			Student student = new Student(fName, lName, selectedCourse, achievedScore);
			student.setAdmissionStatus(admitted);
			//add student details in min scope required in server pull : request
			request.setAttribute("student_dtls", student);
			pw.print("<h4>from 1st page....</h4>");
			//pw.flush();
			// server pull : RD --forward technique.
			// 1 get RD
			RequestDispatcher rd = request.getRequestDispatcher("result");
			// forward
			rd.forward(request, response);
			/*
			 * WC suspends doPost of 1st page -- WC clrs the resp buffer(PW's buffer)
			 * WC invokes doPost method of the result page
			 */
			System.out.println("control came back .....");//appears on server side console
			pw.print("<h4>contents after forward....</h4>");
			}

	}

}
