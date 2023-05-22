package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.BankAccountA;
import pojos.UserA;
import utils.DButilsA;

public class UserDaoImplA implements UserDaoIFA{
	
//	state:
//	The declarations of Connection and PreparedStatement objects (cn, pst1, pst2, and pst3) are not written in the default constructor of the UserDaoImpl class because they are instance variables that are used across multiple methods in the class.
//	get the reference of a connection instance
	Connection cn;
//	create a prepared statement reference one for each query
	PreparedStatement getUserDetails,registerUser,deleteUser;
	
//	default ctor, invoked by the layer above(tester) only once during the init phase
//	The preparation of the PreparedStatement object pst1 is done in the constructor of the UserDaoImpl class instead of inside the getSelectedUserDetails method for a few reasons:
//		1. Code Reusability: By preparing the statement in the constructor, the prepared statement can be reused across multiple method calls. If the statement preparation were done inside the getSelectedUserDetails method, it would need to be repeated every time the method is called, leading to code duplication.
//		2. Performance: Preparing a statement involves an additional overhead as the database needs to parse and optimize the SQL query. By preparing the statement once during object creation, it avoids this overhead during subsequent method calls, resulting in improved performance.
//		3. Separation of Concerns: The constructor is responsible for initializing the necessary resources for the UserDaoImpl object, including the database connection and prepared statements. This separation of concerns allows the getSelectedUserDetails method to focus solely on retrieving selected user details without worrying about statement preparation.
//		4. Object Lifecycle: The constructor is invoked only once during the creation of the UserDaoImpl object. Since pst1 is needed in multiple method calls, it makes sense to prepare it once during object creation to ensure its availability throughout the object's lifecycle.
	
//	the following code is written inside the constructor of the current class and not in a method
	public UserDaoImplA() throws SQLException {
//		get connection from Dbuitls;
		cn=DButilsA.openCustomConnection();
//		initialize the pst1 to store a prepared statement
		getUserDetails=cn.prepareStatement("select * from users where role=? and reg_date between ? and ?");
		registerUser=cn.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)");
		deleteUser=cn.prepareStatement("delete from users where id=?");
		
		System.out.println("user dao created and prepared stmts compiled !!!!!!!!!");
	}
	

	@Override
	public String registerNewUser(UserA dto) throws SQLException {
//		set the IN params
		registerUser.setString(1, dto.getFirstName());
		registerUser.setString(2, dto.getLastName());
		registerUser.setString(3, dto.getEmail());
		registerUser.setString(4, dto.getPassword());
		registerUser.setDouble(5, dto.getRegAmt());
		registerUser.setDate(6, dto.getRegDate());
		registerUser.setString(7, dto.getRole());
//		executeUpdate returns no of rows affected
		int row=registerUser.executeUpdate();
		
		if(row==1)
			return "User added successfully!!!!!";
		else
			return "User not added";
	}

	@Override
	public String deleteNewUser(int custId) throws SQLException {
//		set IN params
		deleteUser.setInt(1, custId);
//		executeUpdate
		int row=deleteUser.executeUpdate();
		
		if(row==1)
			return "User deleted successfully!!!!!";
		else
			return "User not deleted";
	}

	//	implement and thus override the method to display users between two dates and with a specific role
	@Override
	public List<UserA> getSelectedUserDetails(String role, Date d1, Date d2) throws SQLException {
//		initialize an empty list to store the extracted user details
		List<UserA> users = new ArrayList<UserA>();
		
//		set the IN params of the pst1
		getUserDetails.setString(1, role);
		getUserDetails.setDate(2, d1);
		getUserDetails.setDate(3, d2);
		
//		then execute the prepared statement with set IN params and store the returned result set in a result set reference
//		open ResultSet in try with resources as it's autocloseable
		try(ResultSet rst = getUserDetails.executeQuery()){
//		writing a while loop to store the result set values in the arraylist
//			 id | first_name | last_name | email | password | reg_amt | reg_date | role
			while(rst.next()) {
				users.add(new UserA(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),
						rst.getString(5), rst.getDouble(6), rst.getDate(7),rst.getString(8)));
			}
		}
		return users;
	}
	
	
//	cleanup of DB resources, invoked by tester, once during shutdown of app
	public void cleanUp() throws SQLException {
		if(getUserDetails!=null)
			getUserDetails.close();
		if(registerUser!=null)
			registerUser.close();
		if(deleteUser!=null)
			deleteUser.close();
		DButilsA.closeCustomConnection();
		System.out.println("user dao cleaned up!!!!");
	}
}
