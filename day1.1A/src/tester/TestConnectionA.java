package tester;

import java.sql.Connection;
import java.sql.SQLException;

import utils.DButilsA;

public class TestConnectionA {
	public static void main(String[] args) {
//	call the openCustomConnection method to establish a connection with the database
		
		String url="jdbc:mysql://localhost:3306/advjava";
		try {
			Connection cnTest = DButilsA.openCustomConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
