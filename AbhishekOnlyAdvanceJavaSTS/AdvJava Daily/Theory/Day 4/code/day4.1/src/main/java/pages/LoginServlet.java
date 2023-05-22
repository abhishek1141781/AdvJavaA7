package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoImpl;
import pojos.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/authenticate", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		System.out.println("in init of " + getClass());
		try {
			// create user dao instance
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			// re throw the exc to the caller (WC) --so that WC DOES NOT continue with
			// service phase => centralized exc handling in servlets
			throw new ServletException("err in init " + e.getMessage(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// dao cleanup
		try {
			userDao.cleanUp();
		} catch (SQLException e) {
			throw new RuntimeException("err in destroy of " + getClass() + " " + e.getMessage(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set cont type
		response.setContentType("text/html");
		// get PW to send text resp
		try (PrintWriter pw = response.getWriter()) {
			// read req params
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			// invoke DAO's method
			User user = userDao.authenticateUser(email, password);
			if (user == null)
				pw.print("<h5> Invalid Login , Please <a href='login.html'>Retry</a></h5>");
			else //login successful
			{
				//create a cookie to store validated user dtls
				Cookie c1=new Cookie("user_details", user.toString());
				
				//add the cookie to resp hdr
				response.addCookie(c1);
			//	pw.print("<h5>Successful Login , User Details "+user+"</h5>");
				// add authorization logic
				if (user.getRole().equals("admin"))
					// redirect the clnt in the NEXT req to the admin page
					response.sendRedirect("admin_main");
				else { // voter : user
					if (user.isStatus()) // => alrdy voted !!!
						response.sendRedirect("logout");
					else // voter : not yet voted
						response.sendRedirect("candidate_list");
					//WC sends temp redirect resp
					//SC 302 --headers : location : candidate_list , set-cookie --user_details : voter details
					//body : EMPTY
					//if cookies are accepted -- def age=-1 --cookie stored in browser's cache
					//next req : hdr : cookie : user_details : voter details
				}
			}
		} catch (Exception e) {
			throw new ServletException("err in do-post" + getClass(), e);
		}
	}

}
