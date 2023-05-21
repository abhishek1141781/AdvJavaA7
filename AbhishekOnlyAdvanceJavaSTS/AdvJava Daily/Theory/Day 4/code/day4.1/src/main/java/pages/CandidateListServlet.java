package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			//get cookie/s from the req hdr
			Cookie[] cookies=request.getCookies();
			//display user details : retrieved from a cookie
			if(cookies != null)//cookies recvd from the clnt
			{
				for(Cookie c : cookies)
					if(c.getName().equals("user_details"))
						pw.print("<h4> Voter Email "+c.getValue()+"</h4>");
			}
			else //=> no cookies!
				pw.print("<h4> Session Tracking Failed!!!!!! , No cookies!!!!!</h4>");
			
			
			
		}
	}
	

}
