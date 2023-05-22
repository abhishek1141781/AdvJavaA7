package tester;

import java.sql.Date;
import java.util.Scanner;

import dao.UserDaoImpl;
import pojos.User;

public class TestUserCRUD {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create dao instance
			UserDaoImpl userDao = new UserDaoImpl();// init phase
			boolean exit = false;
			while (!exit) {
				System.out.println("1. Display selected users 2. Sign Up 3. UN subscribe User 0.Exit");
				System.out.println("Choose option");
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Enter role , begin end date");
						userDao.getSelectedUserDetails(sc.next(), Date.valueOf(sc.next()), Date.valueOf(sc.next()))
								.forEach(System.out::println);
						break;
					case 2:
						System.out.println(
								"Enter new user details : first_name | last_name | email | password | reg_amt | reg_date | role");
						System.out.println("Reg status : "+userDao.registerNewUser(new User(sc.next(), sc.next(), sc.next(), sc.next(),
								sc.nextDouble(), Date.valueOf(sc.next()), sc.next())));
						break;
						
					case 3 :System.out.println("Enter user id to un subscribe");
					System.out.println("Status "+userDao.deleteUserDetails(sc.nextInt()));
						break;

					case 0:
						exit = true;
						userDao.cleanUp();
						break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					sc.nextLine();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
