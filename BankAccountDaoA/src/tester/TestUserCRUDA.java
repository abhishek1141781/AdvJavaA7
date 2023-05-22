package tester;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDaoImplA;
import pojos.UserA;

public class TestUserCRUDA {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			//create dao instance
			UserDaoImplA userDao = new UserDaoImplA();
			boolean exit=false;
			while(!exit) {
				System.out.println("1. Display selected users\n 2. Sign Up\n 3. UN subscribe User\n 4.Update user details\n 0.Exit\n");
				System.out.println("Choose option");
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Enter role, begin and end date");
						userDao.getSelectedUserDetails(sc.next(), Date.valueOf(sc.next()), Date.valueOf(sc.next())).forEach(i -> System.out.println(i));
						break;
					case 2:
						System.out.println("Enter new user details : first_name | last_name | email | password | reg_amt | reg_date | role");
//						System.out.println("Reg status: "+userDao.registerNewUser(new UserA(sc.next(),sc.next(),sc.next(),sc.next(),sc.nextDouble(),Date.valueOf(sc.next()),sc.next())));
						System.out.println("Reg status : "+userDao.registerNewUser(new UserA(sc.next(), sc.next(), sc.next(), sc.next(),
								sc.nextDouble(), Date.valueOf(sc.next()), sc.next())));
					case 3:
						System.out.println("Enter userid to unsubscribe");
						System.out.println("Status "+userDao.deleteNewUser(sc.nextInt()));
						break;
						
					case 0:
						exit=true;
						userDao.cleanUp();
						break;
					default:
						break;
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
