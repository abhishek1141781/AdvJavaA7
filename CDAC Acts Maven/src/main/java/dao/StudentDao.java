package dao;

import java.util.List;

import pojos.Student;
import pojos.Course;

public interface StudentDao {
// declare an abstract method to register students in cdac acts
	String registerStudents(Student transientStudent);
	
//	2. Student login
//	o/p : login successful , with student details lifted from db or failure mesg.
	String studentLogin(String email, String pass);

//	3. Get all students from a specific course
	List<Student> listStudentsFromCourse(Course course);
	
//	4. Offer scholarship to a particular  student
	Student offerScholarship(int studId, double scholarshipDisc);
}
