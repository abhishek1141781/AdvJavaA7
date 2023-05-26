package tester;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import pojos.Course;
import pojos.Student;
import utils.HibernateUtils;

public class CDACScholarship {

	public static void main(String[] args) {
//		open scanner and sessionfactory to get factory facin try with resources block
		try (Scanner sc = new Scanner(System.in); SessionFactory sf = HibernateUtils.getFactory()) {
//			create a dao instance
			StudentDaoImpl dao = new StudentDaoImpl();
//			take input
//			firstName, lastName, email, password, confirmPassword,
//			courseName, admissionFees, dob
			System.out.println(
					"Enter the studentId and the scholarship amount: ");
			int studInt = sc.nextInt();
			double scholarship = sc.nextDouble();

			System.out.println(dao.offerScholarship(studInt, scholarship));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}