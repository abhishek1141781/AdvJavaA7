package dao;

import java.sql.*;
import static utils.DBUtils.*;

import pojos.User;

public class UserDaoImpl implements UserDao {
	// state (props)
	private Connection cn;
	private PreparedStatement pst1;

	// def ctor : invoked by layer above(tester) , once , in init phase
	public UserDaoImpl() throws SQLException {
		// get cn from db utils
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
		//exec : execQuery
		try(ResultSet rst=pst1.executeQuery()){
			/*
			 * int id, String firstName, String lastName,
			 *  String email, String password, Date dob, boolean status,
			String role
			 */
			if(rst.next())
				return new User(rst.getInt(1), rst.getString(2), rst.getString(3),
						email, password, rst.getDate(6), 
						rst.getBoolean(7), rst.getString(8));
		}
		return null;
	}

	// add a method to clean up dao
	public void cleanUp() throws SQLException {
		// close psts
		if (pst1 != null)
			pst1.close();
		closeConnection();
	}
}
