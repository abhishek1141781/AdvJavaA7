package dao;

import java.sql.SQLException;

public interface BankAccountDao {
//add a method to transfer fund
	String transfterFunds(int srcAcctNo, int destNo, double amount) throws SQLException;
}
