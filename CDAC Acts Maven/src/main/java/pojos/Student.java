package pojos;

import java.time.LocalDate;

import javax.persistence.*;
//	first name , last name,email,password,confirmPassword,course(enum), admission fees (double) ,dob 

// to tell the hibernate that this is and entity class and it's lifecycle has to be managed by it's framework
/**
 * @author abhis
 *
 */
@Entity
@Table(name = "dac_students") // for specifying table name
public class Student {

	@Id // to tell it's the PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // => auto increment
	@Column(name = "stud_id")
	private int studentId;
	@Column(name = "first_name", length = 30)
	private String firstName;

	@Column(name = "last_name", length = 30)
	private String lastName;

	@Column(length = 30, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Transient // skip from the persistence, no column for confirm password
	private String confirmPassword;

	@Enumerated(EnumType.STRING)
	@Column(name = "course")
	private Course courseName;

	@Column(name = "admiss_fees")
	private double admissionFees;

	private LocalDate dob;

//	generate an empty default constructor
	public Student() {
		super();
	}

//	 generate a constructor to be used
	public Student(String firstName, String lastName, String email, String password, String confirmPassword,
			Course courseName, double admissionFees, LocalDate dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.courseName = courseName;
		this.admissionFees = admissionFees;
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", courseName=" + courseName + ", admissionFees=" + admissionFees + ", dob=" + dob + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Course getCourseName() {
		return courseName;
	}

	public void setCourseName(Course courseName) {
		this.courseName = courseName;
	}

	public double getAdmissionFees() {
		return admissionFees;
	}

	public void setAdmissionFees(double admissionFees) {
		this.admissionFees = admissionFees;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public int getStudentId() {
		return studentId;
	}

	public Student(int studentId, double admissionFees) {
		super();
		this.studentId = studentId;
		this.admissionFees = admissionFees;
	}
	
	
}
