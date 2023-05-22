package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */

public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@Override
	public void destroy() {
		System.out.println("in destroy2");
	}


	@Override
	public void init() throws ServletException {
		System.out.println("in init2");
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do-get2");
		// set cont type
		response.setContentType("text/html");
		// get PW
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h4> Resp from a servlet , deployed using "
					+ "xml tags " + LocalDateTime.now() + "</h4>");
		}
	}

}
