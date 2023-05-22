package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/test")
public class HelloServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in do-get "+Thread.currentThread());
		//set resp cont type
		resp.setContentType("text/html");
		//get PW to send buf text data from servlet ---> clnt
		try(PrintWriter pw=resp.getWriter())
		{
			pw.print("<h4>Hello from Servlet , Server TS "
		+LocalDateTime.now()+"</h4>");
		}

	}

	@Override
	public void destroy() {
		System.out.println("in destroy "+Thread.currentThread());

	}

	@Override
	public void init() throws ServletException {
		System.out.println("in init "+Thread.currentThread());
	}

}
