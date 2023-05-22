package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Team;

public interface TeamDao {
	//add a method to fetch selected details(tem id , abbrevation) from the teams table
	List<Team> getAllTeams() throws SQLException;
	
}
