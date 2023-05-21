package tester;

import java.util.Scanner;

import dao.UserDaoImpl;
import pojos.User;

public class ValidateUser {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)) {
			//create dao instance
			UserDaoImpl dao=new UserDaoImpl();
			//validate user
			System.out.println("Enter email n pwd");
			User user = dao.authenticateUser(sc.next(), sc.next());
			if(user== null)
				System.out.println("Invalid Login!!!!!!!!!");
			else
				System.out.println("Login success, Your details "+user);
			//before closing the app, clean up dao
			dao.cleanUp();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
