package dao;

import java.sql.SQLException;

import pojos.User;

public interface UserDao {
//add a method declaration for user login(auth)
	User authenticateUser(String email,String password) throws SQLException;
}
