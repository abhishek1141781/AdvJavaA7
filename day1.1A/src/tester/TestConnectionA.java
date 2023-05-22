package tester;

import java.sql.Connection;
import java.sql.SQLException;

import utils.DButilsA;

public class TestConnectionA {
	public static void main(String[] args) {
//	call the openCustomConnection method to establish a connection with the database
		
		String url="jdbc:mysql://localhost:3306/advjava";
		try {
			Connection cn = DButilsA.openCustomConnection();
			System.out.println("Got a new connection to the advjava DB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main over");
	}
}
