package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButilsA {
//	declare a variable to store a reference to a database connection that can be used by other methods in the DBUtils class..... 
//	why is the following varaible static ?
//	By declaring it as static, the variable is shared across all instances of the class, ensuring that the same connection is used throughout the application's lifecycle.
private static Connection cn;
	

	
//	write a static method to make/open a database connection
//	why only a static method btw why not a normal method ?
	public static Connection openCustomConnection() throws SQLException {
		String url="jdbc:mysql://localhost:3306/advjava";
		Connection cn=DriverManager.getConnection(url, "root", "mansys");
		return cn;
	}
}
