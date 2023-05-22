package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.*;

import pojos.User;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// set cont type
		response.setContentType("text/html");
		// get PW
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h4> in logout Servlet : </h4>");
			// Greeting to voter
			// 1. get HttpSession from WC
			HttpSession session = request.getSession();
			// 2. get user details from session scope
			User user = (User) session.getAttribute("user_dtls");
			pw.print("<h4> Hello ," + user.getFirstName() + "</h4>");
			// 3. chk if voted or not
			if (user.isStatus()) {
				// => alrdy voted
				pw.print("<h4> You have already voted, can't vote again!!!!!!!!!!!!!!!!!!</h4>");
			} else {
				UserDaoImpl userDao = (UserDaoImpl) session.getAttribute("user_dao");
				CandidateDaoImpl candidateDao = (CandidateDaoImpl) session.getAttribute("candidate_dao");
				// update voting sts
				pw.print("<h4>"+userDao.updateVotingStatus(user.getId())+"</h4>");
				// incr votes
				int candidateId=Integer.parseInt(request.getParameter("candidate_id"));
				pw.print("<h4>"+candidateDao.incrementVotes(candidateId)+"</h4>");
			}
			//invalidate Http Session
			session.invalidate();
			pw.print("<h4> You have logged out .....</h4>");

		} catch(Exception e)
		{
			throw new ServletException("err in do-get : "+getClass() ,e);
		}
	}

}
