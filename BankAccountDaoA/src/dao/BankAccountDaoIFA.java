package dao;

import java.sql.SQLException;

public interface BankAccountDaoIFA {
//	add a method to transfer funds
	public String transferFunds(int sAccNo,int dAccNo,double amt) throws SQLException;
}
