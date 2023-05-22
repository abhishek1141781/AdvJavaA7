package tester;

import java.sql.Date;
import java.util.Scanner;

import dao.UserDaoImpl;

public class TestUserCRUD {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create dao instance
			UserDaoImpl userDao = new UserDaoImpl();// init phase
			boolean exit = false;
			while (!exit) {
				System.out.println("1. Display selected users 0.Exit");
				System.out.println("Choose option");
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Enter role , begin end date");
						userDao.getSelectedUserDetails(sc.next(), Date.valueOf(sc.next()), Date.valueOf(sc.next()))
								.forEach(System.out::println);
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
