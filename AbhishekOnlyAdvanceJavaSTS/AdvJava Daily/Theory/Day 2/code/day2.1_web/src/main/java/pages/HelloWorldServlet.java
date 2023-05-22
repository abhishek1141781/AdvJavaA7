package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet{
	//override init , destroy , doGet

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in do-get "+Thread.currentThread());//nm , prio , grp
		//set content type 
		resp.setContentType("text/html");
		//to send text resp using buffer : PrintWriter
		try(PrintWriter pw=resp.getWriter())
		{
			pw.print("<h3>Hello from servlets @ "+LocalDateTime.now()+"</h3>");
		}
	}

	@Override
	public void destroy() {
		System.out.println("in destroy "+Thread.currentThread());//nm , prio , grp
	}

	@Override
	public void init() throws ServletException {
		System.out.println("in init "+Thread.currentThread());//nm , prio , grp
	}

	
}
