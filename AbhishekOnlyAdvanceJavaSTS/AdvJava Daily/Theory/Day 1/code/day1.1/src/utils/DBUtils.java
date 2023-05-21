package utils;

import java.sql.*;

public class DBUtils {
	private static Connection cn;

	// write static method to open db connection
	public static Connection openCustomConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/advjava?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
		cn = DriverManager.getConnection(url, "root", "root");
		return cn;
	}
}
