package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static utils.DBUtils.*;

import pojos.User;

public class UserDaoImpl implements UserDao {
	// state :
	private Connection cn;
	private PreparedStatement pst1;

	// def ctor , invoked by Tester(layer above) , exactly once ,from init phase
	public UserDaoImpl() throws SQLException {
		// get cn from DBUtils
		cn = openConnection();
		// create PST1 : sql
		pst1 = cn.prepareStatement("select * from users where role=? and reg_date between ? and ?");
		System.out.println("user dao created!");
	}

	@Override
	public List<User> getSelectedUserDetails(String role, Date start, Date end) throws SQLException {
		// create empty AL
		List<User> users = new ArrayList<>();
		// set IN params
		pst1.setString(1, role);
		pst1.setDate(2, start);
		pst1.setDate(3, end);
		// exec : execQuery
		try (ResultSet rst = pst1.executeQuery()) {
			/*
			 * int userId, String firstName, String lastName, String email, String password,
			 * double regAmount, Date regDate, String role
			 */
			while (rst.next())
				users.add(new User(rst.getInt(1), rst.getString(2), 
						rst.getString(3), rst.getString(4),
						rst.getString(5), rst.getDouble(6), rst.getDate(7), role));
		}//rst.close()
		return users;//dao rets list of users to Tester
	}

	// clean up of DB resources , invoked by Tester , once , in the shut down of app
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		closeConnection();
		System.out.println("user dao cleaned up....");
	}

}
