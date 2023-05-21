package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static utils.DBUtils.*;

import pojos.User;

public class UserDaoImpl implements UserDao {
	// state :
	private Connection cn;
	private PreparedStatement pst1, pst2,pst3;

	// def ctor , invoked by Tester(layer above) , exactly once ,from init phase
	public UserDaoImpl() throws SQLException {
		// get cn from DBUtils
		cn = openConnection();
		// create PST1 : sql
		pst1 = cn.prepareStatement("select * from users where role=? and reg_date between ? and ?");
		pst2 = cn.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)");
		pst3=cn.prepareStatement("delete from users where id=?");
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
				users.add(new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),
						rst.getString(5), rst.getDouble(6), rst.getDate(7), role));
		} // rst.close()
		return users;// dao rets list of users to Tester
	}

	@Override
	public String registerNewUser(User dto) throws SQLException {
		// set IN params
		/*
		 *  first_name | last_name | email | password | reg_amt | reg_date | role
		 */
		pst2.setString(1, dto.getFirstName());
		pst2.setString(2, dto.getLastName());
		pst2.setString(3, dto.getEmail());
		pst2.setString(4, dto.getPassword());
		pst2.setDouble(5, dto.getRegAmount());
		pst2.setDate(6, dto.getRegDate());
		pst2.setString(7, dto.getRole());
		//exec method : public int executeUpdate() throws SE
		int rowCount=pst2.executeUpdate();
		if(rowCount == 1)
			return "User registered successfully!";

		return "User reg failed!!!!";
	}
	

	@Override
	public String deleteUserDetails(int userId) throws SQLException {
		// set IN param
		pst3.setInt(1, userId);
		//exec method : exec update
		int rows=pst3.executeUpdate();
		if(rows == 1)
			return "User un subscribed  successfully!";

		return "User un subscription failed!!!!";
	}

	// clean up of DB resources , invoked by Tester , once , in the shut down of app
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		closeConnection();
		System.out.println("user dao cleaned up....");
	}

}
