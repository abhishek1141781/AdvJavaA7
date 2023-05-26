package pojos;

import java.time.LocalDate;

public class Student {
	private String firstName;
	private String lastName;
	private Course chosenCourse;
	private int score;
	private boolean admissionStatus;
	private LocalDate dob;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Student(String firstName, String lastName, Course chosenCourse, int score,
			LocalDate dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.chosenCourse = chosenCourse;
		this.score = score;
		this.dob = dob;
	}

	public Student(String firstName, String lastName, Course chosenCourse, int score) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.chosenCourse = chosenCourse;
		this.score = score;
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

	public Course getChosenCourse() {
		return chosenCourse;
	}

	public void setChosenCourse(Course chosenCourse) {
		this.chosenCourse = chosenCourse;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isAdmissionStatus() {
		return admissionStatus;
	}

	public void setAdmissionStatus(boolean admissionStatus) {
		this.admissionStatus = admissionStatus;
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", chosenCourse=" + chosenCourse
				+ ", score=" + score + ", admissionStatus=" + admissionStatus + ", dob=" + dob + "]";
	}
	
}
