package tester;

import java.sql.*;

public class TestConnection {

	public static void main(String[] args) {
		try {
			// load JDBC driver : OPTIONAL
		//	Class.forName("com.mysql.cj.jdbc.Driver");
			// establish DB connection
			// API of java.sql.DriverManager : Connection getConnection(url,usernm,pwd)
			// throws SQLExc.
			String url="jdbc:mysql://localhost:3306/advjava?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
			try(Connection cn=DriverManager.getConnection(url,"root","root")){
				System.out.println("Connected to DB "+cn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("main over...");
	}
}