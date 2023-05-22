package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.UserDaoImpl;
import pojos.User;

/**
 * Servlet implementation class CandidateListServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
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
			// 2. get user details from session scope
			User user = (User) session.getAttribute("user_info");
			if (user != null) {
				pw.print("<h5> Hello ,"+user.getFirstName()+" "+user.getLastName()+"</h5>");
				// chk voting status
				if (user.isStatus())
					pw.print("<h5>You have already voted !!!!!!!!!!!!</h5>");
				else {
					//voter casted a voted
					int candidateId=Integer.parseInt(request.getParameter("cid"));
					//invoke user dao's method to change voting status
					UserDaoImpl userDao=(UserDaoImpl)session.getAttribute("user_dao");
					pw.print("<h5> "+userDao.changeVotingStatus(user.getUserId())+"</h5>");
					//invoke candidate  dao's method to increment votes
					CandidateDaoImpl candidateDao=(CandidateDaoImpl)session.getAttribute("candidate_dao");
					pw.print("<h5> "+candidateDao.incrementVotes(candidateId)+"</h5>");
				}
			} else
				pw.print("<h4>No Cookies : Session Tracking Failed!!!!!!!!!!!!!!!!!!!!!!</h4>");
			//Invalidate HttpSession
			session.invalidate();
			pw.print("<h5> You have logged out....</h5>");

		} catch (Exception e) {
			throw new ServletException("err in do-get of "+getClass(), e);
		}
	}

}
