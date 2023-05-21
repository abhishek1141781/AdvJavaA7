package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojos.User;

@WebServlet("/candidate_list")
public class CandidateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// set cont type
		response.setContentType("text/html");
		// get PW
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h4> in candidate list </h4>");
			// retrive validated voter details from the session scope
			// 1. get HS from WC
			HttpSession hs = request.getSession();//existing : provided cookies are enabled
			System.out.println("session is new " + hs.isNew());// f : provided cookies are enabled
			System.out.println("session id " + hs.getId());// SAME session id
			// get attribute
			User voter = (User) hs.getAttribute("user_dtls");
			if (voter != null)
				pw.print("<h4> Hello , " + voter.getFirstName() + " " + voter.getLastName() + "</h4>");

			else //=> no cookies!
				pw.print("<h4> Session Tracking Failed!!!!!! , No cookies: JSESSIONID</h4>");

		}
	}

}
