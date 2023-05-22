package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static utils.DBUtils.getConnection;

import pojos.User;

public class UserDaoImpl implements UserDao {
	private Connection cn;
	private PreparedStatement pst1, pst2;

	// def ctor : get cn from utils , create pst1
	public UserDaoImpl() throws SQLException {
		// get db cn from DB utils
		cn = getConnection();
		pst1 = cn.prepareStatement("select * from users where email=? and password=?");
		pst2 = cn.prepareStatement("update users set status=? where id=?");
		System.out.println("user dao created !");
	}

	/*
	 * int userId, String firstName, String lastName, String email, String password,
	 * Date dob, boolean status, String role)
	 */
	@Override
	public User authenticateUser(String email, String password) throws SQLException {
		// set IN params
		pst1.setString(1, email);
		pst1.setString(2, password);
		// exec the query to ret ResultSet
		try (ResultSet rst = pst1.executeQuery()) {
			if (rst.next()) // => valid login
				return new User(rst.getInt(1), rst.getString(2), rst.getString(3), email, password, rst.getDate(6),
						rst.getBoolean(7), rst.getString(8));
		}
		return null;
	}

	@Override
	public String changeVotingStatus(int voterId) throws SQLException {
		// set In params
		pst2.setBoolean(1, true);
		pst2.setInt(2, voterId);
		// exec : DM : execUpdate
		int updateCount = pst2.executeUpdate();
		if (updateCount == 1)
			return "You have cast a vote successfully!";
		return "Casting vote failed!!!!!!!!!!!!!!!!!";

	}

	// add a method to clean db resource (psts)
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		System.out.println("user dao cleaned up !");
	}

}
