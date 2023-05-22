package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeamDaoImpl;
import pojos.Team;

/**
 * Servlet implementation class AddPlayerFormServlet
 */
@WebServlet(value="/add_player_form",loadOnStartup = 1)
public class AddPlayerFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamDaoImpl teamDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		// create team dao instance
		try {
			teamDao = new TeamDaoImpl();
		} catch (SQLException e) {
			throw new ServletException("err in init " + getClass(), e);
		}

	}	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			teamDao.cleanUp();
		} catch (SQLException e) {
			// NO need of re throwing it to WC : since end of life cycle
			System.out.println("err in destroy " + e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// get teams data from dao layer
			List<Team> allTeams = teamDao.getAllTeams();
			// dyn form generation
			pw.print("<h3 align='center'>Player Selection Form</h3>");
			pw.print("<form action='validate'>");
			pw.print("<h5>");
			pw.print("Choose Team : <select name='team_id'>");
			for (Team team : allTeams)
				pw.print("<option value='" + team.getTeamId() 
				+ "'>" + team.getAbbrevation() + "</option>");
			pw.print("</select>");
			pw.print("</h5>");
			pw.print("<h5>Player Name <input type='text' name='player_name'/></h5>");
			pw.print("<h5>Player DoB <input type='date' name='dob'/></h5>");
			
			pw.print("<h5>Batting Avg <input type='number' name='avg'/></h5>");
			
			pw.print("<h5>Wickets Taken <input type='number' name='wickets'/></h5>");
			pw.print("<h5><input type='submit' value='Add Player'/></h5>");
			pw.print("</form>");

		} catch (Exception e) {
			throw new ServletException("err in doGet " + getClass(), e);
		}
	}

}
