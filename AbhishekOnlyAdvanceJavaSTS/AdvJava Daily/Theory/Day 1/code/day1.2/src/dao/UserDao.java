package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import pojos.User;

public interface UserDao {
//Display complete name , reg date , reg amount for all users under
	// a specific role , reged between 2 dates (Retrieve with IN params)
	List<User> getSelectedUserDetails(String role, Date start, Date end) throws SQLException;
}
