package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButilsA {
//	declare a static variable for connection so as to use the same connection through out the program
	private static Connection cn;

//	write a static method to open a database connection that returns a connection when called
	public static Connection openCustomConnection() throws SQLException {
//		store the url where the database is stored in a string
		String url = "jdbc:mysql://localhost:3306/advjava";
//		call
		cn = DriverManager.getConnection(url, "root", "mansys");
		System.out.println("db connection opened at port 3306");
		return cn;
	}

//	write a method to close the db connection
	public static void closeCustomConnection() throws SQLException {
		if (cn != null) {
			cn.close();
			System.out.println("db connection closed!!!");
		}
	}
}
