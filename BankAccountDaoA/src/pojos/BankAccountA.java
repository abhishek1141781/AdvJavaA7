package pojos;

//id | name | type | bal
public class BankAccountA {

	private int id;
	private String customerName;
	private String accType;
	private double balance;
	
	
	public BankAccountA(int id, String customerName, String accType, double balance) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.accType = accType;
		this.balance = balance;
	}


	
	public BankAccountA() {
		super();
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


	public String getAccType() {
		return accType;
	}


	public void setAccType(String accType) {
		this.accType = accType;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}



	@Override
	public String toString() {
		return "BankAccountA [id=" + id + ", customerName=" + customerName + ", accType=" + accType + ", balance="
				+ balance + "]";
	}
	
	
	
}

