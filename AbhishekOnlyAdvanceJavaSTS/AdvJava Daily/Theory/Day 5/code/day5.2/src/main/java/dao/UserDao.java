package dao;

import java.sql.SQLException;

import pojos.User;

public interface UserDao {
//add a method declaration for user login(auth)
	User authenticateUser(String email,String password) throws SQLException;
	//add a method for changing the voting status
	String updateVotingStatus(int voterId) throws SQLException;
}
