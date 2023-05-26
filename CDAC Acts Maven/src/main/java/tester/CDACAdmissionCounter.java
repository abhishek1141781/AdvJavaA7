package tester;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import pojos.Course;
import pojos.Student;
import utils.HibernateUtils;

public class CDACAdmissionCounter {

	public static void main(String[] args) {
//		open scanner and sessionfactory to get factory facin try with resources block
		try (Scanner sc = new Scanner(System.in); SessionFactory sf = HibernateUtils.getFactory()) {
//			create a dao instance
			StudentDaoImpl dao = new StudentDaoImpl();
//			take input
//			firstName, lastName, email, password, confirmPassword,
//			courseName, admissionFees, dob
			System.out.println(
					"Enter: firstName, lastName, email, password, confirmPassword,courseName, admissionFees, dob");
			Student stud = new Student(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), Course.valueOf(sc.next().toUpperCase()),
					sc.nextDouble(), LocalDate.parse(sc.next()));

			System.out.println(dao.registerStudents(stud));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}