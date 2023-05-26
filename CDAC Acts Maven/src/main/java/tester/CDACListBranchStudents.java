package tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDaoImpl;
import pojos.Course;
import utils.HibernateUtils;

public class CDACListBranchStudents {

	public static void main(String[] args) {
//		open scanner and sessionfactory to get factory facing try with resources block
		try(Scanner sc = new Scanner(System.in);
				SessionFactory sf = HibernateUtils.getFactory()) {
//			create a dao instance
			StudentDaoImpl dao = new StudentDaoImpl();
			
			System.out.println("Enter the branch name for which to list all studs: DAC,DBDA,DESD,DITISS,DAI");
			dao.listStudentsFromCourse(Course.valueOf(sc.next().toUpperCase())).forEach(System.out::println);
		} //sf.close sc.close
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

