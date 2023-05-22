package utils;

import java.sql.*;

public class DBUtils {
	private static Connection connection;

	// add a static method to open db conn
	public static void openConnection(String dbUrl,String userName,String pwd) throws SQLException{
	//	String url = "jdbc:mysql://localhost:3306/acts?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
		connection = DriverManager.getConnection(dbUrl, userName,pwd);
		System.out.println("db cn opened");
	}
	//add a method to close db conn
	public static void closeConnection() throws SQLException
	{
		if(connection != null)
			connection.close();
		System.out.println("db cn closed");
	}
	public static Connection getConnection() {
		return connection;
	}
	

}
