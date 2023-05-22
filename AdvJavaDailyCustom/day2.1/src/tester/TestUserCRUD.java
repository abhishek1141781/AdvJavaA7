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
				System.out.println("1. Display selected users\n" + "2. Sign Up\n" + "3. UN subscribe User\n"
						+ "4. Update details of User\n"
//											  + "5. "
						+ "0.Exit");
				System.out.println("Choose option");
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Enter role , begin end date");
						System.out.println("First in Tester CRUD\n");
						userDao.getSelectedUserDetails(sc.next(), Date.valueOf(sc.next()), Date.valueOf(sc.next()))
								.forEach(System.out::println);
						break;
					case 2:
						System.out.println(
								"Enter new user details : first_name | last_name | email | password | reg_amt | reg_date | role");
						System.out.println("Reg status : " + userDao.registerNewUser(new User(sc.next(), sc.next(),
								sc.next(), sc.next(), sc.nextDouble(), Date.valueOf(sc.next()), sc.next())));
						break;

					case 3:
						System.out.println("Enter user id to un subscribe");
						System.out.println("Status " + userDao.deleteUserDetails(sc.nextInt()));
						break;

					case 4:
						System.out.println("Update details of User: ");
//						first print all the existing details upon entering the userId
						System.out.println("Enter the user id to first print the current existing details: ");
						int usrId=sc.nextInt();
						userDao.displayUserDetailOnId(usrId).forEach(System.out::println);
//						then proceed on to run the second prepared statement to take inputs and update the details
//						 first_name | last_name | email | role
						System.out.println("Enter new user details : first_name | last_name | email | role: ");
						userDao.updateUserDetails(new User(sc.next(), sc.next(), sc.next(), sc.next(),usrId));

						System.out.println("Details after updation: ");
						userDao.displayUserDetailOnId(usrId).forEach(System.out::println);

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
