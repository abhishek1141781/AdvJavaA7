package tester;

import java.util.Scanner;

import dao.UserDaoImpl;

public class AuthenticateUser {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("Enter email n password");
			System.out.println(dao.authenticateUser
					(sc.next(), sc.next()));
			//clean up dao
			dao.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
