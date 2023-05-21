package dao;

import java.sql.*;

import static utils.DBUtils.*;

import pojos.User;

public class UserDaoImpl implements UserDao {
	// state
	private Connection cn;
	private PreparedStatement pst1;

	// def ctor : invoked by Tester/ Servlet
	public UserDaoImpl() throws SQLException {
		// open cn
		cn = openConnection();
		// pst1
		pst1 = cn.prepareStatement("select * from users2 where email=? and password=?");
		System.out.println("user dao created...");
	}

	@Override
	public User authenticateUser(String email, String password) throws SQLException {
		// set IN params
		pst1.setString(1, email);
		pst1.setString(2, password);
		//exec : executeQuery--> RST
		try(ResultSet rst=pst1.executeQuery())
		{
			/*
			 * (int id, String firstName, String lastName, String email, String password, Date dob,
			boolean votingStatus, String role)
			 */
			if(rst.next())
				return new User(rst.getInt(1), rst.getString(2), rst.getString(3), email, password,
						rst.getDate(6),rst.getBoolean(7), rst.getString(8));
		}
		
		return null;
	}

	// add a method to clean up db resources
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		closeConnection();
		System.out.println("user dao cleaned up....");
	}

}
