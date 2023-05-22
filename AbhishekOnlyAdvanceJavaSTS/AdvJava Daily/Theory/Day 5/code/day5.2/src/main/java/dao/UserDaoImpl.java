package dao;

import java.sql.*;
import static utils.DBUtils.*;

import pojos.User;

public class UserDaoImpl implements UserDao {
	// state (props)
	private Connection cn;
	private PreparedStatement pst1, pst2;

	// def ctor : invoked by layer above(tester) , once , in init phase
	public UserDaoImpl(String url,String name,String password) throws SQLException {
		// get cn from db utils
		cn = openConnection(url,name,password);
		// pst1
		pst1 = cn.prepareStatement("select * from users2 where email=? and password=?");
		pst2 = cn.prepareStatement("update users2 set status=1 where id=?");
		System.out.println("user dao created...");
	}

	@Override
	public User authenticateUser(String email, String password) throws SQLException {
		// set IN params
		pst1.setString(1, email);
		pst1.setString(2, password);
		// exec : execQuery
		try (ResultSet rst = pst1.executeQuery()) {
			/*
			 * int id, String firstName, String lastName, String email, String password,
			 * Date dob, boolean status, String role
			 */
			if (rst.next())
				return new User(rst.getInt(1), rst.getString(2), rst.getString(3), email, password, rst.getDate(6),
						rst.getBoolean(7), rst.getString(8));
		}
		return null;
	}

	@Override
	public String updateVotingStatus(int voterId) throws SQLException {
		// Set N param
		pst2.setInt(1, voterId);
		// exec : execUpdate
		int rowCount = pst2.executeUpdate();
		if (rowCount == 1)
			return "Voting status updated....";
		return "Voting status updation failed!!!!!!!";
	}

	// add a method to clean up dao
	public void cleanUp() throws SQLException {
		// close psts
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		closeConnection();
	}

}
