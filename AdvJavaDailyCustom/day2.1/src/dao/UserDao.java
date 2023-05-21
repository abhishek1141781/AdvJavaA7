package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import pojos.User;

public interface UserDao {
//Display complete name , reg date , reg amount for all users under
	// a specific role , reged between 2 dates (Retrieve with IN params)
	List<User> getSelectedUserDetails(String role, Date start, Date end) throws SQLException;
	
	// add a method to register new user
	String registerNewUser(User dto) throws SQLException;
	
	// add a method to delete user details
	String deleteUserDetails(int userId) throws SQLException;
	
	// add a update method to update user details of first_name | last_name | email | password | role | id
	
	//display user details based on the id provided by the user
	List<User> displayUserDetailOnId(int userId) throws SQLException;
	// return the new updated details of the respective user
	String updateUserDetails(User dto) throws SQLException;
	
	
}
