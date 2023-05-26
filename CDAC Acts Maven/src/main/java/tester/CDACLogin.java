package tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import utils.HibernateUtils;

public class CDACLogin {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); SessionFactory sf = HibernateUtils.getFactory()) {
//			create a dao instance
			StudentDaoImpl dao = new StudentDaoImpl();
//			take input
//			firstName, lastName, email, password, confirmPassword,
//			courseName, admissionFees, dob
			System.out.println(
					"Enter the email and password: ");
			
			System.out.println(dao.studentLogin(sc.next(),sc.next()));
		} catch (Exception e) {
			e.printStackTrace();
		}		// TODO Auto-generated method stub

	}

}
