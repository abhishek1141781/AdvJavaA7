package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import pojos.Course;
import pojos.Student;
import utils.HibernateUtils;

public class StudentDaoImpl implements StudentDao {

//	Using getCurrentSession

	@Override
	public String registerStudents(Student transientStudent) {
//	transientStudent : TRANSIENT
		String mesg = "Sorry student not admitted";

//	get session from SF : use getCurrentSession
		Session session = HibernateUtils.getFactory().getCurrentSession();

//	begin a tx
		Transaction txn = session.beginTransaction();
		System.out.println("session is open: " + session.isOpen() + "conn with DB: " + session.isConnected());

		try {
//			CRUD : save
//			Session API: public Serializable save(Object o) throws HibExe
			Serializable id = session.save(transientStudent);
//			if success
			txn.commit();
			/*
			 * Hibernate performs auto dirty checking upon commit - --> session.flush() -->
			 * synchs up state of L1 cache with the DB DML (insert) session.close() --> l1
			 * cache is destroyed , db cn rets to the pool --> SO THAT : same db cn can be
			 * REUSED for any other clnt any other request
			 */
			System.out.println(
					"after commit: is session open " + session.isOpen() + "conn with DB: " + session.isConnected());

			mesg = "Student addmitted sucessfully!!!!!!! with id " + id;
		} catch (RuntimeException e) {
//			failure
			if (txn != null)
				txn.rollback();
			/*
			 * session.close() cache is destroyed , db cn rets to the pool --> SO THAT :
			 * same db cn can be REUSED for any other clnt any other request
			 */
			System.out.println(
					"on failure: is session open " + session.isOpen() + " conn with DB " + session.isConnected());// f f

//			rethrow the execption to the caller
			throw e;
		}
		// newStudent : DETACHED (detached from L1 cache , BUT still DB rec exists!)
		return mesg;
	}

//	3. Get all students from a specific course
	@Override
	public List<Student> listStudentsFromCourse(Course course) {
//		initilize a null ref list
		List<Student> list = null;
//		JPQL
		String jpql = "select s from Student s where s.courseName=:courses";

//		get session from sessionFactory of HibernateUtils: getCurrentSession
		Session session = HibernateUtils.getFactory().getCurrentSession();
		
//		begin a txn
		Transaction tx = session.beginTransaction();
		
//		populate the list inside the try catch block as exceptions may occur
		try {
//			move ahead to execute the query
			list = session.createQuery(jpql, Student.class)//Query<Student>
					.setParameter("courses", course).getResultList();
//			list: list of persistent entities : if success then commit
			tx.commit();
		} catch (RuntimeException e) {
			if(tx!=null) // shouldn't this be tx = null 
				tx.rollback();
			throw e;
		}
		return list; //list: list of detached entities
	}

	
	
	
	
	
	
	
	
	
//	4. Offer scholarship to a particular  student
//	in case of success : reduce admission fees by the scholarship amount n dusplay student details

	@Override
	public Student offerScholarship(int studId, double scholarshipDisc) {
//		initialize a null ref to store the updated student details
//		UPDATE table_name SET column1 = value1 WHERE id = input_id;

//		get session from sessionFactory
		Session session = HibernateUtils.getFactory().getCurrentSession();
		
//		begin tx
		Transaction tx = session.beginTransaction();
		
//		retrieve the student by the student id
		Student getStudent = session.get(Student.class, studId);
		
		if(getStudent!=null) {
//			Apply the scholarship discount
			getStudent.setAdmissionFees(getStudent.getAdmissionFees()-scholarshipDisc);
			
//			if success then commit the txn
			tx.commit();
			
//			return the updated student details
			return getStudent;
		}else {
//			student not found, rollback the tx
			tx.rollback();
			return null;
		}
		
	}

//		2. Student login
	@Override
	public String studentLogin(String email, String pass) {
		String mesg = "Not succesfull";
//		initialize a null ref to store the updated student details
//		UPDATE table_name SET column1 = value1 WHERE id = input_id;
		Student studLogin = null;
		
		String jpql = "select s from Student s where s.email=:em and s.password=:pwd";

//		get session from sessionFactory
		Session session = HibernateUtils.getFactory().getCurrentSession();
		
//		begin tx
		Transaction tx = session.beginTransaction();

		try {
			Student s = session.createQuery(jpql, Student.class).setParameter("em",email).setParameter("pwd", pass).getSingleResult();
			
			tx.commit();
			System.out.println(s);
			mesg="successfull";
		} catch (RuntimeException e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}
}