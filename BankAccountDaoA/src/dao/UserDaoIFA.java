package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import pojos.BankAccountA;
import pojos.UserA;

public interface UserDaoIFA {
//	declare methods to be over ridden in implementation classes
	
//	write a method decl to add a new bankAccount input vis User DTO
//	abstract methods can't be static
	public abstract String registerNewUser(UserA dto) throws SQLException;

//	delete user upon entering a customerID
	public abstract String deleteNewUser(int custId) throws SQLException;
	
//	get details of selected users between two specific dates with a specific role
	public abstract List<UserA> getSelectedUserDetails(String role, Date d1,Date d2) throws SQLException;
	
	
	
}
