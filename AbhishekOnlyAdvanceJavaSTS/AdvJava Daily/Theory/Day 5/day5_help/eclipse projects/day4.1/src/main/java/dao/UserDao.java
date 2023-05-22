package dao;

import java.sql.SQLException;

import pojos.User;

public interface UserDao {
//add a method for user's sign in
	User authenticateUser(String email,String password) throws SQLException;
	//add a method for changing voting status
	String changeVotingStatus(int voterId) throws SQLException;
}
