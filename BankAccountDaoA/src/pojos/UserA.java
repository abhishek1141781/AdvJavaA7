package pojos;

import java.sql.Date;

public class UserA {
//	fields of the database of users table
//	 id | first_name | last_name | email | password | reg_amt | reg_date | role
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private double regAmt;
	private Date regDate;
	private String role;
	
	
	public UserA() {
		// not used in JDBC , BUT required in hibernate
		super();
	}


	public UserA(int userId, String firstName, String lastName, String email, String password, double regAmt, Date regDate,
			String role) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.regAmt = regAmt;
		this.regDate = regDate;
		this.role = role;
	}
	
	public UserA(String firstName, String lastName, String email, String password, double regAmt, Date regDate,
			String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.regAmt = regAmt;
		this.regDate = regDate;
		this.role = role;
	}


	//getters n setters : MUST for hibernate
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
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


	public double getRegAmt() {
		return regAmt;
	}


	public void setRegAmt(int regAmt) {
		this.regAmt = regAmt;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", regAmt=" + regAmt + ", regDate=" + regDate + ", role=" + role + "]";
	}
	
	
}
