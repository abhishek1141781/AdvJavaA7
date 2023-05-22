package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//add the class level anno , meant for server side run time :WC
@WebServlet("/hello")
public class FirstServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in do-get "+Thread.currentThread());
		//set resp cont type
		resp.setContentType("text/html");
		//get PW to send text resp from servlet ---> clnt
		try(PrintWriter pw=resp.getWriter()){
			pw.print("hello from servlet , server TS "+LocalDateTime.now());
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
//init , destroy , doGet
}
