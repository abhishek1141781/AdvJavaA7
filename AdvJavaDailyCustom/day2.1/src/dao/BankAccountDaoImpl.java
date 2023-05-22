package dao;

import java.sql.*;
import static utils.DBUtils.*;

public class BankAccountDaoImpl implements BankAccountDao {
	//state
	private Connection cn;
	private CallableStatement cst1;
	//def ctor 
	public BankAccountDaoImpl() throws SQLException{
		// open cn
		cn=openConnection();
		//cst1
		cst1=cn.prepareCall("{call transfer_funds_proc(?,?,?,?,?)}");
		//register OUT params
		cst1.registerOutParameter(4, Types.DOUBLE);
		cst1.registerOutParameter(5, Types.DOUBLE);
		System.out.println("bank acct dao created!");
	}

	@Override
	public String transfterFunds(int srcAcctNo, int destNo, double amount) throws SQLException {
		// set IN params
		cst1.setInt(1, srcAcctNo);
		cst1.setInt(2, destNo);
		cst1.setDouble(3, amount);
		//exec a stored proc : public boolean execute() throws SE
		cst1.execute();	
		return "Upadated src balance "+cst1.getDouble(4) + "dest bal "+cst1.getDouble(5);
	}
	public void cleanUp() throws SQLException
	{
		if(cst1 != null)
			cst1.close();
		closeConnection();
		System.out.println("acct dao cleaned up!");
	}

}
