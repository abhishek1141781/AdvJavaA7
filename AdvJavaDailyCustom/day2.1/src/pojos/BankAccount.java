package pojos;

//id | name | type | bal
public class BankAccount {
	private int id;
	private String customerName;
	private String acType;
	private double balance;
	public BankAccount() {
		// TODO Auto-generated constructor stub
	}
	public BankAccount(int id, String customerName, String acType, double balance) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.acType = acType;
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAcType() {
		return acType;
	}
	public void setAcType(String acType) {
		this.acType = acType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", customerName=" + customerName + ", acType=" + acType + ", balance="
				+ balance + "]";
	}
	

}
