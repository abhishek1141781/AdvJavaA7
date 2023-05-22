package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import pojos.Candidate;
import pojos.User;

/**
 * Servlet implementation class CandidateListServlet
 */
@WebServlet("/list")
public class CandidateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// 1. get HttpSession from WC
			HttpSession session = request.getSession();
			// 2. get user details from sessiom
			User userDetails = (User) session.getAttribute("user_info");
			if (userDetails != null) {
				pw.print("<h4> Hello ,  " + userDetails.getFirstName() + " " + userDetails.getLastName() + "</h4>");
				// get candidate dao instance from session
				CandidateDaoImpl dao = (CandidateDaoImpl) session.getAttribute("candidate_dao");
				// invoke dao's method to get list of all candidates
				List<Candidate> candidates = dao.getAllCandidates();
				// dyn form generation
				pw.print("<form action='logout'>");
				for (Candidate c : candidates)
					pw.print("<h5> <input type='radio' name='cid' value='" + c.getCandidateId() + "'>" + c.getName()
							+ "</h5>");
				pw.print("<input type='submit' value='Cast A Vote'>");
				pw.print("</form>");

			} else
				pw.print("<h4>No Cookies : Session Tracking Failed!!!!!!!!!!!!!!!!!!!!!!</h4>");
		} catch (Exception e) {
			throw new ServletException("err in do-get of " + getClass(), e);
		}
	}

}
