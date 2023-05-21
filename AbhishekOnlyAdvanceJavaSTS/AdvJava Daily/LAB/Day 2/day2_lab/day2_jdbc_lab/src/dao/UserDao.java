package dao;

import java.sql.SQLException;

import pojos.User;

public interface UserDao {
//add a method for user authentication
	User authenticateUser(String email,String password) throws SQLException;
}
