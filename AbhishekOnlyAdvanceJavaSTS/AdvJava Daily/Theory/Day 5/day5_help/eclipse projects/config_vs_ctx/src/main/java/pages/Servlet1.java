package pages;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet1
 */
/*@WebServlet(
		urlPatterns = { "/s1" }, 
		initParams = { 
				@WebInitParam(name = "test", value = "1234")
		})*/
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Servlet1() {
		System.out.println("in def ctor : config : "+getServletConfig());//null
	//	System.out.println("context "+getServletContext());//what will happen ? NPE
	}
	//add paramterized ctor
	public Servlet1(String mesg) {
		System.out.println("in paramterized ctor....");//will not be invoked!!!!
	}
	
	public void init() throws ServletException {
		ServletConfig sc=getServletConfig();
		System.out.println("in init of s1: init param "+sc.getInitParameter("test1"));//1234
		ServletContext ctx=getServletContext();
		System.out.println("ctx param "+ctx.getInitParameter("test2"));//5678
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(" s1");
	}

}
